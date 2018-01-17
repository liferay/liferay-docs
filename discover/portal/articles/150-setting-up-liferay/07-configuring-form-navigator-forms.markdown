# Configuring Form Navigator Forms [](id=configuring-form-navigator-forms)

@product@'s forms can be built to gather large amounts of data. These larger
forms are organized into easily navigable sections and categories. This is
possible thanks to the 
[Form Navigator](/participate/liferaypedia/-/wiki/Main/Form+Navigator) 
framework. As an administrator, you may wish to hide certain form sections. 
Maybe you want to show certain sections when creating new content, but hide 
other sections when someone updates that content. Perhaps you want to reorder 
form sections. Since Liferay DXP Fix Pack 14 and Liferay Portal CE GA4, all 
these configuration options are possible via the *Form Navigator Configuration* 
system setting. 

This tutorial covers how to use the *Form Navigator Configuration* system 
setting to control which form categories and sections are visible.

+$$$

**Note:** Individual fields within form sections cannot be hidden using this
method. Your developers can use a 
[Core JSP Hook](/develop/reference/-/knowledge_base/7-0/core-jsp-hook) 
to override a form's JSP if you need to customize the individual fields within a
form section. 

$$$
 
Get started by retrieving the available entry keys and categories next. 

## Running the Form Navigator Gogo Shell Command [](id=running-the-form-navigator-gogo-shell-command)

Form Navigator configurations contain the following information:

- Form ID
- Category (if there is one)
- Context (optional) 
- Section entry keys

You can retrieve this information easily, thanks to the following 
[Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell) 
command: 

    formNavigator:getPossibleConfigurations [optional form ID]
 
This command can be run two ways:

- **With no form ID:** lists every form with all possible categories and entries
- **With a form ID:** lists all categories and entries for the given form

For example, running the Gogo shell command 
`formNavigator:getPossibleConfigurations sites.form` results in the  output 
shown in the figure below:

![Figure 1: You can use the formNavigator GoGo shell command to retrieve the available categories and entries for forms.](../../images/form-navigator-gogo-shell.png)

You can read more about using the GoGo shell in the 
[Felix GoGo Shell Reference Guide](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell).

Now that you have the form ID(s) and available categories and entry keys, you 
can create a Form Navigator configuration. As mentioned above, Form Navigator 
configurations can contain an optional context. Form contexts are covered next. 

## Form Navigator Contexts [](id=form-navigator-contexts)

A Form Navigator context refers to the context in which a form is being viewed.
Form contexts allow you to control dynamically which form sections are displayed. 
The following contexts are available by default:

`add`: Denotes that the form is being viewed when new content is being created. 
For example, you could use the `add` context to specify which form sections are 
visible when someone creates a new site.

`update`: Denotes that the form is being viewed when content is being edited. 
For example, you could use the `update` context to specify which form sections 
are visible when someone edits a web content article.

You can create additional contexts by following the steps covered 
in the [Creating Form Navigator Contexts](/develop/tutorials/-/knowledge_base/7-0/creating-form-navigator-contexts) 
tutorial.

Now that you have the form ID(s), available categories and entry keys, and 
you understand Form Navigator contexts, you can configure the Form Navigator
system settings next. 

## Configuring Form Navigator System Settings [](id=configuring-form-navigator-system-settings)

Open the *Control Panel* and go to *Configuration* &rarr; *System Settings*.
Select *Foundation* &rarr; *Form Navigator Configuration*.

Follow these steps to create a new configuration entry: 

1.  Click the *Add* button to bring up the Form Navigator Configuration Form.
 
2.  Enter the Form Navigator ID you retrieved from the Gogo shell command 
    (`sites.form` for example).

3.  Next, enter the Form Navigator entry keys. Enter an optional context 
    (`add` or `update`), category (if there is one), and a comma-separated list 
    of Form Navigator entry keys for each section that you want to display. For 
    example, the value below hides the Documents and Media section under the 
    General tab for the Sites form:
    
        general=categorization,custom-fields,details,pages,site-template,site-url
 
    ![Figure 2: You can hide form categories and sections using the Form Navigator Configuration system setting.](../../images/form-navigator-configuration.png)
 
    The value below provides a configuration for the Sites form when editing a 
    site:
    
        update.general=categorization,custom-fields,details,documents-and-media
    
    Here is an example configuration for the web content form that has no 
    categories and several entries:
    
        content,metadata,permissions,related-assets,schedule,small-image

    Here is the same configuration with an `update` context:

        update=content,metadata,permissions,related-assets,schedule,small-image    
 
    **The order of the entry keys specifies the order in which the sections are 
    displayed in the form, from top to bottom.** To reorder sections, place the 
    comma-separated list of entry keys in the order you want the sections to 
    appear in the form. You can also move a section from one category to another 
    by adding the entry key to the category in which you want the section to 
    appear. 
  
    +$$$
 
    **Note:** If you want to hide a category, enter the category followed by an 
    equal sign with no entry keys. For example, `general=` would hide the 
    General category for the Sites form.
    
    $$$

4.  Save your changes.

Now you know how to control the visibility of your Form Navigator form 
categories and sections!

## Related Topics [](id=related-topics)

[Custom Fields](/discover/portal/-/knowledge_base/7-0/custom-fields)

[Form Navigator Extensions](/develop/tutorials/-/knowledge_base/7-0/form-navigator)

[Forms and Validation](/develop/tutorials/-/knowledge_base/7-0/forms-and-validation)

[Core JSP Hook](/develop/reference/-/knowledge_base/7-0/core-jsp-hook)
