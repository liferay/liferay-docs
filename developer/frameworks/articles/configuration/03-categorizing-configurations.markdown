---
header-id: categorizing-the-configuration
---

# Categorizing the Configuration

[TOC levels=1-4]

By default, the configuration UI for your app is generated in *System Settings*
&rarr; *Platform* &rarr; *Third Party*. You probably don't really want it
there; by categorizing your configuration you can place it somewhere intuitive
and easy to find.

| Note: If you
| [scope](/docs/7-2/frameworks/-/knowledge_base/f/scoping-configurations) your
| configuration so that a UI is generated in Instance Settings as well, your
| categorization will apply to that UI also.

You have two options: either locate your configuration UI in an existing
category and section, or create your own.

Here are the default  System Settings sections. All available categories are
nested beneath these sections:

1.  Content and Data

2.  Platform

3.  Security 

4.  Commerce

5.  Other

| **Note:** Sections appear if they contain at least one configuration category.
| Categories appear if they contain at least one configuration. The visible
| sections and categories depend on the deployed modules.

## Specifying a Configuration Category

Specify the category for your UI by placing an `@ExtendedObjectClassDefinition`
annotation in your configuration interface. This example, which appears
right before the interface's `@Meta.OCD` annotation, places the UI in the
`dynamic-data-mapping` category in the Content management section:

```java

    @ExtendedObjectClassDefinition(
        category = "dynamic-data-mapping",
        scope = ExtendedObjectClassDefinition.Scope.GROUP
    )

```

This annotation does two things:

-   Specifies the `dynamic-data-mapping` category in the Content Management section.

-   Sets the scope of the configuration. You'll learn more about this 
    [next](/docs/7-2/frameworks/-/knowledge_base/f/scoping-configurations).

The fully qualified class name of the `@ExtendedObjectClassDefinition` class is
`com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition`.

Note: The infrastructure used by System Settings assumes the `configurationPid`
is the same as the fully qualified class name of the interface. If they don't
match, it can't provide any information through
`ExtendedObjectClassConfiguration`.

The `@ExtendedObjectClassDefinition` annotation is distributed through the
`com.liferay.portal.configuration.metatype` module, which you can 
[configure as a dependency](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies). 

## Creating New Sections and Categories

If you don't like the default sections and categories, you can create your own
by implementing the `ConfigurationCategory` interface.

Here's code that creates the *Content and Data* section and the *Dynamic
Data Mapping* category:

```java

@Component(service = ConfigurationCategory.class)
public class DynamicDataMappingConfigurationCategory
	implements ConfigurationCategory {

	@Override
	public String getCategoryIcon() {
		return _CATEGORY_ICON;
	}

	@Override
	public String getCategoryKey() {
		return _CATEGORY_KEY;
	}

	@Override
	public String getCategorySection() {
		return _CATEGORY_SECTION;
	}

	private static final String _CATEGORY_ICON = "dynamic-data-mapping";

	private static final String _CATEGORY_KEY = "dynamic-data-mapping";

	private static final String _CATEGORY_SECTION = "content-and-data";

}

```

The `getCategorySection` method returns the String with the new section's key.
Similarly, `getCategoryKey` returns the key for the new category. Provide
localized values for these keys in your module's
`src/main/resources/content/Language.properties` file.

| **Note:** the language keys for categories and sections need to follow
| a specific format. Prefix each section language key with `category-section.` and
| each category language key with `category.` For example:
| 
| `category-section.content-and-data=Content and Data`
| `category.dynamic-data-mapping=Dynamic Data Mapping`

Next you'll specify the scope of your application's configuration.
