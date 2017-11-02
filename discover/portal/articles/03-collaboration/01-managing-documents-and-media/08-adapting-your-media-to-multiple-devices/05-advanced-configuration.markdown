# Advanced Configuration Options [](id=advanced-configuration-options)

Adaptive Media has some advanced configuration options that are available in 
System Settings. Open the Control Panel and go to *Configuration* &rarr; 
*System Settings*, then select the *Collaboration* tab. There are two 
configurations for Adaptive Media: *Adaptive Media* and *Adaptive Media Image*. 

The *Adaptive Media* configuration affects all virtual instances and is related 
to Adaptive Media's asynchronous processing. These values can be modified to 
improve performance for specific scenarios or use cases. The following 
configuration options are available: 
<!-- 
It's not clear what these options do. What are "workers"? I'm assuming they're 
not guys in hard hats. What happens when you increase/decrease the number of 
workers? What's the difference between a regular worker and a core worker? The 
descriptions just say that they both "process adaptive media", whatever that 
means. Why does "workers core size" have to be less than or equal to "workers 
max size"? Workers core size also says that it, "improves performance for 
specific environments". What does that mean? Do more or fewer core workers 
improve performance? 

Also, the option names themselves are problematic (I know we can't change that, 
but we can tell the devs to). Both option names refer to "size" (a short/tall 
guy in a hard hat?), but according to the descriptions, they actually describe 
the number of workers (many/few guys in hard hats?). Also, based on the 
description, the "workers core size" option is named incorrectly... the name 
refers to the size of a worker core ("worker" being an adjective for "core"), 
while the description says that it's the size of a core worker ("core" being an 
adjective for "worker"). 
-->

-   **Workers max size:** The maximum size of workers that can process adaptive 
    media. The default value is 10 workers. 

-   **Workers core size:** The size of core workers that can process adaptive 
    media. This size should always be smaller or equal to *Workers max size*. 
    This size can be modified to improve performance for specific environments 
    and use cases. The default value is 5 workers. 

The *Adaptive Media Image* configuration can be different for each virtual 
instance. It contains only this option: 
<!-- 
Are there any default settings in this list? I'm assuming so, otherwise you'd 
have to manually populate it for Adaptive Media to work at all.
-->

-   **MimeTypes:** A list of the image MIME types that Adaptive Media supports. 
    If an image is uploaded and its MIME type isn't in this list, then Adaptive 
    Media ignores the image. 
