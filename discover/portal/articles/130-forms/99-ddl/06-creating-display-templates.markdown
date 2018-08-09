# Creating Display Templates [](id=creating-display-templates)

For every data definition, you can create as many displays as you need. If
you've created a form template that doesn't show all the fields of a particular
data definition in the data list's form view, you probably don't want to display
those fields in the list view, either. Modify the list view using Display
Templates. 

+$$$

**Note:** If you're familiar with 
[web content templates](/discover/portal/-/knowledge_base/7-1/designing-web-content-with-templates), 
display templates customize the display of a list in the same way. Display 
templates are written in FreeMarker or Velocity, pulling data from the data 
definition in the same way that web content templates pull data from their 
structures. Also similar to web content templates, display templates can be
embedded in other display templates. This allows for reusable code, JavaScript
library imports, or macros imported by Velocity or FreeMarker templates in the
system. Embedding display templates provides a more efficient process when you
have a multitude of similar data definitions. Just import an embedded display
template and work off of it for your new display template. 

$$$

As with 
[form templates](/discover/portal/-/knowledge_base/7-1/creating-form-templates), 
you can create display templates from the Dynamic Data Lists app in Site 
Administration or the Dynamic Data Lists Display widget. 

Follow these steps to create a display template from Site Administration: 

1.  Open the Menu 
    (![Menu](../../../images/icon-menu.png)) 
    and expand your Site's menu (the Site Administration menu). Then select 
    *Content* &rarr; *Dynamic Data Lists*. This opens the Dynamic Data Lists 
    screen. A table lists any existing dynamic data lists. 

2.  Click the *Options* button at the top-right 
    (![Options](../../../images/icon-options.png)) 
    and select *Manage Data Definitions*. The Data Definitions screen appears. A 
    table lists existing data definitions. 

3.  Click the definition's *Actions* button 
    (![Options](../../../images/icon-options.png)) 
    and select *Manage Templates*. This lists the definition's templates. 

4.  Click the *Add* button 
    (![Add](../../../images/icon-add.png)) 
    and select *Add Display Template*. 

5.  Give the template a name, expand the *Details* section of the form, and 
    give it a description. Also in the details section of the form, select the 
    templating language to use from the *Language* selector. You can choose 
    [FreeMarker](https://freemarker.apache.org/index.html), 
    or 
    [Velocity](https://velocity.apache.org/). 

6.  In the *Script* section of the form, create your template in the editor 
    using the templating language you chose in the previous step. The palette to 
    the left of the editor contains common variables. Click a variable to insert
    it in the editor. The editor also autocompletes. In a FreeMarker
    template, type `${`, which opens an autocomplete list of common variables.
    Select a variable to insert it in the editor. Alternatively, you can upload
    a complete script file via the *Browse* button below the editor. 

7.  Click *Save* when you're done creating the template. 

![Figure 1: Create your display template in the editor.](../../../images/ddl-template-editor.png)

Alternatively, you can use the Dynamic Data Lists Display widget to create 
display templates: 

1.  Follow the instructions in the 
    [Creating Data Lists article](/discover/portal/-/knowledge_base/7-1/creating-data-lists) 
    for adding and configuring the widget in a site page. Make sure to configure
    the widget to show the list's definition you're making into a template. 

2.  Click the widget's *Add Display Template* button. This opens the same form 
    as above for creating a display template for the list's definition. 

## Display Template Editor [](id=display-template-editor)

Helper variables are available in the template editor. These provide access to
most of the data that you'll use in creating Display Templates. The variables
under the heading Data List Variables let you inject specific information about
the data definition the template is being created for:

    reserved_ddm_structure_id
    
    reserved_record_set_description
    
    reserved_record_set_id
    
    reserved_record_set_name

    reserved_ddm_template_id

Inside a template, these variables give the ID for the record set (that contains
all of the volunteers in our list) as well as the name, description, and data
definition. 

Display the list of records by retrieving them and assigning them to the handy
`records` variable. Retrieve the list's records from `DDLDisplayTemplateHelper`,
which contains these functions:

    getDocumentLibraryPreviewURL

    getHTMLContent

    getLayoutFriendlyURL

    getRecords

    renderRecordFieldValue

`DDLDisplayTemplateHelper` performs common tasks. Use the `getRecords` method to
access a data definition's entries and assign them to a `records` variable: 

    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>

This *fetches* the records of the associated data list. You haven't done
anything with them yet, so your display is still empty. To list all the records,
use the *Data List Records* helper in the sidebar of the template editor.
Remember to place your cursor in the proper place in the template editor window,
then click *Data List Records*. This code appears at the cursor: 

    <#if records?has_content>
        <#list records as cur_record>
            ${cur_record}
        </#list>
    </#if>

This default code snippet spits out everything in the database for the given
data definition, which is ugly and practically useless:

    {uuid=52c4ac1c-afe7-963c-49c6-5279b7030a99, recordId=35926, groupId=20126, 
    companyId=20099, userId=20139, userName=Test Test, versionUserId=20139, 
    versionUserName=Test Test, createDate=2018-07-16 14:31:51.056, 
    modifiedDate=2018-07-16 14:31:51.058, DDMStorageId=35927, recordSetId=35922, 
    recordSetVersion=1.0, version=1.0, displayIndex=0, lastPublishDate=null}

Here's a simple example template that uses a list based on the embedded Contacts
data definition, and only displays the Company Name and Email fields in
a bulleted list:

    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>

        <h1>Here are contacts by company name and email address.</h1>

        <#if records?has_content>
            <#list records as cur_record>
                <ul>
                    <li>
                        <#-- The below gets the Company field and wraps it in an <em> tag -->
                        Company Name: <em>${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("company")?first, locale)}</em><br /> 
                        <#-- The below gets the Email field  and wraps it in an <em> tag --> 
                        Email: ${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("email")?first, locale)} 
                    </li> 
                </ul> 
            </#list> 
        </#if>

Here's what it looks like: 

![Figure 2: Extract appropriate display information, rather than spitting out the whole object.](../../../images/ddl-contacts-template.png)

Now you're prepared to make data lists beautiful using Display Templates.
