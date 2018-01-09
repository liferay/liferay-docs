# Advanced Configuration Options [](id=advanced-configuration-options)

Adaptive Media's advanced configuration options are available in System
Settings. Open the Control Panel and go to *Configuration* &rarr; *System
Settings*, then select the *Collaboration* tab. There are two configurations for
Adaptive Media: *Adaptive Media* and *Adaptive Media Image*. 

The *Adaptive Media* configuration affects all virtual instances and is related 
to Adaptive Media's asynchronous processing. These values can be modified to 
improve performance for specific scenarios or use cases. The following 
configuration options are available: 

-   **Max Processes:** The maximum number of processes for generating adapted 
    media. The default value is `5`. 

-   **Core Processes:** The number of processes always available for generating 
    adapted media. The default value is `2`. This setting can't exceed the *Max 
    processes* setting. 

+$$$

**Warning:** Larger values for Max Processes and Core Processes may cause out of 
memory errors, as processing more images at once can consume large amounts of 
memory. Out of memory errors can also occur if the source images Adaptive Media 
uses to generate adapted images are large. You can restrict the maximum size of 
such images via the *Max Image Size* setting in the *Adaptive Media Image* 
configuration, which is described next. You should run performance tests to 
optimize these settings for the amount of memory available on your system. 

$$$

The *Adaptive Media Image* configuration can be different for each virtual 
instance. The following configuration options are available: 

-   **Supported MIME Types:** A list of the image MIME types that Adaptive Media 
    supports. If an image is uploaded and its MIME type isn't in this list,
    Adaptive Media ignores the image. By default, this list contains many common
    MIME types. 

-   **Gifsicle:** To scale animated GIFs, Adaptive Media uses an external tool 
    called 
    [Gifsicle](https://www.lcdf.org/gifsicle/). First install Gifsicle on the 
    server, ensure that it's on the `PATH` environment variable, and then click 
    the box next to *Gifsicle Enabled*. If Gifsicle isn't installed and 
    `image/gif` is included as a supported MIME type, Adaptive Media scales only 
    one frame of the GIF, making a static GIF. 

-   **Max Image Size:** Maximum size of the source images that Adaptive Media 
    can use to generate adapted images. Adaptive Media will not generate adapted 
    images for source images larger than this setting. The default value is 10 
    MB. To generate adapted images for all source images regardless of size, set 
    this to -1. Since generating adapted images from large source images 
    requires significant amounts of memory, you can specify a lower *Max Image 
    Size* to avoid out of memory errors. 
