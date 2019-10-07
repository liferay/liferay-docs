---
header-id: the-state-object
---

# The State Object

[TOC levels=1-4]

MetalJS's `component` class, which your 
[view component](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#configuring-the-view-layer) 
extends, extends MetalJS's `state` class. The `state` class provides a `STATE` 
object that contains state properties and watches these properties for 
changes. Any 
[template parameters](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#using-portlet-template-parameters-in-the-soy-template) 
defined in your portlet classes are added automatically as properties to the 
portlet's `STATE` object. The `component` class provides additional rendering 
logic, such as automatically re-rendering the component when the `state` class 
detects a change in a state property. This means that you can 
[change a state property on the client side](/docs/7-1/tutorials/-/knowledge_base/t/configuring-soy-portlet-template-parameters-on-the-client-side) 
and automatically see that change reflected in the component's UI!

This section of tutorials covers how to configure and use your Soy portlet's 
`STATE` object.
