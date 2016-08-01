# Leveraging the Portal User's Locale with Liferay Faces Portal

By default, the
[`Locale`](http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html) that
is normally used to present internationalized JSF views is based on the
web-browser's locale settings. In order to use the portal user's language
preference, Liferay Faces Portal automatically registers the
[`LiferayLocalePhaseListener`](https://github.com/liferay/liferay-faces/blob/4.2.5-ga6/portal/src/main/java/com/liferay/faces/portal/lifecycle/LiferayLocalePhaseListener.java).
The `LiferayLocalePhaseListener` is registered by adding the following in the
`faces-config.xml` descriptor:

    <lifecycle>
        <phase-listener>com.liferay.faces.portal.lifecycle.LiferayLocalePhaseListener</phase-listener>
    </lifecycle>

This phase listener modifies the locale inside the
[`UIViewRoot`](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/component/UIViewRoot.html),
based on the user's language preference, which is accessed via the
[`User.getLocale`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/model/User.html#getLocale\(\))
method. 

Now you're familiar with another key utility that you can access through Liferay
Faces Portal. Awesome! 

## Related Topics

[Internationalization](/develop/tutorials/-/knowledge_base/7-0/internationalization)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials/-/knowledge_base/7-0/understanding-liferay-faces-alloy)
