# Assigning Template Permissions [](id=assigning-template-permissions)

Structures and Templates provide direct access to Liferay's APIs which makes 
them powerful, but it also means that they can be dangerous in the 
wrong hands. Only trusted users who need access to do their 
job should be given access. The recommended practice is to create two roles 
with access to structures and templates:

-  **Content Developers** need full permission to create and edit structures 
    and templates.
    
-  **Content Creators** only need permission to view the structures and 
    templates so that they can use them to create content.

When creating the Roles, you also define whether a user will have global 
permission for all structures and templates across the entire instance or only 
access to specific Sites. For more information on creating roles see the [Roles and Permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions) 
article.

## Assigning Permissions for Individual Templates

You can also control access to specific templates separately. To determine who 
can view and interact with a template,

1.  Go to the *Templates* tab.

2.  Click the Action* button (![Actions](../../../../../images/icon-actions.png))
    for a template that you created and select *Permissions*.

Here permissions for a template can be set for Roles or Teams. Use this option 
to provide access to templates on a case by case basis for users that shouldn't
have access to templates on a larger level granted by a Role.

Whether your Site is small and static or large and dynamic, Liferay's Web
Content Management system enables you to plan and manage it. With tools such as
the WYSIWYG editor, structures and templates, you can quickly add and edit
content. With Web Content Display, you can rapidly select and configure what
content to display. You'll find that managing your site becomes far easier when
using @product@'s Web Content Management system.
