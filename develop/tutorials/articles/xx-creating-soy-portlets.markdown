# Creating a Soy Portlet

This template language allows me to use Metal components.

<!-- Is this the only templating language that allows me to use Metal components?

Can I use metal components in JSPs for instance?

(This is more of a broad question) Looking into the future, seeing as AUI is
going to be removed completely and replaced with Metal components, are soy
portlets going to be what we move to as our primary focus in the future? 

-->

<!--

List benefits of soy portlets here

-->

## Configuring a Web Module

The file strucutre of a Soy Portlet is very similar to the structure of an MVC 
portlet:

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
            - `MyComponent.es.js`
            - `MyComponent.soy`
            - `MyComponent.scss`

<!-- Is the `action folder may simply be for organization.`

-->

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

## Specifying OSGi Metadata

1.  Specify OSGi metadata, specifying the bundle symbolic name, bundle version,
and require capability at minimum:

    Bundle-Name: Liferay Hello Soy Web
    Bundle-SymbolicName: com.liferay.hello.soy.web
    Bundle-Version: 1.0.3
    Require-Capability: soy;filter:="(type=metal)"
    Include-Resource:\
        META-INF/resources=src/main/resources/META-INF/resources
    
In addition to the standard metadata, take notice of the `Require-Capability`.

<!-- What does that property `Require-Capability` do, and is it required?

Should Include resource point to that META-INF location since it is outside of
portal?

-->

2. Specify dependencies in your `package.json`:

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
    
<!-- Is this required? Is everything in here standard and required or commonly used?
What is each attribute doing here? Do you need this if your Soy portlet doesn't
contain any Metal components? Would you ever create a Soy portlet that didn't
contain metal components? why?

-->

3. Specify `build.gradle` dependencies:

<!-- Code sample goes here. Need to update file with proper class names used 
outside of portal and test build.-->

<!--

What is the `npm-shrinkwrap.json`? Is this a file used for caching, so NPM
doesn't have to download all this information everytime? Is it required? Why
would I want to include it?

-->

## Creating a Portlet Component

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
`javax.portlet.portlet`, so you've provided the right implementation. What this 
means is that you have access to all of the MVC Portlet functionality you are 
familar with, and you get the added bonus of using Soy templates for writing 
your frontend, rather than JSPs.

The component requires further fleshing out with more properties:

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

<!-- Why do you need to implement the `MVCRenderCommand`? 
As seen here: https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/hello-soy/hello-soy-web/src/main/java/com/liferay/hello/soy/web/internal/portlet/action/HelloSoyNavigationExampleMVCRenderCommand.java

Is that required to render the portlet?

From what I can tell, it is required if you wish to navigate to other views.

MVCRenderCommand implementation is required for creating URLs to navigate
betweem views.

-->

    public class MySoyPortlet extends SoyPortlet {
        @Override
        public void render(RenderRequest renderRequest, RenderResponse renderResponse) {
            //do things here
        }
    }

## Writing Controller Code

Soy portlets extend MVC portlets, so they essentially use the same model view
controller framework to operate.

your controller receives requests from the front end, and it receives data from 
the back end. It’s responsible for sending that data to the right front end view 
so it can be displayed to the user, and it’s responsible for taking data the 
user entered in the front end and passing it to the right back end service. For 
this reason, it needs a way to process requests from the front end and respond 
to them appropriately, and it needs a way to determine the appropriate front end 
view to pass data back to the user. 

### Render Logic

Note the `init-param` properties you set in your Component:

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=View",
    
This directs the default rendering to View(View.soy). The `template-path` 
property tells the framework where your Soy templates are located. The `/` 
above means that the Soy files are located in your project's root `resources` 
folder. That's why it's important to follow Liferay's standard folder structure, 
outlined above. Here's the path of a hypothetical Web module's
resource folder:

    docs.liferaysoy.web/src/main/resources/META-INF/resources
    
In this case, the `View.soy` file is found at:

    docs.liferaysoy.web/src/main/resources/META-INF/resources/View.soy
    
and that's the default view of the application. When the `init` method is
called, the initialization parameters you specify are read and used to direct
rendering to the default template. Throughout this framework, you can render a
different view(Soy template) by setting the `mvcRenderCommandName` parameter 
like this:

    navigationURL.setParameter("mvcRenderCommandName", "View");


    
In `*MVCRenderCommand.java` class that renders URL:

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

n some cases, the uses of initialization parameters and render parameters 
obviates the need for additional render logic. However, it’s often necessary to 
provide additional render logic. To do this, override the render method. Here’s 
an example:

