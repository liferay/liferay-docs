# Creating An Upgrade Process for Your App [](id=creating-an-upgrade-process-for-your-app)

Your app goes through various stages of development. You add new features,
remove features, improve features, reorganize the code, etc. During the cycle of
development, you'll want to upgrade your app to a new version to indicate to
your end users that improvements have been made. These upgrades will need to be
registered in the database. The system records the current state of the schema 
so, in case of failure, upgrade processes can revert the app back to its 
previous version.

This tutorial demonstrates how to:

- Create an upgrade process for your app using Liferay's new upgrade framework
- Migrate an existing upgrade process to the new upgrade framework

Go ahead and jump right in to declaring your dependencies next.

## Declaring Your Dependencies [](id=declaring-your-dependencies)

If your app has any dependencies, you'll have to declare a dependency on the
`portal-upgrade` module in your `build.gradle` file:

    compile project(":portal:portal-upgrade")

If any of your upgrades extend `UpgradePortletIds`, add a dependency on 
`portal-impl` as well in your app's `build.gradle` file:

    compile group: "com.liferay.portal", name: "portal-impl", 
    version: liferay.portalVersion

Now that your dependencies are declared, you can write your upgrade package
next.

## Writing the Upgrade Package [](id=writing-the-upgrade-package)

The first thing you need to do is create a package called `upgrade` in your 
project’s layout. Inside of the `upgrade` package, create a Java class that 
implements the interface `UpgradeStepRegistrator`, which is located under the 
[portal-upgrade module](https://www.google.com/url?q=https://github.com/liferay/liferay-portal/tree/master/modules/portal/portal-upgrade&sa=D&ust=1464278927768000&usg=AFQjCNGF0wzU7e50p5GOwiXbT7XQ1OJ1JQ)).
This interface provides a `register` method that handles the upgrade 
registration process.

Once you've implemented your `UpgradeStepRegister` interface, you’ll have to use 
its `register` method to specify your upgrades, defining from which versions you 
want to upgrade, to the version it will be upgraded to.

Each upgrade is represented by an upgrade registration. An Upgrade registration 
is an abstraction for the changes you need to apply to the database from one 
version to the next one.

To define a registration, you need to provide:
- the name of the bundle representing the module
- the version your module wants to upgrade from (as a String)
- the version your module wants to upgrade to (as a String)
- a list of UpgradeSteps

For example, here is an upgrade process for the `com.liferay.microblogs.service`
module:

    @Override

    public void register(Registry registry) {
    
            registry.register(
            
                    "com.liferay.microblogs.service", "0.0.1", "1.0.0",
                    
                    new
            com.liferay.microblogs.upgrade.v1_0_0.UpgradeMicroblogsEntry());
    
    }

In this example, the `com.liferay.microblogs.service` module is being upgraded 
from version 0.0.1 to version 1.0.0. The changes are produced by a list of 
UpgradeSteps, which in this example contains only one step:

    new com.liferay.microblogs.upgrade.v1_0_0.UpgradeMicroblogsEntry());
    
You can also register multiple upgrades in one class. For example, here is an
extension of the previous upgrade process that runs two additional upgrades, 
each with their own set of UpgradeSteps:

    @Override

    public void register(Registry registry) {
    
            registry.register(
            
                    "com.liferay.microblogs.service", "0.0.1", "1.0.0",
                    
                    new
            com.liferay.microblogs.upgrade.v1_0_0.UpgradeMicroblogsEntry());
            
            registry.register(
            
                    "com.liferay.microblogs.service", "1.0.0", "1.0.1",
                    
                    new UpgradeUserNotificationEvent());

            registry.register(
            
                    "com.liferay.microblogs.service", "1.0.1", "1.0.2",
                    
                    new
            com.liferay.microblogs.upgrade.v1_0_2.UpgradeMicroblogsEntry(),
            
                    new UpgradeSocial());
    
    }

In this example the `com.liferay.microblogs.service` module is upgraded from 
version 0.01 to 1.0.0 with one step. Next, it is upgraded from version 1.0.0
to 1.0.1 using a list of UpgradeSteps with only one step in it. Finally, it is 
upgraded from version 1.0.1 to version 1.0.2, using a list of
UpgradeSteps with two steps in it, `UpgradeMicroblogsEntry` and `UpgradeSocial`, 
both in the `v1_0_2` package!

+$$$

**Note:** Be careful with upgrade steps with the same name in different packages. 
This could result in the wrong version being used for the upgrade.

