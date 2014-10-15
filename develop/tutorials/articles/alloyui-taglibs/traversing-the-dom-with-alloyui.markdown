# Traversing the DOM with AlloyUI [](id=traversing-the-dom-with-alloyui)

In this tutorial, you'll learn how you can use AlloyUI to manipulate HTML 
elements in the DOM (Document Object Model). You'll explore this subject through 
a fun application: a silly phrase generator. If you wish to follow along with
the example, you can get the code [here](../../code/alloy/silly-phrase-generator-portlet).

Follow this tutorial and you'll be a master of AlloyUI DOM traversal in no time.

## Grabbing Element Nodes

In order to manipulate HTML elements, you must first have access to the node 
object of the HTML elements.

Follow these steps to get started:

- **Step 1:** Reference the AUI Taglib.
- **Step 2:** Insert and Configure the AUI Script in the View JSP.
- **Step 3:** Reference the Element Node Using a Selector.

Go ahead and dive right in.

### Step 1: Reference the AUI Taglib 

First you need to make sure that you've referenced the `aui` taglib in your JSP. 
If you're already using AlloyUI tags in your JSP, then you can skip this step. 
If you're not, open the `view.jsp` of your portlet or create it if it doesn't 
already exist. Add the directive to reference the `aui` taglib:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
        
Just like that you're ready to use the AUI tags in your portlet. Onwards to the 
next step!

### Step 2: Insert and Configure the AUI Script in the View JSP

Next, you will need to add an `<aui:script>` to hold the scripts you will use to 
grab and edit your element nodes. You will also need to configure the 
`<aui:script>`'s `use` attribute to use the packages you will need. Since this 
tutorial covers how to manipulate HTML elements in the DOM, you will need to use 
the `node` and `event` packages. Your fully configured AUI script should look 
like the following code:

        <aui:script use="event, node">

        </aui:script>
        
Now that your script is configured, you can grab your element node next.

### Step 3: Reference the Element Node

You can use the `A.one()` method to return the first node that matches the 
selector or the `A.all()` method to grab all the elements that match the 
selector. AlloyUI uses the same selectors as CSS. The list below shows a few of 
the available selectors:

**.class:** Selects nodes with the given class name. For example .wrapper 
selects elements with the class='wrapper'.

**element:** Selects nodes that match the given element. For example p selects 
the paragraph elements.

**element[attribute=value]:** Selects nodes that match the given attribute with 
the set value. For example div[title=section] selects div elements with the 
title='section'.

**#id:** Selects nodes that match the given id. For example #container selects 
elements with the id='container'.

+$$$

