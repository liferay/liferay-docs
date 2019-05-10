---
header-id: configuration-json-available-options
---

# Configuration JSON Available Options

[TOC levels=1-4]

If you've 
[created an OSGi bundle with the Liferay Bundle Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-bundle-generator-and-generating-a-bundle) 
and want to provide system settings or instance settings for your widget, you 
must provide a `configuration.json` file. This reference guide lists the 
available configuration options for `configuration.json` along with example 
code. 

## JSON Format

The `configuration.json` must follow the basic pattern shown below:

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

The available options are described in the table below:

| Option | Value |
| --- | --- |
| `{category identifier}` | Describes the identifier of the configuration category where the settings must be placed. It's equivalent to the category field of the `@ExtendedObjectClassDefinition` annotation explained [here](/docs/7-2/frameworks/-/knowledge_base/f/categorizing-the-configuration). The category field of `configuration.json` is optional and, when not set, the project's name specified in `package.json` is used. You need JS Portlet Extender 1.1.0+ for this feature to work. Otherwise, the system configuration will show up under *Platform* &rarr; *Third Party* in System Settings. |
| `{name of configuration}` | the configuration's name as a string or a localization key. If no value is given, the bundler falls back to the project's name, then description given in `package.json`. |
| `{field id}` | the field's name as a string or a localization key |
| `{field type}` | specifies the field's type, which can be one of the following types: &nbsp;- `number`: an integer number&nbsp;- `float`: a floating point number&nbsp;- `string`: a string&nbsp;- `boolean`: true or false&nbsp;- `password`: a password (string) |
| `{field name}` | the field's name as a string or a localization key |
| `{field description}` | an optional string or a localization key that describes the field's purpose and appears as hint text near it |
| `{default value}` | an optional default value for the field |
| `options` | an optional section that defines a fixed set of values for the field |
| `{option id}` | a string that defines the option's ID |
| `{option name}` | the option's name as a string or a localization key |

An example configuration is shown below:

```json
{
  "system": {
    "category": "third-party",
    "name": "My project",
    "fields": {
      "a-number": {
        "type": "number",
        "name": "A number",
        "description": "An integer number",
        "default": "42"
      },
      "a-string": {
        "type": "string",
        "name": "A string",
        "description": "An arbitrary length string",
        "default": "this is a string"
      },
      "a-password": {
        "type": "password",
        "name": "A password",
        "description": "A secret string",
        "default": "s3.cr3t"
      },
      "a-boolean": {
        "type": "boolean",
        "name": "A boolean",
        "description": "A true|false value",
        "default": true
      },
      "an-option": {
        "type": "string",
        "name": "An option",
        "description": "A restricted values option",
        "required": true,
        "default": "A",
        "options": {
          "A": "Option a",
          "B": "Option b"
        }
      }
    }
  },
  "portletInstance": {
    "name": "Portlet configuration",
    "fields": {
      "a-float": {
        "type": "float",
        "name": "A float",
        "description": "A floating point number",
        "default": "1.1"
      }
    }
  }
}
```
