# Enabling Assets at the Service Layer [](id=enabling-assets-at-the-service-layer)

<div class="learn-path-step">
    <p>Enabling Assets at the Service Layer<br>Step 1 of 3</p>
</div>

Each row in the `AssetEntry` table represents an asset and has an `entryId` 
primary key, and `classNameId` and `classPK` foreign keys. The `classNameId` 
specifies the asset's type. For example, an asset with a `classNameId` of 
`JournalArticle` means that the asset represents a web content article (in 
@product@, `JournalArticle` is the back-end name for a web content article). An 
asset's `classPK` is the primary key of the entity represented by the asset. 

Follow these steps to make @product@'s asset services available to your 
entities' service layers: 

1.  In the `guestbook-service` module's `service.xml` file, add the following 
    references directly above the closing `</entity>` tags for `Guestbook` and 
    `Entry`: 

        <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
        <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

    As mentioned above, you must use @product@'s `AssetEntry` service for your 
    application to add asset entries that correspond to guestbooks and guestbook
    entries. You also use @product@'s `AssetLink` service for your application
    to support related assets. *Asset links* are @product@'s back-end term for
    related assets. 

2.  You must add finders--two for `Guestbook`s and two for `Entity`s--so your
    assets show in Asset Publisher. Add these below the existing finders for the
    `Guestbook` entity: 

        <finder name="Status" return-type="Collection">
			<finder-column name="status" />
		</finder>

		<finder name="G_S" return-type="Collection">
			<finder-column name="groupId" />
			<finder-column name="status" />
		</finder>

    Add these below the existing finders for the `Entry` entity: 

        <finder name="Status" return-type="Collection">
			<finder-column name="status" />
		</finder>
		<finder name="G_S" return-type="Collection">
			<finder-column name="groupId" />
			<finder-column name="status" />
		</finder>

3.  Open the `build.gradle` file. Add these dependencies: 

        compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"

    Save both files. 

4.  Right-click `build.gradle` and select *Gradle* &rarr; *Refresh Gradle
    Project*. 

5.  Run the `buildService` Gradle task. This task causes the objects referenced above
    to be injected into your services for use. 

Great! Next, you'll handle assets in your service layer. 
