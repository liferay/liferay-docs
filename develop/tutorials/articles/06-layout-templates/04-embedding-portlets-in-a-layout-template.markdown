# Embedding portlets in a layout template [](id=embedding-portlets-in-a-layout-template)

Are there portlets you need displayed in the same location on all pages using
a particular layout template? Perhaps you want to prevent others from disrupting
the Feng Shui you've generated with your design? You can embed portlets in
layout templates, ensuring that specified portlets always display in consistent
locations on your pages. Users can minimize embedded portlets but can't move or
remove them. Whether instanceable or non-instanceable, core portlets and custom
portlets you created with the Plugins SDK can be embedded in layout templates. 

Let's embed some portlets in our *Columns 1 4 1* layout template. We'll place
the *navigation portlet* and *search portlet* in the first and last columns of
our layout template's middle row. Additionally, we'll embed a custom portlet in
the template's upper and lower rows. 

First, specify some attributes of the embedded portlet:

- ***Portlet ID:*** The portlet's name, `<portlet-name>`, found in
  `docroot/WEB-INF/portlet.xml`. For core portlets, find the name in
  `liferay-portal/portal-web/docroot/WEB-INF/liferay-portlet.xml`. 
- ***Core vs. Custom:*** Specify whether the portlet is a core portlet or
  custom. 
- ***Instanceable:*** Specify whether multiple instances of the portlet can
  exist in the portal. 
- ***Web Application Context*** - Required for *custom* portlets only. Log into
  your Liferay Portal. Go to the portlet's *Look and Feel* &rarr; *Advanced
  Styling* to find the context in the Fully Qualified Portlet ID (FQPI). The
  context is the portion of the Portlet ID string that follows `WAR_`. The *Web
  Application Context* in the following figure is *myhelloworldportlet*. 

![Figure 6.5: Fully Qualified Portlet ID (FQPI) for a custom portlet](../../images/layout-template-custom-portlet-look-n-feel.png)

Here's a description of the portlets we're embedding in the layout:

| Portlet      | ID                         | Row | Column | Type   | Instanceable | Context |
-------------- | -------------------------- | --- | ------ | ------ | ------------ | ------- |
 Navigation    | `71`                       | 2   | 1      | core   | yes | N/A |
 Search        | `3`                        | 2   | 4      | core   | no  | N/A |
 Hello World   | `my-hello-world-portlet`   | 1   | 1      | custom | no  | myhelloworldportlet |
 Goodbye World | `my-goodbye-world-portlet` | 3   | 1      | custom | yes | myhelloworldportlet |
---

Here's the TPL that implements embedding of these portlets:

	<div class="columns_1_4_1" id="main-content" role="main">
		<div class="portlet-layout">
			<div class="portlet-column portlet-column-only" id="column-1">
				$processor.processPortlet("my-hello-world-portlet_WAR_myhelloworldportlet")
				$processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")
			</div>
		</div>
		<div class="portlet-layout">
			<div class="aui-w25 portlet-column portlet-column-first" id="column-2">
				$processor.processPortlet("71_INSTANCE_xyz1")
				$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
			</div>
			<div class="aui-w25 portlet-column" id="column-3">
				$processor.processColumn("column-3", "portlet-column-content")
			</div>
			<div class="aui-w25 portlet-column" id="column-4">
				$processor.processColumn("column-4", "portlet-column-content")
			</div>
			<div class="aui-w25 portlet-column portlet-column-last" id="column-5">
				$processor.processPortlet("3")
				$processor.processColumn("column-5", "portlet-column-content portlet-column-content-last")
			</div>
		</div>
		<div class="portlet-layout">
			<div class="portlet-column portlet-column-only" id="column-6">
				$processor.processPortlet("my-goodbye-world-portlet_WAR_myhelloworldportlet_INSTANCE_jkl1")
				$processor.processColumn("column-6", "portlet-column-content portlet-column-content-only")
			</div>
		</div>
	</div>

What would a page using our *Columns 1 4 1* layout template look like? Check out
the following figure for a screenshot of our layout template with its embedded
portals. 

![Figure 6.6: Page with portlets embedded in layout template](../../images/layout-template-embed-portlets-visual.png)

See how simple it is to embed portlets in your pages? 

Wouldn't it be nice to have an organized reference of available layout template
variables? You're in luck! We'll dive into available variables next!
