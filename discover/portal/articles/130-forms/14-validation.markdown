# Validating Text and Numeric Fields

Validation ensures that only certain values are entered in a field. Validation
functionality is available for Text Fields and Numeric Fields.

To enable validation, 

1.  Add a Text or Numeric Field to a form in the Builder view.

2.  Open the field's Properties tab.

3.  Slide the switcher next to *Validation* to enable validation and open its
    configuration options.

![Figure x: Validate data to ensure you're collecting only useful information.](../../images/forms-text-validation.png)

## Validating Text Fields

Validation for text fields contains several options.

First you must choose a list of available conditions to check. Of the condition
is not met, an error message is displayed to the user:

- If Input *Contains*
- If Input *Does Not Contain*
- If Input *Is not URL*
- If Input *Is not Email*
- If Input *Does not Match*

Where you go  from there depends on which condition you used.

### If Input Contains/Does Not Contain

When you validate text data to check whether it does or doesn't contain a
certain text value, there's one additional step to take after selecting the
condition:

1.  Enter the text to check for in the value.

2.  Enter an error message so Users understand why their submission failed.

![Figure x: If "Liferay" is not part of the field's value, an error message is displayed.](../../images/forms-text-val-contains.png)

### If Input Is not URL/Email

Checking for properly formatted URLs and Emails is easy. Just choose the
condition from the *If Input* drop-down, and enter the error message.

Valid URLs begin with `http://` or `https://`.
<!-- anything else we're checking for? -->

Valid emails must contain `@`. 
<!--anything else?-->

![Figure x: Make sure users enter a valid email address or URL using text field validation.](../../images/forms-text-val-email.png)

### If Input Does Not Match

The *Does Not Match* condition is used for entering 
[Regular Expressions](https://en.wikipedia.org/wiki/Regular_expression) 
to create custom validation criteria. For example, use this basic RegEx to
ensure that ten consecutive numeric digits are entered in a Phone Number field:

    ^[0-9]{10}$

If you use RegEx validation, provide some explanatory text (using Help Text, Placeholder Text, and a
clear Error Message) to guide form Users in entering the proper data.

![Figure x: RegEx text validation opens up countless validation possibilities.](../../images/forms-text-val-regex.png)

## Validating Numeric Fields

Numeric field validation works much the same as Text Field validation, but the
conditions are for comparing the value of the number entered to some other
value.

Available conditions to check include:

- Is greater than or equal to
- Is greater than
- Is not equal to
- Is less than or equal to
- Is less than or equal to
- Is less than

![Figure x: Make sure User-entered numeric data is within reasonable bounds.](../../images/forms-numeric-val1.png)

To make sure Users don't enter a number over 10, make sure the enable validation
and use the *Is greater than or equal to* condition with a value of *10*. Use
the message *Please enter 10 or less**.

![Figure x: Numeric conditions constrain User-entered numeric data.](../../images/forms-numeric-val2.png)

Numeric fields are themselves just Text Fields validated to only allow numeric
data entry. That's why they're in the Customized Elements section of the Form
fields list. In addition, the property on the Basic tab of a numeric form called
*My numeric type is* (can be Integer or Decimal) is another form of validation.

