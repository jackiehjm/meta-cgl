#inherit core-image
#For the moment just inherit enea-image-demo.
include images/enea-image-minimal.bb


VALGRIND ?= ""
VALGRIND_powerpc ?= "valgrind"
VALGRIND_e500v2 ?= ""
VALGRIND_x86 ?= "valgrind"
VALGRIND_x86_64 ?= "valgrind"
VALGRIND_armv7a ?= "valgrind"

ENEA_GPL = " \
    linx \
    linx-mod \
           "
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
LM_SENSORS = " \
    lmsensors-libsensors \
    lmsensors-sensors \
    lmsensors-sensord \
    lmsensors-fancontrol \
    lmsensors-sensorsdetect \
    lmsensors-sensorsconfconvert \
    lmsensors-pwmconfig \
    lmsensors-isatools \
    lmsensors-config-libsensors \
    lmsensors-config-sensord \
    lmsensors-config-fancontrol \
    lmsensors-config-cgi \
             "

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
    ${ENEA_GPL} \
    ${VALGRIND} \
    kernel-modules \
    fuse \
    gdb \
    gdbserver \
    rsync \
    strace \
    libevent \
    mdadm \
    ntp \
    quota \
    smartmontools \
    strongswan \
    vlan \
    ${DHCP} \
    ${MULTIPATH_TOOLS} \
    ${LM_SENSORS} \
    monit \
    "


# kexec-tools doesn't work on Mips
KEXECTOOLS_mips ?= ""
KEXECTOOLS_mipsel ?= ""

IMAGE_FEATURES += "splash ssh-server-openssh hwcodecs package-management"
IMAGE_FEATURES += "eclipse-debug tools-debug tools-profile dbg-pkgs"
