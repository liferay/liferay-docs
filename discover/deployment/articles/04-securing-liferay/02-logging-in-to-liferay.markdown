# Logging into @product@ [](id=logging-into-liferay)

One of the primary functions of a security system is to make pages, content, and
web applications are accessible to only the appropriate users. A student
logging into a university portal should not be able to access the same resources
a professor can. A patient logging into a health care portal should not be able
to access a doctor's resources. Some content (at least a login page) should be
available to everybody, including unauthenticated users (called *guest* users).
<!-- To learn more about how @product@ restricts access to portal resources to
different users, please see the [Roles and Permissions]() documentation. (Not
yet written) -->

## Authentication Types [](id=authentication-types)

There are three authentication types: by email address, screen
name, or user ID. To choose an authentication type, navigate to the Control
Panel, click on *Configuration* &rarr; *Instance Settings* &rarr;
*Authentication* and use the *How do users authenticate?* selector to make
a selection. Alternatively, add the following lines to your
`portal-ext.properties` file, uncomment the appropriate line, comment out the
others, and restart your server.

    company.security.auth.type=emailAddress
    #company.security.auth.type=screenName
    #company.security.auth.type=userId

The default authentication type is by email address, but you can choose screen
names or user IDs instead. Users choose screen names when they create their
accounts or administrators can choose them. User IDs are auto-generated when the
account is created. Regardless of which authentication type is configured, users
must always enter a password. <!-- For information on
adding restrictions on the kinds of passwords that are allowed or required
(e.g., to require a minimum password length or require special characters),
please see the [Password Policies]() documentation. -->

## The Sign In Portlet [](id=the-sign-in-portlet)

The Sign In portlet is how users log in. By default, the Sign In portlet can
create new accounts or request a password reset. The default home page contains
a Sign In portlet. You can access this page at
[http://localhost:8080/web/guest/home](http://localhost:8080/web/guest/home).

![Figure 1: By default, the Sign In portlet allows users to log in, create a new account, or request a password reset.](../../images/sign-in-portlet.png)

If the Sign In portlet doesn't appear on any page, you can still access it here:

- [localhost:8080/c/portal/login](localhost:8080/c/portal/login)

By default, guest users can create accounts by clicking on the *Create Account*
link in the Sign In portlet, completing the form, and submitting it. If a user
already has an account but has forgotten its password, the user can click on the
*Forgot Password* link to request a password reset. Both the *Create Account*
form and the *Forgot Password* form include a CAPTCHA-based text verification
field. Using [CAPTCHA](http://www.captcha.net) prevents bots from submitting
these forms. You can use
[reCAPTCHA](https://www.google.com/recaptcha/intro/index.html) instead of
CAPTCHA. One advantage of reCAPTCHA is that it can allow visually impaired
users to pass the test. To use reCAPTCHA, navigate to the Control Panel, then
click on *Configuration* &rarr; *System Settings* &rarr; *CAPTCHA*.

Some use cases require that all user accounts be created by administrators.
To prevent guest users from creating new user accounts, navigate to the Control
Panel, click on *Configuration* &rarr; *Instance Settings* &rarr;
*Authentication* and uncheck the *Allow strangers to create accounts?* box. You
can also prevent users from requesting forgotten passwords or from requesting
password reset links by unchecking the appropriate boxes. 

![Figure 2: Here's a view of the Sign In portlet with the *Create Account* and *Forgot Password* options removed.](../../images/sign-in-portlet2.png)

Remember that the Sign In portlet is the default way for users to log in, but
it's not the only way. User accounts can be imported from and exported to LDAP
directories. You can use single-sign-on (SSO) solutions or token-based
authentication, which allows remote web applications to authenticate. Please
refer to the other articles in this section for more information. Finally,
remember that  user authentication and remote application authentication
mechanisms are
[extensible](/develop/tutorials/-/knowledge_base/7-1/authentication-pipelines). 

