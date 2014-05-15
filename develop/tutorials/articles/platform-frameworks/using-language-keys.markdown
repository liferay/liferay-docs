# Using Liferay's Language Keys

Liferay specifies a host of language keys in its core `Language.properties` file
found in the `content` folder of your `portal-impl.jar`, or
`portal-impl/src/content` of your Liferay Portal source tree. Leveraging
Portal's core language keys saves you time, since these keys always have up to
date translations for multiple languages. Additionally, your portlet blends
better into Liferay's UI conventions. 

For this tutorial, we'll implement a custom greeting for a portal user. When
we're finished with our tutorial, a user can view the following greeting in
their custom My Greeting portlet:

![Figure 1: The portal user's name is used in the `welcome` language key.](../../images/screen-name-greeting.png)

Let's get started by implementing this functionality into the My Greeting
portlet!

## Implementing Language Keys for Your Portlet

Before we get started implementing our own language key, we'll step through the
components of a Liferay UI language key.

You can use a language key in your JSP via a `<liferay-ui:message />` tag. 

    <liferay-ui:message key="message-key" />

You specify the message key corresponding to the language key in the
`Language.properties` file you want to display. For example, to welcome a user
in their language, specify the message key named `welcome`.

    <liferay-ui:message key="welcome" />

This key maps to the word "Welcome", in your translation of it to the
user's locale. Here is the `welcome` language key from Liferay's
`Language.properties` file.

    welcome=Welcome

Now that we know some basics, let's implement the `welcome` language key for a
custom portlet.

1. As a starting point, use the My Greeting portlet which is available in the
   <https://github.com/liferay/liferay-docs> GitHub repository
   [here](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferayui/success/end/my-greeting-portlet).
   You'll need to clone the repository if you haven't already done so. Then copy
   the `my-greeting-portlet` folder into the `portlets` folder of your Liferay
   Plugins SDK. Note that this My Greeting portlet is the finished portlet from
   the [Using liferay-ui:success and liferay:ui error
   Messages](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/articles/liferayui-taglibs/using-liferay-ui-success-and-error-messages.markdown)
   tutorial. Deploy the portlet to your portal and then add it to a page.

2. To use the `welcome` language key in the portlet's UI, open the `view.jsp`
   file and replace its greeting paragraph with this:

        <p><liferay-ui:message key="welcome" />! <%= greeting %></p>

Revisit the page to confirm that the word "Welcome", from `Language.properties`,
now precedes your greeting!

Note, in order to use the `<liferay-ui:message />` tag, or any of the
`liferay-ui` tags, you must include the following line in your JSP. It imports
the `liferay-ui` tag library. 

    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

The `<liferay-ui:message />` tag also supports passing strings as arguments to
a language key. For example, the `welcome-x` key expects one argument. Here is
the `welcome-x` key from the `Language.properties` file:

    welcome-x=Welcome{0}!

It references `{0}`, which denotes the first argument of the argument list. An
arbitrary number of arguments can be passed in via a message tag, but only those
arguments expected by the language key are used. The arguments are referenced in
order as `{0}`, `{1}`, etc. Let's pass in the user's screen name as an argument
to the `welcome-x` language key in the "My Greeting" portlet. 

1. Open the `view.jsp` file and add the following lines near the top of the JSP,
   just above the `<portlet:defineObjects />` tag. The first line imports the
   `liferay-theme` tag library. The second line defines the library's objects,
   providing access to the `user` object holding the user's screen name.

        <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>

        <liferay-theme:defineObjects />

2. Replace the current welcome message tag and exclamation point,
   `<liferay-ui:message key="welcome" />!`, in the JSP with the following:

        <liferay-ui:message key="welcome-x" arguments="<%= user.getScreenName() %>" />

When you refresh your page, your "My Greeting" portlet greets you by your screen
name!

![Figure 2: By passing the user's screen name as an argument to Liferay's `welcome-x` language key, we're able to display a personalized greeting.](../../images/screen-name-greeting.png)

That's all you need to do to leverage Liferay's core localization keys. In this
tutorial, you learned about Liferay core language keys, and how to implement in
a Liferay portlet.
