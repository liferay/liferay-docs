# Adding Settings to Form Field Types [](id=adding-settings-to-form-field-types)

Once you develop a 
[Form Field Type](/develop/tutorials/-/knowledge_base/7-1/creating-form-field-types), you
might need to add settings to it. For example, a Time field might accept
different time formats. Here you'll learn how to add settings to form field
types by adding a *mask* and a *placeholder* to the Time field type created in
the previous tutorial. 

+$$$

**Note:** To learn more about using masks with the AUI Timepicker, go
[here](http://alloyui.com/tutorials/timepicker/). 
The mask just sets the format used to display the time choices. Use the 
[strftime format](http://pubs.opengroup.org/onlinepubs/007908799/xsh/strftime.html) 
to pick the mask you want.

$$$

To add settings to form field types, take these steps:

- Write an interface that extends the default field type configuration,
  `DefaultDDMFormFieldTypeSettings`.

- Update the `*FormFieldType` to refer the new interface created on the previous step.

- Update the `*FormFieldRenderer` so it makes the new configuration options
  available to the JavaScript component and/or the Soy template for rendering.

- Update the JavaScript component (defined in `time_field.js` in our example) to
  configure the new settings and their default values.

- Update the Soy template to include settings that must be rendered
  in a form (the placeholder, in our example).

First craft the interface that controls your field's settings.

## Extending the Default Type Settings [](id=extending-the-default-type-settings)

To add type settings, you need a `*TypeSettings` class that extends
`DefaultDDMFormFieldTypeSettings`. Since this example works with a Time field
type, call it `TimeDDMFormFieldTypeSettings`.

This class sets up the *Field Type* configuration form.

![Figure 1: Like your custom field types, the text field type's settings are configured in a Java interface.](../../../images/forms-text-settings.png)

Here's what it looks like:

    package com.liferay.dynamic.data.mapping.type.time;

    import com.liferay.dynamic.data.mapping.annotations.DDMForm;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
    import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;

    @DDMForm
    @DDMFormLayout(
        paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
        value = {
            @DDMFormLayoutPage(
                title = "%basic",
                value = {
                    @DDMFormLayoutRow(
                        {
                            @DDMFormLayoutColumn(
                                size = 12,
                                value = {
                                    "label", "required", "tip", "mask",
                                    "placeholder"
                                }
                            )
                        }
                    )
                }
            ),
            @DDMFormLayoutPage(
                title = "%properties",
                value = {
                    @DDMFormLayoutRow(
                        {
                            @DDMFormLayoutColumn(
                                size = 12,
                                value = {
                                    "dataType", "name", "showLabel", "repeatable",
                                    "type", "validation", "visibilityExpression"
                                }
                            )
                        }
                    )
                }
            )
        }
    )
    public interface TimeDDMFormFieldTypeSettings
        extends DefaultDDMFormFieldTypeSettings {

        @DDMFormField(label = "%mask", predefinedValue="%I:%M %p")
        public String mask();

        @DDMFormField(label = "%placeholder-text")
        public String placeholder();
        
    }

Most of the work you need to do is in the class's annotations. 

This class sets up a dynamic form with all the settings the form field type
needs. The form layout presented here gives your form the look and feel of a
native form field type. See the note below for more information on the DDM
annotations used in this form.

One thing to note is that all the default settings must be present in your
settings form. Note the list of settings present for each tab (each
`@DDMFormLayoutPage`) above. If you must make one of the default settings
unusable in the settings form for your field type, configure a *hide rule* for
the field. Form field rules are configured using the `@DDMFormRule` annotation.

The interface extends `DefaultDDMFormFieldTypeSettings`. That's why the default
settings can be used in the class annotation without setting them up in the
class, as was necessary for the mask and placeholder.

+$$$

**DDM Annotations:** The `@DDMForm` annotation on this class allows the form engine to
convert the interface definition into a dynamic form. This makes it really
intuitive to lay out your settings form. 

For now, here are brief explanations for the annotations used in the above
example:

`@DDMForm`
: Instantiates a new `DDMForm`. Creates a dynamic form from the annotation.

`@DDMFormLayout`
: Takes two variables: `paginationMode` and `value`. The pagination mode is a
String that controls how the layout pages are displayed. The `paginationMode`
can be `TABBED_MODE`, `SINGLE_PAGE_MODE`, `SETTINGS_MODE`, or `WIZARD_MODE`.
Under `value`, specify any `@DDMFormLayoutPage`s that you want to use.

`@DDMFormLayoutPage` 
: The sections of the type settings form. It takes two
variables: `title` and `value`, where title is a String value that names the
section of the form and value is one or more `@DDMFormLayoutRow`s.

The layout page titles `%basic` and `%properties` are common to all of
@product@'s field types, but you can use whatever titles you want. To change the
title of a layout page, specify the title in the annotation properties (`title
= "%advanced"`, for example), and then create a new key in the language
resources files. For example, use `advanced=Advanced` in the
`Language.properties`.

`@DDMFormLayoutRow`
: Lay out the number of columns you want in the row. Most settings
forms have just one row and one column.

`@DDMFormLayoutColumn`
: Lay out the columns your settings form needs. Most settings forms
have one row and one column. Each column accepts two argument, `size` and
`value`.

`@DDMFormField`
: Add new fields to the settings form. In this example, the `mask` and
`placeholder` settings are configured with this annotation. Don't forget to add
the settings language keys (`mask` and `placeholder-text`) to the language
resources files.

$$$

Once your `*TypeSettings` class is finished, update the `*Type` class for your
form field type.

## Updating the Type Class [](id=updating-the-type-class)

The class `TimeDDMFormFieldType` currently has one method, `getName`, returning
the name of the current form field. Add a new method to reference
`TimeDDMFormFieldTypeSettings` that holds the specific settings of the Time
field. This method already exists in the base class (`BaseDDMFormFieldType`), so
override it:

    @Override
	public Class<? extends DDMFormFieldTypeSettings>
	    getDDMFormFieldTypeSettings() {

	    return TimeDDMFormFieldTypeSettings.class;
	}

Next, render new Time field settings.

