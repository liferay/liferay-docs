# Using the AlloyUI aui:select and aui:option Tags

Select fields, also known as drop-down menus or lists, are very handy tools that 
provide your users wih several options to choose from. Unlike radio buttons, 
which display options in a static list, select fields display all of their 
available options in a collapsible menu. AlloyUI provides the `aui:select` and 
`aui:option` tags for creating select fields. 

This tutorial covers how to add and use the `aui:select` and `aui:option` tags. 
The first section covers how to implement select fields in your UI with the 
`aui:select` and `aui:option` tags. The second section shows you how to retrieve 
the values of the options selected in the UI. After all, select fields aren't 
very useful if your application can't tell what is selected! 

## Setting Up the AlloyUI aui:select and aui:option Tags

The example in this tutorial shows you how to implement select fields by using 
the `aui:select` and `aui:option` tags in the `view.jsp` of a portlet. The 
following steps are used:

- **Step 1:** Reference the aui Taglib.
- **Step 2:** Add the aui:select Tag in the View JSP.
- **Step 3:** Configure the aui:option Tags for your Select Field.

### Step 1: Reference the aui Taglib

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
    exist.

2.  Add a directive to reference the `aui` taglib:

        <%@ taglib prefix="aui" uri="http://alloy.liferay.com/tld/aui" %>

Alright! You can now use the `aui` tags in your JSP. Next, you'll add the 
`aui:select` tags to your JSP.

### Step 2: Add the aui:select Tag in the View JSP

Inside the `view.jsp` of your portlet, add the `aui:select` tag and give it a 
`name`:

        <aui:select name="select-example">

        </aui:select>

It's important to note that the `name` attribute is required for the 
`aui:select` tag. Now that you created your select field, you can add its 
options.

### Step 3: Configure the aui:option Tags for Your Select Field

Still inside the `view.jsp`, nest the `aui:option` tags inside of the 
`aui:select` tag:

        <aui:select name="select-example">
            <aui:option value="Option 1">Option 1</aui:option>
            <aui:option value="Option 2">Option 2</aui:option>
            <aui:option value="Option 3">Option 3</aui:option>
        </aui:select>

This example implements three options. Of course, you can have as many options 
as you want. There you have it! Now you know how to create a select field and 
populate it with options using AlloyUI's tags. However, it's important to note 
that the configuration above only skims the surface of the power of the 
`aui:select` tag.

