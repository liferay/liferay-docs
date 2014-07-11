# Using the AlloyUI Form Validator in Your Portlet 

Typically, if you want to validate a form before submitting it, you have to 
write a complicated function that makes sure you have all your i's dotted 
and t's crossed. AlloyUI removes that step from your workflow. Keep reading to 
find out how! 

## Adding a Form Validator to a Portlet 

- **Step 1:** Create Your Form in Your View JSP.
- **Step 2:** Add a Form Validator to Your View JSP.
- **Step 3:** Configure the Rules and FieldStrings Attributes.

Go through these steps and you'll be a form validating master in no time! 

### Step 1: Create Your Form in Your View JSP 

First you need to create your form in the `view.jsp` of your portlet. The 
example below creates a form with `firstname`, `email`, and `age` input fields. 
It's important to note that each input field must be nested within the `<div>` 
elements and given the classes seen below. 

        ```
        <form id="myForm">
          <div class="control-group">
            <label class="control-label" for="firstname">First Name:</label>
            <div class="controls">
              <input name="firstname" id="firstname" type="text">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="email">E-mail:</label>
            <div class="controls">
              <input name="email" id="email" type="text">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="age">Age:</label>
            <div class="controls">
              <input name="age" id="age" type="text">
            </div>
          </div>
  
          <input class="btn btn-info" type="submit" value="Submit">
          <input class="btn btn-primary" type="reset" value="Reset">
  
         </form>
         
         ```
The `name` attributes of the `<input>` tags above are key to making the form 
validator work. The value of the `name` attribute tells the validator what rule 
to use for validation. You'll learn more about that later. 

### Step 2: Add a Form Validator to Your View JSP 

1.  Still inside your `view.jsp`, add the following taglib just below the 
`portlet` taglib at the top of the file:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

2.  To create a [Form Validator](http://alloyui.com/api/classes/A.FormValidator.html)
for the form you created in step 1, first add `<aui:script>...</aui:script>` 
tags at the bottom of your JSP. Within those tags, add code that instantiates 
the Form Validator, passing a reference to the `<form id="myForm">` as the value 
for the Validator's `boundingBox` element. 

    The example code below creates a Form Validator for a `<form>` named 
    *myForm*:

        ```
        <aui:script>
        AUI().use(
          'aui-form-validator',
          function(Y) {
           new Y.FormValidator(
             {
               boundingBox: '#myForm',
               rules: {
                 
                }
             }
           )
         }
        );
        </aui:script>

        ```
    This code creates the Form Validator by getting the `AUI` object to use
    the `aui-form-validator` module to create a Form Validator component. It
    specifies values for the new Form Validator's `boundingBox`.
         
3.  Save the `view.jsp` file.

If you deploy the portlet at this point, your fields are not being validated. 
This is because no rules have been created for your form. You'll add these rules 
in the next step.

### Step 3: Configure the Rules and FieldStrings Attributes 

This step is where validation really comes into play. By setting up the rules 
for your form to validate, you acheive a great deal of control over what users 
can enter into your form. 

Here is a list of the available rules: 

**acceptFiles:** List of file types accepted. (Default:empty)

**alpha:** Evaluates whether or not a field contains only alpha characters.
(Default:none) 

**alphanum:** A boolean value that determines whether a field is supposed to
contain only alphanumeric characters and evaluates it accordingly.
(Default:false) 

**date:** A boolean value that determines whether a field is a date and
evaluates it accordingly. (Default:false)

**digits:** A boolean value that determines whether a field is supposed to 
contain only digits and evaluates it accordingly. (Default:false)

**email:** A boolean value that determines whether a field is an email address 
and evaluates it accordingly. (Default:false)

**equalTo:** Evaluates whether a field is equal to the field written.
(Default:empty)

**iri:** A boolean value that determines whether a field is an IRI and evaluates
it accordingly (Default:false) 

**max:** Evaluates whether the integer value is greater than the value written. 
(Default:none)

**maxLength:** Evaluates whether a field is over the maximum length of
characters specified. (Default:empty)

**min:** Evaluates whether the integer value is less than the value written. 
(Default:none)

**minLength:** Evaluates whether a field is under the minimum length of
characters specified. (Default:empty)

**number:** A Boolean value that determines whether a field is supposed to
contain only numeric values and evaluates it accordingly. (Default:false)

**range:** Evaluates whether the integer value lies within the range given.
(Default:none)

**rangeLength:** Evaluates whether a field lies within the range of characters
written. (Default:empty)

**required:** Evaluates whether the field is required before submission.
(Default:false)

**url:** A boolean value that determines whether a field is a URL and evaluates 
it accordingly. (Default:false)

To use a rule, add it to the `rules` attribute and give it a proper value. Here 
is an example of a `firstname` field with a few rules applied to it:

		```
        rules: {
          firstname: { /*field name taken from input tag's name value*/
            required: true, /*this field is required*/
            rangeLength: [2,20], /*this field can contain 2 to 20 characters*/
            alpha: true /*this field can only contain alpha characters*/
          }
        }
           
        ```
The rules' name, in this case `firstname`, is taken from the `name` attribute of
the `<input>` tags in your form. It's important to note that the value received 
by `firstname` is case sensitive. Upon breaking one of your validation rules, a 
message is displayed next to the validated field. Only one message is displayed 
by default. However, in some cases you may want to display more than one 
message. To do this, you can add the `showAllMessages` attribute to the 
Form Validator. In this code, `showAllMessages` is set to `true` so that more 
than one validation message can be displayed:

		```
        new Y.FormValidator(
             {
               boundingBox: '#myForm',
               showAllMessages: true,
               rules: {
        ```
Once you have mastered the `rules` attribute, the `fieldStrings` attribute is an 
easy transition. It's essentially a modification of the `rules` attribute. For 
example, if you want to replace the default messages for the `required` and 
`rangeLength` rules, you could use the following code:

        fieldStrings: {
          firstname: {
            required: "The Force is strong with you, but we still need a name.",
            rangeLength: "2 to 20 characters Padawan."    
          }
        }

Now it's time to enjoy the fruits of your labor! Redeploy your portlet and break 
the `required` and `rangeLength` rules to see your custom messages. The rule to 
remember (no pun intended) when it comes to the `fieldStrings` attribute is that 
you can write a custom message for any rule that has been setup in the `rules` 
attribute. With the addition of the `fieldStrings` attribute, here is what the 
`firstname` field for your portlet should look like once you break the rules:

![Figure 1: Here is a look at the `aui-form-validator` in a portlet with the `fieldStrings` attribute configured and the `showAllMessages` attribute set to true.](../../images/alloyui-form-validator-in-a-portlet.png)

As you can see, using the AlloyUI Form Validator is a no-brainer when it comes 
time for form validation!

## Next Steps 

 [Using AlloyUI Carousel in a Portlet](http://dev.liferay.com/tutorials/-/knowledge_base/using-alloyui-carousel-in-your-portlet-lp-6-2-develop-tutorial)
