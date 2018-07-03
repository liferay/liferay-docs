# Creating Display Templates

For every data definition, you can create as many displays as you need. If 
you've created a form template that doesn't show all the fields of a particular
data definition, you probably don't want to display those fields in the list, 
either. 

+$$$

**Note:** If you're familiar with 
[web content templates](/discover/portal/-/knowledge_base/7-1/designing-web-content-with-templates), 
display templates customize the display of a list in the same way. Display 
templates can be written in FreeMarker or Velocity, pulling data from the data 
definition in the same way that web content templates pull data from their 
structures. Also similar to web content templates, display templates can be 
embedded in other display templates. This allows for reusable code, JS library 
imports, or macros which will be imported by Velocity or FreeMarker templates in 
the system. Embedding display templates provides a more efficient process when 
you have a multitude of similar data definitions. Just import an embedded 
display template and work off of it for your new display template. 

$$$

As with 
[form templates](liferay.com), 
you can create display templates from the Dynamic Data Lists app in Site 
Administration, or the Dynamic Data Lists Display widget. 

Follow these steps to create a display template from Site Administration: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and expand your site's menu (the Site Administration menu). Then select 
    *Content* &rarr; *Dynamic Data Lists*. This opens the Dynamic Data Lists 
    screen. A table lists any existing dynamic data lists. 

2.  Click the *Options* button at the top-right 
    (![Options](../../../images/icon-options.png)) 
    and select *Manage Data Definitions*. The Data Definitions screen appears. A 
    table lists any existing data definitions. 

3.  Click the definition's *Actions* button 
    (![Options](../../../images/icon-options.png)) 
    and select *Manage Templates*. This opens a screen that lists the 
    definition's templates. 

4.  Click the *Add* button 
    (![Add](../../../images/icon-add.png)) 
    and select *Add Display Template*. This opens the New Template form. 

5.  Give the template a name, then expand the *Details* section of the form and 
    give it a description. Also in the details section of the form, select the 
    templating language to use from the *Language* selector. You can choose 
    [FreeMarker](https://freemarker.apache.org/index.html), 
    or 
    [Velocity](https://velocity.apache.org/). 

6.  In the *Script* section of the form, create your template in the editor 
    using the templating language you chose in the previous step. The palette to 
    the left of the editor contains common variables. Click a variable to insert 
    it in the editor. Another useful tool in the editor is the autocomplete 
    feature. In a FreeMarker template, you can invoke it by typing `${`, which 
    opens a drop-down menu of common variables. Select a variable to insert it 
    in the editor. Alternatively, you can upload a complete script file via the 
    *Browse* button below the editor. 

7.  Click *Save* when you're done creating the template. 

![Figure 1: Create your display template in the editor.](../../../images/ddl-template-editor.png)

Alternatively, you can use the Dynamic Data Lists Display widget to create 
display templates: 

1.  Follow the instructions in the 
    [Creating Data Lists article](liferay.com) 
    for adding and configuring the widget in a site page. Make sure to configure 
    the widget to show the list whose definition you want to create a template 
    for. 

2.  Click the widget's *Add Display Template* button. This opens the same form 
    as above for creating a display template for the list's definition. 

<!-- 
Per Russ, include a more detailed example of creating a template, when possible. 
See example in the analogous 7.0.x article, and figure out which code isn't 
specific to the cross-article Lunar Resort example in those docs. 
-->
