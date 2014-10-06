DESCRIPTION = "Scalable High-Availability cluster resource manager"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = " \
	bzip2 \
	cluster-glue \
	cluster-resource-agents \
	glib-2.0 \
	gnutls \
	libxml2 \
	libxslt \
	ncurses \
	openais \
	python-native \
	util-linux \
	zlib \
	"
RDEPENDS_${PN} += "openais"

#PR = "r2"

SRC_URI = " \
    https://github.com/ClusterLabs/pacemaker-1.0/archive/Pacemaker-${PV}.tar.gz \
    file://pacemaker-remove-native-includes.patch \
    file://pacemaker-dont-use-help2man.patch \
    file://fix-header-defs-lookup.patch \
    file://pacemaker-remove-ptest-functionality.patch \
	file://volatiles \
	"
SRC_URI_append_libc-uclibc = " file://kill-stack-protector.patch"
SRC_URI[md5sum] = "103fb2e804be3f8ace17021c5d9ad15d"
SRC_URI[sha256sum] = "aabfc9ee1c66804151d973d0ed0323798ffebe49e1c2219fa804dc6898a69a1e"
inherit autotools-brokensep python-dir

S = "${WORKDIR}/pacemaker-1.0-Pacemaker-${PV}"

EXTRA_OECONF = "--with-ais --without-heartbeat --disable-fatal-warnings --disable-pretty"

CFLAGS += "-I${STAGING_INCDIR}/heartbeat -lncurses"

do_install_append() {
	install -d ${D}${sysconfdir}/default/volatiles
	install -m 0644 ${WORKDIR}/volatiles ${D}${sysconfdir}/default/volatiles/06_pacemaker
	find ${D} -name "*.pyo" -exec rm {} \;
	find ${D} -name "*.pyc" -exec rm {} \;
	find ${D} -name "*.py" | xargs sed -i -e "s:${STAGING_BINDIR_NATIVE}:${bindir}:g"
}

pkg_postinst_${PN} () {
	set -e
	grep haclient /etc/group || addgroup haclient
	grep hacluster /etc/passwd || adduser --disabled-password --home=${localstatedir}/lib/heartbeat --ingroup haclient -g "HA cluster" hacluster
	/etc/init.d/populate-volatile.sh update
}
FILES_${PN}-doc += "${datadir}/pacemaker/crm_cli.txt ${datadir}/pacemaker/templates/"
FILES_${PN} += " \
    /run \
    ${libdir}/corosync \
    ${libdir}/python2.7 \
	${libdir}/service_crm.so \
	${libdir}/ocf/resource.d/pacemaker \
	${libdir}/heartbeat/attrd \
	${libdir}/heartbeat/c* \
	${libdir}/heartbeat/pengine \
	${libdir}/heartbeat/pingd \
	${libdir}/heartbeat/plugins/RAExec/stonith.so \
	${libdir}/heartbeat/stonithd \
	${datadir}/pacemaker/*.rng \
	"
FILES_${PN}-dbg += "${libdir}/heartbeat/.debug ${libdir}/heartbeat/plugins/RAExec/.debug/ ${libdir}/heartbeat/stonithdtest/.debug/ ${libexecdir}/lcrso/.debug ${libdir}/corosync/lcrso/.debug"
FILES_${PN}-dev += "${libdir}/heartbeat/plugins/RAExec/*.la"
FILES_${PN}-staticdev += "${libdir}/heartbeat/plugins/RAExec/*.a"

PACKAGES =+ "${PN}-crm ${PN}-hb2openais ${PN}-haresources2cib ${PN}-tests ${PN}-snmp"
FILES_${PN}-crm = "${sbindir}/crm ${PYTHON_SITEPACKAGES_DIR} ${libdir}/heartbeat/crm_primitive.py "
RDEPENDS_${PN}-crm += "python-core"
FILES_${PN}-hb2openais = "${libdir}/heartbeat/hb2openais.sh ${libdir}/heartbeat/hb2openais-helper.py"
RDEPENDS_${PN}-hb2openais += "python-core"
FILES_${PN}-haresources2cib = "${libdir}/heartbeat/haresources2cib.py"
RDEPENDS_${PN}-haresources2cib += "python-core"
FILES_${PN}-tests = "${datadir}/pacemaker/tests ${datadir}/pacemaker/stonithdtest ${libdir}/heartbeat/atest ${libdir}/heartbeat/stonithdtest/*"
RDEPENDS_${PN}-test += "python-core"
FILES_${PN}-snmp = "${datadir}/snmp/mibs/PCMK-MIB.txt"
