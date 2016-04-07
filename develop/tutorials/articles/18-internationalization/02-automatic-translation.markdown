# Automatically Generating Language Files [](id=automatically-generating-language-files)

If you already have a default `Language.properties` file that holds [language keys for your user interface messages](/develop/tutorials/-/knowledge_base/7-0/localization-tutorial), or
even a language module that holds these keys, you're in the right place.

-  Instead of manually creating a language properties file for each locale
   that's supported by Liferay, you can get them all automatically generated for
you with one command. The same command also propagates the keys from the default
language file to all of the translation files.

-  You can take a few additional steps and get automatic translations using
   Microsoft's Translator API.

## Generating Language Files for Supported Locales [](id=generating-language-files-for-supported-locales)

If you want to automatically generate files for all of the [locales supported by Liferay](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones),
you only have to do a little work in the build file of your application.

1. Make sure your module's build includes the `com.liferay.lang.builder`
[plugin](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-lang-builder),
by putting the plugin in build script classpath.

2. Make sure you have a default `Language.properties` file in
   `src/main/content`. 

3. Run the `gradle buildLang` task from your project's root directory to generate
   default translation files.

    The generated files will contain automatic copies of all the keys and values
    in your default `Language.properties` files. That way you don't have to worry
    about manually copying your lanugage keys into all of the files. Just run the
    `buildLang` task each time you change the default language file.

    When the task completes, you'll get a `BUILD SUCCESSFUL` message, and you'll
see this log output as well:

        Translation is disabled because credentials are not specified

    See the next section to learn how to turn translation on and provide
    credentials.

Here's what a configuration of the `com.liferay.lang.builder` plugin looks in a
`build.gradle` file: 

        buildscript {
            dependencies {
                classpath ;com.liferay:com.liferay.gradle.plugins.lang.builder':latest.release'
            }

            repositories {
                maven {
                    url "http://cdn.repository.liferay.com/nexus/content/groups/public"
                }
            }
        }

        apply plugin: "com.liferay.lang.builder"

        repositories {
            maven {
                url "http://cdn.repository.liferay.com/nexus/content/groups/public"
            }
        }
Now you can start translating your application's messages, or delegate it to
somebody else. If you want to configure your app to generate automatic
translations using the Microsoft Translator API, keep reading.

## Translating Language Keys Automatically [](id=translating-language-keys-automatically)

Generate automatic translations of your language keys using [Microsoft's
Translator API](https://msdn.microsoft.com/en-us/library/hh454950).

If you've configured the `com.liferay.lang.builder` plugin in your app, there
are only a few steps remaining.

+$$$

**Note:** These translations are probably best used as a starting point. A
machine translation can't match the accuracy of a real person who is fluent in
the language. Then again, if you only speak English and you need a Hungarian
translation, this will be much better and faster than your attempts at a
manual translation.

$$$

1. Register your app with Azure DataMarket. Follow the instructions [here](https://msdn.microsoft.com/en-us/library/hh454950)

2. Make sure the `buildLang` task knows to use your credentials for translation.
For security reasons you probably don't want to pass them directly in your
application's build script.

        buildLang {
            translateClientId = "my-id"
            translateClientSecret = "my-secret"
        } 

    Instead, pass the credentials to a property that's stored in your [local build environment](https://docs.gradle.org/current/userguide/build_environment.html),
and pass the property into your application's build script.

         buildLang {
           translateClientId = langTranslateClientId
           translateClientSecret = langTranslateClientSecret
        }

So what would the complete `build.gradle` file look like if you followed all the
steps above? 

    buildscript {
        dependencies {
            classpath ;com.liferay:com.liferay.gradle.plugins.lang.builder':latest.release'
        }

        repositories {
            maven {
                url "http://cdn.repository.liferay.com/nexus/content/groups/public"
            }
        }
    }

    apply plugin: "com.liferay.lang.builder"

     buildLang {
       translateClientId = langTranslateClientId
       translateClientSecret = langTranslateClientSecret
    }

    repositories {
        maven {
            url "http://cdn.repository.liferay.com/nexus/content/groups/public"
        }
    }

Of course that would not be a complete `build.gradle` file. The sample above
contains only the elements necessary to use the `buildLang` task from the
`com.liferay.lang.builder` plugin.
