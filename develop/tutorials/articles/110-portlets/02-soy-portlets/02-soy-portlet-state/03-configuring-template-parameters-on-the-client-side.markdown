# Configuring Soy Portlet Template Parameters on the Client Side [](id=configuring-soy-portlet-template-parameters-on-the-client-side)

[Portlet template parameters](/develop/tutorials/-/knowledge_base/7-1/creating-a-soy-portlet#using-portlet-template-parameters-in-the-soy-template) 
are set in the Soy Portlet's server-side code. MetalJS's `state` class provides 
a 
[`STATE` object](/develop/tutorials/-/knowledge_base/7-1/understanding-the-state-object-architecture) 
that exposes these parameters as properties so you can access them on the client 
side. This tutorial covers how to configure your view component's `STATE` object 
and its properties on the client side so you can update the UI.

This tutorial references the example below.

## An Example Header State Portlet [](id=example-header-state-portlet)

This tutorial references the example portlet covered in this section. It 
includes one view with a header that reads *Hello Soy* by default.

![Figure 1: The example Soy portlet has a configurable header.](../../../../images/soy-example-portlet-start.png)

The text in the header following *Hello* is provided by the `header` state 
property defined in its `*mvcRenderCommand` class:

`*MVCRenderCommand` class:

    @Component(
    	immediate = true,
    	property = {
    		"javax.portlet.name=MyStateSoyPortlet", "mvc.command.name=View",
    		"mvc.command.name=/"
    	},
    	service = MVCRenderCommand.class
    )
    public class MyStateSoyPortletViewMVCRenderCommand
    	implements MVCRenderCommand {

    	@Override
    	public String render(
    		RenderRequest renderRequest, RenderResponse renderResponse) {

    		Template template = (Template)renderRequest.getAttribute(
    			WebKeys.TEMPLATE);

    		String header = "Soy";

    		template.put("header", header);

    		return "View";
    	}

    }

`View.soy`:

    {namespace View}

    /**
     * Prints the portlet main view.
     *
     * @param id: string
     * @param header: string
     */
    {template .render}

    	<div id="{$id}">

    		<h1>Hello {$header}</h1>

    		<p>You can update the header with the portlet's header State properties.</p>
    	</div>
    {/template}

## Configuring the State properties [](id=configuring-the-state-properties)

Soy Portlets are registered automatically using the `Liferay.component` API, 
so you can use this API to retrieve your portlet and update its state 
properties. You can test this in your browser's developer console.

Follow these steps:

1.  Open the console in your web browser.

2.  Retrieve your portlet's component by passing the Soy portlet's ID in the 
    method `Liferay.component()`. Here's an example configuration:

        Liferay.component('_MyStateSoyPortlet_');
 
    This returns the Soy portlet's component Object containing the state 
    properties along with properties inherited from the prototype. 
    Alternatively, you can access the `STATE` object directly by calling the 
    `getState()` method:

        Liferay.component("_MyStateSoyPortlet_").getState();
 
    +$$$

    **Note:** The `Liferay.component()` method only returns the `STATE` object 
    information for components currently on the page. These are the state 
    properties defined for the current view.

    $$$

3.  Now that you retrieved your Soy portlet's component, you can access its 
    state properties the same way you would access any object's properties: the 
    dot notation or the bracket notation. The code below retrieves the example 
    portlet's `header` state property:
    
        Liferay.component("_MyStateSoyPortlet_").header;
 
    or
    
        Liferay.component("_MyStateSoyPortlet_")["header"]

4.  Update the state property's value:

        Liferay.component("portletID").stateProperty = "new value";
 
    or
        
        Liferay.component("portletID")["stateProperty"] = "new value";
 
    or you can pass a configuration object with the `setState()` method:

        Liferay.component("portletID").setState({stateProperty: new value});

    For example, you can change the example portlet's header to read 
    *Hello Hamburger* instead, if you don't like soy:
    
        Liferay.component('_MyStateSoyPortlet_').setState({header: 'Hamburger'});

![Figure 2: You can change the example portlet's header state property on the client side.](../../../../images/soy-example-portlet-fin.png)

Now you know how to configure Soy portlet state properties on the client side!

## Related Topics [](id=related-topics)

[Understanding the State Object's Architecture](/develop/tutorials/-/knowledge_base/7-1/understanding-the-state-object-architecture)

[Configuring Portlet Template Parameter State Properties](/develop/tutorials/-/knowledge_base/7-1/configuring-portlet-template-parameter-state-properties)
