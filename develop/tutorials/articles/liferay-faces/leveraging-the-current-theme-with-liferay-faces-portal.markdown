# Leveraging the Current Theme [](id=current-theme-in-jsf-liferay-faces-portal-liferay-portal-6-2-dev-guide-en)

Liferay Faces Portal offers several features to help you access and use the
current Liferay theme. 

Liferay Faces Portal provides the `LiferayFacesContext.getThemeDisplay()` method
at the Java level and the `liferay.themeDisplay` EL variable at the Facelet
level, for accessing the Liferay
[`ThemeDisplay`](http://docs.liferay.com/portal/6.2/javadocs/portal-service/com/liferay/portal/theme/ThemeDisplay.html)
object. 

Liferay Faces Portal provides the `liferay-ui:icon` Facelet composite component
tag that encapsulates an HTML `img` tag whose `src` attribute contains a fully
qualified URL to an icon image in the current Liferay theme. Additionally,
Liferay Faces Portal provides the `liferay.themeImagesURL` and
`liferay.themeImageURL` Facelet composite component tags for gaining access to
theme image icons. 
