# Creating Form Field Types [](id=creating-form-field-types)

Liferay's Forms application does not contain a dedicated time field
out-of-the-box. For ease of use and to ensure proper time data is collected,
develop a time field and learn how @product@'s field types work at the same
time.

There are several steps involved in creating a form field type:

1.  Creating the Form Field Type's Java class.

2.  Creating the Form Field Type Renderer Java class.

3.  Defining the field's behavior in JavaScript and Soy templates.

+$$$

**Note:** To jump-start your project, use
[BladeCLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli) 
or 
[Liferay Dev Studio](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-liferay-ide). 
There's a Blade template for creating form fields. Using the CLI, enter

    blade create -t form-field -v 7.1 [ADDITIONAL OPTIONS] [PROJECT NAME]

See the [BladeCLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)
documentation for more information, such as the answer to your question "What
are those additional options?"

Using Blade CLI or Liferay Dev Studio, you get a project skeleton with much of
the boilerplate filled in, so you can focus immediately on coding.

$$$

Start by setting up the project's metadata.

## Specifying OSGi Metadata [](id=specifying-osgi-metadata)

First specify the necessary OSGi metadata in a `bnd.bnd` file (see
[here](http://bnd.bndtools.org/chapters/800-headers.html) 
for more information).  Here's what it would look like for a module in a folder
called `dynamic-data-mapping-type-time`:

    Bundle-Name: Liferay Dynamic Data Mapping Type Time
    Bundle-SymbolicName: com.liferay.dynamic.data.mapping.type.time
    Bundle-Version: 1.0.0
    Liferay-JS-Config: /META-INF/resources/config.js
    Web-ContextPath: /dynamic-data-mapping-type-time

Point to the JavaScript configuration file (`config.js`) that defines JavaScript
modules added by your module (you'll get to that later), and set the Web Context
Path to the modules root folder, so your module's resources are made available
upon module activation. 

Next craft the OSGi Component that marks your class as an implementation of
`DDMFormFieldType`. 

## Creating a `DDMFormFieldType` Component [](id=creating-a-ddmformfieldtype-component)

If you're creating a *Time* field type, define the Component at the top of your
`*DDMFormFieldType` class like this:

    @Component(
      immediate = true,
      property = {
        "ddm.form.field.type.description=time-field-type-description",
        "ddm.form.field.type.display.order:Integer=10",
        "ddm.form.field.type.icon=time",
        "ddm.form.field.type.js.class.name=Liferay.DDM.Field.Time",
        "ddm.form.field.type.js.module=liferay-ddm-form-field-time",
        "ddm.form.field.type.label=time-field-type-label",
        "ddm.form.field.type.name=time"
      },
      service = DDMFormFieldType.class
    )

Define the field type's properties (`property=...`) and declare that you're
implementing the `DDMFormFieldType` service (`service=...`).

`DDMFormFieldType` Components can have several properties:

`ddm.form.field.type.description`
: An optional property describing the field type. Its localized value appears in
the form builder's sidebar, just below the field's label.

`ddm.form.field.type.display.order`
: An Integer defining the field type's position in the sidebar.

`ddm.form.field.type.icon`
: The icon for the field type. Choosing one of the 
[Lexicon icons](https://lexicondesign.io/docs/patterns/icons.html) 
makes your form field blends in with the existing form field types.

`ddm.form.field.type.js.class.name`
: The field type's JavaScript class name---the JavaScript file defines the field
type's behavior.

`ddm.form.field.type.js.module`
: The name of the JavaScript module is provided to the Form engine so the module
can be loaded when needed.

`ddm.form.field.type.label`
: The field type's label. Its localized value appears in the form builder's
sidebar.

`ddm.form.field.type.name`
: The field type's name must be unique. Each Component in a field type module
references the field type name, and it's used by OSGi service trackers to filter
the field's capabilities (for example, rendering and validation).

Next code the `*DDMFormFieldType` class.

## Implementing `DDMFormFieldType` [](id=implementing-ddmformfieldtype)

Implementing the field type in Java is made easier because of
`BaseDDMFormFieldType`, an abstract class you can leverage in your code.

After extending `BaseDDMFormFieldType`, override the `getName` method by
specifying the name of your new field type:

    public class TimeDDMFormFieldType extends BaseDDMFormFieldType {

        @Override
        public String getName() {
            return "time";
        }

    }

That's all there is to defining the field type. Next determine how your field
type is rendered.

## Rendering Field Types [](id=rendering-field-types)

Before you get to the frontend coding necessary to render your field type,
there's another Component to define and a Java class to code.

### Implementing a `DDMFormFieldRenderer`

The Component only has one property, `ddm.form.field.type.name`, and then you
declare that you're adding a `DDMFormFieldRenderer` implementation to the OSGi
framework:

    @Component(
        immediate = true, 
        property = "ddm.form.field.type.name=time",
        service = DDMFormFieldRenderer.class
    )

Extend `BaseDDMFormFieldRenderer`, an abstract class implementing the API's only
required method, `render`. The Form engine calls the render method for every
form field type present in a form, and returns the plain HTML of the rendered
field type. The abstract implementation also includes some utility methods.
Here's what the time field's `DDMFormFieldRenderer` looks like:

    public class TimeDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {

        @Override
        public String getTemplateLanguage() {
            return TemplateConstants.LANG_TYPE_SOY;
        }

        @Override
        public String getTemplateNamespace() {
            return "DDMTime.render";
        }

        @Override
        public TemplateResource getTemplateResource() {
            return _templateResource;
        }

        @Activate
        protected void activate(Map<String, Object> properties) {
            _templateResource = getTemplateResource("/META-INF/resources/time.soy");
        }

        private TemplateResource _templateResource;

    }

Set the templating language (Soy closure templates), the template namespace
(`DDMTime`) and name (`render`), and point to the location of the templates
within your module (`/META-INF/resources/time.soy`).

### Writing the Soy Template

Now it's time to write the template you referenced in the renderer class:
`time.soy` in the case of the time field type.

+$$$

**Note:** [Closure templates](https://developers.google.com/closure/templates/)
are a templating system for building UI elements. @product@ developers chose to
build the Forms UI with closure templates because they enable a smooth,
responsive repainting of the UI as a user enters data. With closure templates
there's no need to reload the entire page from the server side when the UI is
updated by the user: only the relevant portion of the page is updated from the
server. This makes for a smooth user experience.

$$$

Create

    src/main/resources/META-INF/resources/time.soy

and populate it with these contents:

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
                        type="text"
                        value="{$displayValue}">
                </div>
            </div>
        </div>
    {/template}

There are four important things to do in the template:

1.  Define the template namespace. The template namespace allows you to define
    multiple templates for your field type by adding the namespace as a prefix.

        {namespace DDMTime}

2.  Set the template that will be called to render the time field. The
    `variant="'time'"` identifies the time field and the `.render` names the
    template in charge to render it. The template comes just below this part and
    is defined through the block `{template .render}...{/template}`.

        /**
        * Defines the delegated template for the time field.
        */
        {deltemplate ddm.field variant="'time'"}
            {call .render data="all" /}
        {/deltemplate}

3.  Describe the template parameters. The template above uses some of the
    parameters as flags to display or hide some parts of the HTML (for example,
    the `$required` parameter). All listed parameters are available by default.

        {@param name: string}
        {@param pathThemeImages: string}
        {@param value: ?}
        {@param visible: bool}
        {@param? dir: string}
        {@param? label: string}
        {@param? predefinedValue: string}
        {@param? readOnly: bool}
        {@param? required: bool}
        {@param? showLabel: bool}
        {@param? tip: string}

4.  Write the template logic (everything encapsulated by the
    `{template .render}...{/template}` block). In the above example the template does
    these things:

    - Checks whether to show the label of the field, and if so, adds it.

    - Checks if the field is required, and adds `asterisk` if it is.

    - Checks if a tip is provided, and displays it.

    - Provides the markup for the time field in the `<input>` tag. In this case
    a text input field is defined.

Once the template is defined, write the JavaScript file modeling your field.

### Writing the JavaScript Files

Create a `time_field.js` file and give it these contents:

    AUI.add(
        'liferay-ddm-form-field-time',
        function(A) {
            var TimeField = A.Component.create(
                {
                    ATTRS: {
                        type: {
                            value: 'time'
                        }
                    },

                    EXTENDS: Liferay.DDM.Renderer.Field,

                    NAME: 'liferay-ddm-form-field-time',

                    prototype: {
                    }
                }
            );

            Liferay.namespace('DDM.Field').Time = TimeField;
        },
        '',
        {
            requires: ['liferay-ddm-form-renderer-field']
        }
    );

The JavaScript above creates a component called `TimeField`. The component
extends `Liferay.DDM.Renderer.Field`, which gives you automatic injection of the
default field parameters. 

Next write the `*.es.js` file to configure the Soy template's rendering.  Create
a file called `time.es.js` and populate it:

    import Component from 'metal-component';
    import Soy from 'metal-soy';
    import templates from './time.soy';
    /**
     * Time Component
     */
    class Time extends Component {}
    // Register component
    Soy.register(Time, templates, 'render');
    if (!window.DDMTime) {
        window.DDMTime = {
        };
    }
    window.DDMTime.render = Time;
    export default Time;

This dictates that the Soy template is called to render the `Time` component.
Then create the `config.js` file:

    ;(function() {
        AUI().applyConfig(
            {
                groups: {
                    'field-time': {
                        base: MODULE_PATH + '/',
                        combine: Liferay.AUI.getCombine(),
                        filter: Liferay.AUI.getFilterConfig(),
                        modules: {
                            'liferay-ddm-form-field-time': {
                                condition: {
                                    trigger: 'liferay-ddm-form-renderer'
                                },
                                path: 'time_field.js',
                                requires: [
                                    'liferay-ddm-form-renderer-field'
                                ]
                            }
                        },
                        root: MODULE_PATH + '/'
                    }
                }
            }
        );
    })();

This file is entirely boilerplate. In fact, if you use Blade CLI to generate a
field type module, you won't need to touch this file. Functionally, it's a
JavaScript file that defines the dependencies of the declared JavaScript
components (`requires...`), and where the files are located (`path...`). The
`config.js` is used by the Alloy loader when it satisfies dependencies for each
JavaScript component. For more information about the Alloy loader see the
[tutorial on its usage](/develop/tutorials/-/knowledge_base/7-0/loading-amd-modules-in-liferay).

![Figure 1: Add your own form field types to the Forms application.](../../../images/forms-time-field-type.png)

If you build and deploy your new field type module, you'll see that you get
exactly what you described in the `time.soy` file: a single text input field. Of
course, that's not what you want! You need a time picker.

## Adding Behavior to the Field [](id=adding-behavior-to-the-field)

To do more than simply provide a text input field, define additional behavior in
the `time_field.js` file. 

To add an AlloyUI timepicker, first specify that your component requires the
`aui-timepicker` in the `requires...` block of the `time_field.js` and
`config.js`:

    {
        requires: ['aui-timepicker','liferay-ddm-form-renderer-field']
    }

Change the default rendering of the field by overwriting the base `render`
logic, instantiating the time picker, and adding the chosen time to the field.
This occurs in the `prototype` block:

    prototype: {
        render: function() {
            var instance = this;

            TimeField.superclass.render.apply(instance, arguments);

            instance.timePicker = new A.TimePicker(
                {
                    trigger: instance.getInputSelector(),
                    popover: {
                        zIndex: 1
                    },
                    after: {
                        selectionChange: A.bind('afterSelectionChange', instance)
                    }
                }
            );
        },

        afterSelectionChange: function(event) {
            var instance = this;

            var time = event.newSelection;

            instance.set('value', time);
        }
    }

Invoke the original render method---it prints markup required by the Alloy time
picker. Then instantiate the time picker, passing the field type input as a
`trigger`. In addition, add a callback method (`afterSelectionChange`) to be
executed `after` the time is chosen in the time picker. This method is
responsible for updating the field's value. See the 
[Alloy documentation for more information](http://alloyui.com/tutorials/timepicker/). 

Now when the field is rendered, there's a real time picker.

![Figure 2: The Alloy UI Timepicker in action.](../../../images/forms-timepicker.png)

Now you know how to create a new field type and define its behavior. Currently,
the field type only contains the default settings it inherits from its
superclasses. If that's not sufficient, create additional settings for your
field type. See the next tutorial to learn how.
