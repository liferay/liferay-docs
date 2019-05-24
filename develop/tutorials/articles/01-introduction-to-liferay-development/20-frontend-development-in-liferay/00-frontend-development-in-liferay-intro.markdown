---
header-id: introduction-to-frontend-development
---

# Introduction to Front-End Development

When approaching the development of your application's front-end, @product@ 
offers a wide range of approaches, frameworks, utilities, and mechanisms to make 
your life easier.

## JavaScript

If you've used Liferay in the past, you can of course continue to use Liferay's
venerable Alloy UI, but you are also free to use the front-end technologies you
love the most:

-   ECMAScript 2015
-   Metal.js (developed by Liferay)
-   AlloyUI (developed by Liferay)
-   jQuery (included)
-   Lodash (included)

## Lexicon

@product@ follows a design language created by our designers at Liferay called 
[Lexicon Experience Language](https://lexicondesign.io/), which has been 
implemented for use of the web as [Lexicon](https://liferay.github.io/clay/).

Lexicon is automatically made available to application developers through a set 
of CSS classes and markup, although it's even easier to use our tag library. 

## Templates

For templating, Java EE's JSP is there as expected as well as FreeMarker, but the
platform's modularity enables using Google's Soy (aka Closure Templates) or
whatever else you like.

## Themes

A Liferay Theme is the overall look and feel for a site. Themes are a
combination of CSS, JavaScript, HTML, and FreeMarker templates. Although the
default themes are nice, you may wish to create your own look and feel for your
site.

In @product@, Liferay provides an easy-to-use tool called the [Liferay Theme Generator](/docs/7-0/tutorials/-/knowledge_base/t/themes-generator)
that helps automate the theme development process.

Themes created with the Liferay Theme Generator give you access to [theme gulp tasks](/docs/7-0/reference/-/knowledge_base/r/theme-gulp-tasks)
that offer basic functions, such as `build` and `deploy`, along with 
more complex interactions, such as auto deploying when a change is made and
setting the base theme.

## Front-End Extensions

@product@'s modularity has many benefits for the front-end developer, in the
form of development customizations and extension points. These extensions assure
the stability, conformity, and future evolution of your applications.

Below are some of the available front-end extensions:

- [Theme Contributors](/docs/7-0/tutorials/-/knowledge_base/t/theme-contributors)
- [Context Contributors](/docs/7-0/tutorials/-/knowledge_base/t/context-contributors)
- [Portlet Decorators](/docs/7-0/tutorials/-/knowledge_base/t/portlet-decorators)
- [Editor Config Contributors](/docs/7-0/tutorials/-/knowledge_base/t/modifying-an-editors-configuration)
