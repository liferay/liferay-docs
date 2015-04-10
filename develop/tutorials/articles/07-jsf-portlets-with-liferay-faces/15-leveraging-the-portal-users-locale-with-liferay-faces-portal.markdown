# Leveraging the Portal User's Locale with Liferay Faces Portal [](id=leveraging-the-portal-users-locale-with-liferay-faces-portal)

By default, the
[`Locale`](http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html) that
is normally used to present internationalized JSF views is based on the
web-browser's locale settings. In order to use the portal user's language
preference, Liferay Faces Portal automatically registers the
[`LiferayLocalePhaseListener`](https://github.com/liferay/liferay-faces/blob/3.2.4-ga5/portal/src/main/java/com/liferay/faces/portal/lifecycle/LiferayLocalePhaseListener.java).
This phase listener modifies the locale inside the
[`UIViewRoot`](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/component/UIViewRoot.html),
based on the user's language preference, which is accessed via the
[`User.getLocale`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/User.html#getLocale\(\))
method. 

Now you're familiar with another key utility that you can access through Liferay
Faces Portal. Awesome! 

**Related Topics**

[Localizing JSF Portlets](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/localizing-jsf-portlets)

[Understanding Liferay Faces Portal](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-portal)

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)
