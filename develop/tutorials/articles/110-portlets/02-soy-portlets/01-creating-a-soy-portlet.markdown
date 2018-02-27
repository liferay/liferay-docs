# Creating a Soy Portlet [](id=creating-a-soy-portlet)

To create a Soy portlet, you'll need these key components:

-  A module that publishes a portlet component with the necessary properties
-  Controller code to handle the request and response
-  Soy templates to implement your view layer

## Configuring the Web Module [](id=configuring-a-web-module)

First, familiarize yourself with a Soy portlet's anatomy. You may recognize it, 
since a Soy portlet extends an MVC portlet:

- `my-soy-portlet`
    - `bnd.bnd`
    - `build.gradle`
    - `package.json`
    - `src/main/`
        - `java/path/to/portlet/`
            - `MySoyPortlet.java`
            - `action/`
                - `*MVCRenderCommand.java`
        - `resources/META-INF/resources/`
            - `content/`
                - `Language.properties`
            - `View.es.js` (MetalJS component)
            - `View.soy` (Soy template)

Now that you know the basic structure of a Soy portlet module, you can configure 
it. You can use the 
[soy portlet Blade template](/develop/reference/-/knowledge_base/7-1/soy-portlet-template) 
to build your initial project if you wish. Otherwise, you can follow the 
instructions in this section to manually configure the module. 

### Specifying OSGi Metadata [](id=specifying-osgi-metadata)

Add the OSGi metadata to your module's `bnd.bnd` file. A sample BND 
configuration is shown below:

    Bundle-Name: Liferay Hello Soy Web
    Bundle-SymbolicName: com.liferay.hello.soy.web
    Bundle-Version: 1.0.3
    Require-Capability:\
    	soy;\
    		filter:="(type=metal)"
    Include-Resource: package.json

Notice the addition of the `Require-Capability` property; This specifies that 
the bundle requires modules that provide the capability `soy` with a `type` of 
`metal` to work. Also note the `Include-Resource` property. **You must include** 
your `package.json` file to load the Soy Portlet's JavaScript files. 

### Specifying JavaScript Dependencies [](id=specifying-javascript-dependencies)

Specify the JavaScript module dependencies in your `package.json`. At a minimum, 
you should have the following dependencies and configuration parameters. Always 
use the latest component versions (the versions shown below may not be the 
latest).

    {
    	"dependencies": {
    		"metal-component": "^2.4.5",
    		"metal-soy": "^2.4.5"
    	},
    	"devDependencies": {
    		"liferay-module-config-generator": "^1.2.1",
    		"metal-cli": "^4.0.1"
    	},
    	"name": "my-portlet-name",
    	"version": "1.0.0"
    }

This provides everything you need to create a Metal component based on Soy. Note 
that the `version` in your `package.json` should match the `Bundle-Version` in 
your `bnd.bnd` file.

Next you can specify your module's build dependencies.

### Specifying Build Dependencies [](id=specifying-build-dependencies)

Add the dependencies shown below to your `build.gradle` file:

    dependencies {
    	provided group: "com.liferay", name: "com.liferay.portal.portlet.bridge.soy", version: "3.1.0"
    	provided group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
    	provided group: "com.liferay.portal", name: "com.liferay.util.java", version: "2.0.0"
    	provided group: "javax.portlet", name: "portlet-api", version: "2.0"
    	provided group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    	provided group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }

+$$$

