# Working With the DOM in AlloyUI [](id=working-with-the-dom-in-alloyui)

In this tutorial, you'll learn how you can use AlloyUI to manipulate HTML 
elements in the DOM (Document Object Model). You'll also learn how to handle
events on those elements. Lastly, for those who are playful at heart, you can
explore what you've learned in this tutorial by exploring the Silly Phrase
Generator portlet. 

Throughout the exercises in this tutorial, you'll use the AlloyUI taglib `aui`.
To access it in your view JSP, add this directive to it:

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

Also, you will need to add a `<aui:script>` element to hold the AlloyUI-based
JavaScript for manipulating athe DOM. You'll need to configure the
`<aui:script>`'s `use` attribute to leverage AlloyUI's DOM-related packages. In
order to reference HTML element node objects, you can specify that your script
uses AlloyUI's `node` package, like this:

    <aui:script use="node">

    </aui:script>

You can use as many AlloyUI packages as you like by declaring them in a
comma-separated list of values assigned to the script's `use` attribute. 

Each exercise in this tutorial, shows you ways to manipulate DOM nodes in your
JSPs; so feel free to write similar code in your JSPs as you go. Follow this
tutorial and you'll be a DOM master with AlloyUI in no time! 

## Manipulating Nodes [](id=manipulating-nodes)

AlloyUI provides the `html()` method to set and get the inner HTML value of a
node object. To *get* a node object's value, invoke the `html()` method passing
no arguments. To *set* the HTML value of a node object, pass in an HTML value as
the argument for the `html()` method. Below are a couple examples to help
illustrate the `html()` method:

**html():** gets the HTML value of the node on which it's called. For example,
`node1.html()` gets the HTML of `node1`'s node object. 

**html('html'):** sets the HTML value of the node on which it's called. For example, `node1.html('rosebud')` 
sets the HTML value `node1`'s node object.

You'll need to grab, or *select*, nodes in order to get and set their HTML
values. You can use the `A.one()` method to return the *first* node that matches
a selector or use the `A.all()` method to grab *all* of the elements that match
a selector. AlloyUI uses the same selectors as CSS.

The list below describes a few of AlloyUI's selectors that you can use:

**.class:** Selects nodes with the given class name. For example,
`A.one('.wrapper')` returns the first element with the class name `wrapper`.

**element:** Selects nodes that match the given element. For example, 
`A.all('p')` returns all of the paragraph (`<p>`) element nodes.

**element[attribute=value]:** Selects nodes with the matching attribute setting.
For example, `A.one('div[title=section]')` returns the first div element with
the setting `title='section'`. 

**#id:** Selects nodes that match the given ID. For example, 
`A.one('#container')` returns the first element with the setting
`id="container"`. 

+$$$

