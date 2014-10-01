# Using the AlloyUI Select and Option Tags

Select fields(sometimes referred to as a drop-down menu or list) are very handy 
tools that provide your users wih several options to choose from. Unlike radio 
buttons, which display options in a static list, select fields display all of 
their available options in a collapsible menu. AlloyUI provides a couple tags to 
create the select field UI: the `aui:select` and `aui:option` tags. 

This tutorial covers how to add and use the AUI Select and Option tags. It is
broken into two sections. The first section covers how to setup the Select
tag and configure options for it. The second half covers a couple methods that
you can use to retrieve the values of the options selected.

Go ahead and get started with the configuration next.

## Setting Up the AlloyUI Select and Option Tags

The example below shows how to add and use the `aui:select` and `aui:option`
tags in the `view.jsp` of a portlet:

- **Step 1:** Reference the aui Taglib.
- **Step 2:** Configure the Select Tag in the View JSP.
- **Step 3:** Configure the Option Tags for your Select Field.
 	
Go through each of these steps to get your select field up and running.

### Step 1: Reference the aui Taglib

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
    exist.

2.  Add a directive to reference the `aui` taglib:

        <%@ taglib prefix="aui" uri="http://alloy.liferay.com/tld/aui" %>

You can now use the aui tags in your portlet! Continue onward to step 2.

### Step 2: Configure the Select Tag in the View JSP

Inside the `view.jsp` of your portlet add the `aui:select` tags at the bottom:

        <aui:select name="select-example">

        </aui:select>

It is important to note that the `name` attribute is required for the Select tag. 
Now that you have your select field created, you can add your options next.

### Step 3: Configure the Option Tags for your Select Field

Still inside the `view.jsp` nest the `aui:option` tags inside of the 
`aui:select` tags:
    
        <aui:select name="select-example">
            <aui:option value="Option 1">Option 1</aui:option>
            <aui:option value="Option 2">Option 2</aui:option>
            <aui:option value="Option 3">Option 3</aui:option>
        </aui:select>

Repeat the step above to add as many options as you need. There you have it! Now
you know how to create a select field and populate it with options using 
AlloyUI's tags. You could stop there, but the configuration above only skims the 
surface of the power of the Select tag.  

