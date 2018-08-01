# Generating Model, Service, and Persistence Layers [](id=generating-model-service-and-persistence-layers)

<div class="learn-path-step">
    <p>Generating the Back-end<br>Step 2 of 3</p>
</div>

The persistence layer saves and retrieves your model data. The service layer is
a buffer between your application and persistence layers: having it lets you 
swap out your persistence layer for a different implementation without 
modifying anything but the calls in the service layer. 

To model the guestbooks and entries, you'll create guestbook and entry model 
classes. But you won't do this directly in Java. Instead, you'll define them in 
Service Builder, which generates your object model and maps it to all the SQL 
databases @product@ supports. 

This application's design lets you create multiple guestbooks, each containing 
different sets of entries. All users with permission to access the application 
can add entries, but only administrative users can add guestbooks. 

It's time to get started. You'll create the `Guestbook` entity first: 

1.  In your `guestbook-service` project, open `service.xml`. 

2.  When Liferay @ide@ generated your project, it filled this file with dummy 
    entities, which you'll replace. First replace the file's opening contents 
    (below the `DOCTYPE`) with the following code: 

        <service-builder auto-namespace-tables="true" package-path="com.liferay.docs.guestbook">
            <author>liferay</author>
            <namespace>GB</namespace>
            <entity name="Guestbook" local-service="true" uuid="true">

    This defines the author, namespace, and the entity name. The namespace keeps 
    the database field names from conflicting. The last tag is the opening tag 
    for the `Guestbook` entity definition. In this tag, you enable local 
    services for the entity, define its name, and specify that it should have a 
    [universally unique identifier (UUID)](https://en.wikipedia.org/wiki/Universally_unique_identifier). 

3.  Next, replace the PK fields section: 

        <column name="guestbookId" primary="true" type="long" />

    This defines `guestbookId` as the entity's primary key, of the type `long`. 

4.  The group instance can be left alone.

        <column name="groupId" type="long" />

    This defines the ID of the site in @product@ that the entity instance 
    belongs to (more on this in a moment). 

5.  Leave the Audit Fields section alone. Add status fields:

        <!-- Status fields -->

        <column name="status" type="int" />
        <column name="statusByUserId" type="long" />
        <column name="statusByUserName" type="String" />
        <column name="statusDate" type="Date" />

    The Audit section defines @product@ metadata. The `companyId` is the primary
    key of a 
    [portal instance](/discover/portal/-/knowledge_base/7-0/setting-up-a-liferay-instance).
    The `userId` is the primary key of a user. The `createDate` and
    `modifiedDate` store the respective dates on which the entity instance is
    created and modified. The Status section is used later to implement
    workflow. 

6. In the Other fields section, remove all the generated fields and put this one
   in their place: 

        <column name="name" type="String" />

7.  Next, remove everything else from the Guestbook entity. Before the closing 
    `</entity>` tag, add this finder definition: 

            <finder name="GroupId" return-type="Collection">
                <finder-column name="groupId" />
            </finder>

    This defines a finder that generates a `get` method you'll use to retrieve
    Guestbook entities. The fields used by the finder define the scope of the
    data retrieved. This finder gets all Guestbooks by their `groupId`, which
    corresponds to the
    [site](/discover/portal/-/knowledge_base/7-0/starting-site-development) the
    application is on. This lets administrators put Guestbooks on multiple 
    sites, and each `Guestbook` has its own data scoped to its site. 

The `Guestbook` entity is finished for now. Next, you'll create the `Entry` 
entity: 

1.  Add the opening entity tag:

        <entity name="Entry" local-service="true" uuid="true">

    As with the `Guestbook` entity, you enable local services, define the 
    entity's name, and specify that it should have a UUID. 

2.  Add the tag to define the primary key and the `groupId`: 

        <column name="entryId" primary="true" type="long" />

        <column name="groupId" type="long" />

3.  Add the audit fields as you did with the `Guestbook` entity:

        <column name="companyId" type="long" />
        <column name="userId" type="long" />
        <column name="userName" type="String" />
        <column name="createDate" type="Date" />
        <column name="modifiedDate" type="Date" />

4. Add status fields like you did for the guestbook:

        <!-- Status fields -->

        <column name="status" type="int" />
        <column name="statusByUserId" type="long" />
        <column name="statusByUserName" type="String" />
        <column name="statusDate" type="Date" />

5.  Add the fields that define an `Entry`: 

        <column name="name" type="String" />
        <column name="email" type="String" />
        <column name="message" type="String" />
        <column name="guestbookId" type="long" />

    The `name`, `email`, and `message` fields comprise an `Entry`. These fields 
    define the name of the person creating the entry, their email address, and 
    the Guestbook message, respectively. The `guestbookId` is assigned 
    automatically by code you'll write, and is a `Guestbook` foreign key. This 
    ties the `Entry` to a specific `Guestbook`. 

6.  Add your finder and closing entity tag:

            <finder name="G_G" return-type="Collection">
                <finder-column name="groupId" />
                <finder-column name="guestbookId" />
            </finder>
        </entity>

    Here, you define a finder that gets guestbook entries by `groupId` and 
    `guestbookId`. As before, the `groupId` corresponds to the
    [site](/discover/portal/-/knowledge_base/7-0/starting-site-development) the
    application is on. The `guestbookId` defines the guestbook the entries come 
    from. This finder returns a `Collection` of entries. 

7.  Define your exception types outside the `<entity>` tags, just before the 
    closing `</service-builder>` tag: 

        <exceptions>
            <exception>EntryEmail</exception>
            <exception>EntryMessage</exception>
            <exception>EntryName</exception>
            <exception>GuestbookName</exception>
        </exceptions>

    These generate exception classes you'll use later in try/catch statements. 

8.  Save your `service.xml` file.

Now you're ready to run Service Builder to generate your model, service, and
persistence layers!

1.  In the Gradle Tasks pane on the right side of @ide@, open `guestbook-service`
    &rarr; `build`. 

2.  Run `buildService` by right-clicking it and selecting *Run Gradle Tasks*.
    Make sure you're connected to the Internet, as Gradle downloads dependencies
    the first time you run it. 

3.  In the Project Explorer, right-click the `guestbook-service` module and 
    select *Refresh*. Repeat this step for the `guestbook-api` module. This 
    ensures that the new classes and interfaces generated by Service Builder 
    show up in @ide@.

4.  In the Project Explorer, right-click the `guestbook-service` module and 
    select *Gradle* &rarr; *Refresh Gradle Project*. Repeat this step for the 
    `guestbook-api` module. This ensures that your modules' Gradle dependencies 
    are up to date. 

Service Builder is based on a design philosophy called loose coupling. It
generates three layers of your application: the model, the service, and the
persistence layers. Loose coupling means you can swap out the persistence layer
with little to no change in the model and service layers. The model is in the 
`-api` module, and the service and persistence layers are in the `-service` 
module. 

![Figure 1: The Model, Service, and Persistence Layer.](../../../images/model-service-persistence.png)

Each layer is implemented using Java Interfaces and implementations of those
interfaces. Rather than have one `Entry` class that represents your model, 
Service Builder generates a system of classes that include a `Guestbook` 
interface, a `GuestbookBaseImpl` abstract class that Service Builder manages, 
and a `GuestbookImpl` class that you can customize. This design lets you 
customize your model, while Service Builder generates code that's tedious to 
write. That's why Service Builder is a code generator for code generator haters. 

Next, you'll create the service implementations. 
