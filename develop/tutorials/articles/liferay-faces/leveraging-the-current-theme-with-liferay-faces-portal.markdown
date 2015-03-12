# Leveraging the Current Theme with Liferay Faces Portal

Liferay Faces Portal offers several features to help you access and use the
current Liferay theme. In this tutorial, you'll learn how to take advantage of
these features. 

Liferay Faces Portal provides the `LiferayFacesContext.getThemeDisplay()` method
at the Java level and the `liferay.themeDisplay` EL variable at the Facelet
level, for accessing the Liferay
[`ThemeDisplay`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/theme/ThemeDisplay.html)
object. 

Liferay Faces Portal provides the `liferay-ui:icon` Facelet composite component
tag that encapsulates an HTML `img` tag whose `src` attribute contains a fully
qualified URL to an icon image in the current Liferay theme. Additionally,
Liferay Faces Portal provides the `liferay.themeImagesURL` and
`liferay.themeImageURL` Facelet composite component tags for gaining access to
theme image icons. 

By using some of these features, you'll be able to access the current Liferay
theme. 

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->
