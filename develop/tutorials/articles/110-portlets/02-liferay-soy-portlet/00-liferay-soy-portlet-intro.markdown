# Liferay Soy Portlet [](id=liferay-soy-portlet)

Soy portlets are an extension of Liferay's MVC portlet. This gives you access to
all of the MVC Portlet functionality you are familar with, and you get the added
bonus of using Soy templates for writing your frontend, rather than JSPs. Soy
templates are an easy-to-use templating language that also allows you to use
MetalJS components. With all these benefits and more, Soy portlets can be a good
frontend tool to have in your utility belt.

You can learn about Liferay MVC portlets in the [Creating an MVC Portlet](/develop/tutorials/-/knowledge_base/7-0/creating-an-mvc-portlet)
tutorial.

To create a Soy portlet, you'll need these key components:

-  A module that publishes a portlet component with the necessary properties.
-  Controller code to handle the request and response
-  Soy templates to implement your view layer

This tutorial covers how to implement a Soy portlet.

Go ahead and get starting by configuring the module next.

## Configuring the module

The first thing you will need to do is configure the WEB module for your Soy
portlet.

The file structure of a Soy Portlet is very similar to the structure of an MVC
portlet. The basic structure of a Soy portlet module is shown below:

- `my-soy-portlet`
    - `.lfrbuild-portal`
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
            - `MyComponent.es.js` (MetalJS component)
            - `MyComponent.soy` (Soy template)
            - `MyComponent.scss` (Sass file)

<!--

The sample module structure shows an `*.scss` file in the resources folder. Is
this needed? Is this the only way to handle styling for the views?

It is not used in the Hello-Soy-Portlet.

Does the Sass file name have to match the template and JS filename to be used
for that specific file?

-->

<!--
You can also use the blade template to build your initial project.
(Point to Blade tutorial). Add this back  after blade tutorial is published.
Remove this info and add to separate text file.
-->

Follow these steps to specify the OSGi metadata and dependencies for your module:

1.  Add the OSGi metadata to your module's `bnd.bnd` file. A sample BND
    configuration is shown below:

    Bundle-Name: Liferay Hello Soy Web
    Bundle-SymbolicName: com.liferay.hello.soy.web
    Bundle-Version: 1.0.3
    Require-Capability: soy;filter:="(type=metal)"
    Include-Resource:\
        META-INF/resources=src/main/resources/META-INF/resources

In addition to the standard metadata, take notice of the `Require-Capability`
property. This specifies that this bundle requires modules that provide the
capability `soy` with a `type` of `metal` to work. The `Include-Resource`
property defines the full filepath where the module's resources are located.

<!--

Should Include resource point to that META-INF location since it is outside of
portal? I had to configure this for another external module I worked on. I'm
guessing that this is required?

-->

2. Specify dependencies in your `package.json`. Below is an example
configuration:

        {
                "dependencies": {
                        "metal-component": "^2.4.5",
                        "metal-soy": "^2.4.5"
                },
                "devDependencies": {
                        "liferay-module-config-generator": "^1.1.10",
                        "metal-cli": "^1.3.1"
                },
                "name": "hello-soy-web",
                "version": "1.0.3"
        }

    This provides everything you need to create a Metal component based on soy.
    Your `package.json` should contain the same `dependencies` and
    `devDependencies`.

3. Add the dependencies shown below to your `build.gradle`:

    dependencies {
            compileOnly group: 'com.liferay.portal',
            name: 'com.liferay.portal.kernel', version: '2.0.0'
            compileOnly group: 'com.liferay.portal',
            name: 'com.liferay.util.java', version: '2.0.0'
            compileOnly group: 'javax.portlet',
            name: 'portlet-api', version: '2.0'
            compileOnly group: 'javax.servlet',
            name: 'javax.servlet-api', version: '3.0.1'
            compileOnly group: 'org.osgi',
            name: 'org.osgi.service.component.annotations', version: '1.3.0'
            compileOnly project(':com.liferay.portal.portlet.bridge.soy')
    }

Now that your module is configured, you can learn how to create the Soy portlet
component next.

## Creating the Soy Portlet Component

