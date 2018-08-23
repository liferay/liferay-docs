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

To import a Collection into Liferay, it must be archived in a `.zip` with the
contents in the following format:

- `collection.json`: a text file which describes your collection with the 
     format  `{"name":"<collection-name>","description":"<collection-description>"}`
 
  - `[fragment-name]/`: a folder containing all of the files for a single 
     Page Fragment.
 
    - `fragment.json`: a text file that describes a Page Fragment with the 
        format `{"jsPath":"src/index.js","htmlPath":"src/index.html","cssPath":"src/index.css","name":"<fragment-name>"}`
 
    - `src/`: a folder containing the source code files for the fragment.
 
       - `index.html`: the HTML source for the fragment
 
       - `index.css`: the CSS source for the fragment
 
       - `index.js`: the JavaScript source for the fragment

A collection can contain any number of fragments, so you can have lots of
subfolders in the collection. This format is the same as what's exported from
within Liferay.

Developers can also create fragments to be imported into an existing collection.
Put them in a similarly formatted `.zip`, but without the Collection information
at the top level:

- `[fragment-name]/`: a folder containing all the files for a single 
     Page Fragment.
 
 - `fragment.json`: a text file that describes a Page Fragment with the 
        format `{"jsPath":"src/index.js","htmlPath":"src/index.html","cssPath":"src/index.css","name":"<fragment-name>"}`
 
  - `src/`: a folder containing the source code files for the fragment.
 
     - `index.html`: the HTML source for the fragment
 
     - `index.css`: the CSS source for the fragment
 
     - `index.js`: the JavaScript source for the fragment

Multiple fragments can be included in a single `.zip` file with each having 
its own folder at the top level.
