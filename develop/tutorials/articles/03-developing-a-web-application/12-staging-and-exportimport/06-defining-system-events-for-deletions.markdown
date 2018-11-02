# Defining System Events for Deletions [](id=defining-system-events-for-deletions)

<div class="learn-path-step">
    <p>Enabling Staging and Export/Import<br>Step 6 of 7</p>
</div>

The Staging framework tracks entity modifications in a few different ways.
Actions like *adding* a guestbook or *editing* an entry are tracked
automatically by the framework with the configuration of staged models and their
data handlers. Entity deletions are handled slightly differently using system
events.

For the Guestbook app, you must define system events for entity deletions so
they're appropriately tracked by the Staging framework. If Staging can't track
your entity deletions, they can't be managed on the staged Site, which means you
can only delete entities from the live Site.

You must define your system events in your local services.

1.  Open the `guestbook-service` module's
    `com.liferay.docs.guestbook.service.impl.GuestbookLocalServiceImpl` class
    and add the following `deleteGuestbook` methods. These override the default
    Service Builder generated methods the application has been using:

        @Indexable(type = IndexableType.DELETE)
        @Override
        public Guestbook deleteGuestbook(long guestbookId)
            throws PortalException {

            Guestbook guestbook =
                guestbookPersistence.findByPrimaryKey(guestbookId);

            return guestbookLocalService.deleteGuestbook(guestbook);
        }

        @Indexable(type = IndexableType.DELETE)
        @Override
        @SystemEvent(type = SystemEventConstants.TYPE_DELETE)
        public Guestbook deleteGuestbook(Guestbook guestbook) {

            return guestbookPersistence.remove(guestbook);
        }

    These methods override the `GuestbookLocalServiceBaseImpl`'s
    `deleteGuestbook` methods. The major addition is the `@SystemEvent`
    annotation added to the `deleteGuestbook(Guestbook)` method. This notifies
    the Staging framework that a deletion system event occurs when the method is
    called.

    The `deleteGuestbook(long)` method is rerouted to call the `SystemEvent`
    tracked method, so all deletions are accounted for.

2.  Ensure that the remaining `deleteGuestbook` method triggers the system
    event. Within the `deleteGuestbook(long, ServiceContext)` method, change

        guestbook = deleteGuestbook(guestbook);

    to this

        guestbook = guestbookLocalService.deleteGuestbook(guestbook);

    Now all guestbook deletions are tracked via `SystemEvent`.

3.  Add the import packages for the new `SystemEvent` annotation
    (*[CTRL]+[SHIFT]+O*) and then save the file.

4.  You must apply the deletion system event to both entities. Open the
    `com.liferay.docs.guestbook.service.impl.EntryLocalServiceImpl` class and
    add similar delete methods:

        @Indexable(type = IndexableType.DELETE)
        @Override
        public Entry deleteEntry(long entryId)
            throws PortalException {

            Entry entry = entryPersistence.findByPrimaryKey(entryId);

            return entryLocalService.deleteEntry(entry);
        }

        @Indexable(type = IndexableType.DELETE)
        @Override
        @SystemEvent(type = SystemEventConstants.TYPE_DELETE)
        public Entry deleteEntry(Entry entry) {

            return entryPersistence.remove(entry);
        }

    This is the same logic as before, except the delete system event is now
    applied for the entry.

5.  Make sure the remaining `deleteEntry` method triggers the system
    event. Within the `deleteEntry(long, ServiceContext)` method, change

        entry = deleteEntry(entryId);

    to this

        entry = entryLocalService.deleteEntry(entry);

    Now all entry deletions are tracked via `SystemEvent`.

6.  Add the import packages for the new `SystemEvent` annotation
    (*[CTRL]+[SHIFT]+O*) and then save the file.

7.  Run Service Builder by navigating to the Gradle Tasks pane on the right side
    of @ide@ and selecting your project's *build* &rarr; *buildService* task.

Your Guestbook app's deletions are now properly tracked by the Staging
framework.
