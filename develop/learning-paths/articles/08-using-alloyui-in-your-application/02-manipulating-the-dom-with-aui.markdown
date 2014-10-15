# Manipulating the DOM with AlloyUI

In the last section, you used AlloyUI to implement form validation on several
fields. In this section, you'll learn how to use AlloyUI to manipulate HTML
elements in the DOM (Document Object Model). You'll add several buttons to the
Add Entry form, that, when clicked, add or remove elements from the DOM or
automatically populate some of the form fields. First, you'll create buttons
that autopopulate the name and email fields of the Add Entry form with the full
name and email address of the current user. Then you'll create a button that
generates sample guestbook entry messages. The user can select a message and
the message field will be autopopulated with the selected message. To achieve
these goals, you'll use the AlloyUI node module for DOM manipulation and the
AlloyUI event module to create event listeners.

## Creating Autopopulate Buttons for the Name and Email Fields

To keep the Add Entry form as clean as possible, you'll create a collapsible
panel within which to add the buttons for autopopulating the form fields. Add
the following lines to your project's `docroot/html/guestbook/edit_entry.jsp`
file:

    <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="autopopulatePanel" persistState="<%= true %>" title="autopopulate">
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
    </liferay-ui:panel>

The `<liferay-ui:panel>` tag creates a collapsible panel. The following
attributes are useful here:

- `defaultState="closed"`: sets the panel to be closed (collapsed) by default
- `extended="<%= false %>"`: sets the panel not to extend horizontally
- `id="autopopulatePanel"`: specifies a CSS ID for the panel
- `persistState="<%= true %>"`: sets the panel to "remember" the state
  (collapsed or expanded) chosen by the user
- `title="autopopulate"`: sets the title of the panel to *autopopulate*

It's a best practice to use language keys for titles so that they can be
localized. Open your project's `docroot/WEB-INF/src/content/Language.properties`
file and add the following line:

    autopopulate=Autopopulate

Note the `c:if test="<%= themeDisplay.isSignedIn() %>">` tags surrounding the
buttons in the panel that you added. The *Use My Full Name* and *Use My Email
Address* buttons should only appear for signed-in (non-guest) users. The portal
doesn't know the full names or email addresses of guest users so the buttons
could never work for guests! The `themeDisplay` object is made available by the
`<theme:defineObjects />` tag which you're importing from `init.jsp`. Using the
`<aui:button-row>` and `<aui:button>` tags lets you take advantage of AlloyUI
button styling.

Test your update by clicking on the Add Entry form of the Guestbook portlet.
Confirm that the panel appears, that its title is *Autopopulate*, and that the
*Use My Full Name* and *Use My Email Address* buttons appear when you're signed
in.

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
in. So you surround it with the `<c:if test="<%= themeDisplay.isSignedIn() %>">`
tag. Next, you retrieve the user's full name and email address. The `user`
object is made available by the `<theme:defineObjects />` tag, just like the
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

Inside of the script tag, you capture the string value of the Java `fullName`
variable in a JavaScript `fullName` variable. Then you capture the *Use My Full
Name* DOM node via the following AUI function call: `A.one('#useNameButton')`.
The `A.one(...)` function returns the first DOM node that matches the selector
argument. For more information on the `A.one(...)` function and a comparison
between jQuery, YUI, and AUI, please refer to
[http://alloyui.com/rosetta-stone](http://alloyui.com/rosetta-stone). For more
information on YUI's node API (which AUI extends), please refer to
[http://yuilibrary.com/yui/docs/node](http://yuilibrary.com/yui/docs/node) and
[http://yuilibrary.com/yui/docs/api/classes/Node.html](http://yuilibrary.com/yui/docs/api/classes/Node.html).

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

The process of creating an event listener to populate the email input field when
the *Use My Email Address* button is clicked is virtually identical to the one
for the *Use My Full Name* button.

Next, you'll develop a more complex example that involves both removing and
adding nodes.

## Creating Autopopulate Buttons for the Message Field

Add the following lines inside of the autopopulate panel that you added, just
above the `</liferay-ui:panel>` tag:

    <div id="generateButtonWrapper">
            <aui:button-row>
                    <aui:button id="generateMessagesButton" value="Generate Sample Messages"></aui:button>
            </aui:button-row>
    </div>

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

Add the following lines to the bottom of `edit_entry.jsp`:

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
