# Localization [](id=localization)

Previous versions of Liferay had the ability to create and manage different
translations of your web content but with Liferay 6.1 we've added several
improvements.

When you create a new piece of Web Content, you have the ability to choose a
default language. If you click *Change*, you can select your default language
from a large number of languages Liferay supports. Before you can create a
translation, you must finish creating the content in your default language and
save it. Once you've done that, editing the content provides you with the option
to *Add Translation*.

![Figure 3.17: Adding a translation](../../images/04-web-content-content-translation.png)

After you click *Add Translation*, you can select a language by scrolling
through the list or by entering the language you want to use in the search box.
When you select a language, a lightbox opens within your browser window enabling
you to easily compare the original with the new translation. Once you are done
with the translation, click *Save* and the translation is added to the list of
*Available Translations*.

![Figure 3.18: Adding a translation](../../images/04-web-content-content-translation-2.png)

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
*Localizable* box checked appear within the translation window. Next, we'll
discuss how to let users customize their site pages.
