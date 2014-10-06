DESCRIPTION = "Implementation of the Service Availability Forum Application Interface Specification (AIS)"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4cb00dd52a063edbece6ae248a2ba663"
DEPENDS = "cluster-glue corosync"

#	ftp://ftp@openais.org/downloads/openais-${PV}/openais-${PV}.tar.gz

SRC_URI = " \
	ftp://ftp@tux.rainside.sk/gentoo/distfiles/openais-${PV}.tar.gz \
	file://fix-lcrso-linkage.patch \
    file://build-cleanup-configure-ac.patch \
	"
SRC_URI[md5sum] = "13d8d590f806fb396d750b086c6c0b78"
SRC_URI[sha256sum] = "eeef58dd2df3eb16ba68b3fbdc6f0d4dfb537443f1c091ec6f0431594f2f00b6"

inherit autotools pkgconfig

FILES_${PN}-dbg += "${libexecdir}/lcrso/.debug"
