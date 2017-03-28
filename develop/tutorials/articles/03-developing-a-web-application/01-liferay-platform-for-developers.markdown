# Why Liferay's Platform is Good for Developers 
Liferay's Learning Paths provide an in-depth, from-the-ground-up guide to
Liferay development. Beginning with installing a Liferay development
environment, you'll learn everything in a step-by-step fashion from writing a
Liferay application, to customizing Liferay Portal, to publishing your
application on Liferay Marketplace.

First we'll talk about Liferay's key features for developers, then we'll go
through the steps for setting up a Liferay development environment using @ide@,
after than we'll create a basically application, and finally we'll go through
each aspect of a Liferay application that you might need for creating a
full-featured web application. 

## Modularity 
One of the key features of @product@ for developers is modularity. Liferay 7.0 
uses OSGi to provide a framework for modular development. Developing modularly 
provides many benefits. Updating your code base is easier when you only need to 
consider one aspect of the application at a time. Dependencies can be more 
easily swapped in and out. Class loader issues are eliminated by deploying 
inside of the OSGi container. If you aren't sold yet on OSGi and Modularity, 
you can find more information in our [OSGI and Modularity for Liferay Portal 6 
Developers](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/osgi-
and-modularity-for-liferay-6-developers) article. 

## Using Liferay's Features 
When you create an application with Liferay, you gain the ability to connect it 
with the every different facet of Liferay's core applications and capabilities. 
You can integrate features like Liferay's built-in rich text editor or manage 
files for the application with Documents and Media. You can also use tools 
Liferay's Permissions system and services like Kaleo Workflow and Staging to 
take advantage of Liferay's enterprise frameworks. To learn more about the full 
depth of Liferay's development features, see [Introduction to Liferay 
Development](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/intr
oduction-to-liferay-development) 

## Service Builder 
Service Builder is a code generation tool to automatically generate code for 
database management. With Service Builder, you define all of your entities 
using an XML file, and all of your Create, Read, Update, and Delete code is 
generated for you. You can then integrate those methods into your application. 
Service Builder is designed to save you time and provide standardized code for 
your persistance layer and database interaction. 

## Liferay Development Tools 
The easiest way to integrate all of these features is to use the development 
tools that Liferay provides - @ide@ and BLADE CLI for the command line. @ide@ 
provides an integrated development environment based on Eclipse to help you 
more quickly and easily develop using Liferay's development framework and to 
integrate with all of the great features that we've been talking about. 
