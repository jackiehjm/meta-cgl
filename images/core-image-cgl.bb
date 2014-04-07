require recipes-extended/images/core-image-lsb.bb


VALGRIND ?= ""
VALGRIND_powerpc ?= "valgrind"
VALGRIND_e500v2 ?= ""
VALGRIND_x86 ?= "valgrind"
VALGRIND_x86_64 ?= "valgrind"
VALGRIND_armv7a ?= "valgrind"

# Include modules in rootfs
IMAGE_INSTALL += "\
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    packagegroup-core-buildessential \
    packagegroup-core-selinux \
    packagegroup-cgl \
    kexec-tools \
    lttng-tools \
    lttng-modules \
    ${VALGRIND} \
    "

# kexec-tools doesn't work on Mips
KEXECTOOLS_mips ?= ""
KEXECTOOLS_mipsel ?= ""

IMAGE_FEATURES += "eclipse-debug tools-debug tools-profile dbg-pkgs"
