SUMMARY = "Carrier Grade Linux (CGL)"
DESCRIPTION = "Packages required to satisfy the Carrier Grade Linux (CGL) specification"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-cgl/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


inherit packagegroup


PACKAGES = "packagegroup-cgl"

RDEPENDS_packagegroup-cgl = "\
	packagegroup-cgl-kernel\
	packagegroup-cgl-sdtools\
	packagegroup-cgl-middleware\
	packagegroup-cgl-applications"

RRECOMMENDS_packagegroup-cgl = ""
