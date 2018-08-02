# Validating Text and Numeric Fields [](id=validating-text-and-numeric-fields)

Validation ensures that only certain values are entered in a field. Validation
functionality is available for text and numeric fields. 

To enable validation: 

1.  Add a Text or Numeric field to a form in the Builder view. 

2.  Open the field's *Properties* tab. 

3.  Turn on the *Validation* toggle to enable validation and open its 
    configuration options. 

![Figure 1: Validate data to ensure you're collecting only useful information.](../../images/forms-text-validation.png)

## Validating Text Fields [](id=validating-text-fields)

Validation for text fields contains several options. You must first choose a 
list of available conditions to check: 

-   If Input *Contains*
-   If Input *Does Not Contain*
-   If Input *Is not URL*
-   If Input *Is not Email*
-   If Input *Does not Match*

If the condition isn't met, an error message is displayed to the user. Where you 
go from there depends on which condition you used. 

### If Input Contains/Does Not Contain [](id=if-input-contains-does-not-contain)

When you validate text data to check if it contains a certain value, there are 
two additional steps to take after selecting the condition: 

1.  Enter the text to check for. 

2.  Enter an error message so users understand why their submission failed. 

![Figure 2: If *Liferay* isn't part of the field's value, an error message is displayed.](../../images/forms-text-val-contains.png)

### If Input Is not URL/Email [](id=if-input-is-not-url-email)

Checking for properly formatted URLs and emails is easy. Just choose the 
condition from the *If Input* drop-down, and enter the error message. 

Valid URLs begin with `http://` or `https://`. Valid emails must contain `@`.
<!-- anything else we're checking for? -->

![Figure 3: Use text field validation to make sure users enter a valid email address or URL.](../../images/forms-text-val-email.png)

### If Input Does Not Match [](id=if-input-does-not-match)

The *Does Not Match* condition is used for entering 
[regular expressions](https://en.wikipedia.org/wiki/Regular_expression) 
to create custom validation criteria. For example, use this regular expression 
to ensure that ten consecutive numeric digits are entered in a phone number 
field:

    ^[0-9]{10}$

If you use regular expression validation, provide some explanatory text (e.g., 
help text, placeholder text, and a clear error message) to guide form users in 
entering the proper data.

![Figure 4: Regular expression text validation opens up countless possibilities.](../../images/forms-text-val-regex.png)

## Validating Numeric Fields [](id=validating-numeric-fields)

Numeric field validation is similar to text field validation, but the conditions 
compare the value of the number entered to some other value. 

Available conditions to check include:

-   Is greater than or equal to
-   Is greater than
-   Is not equal to
-   Is less than or equal to
-   Is less than or equal to
-   Is less than

For example, to make sure users don't enter a number over 10, enable validation
and use *Is greater than* with a value of *10*. Use the message 
*Please enter 10 or less*. 

![Figure 5: Numeric conditions constrain user-entered numeric data.](../../images/forms-numeric-val2.png)

![Figure 6: Make sure user-entered numeric data is within reasonable bounds. Nobody needs 11 sandwiches for lunch.](../../images/forms-numeric-val1.png)

Note that numeric fields are text fields validated to only allow numeric data 
entry. That's why they're in the Customized Elements section of the form fields 
list. In addition, the property *My numeric type is* (can be Integer or Decimal) 
on the Basic tab of a numeric form is another form of validation. 
