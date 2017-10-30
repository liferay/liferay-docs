# Managing Image Resolutions [](id=managing-image-resolutions)

Although Adaptive Media automatically generates images for resolutions when a 
new image is uploaded, this behavior can be disabled if you wish. If you need to 
change the values of an image resolution, you can delete the image resolution 
and create a new one with the appropriate values. See the [Deleting Image 
Resolutions](#deleting-image-resolutions) section for more information.

## Disabling Image Resolutions [](id=disabling-image-resolutions)

To disable an image resolution, click the *Actions* icon
(![Actions](../../../../images/icon-actions.png)) next to the image resolution and 
select *Disable*. Any images uploaded after the resolution is disabled use the 
most appropriate image resolution that is still active. Images previously 
generated with the disabled image resolution are still available.

## Enabling Image Resolutions [](id=enabling-image-resolutions)

To enable a disabled image resolution, click the *Actions* icon next to the 
image resolution and select *Enable*. Note that while an image resolution is 
disabled it doesn't generate images for new uploads. After enabling an image 
resolution, it's recommended that you generate the images that may have been 
missed. See the [Generating Missing Image 
Resolutions](#generating-missing-image-resolutions) for instructions.

## Recycling Adapted Images [](id=recycling-adapted-images)

Adapted images can't be moved to the Recycle Bin explicitly. If the original 
image is in the Recycle Bin, the corresponding adapted images behave as if they 
are in the Recycle Bin and can't be viewed by users.

+$$$

**NOTE:** Adaptive Media URLs that point to adapted images whose original image 
is in the Recycle Bin return an error code of *404 Not Found*.

$$$

When the original image is restored from the Recycle Bin, the adapted images are 
available and accessible again.

## Deleting Image Resolutions [](id=deleting-image-resolutions)

You can also delete image resolutions. **When an image resolution is deleted, 
the existing images generated for that resolution are deleted as well and can no 
longer be used or referenced in any content. This operation can't be undone**. 

**To delete a resolution, it must be disabled first.** This prevents image 
resolutions from being accidentally deleted. Once the resolution is disabled, 
select *Delete* from the Actions menu to delete the resolution and all images 
that have been generated for the resolution.

## Generating Missing Image Resolutions [](id=generating-missing-image-resolutions)

There may be cases where an image resolution hasn't generated all the images 
that you require. For example, if you disable an image resolution and upload new 
images, or perhaps you add a new image resolution that hasn't been applied to 
existing images. In these scenarios, a resolution's percentage of adapted images 
is less than 100%. 

![Figure 1: A resolution's percentage of adapted images is shown under the *Adapted Images* column.](../../../../images/adaptive-media-coverage.png)

You can adapt the missing images. Each image resolution can be adapted 
individually by selecting the *Adapt Remaining* option from the Actions menu 
beside the resolution. Alternatively, You can adapt all image resolutions at 
once by selecting the *Adapt All Images* option from the Actions menu in the 
Control Menu at the top of the page.  
