SUMMARY = "The Corosync Cluster Engine and Application Programming Interfaces"
DESCRIPTION = "This package contains the Corosync Cluster Engine Executive, \
several default APIs and libraries, default configuration files, and an init \
script."
HOMEPAGE = "http://corosync.github.io/corosync/"

inherit autotools pkgconfig update-rc.d useradd cgl-calc

SRC_URI = "http://build.clusterlabs.org/corosync/releases/${BPN}-${PV}.tar.gz \
           file://groff-desc-path.patch \
           file://corosync-docs.patch \
           file://fix-define-semun-union.patch \
           file://build-cleanup-configure-ac.patch \
           file://corosync.init \
           file://notifyd.init \
           file://volatiles \
          "

SRC_URI[md5sum] = "da9b2cf0b55f08ac4cf7cd82ac2f977a"
SRC_URI[sha256sum] = "c1d005b2093e9a725abd1dfb2be936114b561c0b3145aca11f58c1a733a05af8"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=25656171d1e4054c636a9893067f8c30"

DEPENDS = "groff-native nss"

INSANE_SKIP_${PN} += ""

EXTRA_OECONF += " --enable-nss "
CFLAGS_append += " -fPIC "

do_compile() {
    DESCPATH="${STAGING_DATADIR_NATIVE}/groff/`groff -v | awk '{if(NR==1)print $4}'`/font"
    oe_runmake DESCPATH=${DESCPATH}
}

do_install_append() {
    install -d ${D}${sysconfdir}/default/volatiles
    install -m 0644 ${WORKDIR}/volatiles ${D}${sysconfdir}/default/volatiles/05_corosync

    # Original init script is too bashy
    rm -f ${D}/${sysconfdir}/init.d/corosync
    install -m 0755 ${WORKDIR}/corosync.init ${D}/${sysconfdir}/init.d/corosync
    rm -rf ${D}/${sysconfdir}/init.d/corosync-notifyd
    install -m 0755 ${WORKDIR}/notifyd.init ${D}/${sysconfdir}/init.d/corosync-notifyd

    rm -fr "${D}${localstatedir}/lock"
    rm -fr "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

FILES_${PN} += "run"
FILES_${PN}-dbg += "${libexecdir}/lcrso/.debug"

INITSCRIPT_NAME = "corosync"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "haclient"
USERADD_PARAM_${PN} = "-M --home  ${localstatedir}/lib/heartbeat -g haclient hacluster"

CGL_ID="CFH.2.0,CCM.2.2"
PRIORITY="P1,P1"
DISCLOSURE="${PN},${PN}"


