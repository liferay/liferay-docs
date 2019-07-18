---
header-id: dynamically-populating-select-list-fields-in-the-configuration-ui
---

# Dynamically Populating Select List Fields in the Configuration UI

[TOC levels=1-4]

Previously you had to hardcode config options in the [Configuration interface](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-configuration-interface) 
if you wanted to populate select list fields. This is no longer the case, thanks 
to the [`ConfigurationFieldOptionsProvider` interface](@app-ref@/configuration-admin/latest/javadocs/com/liferay/configuration/admin/definition/ConfigurationFieldOptionsProvider.html). 
Rather than hardcode these values, the `ConfigurationFieldOptionsProvider` 
interface defines these values at runtime. 

Follow these steps to dynamically populate the select list fields in your 
configuration UI:

1.  Create a class and add an `@Component` annotation that registers the 
    `ConfigurationFieldOptionsProvider.class` service and includes the two 
    properties shown below:

    - `configuration.field.name`: The name of the attribute in the configuration 
      interface

    - `configuration.pid`: The ID of the corresponding configuration interface 
      (usually the fully qualified class name)

    An example configuration is shown below:

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

2.  Create a class that implements the `ConfigurationFieldOptionsProvider` 
    interface:

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
