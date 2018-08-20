# JSON Web Services Invocation Examples [](id=json-web-services-invocation-examples)

This tutorial provides examples of invoking Liferay's JSON web services via
JavaScript, URL, and 
[cURL](http://curl.haxx.se). 
To illustrate the differences between these, the same two use cases (getting a
user and adding a user) are shown in each example. This tutorial also includes
an example of using JavaScript to invoke Liferay's JSON web services from a
portlet. 

## Get User JSON Web Service Invocation via JavaScript [](id=get-user-json-web-service-invocation-via-javascript)

Among the JavaScript objects that Liferay creates for each page is a `Liferay`
object. This object includes a `Service` function that you can use to invoke
Liferay's API. 

Examine the following JSON web service invocation, written in JavaScript: 

	<script type="text/javascript">

      Liferay.Service(
          '/user/get-user-by-email-address',
          {
              companyId: Liferay.ThemeDisplay.getCompanyId(),
              emailAddress: 'test@liferay.com'
          },
          function(obj) {
              console.log(obj);
          }
      );

	</script>

When you run this example, the test@liferay.com user (JSON object) is returned.
You can examine the returned object from your browser console.

The `Liferay.Service(...)` function takes three arguments:

1. A string representing the service to invoke

2. A parameters object

3. A callback function

The callback function takes the result of the service invocation as an argument.

## Add User JSON Web Service Invocation via JavaScript [](id=add-user-json-web-service-invocation-via-javascript)

Here's an example JSON web service invocation, also written in JavaScript, that 
adds a new user. It requires many more parameters than the one for retrieving a 
user!

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

The `serviceContext` object assigns the `test` tag to the newly created user. 
Note that you can use JSON syntax to supply values for objects and arrays. For 
example, to supply a value for the `serviceContext` object, you use curly 
brackets: `{` and `}`. To supply a value for the `assetTagNames` array, you use 
square brackets: `[` and `]`. Thus, the line 
`serviceContext: {assetTagNames: ['test']}` indicates that `serviceContext` is 
an object containing an array named `assetTagNames`, which contains the string
`test`. 

## Invoking JSON Web Services via JavaScript in an Application [](id=invoking-json-web-services-via-javascript-in-an-application)

You can adapt the example from the previous section for use in a Liferay app. 
For example, the JSP page below creates a form that lets the user specify a 
first name, middle name, last name, screen name, and email address. When the 
user clicks the *Add User* button, the app uses these values to create a new 
user. 

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

## Get User JSON Web Service Invocation via URL [](id=get-user-json-web-service-invocation-via-url)

Here's a simple JSON web service invocation via URL that returns the user with
the specified email address:

    http://localhost:8080/api/jsonws/user/get-user-by-email-address/company-id/20154/email-address/test%40liferay.com?p_auth=[value]

This web service invocation returns the test@liferay.com user. After invoking a 
service via Liferay's JSONWS API page, the URL provided when you click on the 
*URL Example* tab omits the `p_auth` URL query parameter. It's assumed that
you'll add this parameter yourself. Remember that you must be logged in as a 
user with the required permission to invoke a web service. To find the `p_auth` 
token that corresponds to your session, see the 
[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/7-1/invoking-json-web-services) 
tutorial. 

As explained in that tutorial, you can supply parameters as either URL path
parameters or URL query parameters. In the preceding example, the company ID
and email address are supplied as URL path parameters. Here's an equivalent
example using URL query parameters:

    http://localhost:8080/api/jsonws/user/get-user-by-email-address?companyId=20154&emailAddress=test@liferay.com&p_auth=[value]

Next, you'll consider an example that requires many more parameters!

## Add User JSON Web Service Invocation via URL [](id=add-user-json-web-service-invocation-via-url)

Here's an example JSON web service invocation via URL that adds a new user with
the specified attributes:

    http://localhost:8080/api/jsonws/user/add-user/company-id/20154/auto-password/false/password1/test/password2/test/auto-screen-name/false/screen-name/joe.bloggs/email-address/joe.bloggs%40liferay.com/facebook-id/0/-open-id/locale/en_US/first-name/Joe/middle-name/T/last-name/Bloggs/prefix-id/0/suffix-id/0/male/true/birthday-month/1/birthday-day/1/birthday-year/1970/job-title/Tester/-group-ids/-organization-ids/-role-ids/-user-group-ids/send-email/false?p_auth=[value]

And here's the same example using URL query parameters instead of URL path
parameters:

    http://localhost:8080/api/jsonws/user/add-user?companyId=20154&autoPassword=false&password1=test&password2=test&autoScreenName=false&screenName=joe.bloggs&emailAddress=joe.bloggs@liferay.com&facebookId=0&-openId&locale=en_US&firstName=Joe&middleName=T&lastName=Bloggs&prefixId=0&suffixId=0&male=true&birthdayMonth=1&birthdayDay=1&birthdayYear=1970&jobTitle=Tester&-groupIds&-organizationIds&-roleIds&-userGroupIds&sendEmail=false&p_auth=[value]

## Get User JSON Web Service Invocation via cURL [](id=get-user-json-web-service-invocation-via-curl)

Here's an example JSON web service invocation via the cURL tool that returns the
user with the specified email address:

    curl http://localhost:8080/api/jsonws/user/get-user-by-email-address \
      -u test@liferay.com:test \
      -d companyId=20154 \
      -d emailAddress='test@liferay.com'

Note that cURL is a command line tool. You can execute this command from a 
terminal window. 

## Add User JSON Web Service Invocation via cURL [](id=add-user-json-web-service-invocation-via-curl)

Here's an example JSON web service invocation via the cURL tool that adds the
user with the specified attributes:

    curl http://localhost:8080/api/jsonws/user/add-user \
      -u test@liferay.com:test \
      -d companyId=20154 \
      -d autoPassword=false \
      -d password1='test' \
      -d password2='test' \
      -d autoScreenName=false \
      -d screenName='joe.bloggs' \
      -d emailAddress='joe.bloggs@liferay.com' \
      -d facebookId=0 \
      -d openId='0' \
      -d locale=en_US \
      -d firstName='Joe' \
      -d middleName='T' \
      -d lastName='Bloggs' \
      -d prefixId=0 \
      -d suffixId=0 \
      -d male=true \
      -d birthdayMonth=1 \
      -d birthdayDay=1 \
      -d birthdayYear=1970 \
      -d jobTitle='Tester' \
      -d groupIds= \
      -d organizationIds= \
      -d roleIds= \
      -d userGroupIds= \
      -d sendEmail=false

Great! Now you've seen how to invoke Liferay's JSON web services from 
JavaScript, URL, and cURL. 

## Related Topics [](id=related-topics)

[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/7-1/invoking-json-web-services)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/7-1/json-web-services-invoker)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-1/invoking-remote-services)
