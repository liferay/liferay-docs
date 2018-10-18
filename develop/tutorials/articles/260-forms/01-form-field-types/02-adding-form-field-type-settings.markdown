# Adding Settings to Form Field Types [](id=adding-settings-to-form-field-types)

Once you develop a 
[Form Field Type](/develop/tutorials/-/knowledge_base/7-1/creating-form-field-types), 
you might need to add settings to it. For example, a Time field could be
configured to accept different time formats. Here you'll learn how to add
settings to form field types by adding a *mask* and a *placeholder* to the Time
field type created in the previous tutorial. 

+$$$

**Note:** To learn more about using masks with the AUI Timepicker, go
[here](http://alloyui.com/tutorials/timepicker/). 
The mask just sets the format used to display the time choices. Use the 
[strftime format](http://pubs.opengroup.org/onlinepubs/007908799/xsh/strftime.html) 
to pick the mask you want.

$$$

To add settings to form field types, you'll use these steps:

- Write an interface that extends the default field type configuration,
  `DefaultDDMFormFieldTypeSettings`.

- Update the `*FormFieldType` to refer the new interface created on the previous step.

- Update the `*FormFieldRenderer` so it makes the new configuration options
  available to the JavaScript component and/or the Soy template for rendering.

- Update the JavaScript component (defined in `time_field.js` in our example) to
  configure the new settings and their default values.

- Update the Soy template to include any settings that need to be rendered
  in a form (the placeholder, in our example).

First craft the interface that controls what settings your field has.

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

Would you look at that! Most of the work you need to do is in the class's
annotations. 

This class sets up a dynamic form with all the settings the form field type
needs. The form layout presented here gives your form the look and feel of a
native form field type. See the note below for more information on the DDM
annotations used in this form.

One thing to note is that all the default settings must be present in your
settings form. Note the list of settings present for each tab (each
`@DDMFormLayoutPage`) above. If you need to make one of the default settings
unusable in the settings form for your field type, configure a *hide rule* for
the field. Form field rules are configured using the `@DDMFormRule`
annotation. More information on configuring form rules will be written soon.

The interface extends `DefaultDDMFormFieldTypeSettings`. That's why the default
settings are available to use in the class annotation, without setting them up
in the class, as was necessary for the mask and placeholder.

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
String that controls how the layout pages are displayed. The `pagionationMode`
can be `TABBED_MODE`, `SINGLE_PAGE_MODE`, `SETTINGS_MODE`, or `WIZARD_MODE`.
Under `value`, specify any `@DDMFormLayoutPage`s that you want to use.

`@DDMFormLayoutPage`
: The sections of the type settings form. Takes two variables: `title` and
`value`, where title is a String value that names the section of the form and
value is one or more `@DDMFormLayoutRow`s.

The layout page titles `%basic` and `%properties` are common to all of
@product@'s field types, but you can use whatever titles you want. To change the
title of a layout page, specify the title in the annotation properties (`title =
"%advanced"`, for example), then create a new key in the language resources
files. For example, use `advanced=Advanced` in the `Language.properties`.

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

## Updating the Type Class

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

Next, update the `*Renderer` class so it can get the new Time field settings.

## Updating the Renderer Class [](id=updating-the-renderer-class)

Send the new configuration settings to the Soy template so they can be displayed
to the end user. Create a new Java class implementing the interface
`DDMFormFieldTemplateContextContributor` and modify the existing class
`*DDMFormFieldRenderer`.

The `DDMFormFieldTemplateContextContributor` interface has a single method
named `getParameters`. It gets the new configuration settings, specific for a
form field type, and sends for the resources which need them, like the Soy
template.  To get these settings, create a new class,
`TimeDDMFormFieldTemplateContextContributor`. First create its OSGI component
annotation and the class declaration:

    @Component(
        immediate = true,
        property = "ddm.form.field.type.name=time",
        service = {
            DDMFormFieldTemplateContextContributor.class,
            TimeDDMFormFieldTemplateContextContributor.class
        }
    )
    public class TimeDDMFormFieldTemplateContextContributor
        implements DDMFormFieldTemplateContextContributor {

Then override `getParameters` to get the new configurations settings,
`placeholder` and `mask`:

        @Override
        public Map<String, Object> getParameters(
            DDMFormField ddmFormField,
            DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

            Map<String, Object> parameters = new HashMap<>();

            parameters.put(
                "placeholder", (String)ddmFormField.getProperty("placeholder"));
            parameters.put("mask", (String)ddmFormField.getProperty("mask"));

            return parameters;
        }

    }

Now pass the configuration settings to the template with a new method,
`populateOptionalContext`, in `TimeDDMFormFieldRenderer`:

    @Override
	protected void populateOptionalContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		Map<String, Object> parameters =
			timeDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		template.putAll(parameters);
	}

	@Reference
	protected TimeDDMFormFieldTemplateContextContributor
		timeDDMFormFieldTemplateContextContributor;

The `populateOptionalContext` method takes three parameters: The template
object, the `DDMFormField`, and the `DDMFormFieldRenderingContext`. The
`DDMFormField` represents the definition of the field type instance: you can use
this object to access the configurations set for the field type (the mask and
placeholder settings in our case). The `DDMFormFieldRenderingContext` object
contains extra information about the form such as the user's locale, the HTTP
request and response objects, the portlet namespace, and more (all of its
included properties can be found
[here](https://docs.liferay.com/ce/apps/forms-and-workflow/latest/javadocs/com/liferay/dynamic/data/mapping/render/DDMFormFieldRenderingContext.html)).

The OSGI reference (`@Reference`) provides access to
the `TimeDDMFormFieldTemplateContextContributor` service.

Now the JavaScript component and the Soy template can access the new settings.
Next, update the JavaScript Component so it handles these properties and can use
them, whether passing them to the template context (similar to the `*Renderer`,
only this time for client-side rendering), or using them to configure the
behavior of the JavaScript component itself.

+$$$

**Note:** Remember that the Soy template is used for server side and client
side rendering. By defining the settings you're adding in both the Java Renderer
and the JavaScript Renderer, you're allowing for the best possible user
experience. For example, if a form builder is in the form builder, configuring a
form field type, the configuration they enter can be directly passed to the
template, and become visible in the UI, almost instantly. However, when the user
clicks into a form field initially to begin editing, the rendering occurs from
the server side.

$$$

Next configure the JavaScript component to include the new settings.

## Adding Settings to the JavaScript Component [](id=adding-settings-to-the-javascript-component)

The JavaScript component needs to know about the new settings. First configure
them as attributes of the component:

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

The mask setting has a default value of `%I:%M %p`, and the placeholder is
blank. Now that the new settings are declared as attributes of the component,
make the JavaScript component pass the placeholder configuration to the Soy
template on the client side. Just like in the Java renderer, pass the
placeholder configuration to the template context. In this case, override the
`getTemplateContext()` method to pass in the placeholder configuration. Add this
to the `prototype` section of the JavaScript component definition:

    getTemplateContext: function() {
        var instance = this;

        return A.merge(
            TimeField.superclass.getTemplateContext.apply(instance, arguments),
            {
                placeholder: instance.get('placeholder')
            }
        );
    },


Then in the component's render method, add the mask as an 
[attribute of the AUI Timepicker](http://alloyui.com/api/classes/A.TimePicker.html#attr_mask)
using `mask: instance.get('mask')`. 

    render: function() {
        var instance = this;

        TimeField.superclass.render.apply(instance, arguments);

        instance.timePicker = new A.TimePicker(
            {
                trigger: instance.getInputSelector(),
                mask: instance.get('mask'),
                popover: {
                    zIndex: 1
                },
                after: {
                    selectionChange: A.bind('afterSelectionChange', instance)
                }
            }
        );
    },

Now the field type JavaScript component is configured to include the settings.
All you have left to do is to update the Soy template so the placeholder can be
rendered in the form with the time field.

## Updating the Soy Template [](id=updating-the-soy-template)

Add the placeholder setting to your Soy template's logic.

The whole template is included below, but the only additions are in the list of
parameters (adds the placeholder to the list of parameters--the `?` indicates
that the placeholder is not required), and then in the `<input>` tag, where you
use the parameter value to configure the placeholder HTML property with the
proper value.

    {namespace DDMTime}

    /**
    * Defines the delegated template for the time field.
    */
    {deltemplate ddm.field variant="'time'"}
        {call .render data="all" /}
    {/deltemplate}

    /**
    * Prints the time field.
    */
    {template .render}
        {@param name: string}
        {@param pathThemeImages: string}
        {@param value: ?}
        {@param visible: bool}
        {@param? placeholder: string}
        {@param? dir: string}
        {@param? label: string}
        {@param? predefinedValue: string}
        {@param? readOnly: bool}
        {@param? required: bool}
        {@param? showLabel: bool}
        {@param? tip: string}

        {let $displayValue: $value ? $value : $predefinedValue ? $predefinedValue : '' /}

        <div class="form-group {$visible ? '' : 'hide'} liferay-ddm-form-field-time"
            data-fieldname="{$name}">
            {if $showLabel or $required}
                <label for="{$name}">
                    {if $showLabel}
                        {$label}{sp}
                    {/if}

                    {if $required}
                        <svg aria-hidden="true" class="lexicon-icon lexicon-icon-asterisk reference-mark">
                            <use xlink:href="{$pathThemeImages}/lexicon/icons.svg#asterisk" />
                        </svg>
                    {/if}
                </label>
            {/if}

            {if $showLabel}
                {if $tip}
                    <span class="form-text">{$tip}</span>
                {/if}
            {/if}

            <div class="input-group">
                <div class="input-group-item">
                    <input class="field form-control"
                        {if $dir}dir="{$dir}"{/if}
                        {if $readOnly}disabled{/if}
                        id="{$name}"
                        name="{$name}"
                        placeholder="{$placeholder}"
                        type="text"
                        value="{$displayValue}">
                </div>
            </div>
        </div>
    {/template}

The mask is not needed in the Soy template because it's only used in the
JavaScript for configuring the behavior of the timepicker. You don't need the
dynamic rendering of the Soy template to take the mask setting and configure it
in the form. The mask set by the form builder is captured in the rendering of
the timepicker itself.

Now when you build the project and deploy your time field, you have a fully
developed *time* form field type, complete with the proper JavaScript behavior
and with additional settings.
