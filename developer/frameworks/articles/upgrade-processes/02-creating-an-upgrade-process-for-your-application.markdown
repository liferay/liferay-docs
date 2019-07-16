---
header-id: creating-an-upgrade-process-for-your-app
---

# Creating Upgrade Processes for Modules

[TOC levels=1-4]

Follow these steps to create an upgrade process for your module: 

1.  Open your module's `bnd.bnd` file, and specify a 
    `Liferay-Require-SchemaVersion` header with the new schema version value. 
    Here's an example schema version header for a module whose new schema is 
    version `1.1`:

    ```properties
    Liferay-Require-SchemaVersion: 1.1
    ```

    | **Important**: If no `Liferay-Require-SchemaVersion` header is specified, 
    | @product@ considers the `Bundle-Version` header value to be the database 
    | schema version. 
 
2.  [Add a dependency](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies) 
    on the [`com.liferay.portal.upgrade` module](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.upgrade/), along with any other 
    modules your upgrade process requires, in your your module's dependency 
    management file (e.g., Maven POM, Gradle build file, or Ivy `ivy.xml` file). 
    An example configuration for a `build.gradle` file is shown below:

    ```groovy
    compile group: "com.liferay", name: "com.liferay.portal.upgrade.api", version: "2.0.3"
    ```

3.  Create an `UpgradeProcess` class that extends the 
    [`UpgradeProcess` base class](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html) ( 
    which implements the [`UpgradeStep` interface](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeStep.html)):

    ```java
    public class MyUpgradeSchemaClass extends UpgradeProcess {
      ...
    }
    ```

