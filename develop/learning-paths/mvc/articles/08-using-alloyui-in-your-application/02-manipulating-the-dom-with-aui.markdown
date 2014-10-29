# Manipulating the DOM with AlloyUI [](id=manipulating-the-dom-with-alloyui)

In the last section, you used AlloyUI to implement form validation on several
fields. In this section, you'll learn how to use AlloyUI to manipulate HTML
elements in the DOM (Document Object Model). You'll add several buttons to the
Add Entry form that, when clicked, add or remove elements from the DOM or
automatically populate some of the form fields. First, you'll create buttons
that populate the name and email fields of the Add Entry form with the full
name and email address of the current user. Then you'll create a button that
generates sample guestbook entry messages. The user can select a message and
the message field is populated with the selected message. To achieve
these goals, you'll use the AlloyUI Node module for DOM manipulation and the
AlloyUI Event module to create event listeners.

## Creating Populate Buttons for the Name and Email Fields [](id=creating-populate-buttons-for-the-name-and-email-fields)

To keep the Add Entry form as clean as possible, you'll create a collapsible
panel where you'll add the buttons for populating the form fields. Add the
following lines to your project's `docroot/html/guestbook/edit_entry.jsp` file:

    <liferay-ui:panel 
        defaultState="closed" 
        extended="<%= false %>" 
        id="populatePanel" 
        persistState="<%= true %>" 
        title="populate">

            <c:if test="<%= themeDisplay.isSignedIn() %>">
                    <aui:button-row>
                            <aui:button 
                                id="useNameButton" 
                                value="Use My Full Name">
                            </aui:button>
                    </aui:button-row>
            </c:if>
            
            <c:if test="<%= themeDisplay.isSignedIn() %>">

                    <aui:button-row>
                            <aui:button 
                                id="useEmailButton" 
                                value="Use My Email Address">
                            </aui:button>
                    </aui:button-row>

            </c:if>

    </liferay-ui:panel>

The `<liferay-ui:panel>` tag creates a collapsible panel. The following
attributes are useful here:

- `defaultState="closed"`: sets the panel to be closed (collapsed) by default
- `extended="<%= false %>"`: sets the panel not to extend horizontally
- `id="populatePanel"`: specifies a CSS ID for the panel
- `persistState="<%= true %>"`: sets the panel to "remember" the state
  (collapsed or expanded) chosen by the user
- `title="populate"`: sets the title of the panel to *populate*

It's a best practice to use language keys for titles so that they can be
localized. Open your project's `docroot/WEB-INF/src/content/Language.properties`
file and add the following line:

    populate=Populate

Note the `c:if test="<%= themeDisplay.isSignedIn() %>">` tags surrounding the
buttons in the panel that you added. The *Use My Full Name* and *Use My Email
Address* buttons should only appear for signed-in (non-guest) users. The portal
doesn't know the full names or email addresses of guest users, so the buttons
could never work for guests! The `themeDisplay` object is made available by the
`<theme:defineObjects />` tag which is called in `init.jsp`. Using the
`<aui:button-row>` and `<aui:button>` tags lets you take advantage of AlloyUI
button styling.

Test your update by clicking on the Add Entry form of the Guestbook portlet.
Confirm that the panel appears, that its title is *Populate*, and that the
*Use My Full Name* and *Use My Email Address* buttons appear when you're signed
in.

![Figure 1: This is how the Add Entry form should appear with the Populate panel expanded.](../../images/guestbook-populate-use-buttons.png)

Now it's time to make the buttons work. Add the following lines near the bottom
of `edit_entry.jsp`, just above the `aui:script use="aui-char-counter">` tag:

    <c:if test="<%= themeDisplay.isSignedIn() %>">
        <%
        String fullName = user.getFullName();

        String emailAddress = user.getEmailAddress();
        %>

        <aui:script use="node, event">
        var fullName = '<%= fullName %>';

        var useNameButton = A.one('#useNameButton');

        useNameButton.on('click', function(event) {
                var name = A.one('#<portlet:namespace/>name');
                
                name.val(fullName);
        });

        var emailAddress = '<%= emailAddress %>';

        var useEmailButton = A.one('#useEmailButton');

        useEmailButton.on('click', function(event) {
                var email = A.one('#<portlet:namespace/>email');
                
                email.val(emailAddress);
        });
        </aui:script>
    </c:if>

