# Accessing DXP Artifacts for the Target Platform [](id=accessing-dxp-artifacts-for-the-target-platform)

When targeting a Liferay DXP versioned platform, you must provide your
credentials to gain access to DXP-only artifacts. Storing your password in plain
text is dangerous and should be avoided. You can leverage the
[`gradle-credentials-plugin`](https://github.com/etiennestuder/gradle-credentials-plugin)
to store and encrypt your credentials.

Follow the steps below to safely access DXP artifacts:

1.  Apply the `gradle-credentials-plugin` to your workspace by adding the
    following block in your workspace's root `build.gradle` file:

        buildscript {
            dependencies {
                classpath 'nu.studer:gradle-credentials-plugin:1.0.4'
            }

            repositories {
                jcenter()
            }
        }

2.  Create your encrypted username and password:

        ../gradlew addCredentials --key liferayNexusUsername --value YOUR_USERNAME

    and

        ../gradlew addCredentials --key liferayNexusPassword --value YOUR_PASSWORD

    These values are stored in your `~/.gradle/gradle.encrypted.properties`
    file.

3.  Add the following `allprojects` block to the end of your workspace's
    `build.gradle` file:

        allprojects {
            apply plugin: 'nu.studer.credentials'

            String nexusUsername = credentials.liferayNexusUsername
            String nexusPassword = credentials.liferayNexusPassword

            repositories {
                maven {
                    credentials {
                        username nexusUsername
                        password nexusPassword
                    }

                    url "https://repository-cdn.liferay.com/nexus/service/local/repositories/liferay-private-releases/content/"
                }
            }
        }

    This applies the `gradle-credentials-plugin` to all your workspace's
    projects. Then your encrypted username and password are retrieved using the
    `credentials` container. Finally, Liferay's private release repository is
    set and accessed with your Liferay username and password.

Excellent! Now your workspace can generate a BOM file and static distro JAR for
your DXP bundle.
