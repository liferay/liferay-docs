# Upgrading Layout Templates [](id=upgrading-layouts-and-themes)

If you're familiar with developing layout templates for Liferay 6.2, the process 
is the same, only the syntax has changed slightly.

This tutorial demonstrates the following:

- How to upgrade your layout template to @product@

<!-- Comment out Code Upgrade Tool instructions until the tool is worth using with layout templates. Jim

There are a couple ways you can upgrade your layout template to @product@. If
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
points out the @product@ breaking changes, and suggest how to update it. Follow
these steps to upgrade your layout template to @product@.

1.  Right-click on your layout template project in the *Package Explorer*, and 
    select *Liferay* &rarr; *Find Liferay 7 breaking API changes*.
    
    ![Figure 1: The Code Upgrade Tool finds the breaking changes in your code and suggests how to fix them.](../../../images/upgrading-layouts-find-api-breaking-changes.png)
    
    The breaking changes and suggested fixes are listed in the 
    *Liferay 7 Migration Problems* tab of the workspace. At the moment, only a 
    `Code Problems` folder is shown. You'll have to select a file first, in 
    order to see the related breaking changes.
    
2.  Open the `Code Problems/[layout-template-name]` folder and select the 
    `liferay-plugin-package.properties` file.
    
    Now you can see the problems listed, with suggested fixes listed below.
    
    ![Figure 2: Breaking changes are listed in the *Liferay 7 Migration Problems* tab.](../../../images/upgrading-layouts-list-of-breaking-changes.png)
    
    In this case, there is only one problem listed.
    
2.  Double-click the problem to open the `liferay-plugin-package.properties`
    file.
    
    Currently the `liferay-versions` property is set to 6.2. The Code Upgrade
    Tool can fix this.
    
3.  Right-click on the problem in the *Liferay 7 Migration Problems* tab and
    select *Correct automatically*.
    
    ![Figure 3: The Code Upgrade Tool can automatically update some breaking changes for you.](../../../images/upgrading-layouts-correct-automatically.png)
    
    The version is updated to `7.0.0+` now. You're half way there, but you have
    some more adjustments to make.
    
The next section covers the rest of the changes you'll need to make to your
layout template for @product@.

## Upgrading Your Layout Template Files [](id=upgrading-your-layout-template-files)

The first thing you'll need to do is update the version for your layout
template. If you followed the steps in the last section, you can skip to step 2.
-->

Upgrading a layout template involves updating it's Liferay version and updating
the class syntax for its rows and columns.

Follow the steps below to update your layout template for @product@.

1.  Open your `liferay-plugin-package.properties` file and change the 
    `liferay-versions` property to `7.0.0+`

        liferay-versions=7.0.0+
        
    Now that your Liferay version is updated, you can update your row classes
    next.
    
2.  Open your layout template's TPL file, `1_2_1_columns.tpl` for example, and
    replace each `row-fluid` class with `row`.
    
    Now that your rows are updated, you can move to your columns next.
    
    Previously, column size was denoted using the `span[number]` class. The 
    `[number]` specifies the width of the column. Each column is a division of 
    the total width(100%) of 12, which was denoted with the `span12` class. The 
    new Bootstrap grid system uses `col-[device-size]-[number]` instead of 
    `span[number]`. `[device-size]` can be `xs`, `sm`, `md`, or `lg`. In most 
    cases, a `md` device size will work. You can read more about the Bootstrap 
    grid system on their site at [http://getbootstrap.com/css/#grid](http://getbootstrap.com/css/#grid).
    
2.  Still inside your layout template's TPL file, replace each `span-[number]` 
    class with `col-md-[number]`, as shown in the example below:
    
        <div class="portlet-column portlet-column-last col-md-4" id="column-3">
        
With all the updates, your finished layout template should look similar to the 
[1_2_1_columns.tpl](https://github.com/liferay/liferay-portal/blob/7.0.x/portal-web/docroot/layouttpl/custom/1_2_1_columns.tpl)
example shown below:

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

Your layout template is ready to use in @product@!

## Related Topics [](id=related-topics)

[Planning a Plugin Upgrade to Liferay 7](/develop/tutorials/-/knowledge_base/7-0/migrating-existing-code-to-liferay-7)

[Benefits of Liferay 7 for Liferay 6 Developers](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers)

<!-- Code Upgrade Tool link goes here -->
