#inherit core-image
#For the moment just inherit enea-image-demo.
include images/enea-image-minimal.bb

# Include modules in rootfs
IMAGE_INSTALL += "\
    ${CORE_IMAGE_BASE_INSTALL} \
    packagegroup-core-full-cmdline \
    packagegroup-core-lsb \
    packagegroup-core-buildessential \
    packagegroup-cgl \
	"
IMAGE_FEATURES += "splash ssh-server-openssh hwcodecs package-management"
