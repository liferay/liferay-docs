# Providing Portlets to Manage Requests

One of the most important cabiblities of Liferay Portal is the ability to
configure portlets to manage certain requests. For example, if you'd like users
to add Blog posts, you would configure a portlet that could handle requests to
add blogs, like the Blogs portlet. However, you shouldn't request a specific
hardcoded portlet module; instead, you should request an app that abides by the
entity and action type requested. Based on the entity type and action, Liferay
will use the available portlet that can handle the request. This increases the
flexibility and modularity of using portlets in Liferay Portal.

In this tutorial, you'll learn how to declare an entity type and action for a
desired portlet functionality, and create a module that finds the correct
portlet to use based on those given parameters.

## Specifying a Desired Portlet Behavior
