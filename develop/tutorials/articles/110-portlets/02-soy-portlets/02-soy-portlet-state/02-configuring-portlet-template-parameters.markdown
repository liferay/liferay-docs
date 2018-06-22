# Configuring Portlet Template Parameter State Properties [](id=configuring-portlet-template-parameter-state-properties)

[Portlet template parameters](/develop/tutorials/-/knowledge_base/7-1/creating-a-soy-portlet#using-portlet-template-parameters-in-the-soy-template) 
are added automatically as state properties to the 
[view component](/develop/tutorials/-/knowledge_base/7-1/creating-a-soy-portlet#configuring-the-view-layer)'s 
[`STATE` object](/develop/tutorials/-/knowledge_base/7-1/understanding-the-state-object-architecture). 
Therefore, you can provide additional configuration options for them in the 
`STATE` object. The example below sets the default value for the portlet 
template parameter `color` in its 
[`*MVCRenderCommand` class](/develop/tutorials/-/knowledge_base/7-1/creating-a-soy-portlet#render-logic):

    Template template = (Template)renderRequest.getAttribute(
    			WebKeys.TEMPLATE);
          
    String color = "red";

    template.put("color", color);

The configuration above has the implicit state property configuration shown 
below in the view's component file (`View.es.js` for example):

       View.STATE {
         color: {
           value: 'red'
         }
       }

You can provide additional settings by 
[configuring the state Property](/develop/tutorials/-/knowledge_base/7-1/understanding-the-state-object-architecture) 
in the `View` component. The example below defines a `setter` function that 
transforms the color's string to upper case before adding it to the `STATE` 
object:

    function setColor(color) {
      return color.toUpperCase();
    }

    View.STATE = {
      color: {
        setter: 'setColor'      
      }
    }

Now you know how to configure portlet template parameter state properties!

## Related Topics [](id=related-topics)

[Understanding the State Object's Architecture](/develop/tutorials/-/knowledge_base/7-1/understanding-the-state-object-architecture)

[Configuring Soy Portlet Template Parameters on the Client Side](/develop/tutorials/-/knowledge_base/7-1/configuring-soy-portlet-template-parameters-on-the-client-side)
