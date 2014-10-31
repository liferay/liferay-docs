# Using AlloyUI Form Validation [](id=using-alloyui-form-validation)

In a previous learning path, you created a form for users to submit when adding
an entry to a guestbook. You implemented back-end validation (checking the entry
name, email address, and message for `null` values) in the service layer, but
did not provide front-end validation in the form itself. From a security
perspective, it is necessary to implement both forms of validation to prevent
nefarious users from turning off JavaScript in their browsers and submitting bad
data. For this reason, you need not worry: you haven't done extra work. Now it's
time to implement validation on the front end. 

Front-end validation makes sure that when users submit your form, they have
filled out all the required fields. Your front-end form validation should
indicate which fields are required and should highlight the required fields if
the user tries to submit the form with one or more of the required fields left
blank. For email addresses, your form validation should check that the user
entered a valid address. Your form validation should also allow you to restrict
the number of characters that can be entered into a field. The number of
remaining characters for the field should be displayed to the user. With
AlloyUI, it's easy to implement all of these form validation features. Once
you've implemented form validation, users' attempts to submit invalid forms are
caught by the browser before a request is sent to your Liferay server.

You'll implement form validation with AlloyUI in two steps. First, you'll use
the AlloyUI validator tag to specify required fields and fields that require a
special format (e.g., the email address field). Then you'll use the AlloyUI
Character Counter module to limit the number of characters that comprise a
guestbook entry message.

## Using the AUI Validator Tag [](id=using-the-aui-validator-tag)

Validating AUI input fields is very easy. You just have to add an
`<aui:validator>` tag and specify the appropriate attributes.

Open your project's `docroot/html/guestbook/edit_entry.jsp` file.

To keep things simple, replace the contents between the `<aui:fieldset>` and
`<aui:fieldset/>` tags with the following code:

    <aui:input name="name" >
         <aui:validator name="required" errorMessage="Please enter your name." />
    </aui:input>

    <aui:input name="email" >
         <aui:validator name="email" />
         <aui:validator name="required "/>
    </aui:input>

    <aui:input id="message" type="textarea" name="message">
         <aui:validator name="required" errorMessage="Please enter a message." />
    </aui:input>

    <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>

    <aui:input name="entryId" type="hidden" />

Save this update and redeploy your app.

You've added `<aui:validator>` tags inside the `<aui:input>` tags corresponding
to the name, email, and message fields. The `<aui:validator>` tag is a fast and
easy way to validate your fields. First, note that you must add an
`<aui:validator/>` tag inside the `<aui:input>` tag for each field you wish
to validate. Next, note that you have to specify a value for the `name`
attribute. The value of the `name` attribute determines the type of validation
to apply to the field. You added an `aui:validator name="required" />` tag to
all the non-hidden form fields. This prevents blank empty fields from being
submitted. For the email field, you've added an `aui:validator name="required"
/>` tag. This ensures that only valid email addresses can be submitted. 

In the code above, you also changed the message field from a text input field to
a textarea input field. This provides more space for users to enter guestbook
messages. It also allows them to resize the field. Note the `<aui:validator>`'s
`errorMessage` attributes for the name and message fields. These attributes
specify messages that replace the default error message. You're replacing the
default error message (*This field is required*) field with custom messages:
*Please enter a name.* and *Please enter a message.*

Now click on *Add Entry* in the Guestbook portlet to view the form that you
updated. Notice that all the input fields are now marked as *Required*, thanks
to your `<aui:validator>` tags. 

Attempt to save a guestbook message with all of the fields left empty. Check
that the default error messages, along with your custom error message, are
displayed. Your Add Entry form should look like this: 

![Figure 1: Leave the Add Entry input fields empty and attempt to submit the form. It should look like this.](../../images/guestbook-form-validation.png)

Good! You've implemented form validation using AUI's validator tag. Next, you'll
learn how to limit the length of guestbook entry messages.

## Using the AUI Character Counter Module [](id=using-the-aui-character-counter-module)

AUI modules are small, powerful building blocks of UI functionality. Each module
provides a unique set of functionality that's designed to solve a particular
problem. In your case, you want to limit the number of characters that users can
enter into the message field of the Add Entry form. If you used the AUI
validator tag to limit the number of characters, users would have to keep track
of the character limit. A much better solution is to use the AUI Character Counter
module. You can use this module not only to specify a limit on the number of
characters that can be entered, but also to display the number of remaining
characters.

Open the `edit_entry.jsp` file and find the following code:

    <aui:input id="message" type="textarea" name="message">
        <aui:validator name="required" errorMessage="Please enter a message." />
    </aui:input>

Add the following line just below it:

    <div id="counterContainer"><p><span id="counter"></span> character(s) remaining</p></div>

This `<div>` will display the number of remaining characters. To make it
work, we need to create an AUI script that uses the character counter to
populate the `<span id="counter"></span>` element.

Add the following code to the bottom the page:

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

Here, you're specifying that the `<span id="counter"></span>` element should
be used for the counter, that the characters entered inside the text area
created by the `<aui:input id="message" type="textarea" name="message">`
element should be counted, and that the maximum number of characters that
can be entered is `140`. Please refer to the
[AlloyUI Char Counter tutorial](http://alloyui.com/tutorials/char-counter)
tutorial for more information about the character counter module.

+$$$

**Note:** Liferay IDE provides a *script* snippet that's available from the
Liferay AUI Taglib category of the Snippets tab. You can use this snippet
whenever you need to add an `<aui:script>` tag to a JSP page.

$$$

The following code loads the `aui-char-counter` module:

    AUI().use(
      'aui-char-counter',
      function(A) {

      }
    );

Adding the code below inside the `function(A)` brackets creates an instance of
the character counter:

    new A.CharCounter(
      {
       
      }
    );

To configure this instance of the character counter module that you loaded,
you specify the following attributes in the character counter constructor:

    counter: '#counter',
    input: '#<portlet:namespace />message',
    maxLength: 140

This creates a fully functioning character counter. Specifying `counter:
'#counter'` configures the counter to display in the HTML element with the
`counter` ID. Specifying `input: '#<portlet:namespace />message'` selects
the input field (actually a text area in this case) whose characters are to
be counted. The `input` attribute is set to the message input element that
you created with the `message` ID. Since you're using an `<aui:input>` tag to
create the text area, the CSS ID of the text area is not actually `message`,
it's `_guestbook_WAR_guestbookportlet_message`. You don't have to remember the
prefix: just use the `<portlet:namespace />` tag to retrieve it.

Remember that when designing a portlet, you need to make sure that it works
regardless of other portlets that may have been added to the page. There's no
guarantee that another portlet doesn't use CSS class names or IDs that you
assumed would be unique. Using the prefix supplied by the `<portlet:namespace
/>` tag ensures that there won't be naming conflicts with other elements on the
page. Finally, by specifying `maxLength: 140`, you limit the number of
characters that can be entered into the input field to `140`.

Save your work, re-load the page, and click on the Guestbook portlet's *Add
Entry* button to test out the guestbook entry form. Complete the form and check
that your fancy new character counter displays the number of remaining
characters for your message. Your form should look like the one below:
 
![Figure 2: With your new character counter, your form should look like this one.](../../images/guestbook-char-counter.png)

Excellent! Your front-end form validation is now complete! Next, you'll learn
how to manipulate the DOM with AlloyUI.
