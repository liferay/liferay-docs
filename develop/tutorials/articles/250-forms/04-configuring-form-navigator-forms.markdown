# Configuring Form Navigator Forms [](id=configuring-form-navigator-forms)

Some of @product@'s forms contain several categories, with multiple sections in 
some cases. These forms are built on top of the Form Navigator API. As a Portal 
Administrator, you may wish to show certain form sections when creating new 
content, but hide other sections when someone updates that content. You may also 
want to reorder form categories. Whether you want to modify existing form 
navigator forms or forms you have created with [form navigator extensions](/develop/tutorials/-/knowledge_base/7-0/form-navigator), 
you can accomplish this via System Settings. 

This tutorial covers how to configure these forms, using System Settings.

Get started by retrieving the available entries and categories next.

## Running the Form Navigator Gogo Shell Command [](id=running-the-form-navigator-gogo-shell-command)

To modify a form, you need the form ID, entry, and category and context if 
applicable. You can retrieve this information easily, thanks to the following 
Gogo shell command: 

    formNavigator:getPossibleConfigurations [optional form ID]
  
This command can be run two ways:

- **With no form ID:** lists every form with all possible categories and entries
- **With a form ID:** lists all categories and entries for the given form

For example, running the Gogo shell command 
`formNavigator:getPossibleConfigurations sites.form` results in the  output 
shown in the figure below:

![Figure 1: You can use the formNavigator Gogo shell command to retrieve the available categories and entries for forms.](../../images/form-navigator-gogo-shell.png)

You can read more about using the Gogo shell in the [Felix Gogo Shell Reference Guide](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell).

Next, you can use the information you just retrieved to customize your forms. 

## Customizing Forms with System Settings [](id=customizing-forms-with-system-settings)

Now that you have the form ID(s) and available categories and entries, you can 
configure System Settings to modify form sections.

To locate the Form Navigator System Setting open the *Control Panel* and goto 
*Configuration* &rarr; *System Settings*. Select *Foundation* &rarr; 
*Form Navigator Configuration*.

Form Navigator configurations can optionally be specified with a context. A 
context refers to the scenario in which a form is being viewed. The following 
contexts are available:

- `add`: Denotes that the form is being viewed when new content is being created. 
For example, you could use the `add` context to specify which form sections are 
visible when someone creates a new site.

- `update`: Denotes that the form is being viewed when content is being edited. 
For example, you could use the `update` context to specify which form sections 
are visible when someone edits a site.

There are currently no configuration entries listed. Follow these steps to 
create a new configuration entry:

1.  Click the Add button to bring up the Form Navigator Configuration  
    Form.
    
2.  Enter the Form navigator ID (`sites.form` for example).

3.  Next, enter the form navigator entry keys. Enter an optional 
    context(`add` or `update`), category(if there is one), and a comma-separated 
    list of form navigator entry keys for each section that you want to display. 
    For example, the value below hides the Documents and Media section under the 
    General tab for the Sites form:
    
        general=categorization,custom-fields,details,pages,site-template,site-url
        
    the value below provides a configuration for the Sites form when editing a 
    site:
    
    update.general=categorization,custom-fields,details,documents-and-media
    
    **The order of the entry keys specifies the order in which the sections are 
    displayed in the form, from top to bottom.**
    
    +$$$
    
    **Note:** If you want to hide a category, enter the category, followed by an 
    equal sign with no entry keys. For example, `general=` would hide the 
    general category for the Sites form.
    
    $$$

4.  Save your changes.

Now you know how to to customize your Form Navigator forms!

## Related Topics [](id=related-topics)

[Form Navigator Extensions](/develop/tutorials/-/knowledge_base/7-0/form-navigator)

[Forms and Validation](/develop/tutorials/-/knowledge_base/7-0/forms-and-validation)


