# Advanced Configuration Options [](id=advanced-configuration-options)

Adaptive Media has some advanced configuration options that are available in 
System Settings. Open the Control Panel and go to *Configuration* &rarr; 
*System Settings*, then select the *Collaboration* tab. There are two 
configurations for Adaptive Media: *Adaptive Media* and *Adaptive Media Image*. 

The *Adaptive Media* configuration affects all virtual instances and is related 
to Adaptive Media's asynchronous processing. These values can be modified to 
improve performance for specific scenarios or use cases. The following 
configuration options are available: 

-   **Max processors:** The maximum number of processors (CPUs) that can 
    process adapted images. The default value is `10`. 

-   **Core processors:** The number of processors (CPUs) always available to 
    process adapted images. The default value is `5`. This setting can't exceed 
    the *Max processors* setting. 

The *Adaptive Media Image* configuration can be different for each virtual 
instance. It contains only this option: 

-   **MimeTypes:** A list of the image MIME types that Adaptive Media supports. 
    If an image is uploaded and its MIME type isn't in this list, then Adaptive 
    Media ignores the image. By default, this list is populated with many common 
    MIME types. 
