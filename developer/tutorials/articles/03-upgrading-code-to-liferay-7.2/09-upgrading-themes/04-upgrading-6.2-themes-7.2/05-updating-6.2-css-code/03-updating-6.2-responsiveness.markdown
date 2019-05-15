---
header-id: updating-the-6-2-responsiveness
---

# Updating the Responsiveness

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Updating 6.2 CSS Code<br>Step 3 of 3</p>
</div>

Bootstrap 4 explicit media queries replaced the Bootstrap 2 `respond-to` mixins
for CSS responsiveness. Follow these steps to update your theme's
responsiveness:

1.  Open your `_custom.scss` file.

2.  Replace all `respond-to` mixins with corresponding media queries shown
    below. Note that some of the dimensions have slightly changed:

    **Media Query Replacements**

    | Liferay Portal 6.2 Mixin                            |  &nbsp;@product-ver@ Media Query                                     |
    -------------------------------------- |:---------------------------------------------------------- |
    `@include respond-to(phone)` (max-width: 767px)          | `@include media-breakpoint-down(sm)` (max-width: 767px)              |
    `@include respond-to(tablet)` (min-width: 768px, max-width: 979px)          | `@include media-breakpoint-only(md)` (min-width: 768px, max-width: 991px)                |
    `@include respond-to(phone, tablet)` (max-width: 979px) | `@include media-breakpoint-down(md)` (max-width: 991px)      |
    `@include respond-to(desktop, tablet)` (min-width: 768px) | `@include media-breakpoint-up(md)` (min-width: 768px)                                     |
    `@include respond-to(desktop)` (min-width: 980px)        | `@include media-breakpoint-up(lg)` (min-width: 992px)          |

For example, here is a comparison between the Lunar Resort theme's original and
updated syntax:

Original:

```css
@include respond-to(phone, tablet) {
    html #wrapper #banner #navigation {
    ...
    }
}
```

Updated:

```css
@include media-breakpoint-down(md) {
    html #wrapper #banner #navigation {
    ...
    }
}
```

Your CSS code is updated! Next you'll update your theme's templates. 
