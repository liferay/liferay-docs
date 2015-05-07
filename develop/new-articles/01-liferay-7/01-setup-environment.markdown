# Development Environment Setup
The development environment for Liferay 7 is very similar to the previous versions.  Below are the steps needed to set it all up.
## Liferay 7 Tomcat
The first thing you will need is a tomcat server running Liferay 7.  You can get this in one of two ways:

### 1. Download a Milestone Tomcat Bundle
The easiest route is to download a milestone build of the Tomcat bundle.  Click [here][sourceforge] to start downloading the Milestone 4 bundle, or visit [Liferay Portal on Sourceforge][liferay-sf] to check for newer milestones or GA releases.

### 2. Build from Source
The harder but slightly more rewarding route is to build from source.  Visit the [Liferay Portal][github] project on GitHub  and clone it to your local file system.  Simply point your `app.server.<username>.properties` to your local Liferay Tomcat instance (like a 6.2 bundle), and run `ant all`.  Note that you'll need to use JDK 7 to build and run the portal.  Java 8 is not supported at the time of this writing.

## Plugins SDK
### Download
The plugins SDK can be gotten from GitHub via [this link][plugins-sdk].  Once on that page, just click "view the full file" link to start the download.

### Setup
To setup the SDK, simply unzip and place the SDK at the desired location on your file system.  Update `build.<username>.properties` to point it to your new Liferay 7 Tomcat instance mentioned in previous sections.

## Bndtools (Optional)
If you're using the Eclipse IDE, you can download the Bndtools plugin for Eclipse (Luna+).  More on that later.

[sourceforge]: http://sourceforge.net/projects/lportal/files/Liferay%20Portal/7.0.0%20M4/liferay-portal-tomcat-7.0-ce-m4-20150224120313668.zip/download
[liferay-sf]: http://sourceforge.net/projects/lportal/files/Liferay%20Portal/
[github]: https://github.com/liferay/liferay-portal
[plugins-sdk]: https://github.com/liferay/liferay-portal/blob/master/tools/liferay-plugins-sdk-7.0.0.zip