# Registering Permissions [](id=registering-permissions)

Defining permissions was your first step; now you're ready to register the
permissions you've defined. You must register your entities both in the database
and in the permissions service running in the OSGi container. 

## Registering Permissions Resources in the Database [](id=registering-permissions-resources-in-the-database)

All this takes is a call to Liferay's resource service in your service layer. If
you're using Service Builder, this is very easy to do. 

1.  Open your `-LocalServiceImpl` class. 

2.  In your method that adds an entity, add a call to add a resource with the
    entity. For example, Liferay's Blogs application adds resources this way: 
        
		resourceLocalService.addResources(
			entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
			BlogsEntry.class.getName(), entry.getEntryId(), false,
			addGroupPermissions, addGuestPermissions);

    This method requires passing in the company ID, the group ID, the user ID,
    the entity's class name, the entity's primary key, and some boolean
    settings. In order, these settings define 

    - Whether the permission is a portlet resource
    - Whether the default group permissions defined in `default.xml` should be
      added
    - Whether the default guest permissions defined in `default.xml` should be
      added

Note that the resource local service is injected automatically into your Service
Builder-generated service. 

If you're not using Service Builder, but you are using OSGi modules for your
application, you should be able to inject the resource service with an
`@Reference` annotation. If you're building a WAR-style plugin, you need
a [service tracker](/develop/tutorials/-/knowledge_base/7-1/service-trackers) to
gain access to the service. Note that your model classes must also implement
Liferay's `ClassedModel` interface. 

Similarly, when you delete an entity, you should also delete its associated
resource. Here's how the Blogs application does it in its `deleteEntry()`
method: 

		resourceLocalService.deleteResource(
			entry.getCompanyId(), BlogsEntry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

As with adding resources, the method needs to know the entity's company ID,
class, and primary key. Most of the time, its scope is an individual entity of
your own choosing. Other scopes available as constants are for company, group,
or group template (site template). These are used internally for those objects,
so you'd only use them if you were customizing functionality for creating and
deleting them. 

Now you're ready to register your entities with the permissions service. 

## Registering Entities to the Permissions Service [](id=registering-entities-to-the-permissions-service)

The permissions service that's running must know about your entities and how to
check permissions for them. This requires creating a permissions registrar
class. 

1.  In your service bundle, create a package that by convention ends in
    `internal.security.permission.resource`. For example, the Blogs
    application's package is named
    `com.liferay.blogs.internal.security.permission.resource`. 

2.  Create a class in this package called `[Entity
    Name]ModelResourcePermissionRegistrar`. For example, the Blogs application's
    class is named `BlogsEntryModelResourcePermissionRegistrar`. 

3.  This class is a component class that requires overriding the `activate`
    method to register the permissions logic you want for your entities. For
    example, this is how the Blogs application registers its permissions: 

        @Component(immediate = true)
        public class BlogsEntryModelResourcePermissionRegistrar {

            @Activate
            public void activate(BundleContext bundleContext) {
                Dictionary<String, Object> properties = new HashMapDictionary<>();

                properties.put("model.class.name", BlogsEntry.class.getName());

                _serviceRegistration = bundleContext.registerService(
                    ModelResourcePermission.class,
                    ModelResourcePermissionFactory.create(
                        BlogsEntry.class, BlogsEntry::getEntryId,
                        _blogsEntryLocalService::getEntry, _portletResourcePermission,
                        (modelResourcePermission, consumer) -> {
                            consumer.accept(
                                new StagedModelPermissionLogic<>(
                                    _stagingPermission, BlogsPortletKeys.BLOGS,
                                    BlogsEntry::getEntryId));
                            consumer.accept(
                                new WorkflowedModelPermissionLogic<>(
                                    _workflowPermission, modelResourcePermission,
                                    BlogsEntry::getEntryId));
                        }),
                    properties);
            }

            @Deactivate
            public void deactivate() {
                _serviceRegistration.unregister();
            }

            @Reference
            private BlogsEntryLocalService _blogsEntryLocalService;

            @Reference(target = "(resource.name=" + BlogsConstants.RESOURCE_NAME + ")")
            private PortletResourcePermission _portletResourcePermission;

            private ServiceRegistration<ModelResourcePermission> _serviceRegistration;

            @Reference
            private StagingPermission _stagingPermission;

            @Reference
            private WorkflowPermission _workflowPermission;

        }

We call these types of classes Registrars because the classes' job is to configure, 
register and unregister the `ModelResourcePermission`.

1.  The `model.class.name` is set in the properties so that other modules' service 
    trackers can find this model resource permission by its type when it's needed. 
    Liferay has several service trackers checking for model resource permissions. 
    The `service.ranking` property can also be set to a value greater than zero to 
    override other module's model resource permissions. 

2.  This registrar uses two portal-kernel permission logic classes for Staging
    and Workflow. Custom logic classes can be reused or composed inline since
    `ModelResourcePermissionLogic` is a `@FunctionalInterface`. Permission logic
    classes are executed in order of when they are accepted in the `Consumer`.

3.  `ModelResourcePermissionLogic` classes return `true` when users have
    permission for the action, `false` when they are denied permission for the
    action, and `null` when wanting to delegate responsibility to the next
    permission logic. If all permission logics return null then the
    `PermissionChecker.hasPermission` method is called to determine if the
    action is allowed for the user.

This class uses an `@Reference` with the target filter to inject the appropriate
`PortletResourcePermission`. `BlogsConstants.RESOURCE_NAME` evaluates to
`com.liferay.blogs`, which is defined in the `default.xml` you created earlier.
If you were to reference this `ModelResourcePermission`, you'd use a target filter
matching the `model.class.name` property set in the `activate` method.

Note that you specify your entity's class, primary key, and the entity itself
for the factory so it can create permission objects specific to your entity. 

Great! You've now completed step 2 in *DRAC* by registering your permissions.
Now you're ready to provide users the interface to associate permissions with
resources. 

