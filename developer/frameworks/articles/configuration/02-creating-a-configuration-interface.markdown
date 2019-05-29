---
header-id: creating-a-configuration-interface
---

# Creating A Configuration Interface

[TOC levels=1-4]

First, you'll learn how to create a configuration with no scope declaration.
This will automatically scope your configuration to `SYSTEM`.

1.  Create a Java interface to represent the configuration and its default
    values. Using a Java interface allows for an advanced type system for each
    configuration option. Here is the configuration interface for the Liferay
    Forms application:

```java

    @Meta.OCD(
        id = "com.liferay.dynamic.data.mapping.form.web.configuration.DDMFormWebConfiguration",
        localization = "content/Language", name = "ddm-form-web-configuration-name"
    )
    public interface DDMFormWebConfiguration {

        @Meta.AD(
            deflt = "1", description = "autosave-interval-description",
            name = "autosave-interval-name", required = false
        )
        public int autosaveInterval();

        @Meta.AD(
            deflt = "descriptive", name = "default-display-view",
            optionLabels = {"Descriptive", "List"},
            optionValues = {"descriptive", "list"}, required = false
        )
        public String defaultDisplayView();


    }

```

    This defines two configuration options, the autosave interval (with a default
    of one minute) and the default display view, which can be descriptive or
    list, but defaults to descriptive. Here's what the two Java annotations in
    the above snippet do:

    **Meta.OCD:** Registers this class as a configuration with a specific
    id. The ID must be the fully qualified configuration class name.

    **Meta.AD:** Specifies [optional
    metadata](http://bnd.bndtools.org/chapters/210-metatype.html) about the
    field, such as whether it's a required field or if it has a default
    value.  Note that if you set a field as required and don't specify a
    default value, the system administrator must specify a value in order
    for your application to work properly. Use the `deflt` property to
    specify a default value.

    The fully-qualified name of the `Meta` class above is
    `aQute.bnd.annotation.metatype.Meta`. For more information about this class and
    the `Meta.OCD` and `Meta.AD` annotations, please refer to the [bndtools
    documentation](http://bnd.bndtools.org/chapters/210-metatype.html).

2.  To use the `Meta.OCD` and `Meta.AD` annotations in your modules, you must
    [specify a dependency](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies)
    on the bnd library. We recommend using bnd version 3. Here's an example of
    how to include this dependency in a Gradle project: 

```java

    dependencies {
        compile group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.1.0"
    }

```

| **Note:** The annotations `@Meta.OCD` and `@Meta.AD` are part of the bnd
| library, but as of OSGi standard version R6, they're included in the OSGi core
| under the names `@ObjectClassDefinition` and `@AttributeDefinition`. The OSGi
| annotations can be used for simple cases like the one described in this
| tutorial. However, a key difference between the two libraries is that the bnd
| annotations are available at runtime, while the OSGi annotations are not.
| Because runtime availability is necessary for some of the Liferay-specific features
| described below, we recommend defaulting to the bnd annotations.

| **Also Note:** Your project depends on the `-metatype: *` line in it's
| `bnd.bnd` file. This line is generated automatically from your bundle's
| `.build.properties` file, and is required to provide information about your
| app's configuration options so that a configuration UI can be generated.

When you register a configuration interface, a UI is auto-generated for it in
*System Settings* &rarr; *Platform* &rarr; *Third Party*. That's the default
location; read the next section to learn how to locate it somewhere more
intuitive.
