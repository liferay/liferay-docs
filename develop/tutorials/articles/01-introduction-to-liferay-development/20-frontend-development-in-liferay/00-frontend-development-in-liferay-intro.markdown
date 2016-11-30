# Introduction to Front-End Development in @product@ [](id=introduction-to-frontend-development-in-liferay)

When approaching the development of your application's Front-End, @product@ offers a wide range of approaches, frameworks, utilities and mechanisms to make your life easier.

## JavaScript [](id=javascript)

If you've used Liferay in the past, you can of course continue to
use Liferay's venerable Alloy UI, but you are also free to use the front-end
technologies you love the most:

-   EcmaScript 2015
-   Metal.js (developed by Liferay)
-   AlloyUI (developed by Liferay)
-   jQuery (included)
-   Lodash (included)

## Lexicon [](id=lexicon)

@product@ follows a design language created by our designers at Liferay called
Lexicon. It is automatically available to application developers through a set
of CSS classes, although it's even easier to use our tag library.

## Templates [](id=templates)

For templating, JavaEE's JSP is there as expected as well as FreeMarker, but the
modularity of the platform allows you to use Google's Soy (aka Closure
Templates) or whatever else you like.

## Themes [](id=themes)

A Liferay Theme is the overall look and feel for a site. Themes are a
combination of CSS, JavaScript, HTML, and FreeMarker templates. Although the
default themes are nice, you may wish to create your own custom look and feel
for your site.

In @product@, Liferay provides an easy-to-use tool called the [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator) that helps automate the theme development process.

Themes created with the Themes Generator give you access to [theme gulp tasks](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks) that offer basic functions, such as `build` and `deploy`, as well as
more complex interactions, such as auto deploying when a change is made and
setting the base theme.

## Front-End Extensions [](id=frontend-extensions)

All the new modularity efforts in @product@ have also their reflection in the Front-End world, allowing a wide variety of customizations and extensions that conform to the overall modular architecture. By using these extensions, application developers are assured of the stability, conformance and future evolution of their work.

Some of these examples are:

- [Theme Contributors](/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
- [Context Contributors](/develop/tutorials/-/knowledge_base/7-0/context-contributors)
- [Portlet Decorators](/develop/tutorials/-/knowledge_base/7-0/application-decorators)
- [Editor Config Contributors](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration)
