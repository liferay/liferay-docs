# AntiSamy [](id=antisamy)

@product@ includes an AntiSamy module that protects against malicious code that
users might create. When creating content, users can include malicious code
either intentionally or unintentionally. The AntiSamy module filters on
specific HTML/CSS fragments and removes suspect JavaScript code from them. The
module leverages the powerful
[OWASP AntiSamy library](https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project)
to enforce a content policy that's been effective for the online auction site
eBay. The AntiSamy module adds an OWASP AntiSamy implementation to your
portal's list of existing sanitizer implementations. @product@ uses the
AntiSamy sanitizer and any existing configured sanitizers to scrub user input
to blogs entries, calendar events, message boards posts, wiki pages, and web
content articles.

At the time of this writing, @product@'s AntiSamy module uses OWASP AntiSamy
1.5.3. It's enabled by default. To configure the AntiSamy module, navigate to
@product@'s *Control Panel*, go to *System Settings*, open the *Foundation*
category and click on *AntiSamy Sanitizer*.

![Figure 1: @product@'s AntiSamy configuration options allow you to specify both a blacklist and a whitelist.](../../../images/antisamy.png)

Using both a blacklist and a whitelist allows you to easily define subsets of
entities that should be sanitized or not sanitized. By default, everything is
sanitized except for `JournalArticle`. `JournalArticle` is the entity name for
web content articles in @product@. But suppose you don't want to sanitize @product@
entities except for message board posts. This might be reasonable if your
portal only allows message boards posts to be created or updated by untrusted
users.

To achieve this, you can

1. Configure the whitelist to `*`
2. Configure the blacklist to `com.liferay.message.boards.*`

Remember to use both the whitelist and the blacklist. Without the blacklist,
you would need to configure the whitelist to include everything except
`com.liferay.message.boards`: `com.liferay.journal.*`, `com.liferay.blogs.*`,
etc.
