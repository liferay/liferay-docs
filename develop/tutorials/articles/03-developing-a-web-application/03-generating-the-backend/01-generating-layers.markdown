# Generating Model, Service, and Persistence Layers

The persistence layer saves and retrieves your model data. The service layer is
a buffer between your application and your persistence layer: having it gives
you the freedom in the future to swap out your persistence layer for a different
implementation without modifying anything but the calls in the service layer.

In order to model the Guestbook and Entries, you'll create a guestbook object to
contain the entry objects, but you won't do this in Java. Instead, you'll define
them in Service Builder, which not only generates your object model, but also
maps that model to all the SQL databases @product@ supports. 

The design of this application lets you create multiple guestbooks with
different sets of entries. Administrative users can add as many guestbooks and
users can add as many entries as the database can hold. 

It's time to get started. You'll create the Guestbook entity first. 

1.  Open `service.xml` in your `guestbook-service` project.

2.  When @ide@ generated your project, it filled this file with dummy entities,
    which you'll replace. First replace the opening contents of the file with
    the following code:

        <service-builder auto-namespace-tables="true" package-path="com.liferay.docs.guestbook">
        <author>liferay</author>
        <namespace>GB</namespace>
        <entity local-service="true" name="Guestbook" uuid="true">

    This defines the author, namespace, and the entity name. The namespace
    database field names from conflicting. The last tag is the opening tag for
    the Entry entity. You specify that you only need a local service, the name
    of the entity, and that it should have a 
    [universally unique identifier](https://en.wikipedia.org/wiki/Universally_unique_identifier).

3.  Next replace the PK fields section:

        <column name="guestbookId" primary="true" type="long" />

    This defined the `guestbookId` as a `long` type and the primary key for the
    Guestbook entity.

4.  The group instance can be left alone.

        <column name="groupId" type="long" />

5.  Leave the Audit Fields section alone. In the Other fields section, remove all
    the generated fields and this one in their place:

        <column name="name" type="String" />

    The Audit section defines Liferay metadata. `companyId` is the primary key of a 
    [portal instance](/discover/portal/-/knowledge_base/7-0/setting-up-a-liferay-instance). 
    `userId` is the primary key of a user. `createDate`, and `modifiedDate`
    store the create and modified dates. 

6.  Next, remove everything else from the Guestbook entity. Before its closing tag,
    add a finder definition:

            <finder name="GroupId" return-type="Collection">
                <finder-column name="groupId" />
            </finder>
        </entity>

    This defines a finder that generates a `get` method you'll use to retrieve
    Guestbook entities. The fields used by the finder define the scope of the
    data retrieved. This finder gets all Guestbooks by their `groupId`, which
    corresponds to the
    [site](/discover/portal/-/knowledge_base/7-0/starting-site-development) the
    application is on. This allows administrators to put Guestbooks on multiple
    sites, and each Guestbook has its own data scoped to its site. 

The Guestbook entity is finished for now; next, you'll create the Entry entity.

1.  First add the opening entity tag:
	
        <entity local-service="true" name="Entry" uuid="true">

    Again, you're declaring the name, that you want a local service, and that 
	you need a `uuid`.

2.  Next add the tag to define the primary key and the Group instance 
    information:

        <column name="entryId" primary="true" type="long" />

        <column name="groupId" type="long" />

3.  Add the audit fields as you did with the Guestbook entity:

        <column name="companyId" type="long" />
        <column name="userId" type="long" />
        <column name="userName" type="String" />
        <column name="createDate" type="Date" />
        <column name="modifiedDate" type="Date" />

4.  Add the fields that define a Guestbook entry: 

        <column name="name" type="String" />
        <column name="email" type="String" />
        <column name="message" type="String" />
        <column name="guestbookId" type="long" />

    The `name`, `email`, and `message` fields comprise a Guestbook entry: the
    name of the person creating the entry, his/her email address, and the
    Guestbook message. The `guestbookId` is assigned automatically by code
    you'll write, and is a foreign key to the Guestbook to which particular
    entries belong. Note that the design of the Guestbook application allows
    users to be anonymous, so they can enter their names and email address
    manually. 

5.  Then add your finder and closing entity tag:

            <finder name="G_G" return-type="Collection">
                <finder-column name="groupId" />
                <finder-column name="guestbookId" />
            </finder>
        </entity>

    In this example, you've defined a finder that gets Guestbook entries by
    `groupId` and `guestbookId`. The `groupId` corresponds to the
    [site](/discover/portal/-/knowledge_base/7-0/starting-site-development) the
    application is on, and the `guestbookId` defines which guestbook the entries
    come from. It returns a Collection of Guestbooks based on the Site
    (identified by the `groupId`) where the Guestbooks were created.

6.  Finally define your exception types and close out the Service Builder tag:
	 
        <exceptions>
          <exception>EntryEmail</exception>
          <exception>EntryMessage</exception>
          <exception>EntryName</exception>
          <exception>GuestbookName</exception>
        </exceptions>
        </service-builder>
	
	These generate exception classes you'll use later in try/catch statements. 

7.  Save your `service.xml` file.

Now you're ready to run Service Builder to generate your model, service, and
persistence layers!

1.  In the Gradle Tasks pane on the right side of @ide@, open `guestbook-service`
    &rarr; `build`. 

2.  Run `buildService` by right-clicking it and selecting *Run Gradle Tasks*.
    Make sure you're connected to the Internet, as Gradle downloads dependencies
    if this is the first time you're running it. 

4.  Right click on the `guestbook-service` module and select *Gradle* &rarr;
    *Refresh Gradle Project*.

Service Builder is based on a design philosophy called loose coupling. It
generates three layers of your application: the model, the service, and the
persistence layers. Loose coupling means you can swap out the persistence layer
with little to no change in the model and service layers. The model is contained
in the `-api` module, and the service and persistence layers are contained in
the `-service` module.

![Figure X: The Model, Service, and Persistence Layer.](../../../images/model-service-persistence.png)

Each layer is implemented using Java Interfaces and implementations of those
interfaces. Rather than have one Entry class that represents your model, Service
Builder generates a system of classes that include an `Guestbook` interface, an
`GuestbookBaseImpl` abstract class for Service Builder to manage, and an
`GuestbookImpl` class that you can customize. This design gains you the
flexibility to customize your model, while Service Builder can generate code
that's tedious for you to write. That's why Service Builder is a code generator
for code generator haters. 

Next you need to create the service implementations.
