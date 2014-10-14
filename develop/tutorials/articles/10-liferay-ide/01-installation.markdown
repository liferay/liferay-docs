# Installation [](id=installation)

Liferay IDE is a plugin for Eclipse. You can install it in two ways: bundled
with Eclipse (this is the easiest way) or into an existing Eclipse installation. 

If you want to install the bundled version, a prerequisite is a version of Java
supported by Eclipse. As of this writing, the bundled version requires JDK 6 or
above.

Once you've satisfied the prerequisite, it's an easy matter to install Liferay
IDE. Go to Liferay's [downloads page](http://www.liferay.com/downloads) and
choose the *Liferay IDE* option. There's a separate download for each of the
32-bit and 64-bit variants of Windows, Linux, and Mac OS X. Choose the one that
matches your system and click the *Download* button. You'll receive a `.zip`
file. To install Liferay IDE on your system, all you need to do is unzip it to
the folder of your choice. 

If you already have Eclipse installed, you can add Liferay IDE to it. You can
do this in one of two ways: install Liferay IDE via a URL to the update site
or install from a `.zip` file. 

Make sure you have a supported Java JRE and Eclipse release: 

-   Java 6.0 JRE or greater  
-   Liferay IDE supports the following Eclipse releases: 

    Eclipse Kepler Java EE (4.3.x)

    Eclipse Juno Java EE (4.2.x)

    Eclipse Indigo Java EE (3.7.x)

To install Liferay IDE and specify an Eclipse update URL, follow these steps: 

1.  Install Eclipse Kepler, Juno, or Indigo from the
    [Eclipse](http://www.eclipse.org) website. 

2.  Run the Eclipse executable file (e.g., `eclipse.exe`).

3.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

4.  In the *Work with* field, enter the update site URL
    `http://sourceforge.net/projects/lportal/files/Liferay IDE/2.0.0
    GA1/updatesite/` and press *Enter*.

5.  Liferay IDE features should be detected. Select them and click *Next*. 

6.  After calculating dependencies, click *Next*, accept the license agreement,
    and click *Finish* to complete the installation. 

7.  Restart Eclipse to verify that Liferay IDE is properly installed.

8.  After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
    IDE icon badge as in the screenshot below, it's properly installed. 

![Figure 10.1: The Liferay IDE logo in Eclipse](../../images/ide-about-eclipse-liferay.png)

To install Liferay IDE from a `.zip` file, follow these steps:

1.  Install Eclipse Kepler, Juno, or Indigo from the
    [Eclipse](http://www.eclipse.org) website. 

2.  Download the IDE 2.0 `.zip` file from
    [http://sourceforge.net/projects/lportal/files/Liferay IDE/2.0.0
    GA1/](http://sourceforge.net/projects/lportal/files/Liferay%20IDE/2.0.0%20GA1/)
    for your operating system. 

3.  Run the Eclipse executable file (e.g., `eclipse.exe`).

4.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

5.  In the *Add Site* dialog, click the *Archive* button and browse to the
    location of the downloaded Liferay IDE `.zip` file.

6.  Liferay IDE features should be detected. Select them and click *Next*. 

7.  After calculating dependencies, click *Next*, accept the license agreement,
    and click *Finish* to complete the installation.

8.  Restart Eclipse to verify that Liferay IDE is properly installed.

9.  After restarting Eclipse, go to *Help* &rarr; *About*; if you see a Liferay
    IDE icon badge as in the screenshot below, it's properly installed. 

<!-- This whole section needed some help, as we now provide a bundled version
that wasn't covered at all. Additionally, all the links were wrong, so I rewrote
it in a more maintainable way. --> 

Let's set up Liferay IDE now that you have it installed. 
