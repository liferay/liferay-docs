# Using the AlloyUI Validator Tag

The AlloyUI validator tag is a handy way to validate the aui:input fields of a 
form. It offers multiple validation methods depending on your needs, making form
validation a simple task. This tutorial covers using the aui:validator tag in 
your portlets. Alloy also offers a comprehensive form validator
module that incorporates a lot of what is covered in this tutorial; See the 
 [AlloyUI Form Validator](dev.liferay.com) tutorial to learn more about it.
<!-- alloyui form validator tutorial will need to be created and given a link -->
Now that you know why the aui:validator tag is a versatile tool, it's time to 
learn how to set it up next.

## Setting Up the AUI:Validator Tag

It doesn't take much to get aui:validator working. The example below shows how
to add and use the `aui:validator` tag in the `view.jsp` of a portlet:

- **Step 1:** Reference the AUI Taglib.
- **Step 2:** Configure the Form in the View JSP.
- **Step 3:** Insert and Configure the AUI validator Tag in the View JSP.  
 	
Go through each of these steps and you'll be validating in no time!

### Step 1: Reference the AUI Taglib.

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
exist.

2.  Add a directive to reference the `aui` taglib:

        ```
        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
    
        ```
Just like that you are ready to use the AUI tags in your portlet. Onward to step
2!

### Step 2: Setup the Form and Input Fields

1.  Inside the `view.jsp` of your portlet add the `aui:form` tags at the bottom 
and place your input fields inside:

        ```
        <aui:form name="myForm" action="" method="post">
             <aui:input name="Name" value="" label="Name">
             
             </aui:input>
        </aui:form>
    
        ```
Repeat the step above to add as many input fields as you need for your form.
Almost done, onward to the last step!

### Step 3: Insert and Configure the AUI Validator Tag in the View JSP

This final step is easy, but has a lot of moving parts to it. This is where the
real fun comes in:

Still inside the `view.jsp` nest the `aui:validator` tag inside of the 
`aui:input` tags:

        ```
        <aui:form name="myForm" action="" method="post">
             <aui:input name="Name" value="" label="Name">
                  <aui:validator name="required" />
             </aui:input>
        </aui:form>
    
        ```
There you have it. The code above illustrates the basic structure of the 
`aui:validator` tag. You can nest multiple validator tags inside each input
field, depending on how many rules you need to validate. In the example above 
the value of the name attribute of the `aui:validator` tag marks the field as 
required. If you tried to leave the field blank and move onto another you would 
receive a default error message that says "This field is required." The figure
below shows what the error message looks like when a required field is left 
blank.

![Figure 1: Here's what the error message looks like.](../../images/aui-validator-02.png)


If you want to replace the default error message, you have to add a 
`errorMessage` attribute to the `aui:validator` tag. For example:

		```
		<aui:validator name="alpha" errorMessage="Enter characters that exist in the alphabet next time please" />
        
		```
As you can see, you can have a lot of fun customizing your error messages. 

Next you can take a look at the full list of available name values for the AUI
validator.
		
## Available Name Values for the AUI Validator		

There are several values for you to choose from for the name attribute of the 
`aui:validator` tag. The full list of values is below:

**acceptFiles:** List of filetypes accepted. (Default:empty)

**alpha:** Evaluates whether or not a field contains only alpha characters.
(Default:none)

**alphanum:** A boolean value that determines whether a field is suppose to
contain only alphanumeric characters and evaluates it accordingly.
(Default:false)

**date:** A boolean value that determines whether a field is a Date and
evaluates it accordingly. (Default:false)

**digits:** A boolean value that determines whether a field is suppose to
contain only digits and evaluates it accordingly. (Default:false)

**email:** A boolean value that determines whether a field is a email and
evaluates it accordingly. (Default:false)

**equalTo:** Evaluates whether a field is equal to the field written.
(Default:empty)

**iri:** A boolean value that determines whether a field is a IRI and evaluates
it accordingly (Default:false)

**max:** Evaluates whether the integer value is greater than the value written. (Default:none)

**maxLength:** Evaluates whether a field is over the maximum length of
characters specified. (Default:empty)

**min:** Evaluates whether the integer value is under the value written. (Default:none)

**minLength:** Evaluates whether a field is under the minimum length of
characters specified. (Default:empty)

**number:** A Boolean value that determines whether a field is suppose to
contain only numeric values and evaluates it accordingly. (Default:false)

**required:** Evaluates whether the field is required before submission.
(Default:false)

**url:** A boolean value that determines whether a field is a URL and evaluates
it accordingly. (Default:false)

For attributes that have a max or min and require a value, you will need to 
specify it in the `aui:validator` tag. For example:

        ```
        <aui:validator name="max">20</aui:validator>
       
        ```
Here is an example View JSP for a finished AUI validator portlet:

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

Congrats, now you know How to use the AUI Validator tag! If you would like a
different UI framework to explore than Alloy, give the [Liferay UI](http://dev.liferay.com/tutorials/-/knowledge_base/liferay-ui-taglibs-lp-6-2-develop-tutorial)
tutorials a try.
