#inherit core-image
#For the moment just inherit enea-image-demo.
include images/enea-image-minimal.bb


VALGRIND ?= ""
VALGRIND_powerpc ?= "valgrind"
VALGRIND_e500v2 ?= ""
VALGRIND_x86 ?= "valgrind"
VALGRIND_x86_64 ?= "valgrind"
VALGRIND_armv7a ?= "valgrind"

# Include modules in rootfs
IMAGE_INSTALL += "\
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    ${CORE_IMAGE_BASE_INSTALL} \
    packagegroup-core-full-cmdline \
    packagegroup-core-lsb \
    packagegroup-core-buildessential \
    packagegroup-cgl \
    kexec-tools \
    openssl \
    bc \
    dhcp-client \
    sqlite3 \
    pramfs-init \
    zip \
    gettext \
    gettext-runtime \
    mtd-utils \
    net-tools \
    pciutils \
    ltp \
    libuio \
    usbutils \
    lttng-tools \
    lttng-modules \
    babeltrace \
    netbase \
    sudo \
    linx \
    linx-mod \
    ${VALGRIND} \
    kernel-modules \
    fuse \
    gdb \
    gdbserver \
    rsync \
    strace \
    "

# kexec-tools doesn't work on Mips
KEXECTOOLS_mips ?= ""
KEXECTOOLS_mipsel ?= ""

IMAGE_FEATURES += "splash ssh-server-openssh hwcodecs package-management"
IMAGE_FEATURES += "eclipse-debug tools-debug tools-profile dbg-pkgs"
