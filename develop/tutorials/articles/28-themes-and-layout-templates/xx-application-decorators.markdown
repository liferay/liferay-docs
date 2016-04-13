# Application Decorators

In previous versions of Liferay, administrators could display or hide the
application borders through the *Show Borders* option of the look and feel
configuration menu. In Liferay 7.0 this option has been replaced with 
Application Decorators, a more powerful mechanism to customize the style of the 
application wrapper.

For example, the Liferay 7 [Classic theme](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-theme/frontend-theme-classic-web) 
includes three Application Decorators:

-   Decorate: this is the default Application Decorator when using the
    Classic theme. When this decorator is applied, the application is
    wrapped in a white box with a border and the application custom
    title is displayed at the top.

![Screen Shot 2016-03-30 at 11.13.04 AM.png](../../images/image04.png)

-   Borderless: when this decorator is applied, the application is no
    longer wrapped in a white box, but the application custom title is
    displayed at the top.

![Screen Shot 2016-03-30 at 11.14.37 AM.png](../../images/image02.png)

-   Barebone: when this decorator is applied, neither the wrapping box
    nor the custom application title are shown. This option is
    recommended when you only want to display the bare application
    content.

![Screen Shot 2016-03-30 at 11.35.18 AM.png](../../images/image03.png)

+$$$

**Note:** Upgrading to Liferay 7 will automatically assign the “borderless”
decorator to those applications that had the “Show Borders” option set to false 
in previous versions of Liferay.

As mentioned, you should consider to update your Liferay Themes to provide at 
least one for the “decorate”, “borderless” and “barebone” use cases.

$$$

If you inspect the markup of your Liferay application when it's on a page you'll 
observe that it is wrapped by two layers. Among other things, these layers 
provide some common basic features like the drag and drop and the application 
border style. In order to protect these features, you cannot modify the markup 
of these layers directly with a theme.

Application Decorators provide a mechanism to add a custom CSS class to one of 
these wrapping layers via a user’s setting. By defining styles for this class in 
your theme, you can change the look and feel of the application instances where 
the Application Decorator is applied, including its wrapper.

The figure below shows the markup of the layers wrapping a Liferay application
when the *Decorate* Application Decorator is applied:

![code.png](../../images/image01.png)

This tutorial demonstrates how to:

- Add Application Decorators to your theme

- Affect theme markup with Application Decorators

Now that you understand a little more about what Application Decorators are and 
how they work, you can learn how to use them next.

## Using Application Decorators

You can set the Application Decorator for an application through its 
*Look and Feel Configuration* menu. Just select a different option in the 
*Application Decorator* field and save and refresh the page to apply the changes.

![Figure x: Application Decorators can be set through the *Look and Feel* menu.](../../images/image00.png)

Now that you know how to configure Application Decorators, you can learn how to
add them to your theme next.

## Adding Application Decorators to a Theme

Adding Application Decorators to your theme is quite similar to adding
Color Schemes. You just have to follow these steps:

1.  Configure your theme's `liferay-look-and-feel.xml`
2.  Define the Application Decorator CSS styles
3.  Optional: Add conditions to your theme's markup

Now that you know the steps, go ahead and get started.

### Configuring liferay-look-and-feel.xml

The first thing you'll need to do is declare the Application Decorators in your 
theme's `liferay-look-and-feel.xml`.

