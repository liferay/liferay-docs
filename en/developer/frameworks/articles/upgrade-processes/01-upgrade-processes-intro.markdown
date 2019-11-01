---
header-id: upgrade-processes
---

# Upgrade Processes

[TOC levels=1-4]

The development process doesn't end when you first release your application. 
Through your own planning, feature requests, and bug reports, developers improve 
their applications on a regular basis. Sometimes, those changes result in 
changes to the data structure and underlying database. When users upgrade, they 
need a process that transitions them to improved versions of your application. 
For this, you must create an upgrade process.  

Here's what's involved in creating an upgrade process for your app:

- Specifying the schema version
- Declaring dependencies
- Writing upgrade steps
- Writing the registrator
- Waiting for upgrade completion

Liferay has an Upgrade framework you can use to make this easier to do. It's a 
feature-rich framework that makes upgrades safe: the system records the current 
state of the schema so that if the upgrade fails, the process can revert the 
module back to its previous version. [Meaningful schema versioning](/docs/7-2/reference/-/knowledge_base/r/meaningful-schema-versioning) 
is important to clearly communicate the updates to your users. 

@product@'s Upgrade framework executes your module's upgrades automatically when 
the new version starts for the first time. You implement concrete data schema 
changes in upgrade step classes and then register them with the upgrade 
framework using an *upgrade step* registrator. An upgrade step is a class that 
adapts module data to the module's target database schema. It can execute SQL 
commands and DDL files to upgrade the data. The Upgrade framework lets you 
encapsulate upgrade logic in multiple upgrade step classes per schema version. 

The Upgrade framework executes the upgrade steps to update the current module 
data to the latest schema. The registrator's `register` method informs the 
Upgrade framework about each new schema and associated upgrade steps to adapt 
data to it. Each schema upgrade is represented by a *registration*. A 
registration is an abstraction for all the changes you need to apply to the 
database from one schema version to the next one. 

Upgrade registrations are defined by the following values:

- **Module's bundle symbolic name**
- **Schema version to upgrade from** (as a `String`)
- **Schema version to upgrade to** (as a `String`)
- **List of upgrade steps**

A registration's upgrade step list can consist of as many upgrade steps as 
needed. How you name and organize upgrade steps is up to you. Liferay's upgrade 
classes are organized using a package structure similar to this one:

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
and `v2_0_0`. Each version package contains upgrade step classes that update the 
database. The example upgrade steps focus on fictitious data elements `Foo` and 
`Bar`. The registrator class (`MyCustomModuleUpgrade`, in this example) is 
responsible for registering the applicable upgrade steps for each schema 
version. 

Here are some organizational tips:

-   Put all upgrade classes in a sub-package called `upgrade`.

-   Group together similar database updates (ones that operate on a data element
    or related data elements) in the same upgrade step class.

-   Create upgrade steps in sub-packages named after each data schema version. 

The diagram below illustrates the relationship between the registrator and the 
upgrade steps. 

![Figure 1: In a registrator class, the developer specifies a registration for each schema version upgrade. The upgrade steps handle the database updates.](../../images/data-upgrade-module-upgrade-architecture.png)

This section covers these topics:

- [Creating an upgrade process for your app](/docs/7-2/frameworks/-/knowledge_base/f/creating-an-upgrade-process-for-your-app)
- [Creating upgrade processes for former service builder plugins](/docs/7-2/frameworks/-/knowledge_base/f/upgrade-processes-for-former-service-builder-plugins)
- [Upgrading data schemas in development](/docs/7-2/frameworks/-/knowledge_base/f/upgrading-data-schemas-in-development)
