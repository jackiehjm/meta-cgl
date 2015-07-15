DESCRIPTION = "Test::Pod - check for POD errors in files"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"
HOMEPAGE = "https://github.com/perl-pod/test-pod/"
BBCLASSEXTEND = "native"
RDEPENDS_${PN} += " perl-module-test-more \
                    perl-module-file-spec \
                    perl-module-pod-simple \
                    perl-module-test-builder-tester \
                    "
PR = "r1"

SRC_URI ="http://search.cpan.org/CPAN/authors/id/E/ET/ETHER/Test-Pod-${PV}.tar.gz;name=test-pod-perl-${PV}"
SRC_URI[test-pod-perl-1.51.md5sum] = "f806aa84de2f0c0fba48b3a5a8a4aecf"
SRC_URI[test-pod-perl-1.51.sha256sum] = "c1a1d3cedf4a579e3aad89c36f9878a8542b6656dbe71f1581420f49582d7efb"
S = "${WORKDIR}/Test-Pod-${PV}"

inherit cpan

PACKAGE_ARCH = "all"
