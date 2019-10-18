---
header-id: using-ddm-form-annotations-in-configuration-forms
---

# Using DDM Form Annotations in Configuration Forms

[TOC levels=1-4]

The auto-generated configuration form you get by just creating a 
[configuration interface](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-configuration-interface)
can be too simplistic for some configurations. To enhance it, use the Dynamic
Data Mapping (DDM) Form Annotations.

To use DDM Annotations in configuration forms, 

1.  Configure the module dependencies.

2.  Write a `ConfigurationForm` class, including just the fields that you want
    to leverage the enhanced forms capability. This is similar to the
    configuration interface, but with field annotations from the Liferay
    [Dynamic Data Mapping API](https://github.com/liferay/liferay-portal/tree/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/annotations) 
    rather than the bndtools metatype specification. The fields here must match
    fields defined in the configuration interface.

3.  Implement a [`ConfigurationDDMFormDeclaration`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/configuration-admin/configuration-admin-api/src/main/java/com/liferay/configuration/admin/definition/ConfigurationDDMFormDeclaration.java) 
    to mark your configuration as having a `ConfigurationForm`.

This article assumes you already have an auto-generated configuration UI.

Note that the example code here splits up the Configuration interface and the
Configuration Form interface. If you'd rather mash these together into one
class, you can. This approach might make sense if you have a small number of
fields in your configuration and a simple form to create. If you have numerous
configuration fields and/or a complex form to create, or if you're taking an
existing configuration and extending it to use the DDM Form annotations, you can
consider separating the classes, as shown here.

### Step 1: Declare the Dependencies

In the `build.gradle` file, add `compileOnly` dependencies on the
`dynamic-data-mapping-api` and `configuration-admin-api` module artifacts:

```groovy
compileOnly group: "com.liferay", name: "com.liferay.dynamic.data.mapping.api", version: "5.2.0"
compileOnly group: "com.liferay", name: "com.liferay.configuration.admin.api", version: "2.0.2"
```

### Step 2: Write the Configuration Form

This step requires annotating the class with `@DDMForm` to set up the form, and
annotating each method with `@DDMFormField`. Begin by creating the class body,
annotating each configuration field (interface method) with `@DDMFormField`:

```java
public interface MyFooConfigurationForm {

	@DDMFormField(
		label = "%label-key-for-field-1",
		tip = "%description-key-for-field-1",
		properties = {

			"placeholder=%enter-a-value",
			"tooltip=%some-tooltip-text"
		}
	)
	public String[] textArrayValues();

	@DDMFormField(
		label = "%date",
		tip = "%date-description",
		type = "date")
	public String date();

	@DDMFormField(
		label = "%select",
		optionLabels = {"%foo", "%bar"},
		optionValues = {"foo", "bar"},
		type = "select")
	public String select();

	@DDMFormField(
		label = "%numeric",
		properties = {
			"placeholder=%milliseconds",
			"tooltip=%enter-an-integer-between-1000-and-30000"
		},
		validationErrorMessage = "%please-enter-an-integer-between-1000-and-30000-milliseconds",
		validationExpression = "(numeric >= 1000) && (numeric <= 30000)",
		type = "numeric")
	public String numeric();

	@DDMFormField(
		label = "%checkbox",
		properties = "showAsSwitcher=true")
	public boolean checkbox();

}
```

Once the field annotations are in place, lay out the form itself, right above
the class declaration. This example shows the layout of the
`UserFileUploadsConfigurationForm`, so that you can see the resulting form via
the below screenshot:

```java
@DDMForm
@DDMFormLayout(
	paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.SINGLE_PAGE_MODE,
	value = {
		@DDMFormLayoutPage(
			{
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 12,
							value = {
								"imageCheckToken", "imageDefaultUseInitials",
								"imageMaxSize"
							}
						)
					}
				),
				@DDMFormLayoutRow(
					{
						@DDMFormLayoutColumn(
							size = 6, value = "imageMaxHeight"
						),
						@DDMFormLayoutColumn(size = 6, value = "imageMaxWidth")
					}
				)
			}
		)
	}
)
public interface MyFooConfigurationForm {
```

![Figure 1: The DDM annotations are used to lay out this configuration form.](../../../images/configuration-ddm-form.png)

Next, you must make sure the configuration framework knows about your slick
form.

### Step 3: Write the Form Declaration

Create a new implementation of `ConfigurationDDMFormDeclaration` to register
your new configuration form class:

```java
package com.liferay.docs.my.foo.configuration.definition;

import com.liferay.configuration.admin.definition.ConfigurationDDMFormDeclaration;
import org.osgi.service.component.annotations.Component;
...

@Component(
	immediate = true,
	property = "configurationPid=com.liferay.docs.my.foo.configuration.MyFooConfiguration",
	service = ConfigurationDDMFormDeclaration.class
)
public class MyFooConfigurationDDMFormDeclaration
	implements ConfigurationDDMFormDeclaration {

	@Override
	public Class<?> getDDMFormClass() {
		return MyFooConfigurationForm.class;
	}

}
```

The `configurationPid` must match the fully qualified class name of the
configuration interface.

Now your configuration class is backed by the form-building power of Liferay's
native [Forms application](/docs/7-2/user/-/knowledge_base/u/forms).

To see how this is done for one of Liferay's own configurations, check out all
of the configuration classes for the User Images configuration (Control Panel
&rarr; Configuration &rarr; System Settings &rarr; User Images):

[`UserFileUploadsConfigurationForm`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/users-admin/users-admin-api/src/main/java/com/liferay/users/admin/configuration/definition/UserFileUploadsConfigurationForm.java)

[`UserFileUploadsConfiguration.java`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/users-admin/users-admin-api/src/main/java/com/liferay/users/admin/configuration/UserFileUploadsConfiguration.java)

[`UserFileUploadsConfigurationBeanDeclaration.java`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/users-admin/users-admin-web/src/main/java/com/liferay/users/admin/web/internal/configuration/definition/UserFileUploadsConfigurationBeanDeclaration.java)

[`UserFileUploadsConfigurationDDMFormDeclaration.java`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/users-admin/users-admin-web/src/main/java/com/liferay/users/admin/web/internal/configuration/definition/UserFileUploadsConfigurationDDMFormDeclaration.java)

