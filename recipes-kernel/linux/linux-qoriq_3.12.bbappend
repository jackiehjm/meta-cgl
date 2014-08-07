require cgl-common.inc

SRC_URI += ""

DELTA_KERNEL_DEFCONFIG += "cfg/00001-systemtap.cfg \
                           cfg/00002-oprofile.cfg \
                           cfg/00003-lttng.cfg \
                          "
