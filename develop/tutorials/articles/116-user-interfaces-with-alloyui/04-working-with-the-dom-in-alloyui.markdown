# Working With the DOM in AlloyUI [](id=working-with-the-dom-in-alloyui)

In this tutorial, you'll learn how to manipulate HTML elements in the DOM
(Document Object Model) using AlloyUI. You'll also learn how to handle events on
elements. And if you're playful at heart or you simply want to see DOM
manipulation and events come to life, you can explore the Silly Phrase Generator
example portlet at the end of the tutorial.

Throughout the exercises in this tutorial, you'll use the AlloyUI taglib `aui`.
To access it in your view JSP, add this directive to it:

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

You must also add an `<aui:script>` element into which you'll put AlloyUI-based
JavaScript for manipulating he DOM. You'll configure the `<aui:script>`
element's `use` attribute to specify AlloyUI's DOM-related packages.  To
reference HTML element node objects, you must specify that your script uses
AlloyUI's `node` package. Here's what that looks like: 

    <aui:script use="node">

    </aui:script>

You can use as many AlloyUI packages as you like by declaring them in a
comma-separated list of values assigned to the script's `use` attribute. 

The exercises in this tutorial show you various ways to manipulate DOM nodes in
JSPs, so feel free to write similar code in your JSPs. After this tutorial, 
you'll master the DOM with AlloyUI in no time! 

## Manipulating Nodes [](id=manipulating-nodes)

AlloyUI provides the `html()` method to set and get the inner HTML value of a
node object. To *get* a node object's value, invoke the `html()` method, passing
no arguments. To *set* the HTML value of a node object, pass in an HTML value as
an argument for the `html()` method. The examples below illustrate how the
`html()` method works. 

**html():** gets the HTML value of the node on which it's called. For example,
`node1.html()` gets the HTML of `node1`'s node object. 

**html('html'):** sets the HTML value of the node on which it's called. For
example, `node1.html('rosebud')` sets the HTML value of `node1` to `rosebud`. 

You must grab, or *select*, nodes in order to get and set their HTML values. The
`A.one(selector)` method returns the *first* node that matches its selector
parameter and the `A.all(selector)` method returns *all* the elements that
match its selector parameter. Note that AlloyUI uses the same selectors as CSS.

The list below describes a few of AlloyUI's selectors that you can use:

**.class:** Selects nodes with the given class name. For example,
`A.one('.wrapper')` returns the first element with the class name `wrapper`.

**element:** Selects nodes that match the given element. For example, 
`A.all('p')` returns all of the paragraph (`<p>`) element nodes.

**element[attribute=value]:** Selects nodes with the matching attribute setting.
For example, `A.one('div[title=section]')` returns the first `div` element with
an attribute setting `title='section'`. 

**#id:** Selects nodes that match the given ID. For example, 
`A.one('#container')` returns the first element with the ID attribute setting
`id="container"`. 

+$$$

