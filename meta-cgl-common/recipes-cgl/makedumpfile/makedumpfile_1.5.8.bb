DESCRIPTION = "Make dump file utility"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://sourceforge.net/projects/makedumpfile/files/makedumpfile/1.5.8/makedumpfile-${PV}.tar.gz;name=makedumpfile \
	   file://alias-powerpc-powerpc32.patch \
	   "

SRC_URI[makedumpfile.md5sum] = "642d975349dff744c6027d4486499258"
SRC_URI[makedumpfile.sha256sum] = "dd9c6c40c1ae6774b61bbe7b53f5ebbee9734f576d8ecb75ffb929288f5ea64d"

DEPENDS = "zlib elfutils bzip2"

EXTRA_OEMAKE = "TARGET=${TARGET_ARCH}"

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/makedumpfile ${D}${bindir}/
}
