# Skipping the Resolving Process for a Module

It may be easiest to skip validating a particular module during the resolve
process.

1.  Open your workspace's root `build.gradle` file.

2.  Insert the following Gradle code at the bottom of the file:

    ```groovy
    targetPlatform {
        resolveOnlyIf { project ->
            project.name != 'PROJECT_NAME'
        }
    }
    ```

    Be sure to replace the `PROJECT_NAME` filler with your module's name (e.g.,
    `test-api`).

3.  (Optional) If you prefer to disable the Target Platform plugin altogether,
    you can add a slightly different directive to your `build.gradle` file:

    ```groovy
    targetPlatform {
        onlyIf { project ->
            project.name != 'PROJECT_NAME'
        }
    }
    ```

    This both skips the `resolve` task execution and disables BOM dependency
    management. 

Now the `resolve` task skips your module project.
