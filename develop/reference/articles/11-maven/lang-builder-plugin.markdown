# Lang Builder Plugin [](id=lang-builder-plugin)

The Lang Builder plugin lets you sort and translate the language keys in your
project.

## Usage [](id=usage)

To use the plugin, include it in your project's root `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.lang.builder</artifactId>
                <version>1.0.24</version>
                <configuration>
                </configuration>
            </plugin>
        ...
        </plugins>
    </build>

You can view an example POM containing the Lang Builder configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/lang-builder/samples/pom.xml).

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`lang-builder:build` | Runs Liferay Lang Builder to translate language property files.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`excludedLanguageIds` | `String[]` | `{"da", "de", "fi", "ja", "nl", "pt_PT", "sv"}` | The language IDs to exclude in the automatic translation.
`langDirName` | `String` | `"src/content"` | The directory where the language properties files are saved.
`langFileName` | `String` | `"Language"` | The file name prefix of the language properties files (e.g., `Language_it.properties`).
`plugin` | `boolean` | `true` | Whether to check for duplicate language keys between the project and the portal.
`portalLanguagePropertiesFileName` | `String` | `null` | The `Language.properties` file of the portal.
`translate` | `boolean` | `true` | Whether to translate the language keys and generate a language properties file for each locale that's supported by @product@.
`translateSubscriptionKey` | `String` | `null` | The subscription key for Microsoft Translation integration. Subscription to the Translator Text Translation API on Microsoft Cognitive Services is required. Basic subscriptions, up to 2 million characters a month, are free.
 
