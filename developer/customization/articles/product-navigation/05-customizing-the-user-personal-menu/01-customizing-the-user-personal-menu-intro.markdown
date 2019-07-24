---
header-id: providing-the-user-personal-menu
---

# Customizing the User Personal Bar

[TOC levels=1-4]

The User Personal Bar displays options unique to the current user. By default,
this portlet displays an avatar button that opens the Personal Menu. In a custom 
theme, the User Personal Bar could appear anywhere in the interface. 

![Figure 1: By default, the User Personal Bar contains the signed-in user's avatar, which opens the Personal Menu when selected.](../../../images/user-personal-bar.png)

The User Personal Bar can be seen as a placeholder in every Liferay theme. By
default, Liferay provides one sample *User Personal Bar* portlet that fills that
placeholder, but the portlet Liferay provides can be replaced by a 
[custom portlet](/docs/7-2/customization/-/knowledge_base/c/using-a-custom-portlet-as-the-user-personal-bar). 

## Displaying the Personal Menu

Starting with @product-ver@, the Personal Menu is attached to the User Personal
Bar rather than the Product Menu. To display the Personal Menu, embed the User
Personal Bar into your theme by adding the following snippet into your
`portal_normal.ftl`:

```markup
<@liferay.user_personal_bar />
```

If you use a custom portlet as your user personal bar, you must also render the
Personal Menu. Include the following taglib in your portlet's jsp:

```jsp
<liferay-product-navigation:personal-menu
    expanded="<%= true %>"
    label="<%= userAvatar %>"
/>
```

| **Note:** The recommended way to display the Personal Menu is by embedding the
| User Personal Bar in a theme. If this is not practical, a workaround exists: go
| to *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings* &rarr;
| *Users* and select *Personal Menu*. Enable the *Show in Control Menu* toggle
| and click *Update*.
| 
| This places a button to expand the Personal Menu in the Control Menu. It
| appears on every site and page in your virtual instance, including sites that
| have the User Personal Bar embedded in the theme. So, to avoid multiple User 
| Personal Bars appearing on the page, you should only use one of these 
| mechanisms to display the User Personal Bar.

Unlike the Product Menu, the Personal Menu can be customized without
creating panel categories and panel apps. See 
[Customizing the Personal Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-personal-menu)
for details.

This section covers these topics:

- Using a Custom Portlet as the User Personal Bar
- Customizing the Personal Menu
