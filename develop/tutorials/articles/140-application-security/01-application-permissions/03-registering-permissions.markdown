# Registering Permissions

Defining permissions was your first step; now you're ready to register the
permissions you've defined. You must register your entities both in the database
and to the permissions service running in the OSGi container. 

## Registering Permissions Resources in the Database

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
Builder generated service. 

If you're not using Service Builder, but you are using OSGi modules for your
application, you should be able to inject the resource service with an
`@Reference` annotation. If you're building a WAR-style plugin, you need
a [service tracker](/develop/tutorials/-/knowledge_base/7-0/service-trackers) to
gain access to the service. 

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

## Registering Entities to the Permissions Service

The permissions service that's running needs to know about your entities and how
to check permissions for them. This requires creating a permissions registrar
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

The `activate` 

Once you've registered permissions for your entities, you're ready to provide
users the interface to associate permissions with resources. 

