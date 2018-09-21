# Registering Your Defined Permissions [](id=registering-your-defined-permissions)

<div class="learn-path-step">
    <p>Implementing Permissions<br>Step 2 of 4</p>
</div>

The last step introduced the concept of *resources*. Resources are data stored 
with your entities that define how they can be accessed. For example, when the 
configuration in your `default.xml` files is applied to your application's 
entities in the database, resources are created. These resources are then used 
in conjunction with @product@'s permissions system to determine who can do what 
to the entities. 

To use these resources, @product@ must know about them. To do that you
*register* the resources with the system, both in the database and with the
running permissions system in the OSGi container. 

## Registering Permissions in the Database

@product@ provides a complete API for managing resources that's integrated with 
Service Builder. This API is injected into your implementation classes
automatically. To manage the resources, you need only call the API in the
service's add and delete methods. Follow these steps to do this in your
application: 

1.  In your `guestbook-service` module, open `GuestbookLocalServiceImpl.java` 
    from the `com.liferay.docs.guestbook.service.impl` package. 

2.  Just before the `addGuestbook` method's `return` statement, add this code: 

        resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
            Guestbook.class.getName(), guestbookId, false, true, true);

    Note that the `resourceLocalService` object is already there, ready for you 
    to use. This is one of several utilities that are injected automatically
    by Service Builder. You'll see the rest in the future.

    This code adds a resource to @product@'s database to correspond with your 
    entity (note that the `guestbookId` is included in the call). The three 
    booleans at the end are settings. The first is whether to add portlet 
    action permissions. This should only be `true` if the permission is for a 
    portlet resource. Since this permission is for a model resource (an entity), 
    it's `false`. The other two are settings for adding group and guest 
    permissions. If you set these to `true`, you'll add the default permissions 
    you defined in the permissions configuration file (`default.xml`) in the 
    previous step. Since you definitely want to do this, these booleans are set 
    to `true`. 

3.  Next, go to the `updateGuestbook` method. Add a similar bit of code in 
    between `guestbookPersistence.update(guestbook);` and the `return` 
    statement: 
    
        resourceLocalService.updateResources(serviceContext.getCompanyId(),
                        serviceContext.getScopeGroupId(), 
                        Guestbook.class.getName(), guestbookId,
                        serviceContext.getGroupPermissions(),
                        serviceContext.getGuestPermissions());

4.  Now you'll do the same for `deleteGuestbook`. Add this code in between 
    `guestbook = deleteGuestbook(guestbook);` and the `return` statement:
    
        resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                        Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                        guestbookId);

5.  Hit [CTRL]+[SHIFT]+O to organize the imports and save the file. 

6.  Now you'll add resources for the `Entry` entity. Open 
    `EntryLocalServiceImpl.java` from the same package. For `addEntry`, add a 
    line of code that adds resources for this entity, just before the return 
    statement: 

        resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
            Entry.class.getName(), entryId, false, true, true);

7.  For `deleteEntry`, add this code just before the `return` statement:
    
        resourceLocalService.deleteResource(
                       serviceContext.getCompanyId(), Entry.class.getName(),
                       ResourceConstants.SCOPE_INDIVIDUAL, entryId);

8.  Finally, find `updateEntry` and add its resource action, also just before 
    the `return` statement: 

        resourceLocalService.updateResources(
              user.getCompanyId(), serviceContext.getScopeGroupId(), 
              Entry.class.getName(), entryId, serviceContext.getGroupPermissions(),
              serviceContext.getGuestPermissions());

That's all it takes to add permissions resources to the database. Future
entities added to the database are fully permissions-enabled. Note, however,
that any entities you've already added to your Guestbook application in the
portal don't have resources and thus can't be protected by permissions. You'll
fix this at the end of this section. Now you must register permissions with the
permissions system, so it knows how to check for them. 

## Registering Your Entities with the Permissions Service

A running service checks permissions, but since Guestbooks and Guestbook Entries
are new to the system, it must be taught about them. You do this by creating
a permissions registrar class: 

1.  Create a Constants class first. In your API module, create
    `GuestbookConstants` class in a new package called
    `com.liferay.docs.guestbook.constants`: 

        package com.liferay.docs.guestbook.constants;

        public class GuestbookConstants {
            
            public static final String RESOURCE_NAME = "com.liferay.docs.guestbook";

        }

    The `RESOURCE_NAME` string must match exactly your resource name from
    `default.xml`. You'll see why in a moment. 

2.  You have a `GuestbookPortletKeys` class in your web module. These keys must
    now be accessible to all modules, so drag this class from the web module and
    drop it into the new `com.liferay.docs.guestbook.constants` package in your
    API module. 

3.  In your service bundle, create a package that by convention ends in
    `internal.security.permission.resource`. 

4.  Create a class in this package called
    `GuestbookModelResourcePermissionRegistrar` with the contents below. 

        package com.liferay.docs.guestbook.internal.security.permission.resource;

        import java.util.Dictionary;

        import org.osgi.framework.BundleContext;
        import org.osgi.framework.ServiceRegistration;
        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Deactivate;
        import org.osgi.service.component.annotations.Reference;

        import com.liferay.docs.guestbook.constants.GuestbookConstants;
        import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
        import com.liferay.docs.guestbook.model.Guestbook;
        import com.liferay.docs.guestbook.service.GuestbookLocalService;
        import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
        import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
        import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
        import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
        import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
        import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
        import com.liferay.portal.kernel.service.GroupLocalService;
        import com.liferay.portal.kernel.util.HashMapDictionary;
        import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;

        @Component (immediate=true)
        public class GuestbookModelResourcePermissionRegistrar {

         @Activate
            public void activate(BundleContext bundleContext) {
                Dictionary<String, Object> properties = new HashMapDictionary<>();

                properties.put("model.class.name", Guestbook.class.getName());

                _serviceRegistration = bundleContext.registerService(
                    ModelResourcePermission.class,
                    ModelResourcePermissionFactory.create(
                        Guestbook.class, Guestbook::getGuestbookId,
                        _guestbookLocalService::getGuestbook, _portletResourcePermission,
                        (modelResourcePermission, consumer) -> {
                            consumer.accept(
                                new StagedModelPermissionLogic<>(
                                    _stagingPermission, GuestbookPortletKeys.GUESTBOOK,
                                    Guestbook::getGuestbookId));
                            consumer.accept(
                                new WorkflowedModelPermissionLogic<>(
                                        _workflowPermission, modelResourcePermission,
                                        _groupLocalService, Guestbook::getGuestbookId));
                        }),
                    properties);
            }

            @Deactivate
            public void deactivate() {
                _serviceRegistration.unregister();
            }

            @Reference
            private GuestbookLocalService _guestbookLocalService;

            @Reference(target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")")
            private PortletResourcePermission _portletResourcePermission;

            private ServiceRegistration<ModelResourcePermission> _serviceRegistration;

            @Reference
            private StagingPermission _stagingPermission;

            @Reference
            private WorkflowPermission _workflowPermission;
            
            @Reference
            private GroupLocalService _groupLocalService;
        }

This class registers a chain of permission logic classes for checking
permissions. Since this functionality is the same for all entities, all that's
necessary is to specify yours in addition to the standard Liferay ones for
staging and workflow. Introspection is done on your entity by the factory to
create the necessary permissions service. You implemented the constants class so
you can specify the resource model name you defined in `default.xml`. The
`model.class.name` is set so that any module needing this service can find this
model resource permission by its type. 

You've now completed step two: the R in DRAC: registering permissions. Next,
you'll enable users to associate permissions with resources. 
