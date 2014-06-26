# Using Scripting for Advanced Flexibility [](id=using-scripting-for-advanced-flexibility-lp-6-2-use-useportal)

Liferay provides a robust script engine that can be used to interpret scripts in
Beanshell, Javascript, Groovy, Python, and Ruby. The script engine came out of
Liferay's involvement with the [Romulus project](http://www.ict-romulus.eu). It
was originally developed to support non Java-based portlets, but has now been
extended to handle a lot more. For example, when Liferay's workflow framework
was introduced, the script engine was leveraged to support the execution of
scripts from within a workflow module. A script console is now included in the
Server Administration portlet in the control panel. It allows system
administrators an easy way to execute scripts to perform repetitive user
maintenance operations, bulk manipulations using the Liferay API to ensure
consistency, or even system level operations.

This chapter helps you to understand Liferay's script engine and covers the
following topics: 

- Accessing Liferay's service layer from a script

- Running scripts from the script console

- Using the script engine with workflow

- Leveraging custom Java tools in the script engine

The most common thing you'll want to do is access Liferay's services. If you
have any familiarity with Liferay's developer tools and API, this will be a snap
for you. To access Liferay's scripting console, navigate to the Control Panel,
click on *Server Administration* under the Configuration heading, then open the
*Script* tab.
