# Localization [](id=localization)

When you create a new piece of Web Content, you have the ability to choose a
default language. If you click *Change*, you can select your default language
from a large number of languages Liferay supports. Before you can create a
translation, you must finish creating the content in your default language and
save it. Once you've done that, editing the content provides you with the option
to *Add Translation*.

![Figure 3.17: You have many translation languages to choose from for your web content.](../../images/04-web-content-translation.png)

After you click *Add Translation*, you can select a language by scrolling
through the list or by entering the language you want to use in the search box.
When you select a language, a new window opens within your browser enabling you
to translate the original web content into the selected language. Once you are
done with the translation, click *Save* and the translation is added to the list
of *Available Translations*.

![Figure 3.18: After typing your translated text, you can summarize the translation in the *Abstract* section.](../../images/04-web-content-translation-2.png)

You can modify the language translation list by inserting `locales.enabled=`
followed by your preferred languages in your `portal-ext.properties` file. For
example, `locales.enabled=ar_SA,nl_NL,hi_IN` offers *Arabic (Saudi Arabia)*,
*Dutch (Netherlands)*, and *Hindi (India)*.

+$$$

**Warning:** If you switch your site's default language (e.g., via friendly
URL), but do not have the necessary translations for localizable fields, your
site's language values will be used from the old default language. Therefore,
you should change the default language of your site *only* when you have
translated values for all localizable entities. Otherwise, you may not be in
control of what language is displayed in the portal.

$$$

The ability to completely delete a translation in one step has also been added.
Instead of simply disabling a translation or having to go through a multistep
process to remove it, you can now simply open the translation you don't want and
click *Remove Translation*.

When you create a new web content structure, each field you create has a
*Localizable* checkbox displayed next to it. This enables you to control what
can and can't be changed in the translation process. For example, if you don't
want images or content titles to be changed when the content is translated, you
can make sure those fields aren't listed as localizable. When you follow the
steps above to localize content, only fields within the structure that had the
*Localizable* box checked appear within the translation window.

+$$$

**Note:** The Localizable checkbox was removed for 6.2 CE GA1 and GA2, and 6.2
EE GA1, SP1, and SP2. However, the Localizable checkbox is now available for
Liferay following these versions for web content structure fields. You can view
[LPS-33161](https://issues.liferay.com/browse/LPS-33161) for more details.

$$$

Next, we'll discuss how to let users customize their site pages.
