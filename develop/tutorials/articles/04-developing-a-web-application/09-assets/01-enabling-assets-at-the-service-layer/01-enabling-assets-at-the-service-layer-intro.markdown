# Enabling Assets at the Service Layer [](id=enabling-assets-at-the-service-layer)

<div class="learn-path-step">
    <p>Enabling Assets at the Service Layer<br>Step 1 of 3</p>
</div>

Each row in the `AssetEntry` table represents an asset. It has an `entryId`
primary key along with `classNameId` and `classPK` foreign keys. The
`classNameId` specifies the asset's type. For example, an asset with
a `classNameId` of `JournalArticle` means that the asset represents a web
content article (`JournalArticle` is the back-end name for a web content
article). An asset's `classPK` is the primary key of the entity represented by
the asset. 

Follow these steps to make asset services available to your entities' service
layers: 

1.  In the `guestbook-service` module's `service.xml` file, add the following 
    references directly above the closing `</entity>` tags for `Guestbook` and 
    `Entry`: 

        <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
        <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

    As mentioned above, you must use the `AssetEntry` service so your
    application can add asset entries corresponding to guestbooks and guestbook
    entries. You also use the `AssetLink` service to support related assets.
    *Asset links* are @product@'s back-end term for related assets. 

2.  You must add finders---two for `Guestbook`s and two for `Entity`s---so your
    assets show in Asset Publisher, because it searches for entities by `status`
    (i.e., is it Workflow-approved?) and by `groupId` (i.e., is it in this
    Site?). Add these below the existing finders for the `Guestbook` and `Entry`
    entities: 

        <finder name="Status" return-type="Collection">
			<finder-column name="status" />
		</finder>

		<finder name="G_S" return-type="Collection">
			<finder-column name="groupId" />
			<finder-column name="status" />
		</finder>

3.  Run the `buildService` Gradle task. This task injects the objects referenced above
    into your services for use. 

4.  Right-click `build.gradle` and select *Gradle* &rarr; *Refresh Gradle
    Project*. 

Great! Next, you'll handle assets in your service layer. 
