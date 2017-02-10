# Item Selector Criterion and Return Types [](id=item-selector-criterion-and-return-types)

@product@ bundles have apps and app suites containing [`ItemSelectorCriterion` classes](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html)
and [`ItemSelectorReturnType` classes](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html)
developers can use. 

## Item Selector Criterion Classes [](id=item-selector-criterion-classes)

**Collaboration App Suite Modules:**

- `com.liferay.item.selector.criteria.api`:

    -   [ImageItemSelectorCriterion](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html):
        Image file entity type.

    -   [AudioItemSelectorCriterion](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/audio/criterion/AudioItemSelectorCriterion.html):
        Audio file entity type.

    -   [FileItemSelectorCriterion](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/criteria/file/criterion/FileItemSelectorCriterion.html):
        Document Library file entity type.

    -   [UploadItemSelectorCriterion](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/upload/criterion/UploadItemSelectorCriterion.html):
        Uploadable file entity type.

    -   [URLItemSelectorCriterion](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/url/criterion/URLItemSelectorCriterion.html):
        URL entity type.

    -   [VideoItemSelectorCriterion](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/video/criterion/VideoItemSelectorCriterion.html):
        Video file entity type.

-   [`com.liferay.wiki.api`](@app-ref@/collaboration/latest/javadocs/com/liferay/wiki/item/selector/criterion/package-summary.html) has wiki criterion.
 
**Web Experience App Suite Modules:**

-   [`com.liferay.site.item.selector.api`](@app-ref@/web-experience/latest/javadocs/com/liferay/site/item/selector/criterion/package-summary.html) has site criterion. 

-   [`com.liferay.layout.item.selector.api`](@app-ref@/web-experience/latest/javadocs/com/liferay/layout/item/selector/criterion/package-summary.html) has layout criterion.

-   [`com.liferay.journal.item.selector.api`](@app-ref@/web-experience/latest/javadocs/com/liferay/journal/item/selector/criterion/package-summary.html) has web content criterion.

If there's no criterion class for your entity, you can create your
own [`ItemSelectorCriterion` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html)
(tutorial coming soon).

## Item Selector Return Type Classes [](id=item-selector-return-type-classes)

The Liferay Collaboration app suite's [`com.liferay.item.selector.criteria.api` module](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/package-summary.html)
includes the following return types:

-   [Base64ItemSelectorReturnType](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/Base64ItemSelectorReturnType.html):
    Base64 encoding of the entity as a `String`.
 
-   [FileEntryItemSelectorReturnType](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html):
    File entry information as a JSON object.

-   [URLItemSelectorReturnType](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html):
    URL of the entity as a `String`.

-   [UUIDItemSelectorReturnType](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/UUIDItemSelectorReturnType.html):
    Universally Unique Identifier (UUID) of the entity as a `String`.

If there's no return type class that meets your needs, you can implement your
own [`ItemSelectorReturnType` class](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html)
(tutorial coming soon).