<!-- Is this true with Soy portlets? -->
    
In `HelloSoyPortlet.java`:

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
    
With render logic, you’re providing the view layer with information to display 
the data properly to the user. In this case, the `navigationURL` is set to link
to the *Navigation* soy template when rendered. The `navigationURL` and 
`releaseInfo` are then passed as variables to the soy templates with the 
`template.put()` method. Since this logic should be executed before the default
`render` method, the method concludes by calling `super.render`.

<!-- Do you need an implementation of MVCRenderCommand for every new URL that 
you want to use? 

Answer: Yes, it appears so. If you have a new renderURL you need a new
implementation. 

So what are these classes doing? Flipping the NavigationURL?

They appear to be using the same MVCRenderCommand, but just changing the Soy 
template to use via an update to the NavigationURL.

Is this right?

This allows you to only have to implement MVCRenderCommand once, then you can
just update the NavigationURL to the soy template you need.

-->
    
In your Soy template you can set the page by using `$navigationURL` as the value
of the `href` attribute:

    <a href="{$navigationURL}">{msg desc=""}
    click-here-to-navigate-to-another-view{/msg}
    </a>

More info on the MVCRenderCommand can be found [here](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/mvc-render-command)

### Setting and Retrieving Request Parameters and Attributes

In the soy portlet class's render method and even in your soy templates you can
pass and set parameters. This was shown in the last section by setting the 
`mvcRenderCommandName` for the `navigationURL`:

    navigationURL.setParameter("mvcRenderCommandName", "Navigation");


The parameter is then passed as a variable with the `template.put()` method:

    template.put("navigationURL", navigationURL.toString());

Once this is configured you can access these parameters by declaring the
variable at the top of your soy template. Here is an example:

    {namespace View}
    
    /**
     * Prints the Hello Soy portlet main view.
     *
     * @param id
     * @param navigationURL
     * @param themeDisplay
     */
    {template .render}
    
Then the variable can be used in the template like this:

    <a href="{$navigationURL}">{msg desc=""}
    click-here-to-navigate-to-another-view{/msg}
    </a>

## Configuring the View Layer

You now know how to extend Liferay’s SoyPortlet to write controller code and 
register a Component in the OSGi runtime. You also need a view layer, of course, 
and for that, you’ll use Soy templates.

This section will briefly cover how to get your view layer working, from 
including other soy templates, to creating a Metal JS component for rendering 
your views.

To include a soy template within another soy template, call it by its name,
using the `render` method. For example, this call includes all of the content
from a soy template with the name `Header.soy`:

    {call Header.render data="all"}{/call}
    
Note that the soy template's name is declared at the top of the template with
the `namespace`. For example, this template has the namespace `View`:

    {namespace View}
    
It can be accessed in another soy template with the following call:

    {call View.render data="all"}{/call}
    
<!-- Do these templates have to be located in the same folder as the current 
template? Or can you use relative paths when calling the soy template? -->

If needed, you can access Java theme object variables from within the Soy 
template. For example, to access the `themeDisplay` object in a Soy template,
use the following syntax:

    $themeDisplay
    
<!-- What file are these theme object variables defined in?-->

Here is the full [`View.soy` template]() for the 
[`com.liferay.hello.soy.web` portlet](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/foundation/hello-soy/hello-soy-web) 
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

<!--

Is there a way to include java code in a soy template? Or should all of that be
handled in the java classes?

Is there an equivalent of an init.jsp for Soy? Or is that not really used?

-->
    
Once the Soy template is configured with the content you need, you need to 
create a corresponding `*es.js` file (usually with the same name), that imports 
the Soy templates the view requires and registers the view as a Metal JS 
component. For example, here is the [`View.es.js` component](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/hello-soy/hello-soy-web/src/main/resources/META-INF/resources/View.es.js)
for the `View.soy` template:

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

All rendering is done from within the java classes themeselves. To construct a 
URL that calls the render method of your controller

<!-- So does the view extend the Metal Component? Does it depend on it to render? -->

`View.es.js` <!-- Do I have access to these imports in this file outside of 
portal? Would they have the same relative path?

What does this file do exactly?

Does this handle client side rendering? So could we say that it is the
equivalent of an index.html file, in that it renders all the other html pages,
this renders all the templates that match that name or are imported into this
file?

What does the contructor do? I see that it is only called in the Soy templates
that are views (i.e. View.es.js and Navigation.es.js)

-->
