# Portlet Decorators [](id=portlet-decorators)

Portlet Decorators customize the style of an application's wrapper. If you
inspect the markup of your application when it's on a page you'll observe that
it is wrapped by two layers. Among other things, these layers provide some
common basic features like drag and drop and the application border style.
In order to protect these features, you can't modify the markup of these layers
directly with a theme.

With Portlet Decorators, you can add a CSS class to one of these wrapping layers
via a user's setting. By defining styles for this class in your theme, you can
change the look and feel of the application instances where the Portlet
Decorator is applied, including its wrapper.

The figure below shows the markup of the layers wrapping a @product@ application
when the *Decorate* Portlet Decorator is applied:

![Figure 1: Portlet Decorators add the decorator's CSS class to the application's wrapper](../../../../images/portlet-application-markup.png)

Once your Portlet Decorator is complete, apply it to your applications
through the [Look and Feel Configuration menu](https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/look-and-feel-configuration).

![Figure 2: Portlet Decorators can be applied through the Look and Feel Configuration menu](../../../../images/app-decor-look-and-feel.png)

The following sections detail how to customize Portlet Decorators and apply them to your
applications.
