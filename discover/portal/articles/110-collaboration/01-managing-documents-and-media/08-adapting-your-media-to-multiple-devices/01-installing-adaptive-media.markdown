# Installing Adaptive Media [](id=installing-adaptive-media)

You can install the Adaptive Media app from 
[Liferay Marketplace](https://web.liferay.com/marketplace) 
for Liferay Portal CE 7.0 GA5+ or Liferay DXP. Search for *Adaptive Media* in 
the Marketplace and follow the app installation instructions at 
[Using the Liferay Marketplace](/discover/portal/-/knowledge_base/7-0/using-the-liferay-marketplace). 

+$$$

**Note:** To use the Adaptive Media app in Liferay DXP, you must be running 
Liferay Digital Enterprise 7.0 Fix Pack 35 or higher. See the 
[customer portal](https://web.liferay.com/group/customer) 
to download fix packs. 

$$$

If you're running Liferay Portal CE 7.0 GA6+, or Liferay DXP with Fix Pack 35+, 
then installing the Adaptive Media app is all you need to do. If you're running 
Liferay Portal CE 7.0 GA5, however, then you must also install another module. 
The next section explains how to do this. 

## Installing Adaptive Media in Liferay Portal CE 7.0 GA5 [](id=installing-adaptive-media-in-liferay-portal-ce-7-0-ga5)

If you're running Liferay Portal CE 7.0 GA5, then you must install another 
module after installing the Adaptive Media app: 

1.  Shut down your @product@ server. 

2.  [Click here](https://web.liferay.com/documents/4426623/103634825/com.liferay.journal.service.jar/4ba168ec-9e1a-41cb-ab27-f7473eb0085c) 
    to download the module (it's a JAR file). 

3.  Copy the module into this folder of your @product@ installation: 

        [Liferay_Home]/osgi/marketplace/override/

    Create the `override` folder if it doesn't exist. 

4.  Start your @product@ server. You should see these log messages during 
    startup: 

        INFO  [Start Level: Equinox Container: 60d02f03-ed0b-0018-179d-ebc04e65241e][LPKGBundleTrackerCustomizer:411] 
            Disabled Liferay CE Web Experience:/com.liferay.journal.service-3.17.3.jar
        INFO  [Start Level: Equinox Container: 60d02f03-ed0b-0018-179d-ebc04e65241e][DefaultLPKGDeployer:393] 
            Installed override JAR bundle  LPKG-Override::/liferay-ce-portal-7.0-ga5/osgi/marketplace/override/com.liferay.journal.service.jar

    These messages indicate that the new module has successfully replaced the 
    old module. 

5.  You can verify this by running the command `lb "Journal Service"` from the 
    [Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 
    It should indicate that the module is active: 

        ID|State      |Level|Name
        524|Active     |   10|Liferay Journal Service (3.18.0.ADAPTIVE-MEDIA)

Well done! Next, you'll learn which modules in the Adaptive Media app are 
mandatory or optional. 

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
    [migrate the original thumbnails to adapted images](/discover/portal/-/knowledge_base/7-0/migrating-documents-and-media-thumbnails-to-adaptive-media). 
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
[advanced configuration options](/discover/portal/-/knowledge_base/7-0/advanced-configuration-options). 

If Gifsicle isn't installed and `image/gif` is included as a supported MIME type
in the advanced configuration options, Adaptive Media scales only a GIF's single
frame. This results in a static image in place of the animated GIF. 
