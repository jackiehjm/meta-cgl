SUMMARY = "Middleware packages required to satisfy the Carrier Grade Linux (CGL) specification"
DESCRIPTION = "This package group contains high availability application and platform \
               interfaces, databases, application servers, communication protocols etc."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup


PACKAGES = "packagegroup-cgl-middleware"

DHCP = " \
    dhcp-server \
    dhcp-server-config \
    dhcp-client \
    dhcp-relay \
    dhcp-omshell \
    "

MULTIPATH_TOOLS = " \
    libmpathpersist \
    mpathpersist \
    kpartx \
    libmultipath \
    multipath \
    multipathd \
    libmultipath-dev \
    libmpathpersist-dev \
    "

RDEPENDS_packagegroup-cgl-middleware = "\
    ipsec-tools \
    net-snmp-server \
    net-snmp-client \
    net-snmp-libs \
    net-snmp-mibs \
    net-snmp-server-snmpd \
    net-snmp-server-snmptrapd \
    ${DHCP} \
    ${MULTIPATH_TOOLS} \
    openssl \
    mtd-utils \
    net-tools \
    pciutils \
    usbutils \
    netbase \
    ntp \
    strongswan \
    vlan \
    corosync \
    iscsi-initiator-utils \
    lksctp-tools \
    openais \
    openipmi \
    openhpi \
    pacemaker \
    cluster-glue \
    resource-agents \
    ifenslave \
    drbd-utils \
    openl2tp \
    umip \
    dmidecode \
    "

DISTRO_FEATURES_append = " ptest argp ext2 xattr nfs pci ipv4 ipv6"

RRECOMMENDS_packagegroup-cgl-middleware = ""
