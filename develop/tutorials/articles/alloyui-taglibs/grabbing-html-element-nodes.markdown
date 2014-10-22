# Grabbing HTML Element Nodes

In this tutorial, you'll learn how you can use AlloyUI to grab HTML elements in 
the DOM (Document Object Model). In order to manipulate HTML elements, you must 
first have access to the node object of the HTML elements. Code snippets from 
the [Phrase Generator Portlet](../../code/alloy/silly-phrase-generator-portlet) 
will be used as examples during the tutorial.

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

**.class:** Selects nodes with the given class name. For example
`A.one('.wrapper')` returns the first element with the class='wrapper'.

**element:** Selects nodes that match the given element. For example 
`A.all('p')` returns all the paragraph elements.

**element[attribute=value]:** Selects nodes that match the given attribute with 
the set value. For example `A.one('div[title=section]')` returns the first div 
element with the title='section'.

**#id:** Selects nodes that match the given id. For example 
`A.one('#container')` returns the first element with the id='container'.

+$$$

**Note:** You can find a full list of the available selectors in the 
 [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#selectors).

$$$

In most cases, you will want to assign the element node to a variable.

Here's a code snippet from the Phrase Generator Portlet's [view.jsp](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/alloy/silly-phrase-generator-portlet/docroot/view.jsp):

        <aui:script use="event, node">
          var btnSubmit = A.one("#submit");
        </aui:script>

In some cases you may need to include the portlet namespace in your selector. 
Many AlloyUI tags add the portlet namespace to the tag by default. In those
cases, you will need to add the `<portlet:namespace/>` tag before the selector.
Here's a code snippet from the Phrase Generator Portlet's [view.jsp](https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/code/alloy/silly-phrase-generator-portlet/docroot/view.jsp):

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

