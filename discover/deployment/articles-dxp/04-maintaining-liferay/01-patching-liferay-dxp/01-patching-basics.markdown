# Patching Basics [](id=patching-basics)

Liferay ships @product-ver@ fixes through three different channels: 

- Fix packs 
- Hotfixes 
- Service Packs 

## Fix Packs [](id=fix-packs)

The latest fixes that patch the core are bundled together weekly into fix packs
that are provided to all of Liferay's customers. Fix packs include fixes for
both the core and the applications and modules that ship with @product@. The
fixes address regressions or obvious bugs and don't require you to make
additional changes. 

Fixes that don't fit these requirements are considered for service packs or hot
fixes. 

## Hotfixes [](id=hotfixes)

A hotfix is provided to customers when they contact Liferay about an emergency
situation, and Liferay's support team---working with the customer---determines
that the problem is a product issue that must be fixed very quickly. Support
fixes the bug and provides a hotfix to the customer immediately. This is
a short-term fix. Hotfixes can patch the core, the applications, and modules. 

## Service Packs [](id=service-packs)

Every tenth fix pack is a service pack, so their frequency varies. Their version
numbers always end in a zero (e.g., `10`, `20`, etc.). Service packs are built
on top of the original @product@ release and repackaged with the latest fix
pack, Patching Tool, and modules. They include the latest patches and updates.
If starting a new project, always start with the latest service pack. 

Service packs can also include changes that have these characteristics:

- Require more extensive testing. 
- Require some of your attention, such as updating your documentation.
- Improve the product. 

Rather than updating existing @product@ systems with service packs, you should

1.  Keep systems up-to-date with fix packs (according to your own deployment
    schedule).

2.  Install the latest Marketplace updates frequently.

3.  Update the Patching Tool when necessary.

This method updates the installation to the service pack levels, while allowing
scheduled deployments and avoiding full environment rebuilds. 

## How Patches are Tested [](id=how-patches-are-tested)

Liferay extensively tests service packs, fix packs, and hotfixes to ensure high
quality. Fixes in fix packs go through both automated regression testing and
manual testing. Hotfixes receive similar automated testing, and the support
engineer who fixes a reported issue tests it. 

Before releasing a service pack, Liferay runs test suites on the packaged
service pack. 
