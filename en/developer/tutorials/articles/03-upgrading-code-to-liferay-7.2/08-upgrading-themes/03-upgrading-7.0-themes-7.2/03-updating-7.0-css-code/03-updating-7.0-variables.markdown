---
header-id: updating-7-0-class-variables
---

# Updating 7.0 Class Variables

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Updating 7.0 CSS Code</p><p>Step 2 of 3</p>
</div>

@product-ver@ uses Bootstrap 4's CSS rule syntax. The new syntax lets developers 
leverage Bootstrap 4 features and improvements. The [Migrating to v4 guide](https://getbootstrap.com/docs/4.0/migration/) 
provides complete instructions for updating CSS rules to Bootstrap 4. 

Follow these steps to upgrade the theme's CSS variables:

1.  Consult the upgrade log produced by the `gulp upgrade` task. It suggests the 
    manual Lexicon updates required for the theme.

2.  Make the required changes in the log. The log lists removed and/or 
    deprecated variables and suggests possible changes. For each update 
    performed or suggested, the task reports a file name. For example, here is 
    part of the task log for the 7.0 Westeros Bank theme:

```bash
----------------------------------------------------------------
 Lexicon Upgrade (1.0 to 2.0)
----------------------------------------------------------------

File: _variables_custom.scss
    $brand-default was deprecated in Lexicon CSS 1.x.x and has been removed 
		in the new Clay 2.x.x version
```

| **Note:** If the `gulp upgrade` task detects any variables in the theme that 
| are removed in Clay from the previous LexiconCSS version, it adds a 
| `_variables_deprecated.scss` file to the theme containing the removed 
| variables, to make sure the theme compiles and to decouple it from future 
| upgrades.

After updating your theme's CSS variables and mixins, you should update the 
imports next. 
