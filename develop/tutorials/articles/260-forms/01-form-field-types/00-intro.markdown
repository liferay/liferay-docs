# Form Field Types [](id=form-field-types)

The Forms application contains many highly configurable field types
out-of-the-box. Most use cases will be met with one of the existing field types. 

![Figure 1: The Forms application has useful out-of-the-box field types, but you can add your own if you need to.](../../../images/forms-field-types.png)

If you're reading this, however, your use case probably wasn't met with the
default field types. For example, perhaps you need a color picker field. You
could create a select field that lists the color options, but some users don't
know that *gamboge* is the color of spicy mustard (maybe a little darker), and
anyway, seeing colors is much more interesting than listing them. Another
example is a dedicated *time* field. You can use a text field and add a tip to
tell users something like *enter the time in the format `hour:minute`*, but some
users will still enter something indecipherable, like *8:88*. Instead, add a
*time* field to @product@'s Forms application. You can think of other uses where
it's best to break free of the mold of existing field types and create your own
that serve your needs best. Keep reading to find out how.

In these tutorials, learn to 

- create a module that adds a *Time* form field type with a timepicker

- add custom configuration options to your field types

<!--    UPDATE PROJECT ON LDN    -->
<!-- +$$$

**Example project:** The source code for the example *time* project developed in
these tutorials can be downloaded for your convenience. Click
[here](https://dev.liferay.com/documents/10184/656312/Field+Type+Module+Source/de6203af-1bae-4c59-9f90-0ab5e58c5894) to begin downloading the source code zip file.

$$$
-->

Before getting started, learn the structure of a form field type. 

## Anatomy of a Field Type Module [](id=anatomy-of-a-field-type-module)

The `dynamic-data-mapping-type-*` modules in @product@'s source code are good
templates to follow when developing your own field types. For example, look at
the directory structure of the `dynamic-data-mapping-type-paragraph` module
(version 3.0.0):

    .babelrc
    .npmbundlerrc
    bnd.bnd
    build.gradle
    package-lock.json
    package.json
    src
    └── main
        ├── java
        │   └── com
        │       └── liferay
        │           └── dynamic
        │               └── data
        │                   └── mapping
        │                       └── type
        │                           └── paragraph
        │                               └── internal
        │                                   ├── ParagraphDDMFormFieldRenderer.java
        |                                   ├── ParagraphDDMFormFieldTemplateContextContributor.java
        │                                   ├── ParagraphDDMFormFieldType.java
        │                                   └── ParagraphDDMFormFieldTypeSettings.java
        └── resources
            ├── content
            │   ├── Language.properties
            │   └── Language_xx_XX.properties
            │   └── ...
            └── META-INF
                └── resources
                    ├── config.js
                    ├── paragraph_field.js
                    ├── paragraph.es.js
                    └── paragraph.soy

You don't need `*TemplateContextContributor.java` or `*TypeSettings.java`
in your initial module (see the tutorial on adding settings to your form field
types to learn more about these classes). The initial module consists of these
Java classes and resources:

- `*DDMFormFieldRenderer.java`: Controls the rendering of the template. Sets the
    language, declares the namespace, and loads the template resources on
    activation of the Component. Extending the abstract class that implements
    the `DDMFormFieldRenderer` makes your work here easier.

- `*DDMFormFieldType.java`: Defines the form field type in the backend. If you
    extend the abstract class that implements the interface, you automatically
    include the default form configuration options for your form field type. In
    that case, override the interface's `getName` method and you're done. To see
    the default configuration options your form field type will inherit, look at
    the `DefaultDDMFormFieldTypeSettings` class in the `dynamic-data-mapping-api`
    module.

- `config.js`: Auto-generated if you use Blade CLI, `config.js` defines the
    dependencies of all declared JavaScript components.

- `[name-of-field-type]_field.js`: The JavaScript file modeling your field.

- `[name-of-field-type].es.js`: The JavaScript file that configures the template
    rendering (the `[name-of-field-type].soy` rendering).

- `[name-of-field-type].soy`: The template that defines the appearance of the field. 

- `Language_xx_XX.properties`: Define any terms that need to be 
    [translated into different languages](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application).

<!-- Add the additional classes from the Google doc when this intro is split
out, according to the form fields tutorial planning section -->

In addition to the Java classes, Soy templates, and JavaScript files, a form
field type contains the following files:

- `.babelrc`: The [Babel](https://babeljs.io/) configuration file.

- `.npmbundlerrc`: The [liferay-npm-bundler](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-0/liferay-npm-bundler) 
configuration file.

- `bnd.bnd`: The module's metadata.

- `build.gradle`: The module's dependencies and build properties.

- `package.json`: The npm module manager.

- `package-lock.json`: Automatically generated to track the npm modules dependencies.

<!-- UPDATE WHEN TEMPLATE PROJECT IS PUBLISHED ON repository.liferay.com
+$$$

**Blade Template:** There's a [Blade
CLI](/developer/tutorials/-/knowledge_base/7-0/blade-cli) template you can use
to generate the project skeleton, some basic configuration, and give you a head
start on dependency management. To generate your form field type project with
Blade CLI, enter

    blade create -t form-field-type -p com.liferay.docs.formfieldtype -c Time ddm-type-time

This gives you a `ddm-type-time` module with a similar structure to the one
outlined above. The Java classes will be in the package
`com.liferay.docs.formfield` under `src/main/java/` and the frontend resources
(JavaScript and Soy files) are in `sr/main/resources/META-INF/resources`.

$$$
-->

