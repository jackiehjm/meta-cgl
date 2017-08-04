SUMMARY = "The Corosync Cluster Engine and Application Programming Interfaces"
DESCRIPTION = "This package contains the Corosync Cluster Engine Executive, \
several default APIs and libraries, default configuration files, and an init \
script."
HOMEPAGE = "http://corosync.github.io/corosync/"

inherit autotools pkgconfig update-rc.d useradd systemd

SRC_URI = "http://build.clusterlabs.org/corosync/releases/${BPN}-${PV}.tar.gz \
           file://groff-desc-path.patch \
           file://corosync-docs.patch \
           file://fix-define-semun-union.patch \
           file://build-cleanup-configure-ac.patch \
           file://corosync.init \
           file://notifyd.init \
           file://corosync.service \
           file://corosync-notifyd.service \
           file://volatiles \
          "

SRC_URI[md5sum] = "e16c71db45c8b39948847655958d0dec"
SRC_URI[sha256sum] = "64ee50f783a4bfa8a63fd576090cf7dd1810f3c60e01b4886723b87c5ff2b013"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=25656171d1e4054c636a9893067f8c30"

DEPENDS = "groff-native"

INSANE_SKIP_${PN} += ""

CFLAGS_append += " -fPIC "

PACKAGECONFIG ?= "dbus nss snmp"
PACKAGECONFIG[dbus] = "--enable-dbus,--disable-dbus,dbus"
PACKAGECONFIG[nss] = "--enable-nss,--disable-nss,nss"
PACKAGECONFIG[rdma] = "--enable-rdma,--disable-rdma"
PACKAGECONFIG[snmp] = "--enable-snmp,--disable-snmp,net-snmp"

do_compile() {
    DESCPATH="${STAGING_DATADIR_NATIVE}/groff/`groff -v | awk '{if(NR==1)print $4}'`/font"
    oe_runmake DESCPATH=${DESCPATH}
}

do_install_append() {
    install -d ${D}${sysconfdir}/default/volatiles
    install -m 0644 ${WORKDIR}/volatiles ${D}${sysconfdir}/default/volatiles/05_corosync
    install -D -m 0644 ${S}/tools/corosync-notifyd.sysconfig.example ${D}${sysconfdir}/sysconfig/corosync-notifyd

    # Original init script is too bashy
    rm -f ${D}/${sysconfdir}/init.d/corosync
    install -m 0755 ${WORKDIR}/corosync.init ${D}/${sysconfdir}/init.d/corosync
    rm -rf ${D}/${sysconfdir}/init.d/corosync-notifyd
    install -m 0755 ${WORKDIR}/notifyd.init ${D}/${sysconfdir}/init.d/corosync-notifyd

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${datadir}/${BPN}
        cp ${D}/${sysconfdir}/init.d/* ${D}${datadir}/${BPN}/

        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/corosync.service ${D}${systemd_system_unitdir}
        sed -i -e 's,@DATADIR@,${datadir},g' ${D}${systemd_system_unitdir}/corosync.service

        install -m 0644 ${WORKDIR}/corosync-notifyd.service ${D}${systemd_system_unitdir}
        sed -i -e 's,@SYSCONFDIR@,${sysconfdir},g' \
            -e 's,@SBINDIR@,${sbindir},g' ${D}${systemd_system_unitdir}/corosync-notifyd.service

        install -d ${D}${sysconfdir}/tmpfiles.d
        echo "d ${localstatedir}/log/cluster - - - -" > ${D}${sysconfdir}/tmpfiles.d/corosync.conf
    fi

    rm -fr "${D}${localstatedir}/lock"
    rm -fr "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

FILES_${PN}-doc += "${datadir}/snmp/mibs/COROSYNC-MIB.txt"
FILES_${PN}-dbg += "${libexecdir}/lcrso/.debug"

INITSCRIPT_NAME = "corosync"
INITSCRIPT_PARAMS = "remove"

SYSTEMD_SERVICE_${PN} = "corosync.service corosync-notifyd.service"
SYSTEMD_AUTO_ENABLE = "disable"


USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "haclient"
USERADD_PARAM_${PN} = "-M --home  ${localstatedir}/lib/heartbeat -g haclient hacluster"
