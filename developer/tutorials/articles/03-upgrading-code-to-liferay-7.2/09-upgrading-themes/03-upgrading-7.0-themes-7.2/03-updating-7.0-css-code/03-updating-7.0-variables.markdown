# Updating 7.0 Class Variables

@product-ver@ uses Bootstrap 4's CSS rule syntax. The new syntax lets developers 
leverage Bootstrap 4 features and improvements. The [Migrating to v4 guide](https://getbootstrap.com/docs/4.0/migration/) 
provides complete instructions for updating CSS rules to Bootstrap 4. 

The `gulp upgrade` task suggests the manual Lexicon updates required for the 
theme. For example, here is part of the task log for the 7.0 Westeros Bank 
theme:

```bash
----------------------------------------------------------------
 Lexicon Upgrade (1.0 to 2.0)
----------------------------------------------------------------

File: _variables_custom.scss
    $brand-default was deprecated in Lexicon CSS 1.x.x and has been removed 
		in the new Clay 2.x.x version
```

The log lists removed and/or deprecated variables and suggests possible changes. 
For each update performed or suggested, the task reports a file name. For 
reference, here's the full list of variables that were removed in Bootstrap 4:

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

See [Bootstrap Migration Guide](https://getbootstrap.com/docs/4.0/migration/) 
for a full list of the changes. 

These variables were deprecated in Lexicon CSS 1.x.x and are removed in the new 
Clay 2.x.x version:

```scss
$atlas-theme
$box-shadow-default
$box-shadow-default-bg
$box-shadow-default-blur
$box-shadow-default-spread
$box-shadow-default-x
$box-shadow-default-y
$brand-danger-active
$brand-danger-hover
$brand-default
$brand-default-active
$brand-default-hover
$brand-info-active
$brand-info-hover
$brand-primary-active
$brand-primary-hover
$brand-success-active
$brand-success-hover
$brand-warning-active
$brand-warning-hover
$inverse-active-bg
$inverse-active-border
$inverse-active-color
$inverse-bg
$inverse-border
$inverse-color
$inverse-disabled-color
$inverse-header-bg
$inverse-header-border
$inverse-header-color
$inverse-hover-bg
$inverse-hover-border
$inverse-hover-color
$inverse-link-color
$inverse-link-hover-color
$state-danger-bg
$state-danger-border
$state-danger-text
$state-default-bg
$state-default-border
$state-default-text
$state-info-bg
$state-info-border
$state-info-text
$state-primary-bg
$state-primary-border
$state-primary-text
$state-success-bg
$state-success-border
$state-success-text
$state-warning-bg
$state-warning-border
$state-warning-text
```

These Lexicon mixins are removed in Clay 2.x.x:

`@mixin color-placeholder`:

Lexicon's `color-placeholder($element, $color: $input-color-placeholder)` mixin 
is deprecated as of v1.0.9 and was removed in v2.0.0. Please use 
`placeholder($color: $input-color-placeholder)` instead. 

`@mixin select-box-icon`:

Lexicon's `select-box-icon($color)` mixin is deprecated as of v1.0.10 and was 
removed in v2.0.0. Please use `background-image: lx-icon($name, $color)` 
instead. 

Note that if the `gulp upgrade` task detects any variables in the theme that are 
removed in Clay from the previous LexiconCSS version, it adds the 
`_variables_deprecated.scss` file to the theme with the variables to make sure 
the theme compiles and to decouple it from future upgrades. If you wish to 
include this file manually, here are its contents for reference:

```scss
@warn "You're using deprecated variables. Please refer to the update guides 
to remove its usage";

// Deprecated `$brand-*-*` as of v1.0.24, removed in v2.0.0
$brand-default: #869CAD !default; // Atlas
$brand-default-active: darken($brand-default, 12%) !default; // Atlas
$brand-default-hover: darken($brand-default, 8%) !default; // Atlas

$brand-primary: #65B6F0 !default;
$brand-primary-active: darken($brand-primary, 12%) !default; // Atlas
$brand-primary-hover: darken($brand-primary, 8%) !default; // Atlas

$brand-success: #76BD4A !default;
$brand-success-active: darken($brand-success, 12%) !default; // Atlas
$brand-success-hover: darken($brand-success, 8%) !default; // Atlas

$brand-info: #1E5EC8 !default;
$brand-info-active: darken($brand-info, 12%) !default; // Atlas
$brand-info-hover: darken($brand-info, 8%) !default; // Atlas

$brand-warning: #F5984C !default;
$brand-warning-active: darken($brand-warning, 12%) !default; // Atlas
$brand-warning-hover: darken($brand-warning, 8%) !default; // Atlas

$brand-danger: #C67 !default;
$brand-danger-active: darken($brand-danger, 12%) !default; // Atlas
$brand-danger-hover: darken($brand-danger, 8%) !default; // Atlas

// Box Shadow
// Defines global box-shadows

// Deprecated `$box-shadow-default-*` as of v1.0.24, removed in v2.0.0
$box-shadow-default-x: 0 !default; // Atlas
$box-shadow-default-y: 0 !default; // Atlas
$box-shadow-default-blur: 3px !default; // Atlas
$box-shadow-default-spread: 1px !default; // Atlas
$box-shadow-default-bg: rgba(0, 0, 0, 0.2) !default; // Atlas
$box-shadow-default: $box-shadow-default-x $box-shadow-default-y $box-shadow-default-blur $box-shadow-default-spread $box-shadow-default-bg !default; // Atlas

// Inverse Colors

// Deprecated `$inverse-*` as of v1.0.24, removed in v2.0.0
$inverse-bg: #1F282E !default; // Atlas
$inverse-border: darken($inverse-bg, 10%) !default; // Atlas
$inverse-color: #869CAD !default; // Atlas

$inverse-active-bg: #3A4D5A !default; // Atlas
$inverse-active-border: darken($inverse-active-bg, 1%) !default; // Atlas
$inverse-active-color: #FFF !default; // Atlas

$inverse-disabled-color: #394956 !default; // Atlas

$inverse-hover-bg: #1C252C !default; // Atlas
$inverse-hover-border: darken($inverse-hover-bg, 1%) !default; // Atlas
$inverse-hover-color: $inverse-color !default; // Atlas

$inverse-header-bg: #1B2228 !default; // Atlas
$inverse-header-border: darken($inverse-header-bg, 1%) !default; // Atlas
$inverse-header-color: $inverse-color !default; // Atlas

$inverse-link-color: #FFF !default; // Atlas
$inverse-link-hover-color: $inverse-link-color !default; // Atlas

// States for Form and alert

// Deprecated `$state-*-*` as of v1.0.24, removed in v2.0.0
$state-default-text: $brand-default !default; // Atlas
$state-default-bg: lighten($state-default-text, 34%) !default; // Atlas
$state-default-border: $state-default-text !default; // Atlas

$state-primary-text: $brand-primary !default; // Atlas
$state-primary-bg: lighten($state-primary-text, 12%) !default; // Atlas
$state-primary-border: $state-primary-text !default; // Atlas

$state-success-text: $brand-success !default;

$state-success-bg: lighten($state-success-text, 34%) !default;
$state-success-border: $state-success-text !default;

$state-info-text: $brand-info !default;

$state-info-bg: lighten($state-info-text, 34%) !default;
$state-info-border: $state-info-text !default;

$state-warning-text: $brand-warning !default;

$state-warning-bg: #F8F4D5 !default;
$state-warning-border: $state-warning-text !default;

$state-danger-text: $brand-danger !default;

$state-danger-bg: lighten($state-danger-text, 34%) !default;
$state-danger-border: $state-danger-text !default;
```

After updating your theme's CSS variables and mixins, you should update the 
imports. 
