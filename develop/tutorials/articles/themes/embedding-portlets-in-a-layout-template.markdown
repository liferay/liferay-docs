# Embedding Portlets in a Layout Template

Are there portlets you need displayed in the same location on your pages that
use the same layout template? Or perhaps you want to prevent users from moving
portlets you've carefully placed, knowing that it disrupts the Feng Shui of your
page layout's design? Well, there's a way you can maintain serene order in such
pages. You can embed portlets in their layout templates to ensure that specified
portlets always display in consistent locations on your pages. Users will be
able to minimize the embedded portlets but won't be able to move them or remove
them. Core portlets and custom portlets, whether instanceable or
non-instanceable, that are created with the Plugins SDK can be embedded in
layout templates. 

This tutorial example layout template demonstrates placing the *navigation
portlet* and *search portlet* in the first and last columns of the layout
template's middle row. Additionally, it embeds a custom portlet in the
template's upper and lower rows. 

Before you embed portlets in a template, you must find out certain portlet
characteristics that the layout template requires. You'll learn what they are,
next. 

## Specifying Attributes of the Embedded Portlet

First, you'll need to specify some attributes of the embedded portlet:

- ***Portlet ID:*** The portlet's name, `<portlet-name>`, found in
  `docroot/WEB-INF/portlet.xml`. For core portlets, find the name in
  `liferay-portal/portal-web/docroot/WEB-INF/liferay-portlet.xml`. 
- ***Core vs. Custom:*** Specify whether the portlet is a core portlet or
  a custom portlet. 
- ***Instanceable:*** Specify whether multiple instances of the portlet can
  exist in the portal. 
- ***Web Application Context:*** (required for *custom* portlets only) Log in to
  your Liferay Portal. Go to the portlet's *Look and Feel* &rarr; *Advanced
  Styling* to find the context in the Fully Qualified Portlet ID (FQPI). The
  context is the portion of the Portlet ID string that follows `WAR_`. The *Web
  Application Context* in the following figure is *myhelloworldportlet*. 

![Figure 1: You can view the Fully Qualified Portlet ID (FQPI) in the Advanced Styling tab for a custom portlet.](../../images/layout-template-custom-portlet-look-n-feel.png)

Here's a description of the portlets to be embedded in the example layout:

**Example Information for Fully Qualified Portlet IDs** 

| Portlet      | ID                         | Row | Column | Type   | Instanceable | Context |
-------------- | -------------------------- | --- | ------ | ------ | ------------ | ------- |
 Navigation    | `71`                       | 2   | 1      | core   | yes | N/A |
 Search        | `3`                        | 2   | 4      | core   | no  | N/A |
 Hello World   | `my-hello-world-portlet`   | 1   | 1      | custom | no  | myhelloworldportlet |
 Goodbye World | `my-goodbye-world-portlet` | 3   | 1      | custom | yes | myhelloworldportlet |
---

Now, that the embedded portlet's characteristics have been acquired, you can
plug them into a layout template to embed the portlets. 

## Adding Portlets to the Layout Template Columns

To embed a portlet, you must specify do the following things: 

1.  Add a `$processor.processPortlet(...)` directive within the column in which
to embed the portlet. 

2.  Pass in the portlet's Fully Qualified Portlet ID (FQPI) as the string
parameter to the `$processor.processPortlet(...)` directive. 

    **Fully Qualified Portlet ID (FQPI) Value Contentions**

    | Convention | Description |
    | ---------- | ----------- |
    | [portletID] |  Non-instanceable core portlet |
    | [portletID]\_INSTANCE\_[instanceID] |  Instanceable core portlet |
    | [portletID]\_WAR\_[portletContext] |  Non-instanceable custom portlet |
    | [portletID]\_WAR\_[portletContext]\_INSTANCE\_[instanceID] |  Instanceable custom portlet |

Here's the TPL that implements embedding the example portlets in an example
layout: 

    <div class="columns_1_4_1" id="main-content" role="main">
        <div class="portlet-layout row-fluid">
            <div class="portlet-column portlet-column-only" id="column-1">
                $processor.processPortlet(
                    "my-hello-world-portlet_WAR_myhelloworldportlet")
                $processor.processColumn("column-1",
                    "portlet-column-content portlet-column-content-only")
            </div>
        </div>
        <div class="portlet-layout row-fluid">
            <div class="portlet-column portlet-column-first span3" id="column-2">
                $processor.processPortlet("71_INSTANCE_xyz1")
                $processor.processColumn(
                    "column-2",
                    "portlet-column-content portlet-column-content-first")
            </div>
            <div class="portlet-column span3" id="column-3">
                $processor.processColumn("column-3", "portlet-column-content")
            </div>
            <div class="portlet-column span3" id="column-4">
                $processor.processColumn("column-4", "portlet-column-content")
            </div>
            <div class="portlet-column portlet-column-last span3" id="column-5">
                $processor.processPortlet("3")
                $processor.processColumn(
                    "column-5",
                    "portlet-column-content portlet-column-content-last")
            </div>
        </div>
        <div class="portlet-layout row-fluid">
            <div class="portlet-column portlet-column-only" id="column-6">
                $processor.processPortlet(
                "my-goodbye-world-portlet_WAR_myhelloworldportlet_INSTANCE_jkl1")
                $processor.processColumn(
                    "column-6",
                    "portlet-column-content portlet-column-content-only")
            </div>
        </div>
    </div>

What would a page using the example *Columns 1 4 1* layout template look like?
Check out the following figure for a screenshot of the layout template with its
embedded portlets. 

![Figure 2: Your embedded portlets are now available when viewing your customized layout template.](../../images/layout-template-embed-portlets-visual.png)

Congrats, you now know how to embed portlets in your templates, but be careful. 
If your Feng Shui skills become widely known,your friends may ask you to 
re-arrange their living room furniture!

## Related Topics

[Application Display Templates](/develop/tutorials/-/knowledge_base/application-display-templates-lp-6-2-develop-tutorial)

[Performing a Custom Action Using a Hook](/develop/tutorials/-/knowledge_base/performing-a-custom-action-using-a-hook-lp-6-2-develop-tutorial)


