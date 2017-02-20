DESCRIPTION = "Implementation of the Service Availability Forum Application Interface Specification (AIS)"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4cb00dd52a063edbece6ae248a2ba663"
DEPENDS = "cluster-glue corosync"


SRC_URI = " \
    http://tux.rainside.sk/gentoo/distfiles/openais-${PV}.tar.gz \
    file://fix-lcrso-linkage.patch \
    file://build-cleanup-configure-ac.patch \
    file://openais-fix-bash.patch \
	"
SRC_URI[md5sum] = "e500ad3c49fdc45d8653f864e80ed82c"
SRC_URI[sha256sum] = "974b4959f3c401c16156dab31e65a6d45bbf84dd85a88c2a362712e738c06934"

inherit autotools pkgconfig

FILES_${PN}-dbg += "${libexecdir}/lcrso/.debug"
