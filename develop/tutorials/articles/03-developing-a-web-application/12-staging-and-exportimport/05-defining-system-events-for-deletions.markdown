# Defining System Events for Deletions

The Staging framework tracks entity modifications in a few different ways.
Actions like *adding* a guestbook or *editing* an entry are tracked
automatically by the framework with the configuration of staged models and their
data handlers. Tracking entity deletions are done slightly different with
system events.

For the Guestbook app, you must define system events for entity deletions so
they're appropriately tracked by the Staging framework. If Staging can't track
your entity deletions, they can't be managed on the staged site; this means you
can only delete entities from the live site.

You must define your system events in your local services.

1.  Open the `guestbook-service` module's
    `com.liferay.docs.guestbook.service.impl.GuestbookLocalServiceImpl` class
    and add the following `deleteGuestbook` methods:

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

2.  Ensure that the other remaining `deleteGuestbook` method triggers the system
    event. Within the `deleteGuestbook(long, ServiceContext)` method, change

        guestbook = deleteGuestbook(guestbook);

    to this

        guestbook = guestbookLocalService.deleteGuestbook(guestbook);

    Now all guestbook deletions are tracked via `SystemEvent`.

3.  You must apply the deletion system event to both entities. Open the
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

4.  Make sure the other remaining `deleteEntry` method triggers the system
    event. Within the `deleteEntry(long, ServiceContext)` method, change

        entry = deleteEntry(entryId);

    to this

        entry = entryLocalService.deleteEntry(entry);

    Now all entry deletions are tracked via `SystemEvent`.

5.  Run Service Builder by navigating to the Gradle Tasks pane on the right side
    of IDE and selecting your project's *build* &rarr; *buildService* task.

Your Guestbook app's deletions are now properly tracked by the Staging
framework.
