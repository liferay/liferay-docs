# Portlet Decorators [](id=portlet-decorators)

In previous versions of @product@, administrators could display or hide the
application borders through the *Show Borders* option of the look and feel
configuration menu. In @product-ver@ this option has been replaced with
Portlet Decorators, a more powerful mechanism to customize the style of the
application wrapper.

If you inspect the markup of your Liferay application when it's on a page you'll
observe that it is wrapped by two layers. Among other things, these layers
provide some common basic features like the drag and drop and the application
border style. In order to protect these features, you can't modify the markup
of these layers directly with a theme.

Portlet Decorators provide a mechanism to add a CSS class to one of these
wrapping layers via a user's setting. By defining styles for this class in your
theme, you can change the look and feel of the application instances where the
Portlet Decorator is applied, including its wrapper.

The figure below shows the markup of the layers wrapping a Liferay application
when the *Decorate* Portlet Decorator is applied:

![Figure 1: Portlet Decorators add the decorator's CSS class to the application's wrapper](../../../images/portlet-application-markup.png)

Once your Portlet Decorator is complete, apply it to your applications
through the [Look and Feel Configuration menu](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/look-and-feel-configuration).

![Figure 2: Portlet Decorators can be applied through the Look and Feel Configuration menu](../../../images/app-decor-look-and-feel.png)

Your portlet's decor is now in your hands.