Create a Soy Portlet component that extends the `SoyPortlet` class.

This requires an implementation of `javax.portlet.portlet` service to run.
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

Liferay's `SoyPortlet` extends `MVCPortlet`, which is an extension itself of
`javax.portlet.portlet`, so you've provided the right implementation.

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
used to develop an MVC portlet. You can find a list of all the Liferay-specific
attributes that are available for use as properties in your portlet components
in the [`liferay-portlet-app_7_0_0.dtd`](https://docs.liferay.com/portal/7.0/definitions/liferay-portlet-app_7_0_0.dtd.html).

The `javax.portlet...` properties are elements of the [portlet.xml descriptor](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)

Liferay's DTD files can be found [here](https://docs.liferay.com/portal/7.0/definitions/)

The foundation for your Soy portlet component is set. You can write the
controller for the Soy portlet next.

## Writing Controller Code

Soy portlets extend MVC portlets, so they use the same model view
controller framework to operate.

your controller receives requests from the front end, and it receives data from
the back end. It’s responsible for sending that data to the right front end view
so it can be displayed to the user, and it’s responsible for taking data the
user entered in the front end and passing it to the right back end service. For
this reason, it needs a way to process requests from the front end and respond
to them appropriately, and it needs a way to determine the appropriate front end
view to pass data back to the user.

### Render Logic

The render logic is where all the magic happens. After all, what's the use of a
portlet if you can't see it?

Note the `init-param` properties you set in your Component:

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=View",

This directs the default rendering to View(View.soy). The `template-path`
property tells the framework where your Soy templates are located. The `/`
above means that the Soy files are located in your project's root `resources`
folder. That's why it's important to follow Liferay's standard folder structure,
outlined above. Here's the path of a hypothetical Web module's resource folder:

    docs.liferaysoy.web/src/main/resources/META-INF/resources

In this case, the `View.soy` file is found at:

    docs.liferaysoy.web/src/main/resources/META-INF/resources/View.soy

and that's the default view of the application. When the `init` method is
called, the initialization parameters you specify are read and used to direct
rendering to the default template.Throughout this framework, you can render a
different view(Soy template) by setting the `mvcRenderCommandName` parameter of
the `PortletURL` to the soy template like this:

    navigationURL.setParameter("mvcRenderCommandName", "View");

Each view, excluding the default template view, **must have an implementation of
`MVCRenderCommand`.** The `*MVCRenderCommand` implementation must declare itself
as a component with the `MVCRenderCommand` service, and it must specify the
portlet's name and MVC command name using the `javax.portlet.name` and
`mvc.command.name` properties respectively.

More info on the MVCRenderCommand can be found [here](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/mvc-render-command)

Below is an example `MVCRenderCommand` implementation for the
[`com.liferay.hello.soy.web` portlet](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/hello-soy/hello-soy-web/src/main/java/com/liferay/hello/soy/web/internal/portlet/action/HelloSoyNavigationExampleMVCRenderCommand.java):

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

With render logic, you’re providing the view layer with information to display
the data properly to the user. In this case the MVC command name is set to
`Navigation`. The MVC render command name for the `PortletURL` `navigationURL`
is set to `View`, using the `mvcRenderCommandName` attribute. the `navigationURL`
is then passed to the `Navigation.soy` template as the variable `navigationURL`
using the `template.put` method. Finally, the `*MVCRenderCommand` class returns
the MVC render command name as a `String`.

+$$$

**Note:** Variables declared in the main `*SoyPortlet` class can be used in
any of the Soy templates. **This can be overwritten with a variable with the
same name in the `*MVCRenderCommand` implementation class.**

For example `navigationURL` is delcared as a variable for the `hello-soy-web`
module in both its `HelloSoyNavigationExampleMVCRenderCommand` class and
`HelloSoyPortlet` class, each pointing to a different MVC render command.

$$$

Below is an example `*SoyPortlet` class for the
[`com.liferay.hello.soy.web` portlet](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/hello-soy/hello-soy-web/src/main/java/com/liferay/hello/soy/web/internal/portlet/HelloSoyPortlet.java):


    public class HelloSoyPortlet extends SoyPortlet {

            @Override
            public void render(
                            RenderRequest renderRequest, RenderResponse renderResponse)
                    throws IOException, PortletException {

                    PortletURL navigationURL = renderResponse.createRenderURL();

                    navigationURL.setParameter("mvcRenderCommandName", "Navigation");

                    template.put("navigationURL", navigationURL.toString());

                    template.put("releaseInfo", ReleaseInfo.getReleaseInfo());

                    super.render(renderRequest, renderResponse);
            }

            @Reference
            protected LayoutService layoutService;

    }

The `navigationURL` is set to link to the *Navigation* soy template when
rendered. The `navigationURL` and `releaseInfo` are then passed as variables to
the soy templates with the `template.put()` method. Since this logic should be
executed before the default `render` method, the method concludes by calling
`super.render`.

Now that you understand the render logic, you can learn how to configure the
view layer next.

## Configuring the View Layer

You now know how to extend Liferay’s SoyPortlet to write controller code and
register a Component in the OSGi runtime. You also need a view layer, of course,
and for that, you’ll use Soy templates.

This section will briefly cover how to get your view layer working, from
including other soy templates, to creating a Metal JS component for rendering
your views.

Soy templates are defined in a file with extension `.soy`. The filename is
arbitary. The soy template's name is specified at the top of the template using
the `namespace`declaration. For example, this template has the namespace `View`:

    {namespace View}

It can be accessed in another soy template by calling the `render` method on the
namespace as shown below:

    {call View.render data="all"}{/call}

If needed, you can access some java theme object variables from within the Soy
template. For example, to access the `ThemeDisplay` object in a Soy template,
use the following syntax:

    $themeDisplay

You can also access the `Locale` object by using `locale`.

<!-- Should we mention how to extend this to add more objects, using a context
template?
-->

Here is the full [`View.soy` template]() for the
[`com.liferay.hello.soy.web` portlet](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/hello-soy/hello-soy-web)
which demonstrates the features covered in this section:

    {namespace View}

    /**
     * Prints the Hello Soy portlet main view.
     *
     * @param id
     * @param navigationURL
     * @param themeDisplay
     */
    {template .render}
            <div id="{$id}">
                    {call Header.render data="all"}{/call}

                    <p>{msg desc=""}
                    here-you-will-find-how-easy-it-is-to-do-things-like
                    {/msg}</p>

                    <h3>{msg desc=""}listing-pages{/msg}</h3>

                    <div class="list-group">
                            <div class="list-group-heading">{msg desc=""}
                            navigate-to{/msg}
                            </div>

                            {foreach $layout in $themeDisplay.layouts}
                                    <a class="list-group-item"
                                    href="{$layout.friendlyURL}">
                                    {$layout.nameCurrentValue}
                                    </a>
                            {/foreach}
                    </div>

                    <h3>{msg desc=""}navigating-between-views{/msg}</h3>

                    <a href="{$navigationURL}">{msg desc=""}
                    click-here-to-navigate-to-another-view
                    {/msg}</a>

                    {call Footer.render data="all"}{/call}
            </div>
    {/template}

If your view has JavaScript logic associated with it, you'll need to create a
corresponding `*es.js` file (usually with the same name), that imports
the Soy templates the view requires and registers the view as a Metal JS
component. For example, here is the [`View.es.js` component](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/hello-soy/hello-soy-web/src/main/resources/META-INF/resources/View.es.js)
for `com.liferay.hello.soy.web` portlet's `View.soy` template:

    import Component from 'metal-component/src/Component';
    import Footer from './Footer.es';
    import Header from './Header.es';
    import Soy from 'metal-soy/src/Soy';
    import templates from './View.soy';

    class View extends Component {
            constructor(opt_config) {
                    super(opt_config);
            }
    }

    // Register component
    Soy.register(View, templates);

    export default View;

Note that the `constuctor()` method is only needed for Metal components that are
associated with a view. For example, because the Header and Footer templates
shown above only add additional content to the views, they do not require a
constructor.

<!-- Is that true? -->

As you can see, with Liferay Soy it’s pretty easy to make your controller talk
to your view layer.

## Related Topics

<!-- Links to go here -->
