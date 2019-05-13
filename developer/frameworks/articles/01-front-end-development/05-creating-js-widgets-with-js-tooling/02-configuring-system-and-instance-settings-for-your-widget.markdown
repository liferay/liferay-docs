---
header-id: configuring-system-settings-and-instance-settings-for-your-javascript-widge
---

# Configuring System Settings and Instance Settings for Your JavaScript Widget

[TOC levels=1-4]

As of v1.1.0 of the JS Portlet Extender, you can define configuration options 
for your widget. These options are passed to the widget's JavaScript entry point 
as the `configuration` parameter. See the [main entry point's reference](understanding-the-js-portlet-extender configuration#main-entry-point) 
for more information on the entry point. Follow these steps to set system and/or 
portlet instance settings for your widget:

1.  Add a `/features` folder in your project's root folder if it doesn't already 
    exist. 

| **Note:** This location can be overridden with the 
| `create-jar.features.configuration` option in your project's `.npmbundlerrc` 
| file. See [OSGi bundle configuration options](/docs/7-2/reference/-/knowledge_base/r/understanding-the-npmbundlerrcs-structure#osgi-bundle-configuration-options) 
| for all the available options for the bundle.

2.  Create a `configuration.json` file in the `/features` folder and follow the 
    pattern below. See the [Configuration JSON](/docs/7-2/reference/-/knowledge_base/r/configuration-json-available-options) 
    reference for an explanation of each of the available options:
    
```json
{
  "system": {
    "category": "{category identifier}",
    "name": "{name of configuration}",
    "fields": {
      "{field id 1}": {
        "type": "{field type}",
        "name": "{field name}",
        "description": "{field description}",
        "default": "{default value}",
        "options": {
          "{option id 1}": "{option name 1}",
          "{option id 2}": "{option name 2}",

          "{option id n}": "{option name n}"
        }
      },
      "{field id 2}": {},

      "{field id n}": {}
    }
  },
  "portletInstance": {
    "name": "{name of configuration}",
    "fields": {
      "{field id 1}": {
        "type": "{field type}",
        "name": "{field name}",
        "description": "{field description}",
        "default": "{default value}",
        "options": {
          "{option id 1}": "{option name 1}",
          "{option id 2}": "{option name 2}",

          "{option id n}": "{option name n}"
        }
      },
      "{field id 2}": {},

      "{field id n}": {}
    }
  }
}
```

3.  Access a system setting's value or a portlet instance setting's value with 
    the syntax `configuration.system` or `configuration.portletInstance` 
    respectively. For instance, to retrieve the `{field id 1}` system setting's 
    value, you would use `configuration.system.{field id 1}`. Note that all 
    fields are passed as strings no matter what type they declare in their 
    descriptor.

Awesome! Now you know how to configure system settings and portlet instance 
settings for your widget. 

## Related Topics

- [Localizing Your Widget](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-widget)
- [Using Translation Features in Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/using-translation-features-in-your-javascript-widget)
- [Setting Portlet Properties for Your JavaScript Widget](/docs/7-2/frameworks/-/knowledge_base/f/setting-portlet-properties-for-your-javascript-widget)
