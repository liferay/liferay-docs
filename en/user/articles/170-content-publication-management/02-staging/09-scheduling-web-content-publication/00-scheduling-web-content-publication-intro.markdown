---
header-id: scheduling-web-content-publication
---

# Scheduling Web Content Publication

[TOC levels=1-4]

Liferay's Web Content framework lets you define when your content goes live. You
can determine when the content is displayed, expired, and/or reviewed. This is
an excellent way to keep your Site current and free from outdated (and perhaps
incorrect) information.

The scheduler is built right into the Properties menu your users
access when adding web content. To access this menu, click the *Options* gear
(![Options](../../../../images/icon-gear.png)) and open the Schedule dropdown
menu.

![Figure 1: The web content scheduler can be easily accessed from the right panel of the page.](../../../../images/web-content-schedule.png)

The scheduler offers several configurable options:

**Display Date:** Sets (within a minute) when content will be displayed.

**Expiration Date:** Sets a date to expire the content. The default is one year.

**Never Expire:** Sets your content to never expire.

**Review Date:** Sets a content review date.

**Never Review:** Sets the content to never be reviewed.

As an example, you'll step through the process of scheduling a web content
article.

1.  Navigate to the Product Menu &rarr; *Content & Data* &rarr; *Web Content*.

2.  Create a new web content article by selecting the *Add Web Content* button
    (![Add](../../../../images/icon-add.png)) &rarr; *Basic Web Content*.

3.  Add content for your web content article.

4.  Select the *Schedule* tab from the web content's Properties menu. Configure
    the publication schedule.

5.  Click *Publish*. Your web content article is now created and abides by
    the scheduling parameters you've set.
    
When you set a Display Date for an existing article it does not affect previous 
versions of the article. If a previous version is published, it remains the 
same until the new version is scheduled to display. However, the expiration 
date affects all versions of the article. Once an article has expired, no 
version of that article appears.

| **Tip:** If you want only the latest version of articles to expire, and not
| every past version, go to *Control Panel* &rarr; *Configuration* &rarr;
| *System Settings* &rarr; *Web Content* &rarr; *Virtual Instance Scope* &rarr;
| *Web Content* and uncheck *Expire All Article Versions Enabled*. This makes
| the previously approved version of an article appear if the latest version
| expires.

The scheduling feature gives you great control in managing when, and for how
long, your web content is displayed on your Site. Additionally, you can
determine when your content should be reviewed for accuracy and/or relevance.
This makes it possible to manage your growing inventory of content.
