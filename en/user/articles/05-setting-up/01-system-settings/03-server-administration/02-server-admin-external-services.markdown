---
header-id: server-administration-external-services
---

# Server Administration: External Services

[TOC levels=1-4]

Users can upload and share any type of file via the Documents and Media library, 
a customizable and permissions-enabled online repository for files (see 
[publishing files ](/docs/7-2/user/-/knowledge_base/u/publishing-files) 
for more information). 
[PDFBox](https://pdfbox.apache.org/) 
is included with @product@ and generates automatic previews for certain file
types (mostly PDFs). You can install two additional tools to generate previews
for other file types: 

-   [**OpenOffice:**](https://www.openoffice.org/) or [**LibreOffice:**](https://www.libreoffice.org/)
    Convert and generate previews for many types of documents. 

-   [**ImageMagick:**](https://www.imagemagick.org/script/index.php) 
    Generate higher-quality image previews for many types of images. 

-   [**Xuggler:**](http://www.xuggle.com/xuggler/) 
    Convert and generate previews for audio and video files. 

As of Liferay 7.1, OpenOffice/LibreOffice is configured in OSGi Configuration
Admin instead of portal properties. To adjust the these settings, go to *Control
Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; *Connectors* &rarr;
*OpenOffice Integration*. You can also adjust these
settings by deploying a
`com.liferay.document.library.document.conversion.internal.configuration.OpenOfficeConfiguration.config`
file to your `[Liferay Home]/osgi/configs` folder. See the
[Breaking Changes](/docs/7-1/reference/-/knowledge_base/r/breaking-changes#moved-openoffice-properties-to-osgi-configuration)
document for details.

Once you've installed ImageMagick and Xuggler, you can use the Server
Administration app's External Services tab to configure @product@ to use them.
Make sure to choose the correct versions of these tools for your operating
system. Install the latest stable versions, as older versions may not run
properly with @product@. ImageMagick must be installed manually, but you can
install Xuggler from the Control Panel. 

| **Tip:** If you're running @product@ on a Linux server and experience a problem
| enabling Xuggler, check your server's glibc version. For Xuggler to work, you
| may need to update glibc to version 2.6 or later.

## ImageMagick Configuration

Before configuring ImageMagick to generate image and PDF previews, install it
and its dependency, Ghostscript. This differs by operating system: on Linux,
both are likely already installed. They are not likely to be installed on
Windows, but may be on macOS.

1.  Download and install 
    [ImageMagick](https://www.imagemagick.org/script/index.php). 

2.  Download and install 
    [Ghostscript](https://www.ghostscript.com/). 

Once installed, you must enable ImageMagick in the Server Administration app's 
External Services tab, or in a `portal-ext.properties` file. If using 
`portal-ext.properties`, add the following lines and make sure the search path 
points to the directories containing the ImageMagick and Ghostscript 
executables. You may also need to configure the path for fonts used by 
Ghostscript when in macOS or Unix environments. 

    imagemagick.enabled=true
    imagemagick.global.search.path[apple]=/opt/local/bin:/opt/local/share/ghostscript/fonts:/opt/local/share/fonts/urw-fonts
    imagemagick.global.search.path[unix]=/usr/local/bin:/usr/local/share/ghostscript/fonts:/usr/local/share/fonts/urw-fonts
    imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick

Follow these steps to instead enable ImageMagick from the Server Administration 
app's External Services tab: 

1.  In the *Control Panel*, navigate to *Configuration* &rarr; 
    *Server Administration*, then click the *External Services* tab. 

2.  Expand the ImageMagick and Ghostscript section and select *Enabled*. 

3.  Verify that the paths to the ImageMagick and Ghostscript executables are 
    correct. 

![Figure 1: Enable ImageMagick and Ghostscript, and verify that the paths are correct.](../../../../images/imagemagick-ghostscript.png)

4.  Set the Resource limits to use.

## Xuggler Configuration

Follow these steps to install and configure Xuggler: 

1.  In the *Control Panel*, navigate to *Configuration* &rarr; 
    *Server Administration*, then click the *External Services* tab. 

2.  In the Xuggler section, select the Xuggler `.jar` file that matches your 
    operating system. Then click *Install*. 

3.  Restart your application server. 

4.  Enable Xuggler for your portal. There are two ways to do this: 

    -   In the Control Panel, navigate to the *Server Administration* &rarr; 
        *External Services* tab, select *Enabled*, then click *Save*. 
    -   Add the following line to your `portal-ext.properties` file and restart 
        your application server: 

            xuggler.enabled=true

![Figure 2: Install Xuggler.](../../../../images/xuggler-install.png)
