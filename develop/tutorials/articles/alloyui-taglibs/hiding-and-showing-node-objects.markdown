# Hiding and Showing Node Objects

Are you tired of staring at the same elements on your page? Do you wish you 
could get rid of them? Lucky for you, AlloyUI has a method for that. The 
`hide()` and `show()` methods are used to hide and show node objects.

The example below adds a button to the silly phrase generator form that allows 
you to hide and show the title.

1.  Add a button with the following configuration to the form, just below the
    `<aui:button>`:
    
        <aui:button id="button" value="Hide Title"/>
        
    With your button added, you can write the code for it next.

2.  Add the following variables just below the `btnSubmit.on('click'...` 
    function in the `<aui:script>`:
    
        var title = A.one("#phraseTitle");
        var button = A.one("#button");
        var hide = true;
        
    Just as before, the button and title nodes have been referenced by their ids
    and assigned to variables to be used later. A boolean variable `hide` has 
    also been declared and assigned an initial value of `true`; this will be 
    explained later on. Next you will need to write the function that will hide 
    and show the title when the button is clicked.

3.  Add the following code below the variables you just declared:

        button.on('click', function(event){
        
        });
        
    With the `click` event subscribed to your button node, you can add
    the conditions for your `hide()` and `show()` methods.
    
4.  Add the following code to the function you created in the previous step:

        if(hide){
            title.hide();
            hide = false;
            button.text('Show Title');
        }
        else if(!hide){
            title.show();
            hide = true;
            button.text('Hide Title');
        }
        
A conditional statement has been added to test if `hide` is `true` or `false` 
when a user clicks the button. The initial value of the `hide` boolean was set 
to `true` in the previous step to meet the first condition, when the user first 
clicks the button. If `hide = true`, the title is hidden with the  `hide()` 
method, and the button's text is changed back to read `Show Title`. The `hide` 
boolean is also set to `false` so that the next time the user clicks the button, 
the second condition will be met. If `hide = false`, the title is shown using 
the `show()` method, the button's text is changed to read `Hide Title`, and 
`hide` is set back to `true` so that the first condition will be met the next 
time the user clicks the button.

Congrats! Your silly phrase generator is complete, and now you have a basic 
understanding of how to traverse the DOM using AlloyUI. You can view all the 
methods covered in this tutorial, along with many others in the 
 [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone).

## Related Topics

 [Using AlloyUI Carousel](/tutorials/-/knowledge_base/6-2/using-alloyui-carousel-in-a-portlet)

