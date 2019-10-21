---
header-id: customizing-the-user-personal-bar-and-menu
---

# Customizing the User Personal Bar and Menu

[TOC levels=1-4]

The User Personal Bar is a portlet, but it's also an important concept in
@product@. In a fresh bundle using the default theme, it's the section of screen
occupied by the User's avatar and the Personal Menu.

![Figure 1: By default, the User Personal Bar contains the signed-in user's avatar, which opens the Personal Menu when selected.](../../../images/user-personal-bar.png)

The User Personal Bar holds only the Personal Menu by default, but it can also
contain any functionality you want (even additional portlets). The User Personal
Bar is included by default in every Liferay theme, but you can replace it with a 
[portlet](/docs/7-2/customization/-/knowledge_base/c/using-a-custom-portlet-in-place-of-the-user-personal-bar)
or customize it by adding entries to the existing portlet's menu.

This section covers these topics:

- Replacing the default User Personal Bar portlet with a custom portlet.
- Customizing the default User Personal Bar.

## Displaying the Personal Menu

Starting with @product-ver@, the Personal Menu is no longer part of the
Product Menu, but is instead included in the User Personal Bar. To display the
existing User Personal Bar in your own theme, embed the portlet into your theme
by adding the following snippet into `portal_normal.ftl`:

```markup
<@liferay.user_personal_bar />
```

You'll use the same snippet even if you're replacing the default User Personal
Bar portlet with your own.

If you use a custom portlet to provide the User Personal Bar, but wish to
include the default Personal Menu, make sure to render it by using this tag in
your portlet's JSP:

```markup
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
| Personal Bars appearing on the page, you should use only *one* of these 
| mechanisms to display the User Personal Bar.

Unlike the Product Menu, the Personal Menu can be customized without creating
panel categories and panel apps. See 
[Customizing the Personal Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-personal-menu)
for details.

