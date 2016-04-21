# Updating the CSS

In the last section of the Learning Path you ran the `gulp upgrade` task. This
upgrades parts of the theme, but still requires manual steps on the part of the
developer to finish the upgrade.

In this section you'll finish updating the lunar resort theme's CSS.

## Preliminary Setup

1.  Navigate to the lunar resort theme's root directory and run the following
    command:
    
        gulp build

The `gulp build` task builds the base files for the lunar resort theme. You can 
use the base files in the `build` directory as a starting point, when making 
theme modifications.

Now that your directories are set, you can continue the upgrade.

## Updating the CSS Files

At the end of the `gulp upgrade` task a list of changes, removals, and 
suggestions were printed in the command line, for the Bootstrap upgrade.

Follow these steps to make the remaining updates to the lunar resort's CSS files.

1.  Open the `_custom.scss` file in your `src` directory and find the 
    `#reserveBtnWrapper` selector and update the `width` property from `300px` 
    to the value below:

        height: 334px;

2.  Find the `div` selector, just above the `#facebook` selector for the 
    `.social-media-wrapper ul li div`, and change the `height` property's value 
    from `39px` to the value below:

        height: 46px;
        
    Go ahead and remove the `padding-top` rule from that selector as well. 
        
    This modification, accounts for the update to the *Padding* property in each 
    of these selectors, which no longer affects width or height.
    
3.  Add a `padding: 0;` rule to the `.social-media wrapper ul` selector:

        margin: auto;
        padding: 0;
        text-align: center;
        
    This counteracts inherited padding that was offsetting the unordered list 
    for the social media links.
    
    The next update that needs to be made is the file path for the Font Awesome
    icons. The social media links, as well as many other UI elements in Liferay,
    rely on the Font Awesome icons.

3.  Open `_aui_variables.scss` and add these imports to the top:

        // Icon paths
        $FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
        $font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
        $icon-font-path: "aui/lexicon/fonts/";

In versions prior to Liferay 7, the Font Awesome icon path was indicated in the
`aui.css` file. If you are migrating your theme from 6.2 to 7 and have variables
in your `_aui_variables.scss`, make sure you add the Font Awesome imports listed
above, or the icons will not render properly in your theme. If you did not have
any custom variables in the `_aui_variables.scss` file, you could just remove
that file and it would automatically be updated when the gulp `build` task is
run.

The social media links are now updated!

## Updating Responsiveness

In Liferay 6.2, `respond-to` variables were used to respond to a specific aspect
ratio or device. The syntax has slightly changed for Liferay 7. Instead of using
`respond-to` variables, the explicit media query mixins are used now. Follow the
steps below to update the media queries:

1.  Open `_custom.scss` and find the `@include respond-to(phone, tablet)` mixin 
    and replace it with the following media query:
    
        @include media-query(null, $breakpoint_tablet - 1)
        
2.  Find the `@include respond-to(phone)` mixin and replace it with the
    following media query:
    
        @include media-query(null, $screen-xs-max)
        
    Now that the responsiveness is updated, there are a few additional styles 
    that need to be updated for the theme.

3.  Still inside `_custom.scss`, find the `html #wrapper #banner #heading`
    selector, inside of the `@include media-query(null, $breakpoint_tablet - 1)`
    media query and change the value for the `top` property to `0px`.
    
4.  In that same media query, change the `padding-top` property for the 
    `html #wrapper #content` selector to `130px`.
    
5.  Finally, inside of the `@include media-query(null, $screen-xs-max)` media
    query, remove the `margin` property for the `html #wrapper` selector.

That wraps up the changes needed for the theme's CSS files.

In the next section of the Learning Path you'll review the breaking changes for 
the theme templates and make the required updates.
