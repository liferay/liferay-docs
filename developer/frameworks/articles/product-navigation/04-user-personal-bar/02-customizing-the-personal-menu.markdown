# Customizing the Personal Menu

You can add entries to the Personal Menu by implementing the `PersonalMenuEntry`
Interface. You can see the entire interface 
[here](https://github.com/liferay/liferay-portal/blob/master/modules/apps/product-navigation/product-navigation-personal-menu-api/src/main/java/com/liferay/product/navigation/personal/menu/PersonalMenuEntry.java#L27-L43).

Follow these steps. The following uses `SignOutPersonalMenuEntry.java` as an example.

1.  [Create an OSGi module](/docs/7-2/frameworks/-/knowledge_base/f/starting-module-development)
    and place new Java class into a package in it's `src` folder.

2.  Locate your new entry in the menu and implement the interface:

```java
@Component(
	immediate = true,
	property = {
		"product.navigation.personal.menu.entry.order:Integer=100",
		"product.navigation.personal.menu.group:Integer=400"
	},
	service = PersonalMenuEntry.class
)
public class SignOutPersonalMenuEntry implements PersonalMenuEntry {
```

    The `product.navigation.personal.menu.group` property determines the section
    where the entry will be placed. The
    `product.navigation.personal.menu.entry.order` property determines the order of
    entries within each section. Note that sections are not labelled; all you have
    to do to create a new section is assign the `group` property a value other than
    those for the four default sections (100, 200, 300, and 400).

3.  Include the interface's methods. `SignoutPersonalMenuEntry` uses `getLabel`
    and `getPortletURL`, which are the only two which are mandatory. `getLabel`
    retrieves a language key to label the entry in the UI:

```java
	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "sign-out");
	}
```

    `getPortletURL` returns the URL for the portlet you want to access with the
    entry:

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

That's all you need to implement the interface. However, the
`PersonalMenuEntry` interface includes a number of other methods that you can
use if you need them:

`getIcon`: identify an icon to display in the entry.

`isActive`: indicate whether the entry is currently active.

`isShow`: write logic to determine under what circumstances the entry is displayed.

Once you've completed your implementation and deployed your module, your new
entry is displayed in the personal menu.
