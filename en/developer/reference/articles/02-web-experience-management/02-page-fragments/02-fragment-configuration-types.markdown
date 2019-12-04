---
header-id: fragment-configuration-types
---

# Fragment Configuration Types

[TOC levels=1-4]

There are four configurable Fragment types available to implement:

- `checkbox`
- `colorPalette`
- `select`
- `text`

For more information on how to make a Fragment configurable, see 
[Making a Fragment Configurable](/docs/7-2/frameworks/-/knowledge_base/f/making-a-fragment-configurable).

In this article, you'll explore JSON examples of each Fragment type.

## Checkbox Configuration

The following JSON configuration creates a checkbox you can implement for cases
where a boolean value selection is necessary:

```json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "name": "hideBody",
                    "label": "Hide Body",
                    "description": "hide-body",
                    "type": "checkbox",
                    "defaultValue": false
                }
                ...
            ]
        }
    ]
}
```

![Figure 1: The checkbox configuration is useful when a boolean selection is necessary.](../../../images/fragment-config-checkbox.png)

## Color Palette Configuration

The following JSON configuration creates a color selector you can implement for
cases where you must select a color:

```json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "name": "textColor",
                    "label": "Text color",
                    "type": "colorPalette",
                    "dataType": "object",
                    "defaultValue": {
                        "cssClass": "white",
                        "rgbValue": "rgb(255,255,255)"
                    }
                }
                ...
            ]
        }
    ]
}
```

The `colorPalette` type stores an object with two values: `cssClass` and
`rgbValue`.

For example, if you implement the snippet above, you can use it in the
FreeMarker context like this:

```html
<h3 class="text-${configuration.textColor.cssClass}">Example</h3>
```

If you were to choose the color white, the `h3` tag heading would have the class
`text-white'`.

![Figure 2: The `colorPalette` configuration is useful when a color selection is necessary.](../../../images/fragment-config-colorpalette.png)

## Select Configuration

The following JSON configuration creates a selector you can implement for cases
where you must select a predefined option:

```json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "name": "numberOfFeatures",
                    "label": "Number Of Features",
                    "description": "number-of-features",
                    "type": "select",
                    "dataType": "int",
                    "typeOptions": {
                        "validValues": [
                            {"value": "1"},
                            {"value": "2"},
                            {"value": "3"}
                        ]
                    },
                    "defaultValue": "3"
                }
                ...
            ]
        }
    ]
}
```

Configuration values inserted into the FreeMarker context honor the defined
`datatype` value specified in the JSON file.

![Figure 3: The `select` configuration is useful when an option choice is necessary.](../../../images/fragment-config-select.png)

## Text Configuration

The following JSON configuration creates an input text field you can implement
for cases where you must manually input a text option:

```json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "name": "buttonText",
                    "label": "Button Text",
                    "description": "button-text",
                    "type": "text",
                    "typeOptions": {
                        "placeholder": "Placeholder"
                    },
                    "dataType": "string",
                    "defaultValue": "Go Somewhere"
                }
                ...
            ]
        }
    ]
}
```

Configuration values inserted into the FreeMarker context honor the defined
`datatype` value specified in the JSON file.

![Figure 4: The `text` configuration is useful when an input text option is necessary.](../../../images/fragment-config-text.png)
