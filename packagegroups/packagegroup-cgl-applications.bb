SUMMARY = "Application packages required to satisfy the Carrier Grade Linux (CGL) specification"
DESCRIPTION = "This package group includes the application with which the user interacts \
               when using a Linux operation system."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-cgl/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup
inherit pkgconfig


PACKAGES = "packagegroup-cgl-applications"

LM_SENSORS = " \
    lmsensors-fancontrol \
    lmsensors-libsensors \
    lmsensors-pwmconfig \
    lmsensors-sensord \
    lmsensors-sensors \
    lmsensors-sensorsconfconvert \
    lmsensors-sensorsdetect \
    lmsensors-config-cgi \
    lmsensors-config-libsensors \
    lmsensors-config-sensord \
    lmsensors-config-fancontrol \
    "

RDEPENDS_packagegroup-cgl-applications = " \
    lvm2 \
    ${LM_SENSORS} \
    bc \
    gettext \
    gettext-runtime \
    babeltrace \
    gdb \
    gdbserver \
    rsync \
    strace \
    libevent \
    mdadm \
    quota \
    smartmontools \
    monit \
    "

LTTNG ?= "\
    lttng-tools \
    lttng-modules \
    lttng-ust \
    "
LTTNG_armv6 ?= ""

LTTNGUST = "lttng-ust"
LTTNGUST_libc-uclibc = ""

RDEPENDS_${PN}_append_qemux86 = " valgrind lttng-ust"
RDEPENDS_${PN}_append_qemux86-64 = " ${LTTNGUST}"
RDEPENDS_${PN}_append_qemuppc = " ${LTTNGUST}"
RDEPENDS_${PN}_append_qemuarm = " ${LTTNGUST}"
RDEPENDS_${PN}_append_powerpc = " ${LTTNGUST}"

RRECOMMENDS_packagegroup-cgl-applications = ""
