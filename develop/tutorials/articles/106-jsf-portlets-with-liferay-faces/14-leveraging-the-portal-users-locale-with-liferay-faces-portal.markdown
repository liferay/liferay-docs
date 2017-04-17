# Leveraging the Portal User's Locale with Liferay Faces Portal [](id=leveraging-the-portal-users-locale-with-liferay-faces-portal)

By default, the
[`Locale`](http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html) that
is normally used to present internationalized JSF views is based on the
web-browser's locale settings. In order to use the portal user's language
preference, Liferay Faces Portal automatically registers the
[`LiferayLocalePhaseListener`](https://github.com/liferay/liferay-faces-portal/blob/3.0.0/portal/src/main/java/com/liferay/faces/portal/lifecycle/LiferayLocalePhaseListener.java).
This phase listener modifies the locale inside the
[`UIViewRoot`](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/component/UIViewRoot.html),
based on the user's language preference, which is accessed via the
[`User.getLocale`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/User.html#getLocale\(\))
method. 

Now you're familiar with another key utility that you can access through Liferay
Faces Portal. Awesome! 

## Related Topics [](id=related-topics)

[Localizing JSF Portlets](/develop/tutorials/-/knowledge_base/6-2/localizing-jsf-portlets)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)

[Liferay Faces Alloy UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-alloy-ui-components)

[Understanding Liferay Faces Alloy](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-alloy)

[Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-bridge-ui-components)
