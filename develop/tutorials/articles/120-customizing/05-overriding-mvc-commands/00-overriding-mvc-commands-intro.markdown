---
header-id: overriding-mvc-commands
---

# Overriding MVC Commands

[TOC levels=1-4]

MVC Commands are used to break up the controller layer of [Liferay MVC applications](/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet)
into smaller, more digestible code chunks.

Sometimes you'll want to override an MVC command, whether it's in a Liferay
application or another Liferay MVC application whose source code you don't own.
Since MVC commands are components registered in the OSGi runtime, you can simply
publish your own customization of the component, give it a higher service
ranking, and deploy it. 

All existing components that reference the original MVC command service
component (using a greedy reference policy) switch to reference your new one.
Any existing
[reluctant references to the original command must be configured to reference the new one](/docs/7-1/tutorials/-/knowledge_base/t/overriding-service-references). 
Once they're configured with the new service component, their JSP's command URLs
invoke the new custom MVC command. 

Here are the customization options available for each Liferay MVC Command type:

- MVCActionCommand: [Add logic](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcactioncommand)
- MVCRenderCommand:
    - [Add logic](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcrendercommand#adding-logic-to-an-existing-mvc-render-command)
    - [Redirect to a different JSP](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcrendercommand#redirecting-to-a-new-jsp)
- MVCResourceCommand: [Add logic](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcresourcecommand) 

These tutorials demonstrate each MVC command customization option. Since the
steps for adding logic are generally the same across MVC command types, start
with [adding logic](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands). 
