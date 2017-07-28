SUMMARY = "Tools for managing the Ocfs2 cluster file system"
DESCRIPTION = "Programs to manage the Ocfs2 cluster file system, including mkfs.ocfs2, \
tunefs.ocfs2 and fsck.ocfs2.\
Ocfs2 is a general purpose extent based shared disk cluster file \
system. It supports 64 bit inode numbers, and has automatically \
extending metadata groups which may also make it attractive for \
non-clustered use. Ocfs2 leverages some well tested kernel \
technologies, such as JBD - the same journaling subsystem in use by \
ext3."
HOMEPAGE = "http://oss.oracle.com/projects/ocfs2-tools/"
SECTION = "System Environment/Base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "git://oss.oracle.com/git/ocfs2-tools.git \
    file://0003-vendor-common-o2cb.ocf-add-new-conf-file.patch \
    file://cluster.conf.sample \
    file://o2cb.service \
    file://ocfs2.service \
"
SRCREV = "0b8be47d61dbdcd08d21c83f0b3993735b884ef9"
S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig systemd

DEPENDS = "corosync cluster-glue openais pacemaker \
    libxml2 linux-libc-headers libaio \
    e2fsprogs e2fsprogs-native \
"

ASNEEDED_pn-${PN} = ""
PARALLEL_MAKE = ""
INSANE_SKIP_${PN} = "unsafe-references-in-binaries"
CFLAGS_append += "-DGLIB_COMPILATION"
CPPFLAGS_append += "-DGLIB_COMPILATION"

EXTRA_OECONF = " \
    --enable-ocfs2console=no \
    --enable-dynamic-fsck=yes \
    --enable-dynamic-ctl=yes \
"

do_configure_prepend () {
        # fix here or EXTRA_OECONF
        sed -i -e '/^PYTHON_INCLUDES="-I/c\
PYTHON_INCLUDES="-I=/usr/include/python${PYTHON_BASEVERSION}"' \
                ${S}/pythondev.m4
        sed -i  -e 's:PYTHON_PREFIX/lib/python:PYTHON_PREFIX/${baselib}/python:' \
            -e 's:PYTHON_EXEC_PREFIX}/lib/python:PYTHON_EXEC_PREFIX}/${baselib}/python:' \
                ${S}/python.m4

        # fix the AIS_TRY_PATH which will search corosync|openais
        # AIS_TRY_PATH=":/usr/lib64/:/usr/lib:/usr/local/lib64:/usr/local/lib"
        sed -i -e '/^AIS_TRY_PATH=":\/usr\/lib64:/s;:;:=;g' ${S}/configure.in
}


do_compile_prepend() {
    for m in `find . -name "Makefile"` ; do
        sed -i -e "s@-I/usr/include@-I${STAGING_DIR_TARGET}/usr/include@g" $m
    done
}

SYSTEMD_SERVICE_${PN} = "o2cb.service ocfs2.service"

do_install() {
    install -d ${D}/etc/init.d
    install vendor/common/o2cb.init ${D}/etc/init.d/o2cb
    install vendor/common/ocfs2.init ${D}/etc/init.d/ocfs2
    install -d ${D}/etc/sysconfig
    install vendor/common/o2cb.sysconfig ${D}/etc/sysconfig/o2cb
    install -d ${D}/etc/udev/rules.d
    install vendor/common/51-ocfs2.rules ${D}/etc/udev/rules.d/51-ocfs2.rules
    install -d ${D}/${libdir}/ocf/resource.d/ocfs2
    install  -m 0755 vendor/common/o2cb.ocf ${D}/${libdir}/ocf/resource.d/ocfs2/o2cb
    oe_runmake DESTDIR="${D}" install
    chmod 644 ${D}/${libdir}/*.a
    install -dm 0755  ${D}/etc/ocfs2
    install -m 0644 ${WORKDIR}/cluster.conf.sample ${D}/etc/ocfs2/cluster.conf.sample
    rm -rf ${D}/${libdir}/ocf
    rm -rf ${D}/sbin/ocfs2_controld.pcmk
    rm -rf ${D}/sbin/ocfs2_controld.cman
    # fix up hardcoded paths
    sed -i -e 's,/usr/lib/,${libdir}/,' ${WORKDIR}/o2cb.service
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}/${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/o2cb.service ${D}/${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/ocfs2.service ${D}/${systemd_unitdir}/system
        install -d ${D}/${libexecdir}
        install -m 755 vendor/common/o2cb.init ${D}/${libexecdir}/o2cb-helper
    fi
}

RDEPENDS_${PN} = "bash coreutils net-tools module-init-tools e2fsprogs chkconfig glib-2.0"
