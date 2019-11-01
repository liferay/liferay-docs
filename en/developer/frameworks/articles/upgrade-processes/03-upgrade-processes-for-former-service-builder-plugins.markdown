---
header-id: upgrade-processes-for-former-service-builder-plugins
---

# Upgrade Processes for Former Service Builder Plugins

[TOC levels=1-4]

If you modularized a traditional Liferay plugin application that implements 
Service Builder services, your new modular application must register itself in 
the @product@'s `Release_` table. This is required regardless of whether release 
records already exist for previous versions of the app. A Bundle Activator is 
the recommended way to add a release record for the first modular version of 
your converted application. 

**Important**: The steps covered in this article only apply to modular 
applications that use Service Builder and were modularized from traditional 
Liferay plugin applications. They do not apply to you if your application 
doesn't use Service Builder or has never been a traditional Liferay plugin 
application (a WAR application). 

Bundle Activator class code is dense but straightforward. Referring to an 
example Bundle Activator can be helpful. Here's the Liferay Knowledge Base 
application's Bundle Activator:

```java
public class KnowledgeBaseServiceBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Filter filter = bundleContext.createFilter(
			StringBundler.concat(
				"(&(objectClass=", ModuleServiceLifecycle.class.getName(), ")",
				ModuleServiceLifecycle.DATABASE_INITIALIZED, ")"));

		_serviceTracker = new ServiceTracker<Object, Object>(
			bundleContext, filter, null) {

			@Override
			public Object addingService(
				ServiceReference<Object> serviceReference) {

				try {
					BaseUpgradeServiceModuleRelease
						upgradeServiceModuleRelease =
							new BaseUpgradeServiceModuleRelease() {

								@Override
								protected String getNamespace() {
									return "KB";
								}

								@Override
								protected String getNewBundleSymbolicName() {
									return "com.liferay.knowledge.base.service";
								}

								@Override
								protected String getOldBundleSymbolicName() {
									return "knowledge-base-portlet";
								}

							};

					upgradeServiceModuleRelease.upgrade();

					return null;
				}
				catch (UpgradeException ue) {
					throw new RuntimeException(ue);
				}
			}

		};

		_serviceTracker.open();
	}

	@Override
	public void stop(BundleContext bundleContext) {
		_serviceTracker.close();
	}

	private ServiceTracker<Object, Object> _serviceTracker;

}
```

Follow these steps to create a Bundle Activator:

1.  Create a class that implements the `org.osgi.framework.BundleActivator` 
    interface:

    ```java
    public class KnowledgeBaseServiceBundleActivator implements BundleActivator {
      
    }
    ```

2.  Add a service tracker field:

    ```java
    `private ServiceTracker<Object, Object> _serviceTracker;`
    ```

3.  Override BundleActivator's `stop` method to close the service tracker:

    ```java
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        _serviceTracker.close();
    }
    ```

4.  Override BundleActivator's `start` method to instantiate a service 
    tracker that creates a filter to listen for the app's database 
    initialization event and initializes the service tracker to use that 
    filter. You'll add the service tracker initialization code in the next 
    steps. At the end of the `start` method, open the service tracker. 

    ```java
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        Filter filter = bundleContext.createFilter(
            StringBundler.concat(
                "(&(objectClass=", ModuleServiceLifecycle.class.getName(), ")",
                ModuleServiceLifecycle.DATABASE_INITIALIZED, ")"));

        _serviceTracker = new ServiceTracker<Object, Object>(
            bundleContext, filter, null) {
            // See the next step for this code ...
        };

        _serviceTracker.open();
    }
    ```

5.  In the service tracker initialization block `{ // See the next step for this 
    code ... }` from the previous step, add an `addingService` method that 
    instantiates a `BaseUpgradeServiceModuleRelease` for describing your app. 
    The example `BaseUpgradeServiceModuleRelease` instance below describes 
    Liferay's Knowledge Base app:

    ```java
    @Override
    public Object addingService(
        ServiceReference<Object> serviceReference) {

        try {
            BaseUpgradeServiceModuleRelease
                    upgradeServiceModuleRelease =
                new BaseUpgradeServiceModuleRelease() {

                    @Override
                    protected String getNamespace() {
                        return "KB";
                    }

                    @Override
                    protected String getNewBundleSymbolicName() {
                        return "com.liferay.knowledge.base.service";
                    }

                    @Override
                    protected String getOldBundleSymbolicName() {
                        return "knowledge-base-portlet";
                    }

                };

            upgradeServiceModuleRelease.upgrade();

            return null;
        }
        catch (UpgradeException ue) {
            throw new RuntimeException(ue);
        }
    }
    ```

    The `BaseUpgradeServiceModuleRelease` implements the following methods:

    -   `getNamespace`: Returns the namespace value as specified in the former 
        plugin's `service.xml` file. This value is also in the `buildNamespace` 
        field in the plugin's `ServiceComponent` table record. 
    -   `getOldBundleSymbolicName`: Returns the former plugin's name.
    -   `getNewBundleSymbolicName`: Returns the module's symbolic name. In the 
        module's `bnd.bnd` file, it's the `Bundle-SymbolicName` value. 
    -   `upgrade`: Invokes the app's upgrade processes. 

6.  In the module's `bnd.bnd` file, reference the Bundle Activator class you 
    created. Here's the example's Bundle Activator reference:

    ```properties
    Bundle-Activator: com.liferay.knowledge.base.internal.activator.KnowledgeBaseServiceBundleActivator
    ```

The Bundle Activator uses one of the following values to initialize the 
`schemaVersion` field in the application's `Release_` table record:

-   Current `buildNumber`: if there is an existing `Release_` table record for 
    the previous plugin. 
-   `0.0.1`: if there is no existing `Release_` table record. 

Wonderful! You've set your service module's data upgrade process. 

## Related Topics

- [Creating Upgrade Processes for Modules](/docs/7-2/frameworks/-/knowledge_base/f/creating-an-upgrade-process-for-your-app)
- [Upgrading Code to @product-ver@](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-code-to-product-ver)
