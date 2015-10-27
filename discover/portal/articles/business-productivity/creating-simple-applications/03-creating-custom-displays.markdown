
# Using Templates to Display Forms and Lists

After creating data definitions and lists, you can control how the form appears
to your users, and how the resulting list of records is displayed. You do this
by creating templates for each view (form view and record display view) and
selecting them in the DDL Display portlet. You might realize you need to create
a sign-up sheet with a subset of the fields provided by the Volunteer Sign-Up
data definition created in the last two articles. Instead of creating a new
definition and list, just customize the Volunteer Sign-Up definition's templates.
Another use case would be a situation where one of the preexisting data
definitions that Liferay offers suits your list needs, but you need it laid out
differently, or need to provide different labels or field configuration options.
In that case, create a list from the definition, then add form and/or
display templates to the data definition.

Data definitions can have multiple form or display templates, and you can choose
whichever template you want to display. Create as many templates as
you might need, and prototype them to see how each feels. 

<!-- We could link to each h2 of an article in the intro so readers can skip
around more if they want to

Feel free to read each section of this article, or skip to the section you're most
interested in:

- [Managing Display and Form Templates]()

- [Creating Form Templates]()

- [Creating Display Templates]()
    [Creating a Simple FreeMarker Template]()

- [Using Workflow]()

-->

## Managing Display Templates and Form Templates

<!-- Add more detail on embedding templates within templates, copying existing
templates, deleting templates? -->

Since Display and Form Templates correspond to a particular data
definition, they're accessed from the *Data Definitions* section of the
Dynamic Data Lists portlet.

- Open the Product Menu, then click *Content* &rarr; *Dynamic Data Lists*. 

- Open the Configuration menu by clicking the vertical ellipsis icon at the top
  right of the portlet, and select *Manage Data Definitions*.

- Find the definition you're interested in, then open its Actions menu by
  clicking the vertical ellipsis icon.

- Select *Manage Templates* to begin working with the definition's templates.

![Figure 1: Manage and create display and form templates to change the display of forms and
lists.](../../images/ddl-manage-templates.png)

If there are any templates for the definition, you'll see them listed. To edit,
copy, delete, or configure permissions for the definition, open its Actions
menu by clicking the vertical ellipsis icon.

![Figure 2: Manage and create display and form templates to change the display of forms and
lists.](../../images/ddl-template-window.png)

If no templates have been created for a definition, you can create new ones. See
the next sections on Creating Form Templates and Creating Display Templates for
more details.

## Creating Form Templates

The default data entry form is the entire data model from a data definition,
including all required and optional fields. Listies who create new lists using a
data definition will see every item in that definition on the input form. What
if you just want a quick sign-up form to find out who's coming to dinner
tonight? Using a form template for an existing data definition, you can
customize the Volunteer Sign-Up definition's form's display. If you don't know
what the Volunteer Sign-Up data definition is, refer to the preceding articles, where
it was created.

You can access and create new form templates from the Product Menu:

- Click *Content* &rarr; *Dynamic Data Lists*. 

- Open the configuration menu by clicking the vertical ellipsis icon at the top
  right-hand corner of the page. 

- Select *Manage Data Definitions*, then click on the *Actions* button next to
  your data definition of choice and select *Manage Templates*. 

- Click the Add button, then *Add Form Template*, you're presented with the same
  kind of graphical, drag-and-drop interface used for creating the data
definition. 

- Move items around, delete unwanted fields from view and save when ready.You
  can limit the fields displayed for entry, change the order of elements, or
provide more accurate labels. 

![Figure 3: Add display and form templates to change the display of forms and
lists.](../../images/ddl-add-template.png)

Alternatively, you can create form templates from the Dynamic Data List Display
Portlet:

- Navigate to the page with your DDL Display portlet and make sure the
  appropriate list is selected for display.

- Find the *Add Form Template* icon on the bottom-left corner of the portlet
   window and click it to create a new template. Make sure you're signed
   in as a user with permission to create templates.

![Figure 2: Add display and form templates directly from the Dynamic Data Lists portlet.](../../images/ddl-display-add-template.png)

Now your friends and enemies alike will be impressed with your `list.it` skills.
It may look to the untrained eye like you've single-handedly created three or
four different data types for your lists but you know better. You used the power
that form templates provide, using one data model that encompasses the maximum
information you might need. Then you quickly produced form templates with a few
mouse clicks. Now that you have such a vast amount of data collection options,
how will you display them? However you want, of course.

