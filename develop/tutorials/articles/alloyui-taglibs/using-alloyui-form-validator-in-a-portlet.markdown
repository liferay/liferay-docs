# Using the AlloyUI Form Validator in a Portlet

If you want to validate a form before submitting it, you typically have to 
write a complicated function that makes sure you have all your i's dotted 
and t's crossed. AlloyUI removes this step from your workflow. Keep reading to 
find out how! 

## Adding a Form Validator to a Portlet

There are just a few steps you need to follow to enable form validation in your 
portlet:

- **Step 1:** Create Your Form in Your View JSP.
- **Step 2:** Add a Form Validator to Your View JSP.
- **Step 3:** Configure the Rules and FieldStrings Attributes.

Follow these steps and you'll be the master of form validation in no time! 

### Step 1: Create Your Form in Your View JSP

First you need to create your form in the `view.jsp` of your portlet. The 
example here creates a form with the `firstname`, `email`, and `age` input 
fields. It's important to note that each input field must be nested within the 
`<div>` elements and given the classes seen here: 

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

The `name` attributes of the `<input>` tags are key to making the form validator 
work. The value of the `name` attribute tells the validator what rule to use for 
validation. You'll learn more about this in a moment. 

### Step 2: Add a Form Validator to Your View JSP

Now that you have a form you're ready to add a form [validator](http://alloyui.com/api/classes/A.FormValidator.html). 
The steps here implement the validator inside your `view.jsp`.

1.  Add the following taglib just below the `portlet` taglib at the top of the 
    file:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

2.  Add `<aui:script>...</aui:script>` tags to the bottom of your JSP. Within 
    those tags you'll instantiate the form validator. In the example here, the 
    `AUI` object uses the `aui-form-validator` to create the form validator 
    component. A reference to the form created in the previous section, 
    `"myForm"`, is passed as the value for the validator's `boundingBox` 
    element:

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
         
3.  Save the `view.jsp` file.

If you deploy the portlet at this point, your fields are not being validated. 
This is because no rules have been created for your form. You'll add these rules 
in the next step.

### Step 3: Configure the Rules and FieldStrings Attributes

This step is where validation really comes into play. The validation rules you 
implement here control what your users can enter into your form. Here's a list 
of the available rules: 

**acceptFiles:** List of accepted file types. (Default:empty)

**alpha:** A boolean value that determines if a field should contain only 
alphabetic characters. (Default:none) 

**alphanum:** A boolean value that determines if a field should contain only 
alphanumeric characters.
(Default:false) 

**date:** A boolean value that determines if a field should contain only a date. 
(Default:false)

**digits:** A boolean value that determines if a field should contain only 
digits. (Default:false)

**email:** A boolean value that determines if a field should contain only an 
email address. (Default:false)

**equalTo:** Determines if a field's contents are equal to the specified value.
(Default:empty)

**iri:** A boolean value that determines if a field should contain only an IRI. 
(Default:false) 

**max:** Determines if the integer value is greater than the specified value. 
(Default:none)

**maxLength:** Determines if the length of a field's contents are greater than 
the number of characters specified. (Default:empty)

**min:** Determines if the integer value is less than the specified value. 
(Default:none)

**minLength:** Determines if the length of a field's contents are less than the 
number of characters specified. (Default:empty)

**number:** A Boolean value that specifies a field contain only numeric values. 
(Default:false)

**range:** Determines if the integer value in the field lies within the 
specified range. (Default:none)

**rangeLength:** Determines if the length of a field's contents lies within the 
specified range. (Default:empty)

**required:** Determines if the field is required before submission. 
(Default:false)

**url:** A boolean value that determines if a field's contents are a URL. 
(Default:false)

To use a rule with a field, first add the field in the `rules` attribute of your 
JSP. The field's name is taken from the `name` attribute of the `<input>` tags 
in your form. Then add the rule and give it a proper value. The following 
example uses the `required`, `rangeLength`, and `alpha` rules with the 
`firstname` field:

        ```
        rules: {
          firstname: { /*field name taken from input tag's name value*/
            required: true, /*this field is required*/
            rangeLength: [2,20], /*this field can contain 2 to 20 characters*/
            alpha: true /*this field can only contain alpha characters*/
          }
        }
        ```

Upon breaking one of your validation rules, a message is displayed next to the 
field. Only one message is displayed by default. However, in some cases you may 
want to display more than one message. To do this, you can add the 
`showAllMessages` attribute to the form validator. Here, `showAllMessages` is 
set to `true` so that more than one validation message can be displayed:

		```
        new Y.FormValidator(
             {
               boundingBox: '#myForm',
               showAllMessages: true,
               rules: {
        ```

If you want to customize validation messages, use the `fieldStrings` attribute. 
The `fieldStrings` attribute is essentially a modification of the `rules` 
attribute. For example, if you want to replace the default messages for the 
`required` and `rangeLength` rules, you could use the following code:

        fieldStrings: {
          firstname: {
            required: "The Force is strong with you, but we still need a name.",
            rangeLength: "2 to 20 characters Padawan."    
          }
        }

Now it's time to enjoy the fruits of your labor! Redeploy your portlet and break 
the `required` and `rangeLength` rules to see your custom messages. The rule to 
remember, no pun intended, is that you can use `fieldStrings` to write a custom 
message for any rule in the `rules` attribute. Here is what the `firstname` 
field of this example portlet should look like once you break the rules:

![Figure 1: Here, the AUI form validator is used to display custom validation messages.](../../images/alloyui-form-validator-in-a-portlet.png)

As you can see, using the AlloyUI form validator is a no-brainer when it comes 
time for form validation!

## Related Topics

[Liferay UI Taglibs](/tutorials/-/knowledge_base/liferay-ui-taglibs)

[Themes and Layout Templates](/tutorials/-/knowledge_base/themes-and-layout-templates)

[Application Display Templates](/tutorials/-/knowledge_base/application-display-templates)
