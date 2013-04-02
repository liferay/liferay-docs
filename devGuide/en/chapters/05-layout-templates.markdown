# Creating Liferay Layout Templates

By now, you've likely had the satisfaction of adding portlets to your pages by
dragging them from the *Add* menu and dropping them into place on the page. Are
there times, though, when you find yourself limited by Liferay's page layout
options? Maybe your Feng Shui (pronounced *fung SHWAY*) senses are picking up on
some negative energy? Or perhaps you find yourself adding the same portlets over
and over again onto the same types of pages? Don't despair! Overcome the
monotony by creating your own custom layout templates. Layout template plugins
let you design layouts that flow nicely, embed commonly used portlets, and apply
CSS, Velocity, and HTML to make your pages visually pop. 

In this chapter, you'll learn about:

- **Creating a layout template:** Creating custom layout templates using the
Plugins SDK. 

- **Designing a layout template:** Designing templates with Liferay Developer
Studio. 

- **Anatomy of a layout template project:** Directory structure and files of a
layout template project. 

- **Embedding portlets in a layout template:** Ensure that specified portlets
are consistently placed on a page in a desired location. 

- **Available variables:** Variables available for use in TPL files. 

Let's create a custom layout template!

## Creating a Layout Template

Our Plugins SDK supports layout templates as plugins, and creating layout
templates with Liferay Developer Studio is easier than ever.

***Using Developer Studio:*** 

1. Go to *File* &rarr; *New* &rarr; *Liferay Project*. 

2. Fill in the *Project* and *Display* names. 

3. Select the appropriate *Plugins SDK* and *Portal Runtime*. 

4. Select *Layout* as your plugin type. 

5. Click *Finish*. 

![Figure 5.1: Creating a custom layout template project in Studio](../../images/layout-template-new-project.png)

***Using the terminal:*** Alternatively, you can create a layout template from
your Plugins SDK's `layouttpl` folder, by executing the *create* script in your
terminal. Here's the generic version of the *create* script, followed by
operating system-specific commands:

    ./create.[sh|bat] <project-name> "<layout template title>"

1. Example in Linux and Mac OS X:

        ./create.sh 1-4-1-columns "1-4-1 Columns"

2. Example in Windows:

        create.bat 1-4-1-columns "1-4-1 Columns"

Developer Studio's *New Project* wizard and the *create* scripts in terminal
both generate layout template projects in your Plugin SDK's `layouttpl` folder.
Now let's look at the directory structure for our newly generated layout
template project. 

## Anatomy of a Layout Template Project

Let's look at the directory structure of one layout template project and learn
about its various files. 

Directory structure of a layout template project named "Columns 1 4 1":

- `columns-1-4-1-layouttpl/`

	- `docroot/`

		- `WEB-INF/`

			- `liferay-layout-templates.properties`

			- `liferay-plugin-package.properties`

		- `columns_1_4_1.png`

		- `columns_1_4_1.tpl`

		- `columns_1_4_1.wap.tpl`

	- `build.xml`

The SDK automatically appends "-layouttpl" to your project name. Although we've
only shown the `.png`, `.tpl`, and`.wap.tpl` files for one layout template in
our directory structure, a project can contain multiple layout templates. Each
should have its own thumbnail image (PNG), regular template (TPL) and mobile
device template (WAP TPL). The "liferay-" files help to describe the layout
templates for packaging and deployment. 

### Layout template files

As mentioned above, one or more layout template plugins can reside in a layout
template project. Let's see what each of the files actually does:

- ***layout_template_name*.tpl**: Velocity template that generates the HTML
structure of the template. 

- ***layout_template_name*.wap.tpl**: Variant template for mobile devices. WAP
stands for wireless application protocol. 

- ***layout_template_name*.png**: Representation of the template. It is the
thumbnail that you see in Liferay Portal when browsing from the Page Layout
screen from the Dockbar. You are responsible for creating the actual thumbnail,
but can use the default PNG for layout templates as a starting point. 

![Figure 5.2: Default layout template thumbnail](../../images/blank_columns.png)

### Liferay configuration files

On top of the template-specific files we just covered, a layout template project
has two Liferay configuration files:

- **liferay-layout-templates.properties**: Specifies the name of the layout
templates and the location of their TPL and PNG files. 

- **liferay-plugin-package.properties**: Describes the plugin project to
Liferay's hot deployer. 

Now you're familiar with the layout template's files and directory
structure. Let's deploy a layout template on the server. 

### Deploying Layout Templates

If you've ever deployed a theme or portlet that you developed for Liferay, you
already know how to deploy your layout templates! Use Developer Studio or the
terminal to deploy your layout templates:

- ***Deploying in Developer Studio -*** Drag your layout template project onto
your server. 

- ***Deploying in the terminal -*** Execute the following command from your your
layout template project directory:

		ant deploy

Upon deploying your template, your server will output messages indicating that
your template was read, registered and is now available for use. 

Example server output:

	Reading plugin package for columns-1-4-1-layouttpl
	Registering layout templates for columns-1-4-1-layouttpl
	1 layout template for columns-1-4-1-layouttpl is available for use

Wait a minute! We know how to deploy the template, but we still haven't designed
it. Let's design your layout next. 

## Designing a layout template

