
# Creating Liferay Layout Templates [](id=lp-6-1-dgen05-creating-liferay-layout-templates-0)

Surely, you've added many a portlet to your pages by dragging them from the *Add* menu and dropping them into place with great satisfaction. But are there times when you find yourself limited by Liferay's layouts? Maybe your Feng Shui (pronounced *fung SHWAY*) senses are picking up on some negative energy? Or perhaps you find yourself adding the same portlets over and over again onto the same types of pages? Yikes! You can easily overcome these nuisances by creating your own custom layout templates. With layout template plugins you can design layouts that flow nicely, embed commonly used portlets and apply CSS, Velocity, and HTML to make your pages pop.

In this chapter, you'll learn about:

-	Creating custom layout templates using the Plugins SDK
-	Designing templates with Liferay Developer Studio  
- 	Embedding portlets in layout templates

Let's get our "spatial relation" juices flowing as we create a custom layout template!

## Creating a Layout Template [](id=lp-6-1-dgen05-creating-a-layout-template-0)

Our Plugin SDK supports layout templates as plugins. And creating layout templates with Liferay Developer Studio is easier than ever.

***Using Developer Studio:*** go to *File* &rarr; *New* &rarr; *Liferay Project* and do the following:

1. Fill in the *Project* and *Display* names
3. Select the appropriate *Plugins SDK* and *Portal Runtime*
4. Select *Layout* as your plugin type
5. Click *Finish*

![Figure 5.1: Creating a custom layout template project in Studio](../../images/layout-template-new-project.png)

Alternatively, in your terminal, you can create a layout template from your Plugins SDK's `layouttpl` folder, by executing the *create* script.

	./create.[sh|bat] <project name> "<layout template title>"

Example on Linux and Mac OS X:

    ./create.sh 1-4-1-columns "1-4-1 Columns"

Example on Windows:

    create.bat 1-4-1-columns "1-4-1 Columns"

Both Studio's *New Project* wizard and the *create* scripts generate layout template projects in your Plugin SDK's `layouttpl` folder. Great! Now, let's look at our newly generated layout template project.

## Anatomy of a Layout Template Project [](id=lp-6-1-dgen05-anatomy-of-a-layout-template-0)

In this section we'll go over the directory structure of the layout template project and learn about its various files.

Directory structure:

-	*layout-template-name*-layouttpl/
	-	docroot/
		-	WEB-INF/
			-	liferay-layout-templates.properties
			-	liferay-plugin-package.properties
		-	*layout_template_name*.png
		-	*layout_template_name*.tpl
		-	*layout_template_name*.wap.tpl
	-	build.xml

The SDK automatically appends "-layouttpl" to the project name. You can create multiple layout templates in a layout template project. Each layout template should have its own thumbnail image (PNG), regular template (TPL) and mobile device template (WAP TPL). The "liferay-" files help to describe the layout templates for packaging and deployment.

Here is a snapshot of files for a layout template project named "Columns 1 4 1" as shown in Developer Studio's *Package Explorer*.

![Figure 5.2: Example layout template named "Columns 1 4 1" editor](../../images/layout-template-dirs.png)

We'll discuss the project files next.

### Layout template files [](id=lp-6-1-dgen05-layout-template-files-0)

One or more layout template plugins can reside in a layout template project. Each layout template plugin has the following files:

-	***layout_template_name*.tpl**: Velocity template that generates the HTML structure of the template

-	***layout_template_name*.wap.tpl**: Variant template for mobile devices. WAP stands for wireless application protocol.

-	***layout_template_name*.png**: Representation of the template. It is the thumbnail that you see in Liferay Portal when browsing from the Page Layout screen from the Dockbar. You are responsible for creating the actual thumbnail, but can use this default PNG as a starting point.

![Figure 5.3: Default layout template thumbnail](../../images/blank_columns.png)

### Liferay configuration files [](id=lp-6-1-dgen05-liferay-configuration-files-0)

A layout template project has the following Liferay configuration files:

-	**liferay-layout-templates.properties**: Specifies the name of the layout templates and the location of their TPL and PNG files

-	**liferay-plugin-package.properties**: Describes the plugin project to Liferay's hot deployer

Now that you are familiar with the layout template's files and directory structure, let's deploy a layout template on the server.

