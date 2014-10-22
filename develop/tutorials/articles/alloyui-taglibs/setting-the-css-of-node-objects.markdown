# Setting the CSS of Node Objects

AlloyUI provides a few methods for dealing with CSS. Below is a list of some of
the available methods:

**addClass():** Adds a class to the node. For example, 
`nodeObject.addClass('foo')` would add the class foo to the node object.

**removeClass():** Removes a class from the node. For example, 
`nodeObject.removeClass('foo')` would remove class foo from the node object.

**setStyle():** Sets the CSS property given. For example, 
`nodeObject.setStyle('border', 'solid')` would set the value for the border CSS 
property to solid for the node object.

**getStyle():** Gets the value of the CSS property given. For example, 
`nodeObject.getStyle('border')` would get the value for the border CSS
property of the node object.

+$$$

**Note:** You can find a full list of the available CSS methods in the 
 [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#css).

$$$

The silly phrase generator does its job, but the silly phrase that is generated
looks pretty small compared to the title. The example below adds a new class to
the generated phrase and then sets the styles for it.

1.  Add the following code below the `phrase` variable in the `btnSubmit` 
    function:

        phrase.addClass('phrase');
    
    The class has been added inside of the `btnSubmit` function so that when the
    user clicks the button to generate the silly phrase, the styling will be 
    added to the silly phrase before it is displayed. Now that you have your 
    class added, you can write the styles for it next.

2.  Open your `main.css` file and add the following styles:
        .phrase
                {
                    font-size:200%;
                    line-height:120%;
                }    
Now if you generate a silly phrase, you should see your new styles added! There
is one last thing you can add to your silly phrase generator to reinforce
everything you have learned so far.

