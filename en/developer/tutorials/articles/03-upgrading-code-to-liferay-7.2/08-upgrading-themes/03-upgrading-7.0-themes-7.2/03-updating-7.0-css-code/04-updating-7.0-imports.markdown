---
header-id: updating-7-0-imports
---

# Updating 7.0 Imports

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Updating 7.0 CSS Code</p><p>Step 3 of 3</p>
</div>

Font Awesome imports and core imports have changed. Follow these steps to update 
the theme:

1.  Originally in Liferay Portal CE 7.0 and Liferay DXP, Font Awesome icons were 
		imported in `_aui_variables.scss` (now renamed `_clay_variables.scss`). Font 
		Awesome icons were later moved to their own file (`font-awesome.scss`) to 
		avoid the IE9 CSS selector limitation. If a 7.0 theme was made prior to this 
		move and `_aui_variables.scss` was modified, the Font Awesome imports shown 
		below must be removed from `_clay_variables.scss`:

    ```scss
    // Icon paths
    $FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
    $font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
    $icon-font-path: "aui/lexicon/fonts/";
    ```

2.  Update the old AUI lexicon paths to use the new Clay paths instead, as shown 
		in the table below:

    |Pattern|Replacement|
    |---|---|
    |`@import "/aui/lexicon/bootstrap/mixins/";`|removed|
    |`@import "/aui/lexicon/lexicon-base/mixins/";`|removed|
    |`@import "/aui/lexicon/atlas-theme/mixins/";`|removed|
    |`@import "aui/lexicon/atlas-variables";`|`@import "clay/atlas-variables";`|
    |`@import "aui/lexicon/atlas";`|`@import "clay/atlas";`|

Great! Your imports are updated, and your CSS upgrade is complete. Next you can 
upgrade the theme templates. 
