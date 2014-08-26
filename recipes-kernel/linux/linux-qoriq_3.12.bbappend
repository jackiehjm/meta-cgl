require cgl-common.inc

SRC_URI += ""

DELTA_KERNEL_DEFCONFIG += "cfg/00001-systemtap.cfg \
                           cfg/00002-oprofile.cfg \
                           cfg/00003-lttng.cfg \
                           cfg/00004-kgdb.cfg \
                           cfg/00005-quota.cfg \
                           cfg/00007-device-mapper-multipath.cfg \
                           cfg/00008-libcgroup.cfg \
                           cfg/00009-filesystem-acl.cfg \
                           cfg/00010-ext4.cfg \
                           cfg/00011-raid-1.cfg \
                          "
