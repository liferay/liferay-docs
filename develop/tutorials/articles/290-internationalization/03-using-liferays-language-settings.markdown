# Using Liferay's Language Settings [](id=using-liferays-language-settings)

For a given locale, you can
[override @product@'s core UI messages](/develop/tutorials/-/knowledge_base/7-1/overriding-language-keys).
Modifying language key values provides a lot of localization flexibility in
itself, but we're always looking for new ways to give you more control. There
are language settings in `Language_xx.properties` files that give you even more
localization options.

-  In the add and edit user forms, configure the name fields that are displayed
   and the field values available in select fields. For example, leave out the
   middle name field if you want, or alter the prefix selections.

-  Control the directionality of content and messages (left to right or
   right to left).

To see how these settings are configured, open @product@'s core
`Language.properties` file in one of two ways:

1. From Liferay Portal's source code. Navigate to 

        liferay-portal/portal-impl/src/content/Language.properties

2. From a bundle's `portal-impl.jar`.

        [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

    Just open the `content` folder in the JAR to find the language files.

The first section in the `Language.properties` file is labeled *Language
Settings*: 

    ##
    ## Language Settings
    ##

    lang.dir=ltr
    lang.line.begin=left
    lang.line.end=right
    lang.user.default.portrait=initials
    lang.user.initials.field.names=first-name,last-name
    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr

+$$$

**Note:** To use the language settings mentioned here, you need a module, which
is like a magic carpet on which your code and resources ride triumphantly into
@product@'s OSGi runtime. Refer to the tutorial on
[overriding language keys](/develop/tutorials/-/knowledge_base/7-1/overriding-language-keys)
to set up a module with the following characteristics:

-  Contains an implementation of `ResourceBundle` that is registered in the
   OSGi runtime.

-  Contains a `Language.properties` file for the locale whose properties you
   want to override.

$$$

The user name properties are used to customize certain fields of the Add and
Edit user forms based on a user's locale.

## Localizing User Names [](id=localizing-user-names)

Customers come from all over the world, so naming conventions are different
between locales. Because of this, user name fields are configurable in the
following ways:

-  Remove certain name fields and make others appear more than once. Some
   locales need more than one last name, for example.

        lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix

-  Change the prefix and suffix values for a locale.

        lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
        lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr

-  Specify which fields are required.

        lang.user.name.required.field.names=last-name

+$$$

**Note:** A user's first name is mandatory. Because of this, take these two
points into consideration when configuring a locale's user name settings:

- The `first-name` field can't be removed from the field names list. 

        lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix

- Because a first name is required, it's always implicitly included in the
  *required field names* property:

        lang.user.name.required.field.names=last-name

    Therefore, any fields you enter here are *in addition to* the first name
    field. Last name is required by default, but you can disable it by deleting
    its value from the property:

        lang.user.name.required.field.names=

    In that case, only a first name would be required.

$$$

The properties for changing user name settings are those that begin with
`lang.user.name` in the language settings section of a locale's language
properties file.

For most of the
[locales enabled by default](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones),
the user name properties are specifically tailored to that location.

    locales.enabled=ca_ES,zh_CN,nl_NL,en_US,fi_FI,fr_FR,de_DE,iw_IL,hu_HU,ja_JP,pt_BR,es_ES

For example, these are the English (i.e., `Language_en.properties`) properties
for setting user name fields:

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

The biggest difference between the English and Spanish form fields is that the
middle name and suffix fields are omitted in the Spanish configuration. Other
differences include the specific prefix values.

¡Muy excelente! Localizing the forms for adding and editing users is
accomplished using the same method by which @product@'s UI messages are
localized: overriding one of its `Lanuguage_xx.properties` files.

## Identifying User Initials [](id=identifying-user-initials)

The default avatar displays a user's initials. Some cultures use initials
differently, so there's a way to configure them in the `Language.properties`
file.

    lang.user.default.portrait=initials
    lang.user.initials.field.names=first-name,last-name

The `lang.user.default.portrait` property sets the type of portrait to use for
users. This can be set to `initials` or `image`. If set to `image`, the default
images defined by the `image.default.user.female.portrait` or
`image.default.user.male.portrait` properties residing in the
`portal.properties` file are used. Therefore, the
`lang.user.initials.field.names` property is ignored.

![Figure 3: The user's initials are displayed for their avatar by default.](../../images/initials-avatar.png)

If you're leveraging the user's initials for the default avatar, the
`lang.user.initials.field.names` property is used to organize how the initials
are displayed. Valid values for this property include `first-name`,
`middle-name`, and `last-name`, in any order.

Now you can manage how a user's initials are displayed!

## Right to Left or Left to Right? [](id=right-to-left-or-left-to-right)

The first three properties in the `Language.properties`'s Language Settings
section change the direction in which the language's characters are displayed.
Most languages are read from left to right, but some languages are read from
right to left (e.g., Arabic, Hebrew, and Persian). You can also change it for
languages that have been traditionally displayed left to right (like English) as
a funny practical joke. Just don't tell anyone that you got the idea here.

Here's what the relevant language properties look like for a language that
should be displayed from right to left:

    lang.dir=rtl
    lang.line.begin=right
    lang.line.end=left

With these customizations, you can transform your UI into a user-friendly
environment no matter where your users are from.
