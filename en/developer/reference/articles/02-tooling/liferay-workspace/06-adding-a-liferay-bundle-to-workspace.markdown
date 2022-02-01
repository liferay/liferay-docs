---
header-id: adding-a-liferay-bundle-to-liferay-workspace
---

# Adding a Liferay Bundle to Liferay Workspace

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="
https://learn.liferay.com/dxp/latest/en/building-applications/tooling/liferay-workspace/creating-code-with-liferay-workspace.html
">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Liferay Workspaces can generate and hold a Liferay Server. This lets you
build/test your workspace's plugins against a running Liferay instance. Follow
the instructions below to get started.

1.  Open your workspace's root `gradle.properties` file.

2.  Set the `liferay.workspace.bundle.url` property to the bundle's download URL
    you want to generate and install. For example,

    ```properties
    liferay.workspace.bundle.url=https://releases-cdn.liferay.com/portal/7.2.0-ga1/liferay-ce-portal-tomcat-7.2.0-ga1-20190531153709761.7z
    ```

    For DXP subscribers, it would look like this:

    ```properties
    liferay.workspace.bundle.url=https://api.liferay.com/downloads/portal/7.2.10/liferay-dxp-tomcat-7.2.10-ga1-20190531140450482.7z
    ```

    | **Note:** The DXP download URL must be set to the bundle hosted on
    | *api.liferay.com*. It can be tricky to find the fully qualified bundle
    | name/number for the DXP bundle you want. You cannot access Liferay's API
    | site directly to find it, so you must start to download DXP manually from
    | Liferay's Customer Portal, take note of the file name, and append it to
    | `https://api.liferay.com/downloads/portal/`.

    DXP subscribers must also set the `liferay.workspace.bundle.token.download`
    property to `true` to allow your workspace to access Liferay's API site.

3.  Navigate to your workspace's root folder and run

    ```bash
    blade server init
    ```

4.  Verify your bundle was downloaded. The bundle is generated in the `bundles`
    folder by default. You can change this by setting the `gradle.properties`
    file's `liferay.workspace.home.dir` property to a different folder. 

You can also produce a distributable Liferay bundle (Zip or Tar) from within a
workspace. To do this, navigate to your workspace's root folder and run the
following command:

```bash
./gradlew distBundle[Zip|Tar]
```

Your distribution file is available from the workspace's `/build` folder.

| **Note:** You can define different environments for your Liferay bundle for
| easy testing. You can learn more about this in the
| [Testing Projects](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace#testing-projects)
| section.

You're all set to develop projects for a nested @product@ bundle.
