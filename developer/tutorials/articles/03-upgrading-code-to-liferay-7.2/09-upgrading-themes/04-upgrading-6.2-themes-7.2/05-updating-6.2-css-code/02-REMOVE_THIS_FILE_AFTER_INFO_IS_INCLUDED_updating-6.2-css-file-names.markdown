---
header-id: updating-6-2-css-file-names
---

# Updating 6.2 CSS File Names

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Updating 6.2 CSS Code<br>Step 1 of 3</p>
</div>

Although Sass was available in Liferay Portal 6.2, only Sass partial files 
followed the Sass naming convention (using file suffix `.scss`). In 
@product-ver@ themes, all Sass files must end in `scss`, and `aui` filename 
prefixes must be replaced with `clay` to reflect the introduction of 
[Clay](https://clayui.com/docs/getting-started/clay.html) 
(previously Lexicon CSS).

| **Note**: The
| [Gulp `upgrade` task](/docs/7-2/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes)
| renames CSS files automatically.

Follow these steps to update your CSS file names manually: 

1.  Change each CSS file name's suffix in your theme from `.css` to `.scss`, 
    then prepend an underscore (`_`) to all Sass partial file names, except 
    `main.scss` and `aui.scss`. The Lunar Resort's updated files are shown
    below:

```css
        - `css/`
          - `_aui_variables.scss`
          - `_custom.scss`
```

2.  Replace CSS file names that have the `aui` prefix with `clay` instead. Below 
    are the Lunar Resort's updated CSS file names:

```css
        - `css/`
          - `_clay_variables.scss`
          - `_custom.scss` 
```
 
The full list of core files to rename is shown below for reference. If you 
modified a CSS file shown in the table below, **you must** update its name. If 
you didn't modify the file, no action is required:

**CSS File Name Updates**

| Original CSS File Name | &nbsp;Updated CSS File Name |
-------------- |:-------------------------------- |
`application.css` | `_application.scss`|
`aui.css` | `clay.scss`|
`base.css` | `_base.scss`|
`custom.css` | `_custom.scss`|
`dockbar.css` | removed|
`extras.css` | `_extras.scss`|
`layout.css` | `_layout.scss`|
`main.css` | `main.scss`|
`navigation.css` | `_navigation.scss`|
`portal.css` | `_portal.scss`|
`portlet.css` | `_portlet.scss`|
`taglib.css` | `_taglib.scss`|

## Related Topics

[Running the Gulp Upgrade Task for 6.2 Themes](/docs/7-2/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes)

[Updating 6.2 CSS Rules](/docs/7-2/tutorials/-/knowledge_base/t/updating-6-2-css-rules-and-imports)
