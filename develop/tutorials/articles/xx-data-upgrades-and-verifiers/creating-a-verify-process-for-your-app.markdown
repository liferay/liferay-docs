# Creating A Verify Process for Your App [](id=creating-a-verify-process-for-your-app)

Your app goes through various stages of development. You add new features,
remove features, improve features, reorganize the code, etc. During the cycle of
development, you'll want to verify the data your app is producing is consistent.

This tutorial demonstrates how to:

- Create a verify process for your app using new development patterns
- Migrate an existing verify process to the new development patterns

Get started by writing your verify package next.

## Writing the Verify Package

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
rules app verify process shown above.

Now that your verify process class is written, you can learn how to declare
dependencies on Liferay services for your verify processes next.

## Depending on services

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

Now that you know how to use Liferay services in your verifiers, you can learn
how to migrate an existing verify process to the new framework.

## Migrating an existing verify process

To migrate an existing verify process, follow the steps covered in the previous
section:

  - Convert the class into a component, declaring the same properties as above
  - Reference any Liferay services the class uses, using the `@Reference`
  annotation.

There ya go!
