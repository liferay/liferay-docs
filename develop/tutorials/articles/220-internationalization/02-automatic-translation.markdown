# Automatically Generating Language Files [](id=automatically-generating-language-files)

If you already have a `Language.properties` file that holds
[language keys for your user interface messages](/develop/tutorials/-/knowledge_base/7-0/localizing-your-application),
or even a language module that holds these keys, you're in the right place.

-  Instead of manually creating a language properties file for each locale
   that's supported by Liferay, you can get them all automatically generated
   for you with one command. The same command also propagates the keys from the
   default language file to all translation files.

-  You can also generate automatic translations using Microsoft's Translator
   Text API.

## Generating Language Files for Supported Locales [](id=generating-language-files-for-supported-locales)

If you want to generate files automatically for all 
[locales supported by Liferay](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones),
you must make a small modification to your application's build file.

1.  Make sure your module's build includes the `com.liferay.lang.builder`
    [plugin](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-lang-builder),
    by putting the plugin in build script classpath.

2.  Make sure you have a default `Language.properties` file in
    `src/main/resources/content`. 

3.  Run the `gradle buildLang` task from your project's root directory to
    generate default translation files.

    The generated files contain automatic copies of all the keys and values
    in your default `Language.properties` files. That way you don't have to
    copy your lanugage keys manually into all of the files. Run the
    `buildLang` task each time you change the default language file.

    When the task completes, it prints `BUILD SUCCESSFUL` with this log output:

        Translation is disabled because credentials are not specified

    See the next section to learn how to turn translation on and provide
    credentials.

Here's what a configuration of the `com.liferay.lang.builder` plugin looks in a
`build.gradle` file: 

        buildscript {
            dependencies {
                classpath 'com.liferay:com.liferay.gradle.plugins.lang.builder:latest.release'
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
Now you can start translating your application's messages. If you want to
configure your app to generate automatic translations using the Microsoft
Translator Text API, keep reading.

## Translating Language Keys Automatically [](id=translating-language-keys-automatically)

If you've configured the `com.liferay.lang.builder` plugin in your app, you're
almost there. Now you have to configure
[Microsoft's Translator Text API](https://azure.microsoft.com/en-us/services/cognitive-services/translator-text-api/)
so you can generate automatic translations of your language keys. You cannot,
however, use Liferay's Lang Builder to automatically translate language keys
containing HTML (e.g., `<em>`, `<b>`, `<code>`, etc.). Language keys containing
HTML are automatically *copied* to all supported language files.

+$$$

**Note:** These translations are best used as a starting point. A machine
translation can't match the accuracy of a real person who is fluent in the
language. Then again, if you only speak English and you need a Hungarian
translation, this is better and faster than your attempts at a manual
translation.

$$$

1.  Generate a translation subscription key for the Microsoft Translator Text
    API. Follow the instructions
    [here](http://docs.microsofttranslator.com/text-translate.html).

2.  Make sure the `buildLang` task knows to use your subscription key for
    translation by setting the `translateSubscriptionKey` property:

        buildLang {
            translateSubscriptionKey = "my-key"
        }

    For security reasons you probably don't want to pass them directly in your
    application's build script. Instead, pass the credentials to a property
    that's stored in your
    [local build environment](https://docs.gradle.org/current/userguide/build_environment.html),
    and pass the property into your application's build script.

         buildLang {
           translateSubscriptionKey = langTranslateSubscriptionKey
        }

So what would the complete `buildLang` configuration look like if you followed
all the steps above? 

    buildscript {
        dependencies {
            classpath 'com.liferay:com.liferay.gradle.plugins.lang.builder:latest.release'
        }

        repositories {
            maven {
                url "http://cdn.repository.liferay.com/nexus/content/groups/public"
            }
        }
    }

    apply plugin: "com.liferay.lang.builder"

    buildLang {
       translateSubscriptionKey = langTranslateSubscriptionKey
    }

    repositories {
        maven {
            url "http://cdn.repository.liferay.com/nexus/content/groups/public"
        }
    }

Great! You now know how to generate language files and provide automatic
translations of your language keys.
