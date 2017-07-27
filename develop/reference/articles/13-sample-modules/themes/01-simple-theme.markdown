# Simple Theme [](id=theme)

The Simple Theme sample provides the base files for a theme, using the
[Theme Builder Gradle plugin](/develop/reference/-/knowledge_base/7-0/theme-builder-gradle-plugin).

When deploying this sample with no customizations, a theme based off of the 
`_styled` base theme is created. For more information on themes, visit the 
[Introduction to Themes](/develop/tutorials/-/knowledge_base/7-0/introduction-to-themes)
tutorial.

![Figure 1: A theme based off of the Styled base theme is created when the Theme Blade sample is deployed to Liferay Portal.](../../../images/theme.png)

To modify this sample, add the `images`, `js`, or `templates` directory, along
with your modified files, to the `src/main/webapp` directory. The sample already
provides the `src/main/resources/resources-importer`, `src/main/webapp/WEB-INF`,
and `src/main/webapp/css` directories for you. Add your style modifications to
the provided `css/_custom.scss` file. For a complete explanation of a theme's
files, see the
[Theme Reference Guide](/develop/reference/-/knowledge_base/7-0/theme-reference-guide).

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/themes/simple-theme)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/wars/simple-theme)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/themes/simple-theme)
