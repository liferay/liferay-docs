# Decision to Implement Staging [](id=decision-to-implement-staging)

Staging is an advanced publication tool that lets you create or modify your site
before releasing it to the public. Most of @product@'s included applications
(e.g., Web Content, Bookmarks, etc.) support Staging. Implementing Staging in
your own application can be beneficial, but how do you know if it's the right
move?

Not every application needs to support Staging and Export/Import. The most
important question to consider during the decision process is

*What part of your application are you primarily focused on using Staging for?*

When Staging is enabled, all pages and applications are staged automatically.
@product@'s architecture separates the application and its configuration from
the actual content, meaning that content can exist without any application to
display it and vice versa. Although Staging  supports all applications and their
configurations by default, not all applications' content is supported by
Staging.

Implementing Staging for your application means you're defining the logic for
how the Staging framework should process, serialize, and de-serialize your app's
content, and how to insert it into a database.

Therefore, if you want to track your application's content, you should implement
Staging in your application. Here are a few other scenarios where you should
implement Staging in your application:

- You're using remote staging. When publishing to a remote live site, your
  content must be transferred to a different @product@ installation. Therefore,
  Staging must be able to recognize the content to facilitate the transfer.
- You want a space where you can freely edit and test your content before
  publishing it to a live audience.
- Your content is being referenced from another content type that supports
  Staging.
- You want to process your portlet's preferences during publication (i.e., you
  might want to publish some content with it or complete extra steps).
- You want to process the content during publication (e.g., writing validation
  for your content during the import process).

If none of these options are beneficial for you, implementing Staging in your
application is unnecessary.

When content supports Staging and Staging is enabled, it is created in a Staging
group and is only published to a live site when that site is published. When
content is **not** supported by Staging, it is never added to a Staging group
and is not reviewable during the Staging publication process; it's added and
removed from the live site only.

From a technical standpoint, publishing an entity or content follows the process
below:

1.  The entity's possible references are discovered and processed.
2.  The entity's fields are processed.
3.  The entity is serialized into a LAR file.
4.  The LAR is transferred to the live site (local or remote live).
5.  After de-serialization, the entity's fields are processed.
6.  The entity is added to the database.

Awesome! You should now have a good idea about whether you should implement
Staging for your application.
