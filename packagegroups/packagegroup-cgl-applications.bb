SUMMARY = "Carrier Grade Linux (CGL)"
DESCRIPTION = "Application packages required to satisfy the Carrier Grade Linux (CGL) specification"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-cgl/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup
inherit pkgconfig

PACKAGES = "packagegroup-cgl-applications"

RDEPENDS_packagegroup-cgl-applications = " \
    lvm2 \
    "

DISTRO_FEATURES_append = " ptest argp ext2 xattr nfs pci ipv4 ipv6"

LTTNG ?= "\
    lttng-tools \
    lttng-modules \
    lttng-ust \
    "
LTTNG_armv6 ?= ""

LTTNGUST = "lttng-ust"
LTTNGUST_libc-uclibc = ""

RDEPENDS_${PN}_append_qemux86 = " valgrind lttng-ust"
RDEPENDS_${PN}_append_qemux86-64 = " ${LTTNGUST}"
RDEPENDS_${PN}_append_qemuppc = " ${LTTNGUST}"
RDEPENDS_${PN}_append_qemuarm = " ${LTTNGUST}"
RDEPENDS_${PN}_append_powerpc = " ${LTTNGUST}"

RRECOMMENDS_packagegroup-cgl-applications = ""