$$$

The upgrade process shown previously results in the creation of three rows in 
the Release table, one per upgrade registration. In terms of data, each 
registration is represented by a row in the `Release_table` on portal’s 
database. You can check the database to verify which upgrades have been executed 
for each module.

Next, you can learn how to initialize your module.

## Initializing Your Module [](id=initializing-your-module)

It's important to note that upgrades need to wait for portal initialization, to 
perform their tasks.

To achieve this, add a setter method to your Java class, with an OSGI reference
to the portal life cycle:

    @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "_")
    
    protected void setModuleServiceLifecycle(
    
        ModuleServiceLifecycle moduleServiceLifecycle) {
        
    }

This prolongs the module's lifecycle from starting until the portal has started,
ensuring that the module won't be available until portal is fully loaded.

Next, you can learn how to specify differing module versions and schema versions
in your bundle.

## Specifying Schema Versions [](id=specifying-schema-versions)

If your database schema is a different version than your module version, you can 
specify it in the BND file. Add the `Require-SchemaVersion` property to your 
`bnd.bnd` file to describe that the schema version of the module uses a 
different version than the bundle version:

    Require-SchemaVersion: 1.0.2

That's all you need to do to create an upgrade process for your app. Read the
next section to learn how to migrate an existing upgrade process to the new
framework.

## Migrating an existing upgrade process to the new framework [](id=migrating-an-existing-upgrade-process-to-the-new-framework)

Migrating your older code to use the new framework is the same process covered 
in the previous section, with a few additions. Before you get started, you can 
review the older upgrade process next.

### Previous Upgrade Process Review [](id=previous-upgrade-process-review)

Following the prior upgrade process, you started from a class that wrapped the 
execution of several UpgradeProcesses representing the different upgrades for a 
specific version of your module.

For instance, the code below shows the prior upgrade process for 
Microblogs-service module from v1.0.0 to 1.0.1, and then to 1.0.2. :

    @Activate
    
    protected void upgrade() throws PortalException {
    
            List<UpgradeProcess> upgradeProcesses = new ArrayList<>(1);
            
            upgradeProcesses.add(new MicroblogsServiceUpgrade_1_0_0());
            
            upgradeProcesses.add(new MicroblogsServiceUpgrade_1_0_1());
            
            upgradeProcesses.add(new MicroblogsServiceUpgrade_1_0_2());
            
            for (UpgradeProcess upgradeProcess : upgradeProcesses) {
            
                    if (_log.isDebugEnabled()) {
                    
                            _log.debug("upgrade process " + upgradeProcess);
                            
                    }
                    
            }
    
    }

Each step between versions was represented by a single class extending
UpgradeProcess, with a method `doUpgrade`. This method was responsible for
executing the internal steps to update the database to that concrete
version.

The following example represents the required operations to update the database 
to v1.0.0. using the old framework:

    public class MicroblogsServiceUpgrade_1_0_0 extends UpgradeProcess {
    
            @Override
            
            protected void doUpgrade() throws Exception {
            
                    upgrade(new UpgradeMicroblogsEntry());
                    
                    upgrade(new UpgradeFoo());
                    
            }
            
    }

Whenever you needed another internal step, you added another 
`upgrade(new UpgradeBar());` etc. after the existing ones.

Now that you are familiarized with the older framework, you can learn how to
migrate your code to the new upgrade framework next.

### Migrating Your Code to the New Framework [](id=migrating-your-code-to-the-new-framework)

