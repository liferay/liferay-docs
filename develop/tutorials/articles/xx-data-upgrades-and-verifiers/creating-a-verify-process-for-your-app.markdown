# Creating A Verify Process for Your App [](id=creating-a-verify-process-for-your-app)

A verify process is a class that will run on portal startup to verify and fix
any integrity problems found in the database. You should be aware that this may
make modifications directly to the database. During the cycle of development, 
you'll want to verify the data your app is producing is consistent.

This tutorial demonstrates how to:

- Create a verify process for your app using new development patterns

Get started by writing your verify package next.

## Writing the Verify Package [](id=writing-the-verify-package)

In order to create a verify process you must first create a package called 
`verify` in your project's layout. Inside of the `verify` package, create a OSGi 
Component of the service `VerifyProcess`class that extends the interface 
`VerifyProcess`.

This interface provides a `doVerify` method that handles the verifiers. For
example take a look at the [MDRServiceVerifyProcess.java](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/foundation/mobile-device-rules/mobile-device-rules-service/src/main/java/com/liferay/mobile/device/rules/verify/MDRServiceVerifyProcess.java) 
class for the mobile device rules app:

    @Component(
            immediate = true,
            property = {"verify.process.name=
            com.liferay.mobile.device.rules.service"},
            service = VerifyProcess.class
    )
    public class MDRServiceVerifyProcess extends VerifyProcess {
    
            @Override
            protected void doVerify() throws Exception {
                    verifyResourcedModels();
            }

    }

You must use `VerifyProcess.class` as the `service` property value, to denote 
that the class is a valid implementation for the `VerifyProcess` interface.
The `immediate` property specifies that the component will be available 
immediately, rather than the first time it's used.

You must also define the mandatory `verify.process.name` property, which is used 
by the OSGi service tracker for verifiers to capture verifier components. See 
[VerifyProcessTracker.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTracker.java#L152-L153)
for more information. And to execute them see [VerifyProcessTracker.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTracker.java#L344-L361)
for more information.

It is recommended that you use the name of the service package of the app as the
value for the `verify.process.name` property, as shown in the mobile device
rules app verify process above.

Now that your verify process class is written, you can learn how to declare
dependencies on Liferay services for your verify processes next.

## Using Liferay Services in Your Verify Process [](id=using-liferay-services-in-your-verify-process)

Verify processes execute on the startup of a specific release. It's important
to be aware of this while running upgrade processes because your database starts
in a previous schema version, then gets upgraded to the new schema version, 
which triggers the release verify processes.

What this means for you as a developer is that your verify processes can use 
release Liferay services.

This is an important distinction between upgrade processes and verify processes.
An upgrade process of the portal core cannot use Liferay services to access the 
database, as those Liferay services are using the previous definition of the 
database, but since verify processes are already in the upgraded schema version, 
they can access and use Liferay services for the updated database.

Since you can use Liferay services in your verify processes, you can delcare
dependencies on them and make references to them. Because the services are not 
available until the Portal is started, **your verify process must depend on 
them** to load, before your processes can execute. You can delcare a dependency 
for your verify process, using the `@Reference` annotation. For example, here is
a reference to the `DLFileVersionLocalService` for the [document library app](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/collaboration/document-library/document-library-service/src/main/java/com/liferay/document/library/workflow/DLFileEntryWorkflowHandler.java):

    @Reference(unbind = "-")
    protected void setDLFileVersionLocalService(
      DLFileVersionLocalService dlFileVersionLocalService) {

      _dlFileVersionLocalService = dlFileVersionLocalService;
    }
    ...
    private DLFileVersionLocalService _dlFileVersionLocalService;

This lets the verify process know that the OSGi framework will provide a valid 
implementation of the proper service (in this example, a 
`DLFileVersionLocalService`). Whenever some other module provides that 
implementation, the verify process can use it.

As you may have noticed, the ServiceUtil classes are no longer being used.
Instead, the OSGi framwork is providing the Liferay services, so you can
directly use the components.

Now that your verify process is written, you'll need to configure portal
settings for it next.

## Configuring Portal Settings for Your Verify Process [](id=configuring-portal-settings-for-your-verify-process)

Once you've written your data verifier process, you'll need to enable it to 
startup with the portal. To enable your verify process on startup, you must add 
it to your portal configuration, under the `verify.processes` property in your 
`portal-ext.properties` file:

    verify.processes=com.liferay.portal.verify.VerifyProcessSuite,
    my.package.MyVerifyProcess
    
See the [portal.properties](https://github.com/liferay/liferay-portal/blob/d0dc23ac195b2ac0ce3b893b74538b5fe71fcfa2/portal-impl/src/portal.properties#L169-L179)
verify section for more information.

Now that your verify processes are enabled, you can control the frequency at
which they execute next.

### Setting Verification frequency [](id=setting-verification-frequency)

The verify processes can be configured to be executed with a custom frequency on 
portal startup. This frequency only accepts three possible values:

 - **Always executed:** All verify processes will be executed on each portal
   startup.
 - **Never executed:** No verify processes will be executed on each portal
   startup.
 - **Executed once:** All verify processes will be executed only in next portal
   startup, and no more.

This frequency can be configured in `portal-ext.properties` file, using the
`verify.frequency` property. Possible values are `-1`, `0` and `1`, meaning 
Always, Never and Once, respectively:

    verify.frequency=1
    
Next, you can configure the transactions for your verify process.

### Setting Transactions during data verification [](id=setting-transactions-during-data-verification)

You can disable database transactions management during verification. This
forces autocommit, which will speed up the verify process.

To disable database transactions, you must add it to your
`portal-ext.properties`, under the `verify.database.transactions.disabled` 
property. Set the property to `true` to disable data transactions and `false` to
enable data transactions.

    verify.database.transactions.disabled=true
    
Finally, you can control the verification concurrency next. 

### Setting Verification concurrency [](id=setting-verification-concurrency)

Configure this threshold to indicate when to execute certain 
`com.liferay.portal.verify.VerifyProcess` tasks concurrently. Each 
`VerifyProcess` implementation governs what it may concurrently execute.

To configure the concurrency threshold, you must add it to your
`portal-ext.properties`, under the `verify.process.concurrency.threshold` 
property. The threshold property accepts numerical values, including zero:

    verify.process.concurrency.threshold=5

+$$$

**Note:** To migrate an existing verify process, follow the same steps covered 
in the previous sections:

  - Convert the class into a component, declaring the same properties as above
  - Reference any Liferay services the class uses, using the `@Reference`
  annotation.

$$$

There ya go. Now you know how to create a verification process for your app!

## Related Topics [](id=related-topics)

[Creating an Upgrade Process for Your App](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-application)

[Migrating a Liferay 6 Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-liferay-6-application)

[Application Configuration](/develop/tutorials/-/knowledge_base/7-0/application-configuration)
