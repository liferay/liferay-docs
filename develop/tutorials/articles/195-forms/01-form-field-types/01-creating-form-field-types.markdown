# Creating Form Field Types [](id=creating-form-field-types)

Liferay's Forms application does not contain a dedicated time field
out-of-the-box. For ease of use and to ensure proper data is collected, you can
develop a time field and learn how @product@'s field types work at the same
time.

There are several steps involved in creating a form field type:

1. Specify the OSGi metadata
2. Configure your build script and dependencies
3. Create a `DDMFormFieldType` component
4. Implement a `DDMFormFieldType`
5. Render the field type

+$$$

**Note:** To avoid manually creating your own project, use
[BladeCLI](/developer/tutorials/-/knowledge_base/7-0/blade-cli). If you have
Blade CLI on your machine, there's a template for creating form fields you can
leverage using the following command syntax:

    blade create -t form-field [ADDITIONAL OPTIONS] [PROJECT NAME]

See the [BladeCLI](/developer/tutorials/-/knowledge_base/7-0/blade-cli)
documentation for more information, such as the answer to yuor question, What
are those additional options?"

Using Blade CLI, you get a project skeleton with much of the boilerplate
filled in, and you can focus on coding without delay.

$$$

Start by setting up the project's metadata.

## Specifying OSGi Metadata [](id=specifying-osgi-metadata)

First specify the necessary OSGi metadata in a `bnd.bnd` file (see
[here](https://www.google.com/url?q=http://bnd.bndtools.org/chapters/800-headers.html&sa=D&ust=1484604445554000&usg=AFQjCNHA_3LxtNcRBblsT62vG5MoEvs6PQ)
for more information). Here's what it would look like for a module in a folder
called `dynamic-data-mapping-type-time`:

    Bundle-Name: Liferay Dynamic Data Mapping Type Time
    Bundle-SymbolicName: com.liferay.dynamic.data.mapping.type.time
    Bundle-Version: 1.0.0
    Liferay-JS-Config: /META-INF/resources/config.js
    Web-ContextPath: /dynamic-data-mapping-type-time

First name the bundle with a reader-friendly `Bundle-Name` and a unique
`Bundle-SymbolicName` (it's common to use the root package of your module's Java
classes), then set the version. Point to the JavaScript configuration file
(`config.js`) that defines JavaScript modules added by your module (you'll get
to that later), and set the Web Context Path so your module's resources are made
available upon module activation. 

Next add your dependencies to a `build.gradle` file.

## Specifying Dependencies [](id=specifying-dependencies)

If you're using Gradle to manage your dependencies (as @product@ modules do),
add this to your `build.gradle` file:

    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins", version: "3.0.23"
        }

        repositories {
            mavenLocal()

            maven {
                url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
            }
        }
    }

    apply plugin: "com.liferay.plugin"

    dependencies {
        compile group: "com.liferay", name: "com.liferay.dynamic.data.mapping.api", version: "3.2.0"
        compile group: "com.liferay", name: "com.liferay.dynamic.data.mapping.form.field.type", version: "2.0.5"
        compile group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        compileOnly group: "org.osgi", name: "org.osgi.compendium", version: "5.0.0"
    }

    repositories {
        mavenLocal()

        maven {
            url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
        }
    }

    classes {
        dependsOn buildSoy
    }

    transpileJS {
        soySrcIncludes = ""
        srcIncludes = "**/*.es.js"
    }

    wrapSoyAlloyTemplate {
        enabled = true
        moduleName = "liferay-ddm-form-field-time-template"
        namespace = "ddm"
    }

Along with the regular Java `dependencies`, there's some JavaScript
dependency configuration you need to include here. It's all boilerplate and can
be copied directly into your module's `build.gradle` if you follow the
conventions presented here. 

Next craft the OSGi Component that marks your class as an implementation of
`DDMFormFieldType`. 

## Creating a `DDMFormFieldType` Component [](id=creating-a-ddmformfieldtype-component)

