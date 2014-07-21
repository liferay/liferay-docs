# Setting a Base Theme [](id=setting-a-base-theme)

All themes in Liferay are built on top of two base themes, named *_unstyled* and
*_styled*. Your newly created theme is based on these by default, but they
contain very limited styling. You can take advantage of an *existing* theme's
styling by setting the theme of your choice as the base for your theme.

Base themes are added in layers. First *_unstyled* is added, giving you the core
of the theme, then *_styled*, providing the most basic elements. When you set a
different base theme, it's added on top of *_styled* and overrides the default
styling wherever there are differences. After the base themes are added, your
own custom styling is added on top.

Next you can learn a little more about the `styled theme`.

## The Styled Theme

By default, themes are based on the *_styled* theme, which provides only basic
styling of portlets. If you open the `build.xml` file in your theme's directory,
you see the following code:

	<?xml version="1.0"?>
	<!DOCTYPE project>

	<project name="[theme-name]" basedir="." default="deploy">
		<import file="../build-common-theme.xml" />

		<property name="theme.parent" value="_styled" />
	</project>

The `theme.parent` property determines the theme your theme inherits its styling
from. 

Now that you have an understanding of the `styled theme` take a look at some
other base theme options next.

## Other Theme Options

In addition to the *_styled* theme, you can choose to inherit from the
*_unstyled* theme, which contains no styling. This makes more work for you, but
offers full flexibility to design your CSS files from scratch. 

You can also use the default Liferay theme **Classic** as a parent theme. You'll
start with a look and feel that's already smooth and works well. But since so
much is already done for you, there's less flexibility when building your
design. It's a compromise between creating a theme as quickly as possible versus
having full control of the result. It's your choice, and another example of the
flexibility Liferay offers.

To specify a base theme, edit the `build.xml` file for your theme and change
*_styled* in `<property name="theme.parent" value="_styled>` to the name of any
existing theme that's installed or in your Plugins SDK.

As you can see, setting a base theme is a piece of cake!

## Next Steps
<!-- URL will probably need updated when added to the new devsite-->
 [Using Developer Mode with Themes](/tutorials/-/knowledge_base/using-developer-mode)

 [Creating a Theme Thumbnail](/tutorials/-/knowledge_base/creating-a-theme-thumbnail)

