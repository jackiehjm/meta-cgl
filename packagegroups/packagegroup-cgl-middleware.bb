SUMMARY = "Carrier Grade Linux (CGL)"
DESCRIPTION = "Middleware packages required to satisfy the Carrier Grade Linux (CGL) specification"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-cgl/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup


PACKAGES = "packagegroup-cgl-middleware"

RDEPENDS_packagegroup-cgl-middleware = "\
    ipsec-tools \
    net-snmp-server \
    net-snmp-client \
    net-snmp-libs \
    net-snmp-mibs \
    net-snmp-server-snmpd \
    net-snmp-server-snmptrapd \
    "

RRECOMMENDS_packagegroup-cgl-middleware = ""
