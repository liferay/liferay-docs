# Customizing the Product Menu [](id=customizing-the-product-menu)

Liferay's Product Menu comes with three major sections to choose from, by
default: the Control Panel, User Settings, and Site Administration. These
options are called panel categories, which is the term used to differentiate
between sections of the menu. For instance, the Control Panel is a single panel
category, and when clicking on it, you're presented with four other child panel
categories: *Users*, *Sites*, *Apps*, and *Configuration*. It you click on one
of these child panel categories, you're presented with panel apps.

+$$$

**Note:** The Product Menu cannot be changed by applying a new theme. To change
the layout/style of the Product Menu, you must create and deploy a theme
contributor. See the
[Theme Contributors](/develop/tutorials/-/knowledge_base/7-1/theme-contributors)
tutorial for more details.

$$$

This construction of the Product Menu was designed to be intuitive and easy to
use. For your instance of Liferay, however, you may want to add other panel
categories with custom panel apps. Also, you may desire to change the order of
your panel categories and/or apps. In this tutorial, you'll learn how to provide
your own custom or modify existing panel categories and panel apps for the
Product Menu.
