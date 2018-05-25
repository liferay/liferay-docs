# Configuring Message Boards

Before using a message board, you should configure it to your needs. First, open 
the Message Boards app in your scope's Site Administration menu, as described in 
[the article on creating message boards](https://www.liferay.com/). 
To open the message board's configuration screen, click the message board's 
*Options* menu 
(![Options](../../../../images/icon-options.png)) and select *Configuration*. 
From the Configuration tabs, you can configure the message board for the 
behavior you want. 

## General Setup

The *General* tab lets you configure the following:

-   **Allow Anonymous Posting:** Whether to let users post anonymously. Use this 
    with caution---anonymous users tend to be mean. 

-   **Subscribe by Default:** Whether users are subscribed by default to threads 
    they participate in. 

-   **Message Format:** The markup language of users' message board posts. You 
    can choose BBCode or HTML. When creating posts, the type of WYSIWYG editor
    presented to users depends on which option is enabled. Both editors have a
    *Source* button that lets users view a message's underlying BBCode or HTML. 
    Users can compose messages using either the WYSIWYG or Source view and can
    switch between views during message composition by clicking the *Source* 
    button. 

-   **Enable Report Inappropriate Content:** Whether users can report content 
    that other users post as inappropriate. This sends a message to 
    administrators so they can take action. 

-   **Enable Ratings:** Whether users can score posts. The scores are used by 
    the social activity system to rank site members by how helpful their 
    contributions are. You can read more about social activity in the
    [Measuring Social Activity](/discover/portal/-/knowledge_base/7-1/measuring-social-activity)
    article. 

-   **Thread as Question by Default:** Whether to automatically check the 
    *Mark as question* box in the new thread window. Threads marked as questions
    display *waiting for an answer*. Subsequent replies to the original message 
    can be marked as an answer. 

-   **Show Recent Posts from Last:** The amount of time a post is displayed on 
    the message board's *Recent Posts* tab. You can choose the following: 

    -   24 hours
    -   7 days (default) 
    -   30 days
    -   365 days 

    After the allotted time has passed, the post is removed from *Recent Posts*. 
    However, the post itself isn't deleted; it remains accessible everywhere 
    else in the message board. 

## Email Setup 

Use these tabs to configure how the Message Boards app handles email 
notifications:

-   **Email From**: The name and email address that sends email notifications. 
    The default administrator account's name and email address (e.g., 
    *Test Test* and *test@liferay.com*) are used by default. These were set in 
    the Basic Configuration Wizard when installing @product@. Make sure to 
    update this email address to a valid one that can be dedicated to 
    notifications. 

    Select *HTML Format* to support HTML in these emails. Lastly, expand the 
    *Definition of Terms* section to see variables available to use in these 
    emails. 

-   **Message Added Email:** Customize the email message that users receive when 
    a message is added to a topic they subscribe to. 

    -   **Enabled:** Whether automatic emails are sent to subscribed users.
    -   **Subject:** Choose a prefix to prepend to the email's subject line. 
        This lets users set up message filters in their email clients for these 
        notifications. 
    -   **Body:** The content that should appear in the message body. 
    -   **Definition of Terms:** The variables which can use in the fields above 
        to customize the message.

-   **Message Updated Email:** This tab is identical to the Message Added Email 
    tab, except it defines the email that users receive when a post is updated. 
