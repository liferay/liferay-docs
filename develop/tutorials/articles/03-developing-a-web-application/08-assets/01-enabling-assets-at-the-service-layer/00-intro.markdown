# Enabling Assets at the Service Layer [](id=enabling-assets-at-the-service-layer)

Every asset is represented by a row in the `AssetEntry` table. Each row in this
table has an `entryId` primary key and `classNameId` and `classPK` foreign keys.
An asset entry row's `classNameId` specifies the type of asset represented by
the asset entry.  For example, if a row has a `classNameId` corresponding to
`JournalArticle`, this means that the asset entry row represents a web content
article. (In Liferay, `JournalArticle` is the back-end name for web content
article.) An asset entry row's `classPK` represents the primary key of the
entity represented by the asset. 

1.  Your first step in asset-enabling your guestbook and guestbook entry
    entities is to make Liferay's asset services available to your entities'
    service layers.  Open the `guestbook-service` module's `service.xml` file
    and add the following references just above the `Guestbook`'s and the
    `Entry`'s closing `</entity>` tags:

        <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
        <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

    As discussed above, you must use Liferay's `AssetEntry` service so that your
    application can add asset entries corresponding to guestbooks and guestbook
    entries. You'll also use Liferay's `AssetLink` service so that your application
    will support related assets.  *Asset links* are Liferay's back-end term for
    related assets.

2.  Run the `buildService` Gradle task. This causes the objects referenced above
    to be injected into your services so you can use them. 

Great! Next, you'll add assets to your service layer. 
