require recipes-kernel/linux/cgl-common.inc
require recipes-kernel/linux/linux-qoriq.inc

SRC_URI += "git://git.freescale.com/ppc/sdk/linux.git;nobranch=1 \
    file://powerpc-Fix-64-bit-builds-with-binutils-2.24.patch \
    file://Fix-CVE-2014-5077-sctp-inherit-auth-capable-on-INIT-collisions.patch \
"
SRCREV = "c29fe1a733308cbe592b3af054a97be1b91cf2dd"

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
                        cfg/00025-ipmi.cfg \
                        cfg/00026-lm-sensors.cfg \
                        cfg/00027-dmidecode.cfg \
                        "

