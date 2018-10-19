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
: The name of the JavaScript module provided to the Form engine so the module
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
