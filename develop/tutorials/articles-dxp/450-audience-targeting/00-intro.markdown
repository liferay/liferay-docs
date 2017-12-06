# Audience Targeting [](id=audience-targeting)

Liferay's Audience Targeting application lets you monitor your users. You can
organize them into user segments, target specific content to those user
segments, and create campaigns to expose user segments to a certain set of
assets. Visit the
[Targeting Content to your Audience](/discover/portal/-/knowledge_base/7-0/targeting-content-to-your-audience)
section for more information on Audience Targeting and how to use it.

Although the Audience Targeting app can be configured to monitor your audience
out of the box, it is also designed as a framework to be extended by
developers.

There are a set of extensions that can be easily hooked by creating other
hot-deployable plugins. 

These extension points include

* Rule Types
<!-- * Rules Engine -->
* Report Types
* Report Metrics

Audience Targeting extensions are created using OSGi modules. There are
convenient Blade CLI templates for creating these projects, but you can create
the modules any way you want. To use the templates, see the
[Blade CLI tutorials](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

In this section's tutorials, you'll learn how to create these extension points
for your Audience Targeting application.
