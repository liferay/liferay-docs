# Creating A Verify Process for Your App [](id=creating-a-verify-process-for-your-app)

Your app goes through various stages of development. You add new features,
remove features, improve features, reorganize the code, etc. During the cycle of
development, you'll want to verify the data your app is producing is consistent.

This tutorial demonstrates how to:

- Create a verify process for your app using new development patterns
- Migrate an existing verify process to the new development patterns

## Creating a verify process

This very first thing you must do is to create a class that extends the abstract
base class **com.liferay.portal.verify.VerifyProcess**. It will force you to
define a **doVerify** method of return type void. There you'll add you verify
logic.

    @Override
    protected void doVerify() throws Exception {
      verifyABC();
      verifyXYZ();
    }

Besides, you must declare the class a **component**, so that it uses the OSGi
runtime to define its lifecycle. We'll achieve it using the **@Component** annotation:

    @Component(
      immediate = true,
      property = {"verify.process.name=my.app.service"},
      service = VerifyProcess.class
    )

You must use **VerifyProcess.class** as the **service** property value, to
denote that the class is a valid implementation for the VerifyProcess interface.

Defining the component as **immediate**, we are saying that the component will
be available inmediately, not the first time it's used.

We also must define the
mandatory **verify.process.name** property, which is used by the OSGi service
tracker for verifiers to capture verifier components (see [VerifyProcessTracker.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTracker.java#L152-L153)) and execute them (see [VerifyProcessTracker.java](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal/portal-verify-extender/src/main/java/com/liferay/portal/verify/extender/internal/VerifyProcessTracker.java#L344-L361)).

The recommended value for that property is using the name of the service package
of the app, but it could be another different.

## Depending on services

As a verify process executes on portal startup of an specific release, let's
name it the *target release*, the code it executes will be at the target
release. This is important when you come from an upgrade process, because your
database would start in a *previous release*, would be upgraded to the target
release, and then release verify processes would eventually be executed on
portal startup.

Why is this important? Because a verify process can use release Liferay
services, and those Liferay services use the release database schema, which is
different from the previous one.

So this is an important distiction between upgrade processes and verify
processes. An upgrade process of the portal core (not of the modules) cannot use
Liferay services to access the database, as those Liferay service are using the
target definition of the database. But verify process in fact use them, as they
are already in the release version.

So, as you can use Liferay services, you can declare attributes holding
references to them. But, as we are in a new modularised OSGi world, those
services could not be operative yet. So **your verify process must wait for
them**. And how do you wait for an eventually operative service in OSGi? Using
the **@Reference** annotation.

    @Reference(unbind = "-")
    protected void setDLFileVersionLocalService(
      DLFileVersionLocalService dlFileVersionLocalService) {

      _dlFileVersionLocalService = dlFileVersionLocalService;
    }
    ...
    private DLFileVersionLocalService _dlFileVersionLocalService;

This way we are telling the verify process that the OSGi framework will provide
the verify process a valid implementation of the proper service (in the example,
a DLFileVersionLocalService), and this will happen at a not-known moment,
whenever some other module provides that implementation, so the verify process
must wait until then.

As you are wondering, we are not using the ServiceUtil classes anymore. We are
delegating on the OSGi framework to wire the real implementations for the
Liferay services, so we directly use the components.