What makes AlloyUI's tag library so robust is that each tag has a set of 
configurable attributes that allow for quite a bit of flexability to accommodate 
your needs. The list below shows a few of the available attributes that you can
customize(the full list can be found [here](http://docs.liferay.com/portal/6.2/taglibs/)

**first:** Sets whether to mark this as the first element in the form. The 
default value is false.

**helpMessage:** Creates a help tooltip and displays the String given as a 
pop-up dialog on mouse over of the help icon. 

**label:** Sets the select field's label and displays it above the field.

**last:** Sets whether to mark this as the last element in the form. The default 
value is false.

**onChange:** Sets a function to be called on a change in value of the select 
field.

**onClick:** Sets a function to be called on users clicking the select field.

**prefix:** Sets the text to be displayed to the left of the select field.

**required:** Sets whether to mark the field as required. The default value is 
false.

**showEmptyOption:** Sets whether to display an empty option as a placeholder. 
The default value is false.

**suffix:** Sets the text to be displayed to the right of the select field.

Now that you have a better understanding of the attributes available to you for 
the Select tag, here are some helpful attributes to consider when configuring 
the Option tag:

**label:** Sets the text to be displayed as the option in the select form.

**selected:** Sets whether the option is selected when the component loads. The 
default value is false.

**value:** Sets the option's value. If no value is given, the value is taken 
from the label attribute.

Knowing how to create the select field and add options to it is a good start,
but it is only really the first step in the process. Now that you know how to
create the select field and provide options, you can learn how to retrieve the
option your user chose next.

## Retrieving the Selected Option from Your Select Field 

Up to this point, the select field has been shown in the context of a stand
alone field by itself; this would not typically be the environment it would be
placed in. This section will assume that the context is inside of a form. The
example below will be referenced throughout the rest of the tutorial:

    <aui:form>
    <aui:fieldset>
    <aui:select label="Select Option:" id="options" name="selectField1" required="true" showEmptyOption="true">
        <aui:option value="Option 1">Option 1</aui:option>
        <aui:option value="Option 2">Option 2</aui:option>
        <aui:option value="Option 3">Option 3</aui:option>
    </aui:select>
    <aui:button value="Submit" onClick="get()" id="btnSubmit"/>
    </aui:fieldset>
    </aui:form>


As stated in the beginning of this tutorial, there are a couple methods you can 
use to retrieve the value of the selected option. The first method uses an
`<aui:script>` and the aui `event` and `node` packages to grab the button node
and retrieve the value using an on click function. The second method uses the 
`onClick` attribute of the button to call a JavaScript function to retrieve the 
value; both methods are viable solutions and achieve the same goal. There may be
other solutions to retrieve the value of the option selected, but the steps
below will cover the two methods discussed.

### Retrieving the Value Using AlloyUI

One way to retrieve the value of the option selected is to use an `aui:script`.
Go through the steps below to learn the process.

1.  Add the `<aui:script>` tags to the top of your `view.jsp`, just above your
    `aui:select` tags:

        <aui:script use="node, event">
        
        </aui:script>

    The `use` attribute is configured to use the `node` and `event` packages
    since you will be grabbing your button node and attaching an event to it.
    Now that the foundation is set you can move onto the next step.
    
2.  Add a variable inside of the `aui:script` tag to represent your button node:

        var btn = A.one('#btnSubmit');
        
    The code above grabs the `aui:button` by referencing its id `btnSubmit`. The
    `A.one` method is used to grab the first element returned with the matching
    id given as the parameter. Now that you have a variable for your button, you
    can create one for your select field next.
    
3.  Add a variable just below the `btn` variable to stand for your select field:

        var select = A.one('#<portlet:namespace/>options')
        
    Like the previous step, the select field has been referenced using its id. 
    One important difference to note is that the `<portlet:namespace/>` tag has 
    been added to the beginning of the id. Namespacing is a key topic that will 
    be addressed throughout the remainder of the tutorial. The `aui:select` tag
    automatically appends a unique namespace to the beginning of the id given,
    to avoid potential conflicts between other elements and portlets on the same 
    page. The `<portlet:namespace/>` tag adds the unique namespace to the id to 
    complete it.

+$$$

**Note:** You can read more about namespacing in the DevGuide 
[Using Portlet Namespacing](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/passing-info-from-action-to-render-phase-liferay-portal-6-2-dev-guide-en/)
section.

$$$
    
    Now that you have your variables taken care of, you can write your function
    next.
    
4.  Add the following function below the variables you just declared:

        btn.on('click', function(event){
        
        });
        
    An onclick event is attached to the button which you just set to the `btn`
    variable. With the function set, you can add the code for it next. For now,
    you can alert the value to the user.
    
5.  Add the `alert()` method inside of the onclick function:

        alert(option.val());
        
    The value of the option selected is grabbed using the `val()` method
    which can be used to get and set the `value` attribute of the node it's 
    attached to. Since no arguments are given as parameters for the `val()` 
    method in this case, the method gets the value of the option selected in the
    select field. Alternatively you could also use the code below to achieve the
    same result:
    
        alert(option.attr('value'));
        
    Like the previous method, the `attr()` method also grabs the value of the
    option selected. Whereas the `val()` method grabs only the value attribute
    of the node it's attached to, the `attr()` method grabs the value of the
    attribute passed as the argument. The attr() method can also be used to set
    the value of the attribute if a second parameter is given. For example, the
    following code would set the select field's `label` attribute:
    
        option.attr('label', 'Selector:');
        
    Using the AlloyUI method to retrieve the selected option, your finished
    script should look like the following code:
    
        <aui:script use="event, node">
            
            var btn = A.one('#btnSubmit');
            var option = A.one('#<portlet:namespace/>options');

            btn.on('click', function(event){
                alert(option.val());
                alert('first');
            });
            
        </aui:script>
    
6.  Redeploy the portlet, *select* and option, and *click* Submit to alert the
    option.
    
There you go! As you can see, it does not take a lot to retrieve the selected 
option using AlloyUI. Next you can learn how to achieve the same results using 
JavaScript.

## Retrieving the Value Using JavaScript

In addition to using AlloyUI, JavaScript can be used to retrieve the selected
option. Instead of attaching an onclick function to the button node as was done 
previously, you can configure the button's `onClick` attribute to call a 
function. The AlloyUI method that was illustrated earlier used the 
`<portlet:namespace/>` tag to complete the id for the select field; the 
JavaScript process is a bit different. Best practice is to place the JavaScript 
in the `main.js` file that was generated with the portlet in the `docroot/js/`
directory. Since the JavaScript will be called outside of the JSP, the
`<portlet:namespace/>` tag can't be used. The `main.js` file gets rendered after 
the JSP, and therefore the `<portlet:namespace/>` tag will be converted and 
rendered as the namespace at that point. Instead, the namespace will need to be 
passed to `main.js` as a variable before it gets rendered. Follow the steps 
below to learn how to retrieve the selected option.

1.  Configure your `<aui:button>` tag inside the `view.jsp` so that it looks 
    like the code below:

        <aui:button value="Submit" onClick="get()" id="btnSubmit"/>

    In addition to the `onClick` attribute, an id has been added so that it can
    be referenced later on. The `onClick` attribute is set to call the `get()`
    function which doesn't currently exist. You'll write that next.
    
2.  Remove the `<aui:script>` tags and all of the code between them if you
    haven't already. Add the `<script>` tags in their place:
    
        <script>
            function get(){
            
            }
        </script>

    With the script foundation written, you can add the code next.

3.  Add a variable inside the `get()` function to stand for the namespace of the 
    portlet:

        var name = `<portlet:namespace/>`;

    Now that you have a variable set to the namespace, you can access it in
    `main.js`. First you'll need to pass it as an argument for the function to 
    be called in `main.js` when the button is clicked.
    
4.  Add the following code to the `get()` function to call a new function:

        init(space);

    The `space` variable which is set to the portlet namespace has been passed
    as an argument for the `init()` function. You can write the `init()`
    function next.
    
5.  Open the `main.js` file and add the following code:

        function init(config){

        }

    The `init()` function has been given `config` as a parameter. Since the
    namespace was passed as an argument for the `init()` function in the 
    previous step, it will take the place of the `config` variable.
    
6.  Add the following code to the `init()` function:

        var option = document.getElementById(config + 'options');
        var value = option.options[option.selectedIndex].text;
        
    The select field is assigned the `option` variable and referenced by its
    id. The `config` variable is added to the id to append the namespace that 
    was passed earlier in the `view.jsp` and complete it. Next, the value of
    the selected option text is assigned the `value` variable. One last step to
    go.
    
7.  Add the `alert()` to the `init()` function:

        alert(value);
        
    For now, the value of the option selected is alerted to the user to
    illustrate the method in the example above. Your finished `init()` function
    should look like the following code:
    
        function init(config){
	
            var option = document.getElementById(config + 'options');
            var value = option.options[option.selectedIndex].text;
	
            alert(value);
        } 

8.  Redeploy the portlet, *select* an option, and *click* Submit to see your
    option alerted to you.
    
Congratulations! Now you know how to retrieve the selected option. As you can 
see, AlloyUI provides some pretty powerful tags to help make your UI creation a 
smooth process. You can add two new methods to your developer toolkit!

## Related Topics

 [Using AlloyUI Carousel in a Portlet](http://dev.liferay.com/tutorials/-/knowledge_base/using-alloyui-carousel-in-a-portlet)
 
 [Using Liferay UI Tabs and Sections](http://dev.liferay.com/tutorials/-/knowledge_base/using-liferay-ui-tabs-and-sections)
