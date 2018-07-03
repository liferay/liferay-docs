# Sanitizing User Data [](id=sanitizing-user-data)

One of the technically challenging requirements of the General Data Protection
Regulation (GDPR) is _the right to be forgotten_. The purpose of this article is
not to go into the details of this requirement, but to show you how the personal
data erasure functionality can assist you in satisfying this requirement. 

A simple way to think of what it means to be _forgotten_ by software is to
consider a scenario where a new portal administrator is hired immediately after
a User's right to be forgotten request has been honored. The new portal
administrator has access to all of the Site's content and administration
capabilities. Despite this, the administrator must not be able to glean
information that could lead her to knowing the identity of the User whose
personal data was erased.

Conceptually, forgetting a User means two things, at a minimum:

- Erasing the User's identifying information from the system. In @product@, this 
  entails removing the User from database tables and search indexes.
- Erasing or anonymizing content the User has interacted with so it cannot be
  tracked to a real person.

Users can already be deactivated and then deleted, so why add new functionality?
Deleting removes the User from the table of Users in the database. The User's
information is preserved in other locations, however. In a standard User
deletion scenario, all of a User's personally created content is still assigned
to the User and her identifiers (User ID and User Name) still appear in the UI
next to content associated with her. This unintentional preservation of
user-identifying data is inadequate for satisfying some of the GDPR requirements
and is the primary reason why the data erasure functionality was added in
@product-ver@.

+$$$

**Note:** Personal data erasure can help companies in their attempts to satisfy
the requirements of GDPR. Using the data erasure tool described here provides no
guarantee of compliance with the legal requirements of GDPR. Each company or
individual whose website processes user personal data and is under the
jurisdiction of GDPR must carefully determine the precise steps necessary to
ensure they are fully compliant with GDPR.

$$$

To begin sanitizing a user's data,

1.  Go to Control Panel &rarr; Users &rarr; Users and Organizations.

2.  Click the Actions button for a User (![Actions](../../../images/icon-actions.png)) and
    select *Delete Personal Data*.

    The User's Personal Data Erasure screen appears.

3.  Complete the five-step process sequentially to erase the personal data
    associated with the User.

![Figure 1: Sequentially complete five steps to complete the data erasure process.](../../../images/users-data-erasure.png)

## Step 1: Deactivation [](id=step-1-deactivation)

Deactivating the User first ensures she doesn't create more content as you're
sanitizing her from the system. Click *Deactivate User*.

![Figure 2: Deactivating the User kicks off the data erasure process.](../../../images/users-data-erasure3.png)

## Step 2: Delete the User's Personal Site [](id=step-2-delete-the-users-personal-site)

The User's public (profile) and private (dashboard) pages are deleted when the
User is deleted. Separating this step out allows the administrator to make sure
no information important to the enterprise is lost before the personal Site
deletion is completed. Review the User's personal Site (click the provided links
to navigate directly there) and preserve any necessary data. Then click *Delete
Personal Site*.

![Figure 3: The second step in personal data erasure is deleting the User personal Site.](../../../images/users-data-erasure1.png)

## Step 3: Review Application Data [](id=step-3-review-application-data)

There's no automated process for anonymizing application data (Blogs Entries,
Wiki Pages, etc.). The administrator must review the User Associated Data (UAD:
application content created by the User) piece by piece to determine that no
data important to the enterprise is lost by deleting it and that no UAD can be
gleaned from the content if anonymized.

After reviewing each piece of the data, the administrator either anonymizes it
or deletes it to complete this step.

![Figure 4: In the third data erasure step, review, delete, and/or anonymize the User's application data.](../../../images/users-data-erasure2.png)

To enter the review process, click *Review*.

The Application Data Review screen displays a summary including how many content
items in each application are associated with the User.

![Figure 5: The Application Data Review screen makes the third, and most complicated data erasure step, more manageable.](../../../images/users-app-data-review.png)

To manage (anonymize or delete) all the items for an application at once: 

1.  Click the Actions button (![Actions](../../../images/icon-actions.png)) for
    the application.

2.  If you're sure all items for an application can be safely deleted, choose
    *Delete*. 

3.  If you're sure simple anonymization is good enough for all of an
    application's items, choose *Anonymize*.

4.  To view the items for an application, choose *View* or click on the
    application in the table.

Clicking an item takes you to the view/edit screen where you can see the
application's items and take action. 

![Figure 6: Drill down into a User's application data to determine if manual editing is necessary.](../../../images/users-item-data-review.png)

Click the Actions button (![Actions](../../../images/icon-actions.png)) for an
item and select *Edit*, *Anonymize*, or *Delete*, as appropriate.

Once you've worked your way through the items and taken action, the view in the
Application Data Review screen is updated to reflect that there are no more items
needed to review. Click the *Complete Step* button once finished.

![Figure 7: Click *Complete Step* once all of the User's application data is reviewed and dealt with.](../../../images/users-data-review-complete.png)

## Step 4: Anonymize Remaining Data [](id=step-4-anonymize-remaining-data)

In step 4, you must click *Anonymize Data*. This completes the anonymization
process for remaining database references to the User's Name and ID. Some
information is anonymized, but others, such as Notifications, are deleted as
they don't make sense once the User is deleted.

![Figure 8: Anonymize any remaining use of the User's identifier's remaining in the database.](../../../images/users-data-erasure4.png)

## Step 5: Delete User [](id=step-5-delete-user)

Once all data is reviewed, deleted, edited, and/or anonymized as appropriate,
delete the User. This step is simple: Click *Delete User*.

![Figure 9: To finish the data erasure process, delete the User.](../../../images/users-data-erasure5.png)

Now the User's data is anonymized or deleted, and the User is also deleted.
