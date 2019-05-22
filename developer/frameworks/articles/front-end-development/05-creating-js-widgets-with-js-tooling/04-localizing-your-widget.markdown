---
header-id: localizing-your-widget
---

# Localizing Your Widget

[TOC levels=1-4]

Follow the steps below to learn how to localize your widget:

1.  If you didn't choose to use localization when you generated the bundle, 
    follow this step to enable it in your bundle now, otherwise you can skip 
    this step. Create a `/features/localization` folder in your project and add 
    a `Language.properties` file to it. Add a `create-jar.features.localization` 
    key to your `.npmbuildrc` file that points to the `Language.properties` 
    file. An example configuration is shown below:
    
```json
{
	"create-jar": {
		"output-dir": "dist",
		"features": {
			"js-extender": true,
			"web-context": "/my-test-js-widget",
			"localization": "features/localization/Language",
			"settings": "features/settings.json"
		}
	},
	...
}
```

| **Note:** The default file path is shown above. You can update this value, 
| if you want to place your `Language.properties` file in a different 
| location.

2.  Configure the `Language.properties` file and provide the localized property 
    files (e.g. `Language_[locale].properties`) with the 
    [language keys](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application#what-are-language-keys) 
    for each 
    [available translation](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application#what-locales-are-available-by-default). 
    The *JavaScript based portlet* configuration is shown below:

```properties
javax.portlet.title.my_js_portlet_project=My JS Portlet Project
porlet-namespace=Porlet Namespace
context-path=Context Path
portlet-element-id=Portlet Element Id
configuration=Configuration
fruit=Favourite fruit
fruit-help=Choose the fruit you like the most
an-orange=An orange
a-pear=A pear
an-apple=An apple
```

3.  Retrieve a language key's localized value in JavaScript with the 
    `Liferay.Language.get('key')` method.

Great! Now you know how to localize your widget! 

## Related Topics

- [Configuring System Settings and Instance Settings for Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/configuring-system-settings-and-instance-settings-for-your-javascript-widget)
- [Using Translation Features in Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/using-translation-features-in-your-javascript-widget)
- [Setting Portlet Properties for Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/setting-portlet-properties-for-your-javascript-widget)
