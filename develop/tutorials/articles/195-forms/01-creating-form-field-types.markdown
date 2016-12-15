# Creating form Field Types

The Forms application contains a bunch of useful field types out-of-the-box, and
a lot of configurability. Most use cases will be met with one of the existing
fields. If you're reading this, your use case was probably not met. For example,
perhaps you need a dedicated *time* field. You can use a text field and add a
tip to tell users something like *enter the time in the format `hour:minute`*,
but alas, some users will enter something indecipherable, like *8:88*. Instead
you want to add a *time* field to @product@'s Forms application. 

In this tutorial, learn to create a module that adds a simple field type, with
just a basic input field, to @product@'s Forms application. In the next
tutorial, get the details on configuring the field's behavior, using a
*time-picker* JavaScript component to illustrate the process.

## Anatomy of a Field Type Module

The `dynamic-data-mapping-type-*` modules in @product@'s source code (inside the
*Forms and Workflow* application suite) are good templates to follow when
developing your own field types. For example, look at the directory structure of
the `dynamic-data-mapping-type-paragraph` module in the Forms and Workflow
application suite:

    bnd.bnd
    build.gradle
    src
    └── main
        ├── java
        │   └── com
        │       └── liferay
        │           └── dynamic
        │               └── data
        │                   └── mapping
        │                       └── type
        │                           └── paragraph
        │                               └── internal
        │                                   ├── ParagraphDDMFormFieldRenderer.java
        │                                   ├── ParagraphDDMFormFieldTemplateContextContributor.java
        │                                   ├── ParagraphDDMFormFieldType.java
        │                                   └── ParagraphDDMFormFieldTypeSettings.java
        └── resources
            ├── content
            │   ├── Language.properties
            │   └── Language_xx_XX.properties
            │   └── ...
            └── META-INF
                └── resources
                    ├── config.js
                    ├── paragraph_field.js
                    ├── paragraph.soy
                    └── paragraph.soy.js

+$$$

**Blade CLI Template:** There's a [Blade
CLI](/developer/tutorials/-/knowledge_base/7-0/blade-cli) template you can use
to generate you the project skeleton, some basic configuration, and give you a
head start on dependency management. To generate your form field type project
with Blade CLI, enter

    blade create -t form-field-type -p com.liferay.docs.formfieldtype -c Time ddm-type-time

$$$

Your field type module is nearly identical in structure to those found in
@product@, as diagrammed above. You won't need a `*ContextContributor` class or
the `*TypeSettings` class in your module, and the `*.soy.js` is generated
(<!--when?-->, so these are the Java classes and resources you'll need to
create:
<!--Explain why we don't need the context contributor-->

- `*DDMFormFieldRenderer.java`: 
    - Extends `BaseDDMFormFieldRenderer`, which is an abstract class
        implementing `DDMFormFieldRenderer`
    - `BaseDDMFormFieldRenderer` 
        - gives you a default `render()` implementation and some utility methods. 
        - gives you a `getTemplateResource` utility method used to load your
         templates (you just pass in the path to the template(s)).
        - automatically injects all the default field type configurations as
         template parameters to the template engine.
         <!--Huh?-->
    - Sample of `*DDMFormFieldRenderer`:

            @Component(
                immediate = true, property = "ddm.form.field.type.name=time",
                service = DDMFormFieldRenderer.class
            )
            public class TimeDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {

                @Override
                public String getTemplateLanguage() {
                    return TemplateConstants.LANG_TYPE_SOY;
                }

                @Override
                public String getTemplateNamespace() {
                    return "ddm.time";
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

     which has only one operation the render() method - This operation is called
     to every field type present in a form by the Form engine, the return of
     this method is the plain HTML of the rendered field type. The
     BaseDDMFormFieldRenderer contains the default implementations of the
     render() method, also it contains some utility operations such as
     getTemplateResource(), method that can be used to to load template model
     given a template path. Also it automatically injects all the default field
     type configurations as template parameters to the template engine.

- `*DDMFormFieldRenderer.java`: SEE ABOVE
- `*DDMFormFieldType.java`: Define the form field type in the backend. If you
    extend the abstract class that implements the interface, you automatically
    include the default form configuration options for your form field type. In
    that case all you must do is override the interface's `getName` method. To
    see the default configuration options your form field type will inherit,
    look at the `DefaultDDMFormFieldTypeSettings` class in the
    `dynamic-data-mapping-form-field-type` module.
- `*DDMFormFieldTypeSettings.java`:
- `config.js`:
- `[name-of-field-type]_field.js`:
- `[name-of-field-type].soy`:

In addition to the Java classes, Soy templates, and JavaScript files, @product@
applications will also contain a `bnd.bnd` file to manage the module's metadata,
and a `build.gradle` file to manage its dependencies. This example will follow
those patterns.

+$$$

**Form field types with Blade CLI:** If you want a head start, the basic module
developed in this tutorial can be generated with a single [Blade
CLI](/developer/tutorials/-/knowledge_base/7-0/blade-cli) command:

    blade create -t form-field -p com.liferay.docs.formfield -c Time ddm-type-time

$$$

Start by creating the OSGi Component that marks your class as an implementation
of `DDMFormFieldType`.

## Creating a `DDMFormFieldType` Component

If you're creating a *Time* field type, use this Component at the top of your
`*DDMFormFieldType` class:

    @Component(
      immediate = true,
      property = {
        "ddm.form.field.type.display.order:Integer=8",
        "ddm.form.field.type.icon=star-o",
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

`ddm.form.field.type.display.order`
: Integer that defines the field type's position in the *Choose a Field Type* dialog of the form builder.

`ddm.form.field.type.icon`
: The icon to be used for the field type. Feel free to choose one of the
[Lexicon Experience Language icons](http://liferay.github.io/lexicon/content/icons-lexicon/) so your form field blends in with the existing form field types.

`ddm.form.field.type.js.class.name`
: The field type's JavaScript class name--the JavaScript file is used to define
the field type's behavior.

`ddm.form.field.type.js.module`
: The name of the JavaScript module--provided to the Form engine so the module
can be loaded when needed.

`ddm.form.field.type.label`
: The field type's label. It's localized value appears in the *Choose a Field
Type* dialog.

`ddm.form.field.type.name`
: The name of the field type is used by the `DDMFormValuesFactory` to add all
field types to the forms UI.
<!-- Check this -->

Next code the class itself.

## Implementing `DDMFormFieldType`

Implementing the field type in Java is made easier because of
`BaseDDMFormFieldType`, an abstract implementation you can leverage in your
code.

+$$$

**Note:** If you need to add custom configurations to the form field, you'll
need to implement `DDMFormFieldType` yourself. <!--Provide more info on this-->

$$$

All that's left to do is override the `getName` method by specifying the name of
your new field type:

    public class TimeDDMFormFieldType extends BaseDDMFormFieldType {
        @Override
        public String getName() {
            return "time";
        }
    }

That's all there is to defining the field type. Next determine how your field
type is rendered.

## Rendering Field Types

Before you get to the frontend coding necessary to render your field type,
there's another Component to define and a Java class to code.

The Component only has one property, `ddm.form.field.type.name`, and then you
declare that you're adding a `DDMFormFieldRenderer` implementation to the OSGi
framework:

    @Component(
        immediate = true, 
        property = "ddm.form.field.type.name=time",
        service = DDMFormFieldRenderer.class
    )

There's another abstract class to leverage, this time
`BaseDDMFormFieldRenderer`. It gives you a default implementation of the
`render` method, the only required method for implementing the API. The form
engine calls the render method for every form field type present in a form, and returns the plain HMTL of the rendered field type. The abstract implementation also includes some
utility methods. Here's that the time field's `DDMFormFieldRenderer` looks like:

    public class TimeDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {

        @Override
        public String getTemplateLanguage() {
            return TemplateConstants.LANG_TYPE_SOY;
        }

        @Override
        public String getTemplateNamespace() {
            return "ddm.time";
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

Here you're declaring the templating language (Soy), the template namespace
(`ddm.time`), and pointing to the location of the templates within your module
(`/META-INF/resource/time.soy`). On activation of the Component, the static
resources in you module will be loaded in the OSGi runtime.
<!-- Refine that paragraph if necessary -->

+$$$

**Note:** Soy is the form engine's default templating language. [Say something
intelligent about their benefit and closure templates]

If you want to use a different template language, ...
<!-- What else is there to say about this? Is it even possible? -->

$$$

Now it's time to write the template you reference din the renderer: `time.soy`
in the case of the tiem field type.

Create

src/main/resources/META-INF/resources/time.soy

and populate it with these contents:

    {namespace ddm}
    <!-- Is this read by Portal somewhere?-->

    /**
     * Prints the DDM form time field.
     *
     * @param label
     * @param name
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

            <input class="field form-control" id="{$name}" name="{$name}" {if $readOnly}readonly{/if} type="text" value="{$value}">
        </div>
    {/template}

There are three important things to do in the template:

1.  Define the template namespace. The template namespace allows you to define
    multiple templates for your field type by adding the namespace as a prefix.

2.  Describe the template parameters. The template above uses some of the
    parameters as flags to display or hide some parts of the HTML (for example,
    the `$required` parameter). If you extend `BaseDDMFromFieldRenderer`, all
    the listed parameters are passed by default.
   <!--Might be better to remove them from the template and instead just list
   them. Also, we didn't actually need to do this step because of the abstract
   class doing it for us, but confirm this is true-->
    <!-- A figure with a screenshot of the time configuration sidebar or one of
    the default field config sidebars would be helpful-->

3.  Write the template logic. In the above example the template does these
    things:
    - Checks whether to show the label of the field, and if so, add it.
    - Checks if the field is required, and add `icon-asterisk` if it is.
    - Checks if a tip is provided, and display it.
    - Provides the markup for the time field in the `<input>` tag. In this case
    a text input field is defined.

Once you have your template defined, write the JavaScript file modeling your
field. Call it `time_field.js` and give it these contents:

    AUI.add('liferay-ddm-form-field-time', function(A) {
        var TimeField = A.Component.create({
            ATTRS : {
                type : {
                    value : 'time'
                }
            },

            EXTENDS : Liferay.DDM.Renderer.Field,
            NAME : 'liferay-ddm-form-field-time',

            prototype : {}
        });

        Liferay.namespace('DDM.Field').Time = TimeField;
    }, '', {
        requires : [ 'liferay-ddm-form-renderer-field' ]
    });

The JavaScript above creates a component called `TimeField`. The component
extends `Liferay.DDM.Renderer.Field`. Extending the `Liferay.DDM.Renderer.Field`
component gives you automatic injection of the default field parameters. 

+$$$

**Note:** Extending the Java class `TimeDDMFormFieldRenderer` gave you the
default parameters ...,and extending the JavaScript component
`Liferay.DDM.Renderer.Field` gave you...

$$$

All that's left to do is create the `config.js` file that 

    ;
    (function() {
        AUI().applyConfig({
            groups : {
                'field-time' : {
                    base : MODULE_PATH + '/',
                    combine : Liferay.AUI.getCombine(),
                    modules : {
                        'liferay-ddm-form-field-time' : {
                            condition : {
                                trigger : 'liferay-ddm-form-renderer'
                            },
                            path : 'time_field.js',
                            requires : [ 'liferay-ddm-form-renderer-field' ]
                        },
                        'liferay-ddm-form-field-time-template' : {
                            condition : {
                                trigger : 'liferay-ddm-form-renderer'
                            },
                            path : 'time.soy.js',
                            requires : [ 'soyutils' ]
                        }
                    },
                    root : MODULE_PATH + '/'
                }
            }
        });
    })();

This file is entirely boilerplate, and you'll never need anything different if
you follow the conventions described above. It's a JavaScript file that defines
the dependencies of the declared JavaScript components (`requires...`), and
where the files are located (`path...`). The `config.js` is used by the Alloy
loader when it satisfies dependencies for each JavaScript component. For more
information about the Alloy loader please visit [https://github.com/liferay/liferay-amd-loader#amd-module-loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader)

If you want to do more than simply provide a text input field, this is where you
define the behavior. See the tutorial on [adding behavior to form fields](LINK)
to learn more.












<!-- The template definition involves the definition of 1) the template namespace,
it’s a prefix for all templates you may create for your field type, 2) the
template parameters, it’s where you describe all necessary parameters that will
be used in your template. 3) the actual template logic/definition.

In our example, we can see some of the template parameters being used flags to
display or hide some parts of the HTML, such as the “required”. If this property
is set to true, the markup with the span with the required warning will be
displayed. All these parameters shown in this listing should be passed by the
TimeDDMFormFieldRenderer component. Since in our example we're only using
default configurations, we can expect the BaseDDMFormFieldRenderer will pass all
these parameter automatically, so you can expect to have the proper value when
the rendering is triggered to your field.

The markup for the time field type is a single input text which will carry the
time selected by the user. You can see the input in pink.

All those configuration properties are set up in the field type configuration
dialog, as we mentioned before, we using the Default field type settings which
already provide us a lot of configurations, such as field name, label, required,
help text, etc. We'll see how can we extend the default configurations later on
this tutorial.
-->



