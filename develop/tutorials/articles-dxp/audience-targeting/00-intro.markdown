# Audience Targeting [](id=audience-targeting)

Liferay's Audience Targeting application allows you to monitor your portal's
users. You can organize them into user segments, target specific content to
those user segments, and create campaigns to expose user segments to a certain
set of assets. Visit the
[Targeting Content to your Audience](/discover/portal/-/knowledge_base/7-0/targeting-content-to-your-audience)
section for more information on Audience Targeting and how to use it.

Although the Audience Targeting app can be configured to monitor your portal's
audience out of the box, it is also designed as a framework to be extended by
developers.

There are a set of extension points that can be easily hooked by creating other
hot-deployable plugins. 

These extension points include

* Rule Types
<!-- * Rules Engine -->
* Report Types
* Report Metrics

Since Liferay 7.0, the Audience Targeting SDK to create extension points is
based on Blade CLI. Blade CLI is the easiest way for Liferay developers to
create new Liferay modules. Visit the
[Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli)
section for more information on Blade CLI and how to use it.

In this section's tutorials, you'll learn how to create these extension points
for your Audience Targeting application.
