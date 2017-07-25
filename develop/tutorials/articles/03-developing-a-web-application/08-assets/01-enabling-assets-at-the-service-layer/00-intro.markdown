# Enabling Assets at the Service Layer [](id=enabling-assets-at-the-service-layer)

Every Liferay asset is represented by a row in the `AssetEntry` table of
Liferay's database. Each row in this table has an `entryId` primary key and
`classNameId` and `classPK` foreign keys (among many others). An asset entry
row's `classNameId` specifies the type of asset represented by the asset entry.
E.g., if a row has a `classNameId` corresponding to `JournalArticle`, this means
that the asset entry row represents a web content article. (In Liferay,
`JournalArticle` is the back-end name for web content article.) An asset entry
row's `classPK` represents the primary key of the entity represented by the
asset. E.g., the `classPK` field of an asset entry row corresponding to a web
content article contains the primary key of the web content article in the
`JournalArticle` table of Liferay's database.

Your first step in asset-enabling your guestbook and guestbook entry entities is
to make Liferay's asset services available to your entities' service layers.
Open your `guestbook-service` module's `service.xml` file and add the following 
references just above the closing `</entity>` tag corresponding to the guestbook 
entity:

    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
    <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

Add the same two references just above the closing `</entity>` tag of the
guestbook entry entity. As discussed above, you need to use Liferay's
`AssetEntry` service so that your application can add asset entries
corresponding to guestbooks and guestbook entries. You'll also use Liferay's
`AssetLink` service so that your application will support related assets.
"Asset links" are Liferay's back-end term for related assets.