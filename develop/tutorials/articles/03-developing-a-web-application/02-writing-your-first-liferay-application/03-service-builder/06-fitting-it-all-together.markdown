#Fitting it all together

You've created a complete data-driven application from the backend to the 
display. Now take a look back and see how everything connects together.

##The Entry

Take a look at your Entry object, for an example.

First, in `service.xml` you defined all of the things that made up an entry
and all of the data fields that it would need.
	
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
	
Next you created an service implementation that provided the details of how
you get the entry and store it. Each field that you needed to define the 
entry was accounted for in the `addEntry` method.
	
	public Entry addEntry(
				long userId, long guestbookId, String name, String email,
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
	
	Notice that all all of the fields that you described in service builder 
	(including things like the `uuid` that were outside of the main declaration 
	of fields) are present here.
	
You also added various means of getting entries:

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
	fields, and in these methods you used different permutations of those to
	create the getters for entry.

After you implemented all that, service builder generated your model, API 
and util methods that did the real work behind the scenes. Then, in the portlet 
class, you created references to the service classes that service builder 
generated, and used those references to access the service to add an entry:
	
	    _entryLocalService.addEntry( serviceContext.getUserId(), guestbookId, 
	    userName, email,message, serviceContext);
	
Finally you wrapped all this up in a user interface that enables users to 
enter the information they want, and the system to retrieve the data it
needs from the context to create your entry.

Each piece of the application has a function which starts with the basic idea 
of what kind of data you need. Now that you've built the application, and you
can see a clear picture of how it all works, it's time to actually test it all 
out.
