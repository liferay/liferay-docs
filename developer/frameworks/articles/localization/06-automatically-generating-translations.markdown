---
header-id: automatically-generating-translations
---

# Automatically Generating Translations

[TOC levels=1-4]

If your app uses a 
[language module](/docs/7-2/frameworks/-/knowledge_base/f/using-a-language-module) or
[`Language.properties`
file](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application) for
its user interface messages, you're in the right place. Language Builder
provides these localization capabilities:

-  Generating language files for each supported locale with a single
   command. It also propagates new default language file keys to all
   language files, while keeping their translated values intact. 

-  Generating translations automatically using Microsoft's Translator Text API. 
   This gives you a jump start on creating translations. 

| **Note:** Language Builder is available as a plugin for projects that use 
| Gradle or Maven. 

Start with Configuring the Language Builder plugin. 

## Configuring the Language Builder Plugin

Configure the Language Builder plugin for
[Gradle](/docs/7-2/reference/-/knowledge_base/r/lang-builder-gradle-plugin)
or [Maven](/docs/7-2/reference/-/knowledge_base/r/lang-builder-plugin). 

**Gradle:**

```groovy
buildscript {
    dependencies {
        classpath 'com.liferay:com.liferay.gradle.plugins.lang.builder:latest.release'
    }

    repositories {
        maven {
            url "https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/"
        }
    }
}

apply plugin: "com.liferay.lang.builder"

repositories {
    maven {
        url "https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/"
    }
}
```

**Maven:**

```xml 
<project>
    ...
    <build>
        <plugins>
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.lang.builder</artifactId>
                <version>1.0.30</version>
                <configuration>
                    <langDirName>.</langDirName>
                    <translateClientId>${microsoft.translator.client.id}</translateClientId>
                    <translateClientSecret>${microsoft.translator.client.secret}</translateClientSecret>
                </configuration>
            </plugin>
        </plugins>
    </build>
    ...
</project>
``` 

Now you can invoke Language Builder in your project. 

## Running Language Builder

When you run Language Builder, it generates properties files for all your
locales and propagates all new language properties from `Language.properties` to
your locale language files (newly generated and existing). Additionally if you
configured the Microsoft Translator Text API (discussed next), Language Builder
translates your locale language properties. 

Here's the command:

**Gradle:**

```bash
gradlew buildLang
```

**Maven:**

```bash
mvn lang-builder:build
```

| **Tip:** Run Language Builder to update your locale files each time you change
|your `Language.properties` file. 

Note, until you configure translation credentials (discussed  next), Language
Builder prints this message:

```
Translation is disabled because credentials are not specified
```

If you want to configure your app to generate automatic translations using the
Microsoft Translator Text API, keep reading. 

## Translating Language Keys Automatically

If you've configured the Language Builder plugin (above) in your project, you're
well on your way to translating language keys automatically. Now you have to
configure 
[Microsoft's Translator Text API](https://azure.microsoft.com/en-us/services/cognitive-services/translator-text-api/)
so you can translate language keys automatically.

| **Important:** Lang Builder does not translate language keys containing HTML 
| (e.g., `<em>`, `<b>`, `<code>`, etc.). Default language keys that contain HTML
| are only *copied* to your locale language files.

| **Note:** These translations are best used as a starting point. A machine
| translation can't match the accuracy of a real person who is fluent in the
| language. Then again, if you only speak English and you need a Hungarian
| translation, this is better and faster than your attempts at a manual
| translation.

Here's how to set up the translator and generate translations. 

1.  Generate a translation subscription key for the Microsoft Translator Text
    API. Follow the instructions
    [here](https://www.microsoft.com/en-us/translator/business/).

2.  Add your client credentials to the Language Builder plugin configuration. 
    For security reasons, pass the credentials to a property that's stored in
    your local build environment (e.g., see the [Gradle environment
    guide](https://docs.gradle.org/current/userguide/build_environment.html)). 

    **Gradle:**

    Make sure the `buildLang` task knows to use your subscription key for
    translation by setting the `translateSubscriptionKey` property:

    ```groovy
    buildLang {
       translateSubscriptionKey = langTranslateSubscriptionKey
    }
    ```

    Here's the entire `build.gradle` example code, 

    ```groovy
    buildscript {
        dependencies {
            classpath 'com.liferay:com.liferay.gradle.plugins.lang.builder:latest.release'
        }

        repositories {
            maven {
                url "https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/"
            }
        }
    }

    apply plugin: "com.liferay.lang.builder"

    buildLang {
       translateSubscriptionKey = langTranslateSubscriptionKey
    }

    repositories {
        maven {
            url "https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/"
        }
    }
    ```

    **Maven:**

    Set the following Language Builder plugin `<translateClientId />` and
    `<translateClientSecret />` configuration elements using Maven build environment properties: 

    ```xml
    <configuration>
        <langDirName>.</langDirName>
        <translateClientId>${microsoft.translator.client.id}</translateClientId>
        <translateClientSecret>${microsoft.translator.client.secret}</translateClientSecret>
    </configuration>
    ...
    ```

    Here's the entire `pom.xml` example code, 

    ```xml
    <project>
        ...
        <build>
            <plugins>
                <plugin>
                    <groupId>com.liferay</groupId>
                    <artifactId>com.liferay.lang.builder</artifactId>
                    <version>1.0.30</version>
                    <configuration>
                        <langDirName>.</langDirName>
                        <translateClientId>${microsoft.translator.client.id}</translateClientId>
                        <translateClientSecret>${microsoft.translator.client.secret}</translateClientSecret>
                    </configuration>
                </plugin>
            </plugins>
        </build>
        ...
    </project>
    ```

3.  Run Language Builder. 

    **Gradle:**

    ```bash
    gradlew buildLang
    ```

    **Maven:**

    ```bash
    mvn lang-builder:build
    ```

Great! You can now generate language files and provide automatic translations of
your language keys.

## Related Topics 

[Localizing Your Application](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application)

[Using Language Modules](/docs/7-2/frameworks/-/knowledge_base/f/using-a-language-module)

[Gradle Language Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/lang-builder-gradle-plugin)

[Maven Language Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/lang-builder-plugin)

[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace)

[Tooling](/docs/7-2/reference/-/knowledge_base/r/tooling)
