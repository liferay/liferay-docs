# Adding Image Resolutions [](id=adding-image-resolutions)

To use Adaptive Media, you must first define the resolutions for the images 
delivered to users' devices. Adaptive Media then generates new images scaled to
fit those resolutions, while maintaining the original aspect ratio. 

To access Adaptive Media settings, open the Control Panel and go to 
*Configuration* &rarr; *Adaptive Media*. Here you can create and manage 
resolutions. 

+$$$

**Note:** Adaptive Media configurations apply only to the current @product@ 
instance. 

$$$

Once you create a resolution, Adaptive Media automatically generates copies of 
newly uploaded images in that resolution. Images uploaded before you create the 
resolution aren't affected and must be adapted separately (see the 
[Generating Missing Image Resolutions](/discover/portal/-/knowledge_base/7-1/managing-image-resolutions#generating-missing-image-resolutions) 
section for details). 

## Adding a New Image Resolution [](id=adding-a-new-image-resolution)

The number of image resolutions required and the values for each depend on the 
use case. More resolutions may optimize image delivery, but generating 
more images requires additional computational resources and storage space. To 
start, we recommend that you create resolutions to cover common device sizes 
like mobile phones, tablets, laptops, and desktops. If most users use one device 
(e.g., all Intranet users have the same company mobile phone), you can create a 
resolution to target that device. 

To add a new resolution, click the *Add* icon 
(![Add new resolution](../../../images/icon-add.png)) on the Adaptive Media 
configuration page and provide the following information: 

-   **Name**: The resolution's name (this must be unique). This can be 
    updated if a custom `Identifier` is defined. 
-   **Max Width**: The generated image's maximum width. If a *Max Height* is 
    given, this field is optional. This value must be at least `1`.
-   **Maximum Height**: The generated image's maximum height. If a *Max Width* 
    is given, this field is optional. This value must be at least `1`.

    Adaptive Media generates images that fit the Max Width and Max Height, while 
    retaining the original aspect ratio. If you only provide one value (either 
    Max Width or Max Height), the generated image scales proportionally to fit 
    within the specified dimension, while maintaining its original aspect ratio. 
    This ensures that adapted images are not distorted. 

-   **Add a resolution for high density displays (2x):** Defines a scaled up
    resolution for HIDPI displays. Selecting this option creates a new
    resolution double the size of the original with the same name and the
    suffix `-2x`. For example, if the original resolution is `400px` by `300px`
    (max width by max height), the high density resolution is `800px` by 
    `600px`. 

-   **Identifier:** The resolution's ID. By default, this is automatically 
    generated from the name. You can specify a custom identifier by selecting 
    the *Custom* option and entering a new *ID*. Third party applications can 
    use this ID to obtain images for the resolution via Adaptive Media's APIs. 

+$$$

**Note:** Image resolutions and their identifiers can't be updated if the 
resolution has been used to adapt images. This prevents inconsistencies in 
generated images. 

$$$

![Figure 1: The form for adding a new Adaptive Media resolution.](../../../images/adaptive-media-new-img-resolution.png)
