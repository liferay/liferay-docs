# Introduction to Front-End Development [](id=introduction-to-frontend-development)

When approaching the development of your application's front-end, @product@ 
offers a wide range of approaches, frameworks, utilities, and mechanisms to make 
your life easier.

## JavaScript [](id=javascript)

If you've used Liferay in the past, you can of course continue to use Liferay's
venerable Alloy UI, but you are also free to use the front-end technologies you
love the most:

-   EcmaScript 2015
-   Metal.js (developed by Liferay)
-   AlloyUI (developed by Liferay)
-   jQuery (included)
-   Lodash (included)

## Lexicon [](id=lexicon)

@product@ follows a design language created by our designers called Lexicon. It
is automatically available to application developers through a set of CSS
classes, or our tag library.

## Templates [](id=templates)

For templating, Java EE's JSP is there as expected as well as FreeMarker, but the
platform's modularity enables using Google's Soy (aka Closure Templates) or
whatever else you like.

## Themes [](id=themes)

A Liferay Theme is the overall look and feel for a site. Themes are a
combination of CSS, JavaScript, HTML, and FreeMarker templates. Although the
default themes are nice, you may wish to create your own look and feel for your
site.

In @product@, Liferay provides an easy-to-use tool called the [Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
that helps automate the theme development process.

Themes created with the Liferay Theme Generator give you access to [theme gulp tasks](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks)
that offer basic functions, such as `build` and `deploy`, along with 
more complex interactions, such as auto deploying when a change is made and
setting the base theme.

## Front-End Extensions [](id=frontend-extensions)

@product@'s modularity has many benefits for the front-end developer, in the
form of development customizations and extension points. These extensions assure
the stability, conformity, and future evolution of your applications.

Below are some of the available front-end extensions:

- [Theme Contributors](/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
- [Context Contributors](/develop/tutorials/-/knowledge_base/7-0/context-contributors)
- [Portlet Decorators](/develop/tutorials/-/knowledge_base/7-0/portlet-decorators)
- [Editor Config Contributors](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration)
