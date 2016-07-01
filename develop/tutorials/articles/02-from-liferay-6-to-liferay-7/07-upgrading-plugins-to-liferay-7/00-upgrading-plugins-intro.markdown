# Upgrading Plugins to Liferay 7 [](id=upgrading-plugins-to-liferay-7)

The plugin upgrade tutorials guide you through a process of making the minimal
changes necessary for existing plugins to work in Liferay 7. This group of
tutorials will start by covering the most common cases, and we'll continue
writing more tutorials regularly to cover more specific cases, including those
based on feedback you send us. We're providing upgrade tutorials for all plugin
types, including guidance for adapting to Liferay framework changes. And we'll
also demonstrate upgrading hook plugins that implement common customizations. 

The tutorials will cover upgrading all existing plugin types and templates:

- Portlet plugins
- Hook plugins
- Ext plugins
- Theme plugins
- Layout Templates
- FreeMarker and Velocity Templates

The first upgrade process step is to adapting your existing plugin's code to
Liferay 7's APIs. The great news is that Liferay's Code Upgrade Tool makes this
easier ever. It identifies Liferay API changes affecting your code, explains the
API changes, and offers resolution steps. And the tool offers auto-correction
where it can. 

<!-- TODO Give an overview of the rest of the upgrade process. Jim -->

We realize that you might be tempted to optimize your existing plugins right
away, to benefit from the new things Liferay 7 offers, but we strongly suggest
first upgrading your plugins per the tutorials here. In this way, you'll be able
to work with your plugins in Liferay as soon as possible and you'll best prepare
the plugins for implementing optimizations you can do later. 

Let's start with adapting existing plugin code to Liferay 7's API.