The layout template's generated TPL files are initially empty, a fresh canvas on
which you design your layout templates. This can seem overwhelming, but don't
worry. With the handy pallette in Devleoper Studio's *Layout Template Editor*,
designing templates becomes fun! Let's try it:

1. Open your layout template's TPL file in Developer Studio's *Layout Template
Editor* to see it displayed in *Visual (Experimental)* mode. 

2. Click the triangle in the upper left corner of the editor to bring the
Palette into view. From the palette you can drag rows, columns, and even
existing templates onto your canvas. 

3. Start designing your new layout template by dragging an entire *existing*
layout template into the editor. Then modify it by adding, deleting, and
adjusting elements as you like. 

4. After adding rows and columns, drag their edges vertically and horizontally
to achieve the desired dimensions. Remember, your layout is not etched in stone.
In *Visual (Experimental)* mode, you can start over as many times as you like as
you're working out your layout designs. 

![Figure 5.3: Visually adjusted template](../../images/layout-template-tpl-visual-almost.png)

5. To fine tune the dimensions in your design, switch to *Source* view and edit
your TPL files as necessary. 

![Figure 5.4: Source view of the template](../../images/layout-template-tpl-src-almost.png)

For example, if you wanted each of the middle-row columns in the above template
to be 25%, you'd simply adjust that row's first column (i.e. `column-2`) from
20% to 25% as follows ...

Change ...

	<div class="aui-w20 portlet-column portlet-column-first" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
	</div>

... to ...

	<div class="aui-w25 portlet-column portlet-column-first" id="column-2">
		$processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")
	</div>

It's hard to see, but we changed the column's class `aui-w20` value to
`aui-w25`. 

Just like that, the rows and columns of the "Columns 1 4 1" layout template are
arranged and sized evenly. 

Now that we've generated some positive Feng Shui through the design of our
layout, let's spruce things up a bit by embedding some portlets in the template. 

## Embedding portlets in a layout template

Are there some portlets that must be displayed in the same location on all the
pages that use one of your layout templates? Perhaps you want to prevent others
from disrupting the Feng Shui you've generated with your design? You can embed
portlets in layout templates, to ensure specific portlets always display in
consistent locations on pages. Users may minimize these embedded portlets but
are not permitted to move or remove the portlets from the page. You can embed
core portlets and custom portlets you create with the Plugins SDK in the
templates. You can embed instanceable and non-instanceable portlets. 

Let's embed the *navigation portlet* and *search portlet* in the first and last
columns of our layout template's middle row. While we're at it, let's also embed
a couple of custom portlets in the upper and lower rows of the template. 

Embedded portlet attributes:

- ***Portlet ID*** - Set this to the portlet's name, `<portlet-name>`, found in
`docroot/WEB-INF/portlet.xml`. For core portlets, this is
`liferay-portal/portal-web/docroot/WEB-INF/liferay-portlet.xml`. 

- ***Core vs. Custom*** - Specify whether the portlet is a core portlet or custom. 

- ***Instanceable*** - Specify whether multiple instances of the portlet can exist
in the portal. 

- ***Web Application Context*** - The portlet's context (required for *custom*
portlets only). Login to your Liferay Portal, Go to the portlet's *Look and
Feel* &rarr; *Advanced Styling* to find the context in the Fully Qualified
Portlet ID (FQPI). The context is the portion of the Portlet ID string that
follows `WAR_`. The *Web Application Context* in the following figure is
*myhelloworldportlet*. 

![Figure 5.5: Fully Qualified Portlet ID (FQPI) for a custom portlet](../../images/layout-template-custom-portlet-look-n-feel.png)

The following table describes the portlets we're embedding in the layout:

| Portlet |  &nbsp;ID | &nbsp;Row | &nbsp;Column  | &nbsp;Type | &nbsp;Instanceable | &nbsp;Context |
--------- | ----------| --------- | ------------- | ---------- | ------------ | ------------- |
 Navigation  | &nbsp;`71` | &nbsp;2 | &nbsp;1 | &nbsp;core | &nbsp;yes | &nbsp;N/A |
 Search | &nbsp;`3` | &nbsp;2 | &nbsp;4 | &nbsp;core | &nbsp;no | &nbsp;N/A |
 Hello World | &nbsp;`my-hello-world-portlet` | &nbsp;1 | &nbsp;1 | &nbsp;custom | &nbsp;no | &nbsp;myhelloworldportlet |
 Goodbye World | &nbsp;`my-goodbye-world-portlet` | &nbsp;3 | &nbsp;1 | &nbsp;custom | &nbsp;yes | &nbsp;myhelloworldportlet |
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

The following figure shows a page with the portlets embedded in the "Columns 1 4
1" layout template. 

![Figure 5.6: Page with portlets embedded in layout template](../../images/layout-template-embed-portlets-visual.png)

See how simple it is to embed portlets in your pages? 

## Available variables

For your convenience, the variables listed in the following table are available
to use in the TPL files. 

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

Now your layout template toolbox is complete. 

## Conclusion

In this chapter we created layout templates, arranged their rows and columns,
and embedded portlets. Congratulations on mastering the fundamentals of
Liferay's layout templates! But be careful, if your Feng Shui skills become
widely known, your friends may ask you to re-arrange their living room
furniture! 

If you're up for it, let's learn how to customize core Liferay portlets using
hooks -- sounds "catchy", right?! 
