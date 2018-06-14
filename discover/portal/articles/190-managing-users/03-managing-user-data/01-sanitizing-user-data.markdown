# Sanitizing User Data [](id=sanitizing-user-data)

Technically speaking, one of the challenging requirements of the General Data
Protection Regulation (GDPR) is _the right to be forgotten_. The purpose of this
article is not to go into the details of this requirement, but to show you how
@product@ is providing a feature to assist you in satisfying this
requirement. 

A simple way to think of what it means to be _forgotten_ by software is to
consider a scenario in which a new portal administrator is hired immediately
after a User's right to be forgotten request has been honored. The new portal
administrator has access to all of the instance's site content and
administration capabilities. Despite this, the admin must not be able to glean
information that could lead them to knowing the identity of the User whose
personal data was erased.

Conceptually, forgetting a User means, at a minimum:

- Erasing the User's identifying information. In @product@, this will entail
    removing the User from database tables and search indexes.
- Erasure of the User's content OR anonymizing the content so it cannot be
    tracked to a real person.

ADD A NOTE ABOUT NOT GUARANTEEING THAT THIS SATISFIES GDPR, IT'S UP TO THE
COMPANY TO DETERMINE WHETHER THIS IS SATISFACTORY

ALSO DESCRIBE THE DEFICIENCIES OF SIMPLE DEACTIVATION AND DELETION

To begin sanitizing a user's data:

1.  Go to Control Panel &rarr; Users &rarr; Users and Organizations.

2.  Click the Actions button for a User (![Actions](../../../images/icon-actions.png)) and
    select *Delete Personal Data*.

    The User's Personal Data Erasure screen appears.

3.  Complete the five-step process sequentially to erase the personal data
    associated with the User.

![Figure 1: ](../../../images/users-data-erasure.png)

## Step 1: Deactivation [](id=step-1-deactivation)

The end of this process is deleting the User. A user must always be deactivated
before deletion. Click *Deactivate User*.

![Figure 2: ](../../../images/users-data-erasure3.png)

## Step 2: Delete the User's Personal Site [](id=step-2-delete-the-users-personal-site)

The User's public pages (profile) and private pages (dashboard) must be deleted.
Separating this step out allows the administrator to make sure no information
important to the enterprise is lost before the deletion is completed. Once any
necessary data from the User's personal pages is preserved, click *Delete
Personal Site*.

![Figure 3: ](../../../images/users-data-erasure1.png)

## Step 3: Review Application Data [](id=step-3-review-application-data)

There's no automated process for anonymizing application data (Blogs Entries,
Wiki Pages, etc.). The administrator must Review the User Associated Data (UAD;
for example, application content created by the User) piece by piece to
determine that no data important to the enterprise is lost by deleting it and
that no UAD can be gleaned from the content if simply anonymized.

After reviewing each piece of the data, the administrator either anonymizes it
or deletes it to complete this step.

![Figure 4: ](../../../images/users-data-erasure2.png)

To enter the review process, click *Review*.

The Application Data Review screen displays a summary including how many content
items in each application are associated with the User.

![Figure 5: ](../../../images/users-app-data-review.png)

To manage all the items for an application at once: 

1.  Click the Actions button (![Actions](../../../images/icon-actions.png)) for
    the application.

2.  If you're sure all items for an application can be safely deleted, choose
    *Delete*. 

3.  If you're sure simple anonymization is good enough for all of an
    application's items, choose *Anonymize*.

4.  To view the item's for an application, choose *View* or click on the
    application in the table.

Clicking an item takes you to the view/edit screen where you can see the
application's particular items and take action. 

![Figure 6: ](../../../images/users-item-data-review.png)

Click the Actions button (![Actions](../../../images/icon-actions.png)) for an
item and select *edit*, *Anonymize*, or *Delete*, as appropriate.

Once you've worked your way through the items and have taken action, the view in
the Application Data Revie screen is updated to reflect that there ore no more
items needed to review. Click the *Complete Step* button once finished.

![Figure 7: ](../../../images/users-data-review-complete.png)

## Step 4: Anonymize Remaining Data [](id=step-4-anonymize-remaining-data)

In step 4, you must click *Anonymize Data*. This completes the anonymiation
process for any remaining data 
EXPLAIN WHAT DATE THIs ANONYMIZES

![Figure 8: ](../../../images/users-data-erasure4.png)

This automatic anonymization process covers derived data on the User that
resides in the database. 

It includes:

LIST OF ANONYMIZED DATA

## Step 5: Delete User [](id=step-5-delete-user)

Once all data is reviewed, deleted, edited, and/or anonymized as appropriate,
delete the User. This step is simple: Click *Delete User*.

![Figure 9: ](../../../images/users-data-erasure5.png)

