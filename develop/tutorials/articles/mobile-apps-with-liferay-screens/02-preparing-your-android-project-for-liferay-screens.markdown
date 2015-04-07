# Preparing Your Android Project for Liferay Screens

Your Android project must be set up for use with Liferay Screens. This consists 
of installing Screens in your project and then configuring its dependencies. 
Liferay Screens is released as an [AAR file](http://tools.android.com/tech-docs/new-build-system/aar-format) 
that is currently hosted in jCenter. This tutorial shows you how to use Gradle 
or Maven to install and configure Screens in your Android project. While manual 
setup instructions are also provided, it's strongly recommended that you use 
Gradle or Maven. Let the configuration begin! 

## Using Gradle to Configure Your Project [](id=using-gradle-to-configure-your-project)

Use the following steps to configure your project with Gradle:

1. Add jCenter to your repositories:

        repositories {
            jcenter()
        }

2. Include the SDK as a dependency:

        dependencies {
            compile 'com.liferay.mobile:liferay-screens:0.3.0'
        }

Gradle downloads all the necessary dependencies before building your project. If 
you get errors such as `Duplicate files copied in APK META-INF/NOTICE` when 
building with Gradle, add this to your `build.gradle` file:

    android {
        ...
        packagingOptions {	
            exclude 'META-INF/LICENSE'
            exclude 'META-INF/NOTICE'
        }
        ...
    }
    
Next, the steps for configuring your project with Maven are shown.

## Using Maven to Configure Your Project [](id=using-maven-to-configure-your-project)

Use the following steps to configure your project with Maven:

1. Add the following dependency to your `pom.xml`:

        <dependency>
            <groupId>com.liferay.mobile</groupId>
            <artifactId>liferay-screens</artifactId>
            <version>LATEST</version>
        </dependency>

2. Force a Maven update to download all the dependencies.

Note that if Maven can't locate the artifact, you need to add jCenter as a new 
repository in your maven settings (`.m2/settings.xml`):

    <profiles>
        <profile>
            <repositories>
                <repository>
                    <id>bintray-liferay-liferay-mobile</id>
                    <name>bintray</name>
                    <url>http://dl.bintray.com/liferay/liferay-mobile</url>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>bintray-liferay-liferay-mobile</id>
                    <name>bintray-plugins</name>
                    <url>http://dl.bintray.com/liferay/liferay-mobile</url>
                </pluginRepository>
            </pluginRepositories>
	    <id>bintray</id>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>bintray</activeProfile>
    </activeProfiles>

Even though using Gradle or Maven to fully configure your project is strongly 
recommended, the manual configuration steps are shown next. You should note that 
the manual configuration still requires that you use Gradle to point to the 
manually installed components.

## Configuring Your Project Manually [](id=configuring-your-project-manually)

If you're using Gradle, you can use the following steps to manually install 
Screens and its dependencies: 

1. [Download](https://github.com/liferay/liferay-screens/releases) the latest 
   version of Liferay Screens for Android.

2. Copy the contents of `Android/library` in a folder next to your project.

3. Configure a `settings.gradle` file in your project with the paths to the 
   library folders:

        include ':core'
        include ':themes'
        project(':core').projectDir = new File(settingsDir, '../../library/core')
        project(':themes').projectDir = new File(settingsDir, '../../library/themes')

4. Include the required dependencies in your `build.gradle` file: 

        compile project (':core')
        compile project (':themes')

You can also configure the `.aar` binary files (in `Android/dist`) as local 
`.aar` dependencies.

Super! Your Android project should now be ready for Liferay Screens.

## Related Topics [](id=related-topics)

[Using Screenlets](/tutorials/-/knowledge_base/6-2/using-screenlets)

[Using Views and Themes](/tutorials/-/knowledge_base/6-2/using-views-and-themes)

[Architecture of Liferay Screens](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens)
