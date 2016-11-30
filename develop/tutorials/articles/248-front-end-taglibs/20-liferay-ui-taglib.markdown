# @product@'s liferay-ui Taglib

There are a lot of components that can be created using the `<liferay:ui>` taglibs. The benefit of using these is the markup will be consistent, responsive, and accessible across your portlets. The markup that is generated when using the tags can be found on the [Liferay Github Repo](https://github.com/liferay/liferay-portal/tree/master/portal-web/docroot/html/taglib/ui).

## Using `<liferay-ui: />` Tags [](id=using-liferay-ui-tags)

A list of the available `<liferay-ui>` taglibs can be found [here](https://docs.liferay.com/portal/7.0/taglibs/util-taglib/). To use the taglib library you'll need to make sure the following line is in your jsp:

```html
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
```

Now you are good to go! Each taglib as a list of attributes that can be passed to the tag. Some of these are required and some are optional, looking at the documentaiton can help you find which ones you'll need.

Here an example of the `<liferay-ui:alert>` taglib. Using the below example will create a success alert that the user can close.

```html
<liferay-ui:alert
	closeable="true"
	icon="exclamation-full"
	message="Here is our awesome alert example"
	type="success"
/>
```

Here is an example of a `<liferay-ui:user-display>`

```html
<liferay-ui:user-display
	markupView="lexicon"
	showUserDetails="true"
	showUserName="true"
	userId="<%= themeDisplay.getRealUserId() %>"
	userName="<%= themeDisplay.getRealUser().getFullName() %>"
/>
```