**Note:** These are current at the time of this writing, but may change. Please 
check the 
[Nexus Repository](https://repository.liferay.com) 
for the proper versions for your @product@ instance.

$$$

Now that your module build is configured, you can learn how to create the Soy 
portlet component.

## Creating a Soy Portlet Component [](id=creating-a-soy-portlet-component)

Create a Soy Portlet component that extends the `SoyPortlet` class. This    
requires an implementation of the `javax.portlet.portlet` service to run. 
Declare this using an `@Component` annotation in the portlet class:

    @Component(
        immediate = true,
        service = Portlet.class
    )
    public class MySoyPortlet extends SoyPortlet {
        @Override
        public void render(RenderRequest renderRequest, RenderResponse renderResponse) {
            //do things here
        }
    }

@product@'s 
[`SoyPortlet` class](https://github.com/liferay/com-liferay-portal-portlet-bridge/blob/7.0.x/portal-portlet-bridge-soy/src/main/java/com/liferay/portal/portlet/bridge/soy/SoyPortlet.java)
extends its
[`MVCPortlet` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html),
which is an extension itself of `javax.portlet.Portlet`, so you've provided the
right implementation.

The component requires some properties as well. A sample configuration is shown
below:

    @Component(
            immediate = true,
            property = {
              "com.liferay.portlet.add-default-resource=true",
              "com.liferay.portlet.application-type=full-page-application",
              "com.liferay.portlet.application-type=widget",
              "com.liferay.portlet.display-category=category.sample",
              "com.liferay.portlet.layout-cacheable=true",
              "com.liferay.portlet.preferences-owned-by-group=true",
              "com.liferay.portlet.private-request-attributes=false",
              "com.liferay.portlet.private-session-attributes=false",
              "com.liferay.portlet.render-weight=50",
              "com.liferay.portlet.scopeable=true",
              "com.liferay.portlet.use-default-template=true",
              "javax.portlet.display-name=Hello Soy Portlet",
              "javax.portlet.expiration-cache=0",
              "javax.portlet.init-param.copy-request-parameters=true",
              "javax.portlet.init-param.template-path=/",
              "javax.portlet.init-param.view-template=View",
              "javax.portlet.name=hello_soy_portlet",
              "javax.portlet.resource-bundle=content.Language",
              "javax.portlet.security-role-ref=guest,power-user,user",
              "javax.portlet.supports.mime-type=text/html"
            },
            service = Portlet.class
    )

Some of these properties may seem familiar to you, as they are the same ones 
used to develop an MVC portlet. You can find a full list of the available 
Liferay-specific portlet component properties in the 
[`liferay-portlet-app_7_1_0.dtd`](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html).

The `javax.portlet...` properties are elements of the 
[portlet.xml descriptor](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)

Liferay's DTD files can be found 
[here](@platform-ref@/7.1-latest/definitions/)

Now that you've set your Soy portlet component's foundation, you can write the 
controller code.

## Writing Controller Code [](id=writing-controller-code)

Soy portlets extend MVC portlets, so they use the same Model-View-Controller 
framework to operate. Your controller receives requests from the front-end and 
data from the back-end. It's responsible for sending that data to the right 
front-end view so it can be displayed to the user, and it's responsible for 
taking data the user entered in the front-end and passing it to the right 
back-end service. For this reason, it needs a way to process requests from the 
front-end and respond to them appropriately, and it needs a way to determine the 
appropriate front-end view to pass data back to the user.

### Render Logic [](id=render-logic)

The render logic is where all the magic happens. After all, what's the use of a 
portlet if you can't see it? Note the `init-param` properties you set in your 
Component class:

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=View",

This directs the default rendering to View (`View.soy`). The `template-path` 
property specifies the location of your Soy templates. The `/` above means that 
the Soy files are located in the project's root `resources` folder. That's why 
it's important to follow the standard folder structure, outlined above. Here's 
the path of a hypothetical web module's resource folder:

    docs.liferaysoy.web/src/main/resources/META-INF/resources

In this case, the `View.soy` file is found at:

    docs.liferaysoy.web/src/main/resources/META-INF/resources/View.soy

That's the default view of the application. When the `init` method is called, 
the initialization parameters you specify are read and used to direct rendering 
to the default template. Throughout this framework, you can render a different 
view (Soy template) by setting the `mvcRenderCommandName` parameter of the 
`javax.portlet.PortletURL` to the Soy template that you want to render. The 
example below uses a portlet URL called `navigationURL` to render the view 
`View`:

    navigationURL.setParameter("mvcRenderCommandName", "View");

Each view, excluding the default template view, **must have** an implementation 
of the 
[`MVCRenderCommand` class](/develop/tutorials/-/knowledge_base/7-1/mvc-render-command). 
The `*MVCRenderCommand` implementation must declare itself as a component with 
the `MVCRenderCommand` service, and it must specify the portlet's name and MVC 
command name using the `javax.portlet.name` and `mvc.command.name` properties 
respectively. Below is an example `MVCRenderCommand` implementation for a 
`Navigation` Soy template:

    @Component(
            immediate = true,
            property = {
                    "javax.portlet.name=hello_soy_portlet",
                    "mvc.command.name=Navigation"
            },
            service = MVCRenderCommand.class
    )
    public class HelloSoyNavigationExampleMVCRenderCommand
            implements MVCRenderCommand {

            @Override
            public String render(
                    RenderRequest renderRequest, RenderResponse renderResponse) {

                    Template template = (Template)renderRequest.getAttribute(
                            WebKeys.TEMPLATE);

                    PortletURL navigationURL = renderResponse.createRenderURL();

                    navigationURL.setParameter("mvcRenderCommandName", "View");

                    template.put("navigationURL", navigationURL.toString());

                    return "Navigation";
            }

    }

The render logic provides the view layer with information to display the data 
properly to the user. Below is an explanation of the example above: 

- The MVC command name is `Navigation` (the Soy template with namespace 
`Navigation`). This means that this logic is for the `Navigation` view.
- A `PortletURL` (`navigationURL`) is defined and its `mvcRenderCommandName` is 
set to `View`(the Soy template with namespace `View`).
- The `navigationURL` is converted to a `String` and passed as the variable 
`navigationURL` to the `Navigation` Soy template with the `template.put()` 
method. 

Note that Soy portlet parameters are scoped to the portlet class they're written 
in. For instance, you can have a `navigationURL` parameter in two different 
classes, each with a different value. Below is an example 
`HelloSoyViewMVCRenderCommand` class that also defines a `navigationURL` 
parameter:

    public class HelloSoyViewMVCRenderCommand implements MVCRenderCommand {

    @Override
  	public String render(
    		RenderRequest renderRequest, RenderResponse renderResponse) {

    		Template template = (Template)renderRequest.getAttribute(
    			WebKeys.TEMPLATE);

    		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
    			WebKeys.THEME_DISPLAY);

    		template.put("layouts", themeDisplay.getLayouts());

    		PortletURL navigationURL = renderResponse.createRenderURL();

    		navigationURL.setParameter("mvcRenderCommandName", "Navigation");

    		template.put("navigationURL", navigationURL.toString());

    		template.put("releaseInfo", ReleaseInfo.getReleaseInfo());

    		return "View";
    	}

    }

Below is an explanation of the example above:

- The `navigationURL` points to the  `Navigation` Soy template this time. 
- The `navigationURL` and `releaseInfo` parameters are passed to the `View` Soy 
template. 
- Since this logic should be executed before the default `render` method, the 
method concludes by calling `super.render`.

Now that you understand the render logic, you can learn how the view layer 
works.

## Configuring the View Layer [](id=configuring-the-view-layer)

Your portlet also requires a view layer, and for that you’ll use Soy templates, 
which is the whole point of developing a Soy portlet, isn't it? This section 
briefly covers how to get your view layer working, from including other Soy 
templates, to creating a MetalJS component for rendering your views.

Soy templates are defined in a file with the extension `.soy`. The filename is 
arbitrary. The Soy template's name is specified at the top of the template using 
the `namespace` declaration. For example, the declaration below is for a `View` 
template:

    {namespace View}

It can be accessed in another Soy template by calling the `render` method on the 
namespace as shown below. The `data='all'` attribute specifies that the 
template should include all its parameters as well:

    {call View.render data="all"}{/call}

Below is an example `View` Soy template that includes `Header` and `Footer` Soy 
templates:

    {namespace View}

    /**
     * Prints the portlet main view.
     */
    {template .render}
      <div id="{$id}">
        {call Header.render data="all"}{/call}

        <p>{msg desc=""}here-is-a-message{/msg}</p>

        {call Footer.render data="all"}{/call}
      </div>
    {/template}

Each view has a corresponding `*es.js` file (usually with the same name) that 
imports the Soy templates the view requires and registers the view as a MetalJS 
component. This file is also used for any additional JavaScript logic your view 
may have. For example, here is a `View.es.js` component for a `View.soy` 
template:

    import Component from 'metal-component/src/Component';
    import Footer from './Footer.es';
    import Header from './Header.es';
    import Soy from 'metal-soy/src/Soy';
    import templates from './View.soy';

    /**
     * View Component
     */
    class View extends Component {}

    // Register component
    Soy.register(View, templates);

    export default View;
    
Now that you understand how to configure a Soy template view, you can learn how 
to use portlet parameters in your Soy templates next.

### Using Portlet Template Parameters in the Soy Template [](id=using-portlet-template-parameters-in-the-soy-template)

As mentioned above, the `template.put()` method exposes portlet parameters to 
the Soy templates. Once a parameter is exposed, you can access it in the Soy 
template by defining it at the top with the `@param name` declaration. For 
instance, the `hello-soy-web` portlet's `View` Soy template defines the 
`navigationURL` parameter with the code below:

    @param navigationURL
    
It is then used to navigate between portlet views:

    <a href="{$navigationURL}">{msg desc=""}
        click-here-to-navigate-to-another-view
    {/msg}</a>

Some Java theme object variables are available as well. For example, to access 
the 
[`ThemeDisplay` object](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html) 
in a Soy template, use the following syntax:

    {$themeDisplay}

You can also access the `Locale` object by using `{$locale}`. Here is the full 
`View.soy` template for the `com.liferay.hello.soy.web` portlet, which 
demonstrates the features covered in this section:

    {namespace View}

    /**
     * Prints the Hello Soy portlet main view.
     *
     * @param id
     * @param layouts
     * @param navigationURL
     */
    {template .render}
    	<div id="{$id}">
    		{call Header.render data="all"}{/call}

    		<p>
          {msg desc=""}here-you-will-find-how-easy-it-is-to-do-things-like{/msg}
        </p>

    		<h3>{msg desc=""}listing-pages{/msg}</h3>

    		<div class="list-group">
    			<div class="list-group-heading">{msg desc=""}navigate-to{/msg}</div>

    			{foreach $layout in $layouts}
    				<a class="list-group-item" href="{$layout.friendlyURL}">
              {$layout.nameCurrentValue}
            </a>
    			{/foreach}
    		</div>

    		<h3>{msg desc=""}navigating-between-views{/msg}</h3>

    		<a href="{$navigationURL}">
          {msg desc=""}click-here-to-navigate-to-another-view{/msg}
        </a>

    		{call Footer.render data="all"}{/call}
    	</div>
    {/template}

Now you know how to create a Soy Portlet!

## Related Topics [](id=related-topics)

[Liferay MVC Portlet](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet)

[JSF Portlets with Liferay Faces](/develop/tutorials/-/knowledge_base/7-1/jsf-portlets-with-liferay-faces)
