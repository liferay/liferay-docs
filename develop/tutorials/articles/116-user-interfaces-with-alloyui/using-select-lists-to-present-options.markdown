# Using Select Lists to Present Options [](id=using-select-lists-to-present-options)

Select lists are UI components that present collapsible groups of options. A
select list initially shows a single default option. When clicked, all the
options are displayed. When an option is selected, it collapses to display only
the newly selected option. It's handy for getting user input while conserving
UI real estate. 

This tutorial shows you how to implement select lists using the AlloyUI taglib
tags `aui:select` and `aui:option`. You'll learn how to add a select list to
your UI and how to retrieve the option a user selects. 

## Adding a Select List [](id=adding-a-select-list)

Here's how to implement select lists in a portlet's View using `aui:select` and
`aui:option` tags in a JSP file. 

1.  Create or edit a JSP file where you want to add a select list. 

2.  Reference the AlloyUI taglib `aui`: 

        <%@ taglib prefix="aui" uri="http://alloy.liferay.com/tld/aui" %>

3.  Add an `aui:select` element and name it. 

        <aui:select name="select-example">

        </aui:select>

    The `aui:select` tag's
    [reference documentation](https://docs.liferay.com/portal/6.2/taglibs/aui/select.html)
    describes all its attributes. 

4.  For each option listed in the select, insert an `aui:option` element between
    the starting and ending `aui:select` tags. Here's an example select list with
    three options: 

        <aui:select name="select-example">
            <aui:option value="Option 1">Option 1</aui:option>
            <aui:option value="Option 2">Option 2</aui:option>
            <aui:option value="Option 3">Option 3</aui:option>
        </aui:select>

    The `aui:option` tag's 
    [reference documentation](https://docs.liferay.com/portal/6.2/taglibs/aui/option.html)
    describes all its attributes. 

There you have it! Now you know how to create a select list and populate it with
options using `aui` taglib tags. It's important to note, however, this example
skims only the surface of the `aui:select` and `aui:option` tag capabilities. So
far, you've seen nothing that regular HTML can't do. There is more, however: the
`aui:select` tag's configurable attributes give you lots of flexibility to
customize select lists.  So, make sure to check out its [reference documentation](https://docs.liferay.com/portal/6.2/taglibs/aui/select.html). 

Adding the select list to your UI is first part of creating a fully functional
select list. Next, you'll learn how to implement retrieval of the selected
options. 

## Getting the Selected Option [](id=getting-the-selected-option)

There are a couple of ways to retrieve a select list's selected option.
Both are viable and achieve the same goal. One way does so within an
[`aui:script`](https://docs.liferay.com/portal/6.2/taglibs/aui/select.html)
element. The other way does so using JavaScript inside the portlet's
`main.js` file. Each approach has its benefits: the `aui:script` tag gives you 
access to the server side state and its utilities and methods; `main.js` gives 
you cacheability, a separation of concerns with your code, and is shareable 
across all portlets. Generally, you should write as much of your JavaScript as 
possible inside `main.js` so that the code can be cached. If, however, you 
need an initialization point for your code or have to pass data from the
server, you should write your JavaScript inside the `aui:script` tags. The 
sections that follow cover both techniques.

Up to this point, the select list has been shown as a stand-alone component.
Since select lists are typically used in forms, this tutorial demonstrates
implementing a fully functional select list in a form. Here's the example
form JSP code:

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

Next, you'll see how to retrieve a select list's selected option by leveraging
the AlloyUI modules in an `aui:script` tag inside a JSP. 

### Getting the Selected Option Using an AlloyUI Script [](id=getting-the-selected-option-using-an-alloyui-script)

This example retrieves the select list's selected option and displays the
option's value to the user in an alert: 

1.  Add an `aui:script` element in your JSP and set the `use` attribute to `node` 
    and `event`:

        <aui:script use="node, event">

        </aui:script>

    The `node` and `event` packages can be used to retrieve the button node and
    attach an event to it. 

2.  Inside the `aui:script` element, retrieve the event's node. In this example,
    the event is attached to a button that has an ID `btnSubmit`: 

        var btn = A.one('#btnSubmit');

    The `A.one` method retrieves the button with the ID `btnSubmit`. 
    This method returns the first matching node. 

3.  Add a variable to represent the select list node: 

        var option = A.one('#<portlet:namespace/>options')

    As with the button, the select list can be referenced by its ID. It's used
    as part of the argument value passed to the `A.one` method. Note the
    `portlet:namespace` tag is part of the ID. The namespace avoids conflicts
    with other elements and portlets on the same page. Since a unique namespace
    is associated with the select list's ID, the `portlet:namespace` tag is a
    required qualifier for retrieving the select list. Namespacing is discussed
    in greater detail later in this tutorial. You can also read more about it in
    the tutorial
    [Using Portlet Namespacing](/develop/tutorials/-/knowledge_base/6-2/using-portlet-namespacing).

4.  Below the variables you just declared, attach a function to the node for
    retrieving the selected option. The example here attaches a click event to the
    button node assigned to variable `btn`:

        btn.on('click', function(event){

        });

5.  Inside the function, retrieve the select list's selected option by 
    calling the `val` method. The example here uses the method `alert` to notify
    the user about the selected option: 

        alert(option.val());

    You can use the `val` method to get and set the `value` attribute of its
    node. Since this example calls the `val` method without parameters, it
    *gets* the value of the node. You can, alternatively, use the following code
    to achieve the same result: 

        alert(option.attr('value'));

    Note that while the `val` method can get only the value of the node's
    `value` attribute, the `attr` method can get the value of any of the node's
    attributes. To use the `attr` method to retrieve a node attribute's value,
    you must pass that attribute's name as the argument to the method. For
    example, `attr('value')` retrieves the value of the `value` attribute. The
    `attr` method can also be used to *set* the value of an attribute. You can do
    this by passing a second argument, which serves as the attribute's new
    value. For example, the following code sets `Selector:` as the value of the
    select list's `label` attribute: 

        option.attr('label', 'Selector:');

    If you're following along with the example in this tutorial, the finished 
    script looks like this:

        <aui:script use="event, node">

            var btn = A.one('#btnSubmit');       
            var option = A.one('#<portlet:namespace/>options');

            btn.on('click', function(event){
                alert(option.val());
            });

        </aui:script>

6.  Redeploy your portlet, select an option from the select list, and perform 
    the action that calls your script. For this example, click the *Submit* 
    button after selecting an option from the select list. 

There you go! As you can see, it takes little effort to implement an AlloyUI
script to retrieve a select list's selected option. Next, you'll learn how to
achieve the same results using JavaScript in your portlet's `main.js` file. 

### Getting the Selected Option Using a JavaScript File [](id=getting-the-selected-option-using-a-javascript-file)

You can also use JavaScript in your `main.js` file to retrieve a select
list's selected option. Because the `main.js` file is loaded after the JSP, you
have to pass the `portlet:namespace` to the `main.js` file. The following steps
leverage the example form shown at the start of this section to demonstrate how
to get a select list's selected option: 

1.  Open the `main.js` file from your portlet's `docroot/js/` folder. 

2.  Add the following code to your `main.js` file. The `init` function retrieves 
    the portlet namespace, while the `get` function retrieves the value of the 
    select list's selected option: 

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

    Make sure to replace `your-portlet-name` with your portlet's name and
    `options` with your `aui:select` element's ID value. 

    The `init` function is attached as a method to `Liferay.yourportletname`.
    The namespace is set up to be passed as the argument for the `init`
    function's parameter. The `instance._namespace` variable is assigned the
    namespace. The `get` function uses this variable to add the
    `portlet:namespace` to the select list's ID. Note that the `instance`
    variable is declared and set to keyword `this`. 

3.  From within the JSP containing your select list, you must link to the
    JavaScript in `main.js` the component that produces the event (e.g., a
    button). 

    The JSP in the example form has the following button:

        <aui:button value="Submit" onClick="get()" id="btnSubmit"/>

    Using an AlloyUI script in the select list's JSP, you can register to
    receive the event. From within an AlloyUI script, you can initialize the
    JavaScript you wrote in `main.js` and make accessible its function that gets
    the selected option. 

    For example, here's an AlloyUI script that calls the JavaScript from the
    previous step: 

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

    When setting the `aui:script`, make sure to set its `use` attribute to 
    `aui-base, event, node`. Also make sure to replace `yourportletname` with
    your portlet's name and set `btnSubmit` to your button's ID. 

    The `A.one` method uses the button's ID `btnSubmit` to retrieve the button.
    The `init` function is called inside the script and sets the namespace to
    `<portlet:namespace/>`. The `get` variable is then set to the `get` method
    you created in `main.js`. Make sure to match the name of the function you
    implemented to handle the select option to the name of the function
    associated with your button. 

5.  Redeploy the portlet, select an option from the select list, and perform the 
    action that calls the method. If you're following along with this example, 
    the method is called when you click the *Submit* button.

Congratulations! Now you know how to retrieve the options users choose from your
portlet's select lists. This tutorial's example demonstrates only getting the
option and alerting the user of that option. You can, however, build off of
these principles to use the select list's input in your app's business logic. To
learn how to do this, check out the article
[Writing Your First Liferay Application](/develop/tutorials/-/knowledge_base/6-2/writing-your-first-liferay-application)
in the Learning Path
[Beginning Liferay Development](/develop/tutorials/-/knowledge_base/6-2/beginning-liferay-development)
or dive in to the Learning Path
[Writing a Data-Driven Application](/develop/tutorials/-/knowledge_base/6-2/writing-a-data-driven-application).

## Related Topics [](id=related-topics)

[Beginning Liferay Development](/develop/tutorials/-/knowledge_base/6-2/beginning-liferay-development)

[Writing a Data-Driven Application](/develop/tutorials/-/knowledge_base/6-2/writing-a-data-driven-application)

[User Interfaces with the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

[Customizing Liferay Portal with Hooks](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
