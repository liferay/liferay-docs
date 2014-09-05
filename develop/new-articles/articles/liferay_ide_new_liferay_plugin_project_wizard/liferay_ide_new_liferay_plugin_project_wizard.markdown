# Liferay IDE New Liferay Plugin Project Wizard ##

## Introduction

The latest released Liferay IDE version is 2.1.1. Compare with previous one, the
"Liferay Plugin Project Wizard" part of the new version has changed a lot. Many
new functions have been added, such as Maven project build or the choose of
project's location. This Wiki page will show you all the changes of Liferay IDE
New Liferay Plugin Project Wizard.

## Requirements

- Eclipse Kapler or Luna
- Liferay IDE 2.1.1
[Download IDE 2.1.1 GA2](https://www.liferay.com/downloads/liferay-projects/liferay-ide)

## Liferay Plugin Project page

- Launch Eclipse with Liferay IDE 2.1.1 installed.
- Setup Liferay SDK and Server.
- Go to File> New> Liferay Plugin Project

### Use default location 

New IDE version has added a function to set location of your project. The
default "Use default location" is selected and the default folder is plugin
type's folder in the SDK path. If you unselect it, then you can browse a folder
you want as the project's location.

![use_default_location](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard0.jpg)

### Build type 

"Build type" part allows you to build either Ant or Maven project. It makes it
easier to build Maven project in this way.

![build_type](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard1.jpg)

### Plugin type 

The previous Plugin type are listed to be choose. Now Plugin type has become a drop-down list. In additon, new version can
create Service Builder Portlet project and Web project.

![plugin_type](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard2.jpg)

### Include sample code 

When you create Portlet project or Service builder project, you will see
"Include sample code" under "Plugin type". If you select it and finish the
setup, in your project liferay-display.xml, liferay-portlet.xml, portlet.xml and
service.xml, the sample code are imported to these four files. 

![include_sample_code](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard3.jpg)

Select "Include sample code", then click Next. You can see "Additional portlet
options" part. There you can set Portlet name and Display name.

![include_sample_code](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard4.jpg)

### Launch New Portlet Wizard after project is created 

When you create a Portlet project, you can also see "Launch New Portlet Wizard
after project is created" under Plugin type. If you select it and after finish
your setup, you will see a popup window for creating custom portlet class shows
up. Then you can set your portlet class there.

This function is as same as the "Create custom portlet class" in Liferay Portlet
Plugin Project page of the previouse IDE version.

![launch_new_portlet_wizard](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard5.jpg)

## Support Maven project build

Choose Maven(liferay-maven-plugin) as the Build type. There you can set the Artifact version, Group id and Active
profiles.

![support_maven](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard6.jpg)

### Active Profiles

For the "Active profiles" part, click the browse button. A page will appear
allowing you to choose the profile Id. You can add it or not.

![active_profiles](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard7.jpg)

Click "Create New Maven profile", you can set the Maven profile based on Liferay
runtime. After you finish your Maven setup, you can find the profiles which you
just created in the project pom.xml file.

![active_profiles](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard8.jpg)

### Liferay Portlet Plugin Project page

After setting the project name, click "Next", select "JSF 2.x" as portlet
framework. Then click "Next". You will see JSF Components suites listed here.

![portlet_plugin](/github/liferay-docs/tree/master/develop/new-articles/liferay_ide_new_liferay_plugin_project_wizard/img/wizard9.jpg)