### Deploying Layout Templates [](id=lp-6-1-dgen05-deploying-a-layout-template-0)

Deploy your layout templates from either of the following environments:

- ***Using Developer Studio -*** Drag your layout template project onto your server

- ***Using the terminal -*** Execute the following command from your your layout template project directory:

		ant deploy

Upon deploying your template, your server will output messages indicating that your template was read, registered and is now available for use.

Example server output:

	Reading plugin package for columns-1-4-1-layouttpl
	Registering layout templates for columns-1-4-1-layouttpl
	1 layout template for columns-1-4-1-layouttpl is available for use, let's start *designing* our layout template.

Now that you know how to deploy your template, let's design your layout.

## Designing a layout template [](id=lp-6-1-dgen05-designing-a-template-0)

The layout template's generated TPL files are initially empty, leaving you with a fresh canvas to design your layout templates as you like. This can seem overwhelming; but don't worry. Devleoper Studio's *Layout Template Editor* with handy palette makes designing templates fun!

Open your layout template's TPL file in Studio's *Layout Template Editor* to see it displayed in *Visual (Experimental)* mode.

![Figure 5.4: TPL file in *Layout Template Editor* (palette is hidden). To highlight the "view palette" button, we've added a red rectangle around it in the upper left corner of the editor.](../../images/layout-template-tpl-blank.png)

Then click the triangle in the upper left corner of the editor to bring the Palette into view.

![Figure 5.5: TPL file in *Layout Template Editor* with palette in view](../../images/layout-template-tpl-blank-with-palette.png)

From the palette you can drag rows, columns, and even existing templates onto your canvas.

Start designing your new layout template by dragging an entire *existing* layout template into the editor. Then modify it by adding, deleting, and adjusting elements as you like.

![Figure 5.6: Example - Starting with existing template *2 Column (50/50)*](../../images/layout-template-tpl-start.png)

After adding rows and columns, drag their edges vertically and horizontally to achieve the desired dimensions. Remember, your layout is not etched in stone. In *Visual (Experimental)* mode, you can start over as many times as you like as you're working out your layout designs. 

![Figure 5.7: Visually adjusted template](../../images/layout-template-tpl-visual-almost.png)

To fine tune the dimensions in your design, switch to *Source* view and edit your TPL files as necessary.

![Figure 5.8: Source view of the template](../../images/layout-template-tpl-src-almost.png)

For example, if you wanted each of the middle-row columns in the above template to be 25%, you'd simply adjust that row's first column (i.e. `column-2`) from 20% to 25% as follows ...

Change ...

	<div class="aui-w20 portlet-column portlet-column-first" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
	</div>

... to ...

	<div class="aui-w25 portlet-column portlet-column-first" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
	</div>

Notice we changed the column's class `aui-w20` value to `aui-w25`.

Ta-da! The rows and columns of the "Columns 1 4 1" layout template are arranged and sized evenly, as I envisioned them!

![Figure 5.9: Layout design of template  "Column 1 4 1" completed!](../../images/layout-template-tpl-visual-complete.png)

Now that we have some "positive energy" flowing in the design of our layout, let's spruce things up a bit by embedding some portlets in the template.

## Embedding portlets in a layout template [](id=lp-6-1-dgen05-embedding-portlets-in-a-layout-template-0)

You can embed portlets in layout templates to assure specific portlets always display in consistent locations on pages. Users may minimize these embedded portlets but are not permitted to move or remove the portlets from the page. Core portlets and custom portlets you create with the Plugins SDK can be embedded in the templates. Both instanceable and non-instanceable portlets can be embedded.

Let's embed the *navigation portlet* and *search portlet* in the first and last columns of our layout template's middle row. And while we're at it, we'll embed a couple of custom portlets in the upper and lower rows of the template.

**Embedded portlet attributes:**

-	**Portlet ID** - Set this to the value of the portlet's `<portlet-name>` found in `docroot/WEB-INF/portlet.xml`. For core portlets, this is `liferay-portal/portal-web/docroot/WEB-INF/liferay-portlet.xml`.
-	**Core vs. Custom** - Whether the portlet is a core portlet or custom
-	**Instanceable** - Whether multiple instances of the portlet can exist in the portal
-	**Web Application Context** - The portlet's context (required for *custom* portlets only). Go to the portlet's *Look and Feel* &rarr; *Advanced Styling* to find the context in the Fully Qualified Portlet ID (FQPI). The context is the portion of the Portlet ID string that follows  `WAR_`. The *Web Application Context* in the following figure is *myhelloworldportlet*.

![Figure 5.10: Fully Qualified Portlet ID (FQPI) for a custom portlet](../../images/layout-template-custom-portlet-look-n-feel.png)

The following table describes the portlets we'll embed in the layout:

| Portlet |  &nbsp;ID | &nbsp;Row | &nbsp;Column  | &nbsp;Type | &nbsp;Instanceable | &nbsp;Context |
--------- | ----------| --------- | ------------- | ---------- | ------------ | ------------- |
 Navigation  | &nbsp;`71` | &nbsp;2 | &nbsp;1 | &nbsp;core | &nbsp;yes | &nbsp;N/A |
 Search | &nbsp;`3` | &nbsp;2 | &nbsp;4 | &nbsp;core | &nbsp;no | &nbsp;N/A |
 Hello World | &nbsp;`my-hello-world-portlet` | &nbsp;1 | &nbsp;1 | &nbsp;custom | &nbsp;no | &nbsp;myhelloworldportlet |
 Goodbye World | &nbsp;`my-goodbye-world-portlet` | &nbsp;3 | &nbsp;1 | &nbsp;custom | &nbsp;yes | &nbsp;myhelloworldportlet |
---

Here is the TPL that implements embedding these portlets:

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

The following figure shows a page with the portlets embedded in the "Columns 1 4 1" layout template.

![Figure 5.11: Page with portlets embedded in layout template](../../images/layout-template-embed-portlets-visual.png)

See how simple it is to embed portlets in your pages?

## Available variables [](id=lp-6-1-dgen05-available-variables-0)

For your convenience, the variables listed in the following table are available to use in the TPL files.

| Variable | &nbsp;Type | &nbsp;Description |
| -------- | ---------- | ----------------- |
 $processor | &nbsp;com.liferay.portlet.layoutconfiguration.util.velocity.TemplateProcessor | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/portlet/layoutconfiguration/util/velocity/TemplateProcessor.html) |
 $request | &nbsp;javax.servlet.http.HttpServletRequest| |
 $themeDisplay | &nbsp;com.liferay.portal.theme.ThemeDisplay | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/theme/ThemeDisplay.html) |
 $company | &nbsp;com.liferay.portal.model.Company | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/Company.html) |
 $user | &nbsp;com.liferay.portal.model.User | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/User.html) |
 $realUser| &nbsp;com.liferay.portal.model.User | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/User.html) |
 $layout| &nbsp;com.liferay.portal.model.Layout | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/Layout.html) |
 $layouts| &nbsp;java.util.List<com.liferay.portal.model.Layout> ||
 $plid| &nbsp;java.lang.Long ||
 $layoutTypePortlet| &nbsp;com.liferay.portal.model.LayoutTypePortlet | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/LayoutTypePortlet.html) |
 $portletGroupId| &nbsp;java.lang.Long ||
 $locale| &nbsp;java.util.Locale ||
 $timeZone| &nbsp;java.util.TimeZone ||
 $theme| &nbsp;com.liferay.taglib.util.VelocityTaglib | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/taglib/util/VelocityTaglib.html) |
 $colorScheme| &nbsp;com.liferay.portal.model.ColorScheme | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/model/ColorScheme.html) |
 $portletDisplay| &nbsp;com.liferay.portal.theme.PortletDisplay | &nbsp;[Javadoc](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/theme/PortletDisplay.html) |
---

Excellent! Your layout template toolbox is complete.

## Conclusion [](id=lp-6-1-dgen05-conclusion-0)

In this chapter you've learned how to create layout templates, arrange their rows and columns, and embed portlets. Congratulations on mastering the fundamentals of Liferay's layout templates! Hmm, are you a master of Feng Shui, too? Hey, if you get real good at arranging portlets on pages, your friends may ask you to re-arrange their living room furniture!

Next, we'll take a look at how to customize core Liferay portlets using hooks -- sounds "catchy" right?!

