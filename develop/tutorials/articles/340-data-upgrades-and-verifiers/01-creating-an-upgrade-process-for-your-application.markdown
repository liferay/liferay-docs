# Creating an Upgrade Process for Your App [](id=creating-an-upgrade-process-for-your-app)

As changes are made to your app, it's very likely that some of those changes are
to the database. These changes bring with them the need for an upgrade process
to move your app's database from one version to the next. Liferay has an upgrade
framework you can use to make this easier to do. It's a feature-rich framework
that makes upgrades safe: the system records the current state of the schema so
that if the upgrade fails, the process can revert the app back to its previous
version.

@product@'s upgrade framework executes your app's upgrades automatically when
the new version starts for the first time. As an app developer, you implement
concrete data schema changes in upgrade step classes and then register them with
the upgrade framework using an upgrade step registrator. In this tutorial,
you'll learn how to do all these things to create an upgrade process for your
app's modules. 

Here's what's involved:

- **Specifying the schema version**

- **Declaring dependencies**

- **Writing upgrade steps**

- **Writing the registrator**

- **Waiting for upgrade completion**

It's time to get started. 

## Specifying the Schema Version [](id=specifying-the-schema-version)

In your module's `bnd.bnd` file, specify a `Liferay-Require-SchemaVersion`
header with the new schema version value. Here's an example schema version
header for a module whose new schema is version `1.1.0`: 

        Liferay-Require-SchemaVersion: 1.1.0

+$$$

**Important**: Modules that use [Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
rely on a `Liferay-Require-SchemaVersion` header  to be present in order to
trigger upgrade process execution. 

$$$

+$$$

**Important**: If no `Liferay-Require-SchemaVersion` header is specified,
@product@ considers the `Bundle-Version` header value to be the database schema
version.

$$$

Next, you'll specify your upgrade's dependencies. 

## Declaring Dependencies [](id=declaring-dependencies)

In your application's dependency management file (e.g., Maven POM, Gradle build
file, or Ivy `ivy.xml` file), add a dependency on the [com.liferay.portal.upgrade](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.upgrade/)
module.

In a `build.gradle` file, the dependency would look like this:

    compile group: "com.liferay", name: "com.liferay.portal.upgrade", version: "2.0.0"

If there are other modules your upgrade process requires, specify them as
dependencies. 

You've configured your module project for the upgrade. It's time to create
upgrade steps to update the database from the current schema version to the new
one. 

## Writing Upgrade Steps [](id=writing-upgrade-steps)

An upgrade step is a class that adapts module data to the module's target
database schema. It can execute SQL commands and DDL files to upgrade the data.
As a developer, you can encapsulate upgrade logic in multiple upgrade step
classes per schema version. 

The upgrade class extends the [`UpgradeProcess` base class](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html),
which implements the [`UpgradeStep` interface](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeStep.html).
Each upgrade step must override the `UpgradeProcess` class's method `doUpgrade`
with instructions for modifying the database.

Since `UpgradeProcess` extends the [`BaseDBProcess` class](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/dao/db/BaseDBProcess.html),
you can use its `runSQL` and `runSQLTemplate*` methods to execute your SQL
commands and SQL DDL, respectively. 

If you want to create, modify, or drop tables or indexes by executing DDL
sentences from an SQL file, make sure to use ANSI SQL only. Doing this assures
the commands work on different databases.

If you need to use non-ANSI SQL, it's best to write it in the [`UpgradeProcess` class's](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html)
`runSQL` or `alter` methods, along with tokens that allow porting the sentences
to different databases. 

