# Leveraging the Current Theme with Liferay Faces Portal [](id=leveraging-the-current-theme-with-liferay-faces-portal)

Liferay Faces Portal provides access to the Liferay [`ThemeDisplay`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/theme/ThemeDisplay.html)
object and the current theme's images. 

Liferay Faces Portal provides the `LiferayFacesContext.getThemeDisplay` method
for accessing the `ThemeDisplay` Java object and the `liferay.themeDisplay`
exception language (EL) variable for accessing the object in Facelets.

Additionally, Liferay Faces Portal provides the `liferay.themeImagesURL` and
`liferay.themeImageURL` Facelet EL variables for gaining access to theme image
URLs. You can use these URLs as attribute values for Facelet composite component
tags, like the `liferay-ui:icon` tag. The `liferay-ui:icon` tag encapsulates an
HTML `img` tag whose `src` attribute can be set to a fully qualified URL of an
icon image in the current Liferay theme.

By using some of these features, you'll be able to access the current Liferay
theme and its images. 

## Related Topics [](id=related-topics)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)

[Liferay Faces Alloy UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-bridge-ui-components)
