# Anatomy of a Layout Template Project [](id=anatomy-of-a-layout-template-project)

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

## Layout template files [](id=layout-template-files)

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

## Liferay configuration files [](id=liferay-configuration-files)

In addition to the three template-specific files, a layout template project has
two Liferay configuration files:

- `liferay-layout-templates.properties`: Specifies the name of the layout
  templates and the location of their TPL and PNG files. 
- `liferay-plugin-package.properties`: Describes the plugin project to
  Liferay's hot deployer. 

Now that you're familiar with the layout template's files and directory
structure, let's deploy a layout template on the server. 

## Deploying Layout Templates [](id=deploying-layout-templates)

If you've ever deployed a theme or portlet, you already know how to deploy
layout templates! Use Developer Studio or the terminal to deploy your layout
templates:

- ***Deploying in Developer Studio:*** Drag your layout template project onto
  your server. 
- ***Deploying in the terminal:*** If you're using Ant, execute the following
command from your layout template project directory:

        ant deploy

    If you're using Maven, please refer to this guide's
    [section](/develop/tutorials/-/knowledge_base/6-1/deploying-liferay-plugins-with-maven)
    on deploying Liferay plugins with Maven.

When deploying your plugin, the server displays messages indicating that your
plugin was read, registered, and is now available for use. 

Example server output:

	Reading plugin package for columns-1-4-1-layouttpl
	Registering layout templates for columns-1-4-1-layouttpl
	1 layout template for columns-1-4-1-layouttpl is available for use

Wait a minute! We can deploy the template, but we still haven't designed it.
We'll need to add content to the TPL files that were generated when we created
our layout template. 
