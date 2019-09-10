---
header-id: dynamically-populating-select-list-fields-in-the-configuration-ui
---

# Dynamically Populating Select List Fields in the Configuration UI

[TOC levels=1-4]

You've always been able to provide a select list for your configuration options
by entering each label and value directly in the `@Meta.AD` annotation of the
[Configuration interface](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-configuration-interface).

```java
@Meta.AD(
    deflt = "enabled-with-warning", name = "csv-export",
    optionLabels = {"enabled", "enabled-with-warning", "disabled"},
    optionValues = {"enabled", "enabled-with-warning", "disabled"},
    required = false
)
public String csvExport();
```

Now, thanks to the [`ConfigurationFieldOptionsProvider`
interface](@app-ref@/configuration-admin/latest/javadocs/com/liferay/configuration/admin/definition/ConfigurationFieldOptionsProvider.html),
you can populate select list configurations dynamically, using custom logic. 

Follow these steps to populate the select list fields dynamically in your 
configuration UI:

1.  Use an `@Component` annotation to register the
    `ConfigurationFieldOptionsProvider.class` service and include two
    properties:

    `configuration.field.name`: The name of the attribute in the configuration 
    interface

    `configuration.pid`: The ID of the corresponding configuration interface 
    (usually the fully qualified class name)

    For example,

    ```java
    @Component(
    	property = {
    		"configuration.field.name=enabledClassNames",
    		"configuration.pid=com.liferay.asset.auto.tagger.google.cloud.natural.language.internal.configuration.GCloudNaturalLanguageAssetAutoTaggerCompanyConfiguration",
    		"configuration.pid=com.liferay.asset.auto.tagger.opennlp.internal.configuration.OpenNLPDocumentAssetAutoTaggerCompanyConfiguration"
    	},
    	service = ConfigurationFieldOptionsProvider.class
    )
    ```

2.  Implement the `ConfigurationFieldOptionsProvider` interface:

    ```java    
    public class MyConfigurationFieldOptionsProvider implements 
    ConfigurationFieldOptionsProvider {
        ..
    }
    ```

3.  The `getOptions` method returns a list of `Option`s consisting of the
    label and value fields. The labels provided here are translated to
    `optionLabels`, and the values as `optionValues`, in the configuration
    interface. 

    ```java    
    public List<Option> getOptions() {
    	List<AssetRendererFactory<?>> assetRendererFactories =
    		AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
    			CompanyThreadLocal.getCompanyId());

    	Stream<AssetRendererFactory<?>> stream =
    		assetRendererFactories.stream();

    	return stream.filter(
    		assetRendererFactory -> {
    			TextExtractor textExtractor =
    				_textExtractorTracker.getTextExtractor(
    					assetRendererFactory.getClassName());

    			return textExtractor != null;
    		}
    	).map(
    		assetRendererFactory -> new Option() {

    			@Override
    			public String getLabel(Locale locale) {
    				return assetRendererFactory.getTypeName(locale);
    			}

    			@Override
    			public String getValue() {
    				return assetRendererFactory.getClassName();
    			}

    		}
    	).collect(
    		Collectors.toList()
    	);
    }
    ```

    This code gets a list of `AssetRendererFactory` objects and iterates through
    the list, populating a new list of `Option`s, using the asset's type name as
    the label and the class name as the value. It comes from the
    [`EnabledClassNamesConfigurationFieldOptionsProvider`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/asset/asset-auto-tagger-service/src/main/java/com/liferay/asset/auto/tagger/internal/configuration/admin/definition/EnabledClassNamesConfigurationFieldOptionsProvider.java),
    which populates the configuration field labeled _Enable Google Cloud Natural
    Language Text Auto Tagging For_ with all the asset types that have
    registered a `TextExtractor`.

    ![Figure 1: The select list in the Google Cloud Natural Language Text Auto Tagging entry is populated programmatically, using the `ConfigurationFieldOptionsProvider`.](../../images/configuration-field-options-provider.png)

The `ConfigurationFieldOptionsProvider` allows you to populate select lists with
configuration options defined by your custom logic.
