---
header-id: using-translation-features-in-your-widget
---

# Using Translation Features in Your Widget

[TOC levels=1-4]

By default, the Liferay Bundle Generator creates an empty configuration for 
translation. The translate script instructs the user how to add new supported 
locales or configure the credentials when it is run. The translate target 
initially reads the language properties files you have, then it adds those 
locales to the `supportedLocales` key in your `.npmbuildrc` file. 

| **Note:** To use the translation features, you must have a Microsoft 
| Translator key. Provide your credentials through either the 
| `translatorTextKey` variable in your `.npmbuildrc` file, or provide them in 
| the `TRANSLATOR_TEXT_KEY` environment variable. 

Follow these steps to add a new supported locale and automatically create 
a language properties file for it with translations:

1.  Add the locale to the `supportedLocales` array in your `.npmbuildrc` file. 

2.  Run the translate target with the command below:

```bash
npm translate
```

3.  The translate target automatically creates a language properties file for 
    each new locale with translations for your language keys. It also warns 
    about locales that are not supported, but have a `*.properties` file.
    
Great! Now you know how to use the Liferay Bundle Generator's translation 
features in your app. 

## Related Topics

- [Configuring System Settings and Instance Settings for Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/configuring-system-settings-and-instance-settings-for-your-javascript-widget)
- [Localizing Your Widget](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-widget)
- [Setting Portlet Properties for Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/setting-portlet-properties-for-your-javascript-widget)
