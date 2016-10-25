# Patch types [](id=patch-types)

Liferay ships Digital Enterprise fixes through three different channels. Fix packs and hotfixes. You can read about the differences in this document.

## 1. Fix pack
On a regular schedule, the latest fixes that patch the core are bundled together
into fix packs, which are provided to all of Liferay's customers. Fix packs
include fixes for both the core and the application suites that ship with the
product. 

## 2. Hotfix
A hotfix is provided to a customer when they contact Liferay about an
emergency situation, and Liferay's support team--working with the customer--
determines that the problem is indeed an issue with the product that needs to be
fixed very quickly. Support fixes the bug and provides a hotfix to the customer
immediately. This is a short-term fix that solves the issue for the customer as
quickly as possible. Hotfixes can patch both the core and the application
suites.

## Testing methods of patches
We have extensive testing on both of our hotfixes and fix packs.

**Unit tests**

Unit tests are automated tests written by developers that test out the code at a very low level. They don't test functionality of the application, but they do test logical pathways through our code.

**Integration tests**

Integration tests are automated tests also written by developers that test out our web services available from our application. These services are defined to produce functionality within our system without a user interface.

**Functional tests**

Functional Tests are automated tests written by the QA Test Team that use a web browser to automate a user navigating around our portal and testing the functionality from a user standpoint.

The unit, integration, and functional tests are all run through our automated testing infrastructure.

**Manual Exploratory Functional tests**

Manual Exploratory Functional Tests are not defined in a test script, but rely on the QA Test Team to simulate user interactions with the portal to cover those areas of functionality that are not necessarily covered by other tests.


+$$$

**Note:** Regression bugs have higher fix priority alongside with the security fixes, therefore they are scheduled earlier into official fix packs. Because of that, it is always recommended to stay up to date with the latest fix packs.

$$$