# Using Liferay's Language Keys [](id=using-liferays-language-keys)

<!-- The beginning and ending test portlets can be found at the following:
Begin: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferayui/success/end/my-greeting-portlet
End: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/loc/use-lang-keys/end/my-greeting-portlet
-->

Liferay specifies a host of language keys in its core `Language.properties`
file. This file can be found in two different places. If you want to extract it
from an existing runtime, you can find it in the `content` folder of your portal
instance's `portal-impl.jar`. This file is in the
`tomcat-[version]/webapps/ROOT/WEB-INF/lib` directory of Liferay Portal bundled
with Tomcat or the `WEB-INF/lib` folder of the Liferay .war file. If you want to
get it from the Liferay source code, you can find it in
`portal-impl/src/content` of your Liferay Portal source tree. Leveraging
Portal's core language keys saves you time, since these keys always have up to
date translations for multiple languages. Additionally, your portlet blends
better into Liferay's UI conventions. 

For this tutorial, you'll implement a custom greeting for a portal user. When
you're finished with this tutorial, a user can view the following greeting in
their custom portlet:

![Figure 1: The portal user's name is used in the `welcome` language key.](../../images/screen-name-greeting.png)

Before you get started implementing our own language key, you'll want to learn
the components of a Liferay UI language key.

You can use a language key in your JSP via a `<liferay-ui:message />` tag. 

    <liferay-ui:message key="message-key" />

You specify the message key corresponding to the language key in the
`Language.properties` file you want to display. For example, to welcome a user
in his or her language, specify the message key named `welcome`.

    <liferay-ui:message key="welcome" />

This key maps to the word "Welcome" in your translation of it to the
user's locale. Here is the `welcome` language key from Liferay's
`Language.properties` file.

    welcome=Welcome

Now that you know some basics, it's time to implement the `welcome` language key for a
custom portlet.

1. Open your portlet's `view.jsp` file.

2. To use the `welcome` language key in the portlet's UI, insert the following
greeting paragraph:

        <p><liferay-ui:message key="welcome" />! <%= greeting %></p>

The word "Welcome", from `Language.properties`, now precedes your greeting!

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
order as `{0}`, `{1}`, etc. Given this information, you now know how to pass in
the user's screen name as an argument to the `welcome-x` language key in your
portlet. You can compare your portlet's `view.jsp` to the completed
[`view.jsp`](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/loc/use-lang-keys/end/my-greeting-portlet/docroot/view.jsp)
for reference after you've completed the following steps. 

1. Open the `view.jsp` file and add the following lines: 

        <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>

        <liferay-theme:defineObjects />

    The first line imports the `liferay-theme` tag library. The second line
    defines the library's objects, providing access to the `user` object holding
    the user's screen name.

2. Replace the current welcome message tag and exclamation point,
   `<liferay-ui:message key="welcome" />!`, in the JSP with the following code:

        <liferay-ui:message key="welcome-x" arguments="<%= user.getScreenName() %>" />

Your portlet now greets you by your screen name!

![Figure 2: By passing the user's screen name as an argument to Liferay's `welcome-x` language key, we're able to display a personalized greeting.](../../images/screen-name-greeting.png)

That's all you need to do to leverage Liferay's core localization keys. In this
tutorial, you learned about Liferay core language keys, and how to implement in
a Liferay portlet. 

Related Topics:

[Overriding Language Properties Using a Hook](/develop/tutorials/-/knowledge_base/6-2/overriding-language-properties-using-a-hook)

