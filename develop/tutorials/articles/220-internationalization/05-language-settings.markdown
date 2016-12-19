# Using Liferay's Language Settings [](id=using-liferays-language-settings)

For a given locale, you can [override Liferay's core UI messages](/develop/tutorials/-/knowledge_base/7-0/modifying-liferays-language-keys).
Modifying Language key values in Liferay provides a lot of localization
flexibility in itself, but we're always looking for new ways to give you more
control. There are language settings in Liferay's `Language_xx.properties` files
that give you even more localization options.

-  In the add and edit user forms, configure the name fields that are displayed
   and the field values available in select fields. For example, leave out the
middle name field if you want, or alter the prefix selections.

-  Control the directionality of content and messages in Liferay (left to right or
   right to left).

To see how these settings are configured, open Liferay's core `Language.properties` file
in one of two ways:

1. From Liferay's source code, navigate to 

        liferay-portal/portal-impl/src/content/Language.properties

2. From a Liferay bundle's `portal-impl.jar`.

        [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

    Just open the `content` folder in the Jar to find the language files.

The first section in the `Language.properties` file is labeled *Language
settings*: 

    ##
    ## Language settings
    ##

    lang.dir=ltr
    lang.line.begin=left
    lang.line.end=right
    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr

+$$$

**Note:** To use the language settings mentioned here, you need a module, which is
like a magic carpet on which your code and resources ride triumphantly into
Liferay's OSGi runtime. Refer to the tutorial on
[modifying Liferay's core language keys](/develop/tutorials/-/knowledge_base/7-0/modifying-liferays-language-keys)
to set up a module with the following characteristics:

-  Contains an implementation of `ResourceBundle` that is registered in the
   OSGi runtime.

-  Contains a `Language.properties` file for the locale whose properties you
   want to override.

$$$

The user name properties are used to customize certain fields of the add and edit
user forms based on a user's locale.

## Localizing User Names [](id=localizing-user-names)

Liferay's customers come from all over the world, and we recognize that naming
conventions are different between locales. Liferay's engineers have made several
of the user name fields configurable in Liferay where user name information is
entered or edited.

-  Remove certain name fields and make others appear more than once. Some
   locale's need more than one last name, for example.

        lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix

-  Change the prefix and suffix values for a locale.

        lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
        lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr

-  Specify which fields are required.

        lang.user.name.required.field.names=last-name

+$$$

**Note:** A user's first name is mandatory in Liferay. Because of this, take
these two points into consideration when configuring a locale's user name
settings:

1. The `first-name` field can't be removed from the field names list. 

        lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix

2. Because first name is required, it's always implicitly included in the
*required field names* property:

        lang.user.name.required.field.names=last-name

    Therefore, any fields you enter here are *in addition to* the first name
    field. Last name is required by default, but you can disable it by deleting its
    value from the property:

        lang.user.name.required.field.names=

    In that case, only first name would be required.

$$$

The properties for changing user name settings are those that begin with
`lang.user.name` in the language settings section of a locale's language
properties file.

For most of the [locales enabled by default](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html#Languages and Time Zones) the user name properties are specifically tailored to that location.

    locales.enabled=ca_ES,zh_CN,nl_NL,en_US,fi_FI,fr_FR,de_DE,iw_IL,hu_HU,ja_JP,pt_BR,es_ES

For example, these are the English (`Language_en.properties`) properties for
setting user name fields:

    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr

![Figure 1: The user name settings impact the way user information and forms appear in Liferay.](../../images/english-user-name-fields.png)

Compare those to the Spanish (`Language_es.properties`) settings:

    lang.user.name.field.names=prefix,first-name,last-name
    lang.user.name.prefix.values=Sr,Sra,Sta,Dr,Dra
    lang.user.name.required.field.names=last-name

![Figure 2: The Spanish user name settings omit the suffix and middle name fields entirely.](../../images/spanish-user-name-fields.png)

The biggest difference between the English and Spanish form fields in the images
above is that the middle name and suffix fields are omitted in the Spanish
configuration. Other differences include the specific prefix values.

¡Muy excelente! Localizing the forms for adding and editing users is
accomplished using the same method by which Liferay's UI messages are localized:
by overriding one of Liferay's `Lanuguage_xx.properties` files.

## Right to Left or Left to Right? [](id=right-to-left-or-left-to-right)

The first three properties in the language settings section are used for
changing the direction in which the language's characters are displayed. Most
languages are read from left to right, but other languages are meant to be read
from right to left (Arabic, Hebrew, and Persian, for example). It can also be
changed for languages that have been traditionally displayed left to right (like
English) as a funny practical joke. Just don't tell anyone that you got the
idea here.

Here's what the relevant language properties look like for a language that
should be displayed from right to left:

    lang.dir=rtl
    lang.line.begin=right
    lang.line.end=left

With these customizations yo can transform Liferay's UI into a user-friendly
environment no matter where your users are from.
