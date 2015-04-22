# Using the AlloyUI Validator Tag [](id=using-the-alloyui-validator-tag)

The AlloyUI validator tag is a handy way to validate the AlloyUI input fields of 
a form. It offers multiple validation methods depending on your needs, making 
form validation a simple task. This tutorial covers using the `aui:validator` 
tag in your portlets. AlloyUI also offers a comprehensive form validator
module that incorporates much of what is covered in this tutorial. See the 
[AlloyUI Form Validator](/develop/tutorials/-/knowledge_base/6-2/using-the-alloyui-form-validator-in-a-portlet) 
tutorial to learn more about it. Now that you know that the `aui:validator` tag 
is a versatile tool, it's time to learn how to use it. 

## Setting Up the AlloyUI Validator Tag [](id=setting-up-the-alloyui-validator-tag)

It doesn't take much to get the `aui:validator` tag working. There are just a 
few steps that you need to follow: 

- **Step 1:** Reference the AUI Taglib.
- **Step 2:** Configure the Form in the View JSP.
- **Step 3:** Insert and Configure the AUI validator Tag in the View JSP.  
 	
The example below follows these steps to implement AlloyUI input field 
validation in a portlet. Follow along and you'll be validating in no time!

### Step 1: Reference the AUI Taglib [](id=step-1-reference-the-aui-taglib)

First you need to make sure that you've referenced the `aui` taglib in your JSP. 
If you're already using AlloyUI tags in your JSP, then you can skip this step. 
If you're not, open the `view.jsp` of your portlet or create it if it doesn't 
already exist. Add the directive to reference the `aui` taglib:

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
        
Just like that you're ready to use the AUI tags in your portlet. Onwards to the 
next step! 

### Step 2: Set Up the Form and Input Fields [](id=step-2-set-up-the-form-and-input-fields)

If you don't yet have an AlloyUI form with input fields then you need to create 
one. Add the `aui:form` tag at the bottom of the `view.jsp` and place your input 
fields inside. It should look similar to the example here:

    <aui:form name="myForm" action="" method="post">
        <aui:input name="Name" value="" label="Name">
    
        </aui:input>
    </aui:form>

Repeat this step to add as many input fields as you need for your form. You're 
almost done! Onwards to the last step! 

### Step 3: Insert and Configure the AUI Validator Tag in the View JSP [](id=step-3-insert-and-configure-the-aui-validator-tag-in-the-view-jsp)

The final step is where you actually implement the field validation. This is 
straightforward but there are a few things to keep in mind. Still inside the 
`view.jsp`, nest the `aui:validator` tag inside of the input field that you want 
to validate. The following code illustrates the basic structure of the 
`aui:validator` tag. While only a single validation rule is implemented here, 
you can add several if you wish:

    <aui:form name="myForm" action="" method="post">
        <aui:input name="Name" value="" label="Name">
            <aui:validator name="required" />
        </aui:input>
    </aui:form>

In this example the `name` attribute of the `aui:validator` tag marks the 
surrounding input field as required. If you deploy your portlet and try to leave 
the field blank, you receive the default error message, "This field is 
required." The figure here shows what this error message looks like. 

![Figure 1: The default error message when a required field is left blank.](../../images/aui-validator-02.png)

If you want to override the default error message, you have to add an 
`errorMessage` attribute to the `aui:validator` tag. For example:

    <aui:validator name="alpha" errorMessage="Enter characters that exist in the alphabet next time, please." />

As you can see, you can have a lot of fun customizing your error messages. Note 
that `"alpha"` is used as the validation rule to restrict users to alphabetic 
characters for that field. The next section presents the full list of values 
available for the `name` attribute of the `aui:validator` tag. 

## Available Values for the AUI Validator name Attribute [](id=available-values-for-the-aui-validator-name-attribute)

There are several validation rules that you can implement for your input fields. 
As shown in the previous example, this is done by choosing different values for 
the `name` attribute of the `aui:validator` tag. The full list of values is 
shown here:

*acceptFiles*: Specifies that the field can contain only the file types given. 
Each file extension must be separated by a comma. For example: 

    <aui:validator name="acceptFiles">'jpg,png,tif,gif'</aui:validator>

*alpha*: Specifies that a field can contain only alphabetic characters. 

*alphanum*: Specifies that a field can contain only alphanumeric characters.

*date*: Specifies that a field can contain only a date.

*digits*: Specifies that a field can contain only digits.

*email*: Specifies that a field can contain only an email address.

*equalTo*: Determines if a field's contents are equal to the specified 
field id given. The id is declared inside of the opening and closing validator 
tags. For example:

    <aui:validator name="equalTo">'#<portlet:namespace />password'</aui:validator>

*max*: Determines if the integer value the field contains is greater than the 
specified value. For example, a `max` value of `20` is specified here:

    <aui:validator name="max">20</aui:validator> 

*maxLength*: Determines if the length of a field's characters is greater than 
the maximum length of characters specified. The syntax is the same as `max`.

*min*: Determines if the length of a field's characters is less than the 
maximum length of characters specified. The syntax is the same as `max`.

*minLength*: Evaluates whether a field's characters are less than the minimum 
length of characters specified. The syntax is the same as `max`.

*number*: Specifies that the field can contain only numerical values.

*required*: Specifies that the field is required before submission.

*url*: Specifies that the field can contain only a URL.

Next is an example of the `view.jsp` for a finished AUI validator portlet that 
uses several of the validation methods discussed above. The `"Name"` input field 
is required and restricted to alphabetic characters. A custom error message that 
informs the user of this is also specified. Additionally, the `"Email"` field is 
required and must be in the form of an email address:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    
    <portlet:defineObjects />
    
    <aui:form name="myForm" action="" method="post">
        <aui:input name="Name" value="" label="Name">
            <aui:validator name="required"/>
            <aui:validator name="alpha" errorMessage="Enter characters that exist in the alphabet next time please"/>
        </aui:input>
        <aui:input name="Email" value="" label="Email">
            <aui:validator name="required"/>
            <aui:validator name="email"/>
        </aui:input>
        <aui:button type="submit" name="submit" value="submit" />
    </aui:form>

Below is a figure of what the finished portlet could look like with the error 
messages triggered:

![Figure 2: Here's an example of a finished portlet that uses the above code.](../../images/aui-validator-01.png)

Congrats, now you know how to use the `aui:validator` tag! As you can see, it 
gives you a lot of control over what your users can enter in input fields.

## Related Topics [](id=related-topics)

[User Interfaces with the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)
