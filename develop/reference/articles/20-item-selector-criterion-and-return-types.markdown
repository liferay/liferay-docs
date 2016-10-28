# Item Selector Criterion and Return Types [](id=item-selector-criterion-and-return-types)

@product@ bundles have apps and app suites containing [`ItemSelectorCriterion` classes](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html)
and [`ItemSelectorReturnType` classes](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html)
developers can use. 

## Item Selector Criterion Classes [](id=item-selector-criterion-classes)

**Collaboration App Suite Modules:**

- [`com.liferay.item.selector.criteria.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/):

    -   [ImageItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html):
        Image file entity type.

    -   [AudioItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/audio/criterion/AudioItemSelectorCriterion.html):
        Audio file entity type.

    -   [FileItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/file/criterion/FileItemSelectorCriterion.html):
        Document Library file entity type.

    -   [UploadItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/upload/criterion/UploadItemSelectorCriterion.html):
        Uploadable file entity type.

    -   [URLItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/url/criterion/URLItemSelectorCriterion.html):
        URL entity type.

    -   [VideoItemSelectorCriterion](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/video/criterion/VideoItemSelectorCriterion.html):
        Video file entity type.

-   [`com.liferay.wiki.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/wiki/com.liferay.wiki.api/) has wiki criterion.
 
**Web Experience App Suite Modules:**

-   [`com.liferay.site.item.selector.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/site/com.liferay.site.item.selector.api/) has site criterion. 

-   [`com.liferay.layout.item.selector.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/layout/com.liferay.layout.item.selector.api/) has layout criterion.

-   [`com.liferay.journal.item.selector.api`](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/web-experience/journal/com.liferay.journal.item.selector.api/) has web content criterion.

If there's no criterion class for your entity, you can create your
own [`ItemSelectorCriterion` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorCriterion.html)
(tutorial coming soon).

## Item Selector Return Type Classes [](id=item-selector-return-type-classes)

The Liferay Collaboration app suite's [`com.liferay.item.selector.criteria.api` module](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/)
includes the following return types:

-   [Base64ItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/Base64ItemSelectorReturnType.html):
    Base64 encoding of the entity as a `String`.
 
-   [FileEntryItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html):
    File entry information as a JSON object.

-   [URLItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html):
    URL of the entity as a `String`.

-   [UUIDItemSelectorReturnType](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.criteria.api/com/liferay/item/selector/criteria/UUIDItemSelectorReturnType.html):
    Universally Unique Identifier (UUID) of the entity as a `String`.

If there's no return type class that meets your needs, you can implement your
own [`ItemSelectorReturnType` class](https://docs.liferay.com/portal/7.0/javadocs/modules/apps/collaboration/item-selector/com.liferay.item.selector.api/com/liferay/item/selector/ItemSelectorReturnType.html)
(tutorial coming soon).
