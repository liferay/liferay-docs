---
header-id: handling-indexing-in-the-guestbook-service-layer
---

# Handling Indexing in the Guestbook Service Layer

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Enabling Search and Indexing for Guestbooks</p><p>Step 6 of 6</p>
</div>

Whenever a Guestbook database entity is added, updated, or deleted, the search
index must be updated accordingly. The @product@ annotation `@Indexable`
combines with the `IndexableType` to mark your service methods so documents can
be updated or deleted. Next, you'll annotate `addGuestbook`, `updateGuestbook`,
and `deleteGuestbook` service methods. 

1.  Open `GuestbookLocalServiceImpl` in the `guestbook-service` module's 
    `com.liferay.docs.guestbook.service.impl` package and add the following 
    annotation above the method signature for the `addGuestbook` and 
    `updateGuestbook` methods:

    ```java
    @Indexable(type = IndexableType.REINDEX)
    public Guestbook addGuestbook(...)

    @Indexable(type = IndexableType.REINDEX)
    public Guestbook updateGuestbook(...)
    ```

    The `@Indexable` annotation indicates that an index update is required
    following the method execution. The indexing classes control the type of
    index: setting the `@Indexable` annotation type to `IndexableType.REINDEX`
    updates the document in the index that corresponds to the updated Guestbook. 

2.  Add the following annotation above the method signature for the 
    `deleteGuestbook` method: 

    ```java
    @Indexable(type = IndexableType.DELETE)
    public Guestbook deleteGuestbook(...)
    ```

    When a Guestbook is deleted from the database, its document shouldn't
    remain in the search index. This ensures that it is deleted.

3.  Use Ctrl-Shift-O to add the necessary imports:

    ```java
    import com.liferay.portal.kernel.search.Indexable;
    import com.liferay.portal.kernel.search.IndexableType;
    ```

    Save the file. 

4.  In the Gradle Tasks pane on the right-hand side of Liferay @ide@, 
    double-click `buildService` in `guestbook-service` &rarr; `build`. This 
    re-runs Service Builder to incorporate your changes to 
    `GuestbookLocalServiceImpl`. 

Next, you'll enable search and indexing for Guestbook Entries. 
