FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://cfg/00001-systemtap.cfg \
            file://cfg/00002-oprofile.cfg \
            file://cfg/00003-lttng.cfg \
           "