As with the buttons themselves, this script is only useful if the user is signed
in, so it's surrounded by `<c:if test="<%= themeDisplay.isSignedIn() %>">` tag.
Next, you retrieve the user's full name and email address. The `user` object is
made available by the `<theme:defineObjects />` tag, just like the
`themeDisplay` object.

Next comes the script itself. Note that in your `<aui:script use="node, event">`
tag, you're specifying additional AUI modules to use. This is a shortcut for
using the `AUI.use(...)` function like this:

    AUI().use('aui-node','aui-event', function(A) {
        // Write your AUI script here. The global AUI object, A, is available.
    });

Using `<aui:script use="node, event">` makes the global AUI object, `A`,
available just as if you'd written the code above. Since AUI extends YUI, please
refer to YUI's documentation to learn more about the global object:
[http://yuilibrary.com/yui/docs/yui/](http://yuilibrary.com/yui/docs/yui/).
(Just replace `YUI` with `AUI` and `Y` with `A` when you're reading these docs
with AlloyUI in mind.)

Inside the script tag, you capture the string value of the Java `fullName`
variable in a JavaScript `fullName` variable. Then you capture the *Use My Full
Name* node via the following AUI function call: `A.one('#useNameButton')`. The
`A.one(...)` function returns the first node that matches the selector argument.
YUI and AUI nodes are wrapper classes for manipulating DOM nodes. For more
information on the `A.one(...)` function and a comparison between jQuery, YUI,
and AUI, please refer to
[http://alloyui.com/rosetta-stone](http://alloyui.com/rosetta-stone). For more
information on YUI's node API (which AUI extends), please refer to the following
documentation:

- [http://yuilibrary.com/yui/docs/node](http://yuilibrary.com/yui/docs/node)
- [http://yuilibrary.com/yui/docs/api/classes/Node.html](http://yuilibrary.com/yui/docs/api/classes/Node.html)

Once you've obtained the *Use My Full Name* button node, you define a listener
on it:

    useNameButton.on('click', function(event) {
            var name = A.one('#<portlet:namespace/>name');
            
            name.val(fullName);
    });

The `on` function is available to every YUI or AUI node object. This function is
used to subscribe a node to a callback function to execute in response to a DOM
event or custom event. You're passing two arguments to the `on` function:

- `'click'` is the name of the event upon which the callback should be executed
- `function(event) {...}` is the callback function itself (which you define).

In the callback function, you use AUI to retrieve the input field node with the
unique ID `_guestbook_WAR_guestbookportlet_name`. Remember that since this field
was defined by an `<aui:input>` tag, it has the
`_guestbook_WAR_guestbookportlet_` prefix which you retrieve with
`<portlet:namespace />`. Once you have the name input field, it's easy to
populate it with `name.val(fullName)`. For more information on YUI and AUI
events and event listeners, please refer to
[http://yuilibrary.com/yui/docs/event](http://yuilibrary.com/yui/docs/event).

Creating an event listener for the *Use My Email Address* button that populates
the email input field is virtually identical to the *Use My Full
Name* button you already created.

Next, you'll develop a more complex example that uses AUI to add and remove DOM
nodes.

## Creating Populate Buttons for the Message Field [](id=creating-autopopulate-buttons-for-the-message-field)

So far, Guestbook portlet users can click on *Add Entry* to enter and save a
guestbook entry. A guestbook entry consists of a name, email address, and
message. In the last section, you implemented *Use My Full Name* and *Use My
Email Address* buttons that populate the name and email fields of the Add
Entry form. Now it's time to implement a *Generate Sample Messages* button. Here
are your requirements for the *Generate Sample Messages* button:

- Clicking on the *Generate Sample Messages* button should randomly display
  three sample messages from a pool of ten possible messages.
- A *Use Message* button should appear next to each of the sample messages that
  are displayed.
- Clicking on the *Use Message* button should populate the message field with
  the corresponding sample message.
- Repeatedly clicking the *Generate Sample Messages* button should replace
  the three currently displayed sample messages with three new random selections
  from the pool of ten.
- Whenever sample messages are displayed (i.e., whenever the *Generate Sample
  Messages* button has been clicked at least once), a *Hide Sample Messages*
  button should also be displayed.
- Clicking on the *Hide Sample Messages* button should remove the displayed
  sample messages so that the Add Entry form appears the way it did before the
  *Generate Sample Messages* button was first clicked.

AlloyUI makes it easy to implement a *Generate Sample Messages* button that
fulfills these requirements. In fact, you already used the required AUI modules
in the previous section: node and event. In the last section, you learned how to
use `A.one(...)` to retrieve specific nodes. You also learned how to use
`[node].on(...)` to create event listeners. In this section, you'll learn how to
use AUI to add and remove DOM nodes. You'll also make further use of the YUI/AUI
node API and you'll use the YUI/AUI node list API:

- [http://yuilibrary.com/yui/docs/api/classes/Node.html](http://yuilibrary.com/yui/docs/api/classes/Node.html)
- [http://yuilibrary.com/yui/docs/api/classes/Node.html](http://yuilibrary.com/yui/docs/api/classes/NodeList.html)

Your first task is to create the *Generate Sample Messages* button. Add the
following lines inside the Populate panel that you added, just above the
`</liferay-ui:panel>` tag:

    <aui:button-row>
            <aui:button 
                id="generateMessagesButton" 
                value="Generate Sample Messages">
            </aui:button>
    </aui:button-row>

    <div id="messages">
            <aui:layout>
                    <aui:column>
                            <div id="message1-div"></div>
                    </aui:column>
                    
                    <aui:column>
                            <div id="message2-div"></div>
                    </aui:column>
                    
                    <aui:column>
                            <div id="message3-div"></div>
                    </aui:column>
            </aui:layout>
    </div>

Here, you create the *Generate Sample Messages* button using the
`<aui:button-row>` and `<aui:button>` tags for styling. You also create an empty
container `<div>` for the sample messages. Inside of this `<div>`, you use the
`<aui:layout>` and `<aui:column>` tags to create three columns, each of which
contains a `<div>` for one of the sample messages: `<aui:layout>`
creates a row and `<aui:column>` creates a column. It's possible to specify a
percentage value for the `columnWidth` attribute for each `<aui:column>`, but
it's not necessary. The default works fine for your case.

Note that the `<div>` for sample messages is empty by default. You could have
added the `<div id="messages">` dynamically when the *Generate Sample Messages*
button was clicked, but it's easier to create the `<div>`s for the sample
messages inside the `<aui:layout>` and `<aui:column>` tags from the outset. The
whole messages `<div>` is invisible on the Add Entry form before the *Generate
Sample Messages* is clicked.

Now it's time to implement your button functionality. Add the following AUI
script to the bottom of `edit_entry.jsp`:

    <aui:script use="node, event">
    var generateMessagesButton = A.one('#generateMessagesButton');

    var message1Div = A.one('#message1-div');
    var message2Div = A.one('#message2-div');
    var message3Div = A.one('#message3-div');

    generateMessagesButton.on('click', function(event) {
            var entryMessages = [
                    'Amazing!',
                    'Be careful!',
                    'Best wishes!',
                    'Bravo!',
                    'Congratulations!',
                    'Great job!',
                    'Have fun!',
                    "How's it going?",
                    'You did it!',
                    "Wow!"
            ];

            if (message1Div.hasChildNodes()) {
                    message1Div.get('children').remove(true);
            }
            
            var rand1 = Math.floor(Math.random() * entryMessages.length);
            message1Div.append('<p class="message" id="message1">' + entryMessages[rand1] + '</p><p id="use-message1"><input class="btn" onclick="useMessage1();" type="button" value="Use Message" /></p>');
            entryMessages.splice(rand1, 1);
            
            if (message2Div.hasChildNodes()) {
                    message2Div.get('children').remove(true);
            }
            
            var rand2 = Math.floor(Math.random() * entryMessages.length);
            message2Div.append('<p class="message" id="message2">' + entryMessages[rand2] + '</p><p id="use-message2"><input class="btn" onclick="useMessage2();" type="button" value="Use Message" /></p>');
            entryMessages.splice(rand2, 1);
            
            if (message3Div.hasChildNodes()) {
                    message3Div.get('children').remove(true);
            }
            
            var rand3 = Math.floor(Math.random() * entryMessages.length);
            message3Div.append('<p class="message" id="message3">' + entryMessages[rand3] + '</p><p id="use-message3"><input class="btn" onclick="useMessage3();" type="button" value="Use Message" /></p>');

            var parentNode = generateMessagesButton.get('parentNode');

            if (parentNode.get('children').size() < 2) {
                    parentNode.append('<button class="btn" id="hideMessagesButton" type="button">Hide Sample Messages</button>');
            }
            
            var hideMessagesButton = A.one('#hideMessagesButton');
            
            hideMessagesButton.on('click', function(event) {
                    A.one('#message1-div').get('children').remove(true);
                    A.one('#message2-div').get('children').remove(true);
                    A.one('#message3-div').get('children').remove(true);
                    
                    if (parentNode.contains(hideMessagesButton)) {
                            parentNode.removeChild(hideMessagesButton);
                    }
            });
    });

    var message = A.one('#<portlet:namespace/>message');

    useMessage1 = function() {
            message.val(A.one('#message1-div').one('#message1').html());
    };

    useMessage2 = function() {
            message.val(A.one('#message2-div').one('#message2').html());
    };

    useMessage3 = function() {
            message.val(A.one('#message3-div').one('#message3').html());
    };
    </aui:script>

Your first order of business is to capture the nodes corresponding to the
*Generate Sample Messages* button and to the `<div>` elements for the sample
messages. Then you define the event handler for the *Generate Sample Messages*
button.

You begin by defining `entryMessages`, the array of the ten possible sample
messages. Then you check if the `<div>` for the first sample message has any
child nodes. If it does, this means that the *Generate Sample Messages* button
has already been clicked and the `<div>` for the first sample message should be
removed so that it can be replaced:

    if (message1Div.hasChildNodes()) {
            message1Div.get('children').remove(true);
    }

Once the child nodes of the `<div>` for the first sample message has been
removed (or the *Generate Sample Messages* button is being clicked for the first
time), you need add a new sample message to the `<div>`. To do so, you pick a
random selection from the `entryMessages` array, add the message to the `<div>`,
and remove the selected message from `entryMessages` so that it's not selected
for the second or third sample message:

    var rand1 = Math.floor(Math.random() * entryMessages.length);
    message1Div.append('<p class="message" id="message1">' + entryMessages[rand1] + '</p><p id="use-message1"><input class="btn" onclick="useMessage1();" type="button" value="Use Message" /></p>');
    entryMessages.splice(rand1, 1);

Notice the `onclick="useMessage1();"` attribute. `useMessage1`, `useMessage2`,
and `useMessage3` are functions that you define below the *Generate Sample
Messages* button event handler:

    var message = A.one('#<portlet:namespace/>message');

    useMessage1 = function() {
            message.val(A.one('#message1-div').one('#message1').html());
    };

    useMessage2 = function() {
            message.val(A.one('#message2-div').one('#message2').html());
    };

    useMessage3 = function() {
            message.val(A.one('#message3-div').one('#message3').html());
    };

Each of these functions populates the Add Entry form's message field with one of
the displayed sample messages. The handling of the `<div>`s for the second and
third sample messages works the same way as for the first sample message. Each
`<div>` is populated with a random sample message, just like the first sample
message `<div>` was.

Now you should implement the *Hide Sample Messages* button. You need to
add this button dynamically because it shouldn't exist on the page by default.
If the *Generate Sample Messages* button has not yet been clicked, then it will
have no siblings, i.e., its parent node will only have one child node. You use
this fact to add the *Hide Sample Messages* button only if it hasn't already
been added to the page:

    var parentNode = generateMessagesButton.get('parentNode');

    if (parentNode.get('children').size() < 2) {
            parentNode.append('<button class="btn" id="hideMessagesButton" type="button">Hide Sample Messages</button>');
    }

This adds the Hide Sample Messages button inside the same button row as the
*Generate Sample Messages* button. Specifying `class="btn"` styles the button
the same way as the Generate Sample Messages button. Once the Hide Sample
Messages button has been added (or you've confirmed that it has already been
added), you need to define an event handler for it:

    var hideMessagesButton = A.one('#hideMessagesButton');

    hideMessagesButton.on('click', function(event) {
            A.one('#message1-div').get('children').remove(true);
            A.one('#message2-div').get('children').remove(true);
            A.one('#message3-div').get('children').remove(true);
            
            if (parentNode.contains(hideMessagesButton)) {
                    parentNode.removeChild(hideMessagesButton);
            }
    });

Here, you use AUI to grab the *Hide Sample Message* button and define an event
hander. The event handler should remove all the sample messages. You use
`[node].get('children')` to get node lists representing all the sample message
`<div>`s' child nodes, and you remove them all. The event handler for the Hide
Sample Messages button should also remove the Hide Sample Messages button
itself. It wouldn't make sense for this button to appear when there weren't any
sample messages to hide! To remove the Hide Sample Messages button, you use
`parentNode.removeChild(hideMessagesButton)`.

Check out the new functionality of the Guestbook portlet's Add Entry form. Click
on the *Generate Sample Messages* button. Click on it multiple times. Click on
some of the *Use Message* buttons. Test the Hide Sample Messages button.

![The Add Entry form should look like this after you've clicked on the *Generate Sample Messages* button and have clicked on *Use My Full Name*, *Use My Email Address*, and *Use Message*.](../../images/guestbook-populate-all-buttons.png)

Great job! You've added a handy Populate panel with useful buttons for
populating the Add Entry form's fields. You've used AUI's node and event modules
and node and node list APIs to dynamically manipulate the DOM when the various
buttons are clicked.

For reference, this is how your `edit_entry.jsp` should appear after you've
completed this section:

    <%@include file = "/html/init.jsp" %>

    <portlet:renderURL var="viewURL">
            <portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
    </portlet:renderURL>

    <portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

    <%
    long entryId = ParamUtil.getLong(renderRequest, "entryId");

    Entry entry = null;

    if (entryId > 0) {
            entry = EntryLocalServiceUtil.getEntry(entryId);
    }
    %>

    <aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">
            <aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />
            <aui:fieldset>
                <aui:input name="name" >
                    <aui:validator name="required" errorMessage="Please enter your name." />
                </aui:input>

                    <aui:input name="email" >
                            <aui:validator name="email" />
                            <aui:validator name="required" />
                    </aui:input>

                    <aui:input id="message" type="textarea" name="message">
                            <aui:validator name="required" errorMessage="Please enter a message." />
                    </aui:input>
                    <div id="counterContainer"><p>Message: <span id="counter"></span> character(s) remaining</p></div>

                <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>

                <aui:input name="entryId" type="hidden" />
            </aui:fieldset>

            <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="populatePanel" persistState="<%= true %>" title="populate">
                    <c:if test="<%= themeDisplay.isSignedIn() %>">
                            <aui:button-row>
                                    <aui:button id="useNameButton" value="Use My Full Name"></aui:button>
                            </aui:button-row>
                    </c:if>
                    
                    <c:if test="<%= themeDisplay.isSignedIn() %>">
                            <aui:button-row>
                                    <aui:button id="useEmailButton" value="Use My Email Address"></aui:button>
                            </aui:button-row>
                    </c:if>
            
                    <aui:button-row>
                            <aui:button id="generateMessagesButton" value="Generate Sample Messages"></aui:button>
                    </aui:button-row>

                    <div id="messages">
                            <aui:layout>
                                    <aui:column>
                                            <div id="message1-div"></div>
                                    </aui:column>
                                    
                                    <aui:column>
                                            <div id="message2-div"></div>
                                    </aui:column>
                                    
                                    <aui:column>
                                            <div id="message3-div"></div>
                                    </aui:column>
                            </aui:layout>
                    </div>
            </liferay-ui:panel>

            <liferay-ui:asset-categories-error />
            <liferay-ui:asset-tags-error />
            <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="entryCategorizationPanel" persistState="<%= true %>" title="categorization">
                    <aui:fieldset>
                            <aui:input name="categories" type="assetCategories" />
                            
                            <aui:input name="tags" type="assetTags" />
                    </aui:fieldset>
            </liferay-ui:panel>
            
            <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="entryAssetLinksPanel" persistState="<%= true %>" title="related-assets">
                    <aui:fieldset>
                            <liferay-ui:input-asset-links className="<%= Entry.class.getName() %>" classPK="<%= entryId %>"
                            />
                    </aui:fieldset>
            </liferay-ui:panel>
            
            <aui:button-row>
                    <aui:button type="submit" id="save"></aui:button>
                    
                    <aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>
            </aui:button-row>
    </aui:form>

    <c:if test="<%= themeDisplay.isSignedIn() %>">
    <%
    String fullName = user.getFullName();

    String emailAddress = user.getEmailAddress();
    %>

    <aui:script use="node, event">
    var fullName = '<%= fullName %>';

    var useNameButton = A.one('#useNameButton');

    useNameButton.on('click', function(event) {
            var name = A.one('#<portlet:namespace/>name');
            
            name.val(fullName);
    });

    var emailAddress = '<%= emailAddress %>';

    var useEmailButton = A.one('#useEmailButton');

    useEmailButton.on('click', function(event) {
            var email = A.one('#<portlet:namespace/>email');
            
            email.val(emailAddress);
    });
    </aui:script>
    </c:if>

    <aui:script use="aui-char-counter">
    AUI().use(
      function(A) {
        new A.CharCounter(
          {
            counter: '#counter',
            input: '#<portlet:namespace />message',
            maxLength: 140
          }
        );
      }
    );
    </aui:script>

    <aui:script use="node, event">
    var generateMessagesButton = A.one('#generateMessagesButton');

    var message1Div = A.one('#message1-div');
    var message2Div = A.one('#message2-div');
    var message3Div = A.one('#message3-div');

    generateMessagesButton.on('click', function(event) {
            var entryMessages = [
                    'Amazing!',
                    'Be careful!',
                    'Best wishes!',
                    'Bravo!',
                    'Congratulations!',
                    'Great job!',
                    'Have fun!',
                    "How's it going?",
                    'You did it!',
                    "Wow!"
            ];

            if (message1Div.hasChildNodes()) {
                    message1Div.get('children').remove(true);
            }
            
            var rand1 = Math.floor(Math.random() * entryMessages.length);
            message1Div.append('<p class="message" id="message1">' + entryMessages[rand1] + '</p><p id="use-message1"><input class="btn" onclick="useMessage1();" type="button" value="Use Message" /></p>');
            entryMessages.splice(rand1, 1);
            
            if (message2Div.hasChildNodes()) {
                    message2Div.get('children').remove(true);
            }
            
            var rand2 = Math.floor(Math.random() * entryMessages.length);
            message2Div.append('<p class="message" id="message2">' + entryMessages[rand2] + '</p><p id="use-message2"><input class="btn" onclick="useMessage2();" type="button" value="Use Message" /></p>');
            entryMessages.splice(rand2, 1);
            
            if (message3Div.hasChildNodes()) {
                    message3Div.get('children').remove(true);
            }
            
            var rand3 = Math.floor(Math.random() * entryMessages.length);
            message3Div.append('<p class="message" id="message3">' + entryMessages[rand3] + '</p><p id="use-message3"><input class="btn" onclick="useMessage3();" type="button" value="Use Message" /></p>');

            var parentNode = generateMessagesButton.get('parentNode');

            if (parentNode.get('children').size() < 2) {
                    parentNode.append('<button class="btn" id="hideMessagesButton" type="button">Hide Sample Messages</button>');
            }
            
            var hideMessagesButton = A.one('#hideMessagesButton');
            
            hideMessagesButton.on('click', function(event) {
                    A.one('#message1-div').get('children').remove(true);
                    A.one('#message2-div').get('children').remove(true);
                    A.one('#message3-div').get('children').remove(true);
                    
                    if (parentNode.contains(hideMessagesButton)) {
                            parentNode.removeChild(hideMessagesButton);
                    }
            });
    });

    var message = A.one('#<portlet:namespace/>message');

    useMessage1 = function() {
            message.val(A.one('#message1-div').one('#message1').html());
    };

    useMessage2 = function() {
            message.val(A.one('#message2-div').one('#message2').html());
    };

    useMessage3 = function() {
            message.val(A.one('#message3-div').one('#message3').html());
    };
    </aui:script>

## Next Steps [](id=next-steps)

[Making URLs Friendly](/develop/learning-paths/-/knowledge_base/making-urls-friendly)
