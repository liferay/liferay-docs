# Advanced Configuration Options [](id=advanced-configuration-options)

Adaptive Media has some advanced configuration options that are available in 
System Settings. Open the Control Panel and go to *Configuration* &rarr; 
*System Settings*, then select the *Collaboration* tab. There are two 
configurations for Adaptive Media: *Adaptive Media*, and *Adaptive Media Image*. 

The *Adaptive Media* configuration affects all virtual instances and is related 
to Adaptive Media's asynchronous processing. These values can be modified to 
improve performance for specific scenarios or use cases. The following 
configuration options are available: 

-   **Max processes:** The maximum number of processes for generating adapted 
    media. The default value is `10`. 

-   **Core processes:** The number of processes always available for generating 
    adapted media. The default value is `5`. This setting can't exceed the *Max 
    processes* setting. 

The *Adaptive Media Image* configuration can be different for each virtual 
instance. It contains only this option: 

-   **Supported MIME Types:** A list of the image MIME types that Adaptive Media 
    supports. If an image is uploaded and its MIME type isn't in this list, then 
    Adaptive Media ignores the image. By default, this list is populated with 
    many common MIME types. 

-   **Gifsicle:** To scale animated GIFs, Adaptive Media uses an external tool 
    called [Gifsicle](https://www.lcdf.org/gifsicle/). First install Gifsicle 
    in the server, ensure that it's reachable from the `PATH` environment 
    variable, and then click the box next to "Gifsicle Enabled." If Gifsicle 
    isn't installed and `image/gif` is included as a supported MIME type, 
    Adaptive Media scales only a single frame of the GIF. This results in a 
    static image in place of the animated GIF.
