# Patching basics [](id=patching-basics)

Liferay ships Digital Enterprise fixes through three different channels. Fix packs, hotfixes and service packs. You can read about the differences in this document.

## Patch types

### 1. Fix pack
On a regular schedule, the latest fixes that patch the core are bundled together
into fix packs, which are provided to all of Liferay's customers. Fix packs
include fixes for both the core and the application suites that ship with the
product. 

### 2. Hotfix
A hotfix is provided to a customer when they contact Liferay about an
emergency situation, and Liferay's support team--working with the customer--
determines that the problem is indeed an issue with the product that needs to be
fixed very quickly. Support fixes the bug and provides a hotfix to the customer
immediately. This is a short-term fix that solves the issue for the customer as
quickly as possible. Hotfixes can patch both the core and the application
suites.

### 3. Service Packs
Service packs for the @product@ are released about bi-monthly. The schedule depends on the need and security fix releases. They are designed to include the latest patches and updates for our customers who are just about to start their new projects. The service packs are built on the top of the original @product@ release and repackaged with the latest fix pack, Patching Tool and modules.

Instead of using the service packs to keep existing systems updated, we recommend existing customers to

1. keep their system up-to-date with fix packs (according to their own deployment schedule),
2. install the latest Marketplace updates frequently,
3. update the Patching Tool when it requires.

This method will get the installation to the service pack levels, however it would allow deployers to schedule according their timing and won't require full environment rebuilds. 

## Testing methods of patches
We have extensive testing on all 3 types of fix packs to ensure the quality. Fix packs are going through an automated regression testing system and in addition there's manual testing for each of the tickets fixed in the release. Hotfixes are going through a very similar automation testing procedure and the issue reported on the ticket is tested by the support engineer who worked on the ticket. Before releasing a service pack, we run our testing suites on the packaged releases to ensure the quality of the packaging.