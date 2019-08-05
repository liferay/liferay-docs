---
header-id: installing-local-liferay-maven-artifacts
---

# Installing Local Liferay Maven Artifacts

[TOC levels=1-4]

Liferay offers a utility that lets you download all of Liferay's artifacts and
install them to a Maven repository of your choice.

1.  Navigate to the
    [Downloads](https://customer.liferay.com/group/customer/downloads) page in
    Liferay's Help Center.

2.  Select *DXP 7.2* for the Product and *Product/Service Packs* for the File
    Type.

3.  For the *Liferay DXP 7.2* product type, select *Maven* from the drop-down
    menu. This downloads the `liferay-ce-portal-maven-[version].zip` Maven Zip.

    <!--[Figure 1: After selecting the Liferay version, select the Liferay Portal Maven Zip file to download.](../../../images/maven-select-download.png)-->

3.  Unzip the file and navigate to it at the command line. Run the Ant command
    without a target.

        ant

    The messages in your terminal window show Liferay's artifacts being
    installed. The artifacts are downloaded from Liferay's Nexus repository.

    | **Note:** By default, all of Liferay's Maven artifacts are installed in
    | your `[USER_HOME]/.m2` repository. You can change this location by
    | creating a `build.[USER_NAME}.properties` file in the Liferay Portal Maven
    | folder and setting the `local.repository.path` property to the location of
    | your choice.

Now you have all of Liferay's Maven artifacts locally and can delegate them to
any repository or folder you choose.

Congratulations! You've downloaded the Liferay artifacts and installed them to
your chosen repository.