What makes AlloyUI's tag library so robust is that each tag has a set of 
configurable attributes that allow for quite a bit of flexibility. The list 
below shows a few of the available attributes that you can customize for the 
`aui:select` tag. You can find the full list [here](http://docs.liferay.com/portal/6.2/taglibs/).

**first:** Sets whether to mark this as the first element in the form. The 
default value is false. 

**helpMessage:** Creates a help tooltip for the help icon and displays the given 
string as a pop-up dialog. 

**label:** Sets the select field's label and displays it above the field.

**last:** Sets whether to mark this as the last element in the form. The default 
value is false.

**onChange:** Sets a function to be called on a change in value of the select 
field.

**onClick:** Sets a function to be called when users click the select field.

**prefix:** Sets the text to be displayed to the left of the select field.

**required:** Sets whether to mark the field as required. The default value is 
false.

**showEmptyOption:** Sets whether to display an empty option as a placeholder. 
The default value is false.

**suffix:** Sets the text to be displayed to the right of the select field.

Now that you have a better understanding of the attributes available to you for 
the `aui:select` tag, here are some helpful attributes to consider when 
configuring the `aui:option` tag:

**label:** Sets the text to be displayed as the option in the select field.

**selected:** Sets whether the option is selected when the component loads. The 
default value is false.

**value:** Sets the option's value. If no value is given, the value is taken 
from the `label` attribute.

Creating the select field and adding options to it is a great start, but it's 
really just the first step in the process. Next, you need to have your 
application retrieve the option your user chooses. A select field isn't much 
good without this capability!

## Retrieving the Selected Option from Your Select Field 

There are a couple of different ways you can retrieve the value of the selected 
option in a select field. Both are viable solutions and achieve the same goal. 
The first is done with an `aui:script` inside of your JSP. The second is done 
with a bit of JavaScript inside the `main.js` of your portlet. The steps below 
cover both.

It's also important to note that up to this point, the select field has been 
shown as a stand alone field in your JSP. However, select fields typically 
appear in forms. As such, this section uses the following as an example:

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

Now go ahead and get started by using an `aui:script` inside of your JSP to 
retrieve the value of the options in your select field.

### Using an AlloyUI Script to Retrieving the Selected Option

The example here alerts the user with the selected option by attaching an 
`onClick` event to a button node. Follow these steps to learn the process:

1.  Add the `<aui:script>` in your `view.jsp` and set the `use` attribute to 
    `node` and `event`:

        <aui:script use="node, event">
        
        </aui:script>

    The values `node` and `event` are set since you are grabbing your button 
    node and attaching an event to it.
    
2.  Add a variable inside of the `aui:script` tag to represent the node that the
    event is attached to. This example is attached to a button node:

        var btn = A.one('#btnSubmit');
        
    The `A.one` method grabs the `aui:button` by referencing the button's id 
    `btnSubmit`. This method grabs the first element returned.

3.  Add a variable inside of the `aui:script` tag to stand for your select field
    node.

        var option = A.one('#<portlet:namespace/>options')
        
    Like the previous step, the select field is referenced with an id as the 
    parameter to the `A.one` method. One important difference to note is that 
    the `<portlet:namespace/>` tag is present as part of the id. Namespacing is 
    a key topic that is addressed throughout the remainder of the tutorial. The 
    `aui:select` tag automatically appends a unique namespace to the id given. 
    This is done to avoid potential conflicts between other elements and 
    portlets on the same page. The `<portlet:namespace/>` tag adds this unique 
    namespace to the id to complete it. You can read more about namespacing in 
    the tutorial [Using Portlet Namespacing](/tutorials/-/knowledge_base/6-2/using-portlet-namespacing).

4.  Below the variables you just declared, attach a function to the node that 
    retrieves the selected option. The example here attaches a click event to 
    the button node:

        btn.on('click', function(event){
        
        });
        
    The click event is attached to the button that was previously set to the 
    `btn` variable.
    
5.  Inside the function, retrieve the option selected in the select field by 
    calling the `val()` method for your select field node. The example here 
    alerts the user of the selected value:

        alert(option.val());
        
    The `val()` method can be used to get and set the `value` attribute of the 
    node it's attached to. Since the `val()` method in this example is called 
    without parameters, it gets the value of the option selected in the select 
    field. Alternatively, you can use the following code to achieve the same 
    result:
    
        alert(option.attr('value'));
        
    Like the `val()` method, the `attr()` method also grabs the value of the
    selected option. However, while the `val()` method grabs only the `value` 
    attribute of the node it's attached to, the `attr()` method can grab the 
    value of any attribute of the node. To use the `attr()` method to retrieve 
    the value of an attribute, you must pass that attribute as the argument to 
    the method. For example, `attr('value')` retrieves the value of the `value` 
    attribute. The `attr()` method can also be used to set the value of the 
    attribute if a second parameter is given. For example, the following code 
    sets the select field's `label` attribute:
    
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
    
6.  Redeploy your portlet, select an option, and perform the action that calls 
    your script. If you're following along with this specific example, click the 
    *Submit* button after selecting an option from the select field.

There you go! As you can see, it doesn't take an excessive amount of effort to 
retrieve the selected option inside of a JSP. Next, you can learn how to achieve 
the same results by using some JavaScript in your `main.js` file.

## Using JavaScript to Retrieve the Selected Option

Alternatively, you can use JavaScript in your `main.js` file to retrieve the 
selected option. Because the `main.js` file is loaded after the JSP, you have to 
pass the `portlet:namespace` to the `main.js` file. The example here shows how 
to accomplish this.

1.  Open the `main.js` file from your portlet's `docroot/js/` directory.

2.  Add the following code to your `main.js` file. The `init` function retrieves 
    the portlet namespace, while the `get` function retrieves the value of the 
    option selected in the select list:

        AUI().use(
            'aui-base',
            function (A){
                Liferay.namespace('your-portlet-name');
            
                Liferay.yourportletname = {
                
                    init: function(config){
                        var instance = this;
                        instance._namespace = config.namespace;
                    },
            
                    get: function(){
                        var instance = this;
                        var option = A.one('#' + instance._namespace + 'options');
                        alert(option.attr('value'));
                    },
            
                };
            }
        );
    
    The `init` function is attached as a method to `Liferay.yourportletname`. 
    The namespace is set up to be passed in as the argument for the config 
    parameter. The namespace is also set to the `instance._namespace` variable. 
    The `get` function uses this variable to add the `portlet:namespace` to the 
    select field's id. Note that the `instance` variable is declared and set 
    inside of the get() function to the `this` keyword. 
    
3.  Now you're ready to call the JavaScript you just wrote from one of your 
    portlet's JSPs. This is done by adding an `aui:script` to your JSP. For 
    example, here is an `aui:script` that calls the JavaScript from the previous 
    step:
    
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
        </aui:script>
        
    When setting the `aui:script`, make sure that you set its `use` attribute to 
    `"aui-base, event, node"`. Also note that the `init` function is called 
    inside the script and sets the namespace to `<portlet:namespace/>`. The get 
    variable is then set to the `get` method you created in `main.js`. 
    
4.  There's just one last thing to check before you're done. If you're following 
    along with this example, then this is already done and you can redeploy and 
    test your portlet. Make sure that you've attached the function that 
    retrieves the select list's option to the node that calls it. For example, 
    this is done here by setting the `get()` method to a button's `onClick` 
    attribute:

                <aui:button value="Submit" onClick="get()" id="btnSubmit"/>

5.  Redeploy the portlet, select an option from the select list, and perform the 
    action that calls the method. If you're following along with this example, 
    the method is called when you click the *Submit* button.
    
Congratulations! Now you know how to retrieve the option that your users choose 
from the select fields in your portlets. Of course, the example here just alerts 
you of that choice--it doesn't actually do anything else with it. You'll want to 
utilize it to perform other actions in your portlet, or across the portal as a 
whole.

## Related Topics

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Themes and Layout Templates](/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/tutorials/-/knowledge_base/6-2/application-display-templates)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
