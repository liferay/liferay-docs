# Using the AlloyUI Form Validator in a Portlet [](id=using-the-alloyui-form-validator-in-a-portlet)

<!-- Test code is at develop/tutorials/code/alloy/form-validator/end -->

Would you like to provide instant feedback to users when they fill out a form
incorrectly? Do you like to use strongly-typed fields and show clear consistent
messages to users when they enter the wrong type of data? AlloyUI's
`FormValidator` class answers the call! 

Typically you'd have to write tedious complicated validation functions, making
sure to dot all your i's and cross all your t's in order to get the functions to
work properly. Thankfully AlloyUI simplifies form validation with its
`FormValidator` class that is easy to configure and use. The form validator
accepts predefined properties that have helpful default validation
messages. And the form validator gives you flexibility to override the default
messages with your own custom messages.

![Figure 1: AlloyUI's `FormValidator` shows applicable default messages for all kinds user input violations.](../../images/alloyui-form-validator-reporting-violations.png)

You're probably chomping at the bit to start using `FormValidator`. This
tutorial shows you how to use it well. It's time to implement form validation in
your portlet with AlloyUI's `FormValidator`. 

## Adding Form Validation to Your Portlet [](id=adding-form-validation-to-your-portlet)

Here are the steps to follow to implement form validation in your portlet:

- **Step 1:** Create a Form with Named Input Fields
- **Step 2:** Add a `FormValidator`
- **Step 3:** Specify Rules for the Input Fields
- **Step 4:** Specify Custom Violation Messages (optional)

You'll start by creating your form. 

### Step 1: Create a Form with Named Input Fields [](id=step-1-create-a-form-with-named-input-fields)

If you haven't done so already, create an HTML `<form>`. Assign an ID to the
form to facilitate selecting it from the AlloyUI script that you'll write.
Lastly, name each of the form's `<input>` fields that you want to validate. 

Here's a form, for example, that has input fields `firstname`, `email`, and
`age`. Note that the form has an `id` attribute and each of the form's input
fields has a `name` attribute. 

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

    </form>

The `name` attributes of the `<input>` tags are required for form validation.
The validator applies rules to each input field based on its name. You'll learn
more about applying rules shortly. 

### Step 2: Add a `FormValidator` [](id=step-2-add-a-formvalidator)

Now that you have a form, you can add an instance of the
[`FormValidator`](http://alloyui.com/api/classes/A.FormValidator.html) class. 

Follow these steps to add a `FormValidator` to your JSP.

1.  Add the AlloyUI taglib `aui` to the beginning portion of your JSP, if it's
    not specified in your JSP already.

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

2.  Add `<aui:script>...</aui:script>` tags to the bottom of your JSP, unless
    you already have such tags. 

3.  Inside the `<aui:script>...</aui:script>` tags, instantiate a form validator
    that follows this format, replacing `formId` with your form's ID value: 

        AUI().use(
            'aui-form-validator',
            function(A) {
                new A.FormValidator(
                    {
                        boundingBox: '#formId',
                        rules: {

                        }
                    }
                )
            }
        ); 

    The `AUI` object uses the `aui-form-validator` module to create a new
    `FormValidator` component instance. The validator selects the form
    as the bounding box for the validator. The validator can apply rules, that
    you'll add to the rules parameter in the next step. 

3.  Save the changes to the JSP file.

If you deploy the portlet at this point, your fields won't get validated. This
is because the form has no specified rules. You'll add rules next. 

### Step 3: Specify Rules for Input Fields [](id=step-3-specify-rules-for-input-fields)

This step is where validation really comes into play. The validation rules you 
implement here control what users can submit in the form.

Here's a list of form validation rules available for AlloyUI's `FormValidator`: 

**acceptFiles:** A List of accepted file types (Default:empty)

**alpha:** A boolean value that determines if a field should contain only 
alphabetic characters (Default:none) 

**alphanum:** A boolean value that determines if a field should contain only 
alphanumeric characters
(Default:false) 

**date:** A boolean value that determines if a field should contain only a date
(Default:false)

**digits:** A boolean value that determines if a field should contain only 
digits (Default:false)

**email:** A boolean value that determines if a field should contain only an 
email address (Default:false)

**equalTo:** Determines if a field's contents are equal to the specified value.
(Default:empty)

**iri:** A boolean value that determines if a field should contain only an
International Rough Index (IRI) (Default:false) 

**max:** Determines if the integer value is greater than the specified value. 
(Default:none)

**maxLength:** Determines if the length of the field's contents are greater than 
the number of characters specified (Default:empty)

**min:** Determines if the integer value is less than the specified value 
(Default:none)

**minLength:** Determines if the length of a field's contents are less than the 
number of characters specified (Default:empty)

**number:** A Boolean value that specifies a field should contain only numeric
values (Default:false)

**range:** Determines if the integer value in the field is within the 
specified range (Default:none)

**rangeLength:** Determines if the length of a field's contents is within the 
specified range (Default:empty)

**required:** Determines if the field is required for submission 
(Default:false)

**url:** A boolean value that determines if a field's contents are a URL 
(Default:false)

To apply a rule to an input field, add the field's name in the `rules` attribute of your JSP
and assign the field one or more of the rule values listed above. 

For example, the following code applies `required`, `rangeLength`, and `alpha`
rules to an input field named `firstname`:

    rules: {
        firstname: { /*field name selected from input tag's name value*/
            required: true, /*this field is required*/
            rangeLength: [2,20], /*this field can contain 2 to 20 characters*/
            alpha: true /*this field can only contain alpha characters*/
        }
    }

If a user breaks any of the validation rules, a message is shown next to the
field. Only one message is displayed by default. If you want to show multiple
messages, you can specify `showAllMessages: true` when creating the
`FormValidator`, as in the example code snippet below: 

    new A.FormValidator(
        {
            boundingBox: '#myForm',
            rules: ...
            showAllMessages: true
		}

In addition to using the static default messages, you can customize validation
messages by using the `fieldStrings` attribute. 

### Step 4: Specify Custom Violation Messages - Optional [](id=step-4-specify-custom-violation-messages-optional)

The `fieldStrings` attribute lets you specify your own message text for a rule,
instead of using the rule's default message text. 

For example, the following code populates a variable named `fieldStrings` that
can be used to apply custom messages for the `required` and `rangeLength`
rules, for a form's `firstname` input field: 

	var fieldStrings = {
		firstname: {
			required: "The Force is strong with you, but we still need a name.",
			rangeLength: "2 to 20 characters, Padawan."  
		}
	}

You can create variables for your rules and field strings, and then pass those
variables as arguments to `FormValidator`'s constructor.

In the example AUI script code below, the variable `rules` holds rule
specifications for inputs named `firstname`, `email`, and `age`. The variable
`fieldStrings` holds custom messages for the `firstname` input. If the user
doesn't specify a value for the input or if the user enters a string less than 2
characters or greater than 20 characters long, the respective custom message is
shown.

	var rules = {
		firstname: {
			required: true,
			rangeLength: [2,20],
			alpha: true
		},
		email: {
			required: true
		},
		age: {
			number: true
		}
	}

	var fieldStrings = {
		firstname: {
			required: "The Force is strong with you, but we still need a name.",
			rangeLength: "2 to 20 characters Padawan."  
		}
	}

	AUI().use(
		'aui-form-validator',
		function(A) {
			new A.FormValidator(
				{
					boundingBox: myForm,
					fieldStrings: fieldStrings,
					rules: rules,
					showAllMessages: true
				}
			)
		}
	);

As you see from the previous code, declaring variables for rules and field
strings helps to organize your code and makes it easier to understand. 

Now that you've had a chance to implement a `FormValidator`, it's time to deploy
your portlet and test its form validation. 

![Figure 2: Here, the AUI form validator is used to display multiple custom validation messages for an input field.](../../images/alloyui-form-validator-in-a-portlet.png)

As you can see, using the AlloyUI form validator is a no-brainer for
implementing form validation! 

## Related Topics [](id=related-topics)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)
