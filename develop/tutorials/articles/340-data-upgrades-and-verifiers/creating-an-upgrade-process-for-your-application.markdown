# Creating An Upgrade Process for Your App [](id=creating-an-upgrade-process-for-your-app)

As changes are made to your app, it's very likely that your database schema
changes with it, and these changes bring with them the need for an upgrade
process to move your app from one version to the next. These upgrades not only
need to happen, but also they need to be registered in the database. The system
then records the current state of the schema so that if the upgrade fails, the
process can revert the app back to its previous version.

This tutorial demonstrates how to

- Create an upgrade process for your app using Liferay's new upgrade framework

Go ahead and jump right in to declaring your dependencies next.

## Declaring Your Dependencies [](id=declaring-your-dependencies)

If your app has any dependencies, you'll have to declare a dependency on the
`portal-upgrade` module in your `build.gradle` file:

    compile project(":portal:portal-upgrade")

Once your dependencies are declared, you can write your upgrade steps.

## Writing UpgradeSteps [](id=writing-upgradesteps)

`UpgradeSteps` are executed during the upgrade process, and are responsible for
the changes that take place between each schema version. Your `UpgradeSteps` 
must extend the `UpgradeProcess` class, which is under the 
[portal-kernel module](https://github.com/liferay/liferay-portal/tree/master/portal-kernel)).

This base class provides a `doUpgrade` method that handles the upgrade process
itself. For example, the [`UpgradeCalendarBooking`](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/forms-and-workflow/calendar/calendar-service/src/main/java/com/liferay/calendar/upgrade/v1_0_0/UpgradeCalendarBooking.java) 
class modifies a column: 

    public class UpgradeCalendarBooking extends UpgradeProcess {

            @Override
            protected void doUpgrade() throws Exception {
                    alter(
                            CalendarBookingTable.class,
                            new AlterColumnType("description", "TEXT null"));
            }

    }
    
Follow this same pattern to create the `UpgradeSteps` for your upgrade process.

Once your `UpgradeSteps` are created, you can write your upgrade package.

## Writing the Upgrade Package [](id=writing-the-upgrade-package)

The first thing you need to do is create a package called `upgrade` in your 
package namespace. In the `upgrade` package, create an OSGi Component of 
the service `UpgradeStepRegistrator` that implements the interface 
`UpgradeStepRegistrator`, which you'll find under the [portal-upgrade module](https://github.com/liferay/liferay-portal/tree/master/modules/portal/portal-upgrade).

This interface provides a `register` method that handles the upgrade 
registration process.

    @Component(immediate = true, service = UpgradeStepRegistrator.class)
    public class CalendarServiceUpgrade implements UpgradeStepRegistrator {
    
    	@Override
    	public void register(Registry registry) {
    	
    	}
    }

Once you've implemented the `UpgradeStepRegister` interface, you’ll have to use 
its `register` method to specify your upgrades, defining the versions you want
to upgrade to the version you're upgrading to.

Each upgrade is represented by an upgrade registration. An upgrade registration 
is an abstraction for the changes you need to apply to the database from one 
version to the next one.

To define a registration, you need to provide

- the bundle symbolic name of the module.
- the schema version your module wants to upgrade from (as a `String`)
- the schema version your module wants to upgrade to (as a `String`)
- a list of `UpgradeSteps`

For example, here is an upgrade process for the `com.liferay.calendar.service`
module:

    @Override

    public void register(Registry registry) {
    
    	registry.register(
        		"com.liferay.calendar.service", "0.0.1", "1.0.0",
        		new com.liferay.calendar.upgrade.v1_0_0.UpgradeCalendarBooking()));    
    }

In this example, the `com.liferay.calendar.service` module is being upgraded 
from version 0.0.1 to version 1.0.0. The changes are produced by a list of 
`UpgradeSteps`, which in this example contains only one step:

    new com.liferay.calendar.upgrade.v1_0_0.UpgradeCalendarBooking());

You can also register multiple upgrades in one class. For example, here is an
extension of the previous upgrade process that runs two additional upgrades, 
each with their own set of `UpgradeSteps`:

    @Override
    public void register(Registry registry) {
        registry.register(
            "com.liferay.calendar.service", "0.0.1", "1.0.0",
            new com.liferay.calendar.upgrade.v1_0_0.UpgradeCalendarBooking());

        registry.register(
            "com.liferay.calendar.service", "1.0.0", "1.0.1",
            new com.liferay.calendar.upgrade.v1_0_1.UpgradeCalendarBooking());

        registry.register(
            "com.liferay.calendar.service", "1.0.1", "1.0.2",
            new UpgradeCalendar());

        registry.register(
            "com.liferay.calendar.service", "1.0.2", "1.0.3",
            new DummyUpgradeStep());

        registry.register(
            "com.liferay.calendar.service", "1.0.3", "1.0.4",
            new UpgradeClassNames());

        registry.register(
            "com.liferay.calendar.service", "1.0.4", "1.0.5",
            new UpgradeCalendarResource(
                _classNameLocalService, _companyLocalService,
                _userLocalService),
            new UpgradeCompanyId(), new UpgradeLastPublishDate());
    }

In this example the `com.liferay.calendar.service` module is upgraded from 
version 0.0.1 to 1.0.0 in one step. Next it is upgraded incrementally from 
version 1.0.1 to 1.0.4, using only one `UpgradeStep` for each schema version. 
Finally, it is upgraded from version 1.0.4 to version 1.0.5, using a list of 
`UpgradeSteps` with three steps in it: `UpgradeCalendarResource`, 
`UpgradeCompanyId` and `UpgradeLastPublishDate`.

+$$$

**Note:** Be careful with upgrade steps with the same name in different packages. 
This could result in the wrong version being used for the upgrade.
(see `com.liferay.calendar.upgrade.v1_0_0.UpgradeCalendarBooking` 
and `com.liferay.calendar.upgrade.v1_0_1.UpgradeCalendarBooking` as example).

$$$

The upgrade process above results in the creation of three rows in the `Release`
table, one per upgrade registration. In terms of data, each registration is
represented by a row in the `Release_` table on portal’s database. You can check
the database to verify the upgrades that were executed for each module.

Next, you can learn how to specify differing module versions and schema versions
in your bundle.

## Specifying Schema Versions [](id=specifying-schema-versions)

If your database schema is a different version than your module version, you can 
specify it in the BND file. Add the `Require-SchemaVersion` property to your 
`bnd.bnd` file to describe that the schema version of the module uses a 
different version than the bundle version:

    Require-SchemaVersion: 1.0.2

There you have it. Now you know how to create an upgrade process for your app!

## Related Topics [](id=related-topics)

[Planning a Plugin Upgrade to Liferay 7](/develop/tutorials/-/knowledge_base/7-0/migrating-existing-code-to-liferay-7)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)

[Creating a Verify Process for Your App](/develop/tutorials/-/knowledge_base/7-0/creating-a-verify-process-for-your-app)
