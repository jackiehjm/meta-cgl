SUMMARY = "Carrier Grade Linux (CGL)"
DESCRIPTION = "Application packages required to satisfy the Carrier Grade Linux (CGL) specification"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-cgl/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup


PACKAGES = "packagegroup-cgl-applications"

RDEPENDS_packagegroup-cgl-applications = ""

RRECOMMENDS_packagegroup-cgl-applications = ""
