# Recommendations and Best Practices

In general all of your code should be semantic and highly reusable. One of the 
main concerns is making sure that everything is namespaced properly as to not
interfere with other elements on the page outside of the Fragment.

## CSS

While it’s possible to write any CSS desired in a fragment, it’s recommended to 
prefix it with a class specific to the fragment to avoid impacting other 
fragments. To facilitate this, when creating a new fragment, the HTML will 
include a div with an automatically generated class name and the CSS will show 
a sample selector using that class. Use it as the bases for all selectors you 
add.

## JavaScript

Avoid adding a lot of JavaScript code, since it won’t be highly reusable. 
Instead, reference external JS libraries.

## Developing a fragment using desktop tools

It’s possible to develop a fragment using any preferred desktop tools. Since 
the Fragment is simply HTML, CSS, and JavaScript, you could use a simple text 
editor or a specialized tool with its own built in previews, like the one's 
Liferay provides. If you use an external tool, you can copy/paste the code into the appropriate panes in the Fragment editor to publish it.
