# The Workflow Framework

All the coolest Assets in @product@ are workflow-enabled. Blogs Entries, Journal
Articles, Forms Entries, and many more. There's nothing stopping you from
likewise enabling workflow for your custom Assets. Discover here how the
workflow framework works, and find the steps and code samples for enabling your
custom entities to use the workflow capabilities in subsequent articles.

A workflow process is a set of steps that an Asset must proceed through before
it's marked with the workflow status _Approved_. The steps are defined in an XML file
called a 
[workflow definition](/7-2/reference/-/knowledge_base/reference/crafting-xml-workflow-definitions). 
Each Asset is configured to run through a specific workflow definition via the
[Control Panel](/7-2/user/-/knowledge_base/user/workflow).

![Figure x: Enable workflow on your custom Asset, and it can be sent through a workflow process just like a native Asset.](../../images/workflow-configuration.png)

The workflow status is a database field that must be present for an entity to
support workflow. If a database has the status field, but no workflow code has
been written, it's auto-marked _Approved_ by Liferay's Service Builder
infrastructure, to assure that everything works smoothly by default.

## Supporting Workflow in the Database

There are several database fields that must be present for an Asset to support
workflow:

`int status` represents the workflow status of each Asset.

`long statusByUserId` is the ID of the user that set the status (for example,
the initial User that hit the Submit for Publication button to add a new
Asset.

`String statusByUserName` is the User Name of the User that set the status of
the Asset.

`Date statusDate` is the date/time when the status was set.

For Service Builder applications, add these as entity columns in the
`service.xml` file, run Service Builder, and you're good to go.

## Setting the Status Fields

Once the database table has the proper status fields, set them in your Entity's
`addEntity` service method. Normally, you'll want to set the status as a DRAFT.
It's what the workflow framework expects of an entity as it enter the workflow
process. The status is an `int`, btu you don't have to remember which number
corresponds to the DRAFT status. Instead, use the 
[`WorkflowConstants`](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/workflow/WorkflowConstants.java)
in `portal-kernel`. Passing

```java
WorkflowConstants.STATUS_DRAFT
```
is all you need here. If you're curious, the `int` represented by this constant
is `2`. The other important status for all workflow enabled Assets is

```java
WorkflowConstants.STATUS_APPROVED
```

which represents the `int` value `0`.

The User fields (`statusByUserID` and `statusByUserName`) are easy, since the
`userId` of the User making the `addEntity` request is part of the request
itself, and passed into the `addEntity` method. Use the ID directly as the
`statusByUserId`, and get the full name associated with the User by using the ID
tor retrieve the `User` object.

```java
entity.setStatusByUserId(userId);
entity.setStatusByUserName(user.getFullName());
```

The `statusDate` is usually best set as the date the entity was modified, and is
part of the Service Context in the request:

```java
entity.setStatusDate(serviceContext.getModifiedDate(null));
```

Once the status dates are set, the entity is ready to be sent into the workflow
framework.

## Sending the Entity to the Workflow Framework

When an entity is added to the database, the application must detect whether
workflow is enabled. If not, it automatically marks the entity as approved so it
appears in the UI. Otherwise, it's left in draft status and the workflow
back-end handles it. Thankfully, this whole process is easily done with a call
to `WorkflowHandlerRegistryUtil.startWorkflowInstance` in you persistence code.

## Allowing the Workflow Framework to Handle the Entity

Once the entity is sent to the Workflow Framework, much of the process is
automated, and you need not worry about the details. You do need to write one
class that gives the framework some information on how to process the entity.
It's called a workflow handler (`WorkflowHandler<T>`), and you can create it by
extending the handy abstract implementation, `BaseWorkflowHandler<T>`. 

The workflow handler usually goes in the module containing service
implementations. It's nice to keep your back-end code separate from your view
layer and controller (ala the MVC pattern).

As with most services in @product@, make your workflow handler a Component class
so it can be registered properly with OSGi runtime. It requires one Component
property, `model.class.name`, which is the fully qualified class name for class
you pass as the type parameter in the class declaration.

In addition to the property, declare the type of service you're providing in the
Component. In this case it's `WorkflowHandler.class`. You'll see the full
Component declaration in a later article.

Workflow handlers extending the `BaseWorkflowHandler` must override three methods:

`getClassName` returns the model class's fully qualified class name
(`com.my.app.package.model.FooEntity`, for example).

`getType` returns the model resource name
(`model.resource.com.my.app.package.model.FooEntity`, for example).

`updateStatus` does mos of the heavy lifting here. It returns a call to a local
service method of the same name (for example,
`FooEntityLocalService.updateStatus`), so the status returned from the workflow
back-end can be persisted to the entity table in the database. The
`updateStatus` method needs a user ID, the primary key for the class (for
example, `fooEntityId`), the workflow status, the service context, and the
workflow context. The status and the workflow context can be obtained from the
workflow back-end. The other parameters can be obtained from the workflow
context.

After your entity can be handled by Liferay's workflow framework, update the
service methods to account for workflow status and add a new method to update
the status of an entity in the database.

## Supporting Workflow in the Service Layer

The service layer must update the status of the entity when it returns from the
Workflow Framework. Make an `updateStatus` method for this purpose, and make
sure, at a minimum, to set the status fields again as the Asset comes out of the
Workflow Framework, and call the persistence layer's `update` method.

After that, provide any additional logic you might want, like checking the
status and updating the Asset's visibility (using the `assetEntryLocalService`)
based on the condition (visible if _Approved_, not visible is any other status).

You don't want the Asset appearing in the Asset Publisher if it's not
_Approved_.

Return the entry once you're through here.

## Delete the Workflow Instance Links

When you send an entity to the workflow framework via the
`startWorkflowInstance` call, it creates an entry in the `workflowinstancelink`
database table. In your service layer's deletion logic, you must delete the
workflow instance links. This `delete` call ensures there are no orphaned
entries in the `workflowinstancelinks` table.

 To get the `WorkflowInstanceLocalService` injected into your
 `*LocalServiceBaseImpl` so you can call its methods in the `LocalServiceImpl`,
 add a `reference entity` to your entity declaration in `service.xml`,
 specifying `WorkflowInstancelink`. 

## Updating the User Interface

After all the backend work, all that's left to do is update the UI. Some common
tasks here include:

- In any public facing portion of the application (accessible to guest Users),
    don't display the entity if the status is anything except _Approved_. This
    task requires the creation of an additional `finder` method that accounts
    for workfow status, and a corresponding `getter` to expose it in the service
    layer.

- In administrative portions of the application, display the entities, but also
    display their workflow status. There's a tag library for this.
