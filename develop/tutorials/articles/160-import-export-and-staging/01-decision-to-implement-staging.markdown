# Decision to Implement Staging

@product@ comes pre-packaged with Staging, an advanced publication tool helping
content creators and site administrators prepare their site before releasing it
to the public. Most of @product@'s default applications (e.g., Web Content and
Bookmarks) support Staging. Implementing Staging in your own application to
leverage @product@'s Staging features can be beneficial, but how do you know if
it's the right move?

Not every application needs to support Staging and Export/Import. The most
important question to consider during the decision process is

*What part of your application are you primarily focused on using Staging for?*

When Staging is enabled in @product@, all pages and applications are staged
automatically. @product@'s architecture separates the application and its
configuration from the actual content, meaning that content can exist without
any application to display it and vice versa. Although Staging and the
Export/Import framework supports all applications and their configuration by
default, not all applications' content is supported by Staging.

Therefore, if you want to track your application's content, you should implement
Staging in your application. Here's a few other scenarios where you should
implement Staging in your application:

- You're using remote staging. When publishing to a remote live site, your
  content must be transferred to a different @product@ instance. Therefore,
  Staging must be able to recognize the content to facilitate the transfer.
- You want a space where you can freely edit and test your content before
  publishing it to a live audience.

If none of these options are beneficial for you, implementing Staging in your
application is unnecessary.

When content supports Staging and Staging is enabled, it is created in a Staging
group and is only published to a live site when that group is published. When
content is **not** supported by Staging, it is never added to a Staging group
and is not reviewable during the Staging publication process; it's added and
removed from the live site only.

From a technical standpoint, publishing an entity or content follows the process
below:

1. The entity's possible references are discovered and processed.
2. Some of the entity's fields are processed.
3. The entity is serialized into a LAR file.
4. The LAR is transferred to the live site (local or remote live).
5. After deserialization, the entity's fields are processed.
6. The entity is added to the database.

The possibility of being able to turn on and off staging for specific content
types is powerful, but you need to think about some implications. If you are not
interested fine graining these options

<!-- Need further info for paragraph above. -Cody -->

Awesome! You should now have a good idea about whether you should implement
Staging for your application.