## Creating Display Templates

<!-- Too much text before getting to the steps. Move some to the intro of the
article? -->

For every data definition, you can create as many displays as you need. If
you've created a form template that doesn't show all the fields of a particular
data definition, you probably don't want to display those fields in the list,
either. For example, if you created a special "Thanksgiving Dinner Sign-Up" list
using your "Volunteer Sign-Up" definition, you wouldn't want to confuse fellow
Listies by displaying data fields you never asked for. "Preferred task?" a
friend might say, "I don't remember seeing *that* on the sign-up form!" To avoid
such embarrassing situations, you should create a custom display to match that
list. Take it even further and provide a fancy, JavaScript-driven image carousel
preview of all the attendees of the party. This would certainly give you
bragging rights on `list.it`. Display templates give you the power to do all
this and more.

The first task is to create a new display template for the Volunteer Sign-Up
data definition. As with Form Templates, you can do this from the Dynamic Data
List Display portlet, or from the Dynamic Data Lists portlet in the Product
Menu.

From the Dynamic Data List Display portlet:

- Navigate to the page with your DDL Display portlet and make sure your list is
   selected for display.

- Find the *Add Display Template* icon on the bottom-left corner of the portlet
   window and click it to create a new template. Make sure you're signed
   in as a user with permission to create templates.

![Figure 4: Add display and form templates directly from the Dynamic Data Lists portlet.](../../images/ddl-display-add-template.png)

From the Product Menu:

- Click on *Content* &rarr; *Dynamic Data Lists*.

- Open the configuration menu (click the vertical ellipsis in the portlet's
    title bar), then click *Manage Data Definitions*.

- Find your data definition in the list, then click the Actions button and
    choose *Manage Templates*.

- Now click the Add button and choose *Add Display Template*.

![Figure 1: Add display and form templates to change the display of forms and
lists.](../../images/ddl-add-template.png)

