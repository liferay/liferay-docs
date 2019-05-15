---
header-id: updating-6-2-bootstrap-rules-and-font-awesome-imports
---

# Updating 6.2 Bootstrap Rules and Font Awesome Imports

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Updating 6.2 CSS Code<br>Step 2 of 3</p>
</div>

@product-ver@ uses Bootstrap 4's CSS rule syntax. Font Awesome icons have also 
been moved, requiring changes to your imports. If your theme uses the Liferay JS 
Theme Toolkit, the Gulp `upgrade` task reports automatic CSS updates and 
suggests manual updates. For example, here is part of the task log for the Lunar 
Resort theme upgrade from 6.2 to 7.0. For each update performed and suggested, 
the task reports a file name and line number range:

```bash
----------------------------------------------------------------
 Bootstrap Upgrade (2 to 3)
----------------------------------------------------------------

File: src/css/_aui_variables.scss
    Line 5: "$white" has been removed
    Line 11: "$baseBorderRadius" has changed to "$border-radius-base"
    Line 15: "$btnBackground" has changed to "$btn-default-bg"
    Line 16: "$btnBackgroundHighlight" has been removed
    Line 17: "$btnBorder" has changed to "$btn-default-border"
    ...
File: src/css/custom.css
    Line 201: Padding no longer affects width or height, you may need to
    change your rule (lines 201-227)
    Line 207: Padding no longer affects width or height, you may need to
    change your rule (lines 207-226)
    Line 212: You would change height from "62px" to "82px"
    ...
```

<!--
aui_variables should be renamed to clay_variables above
-->

Follow these steps to update your theme's Bootstrap rules and Font Awesome 
imports:

1.  Since Bootstrap 3 adopted the `box-sizing: border-box` property for all 
    elements and pseudo-elements (e.g., `:before` and `:after`), padding no 
    longer affects dimensions. 
    [Bootstrap's documentation](https://getbootstrap.com/docs/3.3/css/#less-mixins-box-sizing) 
    describes the box sizing changes. In all CSS rules that use padding, make 
    sure to update the width and height. For example, examine the `height` value 
    change in this CSS rule for the Lunar Resort theme's `_custom.scss` file:

    Original:

```css
#reserveBtn {
	background-color: #00C4FB;
	border-radius: 10px;
	color: #FFF;
	font-size: 1.5em;
	height: 62px;
	margin: 30px;
	padding: 10px 0;
	...
}
```

    Updated:

```css
#reserveBtn {
	background-color: #00C4FB;
	border-radius: 10px;
	color: #FFF;
	font-size: 1.5em;
	height: 82px;
	margin: 30px;
	padding: 10px 0;
	...
}
```

| **Note:** For individual elements, you can overwrite the 
| `box-sizing:border-box` rule with `box-sizing:content-box`. 

2.  The following variables are removed in Bootstrap 4. Remove any of these if 
    they are used in your theme:

```scss
$line-height-computed
$padding-base-horizontal
$padding-base-vertical
$padding-large-horizontal
$padding-large-vertical
$padding-small-horizontal
$padding-small-vertical
$padding-xs-horizontal
$padding-xs-vertical
$gray-base
$gray-darker
$gray-dark
$gray
$gray-light
$gray-lighter
$brand-primary
$brand-success
$brand-info
$brand-warning
$brand-danger
$state-success-text
$state-success-bg
$state-success-border
$state-info-text
$state-info-bg
$state-info-border
$state-warning-text
$state-warning-bg
$state-warning-border
$state-danger-text
$state-danger-bg
$state-danger-border
```

    See the 
    [Migrating from 2.x to 3.0 guide](http://getbootstrap.com/migration/#migrating-from-2x-to-30) 
    for CSS rules that changed in Bootstrap 3. Likewise, you can refer to the 
    [Migrating to v4 guide](https://getbootstrap.com/docs/4.0/migration/) 
    for updating CSS rules to Bootstrap 4. 

3.  Font Awesome icons were moved to their own file (`font-awesome.scss`) to 
    avoid the IE9 CSS selector limitation. If you include these imports in your 
    `_custom.scss` file, you must remove them:

```scss
@import "aui/alloy-font-awesome/scss/mixins-alloy";
@import "aui/alloy-font-awesome/scss/variables";
```

## Related Topics

[Updating 6.2 CSS Responsiveness](/docs/7-2/tutorials/-/knowledge_base/t/updating-the-6-2-responsiveness)

[Copying an Existing Theme's Files](/docs/7-2/tutorials/-/knowledge_base/t/copying-an-existing-themes-files)
