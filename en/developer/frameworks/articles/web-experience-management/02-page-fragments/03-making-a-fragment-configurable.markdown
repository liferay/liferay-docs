---
header-id: making-a-fragment-configurable
---

# Making a Fragment Configurable

[TOC levels=1-4]

| **Note:** Defining configurations for Page Fragments is available in Liferay
| DXP 7.2 SP1+ and Liferay Portal GA2+.

Defining configuration options for a Fragment gives it more flexibility,
reducing the number of Fragments you must maintain. To make a Fragment
configurable,

1.  Navigate to the *Site Builder* &rarr; *Page Fragments* page.

2.  Click the *Actions* button (![Actions](../../../images/icon-actions.png))
    &rarr; *Edit* for the Fragment (Section or Component) you want to make
    configurable.

3.  Select the *Configuration* tab at the top of the page.

    ![Figure 1: Switch from the Code tab to the Configuration tab to create your configuration logic.](../../../images/fragment-config-tab.png)

4.  In the editor, add your JSON code. This code is added to your fragment's
    `index.json` file. For example, the code below provides the `select` option
    to choose *dark* or *light* for a Fragment's heading:

    ```json
    {
        "fieldSets": [
            {
                "label": "heading",
                "fields": [
                    {
                        "name": "headingAppliedStyle",
                        "label": "applied-style",
                        "description": "this-is-the-style-that-will-be-applied",
                        "type": "select",
                        "dataType": "string",
                        "typeOptions": {
                            "validValues": [
                                {
                                    "value": "dark"
                                },
                                {
                                    "value": "light"
                                }
                            ]
                        },
                        "defaultValue": "light"
                    }
                ]
            }
        ]
    }
    ```

    | **Note:** The `label` property is optional. If it's left out, your
    | configuration option has no title.

    | **Note:** If your configuration is invalid, you can't save the
    | code. Be sure to always have a valid JSON configuration before previewing
    | or saving it.

    The configuration values selected by the user are made available to the
    Fragment developer through the FreeMarker context. A configuration value can
    be referenced using the notation `${configuration.[fieldName]}`. For the
    example snippet above, `${configuration.headingAppliedStyle}` returns
    `dark` or `light` depending on the configuration value selected by the user.

5.  You can refer to your Fragment's configuration values in its HTML file
    (e.g., `index.html`). Navigate back to the *Code* tab at the top of the
    page and add your HTML. For example,

    ```html
    [#if configuration.headingAppliedStyle == 'dark']
    ...
    [#else]
    ...
    [/#if]
    ```

    Configuration values inserted into the FreeMarker context honor the
    defined `datatype` value specified in the JSON file. Therefore, for this
    example, `configuration.headingAppliedStyle?is_string` is `true`.

6.  Click *Publish* to save your work and make it available to add to a Content 
    Page.

    ![Figure 2: You can click your Fragment to view its configuration options.](../../../images/fragment-lang-keys.png)

| **Note:** You can also make a Fragment configurable by leveraging the
| Fragments Toolkit. You can create/modify the Fragment's configuration JSON
| file and then reimport the Fragment to your Liferay instance. For more
| information, see
| [Page Fragment Desktop Tools](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments-desktop-tools).

Although this example highlights accessing configuration values in HTML via the
FreeMarker context, you can also access these values via JavaScript. JavaScript
configuration objects are named the same as their FreeMarker counterparts. 

For example, a configuration object could be built like this:

```js
var configuration = {
    field1: value1,
    field2: value2
}
```

Another example of setting the configuration object and using it is shown below:

```js
const configurationValue = configuration.field1

console.log(configurationValue);
```

For more examples of Fragment configuration, see 
[Fragment Configuration Types](/docs/7-2/reference/-/knowledge_base/r/fragment-configuration-types).

Awesome! You now have a configurable Fragment!