Fill out the form with a name and a description. Next, choose whether to use
[FreeMarker](http://freemarker.org/docs/) or
[Velocity](http://velocity.apache.org/engine/devel/user-guide.html) to write 
your template. 

<!-- Is it okay to link to these docs? Some Portal users might not know how to use a
template language -->

![Figure 5: Give your Display templates a name, description, and choose whether
to use FreeMarker or Velocity in your template. ](../../images/ddl-display-template-details.png)

Once you choose the script language, you can upload a template file or use the
template editor to enter a script manually. Inside the editor, you have access
to a palette featuring common variables related to your selected template
language. Hover your pointer over a variable in the palette for a more detailed
description. To place a variable into the display template code, position your
cursor where you want it placed, and click the variable name in the palette.
Another useful tool in the display template editor is the autocomplete feature.
In a FreeMarker template, it can be invoked by typing *${* which opens a
drop-down menu of common variables. Upon selecting one of the variables, the
editor inserts the variable into your display template code.

![Figure 6: Upload an existing template, or create one using the template
editor.](../../images/ddl-display-template-script.png)


+$$$

**NOTE:** If you're familiar with Web Content Templates, Display templates
customize the display of a list in the same way. Display templates can be
written in FreeMarker or Velocity, pulling data from the data definition in the
same way that web content templates pull data from their structures. Also
similar to web content templates, display templates can be embedded in other
display templates. This allows for reusable code, JS library imports, or macros
which will be imported by Velocity or FreeMarker templates in the system.
Embedding display templates provides a more efficient process when you have a
multitude of similar data definitions. Just import an embedded display template
and work off of it for your new display template.  We'll look at a simple
example, but for more information on using template scripts to pull data from a
backing structure, see the article on web content templates. 

$$$

### Creating a Simple Display Template

There's a lot you can do with your templates. To write a FreeMarker template
that gives a summary of who is helping on the tasks specified in the data
definition you [created earlier](discover/portal/-/knowledge_base/7-0/creating-data-definitions), you need to access the records for the list and display the name and task for
each volunteer. Use the available helper variables to find out what records you
have access to:

    reserved_ddm_template_id

    reserved_ddm_structure_id
    
    reserved_record_set_description
    
    reserved_record_set_id
    
    reserved_record_set_name

<!-- Link to DDLDisplayTemplateHelper class Javadoc when available -->

Inside a template, these variables give us the ID for the record set (that
contains all of the volunteers in our list), as well as the name, description
and data definition. You can easily retrieve all the data list's records using the
handy `DDLDisplayTemplateHelper` class, which provides the ability to perform
some common tasks without any difficulty. These are the functions available using
`DDLDisplayTempalteHelper`:

    getDocumentLibraryPreviewURL

    getHTMLContent

    getLayoutFriendlyURL

    getRecords

    renderRecordFieldValue

For example, if you want to use the `getRecords` method to have access to all
of a data definitions existing entries, enter 

    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>

into your Display Template. This *fetches* the records of the data list
(really,the list's data definition) definition the template is associated with.
You haven't done anything with them yet, so your display is still empty. To list
all the records, you need to use the *Data List Records* helper on the right
hand column of the template editor. Remember to place your cursor in the proper
place in the template editor window, then click *Data List Records*. You'll see

    <#if records?has_content>
        <#list records as cur_record>
            ${cur_record}
        </#list>
    </#if>

appear wherever you placed your cursor. You might think you'll have a nicely
formatted list of all the data records, but you won't. It will spit out
everything in the database for the given data definition, which is ugly and
practically useless.

![Figure 7: It's not useful to simply get the data records and display them
in their raw form.](../../images/ddl-display-unfinished.png)

You're getting closer to having a coherent display template. There's just a
couple steps left. First, wrap the line with `${cur_record}` in HTML tags that
create an unordered list, with the records as list items:

    <ul><li>
    ${cur_record}
    </li></ul>

If you look at the list now, it's getting more organized. Each record is set
apart with a bullet point, and it's a bit more readable. But you don't need all
the confusing database fields displayed to your users. Narrow it down to simply
the Name field and the Preferred Task field. Right now you're listing all the
fields for each record by specifying `${cur_record}`. If you remove that, and
instead use the template editor's *Data List Record Fields* to explicitly list
which fields should be displayed, you can easily finish the display template.
For example, if you want to put the Name field in, place your cursor inside the
`<li>` tag and click the *Name* entry under *Data List Record Fields*. You'll see
that the FreeMarker code

    ${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("Name")?first, locale)}

was added. That's nice, but if you want the format for each bullet to read

*[Person's Name]* will help with [Preferred Task]

you need to replace the name field you added with the following lines:

    <em>${ddlDisplayTemplateHelper.renderRecordFieldValue
        (cur_record.getDDMFormFieldValues("Name")?first, locale)}</em> 
            will help with 
    ${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.
        getDDMFormFieldValues("PreferredTask")?first, locale)}

There's the meat of the template. Wrap the name field in an `<em>` to emphasize
it (the styling on your page will dictate whether it appears in italics, bold,
or whatever other emphatic text type you're site design team chose), add the
text "will help with" and then get the value of the Preferred Task field for
each record.

To complete the template, add a heading and a simple description of the list
before the list items:

    <#if records?has_content>
        <#list records as cur_record>
            ${cur_record}
        </#list>
    </#if>
    <h1>Task Summary</h1>

    Here are the tasks that people have signed up for on ${reserved_record_set_name}

![Figure 8: This display template provides a list of users who've volunteered along with the tasks for which they volunteered.](../../images/ddl-display-template1.png)

That gives you a simple, clean display of only the form data that you want your
site guests to see. Here's the whole template:

    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>
    
    <h1>Task Summary</h1>

    Here are the tasks that people have signed up for on ${reserved_record_set_name}<br />
    
    <#if records?has_content>
        <#list records as cur_record>
            <em>${ddlDisplayTemplateHelper.renderRecordFieldValue
                (cur_record.getDDMFormFieldValues("Name")?first, locale)}</em> 
        will help with 
            ${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.
                getDDMFormFieldValues("PreferredTask")?first, locale)}        
        </#list>
    </#if>

Since that was so simple and fun, what if you wanted to display your Volunteer's
Name field and their picture only? 

    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>

    <h1>Here are the people currently signed up! Join them now! Click the Add button above to fill out the form!</h1>
        <#if records?has_content>
            <#list records as cur_record>
                <ul>
                    <li>
                        <!-- The below FreeMarker code gets the name field and adds 4 spaces -->
                        ${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("Name")?first, locale)}&nbsp;&nbsp;&nbsp;&nbsp;

                        <!-- The below FreeMarker code gets the Preview URL of the Profile Picture and displays it using an HTML img tag -->
                        <img src="${ddlDisplayTemplateHelper.getDocumentLibraryPreviewURL(cur_record.getDDMFormFieldValues("ProfilePicture")?first, locale)}" height=60 width=60 />

                        <!-- The below FreeMarker code gets the Preview URL of the Profile Picture and displays a link using an HTML a href tag -->
                        <a href="${ddlDisplayTemplateHelper.getDocumentLibraryPreviewURL(cur_record.getDDMFormFieldValues("ProfilePicture")?first, locale)}">
                        ${languageUtil.format(locale, "download-x", "Profile Picture", false)}
                        </a>
                    </li>
                </ul>
            </#list>
        </#if>

![Figure 9: This display template shows the Name field and a small version of
the image uploaded as a Profile Picture.](../../images/ddl-display-template.png)

If you don't want to include the link for downloading the Profile Pictures, just
remove the last section of FreeMarker code, and you'll only have the person's
Name and Profile Picture displayed.

As you can see, using the template editor's functionality to access a Data
Definition's records and fields is easy, and you can assemble a display template
quickly. Add some HTML to improve the formatting, and you now have an attractive
display to present your data.

<!-- Find out how secure the use of serviceLocator has become. Document it if
possible.


Service Locator is a Java class in Liferay that can be used to find Liferay's
services and let you access their methods in your templates. To enable the
`serviceLocator` variable in your templates

1. Go to Configuration Admin and find the *Free marker engine configuration* 
2. Edit the configuration however you need, including by specifying the
restricted classes and variables. Make sure to remove `serviceLocator` from the
Restricted Variables section.
3. Explicitly set the services to make available to `serviceLocator`.

To gain access to the `DDLRecordLocalService` methods, we need to use
a helper utility called `serviceLocator` that retrieves an instance of the
service for us. Once we have the service, we can retrieve the list of records
(our list of volunteers). Accessing the service with the `serviceLocator` can be
done with the following line of code:

    <#assign DDLRecordLocalService =
        serviceLocator.findService(
        "com.liferay.dynamic.data.lists.service.DDLRecordLocalService")>

We store a handle to our service in `DDLRecordLocalService` so we can use the
service to retrieve our list of volunteers:

    <#assign records = DDLRecordLocalService.getRecords(reserved_record_set_id)>

Now that we have our records, we can iterate through the list and display the
data from each record that we want to show. To access a field from a record
entry (such as the volunteer's name), we call the `getFieldValue` method and
pass in the field's name. Each dynamic data list record has a number of other
similar methods (see the `com.liferay.dynamic.data.lists.model.DDLRecord`
interface at
[http://docs.liferay.com/portal/6.2/javadocs](http://docs.liferay.com/portal/6.2/javadocs)
but you'll probably use `getFieldValue` most often. This method returns the
content of the field.

    ${cur_record.getFieldValue("name")}

Now all we have to do is set the results in some appealing way. In this example,
we've made it very simple by using an unordered list for the results (`<ul>`).
Here is the complete source for the template:

    <h1>Task Summary</h1>

    Here are the tasks that people have signed up for on ${reserved_record_set_name}

    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>

        <ul>
        <#if records?has_content>
                <#list records as cur_record>
                        <li><em>${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("Name")?first, locale)}</em> will help with ${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("PreferredTask")?first, locale)}</li>
                </#list>
        </#if>
        </ul>

Once you've typed the template's source into the editor window, click *Save* to
save the display template. With the display template selected, your list display
can now be a summary of tasks as shown below. 

-->

With the full power of FreeMarker or Velocity templates at your fingertips, you
have easy access to all the data of your lists, as well as the full complement
of helper methods and the Alloy UI JavaScript library to make easy work of
dynamic displays. 

Over the last few articles, you've gained knowledge in using Liferay's Dynamic
Data Lists portlet, and the Dynamic Data List Display portlet. With these two
portlets, and the flexible power of data structures and templates they provide,
you can create flexible, simple, list-based applications in Liferay very
quickly. 

If you're not a Listie and you happen to be deploying custom lists in an
environment that requires approval from other users, then it's not enough to
just create the list and display a form. What you need is a real integration
with review processes, called workflow in Liferay. Read the next article to find
out how workflow integrates smoothly with Dynamic Data Lists.

<!-- Creating a separate article for workflow forms. This is getting long. -->
