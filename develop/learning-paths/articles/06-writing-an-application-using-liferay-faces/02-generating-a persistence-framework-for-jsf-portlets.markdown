# Creating a Persistence Framework for JSF Portlets

Before you begin developing the guestbook's buttons and actions, you'll generate
the persistence framework using Service Builder. By using Service Builder, you
create a persistence layer and service layer that are responsible for retrieving
your model data.

If you've read the MVC learning path, it goes specifically through creating a
persistence framework for the MVC version of the guestbook portlet; but could
you follow the same steps for a JSF portlet? The answer is *yes*. 

The functionality of the persistence framework for a JSF portlet works exactly
the same as an MVC portlet, meaning you can create the persistence layer the
exact same way you would with an MVC portlet using Service Builder. 

For your JSF portlet, you can complete the [Using Service Builder to Generate a
Persistence
Framework](https://dev.liferay.com/develop/learning-paths/-/knowledge_base/using-service-builder-to-generate-a-persistence-fr)
learning path to successfully generate your persistence framework. Once you've
finished generating your persistence layer, return back to this point and
continue on to the next section to learn how to integrate the persistence
framework into your JSF portlet. 

## Integrating Your Persistence Framework into Your JSF Portlet

Now that you've generated your persistence framework, its time to integrate it
into your JSF guestbook so you can use it. On the persistence side of things,
Service Builder has generated all the basic functionality you'll need for the
JSF guestbook. The next step is defining the service layer, which is the buffer
between your controller and your persistence layer. 

Before you get started defining the service layer, there's one additional thing
you'll need to add to your `service.xml` file. The JSF guestbook will have a
slightly different implementation of its service layer compared to the MVC
portlet, so we'll need to account for that slight change in the way your
services are built. 

1. Open your JSF guestbook's `service.xml` file. Verify that you're in the
Overview tab. 

2. Expand the Guestbook entity and click on *Finders*. 

3. Click the *+* button to the right of the list of finders and name the new
   finder *Name* and give it the return type *Collection*. 

4. Click the *Build Services* button from the top right of the screen. By doing
this, you'll rebuild the services you built previously. 

The new finder you created will aid in finding a guestbook table by name,
instead of solely relying on its `GroupId`. You'll use this finder in the next
section. 

The next thing you'll need to do is implement a service layer for your
*Guestbook* entity; after that, you'll implement a service layer for your Entry
entity. 

### Writing a Service API for Guestbooks

1. Open the `com.liferay.docs.guestbook.service.impl` package. Here, Service
Builder has generated several stub classes for each entity. The
`-LocalServiceImpl.java` class is your implementation for local services,
whereas the `-ServiceImpl.java` class is your implementation for remote
services. 

2. Open the `GuestbookLocalServiceImpl.java` class. This is the core class
you'll customize in the service layer of your application. The controller calls
methods from here (not directly, but exposed via a `-Util` class), and the
methods here call your service layer. You'll learn about the controller and how
to update it later. 

3. You'll implement the simplest methods first. What you're doing here is
exposing methods from the persistence layer in your service layer. Add the
following two methods to `GuestbookLocalServiceImpl`:

        public List<Guestbook> getGuestbooks(long groupId) throws SystemException {
            return guestbookPersistence.findByGroupId(groupId);
        }

        public List<Guestbook> getGuestbooks(long groupId, int start, int end) throws SystemException {
            return guestbookPersistence.findByGroupId(groupId, start, end);
        }

    Notice that the work of actually going to the database and finding your
    `Guestbook` entities has already been done for you: all you’re doing is
    calling the generated methods. In a sense, you’re creating your own API for
    your application’s data. The first method gets all the entities that match a
    given `groupId`, and the second method does the same thing, but includes
    start and end markers to paginate the data. 

4. Now you're ready to create a method that adds `Guestbook`s to the database.
   Add the following method to your class: 

        @Override
        public Guestbook addGuestbook(Guestbook guestbook) throws SystemException {
            long guestbookId = counterLocalService.increment(Guestbook.class.getName());
            guestbook.setGuestbookId(guestbookId);

            return super.addGuestbook(guestbook);
        }

    This method retrieves a guestbook name and assigns it to the `guestbookId`
    variable. Then, that `guestbookId` is used to add a guestbook to the
    database. 

5. You'll need a method that grabs and returns the first guestbook by name. This
   ensures that the portlet is displaying a guestbook when a user views the
   portlet. The guestbook portlet will always have a generic *Main* guestbook if
   no custom made guestbooks are found. Add the following method to return a
   guestbook by its name: 

        public Guestbook getFirstGuestbookByName(long groupId, String name) throws SystemException {
            Guestbook guestbook = null;

            List<Guestbook> guestbooks = guestbookPersistence.findByName(groupId, name);
            if (guestbooks != null && guestbooks.size() > 0) {
                guestbook = guestbooks.get(0);
            }

            return guestbook;
        }

Awesome! You’ve successfully created a service API to retrieve and add
`Guestbook`s. To enable it, you must run Service Builder again. When you do,
it’ll generate the methods you just created in your service layer’s Interface
classes. 

Next, you'll create similar methods to add guestbook entries. 

### Writing a Service API for Guestbook Entries

Now that you've written a service API for guestbooks, you'll need to do the same
thing for your guestbook entries. 

1. Open the `EntryLocalServiceImpl` class, which is located in the
   `com.liferay.docs.guestbook.service.impl` package. 

2. Create two methods for finding guestbook entries. Remember that the finder
   you created required two fields: a `groupId` and a `guestbookId`: 

        public List<Entry> getEntries(long groupId, long guestbookId) throws SystemException {
            return entryPersistence.findByG_G(groupId, guestbookId);
        }

        public List<Entry> getEntries(long groupId, long guestbookId, int start, int end) throws SystemException {
            return entryPersistence.findByG_G(groupId, guestbookId, start, end);
        }

3. Create a method for adding guestbook entries. This method is similar to the
   method you created earlier for adding guestbooks. 

        @Override
        public Entry addEntry(Entry entry) throws SystemException {
            long entryId = counterLocalService.increment(Entry.class.getName());
            entry.setEntryId(entryId);

            return super.addEntry(entry);
        }

4. Create a method for returning the number of guestbook entries that you've
   stored:

        public int getEntriesCount(long groupId, long guestbookId) throws SystemException {
            return entryPersistence.countByG_G(groupId, guestbookId);
        }

Terrific! You now have a fully functional service API for the `Guestbook` and
`Entry` entities. The next step is creating managed beans which will make use of
your new service API. 
