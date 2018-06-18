# Patching Basics [](id=patching-basics)

Liferay ships @product-ver@ fixes through three different channels: 

- Fix packs 
- Hotfixes 
- Service Packs 

## Fix Packs [](id=fix-packs)

On a regular schedule, the latest fixes that patch the core are bundled together
into fix packs that are provided to all of Liferay's customers. Fix packs
include fixes for both the core and the applications and modules that ship with
@product@. 

## Hotfixes [](id=hotfixes)

A hotfix is provided to customers when they contact Liferay about an emergency
situation, and Liferay's support team---working with the customer---determines
that the problem is a product issue that needs to be fixed very quickly. Support
fixes the bug and provides a hotfix to the customer immediately. This is a
short-term fix. Hotfixes can patch both the core and the applications and
modules. 

## Service Packs [](id=service-packs)

Service packs for the @product@ are released about every two months. The
schedule depends on the need and security fix releases. They include the latest
patches and updates---they're for customers starting new projects. The service
packs are built on the top of the original @product@ release and repackaged with
the latest fix pack, Patching Tool, and modules.

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
