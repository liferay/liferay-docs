# Implementing Asset Renderers [](id=implementing-asset-renderers)

You need to create asset renderer classes for your guestbook and guestbook entry
entities and register them with Liferay. Without these classes, Liferay's Asset
Renderer portlet is unable to display your entities. Liferay assets contain such
fields as `title`, `description`, and `summary`. Liferay's Asset Publisher
portlet uses these fields to display assets, regardless of the actual type of
entity represented by the asset. These fields are not columns of the database
tables of the entities themselves (e.g., they are not columns in the
`JournalArticle` table in the case of web content articles or of the
`GB_Guestbook` table in the case of your guestbook entities). Rather, they are
are stored in the `AssetEntry` table.
