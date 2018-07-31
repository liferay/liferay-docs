# Managing Form Entries [](id=managing-form-entries)

Once users begin submitting form entries, you'll want to do these things with
them:

- [View form entries](#viewing-form-entries)
- [Export form entries](#exporting-form-entries)
- [Delete form entries](#deleting-form-entries)

Start by learning how to access and view the entries.

## Viewing Form Entries [](id=viewing-form-entries)

When users fill out forms, they're generating data. You'll want to see that data
at some point. 

1.  From the Menu, navigate back to the *Content* &rarr; *Forms* section of The
    Lunar Resort site.

2.  Click the *Actions* (![Actions](../../images/icon-actions.png)) button for
    the form and select *View Entries*.

    ![Figure 1: You can view the entries right in the Forms application. ](../../images/forms-view-entries.png)

3.  What if you have a lot of form fields and you can't see all the data for
    each entry in the search container? Just click the *Actions*
    (![Actions](../../images/icon-actions.png)) button for the entry and select
    *View*. You're shown all the specifics for that form entry.

    ![Figure 2: You can view a single entry right in the Forms application.](../../images/forms-view-entry.png)

Viewing entries is great, but this is serious data we're talking about. You
might need to get all the entries into a spreadsheet so you can work with them. 

## Exporting Form Entries [](id=exporting-form-entries)

So you need to put your form entries in a spreadsheet to do spreadsheet things
with them? No problem.

1.  Navigate to the Forms application in The Lunar Resort site's Content section
    again.

2.  Click the *Actions* (![Actions](../../images/icon-actions.png)) button and
    select *Export*. 

3.  Choose a File Extension. You can export entries in CSV, XLS, or XML formats 
    by default. For this example, pick CSV.

4.  Click *Okay*, and open the file or save it locally. Open it with your
    favorite spreadsheet program and verify your form entries.

![Figure 3: You can export entries as CSV, XLS, or XML.](../../images/forms-export-csv.png)

+$$$

**Note:** The Forms application itself has an *Import/Export* window accessible 
from the application's Configuration menu
(![Configuration](../../images/icon-options.png)). This is how you import and
export the application configuration and its data (forms and form entries). The
file format for this type of import and export is a LAR file. For more
information, see the article on
[importing and exporting application content](/discover/portal/-/knowledge_base/7-1/importing-exporting-pages-and-content).

$$$

There's a system level setting to determine whether administrators can export
entries in CSV format:

1.  Go to Control Panel &rarr; Configuration &rarr; System Settings and click
    the *Forms* category in the Content section.

2.  Click the *Forms* entry under SITE SCOPE.

3.  The CSV Export property has three options:

    - *Enabled* to enable CSV Export without a warning
    - *Enabled (Show Warning)* to enable CSV Export with the following warning
        to administrators:
            This CSV file contains user supplied inputs. 
            Opening a CSV file in a spreadsheet program may be dangerous.
    - *Disabled* to turn off CSV Export.

Once you export a batch of form entries, it can make sense to delete them from
the database.

## Deleting Form Entries [](id=deleting-form-entries)

What if you export a form's entries and now you want to remove them from the
Liferay database? It's easy to delete all of a form's entries at once.

1.  Navigate back to the Forms application In The Lunar Resort Content section.

2.  Click the *Actions* (![Actions](../../images/icon-actions.png)) button next
    to the Guest Survey form and select *View Entries* again.

3.  Select all entries by checking the box next to *Filter and Order*. A trash 
    can icon
    (![Trash](../../images/icon-trash.png)) appears in the top right corner of 
    the Form Entries screen. Click it.

    ![Figure 4: Delete all form entries in one fell swoop.](../../images/forms-delete-entries.png)

If you just wanted to delete a single entry, select that entry by checking its
box; then click the trash can.

If you're worried about deleting everything irrecoverably by accident, don't
worry. You must confirm the deletion in a dialog box that pops up after clicking
the trash can.

Now you can create basic forms and manage the entries. Keep reading in this
section to learn about the many additional form building features available to
you.
