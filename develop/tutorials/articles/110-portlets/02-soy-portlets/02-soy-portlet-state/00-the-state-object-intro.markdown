# The State Object [](id=the-state-object)

MetalJS's `component` class, which your 
[view component](/develop/tutorials/-/knowledge_base/7-1/creating-a-soy-portlet#configuring-the-view-layer) 
extends, extends MetalJS's `state` class. The `state` class provides a `STATE` 
object that contains state properties and watches these properties for 
changes. Any 
[template parameters](/develop/tutorials/-/knowledge_base/7-1/creating-a-soy-portlet#using-portlet-template-parameters-in-the-soy-template) 
defined in your portlet classes are added automatically as properties to the 
portlet's `STATE` object. The `component` class provides additional rendering 
logic, such as automatically re-rendering the component when the `state` class 
detects a change in a state property. This means that you can 
[change a state property on the client side](/develop/tutorials/-/knowledge_base/7-1/configuring-soy-portlet-template-parameters-on-the-client-side) 
and automatically see that change reflected in the component's UI!

This section of tutorials covers how to configure and use your Soy portlet's 
`STATE` object.
