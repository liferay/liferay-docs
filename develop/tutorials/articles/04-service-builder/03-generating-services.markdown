# Generating Services [](id=generating-services)

To build a service from a `service.xml` file, you can use *Liferay IDE*,
*Liferay Developer Studio*, or use a terminal window. Next, you'll generate the
service for the example Event Listing portlet project you've been developing
throughout this chapter. The project resides in the
`portlets/event-listing-portlet` folder of your Plugins SDK. 

<!-- I thought we we had decided these dual sections of IDE/Plugins SDK were
awkward, and we were going to focus just on showing how it's done in IDE? -Rich
-->

+$$$

**Note:** On Windows, your Liferay Portal instance and your Plugins SDK must be
on the same drive in order to build services. E.g., if your Liferay Portal
instance is on your `C:\` drive, your Plugins SDK must also be on your `C:\`
drive in order for Service Builder to be able to run successfully.

$$$

***Using Liferay IDE or Developer Studio:*** From the Package Explorer, open
the `service.xml` file from your `event-listing-portlet/docroot/WEB-INF` folder.
By default, the file opens up in the Service Builder Editor. Make sure you are
in Overview mode. Then click the *Build Services* button near the top-right
corner of the view. The Build Services button has an image of a document with
the numerical sequence *0101* in front of it. 

Make sure to click the *Build Services* button and not the *Build WSDD* button
that appears next to it. Building the WSDDs won't hurt anything, but you'll
generate files for the remote service instead of the local one. For information
about WSDDs (web service deployment descriptors), please refer to the section on
remote Liferay services later in this chapter. 

![Figure 4.4: The *Overview* mode in the editor provides a nested outline which you can expand, a form for editing basic Service Builder attributes, and buttons for building services or building web service deployment descriptors.](../../images/service-xml-overview.png)

After running Service Builder, the Plugins SDK prints messages listing the
generated files and a message stating `BUILD SUCCESSFUL`. More information about
these files appears below. 

***Using the terminal:*** Open a terminal window, navigate to your
`portlets/event-listing-project-portlet` directory and enter this command:

    ant build-service

When the service has been successfully generated, a `BUILD SUCCESSFUL` message
appears in your terminal window. You should also see that a large number of
files have been generated in your project. These files include a model layer,
service layer, and persistence layer. Don't worry about the number of generated
files--developers never have to customize more than three of them. We'll examine
the files Service Builder generated for your Event entity after we add some
custom service methods to `EventLocalServiceImpl` and call them from
`EventPortlet`.

Now let's add some local service methods to `EventLocalServiceImpl` and learn
how to call them. Later in this chapter, we'll add some remote service methods
to `EventServiceImpl` and learn how to call those, too.
