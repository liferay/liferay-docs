# Form Field Types [](id=form-field-types)

The Forms application contains many highly configurable field types
out-of-the-box. Most use cases are met with one of the existing field types. 

![Figure 1: The Forms application has useful out-of-the-box field types, but you can add your own if you need to.](../../../images/forms-field-types.png)

If you're reading this, however, your use case probably wasn't met with the
default field types. For example, perhaps you need a color picker field. You
could create a select field that lists color names. Some users don't, however,
know that *Gamboge* is the color of spicy mustard (maybe a little darker), and
anyway, seeing colors is better than reading their names, so you can create
a field that shows colors. 

Another example is a dedicated *time* field. Even with a text field and
a tooltip that tells people to *enter the time in the format `hour:minute`*,
some users still enter something indecipherable. You can fix this by adding
a *time* field to @product@'s Forms application. If you have your own ideas for
create your own field types, keep reading to find out how.

These tutorials show you how to

- create a module that adds a *Time* form field type with a timepicker

- add custom configuration options to your field types

+$$$

**Example project:** The source code for the example *time* project developed in
these tutorials can be downloaded for your convenience. Click
[here](https://dev.liferay.com/documents/10184/1608802/dynamic-data-mapping-type-time/f518f6b8-4498-a387-46d5-4715e36cc714?download=true) to begin downloading the source code zip file.

$$$


Before getting started, learn the structure of a form field type. 

## Anatomy of a Field Type Module [](id=anatomy-of-a-field-type-module)

All form field type modules have a similar structure. Here's the directory
structure of the `dynamic-data-mapping-type-time` module developed in these
tutorials:

    .babelrc
    .npmbundlerrc
    bnd.bnd
    build.gradle
    package-lock.json
    package.json
    src
    └── main
        ├── java
        │   └── com
        │       └── liferay
        │           └── dynamic
        │               └── data
        │                   └── mapping
        │                       └── type
        │                           └── time
        │                               ├── TimeDDMFormFieldRenderer.java
        │                               ├── TimeDDMFormFieldTemplateContextContributor.java
        │                               ├── TimeDDMFormFieldType.java
        │                               └── TimeDDMFormFieldTypeSettings.java
        └── resources
            ├── content
            │   └── Language.properties
            └── META-INF
                └── resources
                    ├── config.js
                    ├── time.es.js
                    ├── time_field.js
                    └── time.soy

You don't need `*TemplateContextContributor.java` or `*TypeSettings.java` in the
initial module (see [Rendering Form Field Settings](/develop/tutorials/-/knowledge_base/7-1/rendering-form-field-settings) 
to learn more about these classes). The initial module consists of these Java
classes and resources:

`*DDMFormFieldRenderer.java`: Controls the template's rendering. Sets the
language, declares the namespace, and loads the template resources on
activation of the Component. Extending the abstract class that implements
the `DDMFormFieldRenderer` makes your work here easier.

`*DDMFormFieldType.java`: Defines the form field type in the back-end. If you
extend the abstract class that implements the interface, you automatically
include the default form configuration options for your form field type. In
that case, override the interface's `getName` method and you're done. To see
the default configuration options your form field type inherits, look at
the `DefaultDDMFormFieldTypeSettings` class in the `dynamic-data-mapping-api`
module.

`config.js`: Auto-generated if you use Blade CLI, `config.js` defines the
dependencies of all declared JavaScript components.

`[name-of-field-type]_field.js`: The JavaScript file modeling your field.

`[name-of-field-type].es.js`: The JavaScript file that configures the template
rendering (the `[name-of-field-type].soy` rendering).

`[name-of-field-type].soy`: The template that defines the appearance of the field. 

`Language_xx_XX.properties`: Define any terms that must be 
[translated into different languages](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application).

In addition to the Java classes, Soy templates, and JavaScript files, a form
field type contains the following files:

`.babelrc`: The [Babel](https://babeljs.io/) configuration file.

`.npmbundlerrc`: The [liferay-npm-bundler](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-0/liferay-npm-bundler) 
configuration file.

`bnd.bnd`: The module's metadata.

`build.gradle`: The module's dependencies and build properties.

`package.json`: The npm module manager.

`package-lock.json`: Automatically generated to track the npm modules dependencies.

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