**Note:** [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#selectors)
lists all the selectors that AlloyUI supports. 

$$$

Here's example JSP code that demonstrates getting the value of a specific
paragraph node and applying its value to part of another paragraph node's new
value. 

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

    <p id="paragraph" >Inner HTML value of paragraph.</p>

    <p id="htmlValFromPara" />

    <aui:script use="node">
        var para = A.one("#paragraph");
        var paraInnerHtmlVal = para.html();

        A.one("#htmlValFromPara").html('Result: ' + paraInnerHtmlVal);
    </aui:script>

It selects the first node with setting `id="paragraph"` and then sets that value
in the HTML of the paragraph that has the setting `id="htmlValFromPara"`. The
figure below shows the result of using this example code in a JSP. 

![Figure 1: Grabbing HTML nodes and modifying them is a breeze with AlloyUI's selectors and `html()` methods.](../../images/alloyui-html-set-get.png)

Do you want to manipulate taglib nodes too? No problem! 

For example, if you have an `<aui:button>` element with the setting
`id="submit"`, you could select it by invoking `A.one("#submit")`. Here's
example JSP code that grabs the value *Generate* from an `<aui:button>` and uses
it in a paragraph: 

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

    <aui:button type="submit" class="btn btn-primary" id="submit" value="Generate"/>

    <p id="htmlValFromBtn" />

    <aui:script use="node">
        var btnSubmit = A.one("#submit");

        A.one("#htmlValFromBtn").html('Button HTML value: ' + btnSubmit.html());
    </aui:script>

The figure below shows the results of rendering the code example. 

![Figure 2: AlloyUI lets you select taglib elements, like this `<aui:button>`, and use its HTML value.](../../images/alloyui-html-get-html-from-aui-button.png)

You can also get and set node attributes, including a node's `value` attribute.
AlloyUI's `attr()` and `val()` methods make this possible. Both of these methods
are overloaded, meaning that they can be used for both getting and setting
values. If you pass an argument to the `val()` method, it *sets* the node's
attribute value. If you don't pass `val()` any arguments it *gets* the
attribute's value. The `attr()` method behaves in a similar manner. If you pass
the `attr()` method two arguments, the first argument specifies the attribute to
be modified and the second argument specifies the new value of that attribute.
If a second argument is not given, the `attr()` method gets the value of the
specified attribute. Below are concise descriptions of both methods and a code
example that demonstrates how properly to use the methods. 

**attr(attribute):** Sets or gets the value of a node's specific attribute. For
example, `A.one('#container').attr('name')` gets the value of the *name*
attribute of the first node that has the attribute setting `id="container"`. 

**val('value'):** Sets the value of a node's *value* attribute. If no value
parameter is specified, it returns the value of the node's *value* attribute. For
example, `A.one('#input1').val()` returns the value of the *value* attribute for
the first node that has the attribute setting `id="input1"`.

The example code below demonstrates getting an element's *type* attribute and
setting it to a new value. It changes the `<aui:button>`'s type value from
*submit* to *reset*. 

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

    <aui:button type="submit" class="btn btn-primary" id="submit" value="Generate"/>

    <p id="htmlValFromBtn" />

    <aui:script use="node">
        var btn = A.one("#submit");
        var btnOrigHtml = btn.html();
        var btnOrigType = btn.attr('type');

        btn.html('Reset');
        btn.setAttribute('type', 'reset');
        var btnNewType = btn.attr('type');

        A.one("#htmlValFromBtn").html(
            'Button HTML value: ' + btnOrigHtml + ', original type: ' +
            btnOrigType + ', new type: ' + btnNewType);

    </aui:script>

The figure below shows a portlet that uses this example code in its view. 

![Figure 3: In this example portlet, its button's original HTML value, original type, and new type are printed.](../../images/alloyui-setting-html-and-attr.png)

You can append/add other elements to a node using the `append()` method on the
node object. You can append a brand new element or an existing element that's
assigned to a variable. To append an existing element to a node object, invoke
the `appendTo()` method, passing the variable of the node object you wish to
append as the argument of the method. Below are a few examples:

**append('element'):** Adds an element to a node object. For example,
`node1.append("<p>You're a lizard Harry</p>")` adds the new paragraph to
*node1*. 

**appendTo('nodeObject'):** Adds one node object to another node object. For
example, `node1.appendTo(node2)` adds the element set of *node1* to the element
set of *node2*.

The example below appends new paragraph elements to the `<div>`s and then
appends one element's HTML to another element. 

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

    <div id="node1"></div>
    <hr>
    <div id="node2"></div>

    <aui:script use="node">
        var node1 = A.one("#node1");
        node1.append("<p>You're a lizard Harry.</p>");

        var node2 = A.one("#node2");
        node2.append("<p> and you look better than ever.</p>");

        node2.appendTo(node1);
    </aui:script>

The figure below shows the results of appending the new element. Notice how
`node2`'s paragraph is appended to `node1`'s paragraph. In this example, the
first node is followed by a horizontal rule, to show where the first node ends
and to separate it visually from the second node. 

![Figure 4: Appending new or existing elements to a node is easy with AlloyUI.](../../images/alloyui-append-new-element.png)

Lastly, regarding node manipulation, you can style nodes. AlloyUI provides a few
methods for applying styles with CSS. Below is a list of some of the available
methods: 

**addClass():** Adds a class to the node. For example, 
`nodeObject.addClass('foo')` adds the class `foo` to the node object.

**removeClass():** Removes a class from the node. For example, 
`nodeObject.removeClass('foo')` removes class `foo` from the node object.

**setStyle():** Sets the CSS property given. For example, 
`nodeObject.setStyle('border', 'solid')` sets the node object's `border` CSS 
property to the value `solid`. 

**getStyle():** Gets the value of the CSS property given. For example,
`nodeObject.getStyle('border')` gets the value of the node object's `border` CSS
property. 

At this point, you know how to manipulate nodes that are on your page. It's time
to dynamically work with user input. The next section shows you how to handle
such input using node object events. 
 
## Handling Node Object Events [](id=handling-node-object-events)

Some HTML nodes can generate events. You can handle these events to process
their information and to provide content based on interaction with the user.
In this section, you'll learn how to subscribe to node events and handle them. 

First, you must declare the `portlet` and `aui` tag libraries to access their
tags. From the `portlet` tag library, you should include the
`<portlet:defineObjects />` tag so that you can access portlet objects like
`renderRequest`, `actionRequest`, and `portletPreferences` in your JSP. For more
information about the `<portlet:defineObjects />` tag, see
[Java's portlet specification documentation](https://portlet-container.java.net/docs/jsr286.html#Tag_Library).
Add the following declarations to the beginning of your JSP:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

    <portlet:defineObjects />

You must also configure your script to use AlloyUI's *event* package. You can add
the event package to your leading `<aui:script>` tag's `use` attribute. You can
configure it to be like this script tag:

    <aui:script use="event, node">

    <aui:script />

Using the `on()` method of a node object, you can subscribe to events and
trigger a function when an event occurs on that object. For example, a few of
the available events you can subscribe to on an `<aui:button>` are `blur`(when a
node loses focus), `click`, `focus`(when a node gets focus), `mouseout`, and
`mouseover`.

+$$$

**Note:** You can find descriptions of more AlloyUI event methods at the [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#common).

$$$

The example below subscribes to the `click` event of a button that's a part of a
simple form. The form has a single input field that has the attribute setting
`id="verb"`. When a user clicks the button, the JSP code below handles the event
by printing the value of the input field. 

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

    <portlet:defineObjects />

    <div id="container"></div>

    <aui:form>
        <aui:fieldset>
            <aui:input name="verb" id="verb" type="text">
                <aui:validator name="required"/>
            </aui:input>
            <aui:button type="submit" class="btn btn-primary" id="submit" value="Generate"/>
        </aui:fieldset>
    </aui:form>

    <aui:script use="event, node">
        var btnSubmit = A.one("#submit");

        var verb = A.one("#<portlet:namespace />verb");

        var container = A.one("#container");

        btnSubmit.on('click', function(event){

            ver = verb.val();

            container.append('<p id="phrase"></p>');

            phrase= A.one("#phrase");

            if (ver != '') {
                phrase.html('Phrase: ' + ver); 
            }

        });
    </aui:script>

For the example above, when the user clicks on the *Generate* button, the value
of the input field is printed above the *verb* input field. 

![Figure 5: AlloyUI lets you handle events on all kinds of elements, like the event triggered by this `<aui:button>`.](../../images/alloyui-event-from-button.png)

Some other methods of interest are the `hide()` and `show()` methods. They let
you hide and show elements in the DOM. 

**hide('nodeObject'):** hides a node object. For example, `node1.hide()`
hides the node object *node1*.

**show('nodeObject'):** shows a node object. For example, `node1.show()`
shows the node object *node1*.

With the hide and show methods you can hide and show DOM nodes appropriately for
the flow of your user experience. 

In this tutorial, you've learned the fundamentals of getting node objects,
setting their values, and reacting to events on them. As an added bonus, the
next section shows you an example portlet that demonstrates all of the AlloyUI
features that you've learned in this tutorial. The portlet is called the
*Silly Phrase Generator*. If you want to have fun trying it out and
familiarizing yourself with its code, continue with the next section. 

## Bonus: Silly Phrase Generator [](id=bonus-silly-phrase-generator)

The Silly Phrase Generator portlet relies heavily on AlloyUI's DOM manipulation
and event handling features. You can add the following code into a portlet JSP
or you can just look at the portlet's code. 

The portlet's code resides on GitHub
[here](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/code/tutorials-sdk/portlets/alloy-ui-silly-phrase-generator-portlet),
but it's also shown here for your convenience.

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

    <portlet:defineObjects />

    <aui:script use="event, node">
        var btnSubmit = A.one("#submit");

        var verb = A.one("#<portlet:namespace />verb");
        var adj = A.one("#<portlet:namespace />adj");
        var mam = A.one("#<portlet:namespace />ani"); 
        var adv = A.one("#<portlet:namespace />adv");
        var loc = A.one("#<portlet:namespace />loc");
        var container = A.one("#container");

        btnSubmit.on('click', function(event){

            ver = verb.val();
            adje = adj.val();
            adve = adv.val();
            mama = mam.val();
            loca = loc.val();

            container.append('<p id="phrase"></p>');

            phrase= A.one("#phrase");
            phrase.addClass('phrase');

            if (ver != '' && adje != '' && adve != '' && mama != '' && loca != '') {
                phrase.html('Your silly phrase of the day is:<br>' + '"' + ver + 
                ' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."'); 
            }
        });

        var title = A.one("#phraseTitle");
        var button = A.one("#button");
        var hide = true;

        button.on('click', function(event){
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
        });
    </aui:script>

    <p id="phraseTitle">Silly Phrase Generator</p>
    <div id="container"></div>
     <aui:form>
     <aui:fieldset>
        <aui:input name="adjective" id="adj" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="verb" id="verb" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="adverb" id="adv" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="animal" id="ani" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="location" id="loc" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:button type="submit" class="btn btn-primary" id="submit" value="Generate"/>
        <aui:button id="button" value="Hide Title"/>
        </aui:fieldset>
      </aui:form>

The portlet gets the value of each of the input fields in the `<aui:form>`
element at the end of the code. In the `<aui:script>`, it assigns the input
values to unique variables. Note that in selecting each input field, the input
field's ID value is preceded by the `<portlet:namespace/>` tag. This avoids
namespacing issues between portlets.

In addition to the input field and button variables, the code declares a
variable named `phrase`, in which to display the silly phrase. It grabs the
`<div>` element created earlier in the code. Then it adds an element to display
the silly phrase by appending a `<p>` element to the container `<div>` and
giving it a unique ID to grab.

To make sure that the HTML is not rendered if any fields are blank, conditional
logic wraps the call to the method that sets the HTML for the phrase. The
portlet also includes a button that lets you hide or show the title. 

There you have it! Once you've either copied the code into your portlet's JSP or
you've downloaded the portlet from GitHub into a Liferay Plugin SDK, you can
deploy and test the Silly Phrase Generator code. With the form filled out like
the one below, your silly phrase displays this phrase: 

*Walk your left-footed platypus gingerly in the warehouse."*

![Figure 6: You can experiment with DOM manipulation by generating silly phrases with the Silly Phrase Generator example portlet.](../../images/silly-phrase.png)

If you've copied the Silly Phrase Generator JSP code, then you may also want to
consider making some style changes to it. The silly phrase generator functions
well, but the silly phrase that is generates looks small compared to the title.
You can enlarge the silly phrase text. 

In case you didn't notice, the JSP adds a class to the phrase node.

    phrase.addClass('phrase');

This class was added inside the `btnSubmit` function so that when the user
clicks the button to generate the silly phrase, any styling for the class is
added to the silly phrase before it is displayed. You can write styles for this
phrase node by opening your portlet's `main.css` file and adding styles like
these: 

    .phrase
    {
        font-size:200%;
        line-height:120%;
    }
 
When you generate a silly phrase, you'll see your new styles applied to the
phrase! 

Congratulations! Your silly phrase generator is complete, and you now have a
basic understanding of how to manipulate the DOM using AlloyUI. 

## Related Topics [](id=related-topics)

 [Using AlloyUI Carousel](/develop/tutorials/-/knowledge_base/6-2/using-alloyui-carousel-in-your-portlet)

 [Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)
