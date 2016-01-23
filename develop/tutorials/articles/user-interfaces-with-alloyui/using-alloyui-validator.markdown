# Using the AlloyUI Validator Tag [](id=using-the-alloyui-validator-tag)

The AlloyUI validator tag is a handy way to make sure users enter the right data
into a form's AlloyUI input fields. It offers multiple data checks, making form
validation a simple task. This tutorial covers using the `aui:validator` tag in
your portlets. AlloyUI also offers a comprehensive form validator module that
incorporates much of what is covered in this tutorial. See the [AlloyUI Form Validator](/develop/tutorials/-/knowledge_base/6-2/using-the-alloyui-form-validator-in-a-portlet) 
tutorial to learn more about it. 

## Setting Up the AlloyUI Validator Tag [](id=setting-up-the-alloyui-validator-tag)

It doesn't take much to get the `aui:validator` tag working. 

- **Step 1:** Reference the AUI Taglib.
- **Step 2:** Configure the Form in the View JSP.
- **Step 3:** Insert and Configure the AUI validator Tag in the View JSP. 
 	
The example below uses these steps to implement AlloyUI input field 
validation in a portlet. Follow along and you'll be validating in no time!

### Step 1: Reference the AUI Taglib [](id=step-1-reference-the-aui-taglib)

First you need to make sure that you've referenced the `aui` taglib in your JSP. 
If you're already using AlloyUI tags in your JSP, then you can skip this step. 
If you're not, open or create your portlet's `view.jsp`. Add the `aui` taglib
declaration:

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
 
Just like that you're ready to use the AUI tags in your portlet. Onward to the 
next step! 

### Step 2: Set Up the Form and Input Fields [](id=step-2-set-up-the-form-and-input-fields)

If you don't yet have an AlloyUI form with input fields then you need to create 
one. Add the `aui:form` tag to the bottom of the `view.jsp` and place your input 
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
`view.jsp`, nest the `aui:validator` tag in the input field that you want 
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

If you want to override the default error message, add an `errorMessage`
attribute to the `aui:validator` tag:

    <aui:validator name="alpha" errorMessage="Enter characters that exist in the alphabet next time, please." />

As you can see, you can have a lot of fun customizing your error messages. Note 
that `"alpha"` is used as the validation rule to restrict users to alphabetic 
characters for that field. Next, the full list of values available
for the `name` attribute of the `aui:validator` tag are presented. 

## Available Validation Rules [](id=available-validation-rules)

There are several validation rules that you can implement for your input fields. 
As shown in the previous example, this is done by choosing different values for 
the `name` attribute of the `aui:validator` tag: 

*acceptFiles*: Specifies that the field can contain only the file types given. 
Each file extension must be separated by a comma. For example `<aui:validator
name="acceptFiles">'jpg,png,tif,gif'</aui:validator>`

**alpha**: Allows only alphabetic characters. 

**alphanum**: Allows only alphanumeric characters.

**date**: Allows only a date.

**digits**: Allows only digits.

**email**: Allows only an email address.

**equalTo**: Allows only contents equal some other field that has the specified
field ID. The ID is declared in the opening and closing validator tags. For
example `<aui:validator name="equalTo">'#<portlet:namespace
/>password'</aui:validator>`

**max**: Allows only an integer value less than the specified value. For
example, a `max` value of `20` is specified here `<aui:validator
name="max">20</aui:validator>` 

**maxLength**: Allows a maximum field length of the specified size. The syntax
is the same as `max`.

**min**: Allows only an integer value greater than the specified value. The
syntax is the same as `max`.

**minLength**: Allows a field length longer than the specified size. The syntax
is the same as `max`.

**number**: Allows only numerical values.

**range**: Allows only a number between the specified range. For example,
a range between 1.23 and 10 is specified here `<aui:validator
name="range">[1.23,10]</aui:validator>`

**rangeLength**: Allows field length between the specified range. For example,
a range between 3 and 8 is specified here `<aui:validator
name="rangeLength">[3,8]</aui:validator>`

**required**: Prevents a blank field.

**url**: Allows only a URL value. 

Below is a `view.jsp` that uses several of the validation rules discussed above.
The `"Name"` input field is required and restricted to alphabetic characters. A
custom error message that informs the user of this is also specified.
Additionally, the `"Email"` field is required and must be in the form of an
email address:

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

Below is what a finished portlet could look like with the error messages
triggered:

![Figure 2: Here's an example of a finished portlet that uses the above code.](../../images/aui-validator-01.png)

The custom error message is supplied as a string at the moment. As this is not
best practice, you should supply the custom error message via a language key.
You'll take care of this next.

## Creating a Language Key Hook for the Error Message [](id=creating-a-language-key-hook-for-the-error-message)

In order to supply the language key for the custom error message you will need
to create a hook.

Follow the steps below to create the language key hook:
    
1.  Goto *File*&rarr;*New*&rarr;*Liferay Plugin Project*.

2.  Set the *Project name* as `aui-validator-language-hook` and *Display name* 
as `Aui Validator Language Hook`.

3. Set the proper SDK and runtime, select *Hook* for the *Plugin type*, and
click *Finish*.

    The skeleton for the hook is complete. Now you need to configure the hook to
    modify the existing language properties. In this case you'll be modifying
    the English US language properties, but the process can be used to hook into
    any existing language properties file.

1. Right-click the language key hook you just created in the package explorer
on the left and select *New*&rarr;*Liferay Hook Configuration*.

2. With `aui-validator-language-hook` set as the *Hook plugin project* select
*Language properties* for the *hook type* and *click* *Next*.

3. Leave the default *Content folder* and click *Add*.

4. Enter *Language_en.properties* for the property file, click *OK* and 
*Finish*.

5. Open the `docroot/WEB-INF/src` folder of the `aui-validator-language-hook` 
and open the `Language_en.properties` file in the `content` package.

6. Add the following language key to the file and Save it:

        characters-that-exist = Enter characters that exist in the alphabet next time please

    Now that you have the language key defined you can update the tag to use it.     
    
7. In your `view.jsp`, update the `aui:validator` tag to look like the following 
   code:

        <aui:validator name="alpha" errorMessage="characters-that-exist"/>
    
8. Redeploy the portlet, leave the *Name* field blank, and submit the form. 
The error message now uses the language key you just created! 

Congrats! Now you know how to use the `aui:validator` tag. As you can see, it 
gives you a lot of control over what your users can enter in input fields.

## Related Topics [](id=related-topics)

[User Interfaces with the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)
