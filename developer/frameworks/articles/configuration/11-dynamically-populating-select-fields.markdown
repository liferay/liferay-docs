---
header-id: dynamically-populating-select-list-fields-in-the-configuration-ui
---

# Dynamically Populating Select List Fields in the Configuration UI

[TOC levels=1-4]

Configuration options can be presented in a select list, directly entering each
label and value in the `@Meta.AD` annotation in the [Configuration
interface](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-configuration-interface).

```java
@Meta.AD(
    deflt = "enabled-with-warning", name = "csv-export",
    optionLabels = {"Enabled", "enabled-with-warning", "Disabled"},
    optionValues = {"enabled", "enabled-with-warning", "disabled"},
    required = false
)
public String csvExport();
```

Now, thanks to the [`ConfigurationFieldOptionsProvider`
interface](@app-ref@/configuration-admin/latest/javadocs/com/liferay/configuration/admin/definition/ConfigurationFieldOptionsProvider.html),
you can populate select list configurations dynamically, using custom logic. 

Follow these steps to dynamically populate the select list fields in your 
configuration UI:

1.  Use a `@Component` annotation to register the
    `ConfigurationFieldOptionsProvider.class` service, and include two
    properties:

    - `configuration.field.name`: The name of the attribute in the configuration 
      interface

    - `configuration.pid`: The ID of the corresponding configuration interface 
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

3.  Use the `getOptions()` method to retrieve the options from the Configuration 
    interface and populate the configuration fields with their labels and 
    values, as shown in the example below:

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

Great! Now you know how to dynamically populate select fields in your 
configuration's UI. 
