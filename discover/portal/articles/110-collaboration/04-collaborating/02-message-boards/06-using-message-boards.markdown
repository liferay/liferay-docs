# Using the Message Boards

You can add the Message Boards app to a page from the *Add* 
(![Add](../../../images/icon-control-menu-add.png)) menu's *Collaboration*
section. Users will immediately recognize that the interface is similar to other 
message boards they've seen and used before. In any case, it can't hurt to 
explore how to use @product@'s Message Boards and discover all of its features. 

![Figure 1: The Message Boards application lets you explore its categories, interact with message threads, and post new messages.](../../../../images/message-boards-category-threads.png)

Threads can be viewed many ways. At the top of the app is a set of tabs: 

-   **Categories:** View the message board's categories.
-   **Recent Posts:** View all posts from all categories, sorted by date.
-   **My Subscriptions:** Lets users view and manage their thread subscriptions. 
-   **Statistics:** Shows the number of categories, posts, participants, and a 
    list of the top contributors. 

*Message Boards Home*, *Recent posts*, *My Posts*, *My Subscriptions*, and
for administrative users, *Statistics* and *Banned Users*. The Recent Posts tab
shows all posts from all categories by date, so you can keep up on all the most
recent discussions in the message boards. The My Posts tab shows all of the
user's posts. It provides a convenient interface for revisiting previous
conversations in order to retrieve pertinent information. The My Subscriptions
tab allows the user to manage thread subscriptions. If you lose interest in a
particular topic, you may want to visit this tab and unsubscribe from a category
or thread. 

For administrators, the Statistics tab shows the number of categories, the
number of posts, and the number of participants in your message boards. It also
has a list of your message board's top contributors. The Banned Users tab shows
all of the users who have been banned from posting on the message boards. 

### Posting New Threads [](id=posting-new-threads)

To post a new thread click the *Post New Thread* button in the app or
![Add](../../../images/icon-add.png) in Message Boards in Site Administration)
and select *Thread*. A message editing form appears. The body field on this form
is different from that of the other @product@ applications. The reason for
this is to support *BBCode*, which is a standard form of markup used in many
message board products. Before BBCode was invented, many message board products
would allow users to enter HTML to format their messages. This, however, enabled
attackers to insert malicious code into the message board. BBCode was invented
to provide users a way of formatting their messages without allowing them to
enter HTML. Similarly, Liferay supports BBCode in the message boards because the
other editors--which are used for the Content Management System, Blogs, and
other applications--produce HTML. This is appropriate for those other apps, as
they are only used by privileged users, but it is not appropriate for the
message boards. Besides this, many users of message boards are familiar with
BBCode and are used to it, and the editor that is provided for Liferay's Message
Boards application makes it very easy to use. 

Message Boards uses a rich-text editor. It supports bold, italicized,
underlined, and crossed-out text, links, images, colors, lists, tables,
alignments, quotation blocks, code blocks, different fonts and font sizes, and
more. The editor has keyboard shortcuts Ctrl+b for bold, Ctrl+i for italics, and
Ctrl+u for underline. You can [mention](/discover/portal/-/knowledge_base/7-0/mentioning-users)
another other user by entering the "@" character followed by the user's user
name. There are even a bunch of smiley faces that you can use. 

After entering the message's *Subject*, enter your message *Body*. Below the
Body are sections that let you attach files (e.g., images) to your message,
categorize it, tag it, and relate it to other assets. expand the *More Settings*
section in the list of sections below the content editor. The user can specify
thread options that the message board permits. The options include marking the
message as a question, posting anonymously, subscribing to the message thread,
assigning the message a pre-defined priority, and allowing pingbacks to the
message. 

The user can also specify permissions. The message can be set to be viewable by
a particular role. And the following additional permissions can be set on the
message: 

**Update:** Edit the message. 

**Subscribe:** Receive notifications of updates to the message and its thread. 

**Permissions:** Grant/revoke permissions for the message. 

**Delete:** Remove the message. 

**View:** View the message. 

A permissions icon appears above each posted message. You can revisit the above
permissions by clicking the permissions icon. 

When you're done editing and configuring your message, you can preview it, and
save it as a draft or publish it. Once it's published, it's listed along with
the other threads in the category. 

Message Boards is also highly integrated with @product@'s user management
features. When you click on a thread or thread reply's subject link, the
author's profile picture, name, rank, number of posts, the date the user joined
the site, and a link to the user's recent posts. 

### Participating in Message Board Threads [](id=participating-in-message-board-threads)

To find message board threads that interest you, browse a message board's
categories or Recent Posts. You can view a category's thread listing by clicking
on the category's name. From within a category screen, you can subscribe to an
RSS feed and/or emails that inform you about thread activities in that category.
Similar to a category's screen, Recent Posts lists threads too, except they're the
latest threads across all the categories. 

To view a message thread, click on it. Messages are shown in a threaded view so
that replies are aligned under their proper parent thread. This makes it easy to
follow along with conversations. Thread replies are indented under their parent
thread. 

![Figure 5: A thread's view displays author information and thread content, for the thread and all replies to the thread.](../../../images/message-boards-participate-in-threads.png)

Subscribing to a thread causes @product@ to send the user an email whenever
a new message is posted to the thread. If you have enabled the mailing list
feature for the category in which the thread resides, users can simply reply to
these messages in order to post back to the thread, without having to visit your
site.

Most threads get more interesting as users reply to them. You can start creating
a response by clicking one of the following buttons: *Reply*, *Reply with
Quote*, or *Quick Reply*. The *Reply* screen includes the same rich-text editor
and options that you use for posting new messages. If you want to include text
from the original message, click *Reply with Quote*. If you only need the
rich-text editor to generate your reponse and don't need any other message
options, click *Quick Reply*. The editor lets you insert emoticons, preformatted
text, and more. 

In addition to replying to a message, you can rate it or flag it as
objectionable. A message board moderator can evaluate flagged messages and
decide how to handle the messages and their authors. This provides an
appropriate segue into the next topic: managing message boards. 