# Adding Settings to Form Field Types

Once you develop a [Form Field
Type](/developer/tutorials/-/knowledge_base/7-0/creating-form-field-types), you
might want to add settings to it. To illustrate this, read here to learn to  add
a *mask* and a *placeholder* to the Time field created in the previous tutorial. 

+$$$

**Note:** 
To learn more about using masks with the AUI Timepicker, go
[here](http://alloyui.com/tutorials/timepicker/). The mask just sets the format
the timepicker uses to display the choices. Use the [strftime
format](http://pubs.opengroup.org/onlinepubs/007908799/xsh/strftime.html) to
pick the mask you want.

$$$

To add settings to form field types, use these steps:

- Write an interface that extends the default field type configuration,
    `DefaultDDMFormFieldTypesettings`.
- Update the `time_field.js` to add the new configurations and their default
    values and a default value to the `TimeField` component.
- Update the `FormFieldRenderer` so it passes the new configuration options to
    the Soy template.
- Update the Soy Template to include the new configuration options, and any
    logic controlling their display.

Get started by writing the interface that controls what settings your field will
use.

## Extending the Default Type Settings

To add type settings, you need a `*TypeSettings` class that extends
`DefaultDDMFormFieldTypeSettings`. Since this example works with a Time field
type, call it `TimeDDMFormFieldTypeSettings`.

This class sets up the *Add [Field Type]* configuration form.

![Figure x: Like your custom field types, the text field type's settings are configured in a Java interface.](../../../images/forms-text-settings.png)

    package com.liferay.docs.ddm.time;

    import ...

    @DDMForm
    @DDMFormLayout(
        paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
        value = {
            @DDMFormLayoutPage(
                title = "basic",
                value = {
                    @DDMFormLayoutRow(
                        {
                            @DDMFormLayoutColumn(
                                size = 12,
                                value = {"label", "required", "tip", "mask", "placeholder"}
                            )
                        }
                    )
                }
            ),
            @DDMFormLayoutPage(
                title = "properties",
                value = {
                    @DDMFormLayoutRow(
                        {
                            @DDMFormLayoutColumn(
                                size = 12,
                                value = {
                                    "predefinedValue", "visibilityExpression",
                                    "fieldNamespace", "indexType", "localizable",
                                    "readOnly", "dataType", "type", "name",
                                    "showLabel", "repeatable"
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

        @DDMFormField(label = "%placeholder")
        public String placeholder();

    }

Would you look at that! Most of the work you need to do is in the class's
annotations. 

In this class you're setting up a dynamic form with all the settings your form
will need. The form layout presented here will give your form the look and feel
of a native form field type.

+$$$

**DDM Annotations:** The `@DDMForm` annotation on this class allows the form engine to
convert the interface definition into a dynamic form. This makes it really
intuitive to lay out your settings form. More details on how DDM form
annotations work and their use cases for annotatively creating forms can be
found [here--coming soon](LINK). 

For now, here are brief explanations for the annotations used in the above
example:

`@DDMForm`
: Instantiates a new `DDMForm`. Creates a dynamic form from the annotation. Adds
a new DDL Record Set to your Liferay database. <!--I don't think it does, in
fact.-->

`@DDMFormLayout`
: Takes two variables: `paginationMode` and `value`. The pagination mode is a
String that controls how the layout pages are displayed. The `pagionationMode`
can be `TABBED_MODE`, `SINGLE_PAGE_MODE`, `SETTINGS_MODE`, or `WIZARD_MODE`.
Under `value`, specify any `@DDMFormLayoutPage`s that you want to use.

`@DDMFormLayoutPage`
: The sections of the type settings form. Takes two variables: `title` and
`value`, where title is a String value that names the section of the form, and
value is one or more `@DDMFormLayoutRow`s.

**Note:** The title of the layout pages are `basic` and `properties` for all of
@product@'s field types: in future versions of the Forms application, the
localized value of the key you specify here will be the heading for the form
section (the layout page is a section of the form). In the current (at the time
of this writing, DE DXP SP1 and CE 7.0 GA4) version of the application, these
will not be displayed.  To remain consistent with the Forms application's
default fields, it's best to follow the standard approach and use `basic` and
`properties`. 

`@DDMFormLayoutRow`
: Use this to lay out the nuber of columns you want in the row. Most settings
forms will have just one row and one column.

`@DDMFormLayoutColumn`
: Use this to lay out the number of columns your settings form should have. Most
settings forms have only one row and one column.

`@DDMFormField`
: Use this annotation to add new fields to the settings form. In this example,
these will be the `mask` and `placeholder` settings.

$$$

One thing to take note of is that all of the default settings must be present in
your settings form. Note the list of settings present for each tab above. If you
need to make one of the default settings unusable, you'd need to set up a *hide
rule* for the field. Form field rules can be configured using the
`@DDMFormFieldRule` annotation. More information on configuring rules will be
written soon.

Once your `*TypeSettings` class is finished, move on to update the JavaScript
component for your form field type.

## Adding Settings to the JavaScript Component

The JavaScript component needs to know about the new settings. First configure
them in the attribute of the component:


    ATTRS: {
        mask: {
            value: '%I:%M %p'
        },
        placeholder: {
            value: ''
        },
        type: {
            value: 'time'
        }
    },

How do you change the JavaScript component to pass the placeholder configuration
to the soy template on the client side? Like in the Java code, override the
`getTemplateContext()` method to pass the extra configuration. Add this to the
`prototype` section of the JavaScript component definition:

    getTemplateContext: function() {
        var instance = this;

    return A.merge(
        TimeField.superclass.getTemplateContext.apply(instance, arguments),
            {
            placeholder: instance.get('placeholder')
            }
        );
    },

## Sending Markup to the Soy Template

To send the new configuration settings to the Soy template so they can be
displayed to the end user, you need to modify the `*DDMFormFieldRenderer` and
add a `*DDMFormFieldTemplateContextContributor`. 

Add this to the `TimeDDMFormFieldRenderer`:



## Updating the Soy Template

After all that, now you can add the settings to your Soy template's logic.

The whole template is pasted below, but the only additions are in the commented
section (adds the placeholder to the list of parameters), and then in the
`<input>` tag, where you use the parameter value to configure the placeholder
HTML property with the proper value.

{namespace ddm}

/**
 * Prints the DDM form time field.
 *
 * @param label
 * @param name
 * @param placeholder
 * @param readOnly
 * @param required
 * @param showLabel
 * @param tip
 * @param value
 */
{template .time autoescape="deprecated-contextual"}
    <div class="form-group liferay-ddm-form-field-time" data-fieldname="{$name}">
        {if $showLabel}
            <label class="control-label">
                {$label}

                {if $required}
                    <span class="icon-asterisk text-warning"></span>
                {/if}
            </label>

            {if $tip}
                <p class="liferay-ddm-form-field-tip">{$tip}</p>
            {/if}
        {/if}

        <input class="field form-control" id="{$name}" name="{$name}" placeholder="{$placeholder}" {if $readOnly}readonly{/if} type="text" value="{$value}">
    </div>
{/template}
