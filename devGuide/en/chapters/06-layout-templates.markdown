# Creating Liferay Layout Templates [](id=lp-6-1-dgen05-creating-liferay-layout-templates-0)

By now, you've likely added portlets to a page by dragging them from the *Add*
menu and dropping them into place. Are there times, though, when you find
yourself limited by Liferay's page layout options? Maybe your Feng Shui
(pronounced *fung SHWAY*) senses are picking up on some negative energy? Or
perhaps you find yourself adding the same portlets over and over again onto the
same types of pages? Don't despair! Break the monotony by creating your own
custom layout templates. Layout template plugins let you design layouts that
flow nicely, embed commonly used portlets, and apply CSS, Velocity, and HTML to
make your pages visually pop. 

In this chapter, you'll learn everything you need to know about layout
templates:

- Creating a layout template project 
- Anatomy of a layout template project 
- Designing a layout template 
- Embedding portlets in a layout template 
- Available variables 

Let's create a custom layout template!

## Creating a Layout Template [](id=lp-6-1-dgen05-creating-a-layout-template-0)

With the Plugins SDK you can deploy layout templates as plugins, and creating
layout templates with Liferay Developer Studio is easier than ever. Let's call
our layout template called *Columns 1 4 1*. 

***Using Developer Studio:*** 

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Enter *columns-1-4-1* for the Project name and *Columns 1 4 1* for the
    Display name.
    
3.  Choose whichever build type you prefer (Ant or Maven) and select the
    appropriate *Plugins SDK* and *Liferay runtime*. 

4.  Select *Layout Template* as your plugin type. 

5.  Click *Finish*. 

![Figure 6.1: Creating a custom layout template project in Studio](../../images/layout-template-new-project.png)

***Using the terminal:*** Navigate to your Plugins SDK's `layouttpl` folder,
and execute the *create* script in your terminal. Here's the generic version of
the *create* script, followed by operating system-specific commands:

    ./create.[sh|bat] <project-name> "<layout template title>"

1.  Example in Linux and Mac OS X:

        ./create.sh columns-1-4-1 "Columns 1 4 1"

2.  Example in Windows:

        create.bat columns-1-4-1 "Columns 1 4 1"

Developer Studio's *New Project* wizard and the *create* scripts generate layout
template projects in your Plugin SDK's `layouttpl` folder. Layout template
project names must end with *-layouttpl* so when you enter *columns-1-4-1* for
the project name, *-layouttpl* is automatically appended to the project name. 

## Anatomy of a Layout Template Project [](id=lp-6-1-dgen05-anatomy-of-a-layout-template-0)

Let's look at the directory structure of a layout template project and learn
about its various files: 

- `columns-1-4-1-layouttpl/`
	- `docroot/`
		- `WEB-INF/`
			- `liferay-layout-templates.properties`
			- `liferay-plugin-package.properties`
		- `columns_1_4_1.png`
		- `columns_1_4_1.tpl`
		- `columns_1_4_1.wap.tpl`
	- `build.xml`

Navigate to your Plugins SDK's `layouttpl/` folder and you'll see that the
Plugins SDK automatically appended *-layouttpl* to your project's name. A layout
template project can contain multiple layout templates. The directory structure
is the same, but you'll have a `.png`, `.tpl`, and `.wap.tpl` file for each
layout template in the `docroot/` folder. The `liferay-*` files describe the
layout templates for packaging and deployment. 

Now that you're well-versed on the anatomy of a layout template, let's explore
the layout template files.

### Layout template files [](id=lp-6-1-dgen05-layout-template-files-0)

One or more layout template plugins can reside in a layout template project.
Let's see what each template file does: 

- `[project-name].tpl`: Generates the HTML structure of the template. 
- `[project-name].wap.tpl`: Variant template for mobile devices. WAP stands for
  wireless application protocol. 
- `[project-name].png`: Thumbnail representation of the template that you see in
  Liferay Portal from the Page Layout screen. You'll have to create the
  thumbnail image, but you can use the default PNG for layout templates as a
  starting point.

![Figure 6.2: Default layout template thumbnail](../../images/blank_columns.png)

Let's move on to Liferay configuration files.

### Liferay configuration files  [](id=lp-6-1-dgen05-liferay-configuration-files-0)

In addition to the three template-specific files, a layout template project has
two Liferay configuration files:

- `liferay-layout-templates.properties`: Specifies the name of the layout
  templates and the location of their TPL and PNG files. 
- `liferay-plugin-package.properties`: Describes the plugin project to
  Liferay's hot deployer. 

Now that you're familiar with the layout template's files and directory
structure, let's deploy a layout template on the server. 

### Deploying Layout Templates [](id=lp-6-1-dgen05-deploying-a-layout-template-0)

If you've ever deployed a theme or portlet, you already know how to deploy
layout templates! Use Developer Studio or the terminal to deploy your layout
templates:

