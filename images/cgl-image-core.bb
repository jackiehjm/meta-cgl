#inherit core-image
#For the moment just inherit enea-image-demo.
include images/enea-image-demo.bb

# Include modules in rootfs
IMAGE_INSTALL += "\
    ${CORE_IMAGE_BASE_INSTALL} \
    packagegroup-core-basic \
    packagegroup-core-lsb \
    "

IMAGE_FEATURES += "splash ssh-server-openssh hwcodecs package-management"
