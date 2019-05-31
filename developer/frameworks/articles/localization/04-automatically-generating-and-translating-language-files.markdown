---
header-id: automatically-generating-translations
---

# Automatically Generating Translations

[TOC levels=1-4]

If you already have a `Language.properties` file that holds
[language keys for your user interface messages](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application),
or even a [language module](/docs/7-2/frameworks/-/knowledge_base/f/using-a-language-module) that holds these keys, you're in the right place. Here you'll explore Language Builder's capabilities:

-  Generating language files for each supported locale with a single
   command. This saves you from creating language properties for locales
   manually. It also propagates new default language file keys to all
   translation files, while keeping your translated values intact.  

-  Generating translations automatically using Microsoft's Translator Text API. 
   This gives you a jump start on creating translations. 

| **Note:** Language Builder is available as a plugin for projects that use 
| Gradle or Maven. 

## Generating Language Files for Supported Locales

Language Builder saves you time by generating language files for all your
locales. What's more, it propagates any new default language file keys to all of
your locale language files. All you have to do is translate the key (manually or
automatically, as described later) in place when you can. Here's how to set up Language Builder and generate language files with it. 

1.  Configure the Language Builder plugin for
    [Gradle](/docs/7-2/reference/-/knowledge_base/r/lang-builder-gradle-plugin)
    or [Maven](/docs/7-2/reference/-/knowledge_base/r/lang-builder-plugin). 

**Gradle `build.gradle` example:**

```
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

The `build.gradle` file code includes the plugin's repository, adds the plugin
as a build script dependency, and applies the plugin to the project. 

**Maven `pom.xml` example:**
    
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
    
This `pom.xml` code configures the plugin's repository and adds the plugin to
the project. 

2.  Run Language Builder:

**Gradle:**
```bash
gradlew buildLang
```

**Maven:**

```bash
mvn lang-builder:build
```

When you run Language Builder the first time, it generates locale language files
that contain copies of all your `Language.properties` file's properties. When
you run Language Builder after updating properties in the locale language files,
Language Builder leaves your translations in place and propagates any new
default properties to the locale files. 

| **Tip:** Run Language Builder to update your locale files each time you 
| change your `Language.properties` file. 

| **Note:** Since you haven't configured translation credentials (discussed 
| next), Language Builder prints this message:
|
| `Translation is disabled because credentials are not specified`


If you want to configure your app to generate automatic translations using the
Microsoft Translator Text API, keep reading. 

## Translating Language Keys Automatically [](id=translating-language-keys-automatically)

If you've configured the Language Builder plugin (above) in your project, you're
well on your way to translating language keys automatically. Now you have to configure
[Microsoft's Translator Text API](https://azure.microsoft.com/en-us/services/cognitive-services/translator-text-api/)
so you can generate automatic translations of your language keys.

| **Important:** Lang Builder does not translate language keys containing HTML 
| (e.g., `<em>`, `<b>`, `<code>`, etc.). Default language keys that contain HTML
| are only *copied* to your locale language files.

| **Note:** These translations are best used as a starting point. A machine
| translation can't match the accuracy of a real person who is fluent in the
| language. Then again, if you only speak English and you need a Hungarian
| translation, this is better and faster than your attempts at a manual
| translation.

1.  Generate a translation subscription key for the Microsoft Translator Text
    API. Follow the instructions
    [here](https://www.microsoft.com/en-us/translator/business/).
    <!-- old [here](http://docs.microsofttranslator.com/text-translate.html).--> 

2.  Add your client credentials to the Language Builder plugin configuration. For security reasons, pass the credentials to a property that's stored in your local build environment (e.g., see the [Gradle environment guide](https://docs.gradle.org/current/userguide/build_environment.html)). 

**Gradle:**

Make sure the `buildLang` task knows to use your subscription key for
translation by setting the `translateSubscriptionKey` property:

```
buildLang {
   translateSubscriptionKey = langTranslateSubscriptionKey
}
```

Here's the entire `build.gradle` example code, 

```
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

```
...
<configuration>
    <langDirName>.</langDirName>
    <translateClientId>${microsoft.translator.client.id}</translateClientId>
    <translateClientSecret>${microsoft.translator.client.secret}</translateClientSecret>
</configuration>
...
```

Here's the entire `pom.xml` example code, 

```
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

Great! You can now generate language files and provide automatic translations of
your language keys.

## Related Topics 

[Localizing Your Application](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application)

[Using Language Modules](/docs/7-2/frameworks/-/knowledge_base/f/using-a-language-module)

[Gradle Language Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/lang-builder-gradle-plugin)

[Maven Language Builder Plugin](/docs/7-2/reference/-/knowledge_base/r/lang-builder-plugin)

[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace)

[Tooling](/docs/7-2/reference/-/knowledge_base/r/tooling)
