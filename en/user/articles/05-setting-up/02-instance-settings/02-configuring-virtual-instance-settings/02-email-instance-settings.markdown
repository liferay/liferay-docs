---
header-id: email-instance-settings
---

# Email Instance Settings

[TOC levels=1-4]

The Email configuration is your one-stop-shop for all your email notification 
needs. To access the Email configuration settings for your instance, open the 
Control Panel and navigate to *Configuration* &rarr; *Instance Settings* and 
select the *Email* category under the PLATFORM section. The Email configuration 
contains six entries:

- Account Created Notification
- Email Sender
- Email Verification Notification
- Mail Host Names
- Password Changed Notification
- Password Reset Notification

Each configuration entry is described in the corresponding section below. 

## Account Created Notification

The Account Created Notification entry defines the email templates, with and 
without the new User's password included in the body of the text, for the 
message sent to notify Users when they create a new account. You can specify 
whether this email is sent by checking/unchecking the *Enabled* checkbox at the 
top. Use the template variables listed at the bottom of the configuration under 
the "Definition of Terms" heading to help build your email template. 

## Email Sender

The Email Sender entry specifies the virtual instance's administrative Name and 
Address for email notifications, declared as the `[$FROM_NAME$]` and 
`[$FROM_ADDRESS$]` variables respectively in the email templates. By default, 
they are from the [`admin.email.from.name` and `admin.email.from.address`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Admin%20Portlet) [portal properties](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)).
This name and email address appear in the *From* field in all email messages sent by the virtual instance. 

![Figure 1: Customize the email template for the email messages sent to new Users.](../../../../images/instance-settings-account-created.png)

## Email Verification Notification

The Email Verification Notification entry defines the email template for the 
message sent to Users when asked to verify their Email Address. Use the template 
variables listed at the bottom of the configuration under the 
"Definition of Terms" heading to help build your email template. 

## Mail Host Names

The Mail Host Names entry specifies which mail host names are owned by your 
organization for the virtual instance. Enter one mail host name per line, 
besides the one specified in the [General tab](). 

## Password Changed Notification

The Password Changed Notification entry defines the email template for the 
message sent to notify Users when their password has changed. Use the template 
variables listed at the bottom of the configuration under the 
"Definition of Terms" heading to help build your email template. 

## Password Reset Notification

The Password Reset Notification entry defines the email template for the message 
sent to notify Users when a request has been made to reset their password. Use 
the template variables listed at the bottom of the configuration under the 
"Definition of Terms" heading to help build your email template. 

![Figure 2: There are some handy variables available for use in email templates.](../../../../images/instance-settings-definition-of-terms.png)
