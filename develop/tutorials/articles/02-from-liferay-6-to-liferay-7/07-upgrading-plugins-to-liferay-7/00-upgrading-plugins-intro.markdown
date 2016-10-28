# Upgrading Plugins to @product-ver@ [](id=upgrading-plugins-to-liferay-7)

The plugin upgrade tutorials guide you through a process of making the minimal
changes necessary for existing plugins to work in @product-ver@. This group of
tutorials starts by covering the most common cases, and there are more tutorials 
to come that cover more specific cases, including those based on feedback. 
Upgrade tutorials for all plugin types are on their way, including guidance for 
adapting to Liferay framework changes and upgrading hook plugins that implement 
common customizations. 

The tutorials will cover upgrading all existing plugin types and templates:

- Portlet plugins
- Hook plugins
- Ext plugins
- Theme plugins
- Layout Templates
- FreeMarker and Velocity Templates

The first upgrade process step is to adapt your existing plugin's code to
@product-ver@'s APIs. The great news is that Liferay's Code Upgrade Tool makes this
easier than ever. It identifies Liferay API changes affecting your code, 
explains the API changes, and offers resolution steps. And the tool offers 
auto-correction where it can. 

<!-- TODO Give an overview of the rest of the upgrade process. Jim -->

You might be tempted to optimize your existing plugins right away to benefit 
from the new things @product@ offers, but you shouldn't. It's much better to
upgrade your plugins according to these tutorials. In this way, you'll get your 
plugins running in Liferay as fast as possible, and at the same time you'll have 
prepared the plugins for the optimizations you can implement later. 

You'll start with adapting existing plugin code to @product-ver@'s API.
