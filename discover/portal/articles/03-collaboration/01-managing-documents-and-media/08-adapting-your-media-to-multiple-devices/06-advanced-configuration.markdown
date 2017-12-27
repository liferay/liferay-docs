# Advanced Configuration Options [](id=advanced-configuration-options)

Adaptive Media's advanced configuration options are available in System
Settings. Open the Control Panel and go to *Configuration* &rarr; *System
Settings*, then select the *Collaboration* tab. There are two configurations for
Adaptive Media: *Adaptive Media* and *Adaptive Media Image*. 

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
instance. The following configuration options are available: 

-   **Supported MIME Types:** A list of the image MIME types that Adaptive Media 
    supports. If an image is uploaded and its MIME type isn't in this list,
    Adaptive Media ignores the image. By default, this list contains many common
    MIME types. 

-   **Gifsicle:** To scale animated GIFs, Adaptive Media uses an external tool 
    called [Gifsicle](https://www.lcdf.org/gifsicle/). First install Gifsicle 
    on the server, ensure that it's on the `PATH` environment variable, and then
    click the box next to *Gifsicle Enabled*. If Gifsicle isn't installed and
    `image/gif` is included as a supported MIME type, Adaptive Media scales only
    one frame of the GIF, making a static GIF.
