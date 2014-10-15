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

Alright! You can now use the aui tags in your JSP. Onward to step 2.

### Step 2: Configure the Select Tag in the View JSP

Inside the `view.jsp` of your portlet add an `<aui:select>` tag and give it a 
`name`:

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
AlloyUI's tags. The configuration above only skims the surface of the power of 
the Select tag.  

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
    <aui:select label="Select Option:" id="options" name="selectField1" 
    required="true" showEmptyOption="true">
        <aui:option value="Option 1">Option 1</aui:option>
        <aui:option value="Option 2">Option 2</aui:option>
        <aui:option value="Option 3">Option 3</aui:option>
    </aui:select>
    <aui:button value="Submit" onClick="get()" id="btnSubmit"/>
    </aui:fieldset>
    </aui:form>

As stated in the beginning of this tutorial, there are a couple methods you can 
use to retrieve the value of the selected option. The first method shows how you 
can retrieve the selected option inside of a JSP. The second method shows how 
you can retrieve the selected option inside of the `main.js` of your portlet; 
both methods are viable solutions and achieve the same goal. The steps below 
will cover the two methods discussed.

### Retrieving the Option Inside of a JSP

One way to retrieve the value of the option selected is to use an `aui:script` 
inside of your JSP. 

The example below shows how to attach an `onclick` event to a button node to 
alert the selected option to the user. Follow the steps below to learn the 
process.

1.  Add the `<aui:script>` in your `view.jsp` and configure the `use` attribute.

        <aui:script use="node, event">
        
        </aui:script>

    The `use` attribute is configured to use the `node` and `event` packages
    since you will be grabbing your button node and attaching an event to it.
    Now that the foundation is set you can move onto the next step.
    
2.  Add a variable inside of the `aui:script` tag to represent the node that the
    event will be attached to. The example below grabs a button node.

        var btn = A.one('#btnSubmit');
        
    The code above grabs the `aui:button` by referencing its id `btnSubmit`. The
    `A.one` method is used to grab the first element returned with the matching
    id given as the parameter. Now that you have a variable for your button, you
    can create one for your select field next.
    
3.  Add a variable inside of your aui script to stand for your select field
    node.

        var select = A.one('#<portlet:namespace/>options')
        
    Like the previous step, the select field has been referenced using its id. 
    One important difference to note is that the `<portlet:namespace/>` tag has 
    been added to the beginning of the id. Namespacing is a key topic that will 
    be addressed throughout the remainder of the tutorial. The `<aui:select>` 
    tag automatically appends a unique namespace to the beginning of the id 
    given, to avoid potential conflicts between other elements and portlets on 
    the same page. The `<portlet:namespace/>` tag adds the unique namespace to 
    the id to complete it.

    +$$$

    **Note:** You can read more about namespacing in the DevGuide 
    [Using Portlet Namespacing](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/passing-info-from-action-to-render-phase-liferay-portal-6-2-dev-guide-en/)
    section.

    $$$
    
    Now that you have your variables taken care of, you can write your function 
    next.
    
4.  Attach a function to the node that will get the option and add it below the 
    variables you just declared. The example belows attaches a click event to 
    the button node.

        btn.on('click', function(event){
        
        });
        
    An onclick event is attached to the button which was just set to the `btn`
    variable. With the function set, you can add the code for it next. For now,
    you can alert the value to the user.
    
5.  Retrieve the option selected by calling the `val()` method for your select 
    field node. The example below alerts the value to the user.

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
        
    Using the example above, your finished script should look like the following 
    code:
    
        <aui:script use="event, node">
            
            var btn = A.one('#btnSubmit');
            var option = A.one('#<portlet:namespace/>options');

            btn.on('click', function(event){
                alert(option.val());
            });
            
        </aui:script>
    
6.  Redeploy the portlet, *select* an option, and call your script. If you're 
    following the example, *click* Submit to alert the option.
    
There you go! As you can see, it does not take a lot to retrieve the selected 
option inside of a JSP. Next you can learn how to achieve the same results in
your `main.js` file.

## Retrieving the Option Selected Inside of your Main.js

In addition to retrieving the option in your JSP, you can also retrieve the 
selected option in your `main.js`. Because the `main.js` file is loaded after 
the JSP, you have to pass the `portlet:namespace` to the `main.js` file. The 
example below shows how to pass the `portlet:namespace` to your `main.js` so you 
can retrieve the option selected.

1.  Open the `main.js` file in your portlet's `docroot/js/` directory.

2.  Add a function to get the portlet namespace. The example below uses an 
    `init()` function to get the portlet namespace:

    AUI().use(
        'aui-base',
        function (A){
            Liferay.namespace('your-portlet-name');
            
            Liferay.yourportletname = {
                init: function(config){
                    var instance = this;
                    
                    instance._namespace = config.namespace;
            },
            
        };
    }
    );
    
    The `init()` function is attached as a method to `Liferay.yourportletname`. 
    The namespace is setup to be passed in as the argument for the config 
    parameter and set to the `instance._namespace` variable. Now that the 
    namespace is passed in the `init()` method, you can call it in your 
    portlet's JSP. Before you do that though, you will need to add the function 
    that will alert the option chosen by your user.
    
3.  Add a function inside of the `AUI.use` sandbox to get the value of the
    option selected. The example below alerts the value to the user:

        get: function(){
            var instance = this;
            var option = A.one('#' + instance._namespace + 'options');
            alert(option.attr('value'));
            },
            
    The `get()` function uses the `instance._namespace` variable to add the 
    `portlet:namespace` to the select field's id. Note that the `instance` 
    variable is declared inside of the `get()` function as well and set to the 
    `this` keyword. Now that your functions are written, you can call them 
    in your portlet's JSP.
    
4.  Open one of your portlet's JSPs and add an `<aui:script>` to call the 
    functions you just wrote and configure the `use` attribute to use the
    `aui-base`, `event`, and `node` packages.
    
        <aui:script use="aui-base, event, node">
        var btn = A.one('#btnSubmit');
        
        Liferay.yourportletname.init(
            {
                namespace: '<portlet:namespace/>'
            }
        );
        
        get = function(){
        Liferay.yourportletname.get();
        }
        
    The `init()` function is called inside of the JSP and sets the namespace 
    to the `<portlet:namespace/>`. The get variable is set to the `get()` 
    method that was created in the `main.js`. One last step to go!
    
5.  Attach the function that will retrieve the option to the node that will call 
    it. The example below sets the `get()` method to a `<aui:button>`'s 
    `onClick` attribute:

                <aui:button value="Submit" onClick="get()" id="btnSubmit"/>

8.  Redeploy the portlet, *select* an option, and call the method. If you're 
    following along with the example, *click* Submit to see your option alerted 
    to you.
    
Congratulations! Now you know how to retrieve the selected option. As you can 
see, AlloyUI provides some pretty powerful tags to help make your UI creation a 
smooth process.

## Related Topics

 [Using AlloyUI Carousel in a Portlet](/tutorials/-/knowledge_base/6-2/using-alloyui-carousel-in-a-portlet)
 
 [Using Liferay UI Tabs and Sections](/tutorials/-/knowledge_base/6-2/using-liferay-ui-tabs-and-sections)
