---
header-id: using-ddm-form-annotations-in-configuration-forms
---

# Using DDM Form Annotations in Configuration Forms

[TOC levels=1-4]

## Background Information

The goal of this article is to describe how a third party developer would
Create a configuration form using the Dynamic Data Mapping Form Annotations.

Here are some reasons a third party developer would want to do this: This allows
you to create a more advanced form, as all of the form building functionality
exposed via the DDM annotations is at your disposal.

Here's a conceptual example of how this documentation might be used:
[Your example here > MANDATORY]

## How to do It

This is the overview of steps you need, in order to develop this outside of
liferay-portal:

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  Configure the dependencies.

3.  Write the 
    [configuration interface](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-configuration-interface) 
    as usual. This step is covered elsewhere, so the real code example here
    starts with the next step.

4.  Write a `ConfigurationForm` class, including just the fields that you want
    to leverage the enhanced forms capability. This will look similar to the
    configuration interface, but with field annotations from the Liferay
    [Dynamic Data Mapping API](https://github.com/liferay/liferay-portal/tree/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/annotations) 
    rather than the bndtools metatype specification.

5.  Implement a [`ConfigurationDDMFormDeclaration`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/configuration-admin/configuration-admin-api/src/main/java/com/liferay/configuration/admin/definition/ConfigurationDDMFormDeclaration.java) 
    to mark your configuration as having a `ConfigurationForm`.

To get started by creating a module, see 
[this documentation](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

Note that the example code here splits up the Configuration interface and the
Configuration Form interface. If you'd rather mash these together into one
class, you can. This approach might make sense if you have a small number of
fields in your configuration and a simple form to create. If you have numerous
configuration fields and/or a complex form to create, or if you're taking an
existing configuration and extending it to use the DDM Form annotations, you can
consider separating the classes, as shown here.

### Dependencies and Build Script

USUALLY NECESSARY

[what you'd put into a `build.gradle` goes here. Please be conscious that these must be applicable for developers outside of the portal core]

This could be one line if you're just documenting an extension point. For
example, if you're writing an article on the SLA Calendar extension point for
Liferay DXP 7.2.10-GA1:

In the `build.gradle` file, add `compileOnly` dependencies on the
`dynamic-data-mapping-api` and `configuration-admin-api` module artifacts:

```groovy
compileOnly group: "com.liferay", name: "com.liferay.dynamic.data.mapping.api", version: "5.2.0"
compileOnly group: "com.liferay", name: "com.liferay.configuration.admin.api", version: "2.0.2"
```

### Writing the Configuration Form

This step requires annotating the class with `@DDMForm` to set up the form, and
annotating each method with `@DDMFormField`. Here we'll actually begin by
creating the class body, annotating each configuration field (interface method)
with `@DDMFormField`:

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

### Writing the Form Declaration

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

Now your configuration class is backed by the form-building power of Liferay's
native [Forms application](/docs/7-2/user/-/knowledge_base/u/forms).

To see how this is done for one of Liferay's own configurations, check out all
of the configuration classes for the User Images configuration (Control Panel
&rarr; Configuration &rarr; System Settings &rarr; User Images):

[`UserFileUploadsConfigurationForm`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/users-admin/users-admin-api/src/main/java/com/liferay/users/admin/configuration/definition/UserFileUploadsConfigurationForm.java)

[`UserFileUploadsConfiguration.java`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/users-admin/users-admin-api/src/main/java/com/liferay/users/admin/configuration/UserFileUploadsConfiguration.java)

[`UserFileUploadsConfigurationBeanDeclaration.java`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/users-admin/users-admin-web/src/main/java/com/liferay/users/admin/web/internal/configuration/definition/UserFileUploadsConfigurationBeanDeclaration.java)

[`UserFileUploadsConfigurationDDMFormDeclaration.java`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/users-admin/users-admin-web/src/main/java/com/liferay/users/admin/web/internal/configuration/definition/UserFileUploadsConfigurationDDMFormDeclaration.java)

## Additional Details

Note: we don't have docs for the Forms DDM annotations (I know because I write
for the Forms team too). However, I don't think we need to cover all the
details, but we should cover any details that might apply to this use of the DDM
annotations. Are there more things to consider, opr useful information on how
this works?

NOT ALWAYS MANDATORY

[Most code examples won't cover every option, method, etc. available to third party developers. Tell them what else they can do with the feature or API, and explain how]


[Are the APIs and other classes discussed here useful in some other cool process? Is there something cool third party devs can do? Let's tell them about it!]

## Best practices, pitfalls to be avoided

NOT ALWAYS MANDATORY

[What to do and what not to do with this framework, extension point, etc.]
