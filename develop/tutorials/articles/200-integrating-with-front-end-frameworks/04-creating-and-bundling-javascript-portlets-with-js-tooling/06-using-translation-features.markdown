# Using Translation Features in Your Portlet [](id=using-translation-features-in-your-portlet)

By default, the Liferay Bundle Generator creates an empty configuration for 
translation. The translate script instructs the user how to add new supported 
locales or configure the credentials when it is run. The translate target reads 
the supported locales you have defined in the `supportedLocales` key of your 
`.npmbuildrc` file and checks your `*language.properties` files to make sure 
they match. 

| **Note:** To use the translation features, you must have a Microsoft 
| Translator key. Provide your credentials through either the 
| `translatorTextKey` variable in your `.npmbuildrc` file, or provide them in 
| the `TRANSLATOR_TEXT_KEY` environment variable. 

Follow these steps to add a new supported locale and automatically create 
a language properties file for it with translations:

1.  Add the locale to the `supportedLocales` array in your `.npmbuildrc` file. 

2.  Run the translate target with the command below:

```bash
npm run translate
```

3.  The translate target automatically creates a language properties file for 
    each new **supported** locale with translations for your language keys. It 
    also warns about locales that are not supported, but have a 
    `*language.properties` file.
    
Great! Now you know how to use the Liferay Bundle Generator's translation 
features in your app. 

## Related Topics [](id=related-topics)

- [Configuring System Settings and Instance Settings for Your JavaScript Portlet](/develop/tutorials/-/knowledge_base/7-1/configuring-system-settings-and-instance-settings-for-your-js-portlet)
- [Localizing Your Portlet](/develop/tutorials/-/knowledge_base/7-1/localizing-your-portlet)
- [Configuring Portlet Properties for Your JavaScript Portlet](/develop/tutorials/-/knowledge_base/7-1/configuring-portlet-properties-for-your-js-portlet)