For example, consider the journal-service module's [`UpgradeSchema` upgrade step class](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/journal/journal-service/src/main/java/com/liferay/journal/internal/upgrade/v0_0_4/UpgradeSchema.java): 

    package com.liferay.journal.internal.upgrade.v0_0_4;

    import com.liferay.journal.internal.upgrade.v0_0_4.util.JournalArticleTable;
    import com.liferay.journal.internal.upgrade.v0_0_4.util.JournalFeedTable;
    import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
    import com.liferay.portal.kernel.upgrade.UpgradeProcess;
    import com.liferay.portal.kernel.util.StringUtil;

    /**
     * @author Eduardo Garcia
     */
    public class UpgradeSchema extends UpgradeProcess {

        @Override
        protected void doUpgrade() throws Exception {
            String template = StringUtil.read(
                UpgradeSchema.class.getResourceAsStream("dependencies/update.sql"));

            runSQLTemplateString(template, false, false);

            upgrade(UpgradeMVCCVersion.class);

            alter(
                JournalArticleTable.class,
                new AlterColumnName(
                    "structureId", "DDMStructureKey VARCHAR(75) null"),
                new AlterColumnName(
                    "templateId", "DDMTemplateKey VARCHAR(75) null"),
                new AlterColumnType("description", "TEXT null"));

            alter(
                JournalFeedTable.class,
                new AlterColumnName("structureId", "DDMStructureKey TEXT null"),
                new AlterColumnName("templateId", "DDMTemplateKey TEXT null"),
                new AlterColumnName(
                    "rendererTemplateId", "DDMRendererTemplateKey TEXT null"),
                new AlterColumnType("targetPortletId", "VARCHAR(200) null"));
        }

    }

