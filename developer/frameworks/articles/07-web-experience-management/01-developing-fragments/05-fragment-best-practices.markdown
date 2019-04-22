---
header-id: fragment-best-practices
---

# Recommendations and Best Practices

[TOC levels=1-4]

In general all your code should be semantic and highly reusable. A main concern
is making sure that everything is namespaced properly so it won't interfere with
other elements on the page outside of the Fragment.

## CSS

While you can write any CSS in a fragment, it's recommended to prefix it with
a class specific to the fragment to avoid impacting other fragments. To
facilitate this, when creating a new fragment, the HTML includes a `div` with an
automatically generated class name and the CSS shows a sample selector using
that class. Use it as the basis for all selectors you add.

## JavaScript

Avoid adding a lot of JavaScript code, since it isn't easily reusable. Instead,
reference external JS libraries.