You can review the [Creating An Upgrade Process for Your Application](#creating-an-upgrade-process-for-your-app) 
sections for more detailed instructions and examples. Follow the steps below to
migrate your code to the new framework.

1.  Update your build files.

    Add a dependency on the `portal-upgrade` module to your `build.gradle` file. 
    If any of your upgrades extend UpgradePortletIds, add a dependency on 
    `portal-impl` as well.

2.  Check your database schema version against your bundle version.

    If the database schema is in a different version than the bundle version, 
    specify the different versions for the bundle and schema in your `bnd.bnd` 
    file, using the `require-SchemaVersion` property:

        require-SchemaVersion: 1.0.2

    If no `Required-SchemaVersion` is found, the `Bundle-Version` header will be 
    used. Now that the build files are configured, you can move on to the 
    upgrade class next.

3.  Update the upgrade class to implement the `UpgradeStepRegistrator` interface.

4.  Remove the intermediate classes that wrapped the internal steps, i.e the 
    `MicroblogsServiceUpgrade_1_0_0.java`.

    With the new framework, previous type of classes will be represented by the 
    upgrade registration explained in the [Writing the Upgrade Package](#writing-the-upgrade-package) 
    section.

    The internal steps defined within the intermediate classes, the former 
    `UpgradeProcess` class, as they are indeed UpgradeSteps, require no change
    on your part. The new framework will process the steps as they are.

5.  Remove the following Release service reference code:

        @Reference(unbind = "_")
        
        potected void setReleaseLocalService(
        
            ReleaseLocalService releaseLocalService) {
        
            _releaseLocalService = releaseLocalService;
            
        }

    And this logger code:

        private static final Log _log = logFactoryUtil.getLog(
        
                MicroblogsServiceUpgrade.class);

    You don’t need to declare a reference to the Release service, via OSGi 
    component or a direct call to the `ReleaseLocalServiceUtil`; the framework 
    will perform these tasks for you.

6.  Use the `@Reference` annotation described in the [Initializing Your Module](#initializing-your-module)
    section, to reference the portal life cycle. Remember, upgrades need to wait 
    for portal initialization, to perform their tasks.

A summary of the steps for migration are outlined in the table below for 
reference:

<style>
.lego-image {
	max-height: 100%;
	max-width: 100%;
}
.max-960 {
	margin: 0 auto;
	max-width: 960px;
}
.no-max
.max-960 {
	max-width: none;
}
.metadata-guidelines-table td {
	border-bottom: 1px solid;
	border-top: 1px solid;
	padding: 10px;
}
.table-header {
	font-weight: bold;
}
.table-header.second {
	width: 70%;
}
.left-header {
	border-right: 1px solid;
}
</style>
<div class="lego-article metadata-guidelines-table" id="article-33460946">
<div class="lego-article-content max-960">
<div class="aui-helper-clearfix lego-section section-1" >
<div class="aui-w100 block-1 content-column lego-block" >
<div class="content-column-content">
<table>
	<thead>
		<td class="table-header left-header">
			File
		</td>
		<td class="table-header second">
			Required Changes
		</td>
	</thead>
	<tbody>
		<tr>
			<td class="table-header left-header">
				build.gradle File
			</td>
			<td class="">
				<ul>
					<li>
						Add the `portal-upgrade` dependency.
					</li>
					<li>
					        If any of your upgrades extend 
					        `UpgradePortletIds`, add a 
					        dependency on `portal-impl`.
					</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
			bnd.bnd file
			</td>
			<td class="">
				<ul>
					<li>
						Add Require-SchemaVersion if
						needed.
					</li>					
					
				</ul> 
			</td>
		</tr>		
		<tr>
			<td class="table-header left-header">
			MyCustomServiceUpgrade.java
			</td>
			<td class="">
				<ul>
					<li>
						Although it is still a
						component, it's not needed to
						define the service/interface
						using declarative services.
					</li>
					<li>    
					        Implements
					        UpgradeStepRegistrator.
					</li>
					<li>
					        Waits for portal initialization.
					</li>
					<li>
					        It has no loggers.
					</li>
					<li>
					        It has no reference to Release 
					        service.
					</li>					
					
				</ul> 
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
			ReleaseLocalService reference
			</td>
			<td class="">
				<ul>
					<li>
						Remove it.
					</li>					
					
				</ul> 
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
			Logger reference
			</td>
			<td class="">
				<ul>
					<li>
						Remove it.
					</li>					
					
				</ul> 
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
			MyCustomServiceUpgrade_1_0_0.java
			</td>
			<td class="">
				<ul>
					<li>
						Disappears. It is a registration
						on the UpgradeStepRegistrator:
						
						registry.register(
						    "bundle.name","fromV",
						    "1.0.0",list
						)
					</li>					
					
				</ul> 
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
			package.v1_0_0.UpgradeFoo.java
			</td>
			<td class="">
				<ul>
					<li>
						No changes, it's already an
						UpgradeStep.
					</li>					
					
				</ul> 
			</td>
		</tr>
		<tr>
			<td class="table-header left-header">
			package.v1_0_0.UpgradeBar.java
			</td>
			<td class="">
				<ul>
					<li>
						No changes. Passed as a 
						parameter in the list of
						UpgradeSteps for the
						registration.
					</li>					
					
				</ul> 
			</td>
		</tr>		
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

There you have it. Now you know how to create an upgrade process for your app!

## Related Topics [](id=related-topics)

<!--Links to go here-->
