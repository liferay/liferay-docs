# Fitting it All Together [](id=fitting-it-all-together)

<div class="learn-path-step">
    <p>Refactoring the Prototype<br>Step 6 of 6</p>
</div>

You've created a complete data-driven application from the back-end to the 
display. It's a great time to review how everything connects together. 

## The Entry [](id=the-entry)

First, you defined your model in Service Builder's configuration file, 
`service.xml`. The main part of this is your `Entry` object: 

    <entity local-service="true" name="Entry" uuid="true">

        <!-- PK fields -->

        <column name="entryId" primary="true" type="long" />

        <!-- Group instance -->

        <column name="groupId" type="long" />

        <!-- Audit fields -->

        <column name="companyId" type="long" />
        <column name="userId" type="long" />
        <column name="userName" type="String" />
        <column name="createDate" type="Date" />
        <column name="modifiedDate" type="Date" />
        <column name="name" type="String" />
        <column name="email" type="String" />
        <column name="message" type="String" />
        <column name="guestbookId" type="long" />

        <finder name="G_G" return-type="Collection">
            <finder-column name="groupId" />
            <finder-column name="guestbookId" />
        </finder>
    </entity>
 
Next, you created a service implementation in `EntryLocalServiceImpl` that 
defined how to get and store the entry. Every field you defined was accounted 
for in the `addEntry` method. 

    public Entry addEntry(long userId, long guestbookId, String name, String email,
            String message, ServiceContext serviceContext)
            throws PortalException {

            long groupId = serviceContext.getScopeGroupId();

            User user = userLocalService.getUserById(userId);

            Date now = new Date();

            validate(name, email, message);

            long entryId = counterLocalService.increment();

            Entry entry = entryPersistence.create(entryId);

            entry.setUuid(serviceContext.getUuid());
            entry.setUserId(userId);
            entry.setGroupId(groupId);
            entry.setCompanyId(user.getCompanyId());
            entry.setUserName(user.getFullName());
            entry.setCreateDate(serviceContext.getCreateDate(now));
            entry.setModifiedDate(serviceContext.getModifiedDate(now));
            entry.setExpandoBridgeAttributes(serviceContext);
            entry.setGuestbookId(guestbookId);
            entry.setName(name);
            entry.setEmail(email);
            entry.setMessage(message);
        
            entryPersistence.update(entry);

            return entry;
    }

Notice that all the fields you described in Service Builder (including things 
like the `uuid`) are present here. 
 
You also added ways to get entries:

        public List<Entry> getEntries(long groupId, long guestbookId) {
            return entryPersistence.findByG_G(groupId, guestbookId);
        }

        public List<Entry> getEntries(
            long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {

            return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
        }

        public List<Entry> getEntries(long groupId, long guestbookId, int start, int end)
            throws SystemException {

            return entryPersistence.findByG_G(groupId, guestbookId, start, end);
        }

In `service.xml` you defined `groupId` and `guestbookId` as the two finder 
fields, and in these methods you called methods generated to the persistence 
layer. 

After you implemented all that, Service Builder propagated your implementation
to the interfaces, so they could be called. Then, in the portlet class, you
created references to the service classes that Service Builder generated, and
used those references to access the service to add an entry: 
    
    _entryLocalService.addEntry( serviceContext.getUserId(), guestbookId, 
        userName, email,message, serviceContext);
 
Finally, you wrapped all this up in a user interface that lets users enter the 
information they want, and displays the data they've entered. 

Now that you've built the application, and you can see a clear picture of how it 
all works, it's time to test it. 

## Deploying and Testing the Application [](id=deploying-and-testing-the-application)

1.  Drag and drop the `guestbook-api` module onto the server.

2.  Drag and drop the `guestbook-service` module onto the server.

3.  Look for the STARTED messages from the console. 

4.  Go to your @product@ instance at `localhost:8080` in your browser to test 
    your updated application. 

8.  Click *Add Entry*.

9.  Enter a *Name*, *Message*, and *Email Address*.

10. Click *Submit*.

11. Verify that your entry appears.

![Figure 1: A new guestbook and entry.](../../../images/guestbook-entry-test.png)

## What's Next? [](id=whats-next)

You've created a working web application and deployed it on @product@. If you've 
created web applications before, though, you know that it's missing some 
important features: security, front-end validation, and an interface for 
administrators to create multiple guestbooks per portlet. In the next section, 
you'll begin adding these (and more) features. 
