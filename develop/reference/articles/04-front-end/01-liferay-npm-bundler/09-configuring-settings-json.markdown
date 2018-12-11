# Configuring System Settings for OSGi Bundles Created with the liferay-npm-bundler [](id=configuring-system-settings-for-osgi-bundles-created-with-the-bundler)

If you're 
[creating an OSGi bundle with the `liferay-npm-bundler`](/develop/tutorials/-/knowledge_base/7-1/creating-and-bundling-javascript-portlets-with-javascript-tooling) 
and want to provide system settings for your portlet, you must provide a 
`settings.json` file. This reference guide lists the available configuration 
options for `settings.json` along with example code.

## JSON Format [](id=json-format)

The `settings.json` must follow the basic pattern shown below:

    {
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
    		"{field id 2}" : {
    		},

    		"{field id n}" : {
    		}
    	}
    }

The options are described below:

`{name of configuration}`: the configuration's name as a string or a 
localization key. If no value is given, the bundler falls back to the project's 
name, then description given in `package.json`.

`{field id}`: the field's name as a string or a localization key

`{field type}`: specifies the field's type, which can be one of the following 
types:

- `number`: an integer number
- `float`: a floating point number
- `string`: a string
- `boolean`: true or false
- `password`: a password (string)

`{field name}`: the field's name as a string or a localization key

`{field description}`: an optional string or a localization key that describes 
the field's purpose and appears as hint text near it

`{default value}`: an optional default value for the field

`options`: an optional section that defines a fixed set of values for the field

`{option id}`: a string that defines the option's ID

`{option name}`: the option's name as a string or a localization key

## Example Configuration [](id=example-configuration)

An example configuration is shown below:

    {
      "name": "My project",
      "fields": {
        "a-number": {
          "type": "number",
          "name": "A number",
          "description": "An integer number",
          "default": "42"
        },
        "a-float": {
          "type": "float",
          "name": "A float",
          "description": "A floating point number",
          "default": "1.1"
        },
        "a-string": {
          "type": "string",
          "name": "A string",
          "description": "An arbitrary length string",
          "default": "this is a string"
        },
        "a-boolean": {
          "type": "boolean",
          "name": "A boolean",
          "description": "A true|false value",
          "default": true
        },
        "a-password": {
          "type": "password",
          "name": "A password",
          "description": "A secret string",
          "default": "s3.cr3t"
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
    }
