# Upgrading Build Dependencies

<div class="learn-path-step">
    <p>Upgrading Build Dependencies<br>Step 1 of 1</p>
</div>

Now that your projects are readily available in a workspace, you must ensure
your project build dependencies are upgraded. Your workspace streamlines the
build dependency upgrade process by only requiring three modifications:

- [Update the repository URL](#updating-the-repository-url) (Gradle only)
- [Update the workspace plugin version](#updating-the-workspace-plugin-version)
- [Remove your project's build dependency versions](#removing-your-projects-build-dependency-versions)
  (Gradle only)

If you're upgrading a recently created workspace, only a subset of these tasks
may be required.

You'll start by updating the repository URL.

## Updating the Repository URL

Initiating this step in the Code Upgrade Planner updates the repository URL used
to download artifacts for your workspace.

If you're using a Gradle-based workspace, the repository URL is updated to point
to the latest Liferay CDN repository. This is set in your workspace's
`settings.gradle` file within the `buildscript` block like this:

```gradle
repositories {
    maven {
        url "https://repository-cdn.liferay.com/nexus/content/groups/public"
    }
}
```

Once the repository URL is set to the proper CDN repository, your build
dependencies will be downloaded from Liferay's own managed repo.

For Maven-based workspaces, Maven Central is the default repository, so no
action is required.

## Updating the Workspace Plugin Version

For the best upgrade experience, you should ensure you're leveraging the latest
Liferay Workspace version so all the latest features are available to you.
Initiate this step to upgrade the appropriate plugin.

See the
[Updating Liferay Workspace](/docs/reference/7-2/-/knowledge_base/r/updating-liferay-workspace)
article to do this for Gradle-based workspaces manually. For Maven-based
workspaces, make sure you set the latest
[Bundle Support plugin](/docs/reference/7-2/-/knowledge_base/r/bundle-support-plugin)
version in your root `pom.xml` file.

## Removing Your Project's Build Dependency Versions

| **Note:** This step only applies to Gradle-based workspaces since the target
| platform feature is only available for Gradle projects at this time.

Since your workspace is leveraging the target platform feature, there is no need
to set your plugin's dependency versions in its `build.gradle` file. This is
because the target platform version you set already defines the artifact
versions your project uses. Therefore, if dependency versions are present in
any of your projects' `build.gradle` files, you must remove them.

Initiate this step to remove your dependency versions from your project's
`build.gradle` file

As an example of what a `build.gradle`'s `dependencies` block should look like,
see the below snippet:

```gradle
dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
    compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
    compileOnly group: "javax.portlet", name: "portlet-api"
    compileOnly group: "javax.servlet", name: "javax.servlet-api"
    compileOnly group: "jstl", name: "jstl"
    compileOnly group: "org.osgi", name: "osgi.cmpn"
}
```

If you have not set the target platform feature in your workspace, see the
[Managing the Target Platform](/docs/reference/7-2/-/knowledge_base/r/managing-the-target-platform)
article for more information.

Great! You've successfully upgraded your build dependencies! You likely have
compile errors in your project; this is because your dependencies may have
changed. You'll learn how to update that and more next.
