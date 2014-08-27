# Commenting on Assets

Liferay's asset framework accommodates discussions on individual pieces of 
content posted in a plugin. This is great because when users see a piece of 
content posted, they expect to be able to discuss it. In fact, most of the work 
is already done for you so you don't have to spend time developing a commenting 
system from scratch. As long as your plugin is asset enabled, there are just a 
couple of simple steps to enable commenting on content--add a JSP for viewing 
entities and then create a URL for the new JSP in your existing `view.jsp`. 
That's it. 

This tutorial shows you how to enable commenting on content posted in an asset 
enabled portlet. A custom Insults portlet is used as an example--a community 
discussion will definitely help to bring about insults of the highest quality! 
<!-- Add link to starting and finishing portlets on Github -->

Without any further ado, go ahead and get started enabling comments in your 
portlet!

## Creating a JSP for Adding Comments to Content

The first step to enabling comments in your portlet is to create a separate JSP 
specifically for letting your users comment on content. You might be wondering 
why you need a separate JSP for this. The truth is, you don't. You could just 
enable comments in the edit JSP. However, separating collaborative activities 
like commenting from editing or creating content makes sense--it reduces 
confusion between the two activities. Also, you probably don't want users to be 
able to edit content that someone else produced.

Create this JSP in the same directory as your portlet's `view.jsp`. For example, 
in the Insults portlet this JSP is `docroot/html/insults/view_insult.jsp`. Since 
this JSP is accessed each time an entity in the portlet is clicked, you need to 
add code to the JSP that gets the object for that entity. This object is what 
you use to get the fields that you want the JSP to display, in addition to 
letting your users comment on that entity. To implement commenting, simply add 
the `liferay-ui:discussion` tag to the bottom of the JSP. For example, the 
`liferay-ui:discussion` tag for the Insults portlet looks like this:


