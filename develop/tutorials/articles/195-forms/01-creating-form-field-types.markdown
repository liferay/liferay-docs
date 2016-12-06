# Creating form Field Types



There

The example code below mimics the preexisting field type in @product@, using Soy
templates and JavaScript for generating the UI of the form field.

## Anatomy of a Field Type Module

The modules you build to create additional field types will have the same
structure as the preexisting field type modules in @product@'s source code. For
example, look at the directory structure of the
`dynamic-data-mappingtype-paragraph` module in the Forms and Workflow
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

You won't need a `*ContextContributor` class in your module, so these are the
Java classes and resources you'll need to create:
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

- `*DDMFormFieldRenderer.java`: 
- `*DDMFormFieldType.java`:
- `*DDMFormFieldTypeSettings.java`:
- `config.js`:
- `[name-of-field-type]_field.js`:
- `[name-of-field-type].soy`:
- `[name-of-field-type].soy.js`:

Many @product@ applications will also contain a `bnd.bnd` file to manage the
module's metadata, and a `build.gradle` file to manage its dependencies. This
example will follow those patterns.

## Creating the Field Type


### Creating a `DDMFormFieldType` Component

If you're creating a *Time* field type, use this Component:

    @Component(
      immediate = true,
      property = {
        "ddm.form.field.type.display.order:Integer=9",
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
: The icon to be used for the field type <!-- Available icons?-->

`ddm.form.field.type.js.class.name`
: The field type's JavaScript class name--the JavaScript file is used to define
the field type's behavior.

`ddm.form.field.type.js.module`
: The name of the JavaScript module--provided to the Form engine so the module
can be loaded when needed.

<!--What are these used for?-->
`ddm.form.field.type.label`
: The label to be used for the field type

`ddm.form.field.type.name`
: The name of the field type

Next code the class itself.

### Implementing `DDMFormFieldType`

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

That's all there is to defining the field type in the backend. Next determine
how your field type is rendered.

### Rendering Field Types

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

3.  Write the template logic. In the above example the template does these
    things:
    - Check




The template definition involves the definition of 1) the template namespace,
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




