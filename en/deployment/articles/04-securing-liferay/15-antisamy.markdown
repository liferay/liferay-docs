---
header-id: antisamy
---

# AntiSamy

<aside class="alert alert-info">
   <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/securing-liferay/using-antisamy.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

[TOC levels=1-4]

@product@ includes an [AntiSamy](https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project) 
module that protects against user-entered malicious code. If your site allows
users to post content, such as in message boards, blogs, or other applications,
they could include malicious code either intentionally or unintentionally. The
AntiSamy module filters HTML/CSS fragments and removes suspect JavaScript code
from them. 

The module leverages the powerful
[OWASP AntiSamy library](https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project)
to enforce a content policy that's been effective for the auction site
eBay. The AntiSamy module adds an OWASP AntiSamy implementation to your
portal's list of existing sanitizer implementations. @product@ uses the
AntiSamy sanitizer and any existing configured sanitizers to scrub user input
to blogs entries, calendar events, message boards posts, wiki pages, and web
content articles.

AntiSamy is enabled by default. 

![Figure 1: @product@'s AntiSamy configuration options allow you to specify both a blacklist and a whitelist.](../../images/antisamy.png)

## Configuring AntiSamy

AntiSamy uses both a blacklist and a whitelist, so you can define subsets of
entities that should be sanitized or not sanitized. The whitelist prevents
content of that type from being filtered, while the blacklist filters content of
that type. 

By default, everything is sanitized except for `JournalArticle`,
`BlogsEntry`, and `FragmentEntry`. The assumption is that users posting these
kinds of content are trusted, while users posting message boards or wiki
articles may not be trusted. If this is not the configuration you want, you can
change it: 

1.  Navigate to *Control Panel* &rarr; *System Settings* &rarr; *Security Tools*
    &rarr; *AntiSamy Sanitizer*.

2.  Enter a package path you want to sanitize into the *Blacklist* field. 

3.  Use the plus (+) button to add further Blacklist fields if you need them. 

4.  Use the plus (+) button to add further Whitelist fields if you need them. 

5.  Enter a package path you don't want sanitized into a *Whitelist* field. 

6.  If you want to remove a package path from the configuration, click the trash
    can icon. 

7.  When finished, click *Save*. 

## Using Wildcards

You can use wildcards in the configuration. For example, if you only want to
sanitize message board posts and nothing else, you can

1. Configure the whitelist to `*`

2. Configure the blacklist to `com.liferay.message.boards.*`

The whitelist and the blacklist work together. Without the blacklist,
the above configuration's whitelist must include every content type except
`com.liferay.message.boards`, which would be a daunting task to configure.

Use AntiSamy to ensure user-generated content stays safe for other users to
view. 
