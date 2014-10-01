# Using AlloyUI to Validate Forms in Your Application [](id=using-alloyui-to-validate-forms-in-your-application)

In the previous learning paths, you created a form for the user to fill out
to populate the guestbook. This serves as a good starting point for what you'll
do next. 

You probably noticed that you validated the entities on the back end, but did
not provide front end validation in the form itself. Before users submit your
form, you want to make sure they have filled it out exactly as you had intended. 
You need to let them know which fields are required, if there is a limit on the 
number of characters, or if they left any fields empty. AlloyUI makes form 
validation a easy process.

You'll learn how to do this in two steps: 1) using the AlloyUI validator tag
and 2) using the AlloyUI Character Counter module. The first step is to use the 
AlloyUI validator tag.

## Using the AUI:Validator Tag

Most of the work is already done at this point. You just need to modify the form
input fields a bit.

1.  Open `edit_entry.jsp`.

    To keep things simple, replace the contents between the `<aui:fieldset>` and
    `<aui:fieldset/>` tags with the following code:
        <aui:input name="name" >
             <aui:validator name="required"/>
        </aui:input>
        <aui:input name="email" >
             <aui:validator name="email"/>
             <aui:validator name="required"/>
        </aui:input>
        <aui:input id="message" cssClass="message" type="textarea" name="message">
             <aui:validator name="required" errorMessage="Leave a message please." />
        </aui:input>
        <aui:input name='guestbookId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "guestbookId") %>'/>
        <aui:input name="entryId" type="hidden" />

    The main difference in this code is the presence of the `<aui:validator>`
    tag inside the `<aui:input>` tags. This tag is a quick and easy solution to
    validating your fields. The first thing to note is that the `<aui:validator/>`
    tag is placed inside the `<aui:input>` tags for the field you wish to
    validate. The next step is to give the `name` attribute a value of the type of
    validation you want to use on the field. All the fields are marked with a
    `required` attribute. For the email field, you've added a `email` validation 
    in order to assure that a legitimate email is entered. 

    The message field has been modified into a textarea input field to give your
    users more space. The textarea has been given a `cssClass` attribute to 
    allow for styling later; it has been marked as a required field like the 
    others. The last thing to note is the `errormessage` attribute in the 
    message field. The `errormessage` attribute replaces the default error 
    message. In your code, you replaced the default "this field is required" 
    message field with a custom "Leave a message please" message. One last thing
    you can do is give some styling to the message field to prevent users from
    resizing the field.
    
    There are a few ways you can style your elements: 1)External style sheet; 
    2)Internal styles(written in between `<style>` tags); 3)Inline styles
    (written inside of the element's tag). The preferred method is to add 
    it to an external style sheet. When the portlet was created, a `main.css` 
    file was generated in the `docroot/css/` directory of your portlet. You will 
    add all of your styling to the `main.css` file.
    
2.  Add the styling for the message field to the `main.css`:

        .message
        {
            resize:none;
        }
        
    The `cssClass` attribute value specified for the message field has been 
    referenced for the styling. With the styling added, you can move on to the 
    next step.

2.  Save the changes you made and redeploy your app.

3.  Add a new entry to a guestbook and save it with all the fields left empty.
    
    All the default error messages, along with your custom error message are
    displayed. You'll also notice that all the input fields now have an added
    "Required" label. Your add entry page should reflect the figure below: 

![Figure 1: With the input fields left empty and the form submitted your form should look like this one.](../../images/guestbook-form-validation.png)

Now that you've added validation to your form using AUI's validator tag,
you can do something a little more advanced: keep everyone's message to a
reasonable length.

## Using the AUI Character Counter Module

AUI modules are small, powerful building blocks of UI functionality. There are
many different modules to choose from, depending on the particular problem you
need to solve. In this case, you want to limit the amount of characters users
can type for their messages. You can do this with the AUI Validator tag, but then
users must know how many characters they have typed. As you can guess, this is
not a good solution. A much better option is the `aui-char-counter` module:
not only does it allow you to limit the amount of characters to a number you
specify, it also is capable of notifying the user how may characters are left.
Go ahead and get started. 

1.  Open `edit_entry.jsp` and drag the snippet labeled *script* from the 
    *Liferay AUI Taglib* category. Add it just above the `<%` opening tag for 
    `long entryId = ...`. You'll be placing all code inside these tags.

    The first thing you need to do is load the `aui-char-counter` module.

2.  Inside the `<aui:script>` tag, add the code below:

        AUI().use(
          'aui-char-counter',
          function(A) {
  
          }
        );

    Now that you've loaded the character counter module, you need to create a 
    new instance of the character counter to use.
 
3.  Add the code below inside the `function(A)` brackets to create an instance 
    of the character counter:

        new A.CharCounter(
          {
           
          }
        );

    Now that you've created an instance of the module you loaded, you'll next 
    configure the character counter by setting values for the counter's
    attributes.
 
4.  Add the attributes below inside the character counter instance you just
    created:

        counter: '#counter',
        input: '#<portlet:namespace />message',
        maxLength: 70
    
    Now you have a fully functioning character counter. The values above set the
    counter to display in the element with the id `counter`. The input attribute 
    is set to the message input element you created with the `message` id. To 
    ensure that there are no naming conflicts with other elements or existing 
    portlets on the page, a <portlet:namespace/> tag is added before the 
    `message` id. Finally, the number of characters allowed in the input field 
    are limited to 70.
    
    Don't be so fast to redeploy just yet though. Although, you've configured
    the character counter module, you still have one last step to make your 
    effort worth it: create an element to display the counter. You set the 
    counter attribute of the character counter to a element with the id 
    `counter`, but you don't currently have any elements with that id, so you'll 
    need to create one.
 
5.  Create a `<span>` element just above the closing `</aui:fieldset>` tag: 

        <span id="counter"></span> character(s) remaining
   
    There you have it! Now you can redeploy and test out the guestbook entry
    form. You'll now see your fancy new character counter keeps track of how many
    characters you have left in your message. To make your form a little more 
    visually appealing, you'll nest the `<span>` tag inside a `<div>` and style 
    it, so that the counter text is not so far away from the message input field.
 
6.  Nest your `<span>` tag in between two `<div>` tags so that your code looks
    like this:

        <div id="counterContainer">
            <span id="counter"></span> character(s) remaining
        </div>
        
    Next, you can reference the id for styling.
    
7.  Add the styling below to the `main.css`:

        #counterContainer
        {
            margin-top:-30px;
        }

With updated visuals, your form should look like the one below:
 
![Figure 2: With your new character counter, your form should look like this one.](../../images/guestbook-char-counter.png)

You can take a look at the updated `edit_entry.jsp` [here](https://github.com/liferay/liferay-docs/blob/master/develop/learning-paths/code/learning-sdk/portlets/guestbook-portlet/docroot/html/guestbook/edit_entry.jsp).

Your front-end form validation is done! Save your changes and deploy your
application. 

## Next Steps

 [Traversing the Dom with AlloyUI](/develop/learning-paths/-/knowledge_base/traversing-the-dom-with-alloyui)
