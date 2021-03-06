DESCRIPTION = "libwww-perl provides a simple and consistent API to the World Wide Web"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"
DEPENDS = "liburi-perl-native libhtml-parser-perl-native libhtml-tagset-perl-native"
RDEPENDS_${PN} += " \
	libhtml-parser-perl \
	libhtml-tagset-perl \
	liburi-perl \
	perl-module-digest-md5 \
	perl-module-net-ftp \
	"
BBCLASSEXTEND = "native"

PR = "r1"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/G/GA/GAAS/libwww-perl-${PV}.tar.gz;name=libwww-perl-${PV}"
SRC_URI[libwww-perl-6.05.md5sum] = "637d5f1eb61336ca2caa6e026b382f87"
SRC_URI[libwww-perl-6.05.sha256sum] = "7b25799ff7eec18e8e4e97dc0cad7b2a5b433b50b13feb59d9179173bee78f23"

S = "${WORKDIR}/libwww-perl-${PV}"

inherit cpan

PACKAGE_ARCH = "all"
