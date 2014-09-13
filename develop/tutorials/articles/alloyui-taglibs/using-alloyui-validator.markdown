# Using the AlloyUI Validator Tag

The AlloyUI validator tag is a handy way to validate the AlloyUI input fields of 
a form. It offers multiple validation methods depending on your needs, making 
form validation a simple task. This tutorial covers using the `aui:validator` 
tag in your portlets. AlloyUI also offers a comprehensive form validator
module that incorporates much of what is covered in this tutorial. See the 
[AlloyUI Form Validator](dev.liferay.com) tutorial to learn more about it. Now 
that you know that the `aui:validator` tag is a versatile tool, it's time to 
learn how to use it. 

<!-- alloyui form validator tutorial will need to be created and given a link -->

## Setting Up the AlloyUI Validator Tag 

It doesn't take much to get the `aui:validator` tag working. There are just a 
few steps that you need to follow: 

- **Step 1:** Reference the AUI Taglib.
- **Step 2:** Configure the Form in the View JSP.
- **Step 3:** Insert and Configure the AUI validator Tag in the View JSP.  
 	
The example below follows these steps to implement AlloyUI input field 
validation in a portlet. Follow along and you'll be validating in no time!

### Step 1: Reference the AUI Taglib 

First you need to make sure that you've referenced the `aui` taglib in your JSP. 
If you're already using AlloyUI tags in your JSP, then you can skip this step. 
If you're not, open the `view.jsp` of your portlet or create it if it doesn't 
already exist. Add the directive to reference the `aui` taglib:

        ```
        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
    
        ```
        
Just like that you're ready to use the AUI tags in your portlet. Onwards to the 
next step! 

### Step 2: Set Up the Form and Input Fields 

If you don't yet have an AlloyUI form with input fields then you need to create 
one. Add the `aui:form` tag at the bottom of the `view.jsp` and place your input 
fields inside. It should look similar to the example here:

        ```
        <aui:form name="myForm" action="" method="post">
             <aui:input name="Name" value="" label="Name">
             
             </aui:input>
        </aui:form>
    
        ```

Repeat this step to add as many input fields as you need for your form. You're 
almost done! Onwards to the last step! 

### Step 3: Insert and Configure the AUI Validator Tag in the View JSP 

The final step is where you actually implement the field validation. This is 
straightforward but there are a few things to keep in mind. Still inside the 
`view.jsp`, nest the `aui:validator` tag inside of the input field that you want 
to validate. The following code illustrates the basic structure of the 
`aui:validator` tag. While only a single validation rule is implemented here, 
you can add several if you wish:

        ```
        <aui:form name="myForm" action="" method="post">
             <aui:input name="Name" value="" label="Name">
                  <aui:validator name="required" />
             </aui:input>
        </aui:form>
    
        ```

In this example the `name` attribute of the `aui:validator` tag marks the 
surrounding input field as required. If you deploy your portlet and try to leave 
the field blank, you receive the default error message, "This field is 
required." The figure here shows what this error message looks like. 

![Figure 1: The default error message when a required field is left blank.](../../images/aui-validator-02.png)

If you want to override the default error message, you have to add an 
`errorMessage` attribute to the `aui:validator` tag. For example:

		```
		<aui:validator name="alpha" errorMessage="Enter characters that exist in the alphabet next time, please." />
        
		```

As you can see, you can have a lot of fun customizing your error messages. Note 
that `"alpha"` is used as the validation rule to restrict users to alphabetic 
characters for that field. The next section presents the full list of values 
available for the `name` attribute of the `aui:validator` tag. 

## Available Values for the AUI Validator name Attribute 

There are several validation rules that you can implement for your input fields. 
As shown in the previous example, this is done by choosing different values for 
the `name` attribute of the `aui:validator` tag. The full list of values is 
shown here: 

**acceptFiles:** List of accepted file types. (Default:empty)

**alpha:** Evaluates whether a field can contain only alpha characters. 
(Default:none)

**alphanum:** A boolean value that determines whether a field is supposed to
contain only alphanumeric characters. (Default:false)

**date:** A boolean value that determines whether a field contains a date. 
(Default:false)

**digits:** A boolean value that determines whether a field is supposed to
contain only digits. (Default:false)

**email:** A boolean value that determines whether a field is supoosed to 
contain an email address. (Default:false)

**equalTo:** Evaluates whether a field's contents are equal to the specified 
value. (Default:empty)

**iri:** A boolean value that determines if a field contains an IRI. 
(Default:false)

**max:** Determines if an integer value is greater than the value written. 
(Default:none)

**maxLength:** Determines if a field's contents are greater than the maximum 
length of characters specified. (Default:empty)

**min:** Determines if an integer's value is less than the value written. 
(Default:none)

**minLength:** Determines if a field's contents are less than the minimum length 
of characters specified. (Default:empty)

**number:** A Boolean value that determines if a field is supposed to contain 
only numeric values. (Default:false)

**required:** Determines if the field is required before submission. 
(Default:false)

**url:** A boolean value that determines if a field contains a URL. 
(Default:false)

For attributes that have a max or min and require a value, you need to specify 
it in the `aui:validator` tag. For example:

        ```
        <aui:validator name="max">20</aui:validator>
       
        ```

Here is an example `view.jsp` for a finished AUI validator portlet:

		```
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
        
        ```

Below is a figure of what the finished portlet could look like with the error 
messages triggered:

![Figure 2: Here's an example of a finished portlet that uses the code above.](../../images/aui-validator-01.png)

Congrats, now you know how to use the `aui:validator` tag! 

## Next Steps 

[Liferay UI](/tutorials/-/knowledge_base/liferay-ui-taglibs-lp-6-2-develop-tutorial)
