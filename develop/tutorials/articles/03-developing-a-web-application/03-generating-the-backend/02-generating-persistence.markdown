# Persistence

The persistence layer saves and retrieves your model data. The service layer is
like a buffer zone between your application and your persistence layer: having
it gives you the freedom in the future to swap out your persistence layer for a
different implementation without modifying anything but the calls in the service
layer.

In order to model the Guestbook and Entries properly, you must introduce some
new concepts into the application. Rather than simply create entries in place,
you will create a guestbook object which will contain the entries. You'll need
to also create the various methods associated with guestbook management. In
addition, you'll add a field for users to enter an email address in the
guestbook.

When you create separate entity for guestbooks, you will be able to create
multiple guestbooks with different sets of entries. You will also separate the 
entity data from being simply the portlet data, and instead you will have
guestbooks and entities within those guestbooks.

1. Open `service.xml` in your `guestbook-service` project.
2. First replace the opening contents with file with the following:

	    <service-builder auto-namespace-tables="true" package-path="com.liferay.docs.guestbook">
		<author>liferay</author>
		<namespace>GB</namespace>
		<entity local-service="true" name="Entry" uuid="true">

    This defines the author, namespace, and the basic entity data. The 
	namespace is important for the naming of database fields so that similarly
	named fields don't conflict. The last tag is the opening tag for the Entry
	entity. You specify that you only need a local service, the name of the
	entity, and that it should have a "universally unique identifier."

3. Next replace the PK fields area with the following:

			<!-- PK fields -->

			<column name="entryId" primary="true" type="long" />

    This defined the `entryId` as a `long` type and the primary key for the
	Entry entity.

4. The group instance can be left alone.

			<!-- Group instance -->

			<column name="groupId" type="long" />

5. Next replace the Audit fields.

			<!-- Audit fields -->

			<column name="companyId" type="long" />
			<column name="userId" type="long" />
			<column name="userName" type="String" />
			<column name="createDate" type="Date" />
			<column name="modifiedDate" type="Date" />
			<column name="name" type="String" />
			<column name="email" type="String" />
			<column name="message" type="String" />
			<column name="guestbookId" type="long" />

    These provide the fields for the data which will be stored in the database. 
	The top four fields, `companyId`, `userId`, `createdDate`, and 
	`modifiedDate` are fields that are provided by the system in the current    
	context. The `name`, `email`, and `message` fields are  going to be defined 
	by the user when an entry is created. The `guestbookId` will also be 
	assigned based on the context where the entry is being created.

6. Finally you can add the finder data and the closing tag for the Entry entity:

			<finder name="G_G" return-type="Collection">
				<finder-column name="groupId" />
				<finder-column name="guestbookId" />
			</finder>
		</entity>

	These define the fields that you can search for entries by, they will be 
	returned in as a Java Collection. The fields that you define here will be 
	available for the creation of "get" methods later. Be careful how you define
	your finders because you could run into issues if they are in any way 
	ambiguous.

That takes care of the Entry entity, but you also need to create the Guestbook
entity.

1. First add the opening entity tag:
		
		<entity local-service="true" name="Guestbook" uuid="true">

    Again, you're declaring the name, that you want a local service, and that 
	you need a `uuid`.

2. Next add the tag to define the primary key and the Group instance 
    information:

	 <!-- PK fields -->

	 <column name="guestbookId" primary="true" type="long" />

	 <!-- Group instance -->

	 <column name="groupId" type="long" />

3. Just like with the Entry field, you need to add the main data fields:

	 <!-- Audit fields -->

	 <column name="companyId" type="long" />
	 <column name="userId" type="long" />
	 <column name="userName" type="String" />
	 <column name="createDate" type="Date" />
	 <column name="modifiedDate" type="Date" />
	 <column name="name" type="String" />

	Like the Entry fields, most of these are fields defined by your context, and
	stored in the Guestbook object. The `name` field is the only thing which the
	user will define to add to the `Guestbook` object.

4. Then your finder and closing entity tag:

	 <finder name="GroupId" return-type="Collection">
		<finder-column name="groupId" />
	 </finder>
     </entity>
	 
	 The finder enables you to create methods with which will retrieve a 
	 Collection of Guestbooks based on the Site (identified by the `groupId`) 
	 where the Guestbooks were created.

5. Finally define your exception types and close out the Service Builder tag:
	 
	 <exceptions>
		<exception>EntryEmail</exception>
		<exception>EntryMessage</exception>
		<exception>EntryName</exception>
		<exception>GuestbookName</exception>
	 </exceptions>
	 </service-builder>
	
	These allow you to specify specific exemptions and generate custom messages 
	for these criteria. Providing customized exemptions is a big component of 
	a positive user experience.
	

Once you’ve created your service.xml file, you’re ready to generate everything 
else you need to access your database.

1. Go to the Gradle Tasks pane on the right side of @ide@.
2. Open `guestbook-service` &rarr; `build`
3. Run `buildService`

If this is your first time running Service Builder, you probably have a lot of 
questions right now, like "What just happened?" and "Why are there so many new 
packages and classes?" Service Builder is based on a design philosophy called 
loose coupling. At a macro level, you have three layers of your application: 
the model, the service, and the persistence layers. The application is designed 
in such a way that you can swap out the persistence layer with little to no 
change in the model and service layers. With our template, the model is 
contained in the `-api` module, while the service and persistence layers are 
contained in the `-service` module.

![Figure X: The Model, Service, and Persistence Layer.](../../../images/model-service-persistence.png)

At a micro level, each layer is implemented using Java Interfaces and various 
implementations of those interfaces. Rather than have one Entry class that 
represents your model, Service Builder has generated a system of classes that 
include an `Entry` interface, an `EntryBaseImpl` abstract class for Service 
Builder to manage, and an `EntryImpl` class that you can customize. This gives 
you the flexibility of being able to customize your model, while still allowing 
Service Builder to generate the code it needs. That’s why Service Builder is a 
code generator for code generator haters: it generates the stuff that’s tedious 
to write, while giving you the freedom to customize anything you want.

Next you need to create the implementations for our services.
