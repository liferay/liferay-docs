---
header-id: registering-permissions-with-container
---

# Registering Permissions with the Container

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Implementing Permissions</p><p>Step 3 of 5</p>
</div>

A running service checks permissions, but since the Guestbook portlet,
Guestbooks, and Guestbook Entries are new to the system, it must be taught about
them. You do this by creating permissions registrar classes. These follow what
you did in `default.xml`: you need one for your portlet permissions and one for
each of your entities. First, you must do a little reorganization. 

1.  In your API module, create a `GuestbookConstants` class in a new package
    called `com.liferay.docs.guestbook.constants`: 

    ```java
    package com.liferay.docs.guestbook.constants;

    public class GuestbookConstants {
        
        public static final String RESOURCE_NAME = "com.liferay.docs.guestbook";

    }
    ```

    The `RESOURCE_NAME` string must match exactly your resource name from
    `default.xml`. You'll see why in a moment. 

2.  You have a `GuestbookPortletKeys` class in your web module. These keys must
    now be accessible to all modules, so drag this class from the web module and
    drop it into the new `com.liferay.docs.guestbook.constants` package in your
    API module. 

Now you're ready to create your permissions registrar classes. 

1.  Open the `build.gradle` file in your `guestbook-service` module. 

2.  Add the following three dependencies and save the file: 

    ```groovy
	compileOnly group: "com.liferay", name: "com.liferay.petra.function"
	compileOnly group: "com.liferay", name: "com.liferay.petra.model.adapter"
	compileOnly group: "com.liferay", name: "com.liferay.petra.reflect"
    ```

3.  In your service module, create a package that by convention ends in
    `internal.security.permission.resource`. 

4.  Create a class in this package called
    `GuestbookModelResourcePermissionRegistrar` with the contents below. 

    ```java
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
    ```

This class registers a chain of permission logic classes for checking
permissions for Guestbook entities. Since this functionality is the same for all
entities, all that's necessary is to specify yours in addition to the standard
Liferay ones for staging and workflow. Introspection is done on your entity by
the factory to create the necessary permissions service. You implemented the
constants class so you can specify the resource model name you defined in
`default.xml`. The `model.class.name` is set so that any module needing this
service can find this model resource permission by its type. 

Now create the registrar for the `GuestbookEntry` entity: 

1.  Create a class in the same package called
    `GuestbookEntryModelResourcePermissionRegistrar`. 

2.  The only difference between this class and the one above is that it operates
    on `GuestbookEntry` entities instead of `Guestbook` entities (the imports
    have been left off in the snippet below): 

    ```java
    package com.liferay.docs.guestbook.internal.security.permission.resource;

    @Component(immediate = true)
    public class GuestbookEntryModelResourcePermissionRegistrar {

     @Activate
        public void activate(BundleContext bundleContext) {
            Dictionary<String, Object> properties = new HashMapDictionary<>();

            properties.put("model.class.name", GuestbookEntry.class.getName());

            _serviceRegistration = bundleContext.registerService(
                ModelResourcePermission.class,
                ModelResourcePermissionFactory.create(
                    GuestbookEntry.class, GuestbookEntry::getEntryId,
                    _guestbookEntryLocalService::getGuestbookEntry, _portletResourcePermission,
                    (modelResourcePermission, consumer) -> {
                        consumer.accept(
                            new StagedModelPermissionLogic<>(
                                _stagingPermission, GuestbookPortletKeys.GUESTBOOK,
                                GuestbookEntry::getEntryId));
                        consumer.accept(
                            new WorkflowedModelPermissionLogic<>(
                                    _workflowPermission, modelResourcePermission,
                                    _groupLocalService, GuestbookEntry::getEntryId));
                    }),
                properties);
        }

        @Deactivate
        public void deactivate() {
            _serviceRegistration.unregister();
        }

        @Reference
        private GuestbookEntryLocalService _guestbookEntryLocalService;

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
    ```

3.  Hit Ctrl-Shift-O to add the imports, and then save the file. 

Finally, create the registrar for the portlet permissions: 

1.  Create a class in the same package called
    `GuestbookPortletResourcePermissionRegistrar`. 

2.  This class is simpler because you don't have to tell it how to retrieve
    primary keys from any entity: 

    ```java
    package com.liferay.docs.guestbook.internal.security.permission.resource;

    @Component (immediate = true)
    public class GuestbookPortletResourcePermissionRegistrar {
        
            @Activate
        public void activate(BundleContext bundleContext) {
            Dictionary<String, Object> properties = new HashMapDictionary<>();

            properties.put("resource.name", GuestbookConstants.RESOURCE_NAME);

            _serviceRegistration = bundleContext.registerService(
                PortletResourcePermission.class,
                PortletResourcePermissionFactory.create(
                    GuestbookConstants.RESOURCE_NAME,
                    new StagedPortletPermissionLogic(
                        _stagingPermission, GuestbookPortletKeys.GUESTBOOK)),
                properties);
        }

        @Deactivate
        public void deactivate() {
            _serviceRegistration.unregister();
        }

        private ServiceRegistration<PortletResourcePermission> _serviceRegistration;

        @Reference
        private StagingPermission _stagingPermission;

    }
    ```

3.  Type Ctrl-Shift-O to add the imports. Save the file. 

You've now completed step two: the R in DRAC: registering permissions. Next,
you'll enable users to associate permissions with resources. 
