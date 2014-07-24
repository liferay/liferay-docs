# Leveraging the Portal User's Locale [](id=liferay-faces-portal-liferay-locale-liferay-portal-6-2-dev-guide-en)

By default, the
[Locale](http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html) that is
normally used to present internationalized JSF views is based on the
web-browser's locale settings. In order to use the portal user's language
preference, Liferay Faces Portal automatically registers the
`LiferayLocalePhaseListener`. This phase listener modifies the locale inside the
[UIViewRoot](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/component/UIViewRoot.html),
based on the user's language preference returned by the
[User.getLocale()](http://docs.liferay.com/portal/6.2/javadocs/portal-service/com/liferay/portal/model/User.html#getLocale())
method. 

Now that you're familiar with some of the key utilities that you can access
through Liferay Faces Portal, let's look at the UIComponent and composite
component tags that you can leverage through Liferay Faces Portal. 
