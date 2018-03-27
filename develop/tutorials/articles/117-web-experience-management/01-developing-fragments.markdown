# Developing Fragments

Fragments are composed of three parts:

*  **HTML**: The markup of the fragment. Fragments use standard HTML with  
    special tags to add dynamic behavior.

*  **CSS**: CSS styles and positions the fragment's markup. 

*  **JavaScript**: JavaScript provides dynamic behavior to the fragment.

## Making text editable

You can make any text of a fragment editable using the <lfr-editable /> tag as follows:


This is non editable text:
    
    <h1><lfr-editable id=”unique-id”>

This is editable text:
    
    </lfr-editable>

Including widgets within a fragment
Fragments can have dynamic behavior by including a widget. The current widget types supported are portlets, but more types will be supported in the future.

To include a widget you need to know the name with which it is registered. For example, the Site Navigation Menu portlet is registered as a widget named ‘nav’. The widget can be included through a tag whose name is created by combining the widget name with the prefix ‘lfr-app’ a tag. In our example that results in the tag <lfr-app-nav /> as follows:

    <div class=”container-fluid”>
      <div class=”row”>
        <div class=”col-md-2”>
           <img src=”logo.png”/>
        </div>
        <div class=”col-md-10”>
           <lfr-app-nav />
      </div>

## Developing a fragment using desktop tools

It’s possible to develop a fragment using any preferred desktop tools. In order 
Recommendations

### CSS

While it’s possible to write any CSS desired in a fragment, it’s recommended to prefix it with a class specific to the fragment to avoid impacting other fragments. To facilitate this, when creating a new fragment, the HTML will include a div with an automatically generated class name and the CSS will show a sample selector using that class. Use it as the bases for all selectors you add.

### JavaScript

Avoid adding a lot of JavaScript code, since it won’t be highly reusable. Instead, reference external JS libraries.
