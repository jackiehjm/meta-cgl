DESCRIPTION = "OSI Certified implementation of a complete cluster engine"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6ddd1b9ffefcca04866906195d8bda33"
DEPENDS = "groff-native"

#PR = "r1"

SRC_URI = " \
	https://github.com/corosync/corosync/archive/v1.2.8.tar.gz \
    file://fix-lcrso-linkage.patch \
	file://corosync-docs.patch \
	file://init \
	file://corosync.conf \
	file://volatiles \
	file://fix-define-semun-union.patch \
    file://build-cleanup-configure-ac.patch \
	"
SRC_URI[md5sum] = "ac4a0d206736f0827d419a03b238da4e"
SRC_URI[sha256sum] = "712248bf5698c0bb39c77c3f0fdaa493b92037069401121b801294487f638c26"


inherit autotools-brokensep pkgconfig update-rc.d

INITSCRIPT_NAME = "corosync-daemon"

EXTRA_OECONF = "--disable-nss"

FILES_${PN} += "run"
FILES_${PN}-dbg += "${libexecdir}/lcrso/.debug"

do_install_append() {
	install -d ${D}/${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/default/volatiles
	# Original init script is too bashy
	rm -f ${D}/${sysconfdir}/init.d/corosync
	install -m 0755 ${WORKDIR}/init ${D}/${sysconfdir}/init.d/corosync-daemon
	install -m 0644 ${WORKDIR}/corosync.conf ${D}/${sysconfdir}/corosync/corosync.conf.example
	install -m 0644 ${WORKDIR}/volatiles ${D}${sysconfdir}/default/volatiles/05_corosync
}

pkg_postinst_${PN} () {
	set -e
	grep haclient /etc/group || addgroup haclient
	grep hacluster /etc/passwd || adduser --disabled-password --home=/var/lib/heartbeat --ingroup haclient -g "HA cluster" hacluster
	/etc/init.d/populate-volatile.sh update
}
