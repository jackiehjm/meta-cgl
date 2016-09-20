SUMMARY = "Support for having multipath iSCSI devices as root file system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-cgl/COPYING.MIT;md5=838c366f69b72c5df05c96dff79b35f2"
SRC_URI = "file://init-boot.sh"

do_install() {
        install -m 0755 ${WORKDIR}/init-boot.sh ${D}/init
}

inherit allarch

RDEPENDS_${PN} += "multipath-tools kpartx iscsi-initiator-utils"

FILES_${PN} += " /init "
