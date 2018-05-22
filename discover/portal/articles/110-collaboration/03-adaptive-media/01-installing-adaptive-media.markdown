# Installing Adaptive Media [](id=installing-adaptive-media)

The Adaptive Media app is installed in @product@ by default. The app is also 
available on 
[Liferay Marketplace](https://web.liferay.com/marketplace) 
if you need to update or reinstall it (search for *Adaptive Media* in the 
Marketplace and follow the app installation instructions at 
[Using the Liferay Marketplace](/discover/portal/-/knowledge_base/7-1/using-the-liferay-marketplace)). 

The following sections describe the Adaptive Media app's modules, and how to 
prepare Adaptive Media to handle animated GIFs. 

## Adaptive Media's Modules [](id=adaptive-medias-modules)

Some modules in the Adaptive Media app are mandatory and must be enabled for 
Adaptive Media to function, while others can be disabled. The Adaptive Media API 
modules, which export packages for the other modules to consume, are mandatory; 
disabling one also disables any other modules that depend on it. Here's a list 
of the Adaptive Media API modules: 

-   Liferay Adaptive Media API
-   Liferay Adaptive Media Content Transformer API
-   Liferay Adaptive Media Image API
-   Liferay Adaptive Media Image Item Selector API 

The Adaptive Media core modules are also mandatory, and must be enabled to 
ensure that Adaptive Media works as expected: 

-   Liferay Adaptive Media Document Library
-   Liferay Adaptive Media Document Library Item Selector Web
-   Liferay Adaptive Media Document Library Web
-   Liferay Adaptive Media Image Content Transformer
-   Liferay Adaptive Media Image Implementation
-   Liferay Adaptive Media Image Item Selector Implementation
-   Liferay Adaptive Media Image JS Web
-   Liferay Adaptive Media Image Service
-   Liferay Adaptive Media Image Taglib
-   Liferay Adaptive Media Image Web
-   Liferay Adaptive Media Item Selector Upload Web
-   Liferay Adaptive Media Web

The Adaptive Media Blogs modules, which ensure that images uploaded to  blog 
entries can be processed and adapted, are optional. Here's a list of these 
modules: 

-   Liferay Adaptive Media Blogs Editor Configuration
-   Liferay Adaptive Media Blogs Item Selector Web
-   Liferay Adaptive Media Blogs Web
-   Liferay Adaptive Media Blogs Web Fragment

The Adaptive Media Journal modules are optional. These modules apply Adaptive 
Media to web content articles: 

-   Liferay Adaptive Media Journal Editor Configuration
-   Liferay Adaptive Media Journal Web

There are two more optional modules included in Adaptive Media: 

-   **Liferay Adaptive Media Image Content Transformer Backwards Compatibility:** 
    Ensures that content created before the Adaptive Media installation can use
    adapted images without the need to edit that content manually. It transforms
    the images both at startup and when a user views the content, which can
    negatively affect performance. We therefore recommend that you run some
    performance tests before using this module in production. You can disable
    this module if you don't have old content, are experiencing performance
    problems, or your old content doesn't need adapted images. 

-   **Liferay Adaptive Media Document Library Thumbnails:** Lets thumbnails in 
    Documents and Media use adapted images. For this to work, you must first 
    [migrate the original thumbnails to adapted images](/discover/portal/-/knowledge_base/7-1/migrating-documents-and-media-thumbnails-to-adaptive-media). 
    We highly recommend that you enable this module, but it's not mandatory. 

Great! Now you know the mandatory and optional modules that come with Adaptive 
Media. The next section discusses the installation requirements for using 
animated GIFs with Adaptive Media. If you don't need to use GIFs, you can skip 
ahead to the article on adding image resolutions to Adaptive Media. 

## Processing Animated GIFs [](id=processing-animated-gifs)

To process animated GIFs, Adaptive Media uses an external tool called 
[Gifsicle](https://www.lcdf.org/gifsicle). 
This tool ensures that the animation works when the GIF is scaled to different 
resolutions. You must manually install Gifsicle on the server and ensure that
it's on the `PATH`. Once it's installed, you must enable it in Adaptive Media's
[advanced configuration options](/discover/portal/-/knowledge_base/7-1/advanced-configuration-options). 

If Gifsicle isn't installed and `image/gif` is included as a supported MIME type
in the advanced configuration options, Adaptive Media scales only a GIF's single
frame. This results in a static image in place of the animated GIF. 
