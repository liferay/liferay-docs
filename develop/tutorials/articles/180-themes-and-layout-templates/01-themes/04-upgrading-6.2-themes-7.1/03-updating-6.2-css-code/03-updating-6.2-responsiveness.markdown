# Updating the Responsiveness [](id=updating-the-6.2-responsiveness)

Bootstrap 3 explicit media queries replaced Bootstrap 2 `respond-to` mixins for 
CSS responsiveness. These media queries are still used in @product-ver@. Follow 
these steps to update your theme's responsiveness:

1.  Open your `_custom.scss` file.

2.  Replace all `respond-to` mixins with corresponding media queries shown 
    below:

    **Media Query Replacements**

    | Liferay Portal 6.2 Mixin                            |  &nbsp;@product-ver@ Media Query                                     |
    -------------------------------------- |:---------------------------------------------------------- |
    `@include respond-to(phone)`           | `@include media-query(null, $screen-xs-max)`               |
    `@include respond-to(tablet)`          | `@include media-query(sm, $screen-sm-max)`                 |
    `@include respond-to(phone, tablet)`   | `@include media-query(null, $breakpoint_tablet - 1)`       |
    `@include respond-to(desktop, tablet)` | `@include sm`                                              |
    `@include respond-to(desktop)`         | `@include media-query($breakpoint_tablet, null)`           |

For example, here is a comparison between the Lunar Resort theme's original and 
updated syntax:

Original:

    @include respond-to(phone, tablet) {
        html #wrapper #banner #navigation {
        ...
        }
        ...
    }

Updated:

    @include media-query(null, $breakpoint_tablet - 1) {
        html #wrapper #banner #navigation {
        ...
        }
        ...
    }

## Related Topics [](id=related-topics)

[Updating 6.2 Theme Templates](/develop/tutorials/-/knowledge_base/7-1/updating-6.2-theme-templates)

[Updating CSS File Names for Clay and Sass](/develop/tutorials/-/knowledge_base/7-1/updating-css-file-names-for-clay-and-sass)
