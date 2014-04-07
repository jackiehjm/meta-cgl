require lksctp-tools.inc

FILESEXTRAPATHS_prepend = "${THISDIR}/files:"
SRC_URI += " \
    ${SOURCEFORGE_MIRROR}/lksctp/lksctp-tools-${PV}.tar.gz \
    file://static-libraries-fix.patch \
    "

inherit pkgconfig autotools

SRC_URI[md5sum] = "047562b40537eb3be5d5ec3ae0a2198b"
SRC_URI[sha256sum] = "e920e89d110cff9847f1d86a1d7a23a156a8abcc9e65a35655d687fd08377595"

FILES_${PN}-dev += "${libdir}/lksctp-tools/*.so \
    ${libdir}/*.so \
    ${base_libdir}/*.so \
    ${base_libdir}/lksctp-tools/*.so \
    /usr/lib/lksctp-tools/*.so"
FILES_${PN}-staticdev = "${libdir}/lksctp-tools/*.a \
    ${libdir}/*.a \
    ${base_libdir}/*.a \
    ${base_libdir}/lksctp-tools/*.a \
    /usr/lib/lksctp-tools/*.a"
