# Creating An Upgrade Process for Your Application

## Upgrade Processes Development Best Practices

(See
[https://issues.liferay.com/browse/LRDOCS-2053](https://www.google.com/url?q=https://issues.liferay.com/browse/LRDOCS-2053&sa=D&ust=1464278927761000&usg=AFQjCNHwpXeleci53x7rSg7Ex3eaWOBHEA))

When should I use the SQL file or a Java class? {#h.ci4pqw5vttn2 .c7}
-----------------------------------------------

### Using update-FROM\_RELEASE-TO\_RELEASE.sql file {#h.57x514j6574i .c7}

If you want to execute DDL sentences, to create, modify or drop tables
or indexes, only use this file if your SQL uses ANSI SQL, which means
that can be ported to different databases without any modification.

### Using Java classes {#h.mxreuqcgmvxu .c7}

Whenever you need to use non-ANSI SQL, it’s recommended to use the
runSQL methods in a Java class representing the upgrade process, using
the specific tokens that allows porting the sentences to different
databases.

For instance, to rename a table column, ALTER\_COLUM\_NAME; to change
column type, ALTER\_COLUMN\_TYPE.

Please see
[https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portal/dao/db/BaseDB.java](https://www.google.com/url?q=https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portal/dao/db/BaseDB.java&sa=D&ust=1464278927763000&usg=AFQjCNHqCkMuDGktgleJT0lKg04beYHCNQ) 

Creating an upgrade process with the new framework {#h.syjdqrfgmvmg .c7}
--------------------------------------------------

We want to start from the scratch to create a new upgrade process.

### Dependencies {#h.op4rqgrtxwan .c7}

You have to declare the dependency on portal-upgrade module in your
gradle build file:

+--------------------------------------------------------------------------+
| compile project(":portal:portal-upgrade")                                |
+--------------------------------------------------------------------------+

If your upgrade depends on some Upgrade that it’s still located in
portal-impl, you have to add a dependency on portal-impl too:

+--------------------------------------------------------------------------+
| compile group: "com.liferay.portal", name: "portal-impl", version:       |
| liferay.portalVersion                                                    |
+--------------------------------------------------------------------------+

This situations occurs when your upgrade process extends
UpgradePortletId.

### New code {#h.8kygm1d7et48 .c7}

Well, we need to create a package called upgrade in your project’s
layout, and there you create Java class that implements the interface
UpgradeStepRegistrator, which is located under the portal-upgrade
module (See
[https://github.com/liferay/liferay-portal/tree/master/modules/portal/portal-upgrade](https://www.google.com/url?q=https://github.com/liferay/liferay-portal/tree/master/modules/portal/portal-upgrade&sa=D&ust=1464278927768000&usg=AFQjCNGF0wzU7e50p5GOwiXbT7XQ1OJ1JQ)).
This interface will provide a register method that will do all the work
when registering the upgrades.

You’ll have to use its register method to specify as many upgrades you
have, defining from which versions you want to upgrade, to which version
you want to upgrade.

With the new framework, each upgrade will be represented by an upgrade
registration. An Upgrade registration is an abstraction for the changes
you need to apply to the database from one version to the next one.

To fill in a registration, you need to provide: the name of the bundle
representing the module, the version your module wants to upgrade from
(as a String), the version your module wants to upgrade to (as a
String), and a list of UpgradeSteps.

This would be how an upgrade process with new framework looks like:

+--------------------------------------------------------------------------+
| @Override                                                                |
|                                                                          |
| public void register(Registry registry) {                                |
|                                                                          |
|         registry.register(                                               |
|                                                                          |
|                 "com.liferay.microblogs.service", "0.0.1", "1.0.0",      |
|                                                                          |
|                 new                                                      |
| com.liferay.microblogs.upgrade.v1\_0\_0.UpgradeMicroblogsEntry());       |
|                                                                          |
| }                                                                        |
+--------------------------------------------------------------------------+

In this example, we are upgrading the “com.liferay.microblogs.service”
bundle, from version 0.0.1 to version 1.0.0. And the changes will be
produced by a list of UpgradeSteps with only one step in it.

The cool thing is that you can register as many upgrades as you need in
the same place:

+--------------------------------------------------------------------------+
| @Override                                                                |
|                                                                          |
| public void register(Registry registry) {                                |
|                                                                          |
|         registry.register(                                               |
|                                                                          |
|                 "com.liferay.microblogs.service", "0.0.1", "1.0.0",      |
|                                                                          |
|                 new                                                      |
| com.liferay.microblogs.upgrade.v1\_0\_0.UpgradeMicroblogsEntry());       |
|                                                                          |
|         registry.register(                                               |
|                                                                          |
|                 "com.liferay.microblogs.service", "1.0.0", "1.0.1",      |
|                                                                          |
|                 new UpgradeUserNotificationEvent());                     |
|                                                                          |
|         registry.register(                                               |
|                                                                          |
|                 "com.liferay.microblogs.service", "1.0.1", "1.0.2",      |
|                                                                          |
|                 new                                                      |
| com.liferay.microblogs.upgrade.v1\_0\_2.UpgradeMicroblogsEntry(),        |
|                                                                          |
|                 new UpgradeSocial());                                    |
|                                                                          |
| }                                                                        |
+--------------------------------------------------------------------------+

In this example, which is an extension of previous one, we are also
upgrading the “com.liferay.microblogs.service” bundle, from version1.0.0
to 1.0.1 using a list of UpgradeSteps with only one step in it, and
again upgrading it from version 1.0.1 to version 1.0.2, using a list of
UpgradeSteps with two steps on it, UpgradeMicroblogsEntry and
UpgradeSocial, both in v1\_0\_2 package!.

NOTE: be careful with upgrade steps with same name in different
packages, so you use the right version on each upgrade.

The result of executing this upgrade in the database will be the
creation of three rows in the Release\_ table, one per upgrade
registration.

### Module initialization {#h.ocyyrxgdh7lh .c7}

One important thing is that upgrades need to wait for portal
initialization to perform their tasks.

You can achieve it by adding this setter method, with an OSGi reference
to the portal life cycle.

+--------------------------------------------------------------------------+
| @Reference(target = ModuleServiceLifecycle.PORTAL\_INITIALIZED, unbind = |
| "-")                                                                     |
|                                                                          |
| protected void setModuleServiceLifecycle(                                |
|                                                                          |
|         ModuleServiceLifecycle moduleServiceLifecycle) {                 |
|                                                                          |
| }                                                                        |
+--------------------------------------------------------------------------+

It means that this module, the one for the upgrade, won’t be available
until the portal has started.

### Schema Version {#h.5urdrpuhs0f7 .c7}

If your database schema is in a different version than bundle version,
you can specify it in the BND file. Add the following properties to
describe that the schema version of the module uses a different version
than the bundle version.

+--------------------------------------------------------------------------+
| Require-SchemaVersion: 1.0.2                                             |
+--------------------------------------------------------------------------+

### Data representation {#h.tv2r4593u5hj .c7}

In terms of data, each registration will be represented by a row in the
Release\_ table on portal’s database, so you can check there which
upgrades have been executed for each module.

Migrating an existing upgrade process to the new framework {#h.32r45r8xtk86 .c7}
----------------------------------------------------------

### What we had is... {#h.qr0ncqy9bo1i .c7}

In this case, you start from a class that wraps the execution of several
UpgradeProcesses representing the different upgrades for a specific
version of your module.

For instance, Microblogs-service module needed to upgrade from v1.0.0 to
1.0.1, and then to 1.0.2. Following code represents how it was done in
the past:

+--------------------------------------------------------------------------+
| @Activate                                                                |
|                                                                          |
| protected void upgrade() throws PortalException {                        |
|                                                                          |
|         List\<UpgradeProcess\> upgradeProcesses = new ArrayList\<\>(1);  |
|                                                                          |
|         upgradeProcesses.add(new MicroblogsServiceUpgrade\_1\_0\_0());   |
|                                                                          |
|         upgradeProcesses.add(new MicroblogsServiceUpgrade\_1\_0\_1());   |
|                                                                          |
|         upgradeProcesses.add(new MicroblogsServiceUpgrade\_1\_0\_2());   |
|                                                                          |
|         for (UpgradeProcess upgradeProcess : upgradeProcesses) {         |
|                                                                          |
|                 if (\_log.isDebugEnabled()) {                            |
|                                                                          |
|                         \_log.debug("Upgrade process " +                 |
| upgradeProcess);                                                         |
|                                                                          |
|                 }                                                        |
|                                                                          |
|         }                                                                |
|                                                                          |
| }                                                                        |
+--------------------------------------------------------------------------+

Each step between versions was represented by a single class extending
UpgradeProcess, with a method doUpgrade. This method is responsible of
executing the internal steps to update the database to that concrete
version.

Following example represents the required operations to update the
database to v1.0.0.

+--------------------------------------------------------------------------+
| public class MicroblogsServiceUpgrade\_1\_0\_0 extends UpgradeProcess {  |
|                                                                          |
|         @Override                                                        |
|                                                                          |
|         protected void doUpgrade() throws Exception {                    |
|                                                                          |
|                 upgrade(new UpgradeMicroblogsEntry());                   |
|                                                                          |
|                 upgrade(new UpgradeFoo());                               |
|                                                                          |
|         }                                                                |
|                                                                          |
| }                                                                        |
+--------------------------------------------------------------------------+

Whenever you needed another internal step, you added another upgrade(new
UpgradeBar()); after existing ones.

### Ok, let’s move it to the new Upgrade framework {#h.rk23y8a50c5m .c7}

First, update the build files. The build.gradle file has to contain a
dependency on portal-upgrade module, and perhaps another one to
portal-impl if any of your upgrade extends UpgradePortletIds.

For the BND file, check if your database schema is in a different
version than bundle version, and if so, specify different versions for
the bundle and the schema. If no Required-SchemaVersion is found, the
Bundle-Version header will be used.

Second, our initial upgrade class holding the steps to upgrade for each
version, needs to implement the interface UpgradeStepRegistrator. As you
already know, this interface will provide a register method that will do
all the work when registering the upgrades.

Third, we won’t need the intermediate classes that wrapped the internal
steps, i.e the MicroblogsServiceUpgrade\_1\_0\_0.java. With the new
framework, previous type of classes will be represented by the upgrade
registration we explained before.

For the internal steps defined within intermediate classes -the former
UpgradeProcess class, as they are indeed UpgradeSteps, you don’t need to
change them at any point, the new framework will properly use it.

Besides, you don’t need to declare any reference to the Release service,
via OSGi component or a direct call to the ReleaseLocalServiceUtil. The
framework will perform these tasks for you.

So please remove this code:

+--------------------------------------------------------------------------+
| @Reference(unbind = "-")                                                 |
|                                                                          |
| protected void setReleaseLocalService(                                   |
|                                                                          |
|         ReleaseLocalService releaseLocalService) {                       |
|                                                                          |
|         \_releaseLocalService = releaseLocalService;                     |
|                                                                          |
| }                                                                        |
+--------------------------------------------------------------------------+

And this code:

+--------------------------------------------------------------------------+
| private static final Log \_log = LogFactoryUtil.getLog(                  |
|                                                                          |
|         MicroblogsServiceUpgrade.class);                                 |
+--------------------------------------------------------------------------+

Relating to module startup, remember to wait for portal initialization,
using the @Reference annotation described in the “[Module
Initialization.](#h.ocyyrxgdh7lh)” section under “[Creating an upgrade
process](#h.syjdqrfgmvmg)”.

Following is a summarizing table with all changes you must do in your
existing upgrade:

+-------------------------+-------------------------+-------------------------+
| Gradle Build file       | →                       | -   Add portal-upgrade  |
|                         |                         |     dependency.         |
|                         |                         | -   Check if your       |
|                         |                         |     module depends on   |
|                         |                         |     portal-impl         |
+-------------------------+-------------------------+-------------------------+
| MyCustomServiceUpgrade. | →                       | -   Although it is      |
| java                    |                         |     still a component,  |
|                         |                         |     it’s not needed to  |
|                         |                         |     define the          |
|                         |                         |     service/interface   |
|                         |                         |     using declarative   |
|                         |                         |     services.           |
|                         |                         | -   Implements          |
|                         |                         |     UpgradeStepRegistra |
|                         |                         | tor.                    |
|                         |                         | -   Waits for portal    |
|                         |                         |     initialization.     |
|                         |                         | -   It has no loggers.  |
|                         |                         | -   It has no reference |
|                         |                         |     to Release service. |
+-------------------------+-------------------------+-------------------------+
| ReleaseLocalService     | →                       | Remove it               |
| reference               |                         |                         |
+-------------------------+-------------------------+-------------------------+
| Logger reference        | →                       | Remove it               |
+-------------------------+-------------------------+-------------------------+
| MyCustomServiceUpgrade\ | →                       | Disappears. It is a     |
| _1\_0\_0.java           |                         | registration on the     |
|                         |                         | UpgradeStepRegistrator: |
|                         |                         |                         |
|                         |                         | registry.register(      |
|                         |                         |                         |
|                         |                         |     “bundle.name”,      |
|                         |                         | “fromV”, “1.0.0”, list) |
+-------------------------+-------------------------+-------------------------+
| package.v1\_0\_0.Upgrad | →                       | No changes, it’s        |
| eFoo.java               |                         | already an UpgradeStep  |
+-------------------------+-------------------------+-------------------------+
| package.v1\_0\_0.Upgrad | →                       | No changes. Passed as   |
| eBar.java               |                         | parameter in the list   |
|                         |                         | of UpgradeSteps for the |
|                         |                         | registration.           |
+-------------------------+-------------------------+-------------------------+
| BND file                | →                       | Add                     |
|                         |                         | Require-SchemaVersion   |
|                         |                         | if needed.              |
+-------------------------+-------------------------+-------------------------+

[[a]](#cmnt_ref1)We've improved the tool
https://github.com/brianchandotcom/liferay-portal/pull/32709, I will
update the docs accordingly once this gets merged

[[b]](#cmnt_ref2)+jesse.rao@liferay.com I had trouble following these
instructions today. The \*.jar bits and OSGi paths need to be removed,
and the entries need to be separated by commas not colons in order for
the shell script to iterate over the entries and expand them into
individual JAR paths.

export
LIFERAY\_CLASSPATH=\$TOMCAT\_DIR/lib,\$TOMCAT\_DIR/lib/ext,\$TOMCAT\_DIR/webapps/ROOT/WEB-INF/lib

I've already verified this with Miguel

[[c]](#cmnt_ref3)Thanks Stian. Sent
https://github.com/sez11a/liferay-docs/pull/1526

[[d]](#cmnt_ref4)Hey +david.truong@liferay.com this is assuming that you
have Liferay's source code with the build files. Should we also include
the steps for running the upgrade process when you have a binary
distribution?

[[e]](#cmnt_ref5)+david.truong@liferay.com For the documenation, I
definitely think we should include the steps for running the upgrade
process for a binary distribution.

[[f]](#cmnt_ref6)This one wasn't mentioned here:

https://dev.liferay.com/discover/deployment/-/knowledge\_base/7-0/upgrading-to-liferay-7

[[g]](#cmnt_ref7)+zoltan.takacs@liferay.com I wasn't able to get an
upgrade to run successfully with that one. I would run into a
filename/extension too long error (I'm on windows). But it works if I
follow the documentation on dev.liferay.com

[[h]](#cmnt_ref8)+brian.chiu@liferay.com The same for me. (Win10)

[[i]](#cmnt_ref9)I was curious about this line, why it's added here?

[[j]](#cmnt_ref10)In the 6.2 upgrade documentation
(https://dev.liferay.com/discover/deployment/-/knowledge\_base/6-2/upgrading-liferay),
we mention the "seamless upgrade" feature that allows Liferay to "skip"
versions during an upgrade. Will we support upgrades from, say, 6.1 to
7.0? Or should we tell users to first upgrade to 6.2, and then to 7.0?

[[k]](#cmnt_ref11)We support straigh upgrades from 6.0/6.1 to 7.0.

[[l]](#cmnt_ref12)This is not totally true until this issue is
completed:

https://issues.liferay.com/browse/LPS-60808

[[m]](#cmnt_ref13)This paragraph is not completely clear to me. I think
it would help if you could list the steps in order. Also, when I run the
command on the bookmarks service module, I get 5 steps:

0.0.0 to 1.0.0

0.0.1 to 1.0.0-step-3

1.0.0-step-1 to 1.0.0

1.0.0-step-2 to 1.0.0-step-1

1.0.0-step-3 to 1.0.0-step-2

What's the correct order for these?

[[n]](#cmnt_ref14)That list shows all upgrade steps registered for a
module.

The step

0.0.0 to 1.0.0

it's for the case when you come from an empty database.

If you come from an existing database (where the bookmarks tables were
created), for example from 6.2. This is the order fro that case:

0.0.1 to 1.0.0-step-3

1.0.0-step-3 to 1.0.0-step-2

1.0.0-step-2 to 1.0.0-step-1

1.0.0-step-1 to 1.0.0

[[o]](#cmnt_ref15)+miguel.pastor@liferay.com, +carlos.sierra@liferay.com
does it make sense to create two new features?:

1- Show steps in order

2- Just show the steps we have fullfilled for every specifi case? For
example, for new databases just show:

0.0.0 to 1.0.0

For upgrades:

0.0.1 to 1.0.0-step-3

1.0.0-step-3 to 1.0.0-step-2

1.0.0-step-2 to 1.0.0-step-1

1.0.0-step-1 to 1.0.0

Thanks!

[[p]](#cmnt_ref16)Thanks Alberto! Sent
https://github.com/sez11a/liferay-docs/pull/1505