The above example class `UpgradeSchema` uses the `runSQLTemplateString` method
to execute ANSI SQL DDL from an SQL file. To modify column names and column
types, it uses the `alter` method and [`UpgradeProcess`'s](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html)
`UpgradeProcess.AlterColumnName` and `UpgradeProcess.AlterColumnType` inner
classes as token classes.

Here's a simpler example upgrade step from the `com.liferay.calendar.service`
module. It uses the `alter` method to modify a column type in the calendar
booking table: 

    public class UpgradeCalendarBooking extends UpgradeProcess {

            @Override
            protected void doUpgrade() throws Exception {
                    alter(
                            CalendarBookingTable.class,
                            new AlterColumnType("description", "TEXT null"));
            }

    }

You can implement upgrade steps just like these for your module schemas. 

How you name and organize upgrade steps in your app's modules is up to you.
Liferay's upgrade classes are organized using a package structure similar to
this one:

- *some.package.structure*
    - `upgrade`
        - `v1_1_0`
            - `UpgradeFoo.java`   &larr; Upgrade Step
        - `v2_0_0`
            - `UpgradeFoo.java`   &larr; Upgrade Step
            - `UpgradeBar.java`   &larr; Upgrade Step
        - `MyCustomModuleUpgrade.java`   &larr; Registrator

The example upgrade structure shown above is for a module that has two database
schema versions: `1.1.0` and `2.0.0`. They're represented by packages `v1_1_0`
and `v2_0_0`. Each version package contains upgrade step classes that update
the database. The example upgrade steps focus on fictitious data
elements `Foo` and `Bar`. The registrator class (`MyCustomModuleUpgrade`, in this
example) is responsible for registering the applicable upgrade steps for each
schema version. 

Here are some organizational tips:

-   Put all upgrade classes in a sub-package called `upgrade`.

-   Group together similar database updates (ones that operate on a data element
    or related data elements) in the same upgrade step class.

-   Create upgrade steps in sub-packages named after each data schema version. 

After creating upgrade steps, you can create a registrator that associates them
with their target schema versions. 

## Writing the Upgrade Step Registrator [](id=writing-the-upgrade-step-registrator)

A module's upgrade step registrator notifies Liferay's upgrade framework of all
the upgrade steps to update the module data for each schema version. It
specifies the module's entire upgrade process. The upgrade framework executes
the upgrade steps to update the current module data to the latest schema.  

For example, the upgrade step registrator class `MyCustomModuleUpgrade` (below)
registers upgrade steps incrementally for each schema version (past and
present): 

    package com.liferay.mycustommodule.upgrade;

    import com.liferay.mycustommodule.upgrade.v1_1_0.UpgradeFoo;
    import com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeFoo;
    import com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeBar;

    import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

    import org.osgi.service.component.annotations.Component;

    @Component(immediate = true, service = UpgradeStepRegistrator.class)
    public class MyCustomModuleUpgrade implements UpgradeStepRegistrator {

        @Override
        public void register(Registry registry) {
            registry.register(
                "com.liferay.mycustommodule", "0.0.0", "2.0.0",
                new DummyUpgradeStep());

            registry.register(
                "com.liferay.mycustommodule", "1.0.0", "1.1.0",
                new com.liferay.mycustommodule.upgrade.v1_1_0.UpgradeFoo());

            registry.register(
                "com.liferay.mycustommodule", "1.1.0", "2.0.0",
                new com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeFoo(),
                new UpgradeBar());
        }

    }

The registrator's `register` method informs the upgrade framework about each
new schema and associated upgrade steps to adapt data to it. Each schema
upgrade is represented by a registration. A registration is an abstraction for
all the changes you need to apply to the database from one schema version to the
next one. 

The following diagram illustrates the relationship between the registrator and
the upgrade steps. 

![Figure 1: In a registrator class, the developer specifies a registration for each schema version upgrade. The upgrade steps handle the database updates.](../../images/data-upgrade-module-upgrade-architecture.png)

The previous example `MyCustomModuleUpgrade` registrator class listing shows
how this works. 

The registrator class declares itself to be an OSGi Component of service type
`UpgradeStepRegistrator.class`. The `@Component` annotation registers the class
to the OSGi framework as the module's upgrade step registrator. The attribute
`immediate = true` tells the OSGi framework to activate this module immediately
after it's installed. 

The registrator implements the [`UpgradeStepRegistrator` interface](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/foundation/portal/com.liferay.portal.upgrade/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.html),
which is in the [`com.liferay.portal.upgrade` module](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.upgrade/).
The interface declares a [`register` method](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/foundation/portal/com.liferay.portal.upgrade/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.html)
that the registrator must override. In that method, the registrator implements
all the module's upgrade registrations. 

Upgrade registrations are defined by the following values:

- **Module's bundle symbolic name**
- **Schema version to upgrade from** (as a `String`)
- **Schema version to upgrade to** (as a `String`)
- **List of upgrade steps**

The example registrator `MyCustomModuleUpgrade` registers three upgrades:

-   `0.0.0` to `2.0.0`
-   `1.0.0` to `1.1.0`
-   `1.1.0` to `2.0.0`

The `MyCustomModuleUpgrade` registrator's first registration is applied by the
upgrade framework if the module has not been installed previously. Its list of
upgrade steps contains only one: `new DummyUpgradeStep()`. 

	registry.register(
		"com.liferay.document.library.web", "0.0.0", "2.0.0",
		new DummyUpgradeStep());

The [`DummyUpgradeStep` class](https://github.com/liferay/liferay-portal/blob/7.0.1-ga2/portal-kernel/src/com/liferay/portal/kernel/upgrade/DummyUpgradeStep.java)
provides an empty upgrade step. The `MyCustomModuleUpgrade` registrator defines
this registration so that the upgrade framework records the module's latest
schema version (i.e., `2.0.0`) in @product@'s `Release_` table. 

**Important**: Modules that use Service Builder *should not* define a
registration for their initial database schema version, as Service Builder
already records their schema versions to @product@'s `Release_` table. Modules
that don't use Service Builder, however, *should* define a registration for
their initial schema. 

The `MyCustomUpgrade` registrator's next registration (from schema version
`1.0.0` to `1.1.0`) includes one upgrade step. 

	registry.register(
		"com.liferay.mycustommodule", "1.0.0", "1.1.0",
              new com.liferay.mycustommodule.upgrade.v1_1_0.UpgradeFoo());

The upgrade step's fully qualified class name is required because classes named
`UpgradeFoo` are in package `com.liferay.mycustommodule.upgrade.v1_1_0`and
`com.liferay.mycustommodule.upgrade.v2_0_0`. 

The registrator's final registration (from schema version `1.1.0` to `2.0.0`)
contains two upgrade steps. 

    registry.register(
        "com.liferay.mycustommodule", "1.1.0", "2.0.0",
        new com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeFoo(),
        new UpgradeBar());

Both upgrade steps, `UpgradeFoo` and `UpgradeBar`, reside in the module's
`com.liferay.mycustommodule.upgrade.v2_0_0` package. The fully qualified class
name `com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeFoo` is used for the
`UpgradeFoo` class, while the simple class name `UpgradeBar` is fine for the
second upgrade step. 

A registration's upgrade step list can consist of as many upgrade steps as
needed.

**Important**: If your upgrade step uses an OSGi service, your upgrade must wait
for that service's availability. To specify that your upgrade is to be executed
only after that service is available, add an OSGi reference to that service. 

For example, the [`WikiServiceUpgrade` registrator class](https://github.com/liferay/liferay-portal/blob/7.0.1-ga2/modules/apps/collaboration/wiki/wiki-service/src/main/java/com/liferay/wiki/upgrade/WikiServiceUpgrade.java)
references the `SettingsFactory` class. The upgrade step class 
[`UpgradePortletSettings` upgrade step](https://github.com/liferay/liferay-portal/blob/7.0.1-ga2/modules/apps/collaboration/wiki/wiki-service/src/main/java/com/liferay/wiki/upgrade/v1_0_0/UpgradePortletSettings.java)
uses it. Here's the `WikiServiceUpgrade` class:

	@Component(immediate = true, service = UpgradeStepRegistrator.class)
	public class WikiServiceUpgrade implements UpgradeStepRegistrator {

		@Override
		public void register(Registry registry) {
			registry.register(
				"com.liferay.wiki.service", "0.0.1", "0.0.2", new UpgradeSchema());

			registry.register(
				"com.liferay.wiki.service", "0.0.2", "0.0.3",
				new UpgradeKernelPackage(), new UpgradePortletId());

			registry.register(
				"com.liferay.wiki.service", "0.0.3", "1.0.0",
				new UpgradeCompanyId(), new UpgradeLastPublishDate(),
				new UpgradePortletPreferences(),
				new UpgradePortletSettings(_settingsFactory),
				new UpgradeWikiPageResource());
		}

		@Reference(unbind = "-")
		protected void setSettingsFactory(SettingsFactory settingsFactory) {
			_settingsFactory = settingsFactory;
		}

		private SettingsFactory _settingsFactory;

	}

In the third registration in the listing above, the `UpgradePortletSettings`
upgrade step uses the `SettingsFactory` service. The `setSettingsFactory`
method's `@Reference` annotation declares that the registrator class depends on
and must wait for the `SettingsFactory` service to be available in the run time
environment. The annotation's attribute setting `unbind = "-"` indicates that
the registrator class has no method for unbinding the service. 

Next, you must make sure the module's upgrade is executed before making its
services available.

## Waiting for Upgrade Completion [](id=waiting-for-upgrade-completion)

Before module services that access the database are used, the database should be
upgraded to the latest database schema.

As a convenience, configuring the Bnd header `Liferay-Require-SchemaVersion` to
the latest schema version is all that's required to assure the database is
upgraded for [Service Builder](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
services.

For all other services, the developer can assure database upgrade by specifying
an `@Reference` annotation that targets the containing module and its latest
schema version.

Here are the target's required attributes:

- `release.bundle.symbolic.name`: module's bundle symbolic name
- `release.schema.version`: module's current schema version

For example, the `com.liferay.comment.page.comments.web` module's [`PageCommentsPortlet` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/comment/com.liferay.comment.page.comments.web/com/liferay/comment/page/comments/web/internal/portlet/PageCommentsPortlet.html)
assures upgrading to schema version `1.0.0` by defining the following reference:

    @Reference(
        target = "(&(release.bundle.symbolic.name=com.liferay.comment.page.comments.web)(release.schema.version=1.0.0))",
        unbind = "-"
    )
    protected void setRelease(Release release) {
    }

Dependencies between OSGi services can reduce the number of service classes in
which upgrade reference annotations are needed. For example, there's no need to
add an upgrade reference in a dependent service, if the dependency already
refers to the upgrade. 
 
Now you know how to create data upgrades for all your app's modules. You specify
the new data schema version in the `bnd.bnd` file, add a reference to your
module and to the schema version to assure upgrade execution if the module doesn't
use Service Builder, and add a dependency on the `com.liferay.portal.upgrade`
module. For the second part of the process, you create upgrade step classes to
update the database schema and register the upgrade steps in a registrator
class. That's all there is to it!

## Related Topics [](id=related-topics)

[Upgrading Plugins to Liferay 7](/develop/tutorials/-/knowledge_base/7-0/upgrading-plugins-to-liferay-7)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)

<!--[Upgrading Application Upgrade and Verifier Processes ](upgrading-application-upgrade-and-verifier-processes)-->