The [Document Type Definition for the liferay-look-and-feel.xml](https://docs.liferay.com/portal/7.0/definitions/liferay-look-and-feel_7_0_0.dtd.html#portlet-decorator) 
in Liferay 7 contains the information and rules to add Application Decorators 
(codewise referred as portlet-decorators) to your theme.

Here is how the classic theme defines Application Decorators in its
`liferay-look-and-feel.xml`:

    <?xml version="1.0"?>
    <!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 7.0.0//EN" "http://www.liferay.com/dtd/liferay-look-and-feel_7_0_0.dtd">

    <look-and-feel>
	    <compatibility>
		    <version>7.0.0+</version>
	    </compatibility>
	    ...
            <theme id="classic" name="Classic">
		    ...
		    <portlet-decorator id="barebone" name="Barebone">
			    <portlet-decorator-css-class>portlet-barebone</portlet-decorator-css-class>
		    </portlet-decorator>
		    <portlet-decorator id="borderless" name="Borderless">
			    <portlet-decorator-css-class>portlet-borderless</portlet-decorator-css-class>
		    </portlet-decorator>
		    <portlet-decorator id="decorate" name="Decorate">
			    <default-portlet-decorator>true</default-portlet-decorator>
			    <portlet-decorator-css-class>portlet-decorate</portlet-decorator-css-class>
		    </portlet-decorator>
            </theme>
    </look-and-feel>

The `portlet-decorator` element contains all the information about the
Application Decorator:

-   id: this required attribute contains a unique string that identifies this 
    specific Application Decorator. This is the value that will be stored when 
    applying an Application Decorator, and it can be used to refer to this 
    decorator in your theme templates.
-   name: this required attribute is a user friendly identifier for the 
    Application Decorator to be displayed in the Look and Feel UI.
-   portlet-decorator-css-class: this required element contains the name of the 
    CSS class that will be added to the application wrapping layer when this 
    Application Decorator is applied.
-   default-portlet-decorator: use this optional element to identify the default 
    Application Decorator for your theme.

You can define as many Application Decorators as you want, but it’s recommended 
to include at least one for the *decorate*, *borderless* and *barebone* use 
cases.

### Define the Styles for Your Application Decorator CSS Class

Once you’ve declared your Application Decorators, it’s time to define their 
effect in the application look and feel. While the previous step was quite 
straightforward, this depends on your creativity.

As an example, take a look to the `_portlet_decorator.scss` of the
Classic theme:

    .portlet-decorate .portlet-content {
	    background: #FFF;
	    border: 1px solid #DEEEEE;
    }

    .portlet-barebone .portlet-content {
	    padding: 0;
    }
    
Once your CSS styles are written, make sure to import the CSS file into your 
`_custom.scss`:

    @import "portlet_decorator"
    
That's all that is required to add Application Decorators to your theme. If you
want to modify your application's markup with your Application Decorators, read
the next section.

### Changing Your Application Markup with Application Decorators

So far you’ve seen how to use Application Decorators to change the look and feel 
of an application with styles.

It’s possible to go a step further and alter the markup of your application 
based on the Application Decorator applied. For this, you simply have to edit
the `portlet.ftl` template for your theme, retrieve the `portletDecoratorId` of 
the current Application Decorator from the `portlet_display` object and make 
some decisions based on it.

For example, this is how the Classic theme shows the custom application title 
when the *barebone* Application Decorator is not applied:

    <#if portlet_display.getPortletDecoratorId() != "barebone">
            <h2 class="portlet-title-text">${portlet_title}</h2>
    </#if>
    
There you have it! Now you know how to add Application Decorators to your theme.
Let your creativity be your guide.

Read the next section to learn how to apply Application Decorators to portles
you've embedded in your theme.

## Applying a Default Application Decorator to Embedded Portlets

Once you have installed a theme that contains Application Decorators, site 
administrators can apply them to an application instance by selecting the 
Application Decorator in the Look and Feel Configuration dialog.

If your theme contains embedded applications, it’s also possible to apply an 
Application Decorator other than the default one by setting its preferences.

For example, the Classic theme declares an Application Decorator with id 
*barebone* and applies it to the embedded Navigation Menu application and Search 
application in its `navigation.ftl`:

    <#assign VOID = 
    freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId", 
    "barebone")>

    <div aria-expanded="false" class="collapse navbar-collapse" 
    id="navigationCollapse">
	    <#if has_navigation && is_setup_complete>
		    <nav class="${nav_css_class} site-navigation" 
		    id="navigation" role="navigation">
			    <div class="navbar-form navbar-right" role="search">
				    <@liferay.search default_preferences=
				    "${freeMarkerPortletPreferences}" />
			    </div>

			    <div class="navbar-right">
				    <@liferay.navigation_menu default_preferences=
				    "${freeMarkerPortletPreferences}" />
			    </div>
		    </nav>
	    </#if>
    </div>

    <#assign VOID = freeMarkerPortletPreferences.reset()>

The *barebone* decorator is set as the `portletSetupPortletDecoratorId` for the
FreeMarker portlet preferences. Once the value is set for the preferences it is
assigned as the `default_preferences` for the application through its tag.

You can apply your Application Decorators to the embedded portlets in your
theme, using the same process.
