# Configuring Message Boards [](id=configuring-message-boards)

Before using a message board, you should configure it to your needs. First, open 
the Message Boards app in your scope's Site Administration menu, as described in 
[the article on creating message boards](https://www.liferay.com/). 
To open the message board's configuration screen, click the message board's 
*Options* menu 
(![Options](../../../../images/icon-options.png)) and select *Configuration*. 
From the Configuration tabs, you can configure the message board for the 
behavior you want. The below sections cover these tabs.

## General Setup [](id=general-setup)

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

## Email Setup [](id=email-setup)

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

## Thread Priorities [](id=thread-priorities)

The *Thread Priorities* tab lets you define custom priorities for message 
threads. This lets privileged roles tag a thread with a certain priority, which 
highlights it for users. Three priorities are defined by default: 

-   Urgent
-   Sticky
-   Announcement

To define a thread priority, enter its name, a URL to its image icon, and a 
priority number. Threads with a higher priority are posted above threads with a 
lower priority. 

**Thread Icons**

  &nbsp;**Icon** | **Definition** | 
:-------------------: | :---------------- |
   ![Urgent](../../../../images/icon-message-boards-urgent.png) |  Urgent |
   ![Announcement](../../../../images/icon-message-boards-announcement.png) |  Announcement |
   ![Sticky](../../../../images/icon-message-boards-sticky.png) |  Sticky |
   ![Question](../../../../images/icon-message-boards-question.png) |  Question |

The localized language field lets you name the priorities in each locale. You
can select the locale, update the priority names for it, and save your updates. 

## User Ranks [](id=user-ranks)

The User Ranks tab lets you rank users by the number of messages they have 
posted. Default ranks from 0 to 1000 are provided for you, but you can set 
custom ranks here as well. 

You can also choose message boards labels for users to display in their 
profiles. For example, you can use the message boards label *Moderator* for 
anyone who is a part of any of the Message Boards Administrator groups: the site 
role, the organization, the organization role, the regular role, or the user 
group: 

    Moderator=organization:Message Boards Administrator

    Moderator=organization-role:Message Boards Administrator

    Moderator=regular-role:Message Boards Administrator
    
    Moderator=site-role:Message Boards Administrator

    Moderator=user-group:Message Boards Administrator

As with thread priority names, the *Localized Language* field lets you localize
rank names. 

## RSS [](id=rss)

Message board threads can be published as RSS feeds. The RSS tab lets you 
enable/disable RSS subscriptions and define how the feeds are generated: 

-   **Maximum Items to Display:** The number of items to display in the feed. 
-   **Display Style:** The feed's appearance. You can publish the full content,
    an abstract, or just the thread title. 
-   **Format:** The feed's format: RSS 1.0, RSS 2.0, or Atom 1.0. 

Once you've finished configuring your message board, make sure to *Save* your
changes. 