**Note:** You can find a full list of the available selectors in the 
 [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#selectors).

$$$

The example below grabs the `<aui:button>` element of the silly phrase generator 
form by its id and assigns it to the variable `btnSubmit`.

1.  Add the following script to the top of your JSP:

        <aui:script use="event, node">
          var btnSubmit = A.one("#submit");
        </aui:script>

    the `use` attribute of the `<aui:script>` tag is given the value of the 
    `event` and `node` packages so that you have access to them for your click 
    event and input nodes later on. You then use the `A.one` selector method to 
    grab the button node by passing its `id` as the argument. Now that you have 
    a variable for the button element and the aui packages selected, you can 
    declare the variables for the form fields.

2.  Declare these variables just below the `btnSubmit` variable:

        var verb = A.one("#<portlet:namespace />verb");
        var adj = A.one("#<portlet:namespace />adj");
        var mam = A.one("#<portlet:namespace />ani"); 
        var adv = A.one("#<portlet:namespace />adv");
        var loc = A.one("#<portlet:namespace />loc");

    Each id for the input fields is preceeded by `<portlet:namespace/>` tag to 
    avoid namespacing issues between portlets. In addition to the input field 
    and button variables, you must declare a variable for an element to display 
    the silly phrase inside. You'll grab the `<div>` element you created earlier 
    for this.
    
3.  Add the following variable below the variables you just declared:

        var container = A.one("#container");

All variables have been declared, so you can attach a click event to your button 
node next.
    
## Subscribing to Events of Node Objects

Now that you have referenced your element nodes you can subscribe to events for 
them. Subscribing to events of your element nodes is a quick and easy process. 
Using the `on()` method of the node object, you can subscribe to events and 
trigger a function when the event occurs. A few of the available events you can
subscribe to are `blur`(when a node loses focus), `click`, 
`focus`(when a node gets focus), `mouseout`, and `mouseover`.

+$$$

**Note:** You can read more about events in the [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#common).

$$$

The example below subscribes to the `click` event of the silly phrase button 
node, which is set to the variable `btnSubmit`.

1.  Add the following function just below your variables:

        btnSubmit.on('click', function(event){

        });    

Next, you need to declare variables for the value of your input fields. 

## Getting and Setting Attributes for Node Objects

There are a couple methods that you can use to get and set attributes for your
node objects: the `val()` method; and the `attr()` method; both of these methods 
are overloaded, meaning that they can be used for both getting and setting 
values. If the `val()` method is passed an argument, it sets the value attribute, 
otherwise it gets the value attribute. The `attr()` method takes two arguments: 
the first argument defines the attribute; the second argument defines the value 
of the attribute. If a second argument is not given, the `attr()` method gets 
the attribute, otherwise it sets the attribute. 

The example below gets the value of each of the input fields of the silly phrase 
generator and assigns them to unique variables.

1.  Add these variables just inside the function you just created:

        ver = verb.val();
        adje = adj.val();
        adve = adv.val();
        mama = mam.val();
        loca = loc.val();

Next, you'll add an element to display your silly phrase. To do this, you'll 
append a `<p>` element to the container `<div>` and give it a unique id to grab 
in the steps to follow.

## Appending Elements to Node Objects

Once you have your elements referenced you can append(add) other elements to 
them using the `append()` method of the node object. You can append a brand new 
element, or an existing element. To append an existing element to a node object 
you can use the `appendTo()` method, passing the node object you wish to append 
to  as the argument of the method. 

The example below appends a new empty paragraph element, with the id `phrase`, 
to the `<div>` assigned to the variable `container`:

1.  Append the element just below the value variables:

        container.append('<p id="phrase"></p>');

    Now that you have the HTML element created, you must assign it a variable 
    that you can call upon later.

2.  Add the variable below the appended element:

        phrase= A.one("#phrase");

All right! The framework is set; now you need to write the code for the phrase. 
To do this, you'll set the HTML of the `<p>` element which you just assigned to 
the phrase variable.

## Setting the HTML of Node Objects

AlloyUI provides the `html()` method to set and get the HTML of a node object.
To get the HTML of a node object simply pass no arguments in the method. To set
the HTML of a node object, pass in the HTML you wish to set for the node object
as the argument for the `html()` method.

The example below sets the HTML for the phrase node.

You'll want to make sure that none of the HTML is rendered for the phrase if any 
fields are left blank; to do this, you'll wrap the silly phrase code in a 
conditional statement.

1.  Add this code below the phrase variable:

        if (ver != '' && adje != '' && adve != '' && mama != '' && loca != '') {
			phrase.html('Your silly phrase of the day is:<br>' + '"' + ver + 
			' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."');	
		}

The conditional statement uses the !=(not equal) operator to check that each
field is not blank. The &&(and) operator is used between each condition, meaning 
that all of the conditions must be true before the statement is true. Once the 
statement is true, you use the `html()` method to set the innerHTML of the 
message element.

There you have it! Redeploy your application and test your new Silly Phrase 
Generator. With the form filled out like the one below, your silly phrase should 
read: "Walk your left-footed platypus gingerly in the warehouse."

![Figure 1: You can generate some silly phrases with the silly phrase generator.](../../images/silly-phrase.png)

Now that your silly phrase generator is working properly, there are a couple 
more things you can add to it.

## Setting the CSS of Node Objects

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

## Hiding and Showing Node Objects

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

 [Using AlloyUI Carousel](/tutorials/-/6-2/knowledge_base/using-alloyui-carousel-in-a-portlet)

