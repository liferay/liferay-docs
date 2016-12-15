# Forms and Validation [](id=forms-and-validation)

Creating forms is easy and flexible, thanks to @product@'s form, input, and 
validation tags.

The `<aui:form>` tag sets up the necessary code (HTML, JS), uses Liferay 
JavaScript APIs, calls the validation framework, and submits the form data to 
the back-end.

The *input* tags (`<aui:input>`, `<liferay-ui:input-*>`, etc.) generate the
necessary code (HTML, CSS, JS) to provide you with a consistent UI throughout
your form. These tags also initialize the field's state (e.g. `value`,
`disabled`, `readonly`, etc.).

The `<aui:validator>` tag lets you create validation rules for your form's 
input tags. This ensures that your users enter the proper data in the format you
expect before it gets sent to the back-end for final validation and processing.

The following tags support validators natively:

- `<aui:input>`
- `<aui:select>`
- `<liferay-ui:input-date>`
- `<liferay-ui:input-search>`

All other fields can be validated using the method described in the
[Adding Custom Validators in JavaScript](/develop/tutorials/-/knowledge_base/7-0/forms-and-validation#adding-custom-validators-in-javascript)
section below.

Get started by creating a basic form next.

## Creating Your First Form [](id=creating-your-first-form)

Below is an example configuration for a simple form in a JSP:

    <aui:form action="<%= myActionURL %>" method="post" name="myForm">
            <aui:input label="My Text Input" name="myTextInput" type="text" 
            value='<%= "My Text Value" %>' />
    
            <aui:button type="submit" />
    </aui:form>

Although this form is simple, it provides all that's needed to pass the data to
the back-end. The full HTML generated for this example is shown below:

    <form action="http://localhost:8080/web/guest/home?p_p_id=my_portlet&amp;p_p_lifecycle=1&amp;p_p_state=normal&amp;p_p_mode=view&amp;p_p_col_id=column-1&amp;p_p_col_count=3&amp;_my_portlet_javax.portlet.action=%2Fmy%2Faction&amp;_my_portlet_mvcRenderCommandName=%2Fmy%2Faction&amp;p_auth=WAxorpsN" class="form" data-fm-namespace="_my_portlet_" id="_my_portlet_myForm" method="post" name="_my_portlet_myForm">
            <input class="field form-control" id="_my_portlet_formDate" 
            name="_my_portlet_formDate" type="hidden" value="1472516415545">
    
            <div class="form-group input-text-wrapper">
                    <label class="control-label" 
                    for="_my_portlet_myTextInput">My Text Input</label>
    
                    <input class="field form-control" id="_my_portlet_myTextInput" 
                    name="_my_portlet_myTextInput" type="text" 
                    value="My Text Value">
            </div>
    
            <button class="btn  btn-primary btn-default" id="_my_portlet_kpyg" 
            type="submit">
                    <span class="lfr-btn-label">Save</span>
            </button>
    </form>

As you can see, the tags provide all this to you for very little work! Next you
can learn how to add validation to your forms.

## Adding Validation [](id=adding-validation)

What if you want to ensure the user enters the required data? Add validators.

An example configuration is shown below:

    <aui:input label="My Text Input" name="myTextInput" type="text" 
    value='<%= "My Text Value" %>'>
            <aui:validator name="required" />
    </aui:input>
 
This forces the user to enter something before the form is submitted.

![Figure 1: A field with a failed required validator.](../../images/forms-input-required.png)

What if you wanted to restrict the value to a number between `0` and `10`? 
There's a validator for that. Each of the validators shown below must pass to
submit the form:

    <aui:validator name="required" />
    <aui:validator name="number" />
    <aui:validator name="range">[0,10]</aui:validator>

You can even customize the error message, as shown below:

    <aui:validator errorMessage="Please enter how many fingers you have." 
    name="range">[0,10]</aui:validator>

![Figure 2: A required validator with a custom error message.](../../images/forms-input-required-message.png)

Below is a list of all the available validation rules:

**acceptFiles:** Specifies that the field can contain only the file types given. 
Each file extension must be separated by a comma. For example `<aui:validator
name="acceptFiles">'jpg,png,tif,gif'</aui:validator>`

**alpha:** Allows only alphabetic characters. 

**alphanum:** Allows only alphanumeric characters.

**date:** Allows only a date.

**digits:** Allows only digits.

**email:** Allows only an email address.

**equalTo:** Allows only contents equal to some other field that has the
specified field ID. The ID is declared in the opening and closing validator
tags. For example `<aui:validator name="equalTo">'#<portlet:namespace
/>password'</aui:validator>`

**max:** Allows only an integer value less than the specified value. For
example, a `max` value of `20` is specified here `<aui:validator
name="max">20</aui:validator>` 

**maxLength:** Allows a maximum field length of the specified size. The syntax
is the same as `max`.

**min:** Allows only an integer value greater than the specified value. The
syntax is the same as `max`.

**minLength:** Allows a field length longer than the specified size. The syntax
is the same as `max`.

**number:** Allows only numerical values.

**range:** Allows only a number between the specified range. For example,
a range between 1.23 and 10 is specified here `<aui:validator
name="range">[1.23,10]</aui:validator>`

**rangeLength:** Allows a field length between the specified range. For example,
a range between 3 and 8 characters long is specified here 
`<aui:validator name="rangeLength">[3,8]</aui:validator>`

**required:** Prevents a blank field.

**url:** Allows only a URL value.

Now that you know how to validate your forms, you can learn how to conditionally
require user input next.

## Conditionally Requiring A Field [](id=conditionally-requiring-a-field)

Sometimes you'll want to validate a field based on the value of another field.
You can do this by checking for that condition in a JavaScript function within 
the `required` validator's body.

Below is an example configuration:

    <aui:input label="My Checkbox" name="myCheckbox" type="checkbox" />
    
    <aui:input label="My Text Input" name="myTextInput" type="text">
            <aui:validator name="required">
                    function() {
                            return AUI.$('#<portlet:namespace />myCheckbox').prop('checked');
                    }
            </aui:validator>
    </aui:input>

![Figure 3: Fields can required based on other conditions.](../../images/forms-input-required-condition.png)

Next you can learn how to add custom validators.

## Adding Custom Validators [](id=adding-custom-validators)

So far, you've only seen the default set of AUI validator rules. What if you
need something that the default rules don't provide?

You can write your own validator and optionally supplement it with built-in
validators, as shown below:

    <aui:input label="Email" name="email" type="text">
            <aui:validator name="email" />
    
            <aui:validator errorMessage="Only emails from @example.com are allowed." 
            name="custom">
                    function(val, fieldNode, ruleValue) {
                            var regex = new RegExp(/@example\.com$/i);
    
                            return regex.test(val);
                    }
            </aui:validator>
    </aui:input>

This example runs the regular `email` validator, as well as a `custom` domain 
validator.

![Figure 4: You can write your own custom validations.](../../images/forms-input-custom.png)

Next you can learn how to add custom validators in JavaScript.

## Adding Custom Validators in JavaScript [](id=adding-custom-validators-in-javascript)

Sometimes you need to add additional validation dynamically after the page has 
rendered. Perhaps some additional fields were added to the DOM via an AJAX 
request.

To do this, you must access the `Liferay.Form` object, as demonstrated below:

    <aui:script use="liferay-form">
            var form = Liferay.Form.get('<portlet:namespace />myForm');
            // ...

The `Liferay.Form` object allows you to `get()` and `set()` `fieldRules`.

`fieldRules` are the JavaScript equivalent of all the validators attached to the 
form.

This example below uses a `custom` validator and the built-in `number` validator.

    <aui:script use="liferay-form">
            var form = Liferay.Form.get('<portlet:namespace />myForm');
    
            var oldFieldRules = form.get('fieldRules');
    
            var newFieldRules = [
                    {
                            body: function (val, fieldNode, ruleValue) {
                                    return (val !== '2');
                            },
                            custom: true,
                            errorMessage: 'must-not-equal-2',
                            fieldName: 'fooInput',
                            validatorName: 'custom_fooInput'
                    },
                    {
                            fieldName: 'fooInput',
                            validatorName: 'number'
                    }
            ];
    
            var fieldRules = oldFieldRules.concat(newFieldRules);
    
            form.set('fieldRules', fieldRules);
    
    </aui:script>

Notice that `newFieldRules` are combined with `oldFieldRules`, using `concat()`. 
You could leave this part out, and you'd have a whole new set of validators for 
the whole form.

Next you can learn how to validate your forms manually. 

## Manual Validation [](id=manual-validation)

You may need to execute validation on a field, based on some event not typical
of user input. For instance, you may need to validate a related field at the
same time.

To do this, you must access the `formValidator` object and call the 
`validateField()` method, passing the field's `name`. Below is an example
configuration:

    <aui:input label="Old Title" name="oldTitle" type="text">
            <aui:validator errorMessage="The New Title cannot match the Old Title" 
            name="custom">
                    function(val, fieldNode, ruleValue) {
                            <portlet:namespace />checkOtherTitle('<portlet:namespace />newTitle');
    
                            // check if old and new titles are a match
    
                            return !match;
                    }
            </aui:validator>
    </aui:input>
    
    <aui:input label="New Title" name="newTitle" type="text">
            <!-- same custom validator -->
    </aui:input>

    <aui:script use="liferay-form">
            function <portlet:namespace />checkOtherTitle(fieldName) {
                    var formValidator = 
                    Liferay.Form.get('<portlet:namespace />myForm').formValidator;
    
                    formValidator.validateField(fieldName);
            }
    </aui:script>
 
Now you know how to create and validate forms!

## Related Topics [](id=related-topics)

[Front-End Taglibs](/develop/tutorials/-/knowledge_base/7-0/front-end-taglibs)
