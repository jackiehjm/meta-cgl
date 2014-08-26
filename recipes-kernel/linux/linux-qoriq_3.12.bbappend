require cgl-common.inc

SRC_URI += ""

DELTA_KERNEL_DEFCONFIG += "cfg/00001-systemtap.cfg \
                           cfg/00002-oprofile.cfg \
                           cfg/00003-lttng.cfg \
                           cfg/00004-kgdb.cfg \
                           cfg/00005-quota.cfg \
                           cfg/00007-device-mapper-multipath.cfg \
                          "
