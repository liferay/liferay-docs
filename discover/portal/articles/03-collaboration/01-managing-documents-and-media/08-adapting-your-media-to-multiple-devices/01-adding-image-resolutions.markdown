# Adding Image Resolutions [](id=adding-image-resolutions)

To use Adaptive Media, first you must define a set of image resolutions to 
deliver to the user's device. Adaptive Media then generates new images that are 
scaled to fit the desired resolutions and maintain their original aspect ratio. 

To access Adaptive Media settings, open the Control Panel and go to 
*Configuration* &rarr; *Adaptive Media*. Here you can create new resolutions or 
manage existing ones.

+$$$

**Note:** Adaptive Media configurations are scoped per virtual instance and 
apply to that instance only. 

$$$

Once you create a resolution, Adaptive Media automatically generates copies with 
that resolution for all images uploaded thereafter. Images uploaded before the 
resolution is created are not affected. To apply a new resolution to previously 
uploaded images, you must adapt the remaining images (see the [Generating 
Missing Image Resolutions](/discover/portal/-/knowledge_base/7-0/managing-image-resolutions#generating-missing-image-resolutions) 
section for details). 

## Adding a New Image Resolution [](id=adding-a-new-image-resolution)

The number of image resolutions required and the values for each resolution 
depends on the use case. The more image resolutions added, the more optimal the 
image delivery is, but this also requires more storage and computational 
resources to generate the images.

To start, it's recommended that you create resolutions to cover the common 
device sizes, such as mobile phones, tablets, laptops, and desktops.

If a majority of users are using one device (all the users of the intranet have 
the same company mobile phone for example), you can create a resolution to 
target that device. 

To add a new resolution, click the *Add* icon (![Add new 
resolution](../../../images/icon-add.png)) on the Adaptive Media configuration 
page and provide the information shown below for the resolution:

- **Name**: The resolution's name (this name must be unique). This can be 
updated if a custom `Identifier` is defined. <!-- Need to confirm this with 
Adolfo. This didn't seem to be the case when I tested. -->

- **Max Width**: The generated image's maximum width. If a `Max Height` is given, 
this field is optional. This value must be at least `1`.

- **Maximum Height**: The generated image's maximum height. If a `Max Width` is 
given, this field is optional. This value must be at least `1`.

Adapted images are generated to fit the dimensions of the `Max Width` and 
`Max Height`, while maintaining their original aspect ratio. If only `Max Width` 
or `Max Height` is given, the generated image scales proportionally to fit 
within the specified dimension, while maintaining its original aspect ratio. 
This ensures that adapted images are not distorted.

- **Add a resolution for high density displays (2x):** Sets whether to create an 
additional resolution for high density displays. If this option is selected, a 
new resolution for these devices is created with the same `Name` and the suffix 
`-2x`. This option is only available when adding new image resolutions. These 
resolutions have a width and height that is double the original. For example, if 
the original resolution has a maximum width of `400px` and a maximum height 
of `300px`, the high density resolution has a maximum width of `800px` and a 
maximum height of `600px`. 

- **Identifier:** The resolution's ID. This is automatically generated from the 
`Name` by default. You can specify a custom identifier by selecting the *Custom* 
option and entering a new *ID*. Third party applications can use this ID to 
obtain images for the resolution, via Adaptive Media's APIs. **Image resolutions 
and identifiers can't be updated if the resolution has been used to adapt 
images**. This prevents inconsistencies in generated images.

![Figure 1: Specify an Identifier for an image resolution to make it available to the APIs.](../../../../images/adaptive-media-new-img-resolution.png) 
