# Configuring Mail [](id=configuring-mail)

@product@ uses a mail server and SMTP to email notifications. @product@'s
built-in mail session is the easiest way to configure mail and it's recommended.
You can
[configure the built-in mail session](/deployment/deployment/-/knowledge_base/7-2/configuring-a-mail-session#products-built-in-mail-session)
before or after deploying @product@. Alternatively, you can
[configure @product@ to use a mail session on the application server](/deployment/deployment/-/knowledge_base/7-2/configuring-a-mail-session#application-server-mail-session). 

Creating a mail session in @product@ or on the application server requires this information: 

-   Incoming POP Server and port
-   POP User Name
-   POP Password
-   Outgoing SMTP Server and port
-   SMTP User Name
-   SMTP Password
-   All JavaMail properties you want to use

Here are the two ways to configure your mail session:

-   [@product@'s built-in mail session (recommended)](#products-built-in-mail-session)

-   [A mail session on your application server](#application-server-mail-session)

Built-in mail session setup is recommended and easiest. 

### @product@'s Built-in Mail Session [](id=products-built-in-mail-session)

The built-in mail session setup can be done using either of these methods:

-   [Control Panel](#built-in-mail-session-in-the-control-panel)

-   [Portal properties](#built-in-mail-session-portal-properties)

#### Built-in Mail Session in the Control Panel [](id=built-in-mail-session-in-the-control-panel)

After deploying @product@, you can configure the mail session from the Control Panel. 

1.  Sign in as the administrative user (the user you specified on the
    [Basic Configuration page](/deployment/deployment/-/knowledge_base/7-2/installing-liferay#basic-configuration)). 

2.  Navigate to *Control Panel &rarr; Configuration &rarr; Server Administration
    &rarr; Mail*.

3.  Fill out the form. You're asked for the following information: 

    **Incoming POP Server:** The hostname for a server running the Post Office
    Protocol. @product@ checks this mailbox for incoming messages, such as
    message board replies. 

    **Incoming Port:** The port on which the POP server is listening. 

    **Use a Secure Network Connection:** Use an encrypted connection when 
    connecting to the POP server. 

    **User Name:** The user ID @product@ should use to log into the POP server. 

    **Password:** The password @product@ should use to log into the POP server. 

    **Outgoing SMTP Server:** The hostname for a server running the Simple Mail
    Transfer Protocol. @product@ uses this server to send emails, such as 
    password change emails and other notifications. 

    **Outgoing Port:** The port on which the SMTP server is listening. 

    **Use a Secure Network Connection:** Use an encrypted connection when 
    connecting to the SMTP server. 

    **User Name:** The user ID @product@ should use to log into the SMTP server.

    **Password:** The password @product@ should use to log into the SMTP server.

    **Manually specify additional JavaMail properties to override the above
    configuration:** If there are additional properties you need to specify, 
    supply them here. 

4.  Click *Save*. 

@product@ connects to the mail session immediately.  

#### Built-in Mail Session Portal Properties [](id=built-in-mail-session-portal-properties)

If you prefer specifying your mail session offline or before deploying @product@, use portal properties. 

1.  Create a
    [`portal-ext.properties` file](/deployment/reference/-/knowledge_base/7-2/portal-properties),
    if you haven't already created one. 

2.  Copy these default property settings into your `portal-ext.properties` file:

        mail.session.mail=false
        mail.session.mail.pop3.host=localhost
        mail.session.mail.pop3.password=
        mail.session.mail.pop3.port=110
        mail.session.mail.pop3.user=
        mail.session.mail.smtp.auth=false
        mail.session.mail.smtp.host=localhost
        mail.session.mail.smtp.password=
        mail.session.mail.smtp.port=25
        mail.session.mail.smtp.user=
        mail.session.mail.store.protocol=pop3
        mail.session.mail.transport.protocol=smtp.

3.  Replace the default mail session values with your own. 

4.  Put the `portal-ext.properties` file into your
    [LIFERAY_HOME](/deployment/reference/-/knowledge_base/7-2/liferay-home),
    once you've established it based on your installation. 

@product@ connects to the mail session on the next startup. 

### Application Server Mail Session [](id=application-server-mail-session)

You can manage a mail session for @product@ on your application server. Here's how:

1.  Create a mail session on your application server, following your application
    server documentation.  

2.  Point @product@ to that mail session using the Control Panel or 
    portal properties. Here are instructions for both:

    -   Configure the JNDI name in the *Mail* page at *Control Panel &rarr; 
        Configuration &rarr; Server Administration &rarr; Mail*. 
    -   Set a `mail.session.jndi.name` portal property in a
        `[LIFERAY_HOME]/portal-ext.properties` file. Here's an example property:

            mail.session.jndi.name=mail/MailSession