**Note:** The full list of the available selectors in the 
 [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#selectors).

$$$

Here's example JSP code that demonstrates getting the value *Inner HTML value of
paragraph* of a paragraph node and applying that value as part of a new value
for another paragraph node.

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
`id="submit"`, you could select it using the method `A.one("#submit")`. Here's
example JSP code that grabs the value of an `<aui:button>` and uses its value
*Generate* in a paragraph: 

	<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

	<aui:button type="submit" class="btn btn-primary" id="submit" value="Generate"/>

	<p id="htmlValFromBtn" />

	<aui:script use="node">
		var btnSubmit = A.one("#submit");

		A.one("#htmlValFromBtn").html('Button HTML value: ' + btnSubmit.html());
	</aui:script>

The figure below shows the results. 

![Figure 2: AlloyUI also lets you select taglib elements, like this `<aui:button>` and use its HTML value.](../../images/alloyui-html-get-html-from-aui-button.png)

You can also get and set node attributes and a node's `value` attribute, using
the `attr()` and `val()`. Both of these methods are overloaded, meaning that
they can be used for both getting and setting values. If you pass an argument to
the `val()`, it *sets* the node's attribute value, otherwise it *gets* the
attribute's value. The `attr()` method works in a similar manner. If you pass it
two arguments, the first argument defines the attribute and the second argument
defines the value of the attribute. If a second argument is not given, the
`attr()` method gets the attribute. Below are the methods described above with a
couple examples that illustrate how to properly use the methods. 

**attr(attribute):** sets or gets the value of a node's specific attribute. For
example, 
`A.one('#container').attr('name')` gets the value of the *name* attribute
for the first node with the setting `id="container"`.

**val('value'):** sets the value of the node's *value* attribute. If no value
parameter is specified, it returns the value of the node's *value* attribute. 
For example, `A.one('#input1').val()` gets the value of the *value*
attribute for the first node with the setting `id="input1"`.

Here's example code that demonstrates getting an element's *type* attribute and
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

The figure below shows a portlet using this example code. 

![Figure 3: To show the button's transformation, its original and new HTML and type values are printed, for comparison.](../../images/alloyui-setting-html-and-attr.png)

You can append/add other elements to a node using the `append()` method on the
node object. You can append a brand new element or an existing element. To
append an existing element to a node object use the `appendTo()` method, passing
the node object you wish to append as the argument of the method. Below are a
few examples:

**append('element'):** adds an element to a node object. For example
`node1.append("<p>You're a lizard Harry</p>")` adds the new paragraph to
*node1*. 

**appendTo('nodeObject'):** adds one node object to another node object. For
example `node1.appendTo(node2)` adds the element set of *node1* to the element
set of *node2*.

The example below appends a new paragraph elements to the `<div>`s and then
appends one element's HTML to the other element.

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
`node2`'s paragraph is appended to `node1`'s paragraph. The first node is
followed by a horizontal rule to show where it ends and to visually separate it
from the second node.

![Figure 4: Appending new or existing elements to a node is easy with AlloyUI.](../../images/alloyui-append-new-element.png)

Lastly, regarding node manipulation, you can style them. AlloyUI provides a few
methods for dealing with CSS. Below is a list of some of the available methods:

**addClass():** Adds a class to the node. For example, 
`nodeObject.addClass('foo')` adds the class foo to the node object.

**removeClass():** Removes a class from the node. For example, 
`nodeObject.removeClass('foo')` removes class foo from the node object.

**setStyle():** Sets the CSS property given. For example, 
`nodeObject.setStyle('border', 'solid')` sets the value for the border CSS 
property to solid for the node object.

**getStyle():** Gets the value of the CSS property given. For example,
`nodeObject.getStyle('border')` gets the value for the border CSS property of
the node object. 

At this point, you know how to manipulate nodes that are on your page. It's time
to work with dynamic content. The next section shows you how to handle node
events. 
    
## Subscribing to Node Object Events [](id=subscribing-to-node-object-events)

Some HTML nodes can generate events. You can handle these events, to process
their information and providing content to continue interactions with the user.
In this section, you'll learn how to subscribe to node events and handle them. 

First in your JSP, you'll need to declare the `portlet` and `aui` taglibs to
access their tags and specify the `<portlet:defineObjects />` tag to use portlet
objects in your JSP. So, make sure to add the following declarations in the
beginning of your JSP:

	<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
	<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

	<portlet:defineObjects />

Then, configure your script to use AlloyUI's *event* package. You can add
the event package to your leading `<aui:script>` tag's `use` attribute. It could
look similar to this tag: 

	<aui:script use="event, node">

Using the `on()` method of a node object, you can subscribe to events and
trigger a function when an event occurs on that object. For example, a few of
the available events you can subscribe to on an `<aui:button>` are `blur`(when a
node loses focus), `click`, `focus`(when a node gets focus), `mouseout`, and
`mouseover`.

+$$$

**Note:** You can read more about events in the [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#common).

$$$

The example below subscribes to the `click` event of a button that's a part of a
simple form. The form has a single input field with setting `id="verb"`. When
the button is clicked, the JSP handles the value of the input field and prints
its value. 

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

![Figure 5: AlloyUI lets you handle events on all kinds of elements, like on this `<aui:button>`.](../../images/alloyui-event-from-button.png)

Now that you've seen a basic example of event handling, here are a couple
questions: Are you tired of staring at the same elements on your page? Do you
wish you could move them out of the way? Lucky for you, AlloyUI has a couple
methods for that. The `hide()` and `show()` hide and show node objects.

**hide('nodeObject'):** hides a node object. For example, `node1.hide()`
hides the node object *node1*.

**show('nodeObject'):** shows a node object. For example, `node1.show()`
shows the node object *node1*.

With the hide and show methods you can hide and show your DOM nodes as your
workflow dictates. 

In this tutorial you've learned the fundamentals of getting node objects,
setting their values, and reacting to events on them. As an added bonus, the
next section shows you an example portlet that demonstrates all of the AlloyUI
features that you've learned in this tutorial. The portlet is aptly named the
*Silly Phrase Generator*. If you want to have fun trying it out and
familiarizing yourself with its code, continue with the next section. Otherwise,
you can consider the next tutorial and other related tutorials listed at the
bottom of this article. 

## Bonus: Silly Phrase Generator [](id=bonus-silly-phrase-generator)

The Silly Phrase Generator portlet relies heavily on AlloyUI's DOM manipulation
and event handling features. You can add the following code into a new or
existing portlet's JSP, or you can just take a look at the portlet's code in
GitHub [here](../../code/alloy/silly-phrase-generator/end/silly-phrase-generator-portlet/docroot/view.jsp).

For your convenience, here's the JSP code:

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

The portlet gets the value of each of the input fields found in the `<aui:form>`
element at the end of the code. In the `<aui:script>`, it assigns the input
values to unique variables. Note that in selecting each input field, input
field's ID value is preceded by `<portlet:namespace/>` tag. This avoids
namespacing issues between portlets.

In addition to the input field and button variables, the code declares a
variable named `phrase` in which to display the silly phrase. It grabs the
`<div>` element created earlier in the code. Then it adds an element to display
the silly phrase by appending a `<p>` element to the container `<div>` and
giving it a unique ID to grab.

To make sure that none of the HTML is rendered for the phrase if any fields are
left blank, conditional logic wraps the call to the method that sets the HTML
for the phrase. The portlet also includes a button in the silly phrase generator
form to let you hide or show the title. 

There you have it! Once you've either copied the code into your portlet's view
JSP or you've downloaded the portlet from GitHub into a Liferay Plugin SDK, you
can deploy it and test the Silly Phrase Generator. With the form filled out like
the one below, your silly phrase should display this phrase:

*Walk your left-footed platypus gingerly in the warehouse."*

![Figure 6: You can generate some silly phrases with the Silly Phrase Generator.](../../images/silly-phrase.png)

If you've copied the Silly Phrase Generator JSP code, then you may also want to
consider making a couple style changes to it. The silly phrase generator does
its job, but the silly phrase that is generated looks pretty small compared to
the title. 

In case you didn't notice, the JSP adds a class to the phrase node.

	phrase.addClass('phrase');

This class was added inside of the `btnSubmit` function so that when the user
clicks the button to generate the silly phrase, any styling for the class is
added to the silly phrase before it is displayed. You can write styles for this
phrase node by opening your portlet's `main.css` file and adding styles like
these styles: 

	.phrase
	{
		font-size:200%;
		line-height:120%;
	}
                
Now when you generate a silly phrase, you'll see your new styles applied to the
phrase! 

Congrats! Your silly phrase generator is complete, and now you have a basic 
understanding of how to traverse and manipulate the DOM using AlloyUI. You can 
view all the methods covered in this tutorial, along with several other useful 
methods in the [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone).

## Related Topics [](id=related-topics)

 [Using AlloyUI Carousel](/develop/tutorials/-/knowledge_base/6-2/using-alloyui-carousel-in-a-portlet)

 [Liferay UI  Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)
