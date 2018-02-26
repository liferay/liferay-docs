# Registering Permissions

Defining permissions was your first step; now you're ready to register the
permissions you've defined. All this takes is a call to Liferay's resource
service in your service layer. If you're using Service Builder, this is very
easy to do. 

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

Once you've registered permissions for your entities, you're ready to provide
users the interface to associate permissions with resources. 

