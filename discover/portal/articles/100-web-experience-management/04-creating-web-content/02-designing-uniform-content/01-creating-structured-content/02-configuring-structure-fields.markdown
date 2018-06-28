# Configuring Structure Fields [](id=configuring-structure-fields)

There are a number of options available for configuring each structure field. 
Some of them relate to how the fields are displayed or how users interact with
them, but probably the most important field configuration is the **Name**. When
you create a new field, it has a random name generated that looks something 
like `TextField4882`. In most cases, you will want to change this to something
that is more memorable and more descriptive. When it comes time to create the
template, you don't want to be trying to remember if `TextField4882` was the 
field for entering an applicant's name or their annual salary.

Let's do this now.

1.  In your structure, add an *HTML* element.

2.  Hover over the field and select the *Configuration* icon 
    (![Configuration](../../../../../images/icon-wrench.png)).

3.  Change the *Field Label* value to *Instructions* and the *Name* value 
    (variable name) to `steps`. Now your template writer has a variable by 
    which he or she can refer to this field.

Here's a list of all the configurable settings available for a structure's
fields:

**Type:** Lists the type of field placed in the definition. This is not editable
but is available to reference from a template.

**Field Label:** Sets the text that can be displayed with the field. This is the
human-readable text that the user sees.

**Show Label:** Select *Yes* to display the Field Label.

**Required:** Select *Yes* to mark the field required. If a field is required,
users must enter a value for it in order to submit content using this structure.

**Name:** The name of the field internally, automatically generated. Since this
is the variable name that you can read the data from in a template, you should
give a more memorable name here.

**Predefined Value:** Specifying predefined values for structure forms is a way
to specify defaults. When a user creates a new web content article based on a
structure that has predefined values for various fields, the predefined values
appear in the form as defaults for those fields.

**Tip:** Each field can have a small help icon, with a tooltip attached that
displays helpful information. If you would like to provide text for the tooltip
you may enter it here.

**Indexable:** Select *Yes* to enable @product@ to index your field for search.

**Localizable:** Select *Yes* to enable @product@ to localize your field.

**Repeatable:** Select *Yes* to make your field repeatable. Your users can then
add as many copies of this field as they like. For example, if you're creating a
structure for articles, you might want a repeatable Author field in case you
have multiple authors for a particular article.

**Multiple:** Select *Yes* to enable a multi-selection list (only available for
Select).

**Options:** Changes the options available for selection. You're able to add and
remove options as well as edit each individual option's display name and value
(only available for Radio and Select).

**Style:** Changes the line separator's style (only available for Separator).

### Structure Default Values [](id=structure-default-values)

Structure Default Values let you create one structure that uses common data from
multiple articles.

Returning to the newspaper scenario again, assume you want all sports articles
to have the same display page (sports page), the same categories, or the same
set of tags. Instead of adding them for each article or wondering if your users
are adding them to every web content article, you can add these characteristics
once for every sports article by creating default values for the structure.
Creating default values is not part of creating a new structure, so make sure
you have an existing structure.

To edit a structure's default values:

1.  Go to *Web Content* in the Content section of Site Administration and click 
    the *Options* icon (![Options](../../../../../images/icon-options.png)) &rarr; 
    *Structures* to see the structures list.
    
2.  Find the *Actions* button (![Actions](../../../../../images/icon-actions.png)) 
    for the desired structure and select *Edit Default Values* from the menu to 
    view a window like the one below.

    This form allows you to manage the structure settings.

![Figure 1: You can edit default values via the *Actions* button of the Manage Structures interface.](../../../../../images/structure-actions.png)

Every new web content you create with this structure is preloaded with the
data you inserted. Next, you'll learn about assigning permissions.
