# Portlet Development [](id=portlet-development)

In this chapter we'll create and deploy a simple portlet using the Plugins SDK.
It will allow a customized greeting to be saved in the portlet's preferences and
then display it whenever the portlet is viewed. Last we'll clean up the
portlet's URLs by adding a friendly URL mapping. 

You're free to use any framework you prefer to develop your portlets, including
Struts, Spring MVC, or JSF. Here we'll use the Liferay MVCPortlet framework,
because it's simple, lightweight, and easy to understand. 

You don't have to be a Java developer to take advantage of Liferay's built-in
features (such as user and organization management, page building and content
management). An application developed using Ruby or PHP can be deployed as a
portlet using the Plugins SDK, and it will run seamlessly inside of Liferay. For
examples, check out the liferay-plugins repository from
[Github](http://github.com/liferay).

We'll discuss the following topics as we learn about developing portlets for
Liferay:

- Creating a Portlet project 
- Anatomy of a Portlet project
- Writing the My Greeting Portlet
- Understanding the two phases of Portlet Execution 
- Passing Information from the Action Phase to the Render Phase
- Developing a Portlet with Multiple Actions 
- Adding Friendly URL Mapping to the Portlet 
- Localizing your Portlet 
- Implementing Configurable Portlet Preferences

First, let's create the portlet that we'll use throughout this chapter. 
