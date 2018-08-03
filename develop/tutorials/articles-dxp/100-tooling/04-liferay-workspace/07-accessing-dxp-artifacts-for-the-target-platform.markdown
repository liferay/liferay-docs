# Accessing DXP Artifacts for the Target Platform

When targeting a Liferay DXP versioned platform, you must provide your
credentials to gain access to DXP-only artifacts. To do this, follow the steps
below:

1.  Open your workspace's `settings.gradle` file and update the `maven` block to
    include your credentials and a new URL:

        repositories {
            maven {
                credentials {
                    username "liferayUser"
                    password "liferayPassword"
                }
                url "https://repository.liferay.com/nexus/service/local/repositories/liferay-private-releases/content/"
            }
        }

2.  Store your username and password in the `~/.gradle/gradle.properties` file:

        liferayUser=USER_NAME
        password=PASSWORD

Excellent! Now your workspace can generate a BOM file and static distro JAR for
your DXP bundle.