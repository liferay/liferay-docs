# Recommendations and Best Practices [](id=recommendations-and-best-practices)

In general all your code should be semantic and highly reusable. A main concern
is making sure that everything is namespaced properly so it won't interfere with
other elements on the page outside of the Fragment.

## CSS [](id=css)

While you can write any CSS in a fragment, it's recommended to prefix it with
a class specific to the fragment to avoid impacting other fragments. To
facilitate this, when creating a new fragment, the HTML includes a `div` with an
automatically generated class name and the CSS shows a sample selector using
that class. Use it as the basis for all selectors you add.

## JavaScript [](id=javascript)

Avoid adding a lot of JavaScript code, since it isn't easily reusable. Instead,
reference external JS libraries.

## Developing A Fragment Using Desktop Tools [](id=developing-a-fragment-using-desktop-tools)

You can develop a fragment using any preferred desktop tools. Since the Fragment
is HTML, CSS, and JavaScript, you could use a text editor or a specialized tool
with its own built in previews. 

If you use third party software to create fragments, you must organize them 
into the proper structure to import them: 

*  collection-name/ &rarr; This acts as the internal identifier, allowing for 
    re-imports.

    *  collection.json &rarr; Specify the user friendly name of the Collection

    *  fragment-name-1/ &rarr; This acts as the internal identifier, allowing 
        for re-imports.

        *  fragment.json &rarr; Specify the friendly name or custom locations for 
            files

        *  src/

            *  index.html

            *  index.js

            *  Index.css

    *  fragment-name-2/

        *  fragment.json &rarr; Specify the friendly name or custom locations for 
            files

        *  src/

            *  index.html

            *  index.js

            *  Index.css
 
Once your files are organized like this, you can compress the entire folder 
structure into a .zip archive and import it directly into @product@!
