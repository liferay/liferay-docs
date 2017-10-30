# Advanced Configuration Options [](id=advanced-configuration-options)

Adaptive Media has some advanced configuration options that are available 
through System Settings. Open the Control Panel and go to *Configuration* &rarr; 
*System Settings* and select the *Collaboration* tab. There are two 
configurations for Adaptive Media: *Adaptive Media* and *Adaptive Media Image*.

The *Adaptive Media* configuration affects every virtual instance and is related 
to the asynchronous processing done by Adaptive Media. These values can be 
modified to improve performance for specific scenarios or use cases.

The following configuration options are available:

**Workers max size:** The maximum size of workers that can process adaptive 
media. The default value is 10 workers.

**Workers core size:** The size of core workers that can process adaptive media. 
This size should always be smaller or equal to `Workers Max Size`. This size can 
be modified to improve performance for specific environments and use cases. The 
default value is 5 workers.

The *Adaptive Media Image* configuration can be different for each virtual 
instance. This only contains the option below:

**MimeTypes:** A list of the image mime types that are supported by Adaptive 
Media. If an image is uploaded and the MIME type is not contained in this list, 
Adaptive Media ignores it and doesn't process it.
