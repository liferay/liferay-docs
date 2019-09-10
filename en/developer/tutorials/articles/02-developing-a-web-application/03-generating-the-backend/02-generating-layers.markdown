---
header-id: generating-model-service-and-persistence-layers
---

# Generating Model, Service, and Persistence Layers

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Generating the Back-End</p><p>Step 2 of 3</p>
</div>

To model the guestbooks and entries, you'll create guestbook and entry model 
classes. But you won't do this directly in Java. Instead, you'll define them in 
Service Builder, which generates your object model and maps it to all the SQL 
databases @product@ supports. 

This application's design allows for multiple guestbooks, each containing 
different sets of entries. All users with permission to access the application 
can add entries, but only administrative users can add guestbooks. 

It's time to get started. You'll create the `Guestbook` entity first: 

1.  In your `guestbook-service` project, open `service.xml`. Make sure the
    *Source* tab is selected. 

2.  When Liferay @ide@ generated your project, it filled this file with dummy 
    entities, which you'll replace. Remove everything in the file below the
    `DOCTYPE`. Replace the file's opening contents with the following code: 

    ```xml
    <service-builder dependency-injector="ds" package-path="com.liferay.docs.guestbook" mvcc-enabled="true">
        <author>liferay</author>
        <namespace>GB</namespace>
        <entity name="Guestbook" local-service="true" uuid="true" remote-service="true">
    ```

    This defines the author, namespace, and the entity name. The namespace keeps 
    the database field names from conflicting. The last tag is the opening tag 
    for the `Guestbook` entity definition. In this tag, you enable local and
    remote services for the entity, define its name, and specify that it should
    have a 
    [universally unique identifier (UUID)](https://en.wikipedia.org/wiki/Universally_unique_identifier). 

3.  The Guestbook requires only two fields: a primary key to identify it
    uniquely in the database, and a name. Add these fields: 

    ```xml
    <!-- Guestbook fields -->

    <column name="guestbookId" primary="true" type="long" />
    <column name="name" type="String" />
    ```

    This defines `guestbookId` as the entity's primary key of the type `long`
    and the name as a `String`. 

4.  Next, define the group instance. The `groupId` defines the ID of the Site in
    @product@ where the entity instance exists. The `companyId` is the primary
    key of a 
    [portal instance](/docs/7-2/user/-/knowledge_base/u/setting-up).

    ```xml
    <!-- Group instance -->

    <column name="groupId" type="long" />
    <column name="companyId" type="long" />
    ```

5.  Next, add audit fields. These fields help you track owners of entity
    instances, along with those instances' create and modify dates: 

    ```xml
    <!-- Audit fields -->

    <column name="userId" type="long" />
    <column name="userName" type="String" />
    <column name="createDate" type="Date" />
    <column name="modifiedDate" type="Date" />
    ```

6.  After this, add fields that support Liferay's workflow system. These provide
    fields in the database to track your entity's status as it passes through
    the workflow. 

    ```xml
    <!-- Status fields -->

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />
    ```

7.  Before the closing `</entity>` tag, add this finder definition: 

    ```xml
        <finder name="GroupId" return-type="Collection">
            <finder-column name="groupId" />
        </finder>

    </entity>
    ```

A [finder](/docs/7-2/appdev/-/knowledge_base/a/defining-service-entity-finder-methods) 
generates a `get` method for retrieving Guestbook entities. The fields used by
the finder define the scope of the data retrieved. This finder gets all
Guestbooks by their `groupId`. This is how administrators put Guestbooks on
multiple Sites, and each `Guestbook` has its own data scoped to its Site. 

The `Guestbook` entity is finished for now. Next, you'll create the
`GuestbookEntry` entity: 

1.  Add the opening entity tag:

    ```xml
    <entity name="GuestbookEntry" local-service="true" remote-service="true" uuid="true">
    ```

    As with the `Guestbook` entity, you enable local and remote services, define
    the entity's name, and specify that it should have a UUID. 

2.  Add the fields that define the `GuestbookEntry`'s data: 

    ```xml
    <!-- Guestbook Entry fields -->

    <column name="entryId" primary="true" type="long" />
    <column name="name" type="String" />
    <column name="email" type="String" />
    <column name="message" type="String" />
    <column name="guestbookId" type="long" />
    ```

    The `name`, `email`, and `message` fields comprise a `GuestbookEntry`. These
    fields define the name of the person creating the entry, an email address,
    and the Guestbook message, respectively. The `guestbookId` is assigned
    automatically by code you'll write, and is a foreign key to the `Guestbook`
    where this entry belongs. 

3.  Add fields to track the portal instance and group: 

    ```xml
    <!-- Group instance -->

    <column name="groupId" type="long" />
    <column name="companyId" type="long" />
    ```

4. Add audit fields: 

    ```xml
    <!-- Audit fields -->

    <column name="userId" type="long" />
    <column name="userName" type="String" />
    <column name="createDate" type="Date" />
    <column name="modifiedDate" type="Date" />
    ```

5.  Add status fields to track workflow: 

    ```xml
    <!-- Status fields -->
   
    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />
    ```

6.  When querying for `GuestbookEntry`s, you can order them by one or more
    columns. Since visitors sign `Guestbook`s in order by time, order your
    `GuestbookEntry` instances by the date they were created: 

    ```xml
    <order>
        <order-column name="createDate" order-by="desc" />
    </order>
    ```

7.  Add a finder that retrieves `GuestbooEntry`s by a combination of `groupId`
    and `guestbookId`. This supports @product@'s multi-tenancy by only returning
    those entries that belong both to the current Site and the current
    Guestbook. After defining your finder add the closing entity tag:

    ```xml
        <finder name="G_G" return-type="Collection">
            <finder-column name="groupId" />
            <finder-column name="guestbookId" />
        </finder>
    </entity>
    ```

8.  Define exception types outside the `<entity>` tags, just before the closing
    `</service-builder>` tag: 

    ```xml
        <exceptions>
            <exception>GuestbookEntryEmail</exception>
            <exception>GuestbookEntryMessage</exception>
            <exception>GuestbookEntryName</exception>
            <exception>GuestbookName</exception>
        </exceptions>

    </service-builder>
    ```

    These generate exception classes you'll use later in try/catch statements. 

9.  Save your `service.xml` file.

Now you're ready to run Service Builder to generate your model, service, and
persistence layers!

1.  In the Gradle Tasks pane on the right side of @ide@, open
    `com-liferay-docs-guestbook` &rarr; `modules` &rarr; `guestbook` &rarr;
    `guestbook-service` &rarr; `build`. 

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

![Figure 1: The Model, Service, and Persistence Layer comprise a loose coupling design.](../../../images/model-service-persistence.png)

Each layer is implemented using Java Interfaces and implementations of those
interfaces. Rather than have one `Guestbook` class that represents your
model, Service Builder generates a system of classes that includes a `Guestbook`
interface, a `GuestbookBaseImpl` abstract class that Service Builder manages,
and a `GuestbookImpl` class that you can customize. With this design, you can
customize your model and let Service Builder generate the tedious-to-write
code. That's why Service Builder is a code generator for code generator haters. 

Next, you'll create the service implementations. 
