SUMMARY = "OCF resource agents for use by compatible cluster managers"
DESCRIPTION = "A set of scripts to interface with several services \
to operate in a High Availability environment for both Pacemaker and \
rgmanager service managers."
HOMEPAGE = "http://sources.redhat.com/cluster/wiki/"

LICENSE = "GPLv2+ & LGPLv2+ & GPLv3"
LICENSE_${PN} = "GPLv2+ & LGPLv2+"
LICENSE_${PN}-dev = "GPLv2+ & LGPLv2+"
LICENSE_${PN}-staticdev = "GPLv2+ & LGPLv2+"
LICENSE_${PN}-dbg = "GPLv2+ & LGPLv2+"
LICENSE_${PN}-doc = "GPLv2+ & LGPLv2+"
LICENSE_${PN}-extra = "GPLv3"
LICENSE_${PN}-extra-dbg = "GPLv3"
LICENSE_ldirectord = "GPLv2+"

SRC_URI = "https://codeload.github.com/ClusterLabs/resource-agents/tar.gz/v${PV};downloadfilename=${BPN}-${PV}.tar.gz \
           file://01-disable-doc-build.patch \
           file://02-set-OCF_ROOT_DIR-to-libdir-ocf.patch \
           file://03-fix-header-defs-lookup.patch \
          "

SRC_URI[md5sum] = "c59096b1bacc704e8a5a285f15729109"
SRC_URI[sha256sum] = "e5bd62658fbc236acb83b709f64b2cd9fae52aa4a420a44fed5eb667e928b152"

LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "cluster-glue"
RDEPENDS_${PN} += "bash"

inherit autotools systemd

EXTRA_OECONF += "--disable-fatal-warnings \
                 --with-rsctmpdir=/var/run/heartbeat/rsctmp"

do_install_append() {
    rm -rf "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

# tickle_tcp is published under GPLv3, we just split it into ${PN}-extra,
# and it's required by portblock, so move portblock into ${PN}-extra together.
PACKAGES_prepend  = "${PN}-extra ${PN}-extra-dbg ldirectord "
FILES_${PN}-extra = "${libdir}/resource-agents/heartbeat/tickle_tcp \
                     ${libdir}/ocf/resource.d/heartbeat/portblock \
                     ${datadir}/resource-agents/ocft/configs/portblock \
                    "
FILES_${PN}-extra-dbg += "${libdir}/resource-agents/heartbeat/.debug/tickle_tcp"

FILES_ldirectord = " \
        ${sbindir}/ldirectord \
        ${sysconfdir}/ha.d/resource.d/ldirectord \
        ${sysconfdir}/init.d/ldirectord \
        ${sysconfdir}/logrotate.d/ldirectord \
        ${libdir}/ocf/resource.d/heartbeat/ldirectord \
        "
FILES_ldirectord-doc = "${mandir}/man8/ldirectord.8*"

RDEPENDS_ldirectord += " \
        ipvsadm \
        libdbi-perl \
        libdigest-hmac-perl \
        libmailtools-perl \
        libnet-dns-perl \
        libsocket6-perl \
        libwww-perl \
        perl \
        perl-module-getopt-long \
        perl-module-net-ftp \
        perl-module-net-smtp \
        perl-module-pod-usage \
        perl-module-posix \
        perl-module-socket \
        perl-module-strict \
        perl-module-sys-hostname \
        perl-module-sys-syslog \
        perl-module-vars \
        "

SYSTEMD_PACKAGES = "ldirectord"
SYSTEMD_SERVICE_ldirectord += "ldirectord.service"

FILES_${PN} += "${datadir}/cluster/* \
                ${libdir}/ocf/resource.d/heartbeat/ \
                ${libdir}/ocf/lib/heartbeat/* \
                ${libdir}/ocf/resource.d/redhat \
                "

FILES_${PN}-dbg += "${libdir}/ocf/resource.d/heartbeat/.debug \
                    ${libdir}/resource-agents/heartbeat/.debug "
