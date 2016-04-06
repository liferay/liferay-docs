# Using Liferay's Language Settings

For a given locale, you can [override Liferay's core UI messages](/develop/tutorials/-/knowledge_base/7-0/modifying-core-language-keys).
Modifying Language key values in Liferay provides a lot of localization
flexibility in itself, but we're always looking for new ways to give you more
control. There are language settings in Liferay's `Language_xx.properties` files
that give you even more localization options.

-  Configure the name fields that are displayed (maybe you don't want a middle name
   field), and the field values available (like prefix values) in the add and
edit user forms.

-  Control the directionality of content and messages in Liferay (left-to-right or
   right-to-left).

To see how these are configured, open Liferay's core `Language.properties` file
in one of two ways:

1. From Liferay's source code, navigate to 

        liferay-portal/portal-impl/src/content/Language.properties

2. From a Liferay bundle's `portal-impl.jar`.

        [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

    Just open the `content` folder in the Jar to find the language files.

The first section in the `Language.properties` file is labeled `Language
settings`. 

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
like a magic carpet on which your code (and resources) ride triumphantly into
Liferay's OSGi runtime. Refer to the tutorial on [modifying Liferay's core language keys](/develop/tutorials/-/knowledge_base/7-0/modifying-core-language-keys) to set up a module with the following characteristics:

-  Contains an implementation of `ResourceBundle` that is registered in the
   OSGi runtime.

-  Contains a `Language.properties` file for the locale whose properties you
   want to override.

$$$

The user name properties are used to customize some fields on the add and edit
user forms based on a user's locale.

## Localizing User Names

Since Liferay's employees come from all over the world, we know that
naming conventions are different between locales. Liferay's engineers have made
several of these user name fields configurable in the portal where user name
information is entered or edited. For instance, you might not want a middle name
field at all, or you might want to customize the prefix menu values
for a certain translation. 

The properties for changing user name settings are those that begin with
`lang.user.name` in the language settings section of a locale's language
properties file.

For most of the [locales enabled by default](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Languages and Time Zones) the user name properties are specifically tailored to that
location.

    locales.enabled=ca_ES,zh_CN,nl_NL,en_US,fi_FI,fr_FR,de_DE,iw_IL,hu_HU,ja_JP,pt_BR,es_ES

For example, these are the English (`Language_en.properties`) properties for
setting user name fields:

    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr

![Figure x: The user name settings impact the way user information and forms appear in Liferay.](../../images/english-user-name-fields.png)

Compare those to the Spanish (`Language_es.properties`) settings:

    lang.user.name.field.names=prefix,first-name,last-name
    lang.user.name.prefix.values=Sr,Sra,Sta,Dr,Dra
    lang.user.name.required.field.names=last-name

![Figure x: The Spanish user name settings omit the suffix and middle name fields entirely.](../../images/spanish-user-name-fields.png)

The biggest difference between the English and Spanish form fields in the images
above is that the middle name and suffix fields are omitted in the Spanish
configuration. Other differences include the specific prefix values.

By overriding the language properties file for a given locale you can modify any
of these user name properties to customize the data you're collecting for users.

There are couple of important things to note when considering these
customizations.

## User Name Localization Constraints

There are two important limits to your newfound powers.

1. You cannot remove the `first-name` field from the field names list. 

        lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix

    Why not?  Because it's required by Liferay, as you'll see if you look in
    Liferay's `portal-model-hints.xml` file.

        <field name="firstName" type="String">
            <validator name="required" />
        </field>

    Because first name is a required field in Liferay, you can't remove it from
    the field names list of any locale.

2. Because first name is required, it's not configurable in the property

        lang.user.name.required.field.names

    Instead it's implicitly included in this list. Therefore, any fields you enter
    here are *in addition to* the first name field. Last name is included by
    default, but you can disable it by deleting its value from the property:

        lang.user.name.required.field.names=

    In that case, only first name would be required.

¡Muy excelente! Localizing the forms for adding and editing users is
accomplished using the same method by which Liferay's UI messages are localized:
by overriding one of Liferay's `Lanuguage_xx.properties` files.

## Right to Left or Left to Right?

The first three properties in the language settings section are used for
changing the direction in which the language's characters are displayed. Most
languages are read from left to right, but other languages are meant to be read
from right to left (Arabic, Hebrew, and Persian, for example). It can also be
changed for languages that have been traditionally displayed left to right (like
English) as a funny practical joke. Just don't tell anyone that you got the
idea here.

Here's what the relevant language properties file look like for a language that
should be displayed from right to left:

    lang.dir=rtl
    lang.line.begin=right
    lang.line.end=left

These customizations can help transform Liferay's UI into a user-friendly
environment no matter where your users are from.