4.  Override the `UpgradeProcess` class's `doUpgrade()` method with instructions 
    for modifying the database. Use the `runSQL` and `runSQLTemplate*` methods 
    (inherited from the 
    [`BaseDBProcess` class](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/db/BaseDBProcess.html)) 
    to execute your SQL commands and SQL DDL, respectively. If you want to 
    create, modify, or drop tables or indexes by executing DDL sentences from an 
    SQL file, make sure to use ANSI SQL only. Doing this assures the commands 
    work on different databases. If you need to use non-ANSI SQL, it's best to 
    write it in the [`UpgradeProcess` class's](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html) 
    `runSQL` or `alter` methods, along with tokens that allow porting the 
    sentences to different databases, as shown in journal-service module's 
    [`UpgradeSchema` upgrade step class](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/journal/journal-service/src/main/java/com/liferay/journal/internal/upgrade/v0_0_4/UpgradeSchema.java) 
    below which uses the `runSQLTemplateString` method to execute ANSI SQL DDL 
    from an SQL file and the `alter` method and [`UpgradeProcess`'s](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html) 
    `UpgradeProcess.AlterColumnName` and `UpgradeProcess.AlterColumnType` inner 
    classes as token classes to modify column names and column types:
    
    ```java
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
    ```

    Here's a simpler example upgrade step from the 
    `com.liferay.calendar.service` module. It uses the `alter` method to modify 
    a column type in the calendar booking table:

    ```java
    public class UpgradeCalendarBooking extends UpgradeProcess {

            @Override
            protected void doUpgrade() throws Exception {
                    alter(
                            CalendarBookingTable.class,
                            new AlterColumnType("description", "TEXT null"));
            }

    }
    ``` 

5.  If your application was modularized from a former traditional Liferay plugin 
    application (application WAR) and it uses Service Builder, 
    [create and register a Bundle Activator](/docs/7-2/frameworks/-/knowledge_base/f/upgrade-processes-for-former-service-builder-plugins) 
    to register it in @product@'s `Release_` table. 

6.  Create an `UpgradeStepRegistrator` OSGi Component class of service type 
    `UpgradeStepRegistrator.class` that implements the 
    [`UpgradeStepRegistrator` interface](@app-ref@/foundation/latest/javadocs/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.html):

    ```java
    package com.liferay.mycustommodule.upgrade;

    import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

    import org.osgi.service.component.annotations.Component;

    @Component(immediate = true, service = UpgradeStepRegistrator.class)
    public class MyCustomModuleUpgrade implements UpgradeStepRegistrator {


    }
    ```

7.  Override the [`register` method](@app-ref@/foundation/latest/javadocs/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.html#register-com.liferay.portal.upgrade.registry.UpgradeStepRegistrator.Registry-) 
    to implement the module's upgrade registrations---abstractions for the 
    upgrade steps required to update the database from one schema version to the 
    next. For example, the upgrade step registrator class 
    `MyCustomModuleUpgrade` (below) registers three upgrade steps incrementally 
    for each schema version (past and present, `0.0.0` to `2.0.0`, `1.0.0` to 
    `1.1.0`, and `1.1.0` to `2.0.0`). 

    The first registration is applied if the module hasn't been installed 
    previously. It contains only one empty upgrade step: `new [DummyUpgradeStep](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/upgrade/DummyUpgradeStep.java)()`. 
    This registration records the module's latest schema version (i.e., `2.0.0`) 
    in @product@'s `Release_` table. Note that if the same class name is used in 
    multiple packages, you must provide the fully qualified class name for the 
    class, as shown in the second registration (`1.0.0` to `1.1.0`) below for 
    the `UpgradeFoo` class:

    ```java
    package com.liferay.mycustommodule.upgrade;

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
    ```

    | **Important**: Modules that use Service Builder *should not* define a
    | registration for their initial database schema version, as Service Builder
    | already records their schema versions to @product@'s `Release_` table. Modules
    | that don't use Service Builder, however, *should* define a registration for
    | their initial schema. 

8.  If your upgrade step uses an OSGi service, **your upgrade must wait for 
    that service's availability**. Use the `@Reference` annotation to declare 
    any classes that the registrator class depends on. For example, the 
    [`WikiServiceUpgrade` registrator class](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/wiki/wiki-service/src/main/java/com/liferay/wiki/internal/upgrade/WikiServiceUpgrade.java) 
    below references the `SettingsFactory` class for the 
    [`UpgradePortletSettings` upgrade step](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/wiki/wiki-service/src/main/java/com/liferay/wiki/internal/upgrade/v1_0_0/UpgradePortletSettings.java). 
    The `setSettingsFactory` method's `@Reference` annotation declares that the 
    registrator class depends on and must wait for the `SettingsFactory` service 
    to be available in the run time environment:
    
    ```java
    
    @Component(immediate = true, service = UpgradeStepRegistrator.class)
    public class WikiServiceUpgrade implements UpgradeStepRegistrator {
    
    	@Override
    	public void register(Registry registry) {
    		registry.register("0.0.1", "0.0.2", new UpgradeSchema());
    
    		registry.register("0.0.2", "0.0.3", new UpgradeKernelPackage());
    
    		registry.register(
    			"0.0.3", "1.0.0", new UpgradeCompanyId(),
    			new UpgradeLastPublishDate(), new UpgradePortletPreferences(),
    			new UpgradePortletSettings(_settingsFactory), new UpgradeWikiPage(),
    			new UpgradeWikiPageResource());
    
    		registry.register("1.0.0", "1.1.0", new UpgradeWikiNode());
    
    		registry.register(
    			"1.1.0", "1.1.1",
    			new UpgradeDiscussionSubscriptionClassName(
    				_subscriptionLocalService, WikiPage.class.getName(),
    				UpgradeDiscussionSubscriptionClassName.DeletionMode.ADD_NEW));
    
    		registry.register(
    			"1.1.1", "2.0.0",
    			new BaseUpgradeSQLServerDatetime(
    				new Class<?>[] {WikiNodeTable.class, WikiPageTable.class}));
    	}
    
    	@Reference
    	private SettingsFactory _settingsFactory;
    
    	@Reference
    	private SubscriptionLocalService _subscriptionLocalService;
    
    }
    ```

9.  Upgrade the database to the latest database schema version before making its 
    services available. To do this, configure the Bnd header 
    `Liferay-Require-SchemaVersion` to the latest schema version for 
    [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) 
    services. For all other services, specify an `@Reference` annotation that 
    targets the containing module and its latest schema version.

    Here are the target's required attributes:
    - `release.bundle.symbolic.name`: module's bundle symbolic name
    - `release.schema.version`: module's current schema version

    For example, the `com.liferay.comment.page.comments.web` module's 
    [`PageCommentsPortlet` class](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/comment/comment-page-comments-web/src/main/java/com/liferay/comment/page/comments/web/internal/portlet/PageCommentsPortlet.java) 
    upgrades to schema version `2.0.0` by defining the reference below:

    ```java
    @Reference(
        target = "(&(release.bundle.symbolic.name=com.liferay.comment.page.comments.web)(release.schema.version=2.0.0))"
    )
    private Release _release;
    ```

    Dependencies between OSGi services can reduce the number of service classes 
    in which upgrade reference annotations are needed. For example, there's no 
    need to add an upgrade reference in a dependent service, if the dependency 
    already refers to the upgrade. 

    | **Note**: Data verifications using the class `VerifyProcess` are deprecated.
    | Verifications should be tied to schema versions. Upgrade processes are associated
    | with schema versions but `VerifyProcess` instances are not.
 
Great! Now you know how to create data upgrades for all your modules. 

## Related Topics

- [Upgrade Processes for Former Service Builder Plugins](/docs/7-2/frameworks/-/knowledge_base/f/upgrade-processes-for-former-service-builder-plugins)
- [Upgrading Code to @product-ver@](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-code-to-product-ver)
- [Configurable Applications](/docs/7-2/frameworks/-/knowledge_base/f/configurable-applications)
