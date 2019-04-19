---
header-id: theme-generator
---

# Theme Generator

[TOC levels=1-4]

The Liferay Theme Generator generates themes for @product@. It is just one of 
Liferay JS Theme Toolkit's 
[tools](https://github.com/liferay/liferay-themes-sdk/tree/master/packages). 

A couple versions of the Liferay Theme Generator are available. The version you 
must install depends on the version of @product@ you're developing on. The 
required versions are listed in the table below:

| Liferay Version | Liferay Theme Generator Version | Command |
| --- | --- | --- |
| 6.2 | 8.x.x | `npm install -g generator-liferay-theme@^8.x.x` |
| 7.0 | 8.x.x or 8.x.x | Same as above or below |
| 7.1 | 8.x.x | `npm install -g generator-liferay-theme@^8.x.x` |
| 7.2 | 9.x.x | `npm install -g generator-liferay-theme |

## Sub-generators

The Liferay Theme Generator includes the sub-generators listed in the table 
below:

| Sub-generator | Command to run | Description |
| --- | --- | --- |
| Layouts | `yo liferay-theme:layout` | Generate layout templates with an interactive VIM. |
| Themelets | `yo liferay-theme:themelet` | Create small, reusable, pieces of CSS and HTML for your themes. |

### Layouts Sub-generator

The Layouts sub-generator provides the controls to create a layout template 
that meets your needs. You can add and remove rows and columns on-the-fly as you 
require. See 
[Generating Layout Templates](/docs/7-2/reference/-/knowledge_base/reference/creating-layout-templates-with-the-themes-generator) 
for more information. 

### Themelets Sub-generator

Themelets are small, extendable, and reusable pieces of code. They only require 
the CSS and/or JavaScript files that you want to add to your theme. This modular 
approach reduces the need for duplicated code across themes and makes it easy 
for developers to share code snippets with other developers. Themelets are 
applicable for changes both small and large, from modifying the appearance of an 
individual piece of UI to adding new features. If there is something you have to 
manually code for every theme you create, it's a good candidate for a themelet. 
See 
[Generating Themelets](/docs/7-2/reference/-/knowledge_base/reference/creating-themelets-with-the-themes-generator) 
for more information. 

While you can create themes using the tools you prefer, the Liferay Theme 
Generator is designed with theme development for @product@ in mind. Its toolset 
and features help streamline theme development.
