# Invoking JSON Web Services via JavaScript

This tutorial explains how to invoke Liferay's JSON web services via JavaScript
and provides examples. To learn how to invoke Liferay's JSON web services via
URL or cURL, please see the
[Invoking JSON Web Services via URL](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-url)
tutorial or the
[Invoking JSON Web Services via cURL](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-curl)
tutorial.

## Loading AlloyUI

Liferay web pages use the AlloyUI JavaScript framework. Among the JavaScript
objects created for each Liferay page is a `Liferay` object. This object
includes a `Service` function that can be used to invoke Liferay's API. To
invoke Liferay web services via `Liferay.Service(...)`, your JavaScript context
must include the AlloyUI JavaScript framework. Liferay 6.2 uses AlloyUI 2.0.x.
If you're working in a JSP, you can load the AlloyUI taglib and wrap your
JavaScript code in an `<aui:script>` tag. Here's the required import:

    <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

To load specific AUI modules, specify them via the `use` attribute. (By default,
the `<aui:script>` tag includes the base AUI module.) For example, to use the
AUI `node` and `event` modules, wrap your code like this:

    <aui:script use="node, event">
        // Liferay service invocation here
    </aui:script>

If you're not working in a JSP, you won't have access to taglibs. In this case,
you can create an AUI context manually. For example, use the following HTML
fragment to load the AUI seed and CSS files:

    <script src="http://cdn.alloyui.com/2.0.0/aui/aui-min.js"></script>
    <link href="http://cdn.alloyui.com/2.0.0/aui-css/css/bootstrap.min.css" rel="stylesheet"></link>

Then you can create an AUI context like this:

    AUI().use('aui-base', function(A){
        // Liferay service invocation here
    });

## Get User JSON Web Service Invocation via JavaScript

Let's examine a simple JSON web service invocation in JavaScript:

    Liferay.Service(
        '/user/get-user-by-email-address`,
        {
            companyId: Liferay.ThemeDisplay.getCompanyId(),
            emailAddress: 'test@liferay.com`
        },
        function(obj) {
            console.log(obj);
        }
    );

If you run this code, the test@liferay.com user (JSON object) is logged to the
JavaScript console.

The `Liferay.Service(...)` function takes three arguments:

1. A string representing the service being invoked
2. A parameters object
3. A callback function

The callback function takes the result of the service invocation as an argument.

## Add User JSON Web Service Invocation via JavaScript

Here's an example JSON web service invocation in JavaScript that adds a new
user:

    Liferay.Service(
        '/user/add-user',
        {
            companyId: Liferay.ThemeDisplay.getCompanyId(),
            autoPassword: false,
            password1: 'test',
            password2: 'test',
            autoScreenName: false,
            screenName: 'joe.bloggs',
            emailAddress: 'joe.bloggs@liferay.com',
            facebookId: 0,
            openId: '',
            locale: 'en_US',
            firstName: 'Joe',
            middleName: 'T',
            lastName: 'Bloggs',
            prefixId: 0,
            suffixId: 0,
            male: true,
            birthdayMonth: 1,
            birthdayDay: 1,
            birthdayYear: 1970,
            jobTitle: 'Tester',
            groupIds: null,
            organizationIds: null,
            roleIds: null,
            userGroupIds: null,
            sendEmail: false,
            serviceContext: {assetTagNames: ['test']}
        },
        function(obj) {
            console.log(obj);
        }
    );

Including the specified `serviceContext` object assigns the *test* tag to the
newly created user.

## Invoking JSON Web Services via JavaScript in a Portlet

You can adapt the example from the previous section for use in a custom portlet.
For example, the JSP page below provides a form that allows a first name, middle
name, last name, screen name, and email address to be specified. When the user
clicks the *Add User* button, these values are used to create a new user.

    <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <portlet:defineObjects />

    <portlet:renderURL var="successURL">
        <portlet:param name="mvcPath" value="/success.jsp"/>
    </portlet:renderURL>

    <portlet:renderURL var="failureURL">
        <portlet:param name="mvcPath" value="/failure.jsp"/>
    </portlet:renderURL>

    <aui:form method="GET" name="<portlet:namespace />fm">
        <aui:fieldset>
            <aui:input label="First Name" name="first-name"></aui:input>
            <aui:input label="Middle Name" name="middle-name"></aui:input>
            <aui:input label="Last Name" name="last-name"></aui:input>
            <aui:input label="Screen Name" name="screen-name"></aui:input>
            <aui:input label="Email Address" name="email-address"></aui:input>
        </aui:fieldset>

            <p>Click the button below to add a new user by invoking Liferay's JSON web services.</p>
            
            <aui:button-row>
                <aui:button id="add-user" value="Add User">
                </aui:button>
            </aui:button-row>
    </aui:form>

    <aui:script use="node, event">
    var addUserButton = A.one('#add-user');

    var firstNameNode = A.one('#<portlet:namespace />first-name');
    var middleNameNode = A.one('#<portlet:namespace />middle-name');
    var lastNameNode = A.one('#<portlet:namespace />last-name');
    var screenNameNode = A.one('#<portlet:namespace />screen-name');
    var emailAddressNode = A.one('#<portlet:namespace />email-address');

    addUserButton.on('click', function(event) {
            var firstName = firstNameNode.get('value');
            var middleName = middleNameNode.get('value');
            var lastName = lastNameNode.get('value');
            var screenName = screenNameNode.get('value');
            var emailAddress = emailAddressNode.get('value');

        var user = Liferay.Service(
            '/user/add-user',
            {
                companyId: Liferay.ThemeDisplay.getCompanyId(),
                autoPassword: false,
                password1: 'test',
                password2: 'test',
                autoScreenName: false,
                screenName: screenName,
                emailAddress: emailAddress,
                facebookId: 0,
                openId: '',
                locale: 'en_US',
                firstName: firstName,
                middleName: middleName,
                lastName: lastName,
                prefixId: 0,
                suffixId: 0,
                male: true,
                birthdayMonth: 1,
                birthdayDay: 1,
                birthdayYear: 1970,
                jobTitle: 'Tester',
                groupIds: null,
                organizationIds: null,
                roleIds: null,
                userGroupIds: null,
                sendEmail: false,
                serviceContext: {assetTagNames: ['test']}
            },
            function(obj) {
                console.log(obj);
                
                if (obj.hasOwnProperty('createDate')) {             
                    window.open('<%= successURL %>', '_self');
                }
                else {
                    window.open('<%= failureURL %>', '_self');
                }
            }
        );
    });
    </aui:script>

In this example, it's assumed that the JSP page is part of a portlet plugin that
uses or extends Liferay's `MVCPortlet` class. This is required since the
`mvcPath` URL parameter is used in the snippet above. It's also assumed that the
JSP code is in a file named `view.jsp` and that there are also `success.jsp` and
`failure.jsp` files in the same directory.

## Related Topics

[Invoking JSON Web Services via URL](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-url)

[Invoking JSON Web Services via cURL](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-curl)
