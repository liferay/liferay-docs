# Upgrading Layout Templates [](id=upgrading-layout-templates)

Layout templates for Liferay 7 differ slightly from layout templates for
Liferay 6. The layout template's rows and columns are affected by Bootstrap's
new grid system syntax.

This tutorial demonstrates the following:

- How to upgrade your layout template to Liferay 7

<!-- Comment out Code Upgrade Tool instructions until the tool is worth using with layout templates. Jim

There are a couple ways you can upgrade your layout template to Liferay 7. If
your project is in Liferay IDE or Liferay Developer Studio, you can use the Code 
Upgrade Tool to start the upgrade process. The second option is to manually 
upgrade the template files in the editor of your choice.

If your project is in Liferay IDE or Liferay Developer Studio, you can follow 
the steps in the next section, otherwise you can skip to the 
[Upgrading Your Layout Template Files](#upgrading-your-layout-template-files) 
section.

## Upgrading Your Layout Template with the Code Upgrade Tool [](id=upgrading-your-layout-template-with-the-code-upgrade-tool)

Since Liferay IDE and Liferay Developer Studio version 3.0, the Code Upgrade
Tool has been available to use. The Code Upgrade Tool runs through your code,
points out the Liferay 7 breaking changes, and suggest how to update it. Follow
these steps to upgrade your layout template to Liferay 7.

1.  Right-click on your layout template project in the *Package Explorer*, and 
    select *Liferay* &rarr; *Find Liferay 7 breaking API changes*.
    
    Figure 1: The Code Upgrade Tool finds the breaking changes in your code and suggests how to fix them. ../../../images/upgrading-layouts-find-api-breaking-changes.png
    
    The breaking changes and suggested fixes are listed in the 
    *Liferay 7 Migration Problems* tab of the workspace. At the moment, only a 
    `Code Problems` folder is shown. You'll have to select a file first, in 
    order to see the related breaking changes.
    
2.  Open the `Code Problems/[layout-template-name]` folder and select the 
    `liferay-plugin-package.properties` file.
    
    Now you can see the problems listed, with suggested fixes listed below.
    
    Figure 2: Breaking changes are listed in the *Liferay 7 Migration Problems* tab. ../../../images/upgrading-layouts-list-of-breaking-changes.png
    
    In this case, there is only one problem listed.
    
2.  Double-click the problem to open the `liferay-plugin-package.properties`
    file.
    
    Currently the `liferay-versions` property is set to 6.2. The Code Upgrade
    Tool can fix this.
    
3.  Right-click on the problem in the *Liferay 7 Migration Problems* tab and
    select *Correct automatically*.
    
    Figure 3: The Code Upgrade Tool can automatically update some breaking changes for you. ../../../images/upgrading-layouts-correct-automatically.png
    
    The version is updated to `7.0.0+` now. You're half way there, but you have
    some more adjustments to make.
    
The next section covers the rest of the changes you'll need to make to your
layout template for Liferay 7.

## Upgrading Your Layout Template Files [](id=upgrading-your-layout-template-files)

The first thing you'll need to do is update the version for your layout
template. If you followed the steps in the last section, you can skip to step 2.
-->

Upgrading a layout template involves updating its Liferay version and updating
the class syntax for its rows and columns.

Follow these steps to upgrade your layout template for Liferay 7:

1.  Open your `liferay-plugin-package.properties` file and update the 
    `liferay-versions` property to `7.0.0+`:

        liferay-versions=7.0.0+
 
2.  Open your layout template's `.tpl` file and replace `row-fluid` with `row`,
    in each row's class value. 
 
3.  Previously, column size was denoted using a class value of format
    `span[number]`. The new Bootstrap grid system uses the format
    `col-[device-size]-[number]`. 

    The `[device-size]` value must be `xs`, `sm`, `md`, or `lg`. In most 
    cases, an `md` device size works well. You can read more about the Bootstrap
    grid system on their site at [http://getbootstrap.com/css/#grid](http://getbootstrap.com/css/#grid).

    The `[number]` value must be an integer from `1` to `12`. A row's width is
    divisible by twelve; so the combined width of a row's columns must equal `12`.

    Inside the `.tpl` file, replace each `span-[number]` class value with
    `col-[device-size]-[number]`, where `[device-size]` is `xs`, `sm`, `md`, or
    `lg` and `[number]` is an integeter from `1` to `12`.

    Here's an example column that uses the `md` device size and a column that is
    a third (`4/12`) of the row's total width: 

        <div class="portlet-column portlet-column-last col-md-4" id="column-3">
 
As an example, here's Liferay 6 layout template [1_2_1_columns.tpl](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-web/docroot/layouttpl/custom/1_2_1_columns.tpl)
upgraded to Liferay 7:

    <div class="columns-1-2-1" id="main-content" role="main">
            <div class="portlet-layout row">
                    <div class="col-md-12 portlet-column portlet-column-only" 
                    id="column-1">
                            $processor.processColumn("column-1", 
                            "portlet-column-content portlet-column-content-only")
                    </div>
            </div>
    
            <div class="portlet-layout row">
                    <div class="col-md-6 portlet-column portlet-column-first" 
                    id="column-2">
                            $processor.processColumn("column-2", 
                            "portlet-column-content portlet-column-content-first")
                    </div>
    
                    <div class="col-md-6 portlet-column portlet-column-last" 
                    id="column-3">
                            $processor.processColumn("column-3", 
                            "portlet-column-content portlet-column-content-last")
                    </div>
            </div>
    
            <div class="portlet-layout row">
                    <div class="col-md-12 portlet-column portlet-column-only" 
                    id="column-4">
                            $processor.processColumn("column-4", 
                            "portlet-column-content portlet-column-content-only")
                    </div>
            </div>
    </div>

Your layout template is ready to use in Liferay 7!

**Related Topics**

[Planning a Plugin Upgrade to Liferay 7](/develop/tutorials/-/knowledge_base/7-0/migrating-existing-code-to-liferay-7)

[Benefits of Liferay 7 for Liferay 6 Developers](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers)

<!-- Uncomment this link when the referenced tutorial is published. Jim
[Adapting to Liferay 7's API with the Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
-->
