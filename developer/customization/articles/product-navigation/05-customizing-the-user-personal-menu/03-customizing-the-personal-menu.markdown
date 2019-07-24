---
header-id: customizing-the-personal-menu
---

# Customizing the Personal Menu

[TOC levels=1-4]

You can add entries to the Personal Menu by implementing the `PersonalMenuEntry`
Interface. You can see the entire interface 
[here](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/product-navigation/product-navigation-personal-menu-api/src/main/java/com/liferay/product/navigation/personal/menu/PersonalMenuEntry.java). 
If you're adding a portlet to the User Personal Menu, the process is slightly 
different. Both approaches are covered below.

## Adding an Entry to the User Personal Menu

Follow these steps. `SignOutPersonalMenuEntry.java` is used as an example 
throughout these steps:

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project)
    and place a new Java class into a package in it's `src` folder.

2.  In the `@Component` annotation, specify the two properties shown below to 
    place your new entry in the User Personal Menu:
    
    - `product.navigation.personal.menu.group`: determines the section where the 
      entry will be placed.
    
    - `product.navigation.personal.menu.entry.order`: determines the order of 
      entries within each section. Note that sections are not labelled. To 
      create a new section, assign the `group` property a value other than those 
      for the four default sections (100, 200, 300, and 400).
    
    ![Figure 1: The User Personal Menu is organized into four sections.](../../../images/user-personal-menu-sections.png)

    An example is shown below:

    ```java
    @Component(
    	immediate = true,
    	property = {
        "product.navigation.personal.menu.group:Integer=400",
    		"product.navigation.personal.menu.entry.order:Integer=100"
    	},
    	service = PersonalMenuEntry.class
    )
    public class SignOutPersonalMenuEntry implements PersonalMenuEntry {
    ```

3.  Include the interface's methods. `SignoutPersonalMenuEntry` uses `getLabel`
    and `getPortletURL`, which are the only two that are mandatory. `getLabel`
    retrieves a language key to label the entry in the UI:

    ```java
    	@Override
    	public String getLabel(Locale locale) {
    		return LanguageUtil.get(locale, "sign-out");
    	}
    ```

    `getPortletURL` returns the URL for the portlet or page you want to access 
    with the entry:

    ```java
    	public String getPortletURL(HttpServletRequest httpServletRequest)
    		throws PortalException {

    		ThemeDisplay themeDisplay =
    			(ThemeDisplay)httpServletRequest.getAttribute(
    				WebKeys.THEME_DISPLAY);

    		return themeDisplay.getURLSignOut();
    	}

    }
    ```

That's all you need to implement the interface. However, the `PersonalMenuEntry` 
interface includes a number of other methods that you can use if you need them:

`getIcon`: identify an icon to display in the entry.

`isActive`: indicate whether the entry is currently active.

`isShow`: write logic to determine under what circumstances the entry is displayed.

You can learn how to add a portlet entry to the User Personal Menu next. 

## Adding a Portlet Entry to the User Personal Menu

If you're adding a portlet to the User Personal Menu, you can just extend the 
`BasePersonalMenuEntry` class to save time. Follow these steps:

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project)
    and place a new Java class into a package in it's `src` folder.

2.  In the `@Component` annotation, specify the two properties shown below to 
    place your new entry in the User Personal Menu:
    
    - `product.navigation.personal.menu.group`: determines the section where the 
      entry will be placed.
    
    - `product.navigation.personal.menu.entry.order`: determines the order of 
      entries within each section. Note that sections are not labelled. To 
      create a new section, assign the `group` property a value other than those 
      for the four default sections (100, 200, 300, and 400).

    An example is shown below:

    ```java
    @Component(
    	immediate = true,
    	property = {
    		"product.navigation.personal.menu.entry.order:Integer=100",
    		"product.navigation.personal.menu.group:Integer=300"
    	},
    	service = PersonalMenuEntry.class
    )
    public class MyAccountPersonalMenuEntry extends BasePersonalMenuEntry {
    ```

3.  Override the `getPortletId()` method to provide the portlet's ID, as shown 
    in the example below:
    
    ```java
    public class MyAccountPersonalMenuEntry extends BasePersonalMenuEntry {

    	@Override
    	public String getPortletId() {
    		return UsersAdminPortletKeys.MY_ACCOUNT;
    	}

    }
    ```

    The `BasePersonalMenuEntry` class automatically determines the label, 
    portlet URL, state, and visibility based on the portlet ID. 

Once you've completed your implementation and deployed your module, your new
entry is displayed in the personal menu. Great! Now you know how to customize 
the User Personal Menu. 

## Related Topics

- [Using a Custom Portlet as the User Personal Bar](/docs/7-2/customization/-/knowledge_base/c/using-a-custom-portlet-as-the-user-personal-bar)
- [Customizing the Product Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu)
- [Customizing the Control Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-control-menu)
