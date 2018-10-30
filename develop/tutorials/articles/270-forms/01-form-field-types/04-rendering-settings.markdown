# Rendering Form Field Settings [](id=rendering-form-field-settings)

Once the settings are added to the class backing the field's settings, make sure
the `*Renderer` can get the settings and update the front-end code.

## Passing Settings to the Renderer Class [](id=passing-settings-to-the-renderer-class)

Send the new configuration settings to the Soy template so they can be displayed
to the end user. Create a new Java class implementing the interface
`DDMFormFieldTemplateContextContributor` and modify the existing class
`*DDMFormFieldRenderer`.

The `DDMFormFieldTemplateContextContributor` interface has a single method
named `getParameters`. It gets the new configuration settings, specific for
a form field type, and sends for the resources that need them, like the Soy
template. To get these settings, create a new class,
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
contains extra information about the form like the user's locale, the HTTP
request and response objects, the portlet namespace, and more (all of its
included properties can be found
[here](https://docs.liferay.com/ce/apps/forms-and-workflow/latest/javadocs/com/liferay/dynamic/data/mapping/render/DDMFormFieldRenderingContext.html)).

The OSGI reference (`@Reference`) provides access to the
`TimeDDMFormFieldTemplateContextContributor` service.

Now the JavaScript component and the Soy template can access the new settings.
Next, update the JavaScript Component so it handles these properties and can use
them, whether passing them to the template context (similar to the `*Renderer`,
only this time for client-side rendering), or using them to configure the
behavior of the JavaScript component itself.

+$$$

**Note:** Remember that the Soy template is used for server side and client
side rendering. By defining the settings you're adding in both the Java Renderer
and the JavaScript Renderer, you're allowing for the best possible user
experience. For example, if a form builder is in the form builder configuring
a form field type, the configuration entered can be directly passed to the
template and become visible in the UI almost instantly. However, when the user
clicks into a form field initially to begin editing, the rendering occurs from
the server side.

$$$

Next configure the JavaScript component to include the new settings.

## Adding Settings to the JavaScript Component [](id=adding-settings-to-the-javascript-component)

The JavaScript component must know about the new settings. First configure them
as attributes of the component:

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
parameters (adds the placeholder to the list of parameters---the `?` indicates
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