- ***Deploying in Developer Studio:*** Drag your layout template project onto
  your server. 
- ***Deploying in the terminal:*** If you're using Ant, execute the following
command from your layout template project directory:

        ant deploy

    If you're using Maven, please refer to this guide's
    [section](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/deploying-liferay-plugins-with-maven)
    on deploying  Liferay plugins with Maven.

When deploying your plugin, the server displays messages indicating that your
plugin was read, registered, and is now available for use. 

Example server output:

	Reading plugin package for columns-1-4-1-layouttpl
	Registering layout templates for columns-1-4-1-layouttpl
	1 layout template for columns-1-4-1-layouttpl is available for use

Wait a minute! We can deploy the template, but we still haven't designed it.
We'll need to add content to the TPL files that were generated when we created
our layout template. 

## Designing a layout template [](id=lp-6-1-dgen05-designing-a-template-0)

Initially, the layout template's generated TPL files are empty, a fresh canvas
on which you design layout templates. If this seems overwhelming, don't worry.
With the handy palette in Developer Studio's *Layout Template Editor*,
designing templates becomes fun! Let's try it:

1.  Open your layout template's TPL file in Developer Studio's *Layout Template
    Editor* to see it displayed in *Visual (Experimental)* mode. 

2.  Click the triangle in the upper left corner of the editor to view the
    Palette. Drag rows, columns, and even existing templates onto your
    canvas. 

3.  Start designing your new layout template by dragging an *existing* layout
    template into the editor. Then modify it by adding, deleting, and adjusting
    elements as you like. 

4.  After adding rows and columns, drag their edges to achieve the desired
    dimensions. Your layout isn't etched in stone. You can make changes to your
    layout template whenever you need by using *Visual (Experimental)* mode in
    Developer Studio. 

    ![Figure 6.3: Visually adjusted template](../../images/layout-template-tpl-visual-almost.png)

5.  To fine tune your design's dimensions, switch to *Source* view and edit
    your TPL files as necessary. 

![Figure 6.4: Source view of the template](../../images/layout-template-tpl-src-almost.png)

For example, if you want each second-row column of the above template to be 25%
of the page width, adjust that row's first column (`column-2`) from 20% to 25%. 

Here's how the original 20% column width definition looks:

	<div class="aui-w20 portlet-column portlet-column-first" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
	</div>

Here's the column width definition modified to 25%:

	<div class="aui-w25 portlet-column portlet-column-first" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
	</div>

It's hard to see, but we changed the column's class `aui-w20` value to
`aui-w25`. 

Just like that, the rows and columns of the *Columns 1 4 1* layout template are
arranged and sized evenly. 

Now that we've generated some positive Feng Shui through the design of our
layout, let's increase our control over the layout by embedding portlets. 

## Embedding portlets in a layout template [](id=lp-6-1-dgen05-embedding-portlets-in-a-layout-template-0)

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

## Available variables [](id=lp-6-1-dgen05-available-variables-0)

A number of variables are available for you to use in your custom TPL files. For
your convenience, we've listed all of them in the following table. 

| Variable | Type | Description |
| -------- | ---- | ----------- |
 $processor | com.liferay.portlet.layoutconfiguration.util.velocity.TemplateProcessor | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/portlet/layoutconfiguration/util/velocity/TemplateProcessor.html) |
 $request | javax.servlet.http.HttpServletRequest| |
 $themeDisplay | com.liferay.portal.theme.ThemeDisplay | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/theme/ThemeDisplay.html) |
 $company | com.liferay.portal.model.Company | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/Company.html) |
 $user | com.liferay.portal.model.User | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/User.html) |
 $realUser | com.liferay.portal.model.User | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/User.html) |
 $layout | com.liferay.portal.model.Layout | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/Layout.html) |
 $layouts | java.util.List<com.liferay.portal.model.Layout> ||
 $plid | java.lang.Long ||
 $layoutTypePortlet | com.liferay.portal.model.LayoutTypePortlet | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/LayoutTypePortlet.html) |
 $portletGroupId | java.lang.Long ||
 $locale | java.util.Locale ||
 $timeZone| java.util.TimeZone ||
 $theme | com.liferay.taglib.util.VelocityTaglib | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/taglib/util/VelocityTaglib.html) |
 $colorScheme | com.liferay.portal.model.ColorScheme | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/ColorScheme.html) |
 $portletDisplay | com.liferay.portal.theme.PortletDisplay | [Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/theme/PortletDisplay.html) |
---

Now your layout template toolbox is complete. 

## Summary  [](id=lp-6-1-dgen05-conclusion-0)

In this chapter we created layout templates, arranged their rows and columns,
and embedded portlets in them. Congratulations on mastering the fundamentals of
Liferay's layout templates, but be careful. If your Feng Shui skills become
widely known, your friends may ask you to re-arrange their living room
furniture! 

If you're up for it, let's learn how to customize core Liferay portlets using
hooks--sounds "catchy", right?! 