If you're creating a *Time* field type, define the Component at the top of your
`*DDMFormFieldType` class like this:

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
: The icon to be used for the field type. Choosing one of the 
[Lexicon Experience Language icons](http://liferay.github.io/lexicon/content/icons-lexicon/) makes your form
field blends in with the existing form field types.

`ddm.form.field.type.js.class.name`
: The field type's JavaScript class name--the JavaScript file is used to define
the field type's behavior.

`ddm.form.field.type.js.module`
: The name of the JavaScript module--provided to the Form engine so the module
can be loaded when needed.

`ddm.form.field.type.label`
: The field type's label. Its localized value appears in the *Choose a Field
Type* dialog.

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
engine calls the render method for every form field type present in a form, and
returns the plain HMTL of the rendered field type. The abstract implementation
also includes some utility methods. Here's what the time field's
`DDMFormFieldRenderer` looks like:

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

Here you're setting the templating language (Soy closure templates), the
template namespace (`ddm.time`), and pointing to the location of the templates
within your module (`/META-INF/resource/time.soy`).

+$$$

**Note:** [Closure templates](https://developers.google.com/closure/templates/)
are a templating system for building UI elements. @product@ developers chose to
build the Forms UI with closure templates because they enable a smooth,
responsive repainting of the UI as a user enters data. With closure templates
there's no need to reload the entire page from the server side when the UI is
updated by the user: only the relevant portion of the page is updated from the
server. This makes for a smooth user experience.

$$$

Now it's time to write the template you referenced in the renderer: `time.soy`
in the case of the time field type.

Create

    src/main/resources/META-INF/resources/time.soy

and populate it with these contents:

    {namespace ddm}

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

        {namespace ddm}

2.  Describe the template parameters. The template above uses some of the
    parameters as flags to display or hide some parts of the HTML (for example,
    the `$required` parameter). If you extend `BaseDDMFormFieldRenderer`, all
    the listed parameters are passed by default.
    <!-- A figure with a screenshot of the time configuration sidebar or one of
    the default field config sidebars would be helpful-->


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

3.  Write the template logic (everything encapsulated by the
    `{template}...{/template}` block). In the above example the template does
    these things:
    - Checks whether to show the label of the field, and if so, adds it.
    - Checks if the field is required, and adds `icon-asterisk` if it is.
    - Checks if a tip is provided, and displays it.
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
extends `Liferay.DDM.Renderer.Field`, which gives you automatic injection of the
default field parameters. 

All that's left to do is create the `config.js` file:

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
you follow the conventions described above. In fact, if you use Blade CLI to
generate a field type module, you won't need to modify anything in this file.
So what is the `config.js` file for? It's a JavaScript file that defines the
dependencies of the declared JavaScript components (`requires...`), and where
the files are located (`path...`). The `config.js` is used by the Alloy loader
when it satisfies dependencies for each JavaScript component. For more
information about the Alloy loader see the [tutorial on its
usage](/developer/tutorials/-/knowledge_base/7-0/liferay-amd-module-loader).

[![Figure 1: Add your own form field types to the Forms application.](../../../images/forms-time-field-type.png)

If you build and deploy your new field type module, you'll see that you get
exactly what you described in the `time.soy` file: a single text input field. Of
course, that's not what you want! You need a time picker.

## Adding Behavior to the Field [](id=adding-behavior-to-the-field)

If you want to do more than simply provide a text input field, define the
behavior in the `time_field.js` file. To add an AlloyUI timepicker, first
specify that your component requires the `aui-timepicker` in the `requires...`
block: 

    {
        requires: ['aui-timepicker','liferay-ddm-form-renderer-field']
    }

Since you're now changing the default rendering of the field, overwrite the base
`render` logic and instantiate the time picker. This occurs in the `prototype`
block:

    prototype: {
        render: function() {

            var instance = this;

            TimeField.superclass.render.apply(instance, arguments);

            instance._timePicker = new A.TimePicker(
                {
                    trigger: instance.getInputSelector(),
                    popover: {
                        zIndex: 1
                    }
                }
            );
        }

Invoke the original render method--it prints markup required by the Alloy time
picker. Then instantiate the time picker, passing the field type input as a
`trigger`. See the [Alloy documentation for more
information](http://alloyui.com/tutorials/timepicker/). 

Now when the field is rendered, there's a real time picker.

![Figure 2: The Alloy UI Timepicker in action.](../../../images/forms-timepicker.png)

Now you know how to create a new field type and define its behavior. Currently,
the field type only contains the default settings it inherits from its
superclasses. If that's not sufficient, create additional settings for your
field type. See the next tutorial (not yet written) to learn how.
