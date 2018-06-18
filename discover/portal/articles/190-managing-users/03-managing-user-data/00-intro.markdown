# Managing User Data [](id=managing-user-data)

Enforcement of the General Data Protection Regulation (GDPR) began May 25,
2018. Companies processing the personal data of EU residents must adopt
appropriate measures to protect User data. A wealth of available resources
discuss the implications of the regulation and the steps companies must take to
ensure compliance.

To assist with GPDR compliance, User Management functionality was added to make
these tasks easier:

[Erase and/or anonymize data associated with a User](/discover/deployment/-/knowledge_base/7-1/sanitizing-user-data)
: Administrative Users go through a step by step process, choosing to erase
certain pieces of data and anonymize others.

[Export a User's personal data](/discover/deployment/-/knowledge_base/7-1/exporting-user-data)
: Export ZIP files containing the data associated with a User.

These features can be seen as a tool that gets your enterprise closer to meeting
two of GDPR's technically challenging requirements, the _right to data
portability_ and the _right to be forgotten_. 

+$$$

**Note:** It is Liferay's sincerest hope that through the User Management
functionality of @product@, companies processing the personal data of their
website's users can satisfy the requirements of GDPR. However, the tools
discussed here and anywhere else in the documentation, including those directly
aimed at addressing GDPR requirements (as in this article) do not guarantee
compliance with the legal requirements of GDPR. Each company or individual whose
website processes user personal data and is under the jurisdiction of GDPR must
carefully determine the precise steps necessary to ensure they are fully compliant
with GDPR.

$$$

## Anonymizing Data [](id=anonymizing-data)

Anonymizing User Associated Data (UAD) is all about identity swapping. A User
being anonymized must have their identifiers (for example, User ID and their
User Name) removed from content they've interacted with. However, portal content
usually requires this information for its applications to work properly.
Therefore, the User's identifiers must be replaced by something, or someone.
Meet the new User, *Anonymous Anonymous*. This deactivated User is dedicated to
be the User whose identifiers are assigned to anonymized content. 

![Figure 1: Anonymized content is presented with the User Anonymous Anonymous's identifying information.](../../../images/users-anonymized-content.png)

Here at Liferay, we've grown fond of *Anonymous Anonymous*. If you'd rather
start from scratch or assign an existing User to be the Anonymous User, get rid
of *Anonymous Anonymous* and configure your own Anonymous User.

The anonymous user is programmatically created for each instance the first time
an Administrator clicks *Delete Personal Data* from a User's Actions menu
(![Actions](../../../images/icon-actions.png)). If you haven't yet done that, no
Anonymous User exists.

The easiest way to set up a new User as the Anonymous User is to simply edit an
existing Anonymous User configuration, passing in the new Anonymous User's User
ID. 

To edit an existing configuration:

1.  Go to Control Panel &rarr; Configuration &rarr; System Settings &rarr; Users
    &rarr; Anonymous Users.

2.  Edit the existing configuration, providing a different User ID.

    Get the User ID from Control Panel &rarr; Users &rarr; Users and
    Organizations. Click on the User and find the User ID in the Information
    screen of the Edit User application.

3.  Click *Update*.

To create a new Anonymous User:

1.  [Create a User](/discover/portal/-/knowledge_base/7-1/adding-editing-and-deleting-users#adding-users)
    intended to use for data anonymization. Alternatively, you can use an
    existing User.

2.  If there's already an Anonymous User configured for the instance, there are
    two ways to remove it: 
    
    Delete the User entirely. Deleting the User simultaneously deletes its
    configuration as the Anonymous User. Go to Control Panel &rarr; Users &rarr;
    Users and Organizations. If it's an active User, first deactivate, then
    delete the User. The default Anonymous Anonymous User is deactivated by
    default. Simply delete the User in this case.Click the Actions button
    (![Actions](../../../images/icon-actions.png)) and select *Delete*.

    If you don't want to delete the User, just delete the User's configuration
    as the Anonymous User. Go to Control Panel &rarr; Configuration &rarr;
    System Settings &rarr; Users &rarr; Anonymous Users.

3.  Add the configuration to make the User a Click the *Add* button.

5.  Fill out the two fields, Company ID and User ID. 

    Get the Company ID from Control Panel &rarr; Configuration &rarr; Virtual
    Instances. The Instance ID and Company ID are the same.

    Get the User ID from Control Panel &rarr; Users &rarr; Users and
    Organizations. Click on the User and find the User ID in the Information
    screen of the Edit User application.

There can only be one Anonymous User configured for each instance.

![Figure 2: Assign your own Anonymous User from Control Panel &rarr; Configuration &rarr; System Settings &rarr; Users &rarr; Anonymous User.](../../../images/users-anonymous-config.png)

## Manual Anonymization [](id=manual-anonymization)

Anonymizing just the User's identification fields might not be enough. There can
be user-entered personal data within the content of an application. You must
manually edit such content to remove identifying details. If a User named
Ziltoid Omniscient complains about The Lunar Resort's coffee in a Message Boards
Message and in it signs the post with _Supreme Leader of Ziltoidea 9_,
anonymizing this post would remove the User's name (Ziltoid Omnisicent) and
replace it with Anonymous Anonymous, but searching the internet for _Ziltoidea
9_ will quickly reveal that the post was written by
[Ziltoid the Omniscient](https://en.wikipedia.org/wiki/Ziltoid_the_Omniscient).

![Figure 3: Even though this Message Boards Message (a comment on a blog post in this case) is anonymized, it should be edited to remove User Associated Data from the content of the message.](../../../images/users-partial-anonymization.png)

