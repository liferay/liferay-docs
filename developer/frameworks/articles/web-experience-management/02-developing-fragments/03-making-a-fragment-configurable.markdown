# Making a Fragment Configurable

| **Note:** Defining configurations for Page Fragments is available in Liferay
| DXP 7.2 Fix Pack 1+ and Liferay Portal GA2+. Currently, only `select` fields
| are supported for Fragment configurations. Development for additional field
| types like `checkbox`, `color`, `item-selector`, and `text` are in progress.

Defining configuration options for a Fragment gives it more flexibility,
reducing the number of Fragments you must maintain. To make a Fragment
configurable,

1.  Go back to the *Site Builder* &rarr; *Page Fragments* page.

2.  Click the *Actions* button (![Actions](../../../images/icon-actions.png))
    &rarr; *Edit* for the Fragment (Section or Component) you want to make
    configurable.

3.  Select the *Configuration* tab at the top of the page.

4.  In the editor, add your custom JSON code. This code is added to your
    fragment's `index.json` file. For example, the code below provides the
    `select` option to choose *dark* or *light* for a Fragment's heading:

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

    The configuration values selected by the user are made available to the
    Fragment developer through the FreeMarker context. A configuration value can
    be referenced using the notation `${configuration.<fieldName>}`. For the
    example snippet above, `${configuration.headingAppliedStyle}` would return
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

    Note, configuration values inserted into the FreeMarker context honor the
    defined `datatype` value specified in the JSON file. Therefore, for this
    example, `configuration.headingAppliedStyle?is_string` is `true`.

6.  Click *Publish* to save your work and make it available to add to a content 
    page.

    ![Figure 3: You can click your Fragment to view its configuration options.](../../../images/fragment-config.png)

7.  Your configuration JSON file referred to several language keys. You must
    define these in your Fragment's collection folder so they render properly.
    Create the `[COLLECTION]/src/main/resources/content/Language.properties` and
    add the language keys:

    ```properties
    applied-style=Applied Style
    this-is-the-style-that-will-be-applied=This is the style that will be applied.
    dark=Dark
    light=Light
    ```

    Now reimport your Fragment using the
    [Fragments Toolkit](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments-desktop-tools#importing-and-exporting-fragments).

    ![Figure 4: Once your language keys are created, they're displayed in your Fragment.](../../../images/fragment-lang-keys.png)

Awesome! You now have a configurable Fragment!
