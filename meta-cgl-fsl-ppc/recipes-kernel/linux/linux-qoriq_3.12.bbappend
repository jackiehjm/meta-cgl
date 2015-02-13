require recipes-kernel/linux/cgl-common.inc

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
                        cfg/00012-hyper-threading.cfg \
                        cfg/00013-dot1q-vlans.cfg \
                        cfg/00014-selinux.cfg \
                        cfg/00015-managing-transient-data.cfg \
                        cfg/00016-dynamic-probe-insertion-kprobe.cfg \
                        cfg/00018-ipsec.cfg \
                        cfg/00019-raid-0.cfg \
                        cfg/00021-sctp.cfg \
                        cfg/00022-coredump.cfg \
                        cfg/00023-open-scsi.cfg \
                        cfg/00024-ftrace.cfg \
                        "
