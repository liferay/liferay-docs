# Enabling Assets at the Service Layer [](id=enabling-assets-at-the-service-layer)

Every row represents an asset in the `AssetEntry` table. Each row in this
table has an `entryId` primary key and `classNameId` and `classPK` foreign keys.
An asset entry row's `classNameId` specifies the type of asset represented by
the asset entry. 

For example a row that has a `classNameId` corresponding to
`JournalArticle` means that the asset entry row represents a web content
article. (In Liferay, `JournalArticle` is the back-end name for web content
article.) An asset entry row's `classPK` represents the primary key of the
entity represented by the asset. 

1.  Make Liferay's asset services available to your entities'
    service layers.  Open the `guestbook-service` module's `service.xml` file
    and add the following references directly above the `Guestbook`'s and the
    `Entry`'s closing `</entity>` tags:

        <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
        <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

    As mentioned above, you must use Liferay's `AssetEntry` service so that your
    application can add asset entries that correspond to guestbooks and guestbook
    entries. You also use Liferay's `AssetLink` service so that your application
    can support related assets. *Asset links* are Liferay's back-end term for
    related assets. 

2.  Open the `build.gradle` file. Add these dependencies: 

        compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"

    Save both files. 

3.  Right-click `build.gradle` and select *Gradle* &rarr; *Refresh Gradle
    Project*. 

4.  Run the `buildService` Gradle task. This task causes the objects referenced above
    to be injected into your services for use. 

Great! Next, you'll add assets to your service layer. 
