# Setting a Base Theme [](id=setting-a-base-theme)

Liferay's themes are built on top of two base themes, named *_unstyled* and
*_styled*. If you have another theme that you're rather base yours on, you can
do that by setting the theme of your choice as the base for your theme. 

Base themes are added in layers. First *_unstyled* is added, giving you the core
of the theme, then *_styled*, providing the most basic elements. When you set a
different base theme, it's added on top of *_styled* and overrides the default
styling wherever there are differences. After the base themes are added, your
own custom styling is added on top. 

In addition to the *_styled* theme, you can choose to inherit from the
*_unstyled* theme, which contains no styling. This makes more work for you, but
offers full flexibility to design your CSS files from scratch. 

You can also use the default Liferay theme *Classic* as a parent theme. You'll
start with a look and feel that's already smooth and works well. But since so
much is already done for you, there's less flexibility when building your
design. It's a compromise between creating a theme as quickly as possible versus
having full control of the result. It's your choice, and another example of the
flexibility Liferay offers.

The theme plugin project wizard in Liferay IDE/Developer Studio lets you select
your project's base theme. The Plugins SDK sets *_styled* as your base theme
which provides only basic styling of portlets. If you open the `build.xml` file
in your theme's directory, you'll see the code similar to the following code: 

	<?xml version="1.0"?>
	<!DOCTYPE project>

	<project name="[theme-name]" basedir="." default="deploy">
		<import file="../build-common-theme.xml" />

		<property name="theme.parent" value="_styled" />
	</project>

The `theme.parent` property determines the theme your theme inherits its styling
from. 

To specify a base theme, modify the `build.xml` file for your theme and change
the value of the `theme.parent`'s `value` attribute to the name of any existing
theme that's installed in your portal or available in your Plugins SDK. 

As you can see, setting a base theme is a piece of cake!

**Related Topics**

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/6-2/importing-resources-with-your-themes)

[Developing Liferay Theme Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-theme-plugins-with-maven)
