# Basic Forms [](id=basic-forms)

It's really easy to create simple forms with the Forms Application. 

At The Lunar Resort, it's important to capture guest's feelings about their stay
at the resort. After a (hopefully) safe journey home, guests should receive an
email with a link to a page in The Lunar Resort site with a brief survey that prompts
them to rate their stay from a list of selections, and add any additional comments
they'd like in an optional field.

You can develop a form like this before lunch time, even if that's in 5 minutes.

## Building a Form [](id=building-a-form)

Whether you're creating a simple or complex form, it all starts in the same
place: the Forms Application in your site's *Content* section. You access this
in the Menu by using the site selector to choose the site you want to work in
(The Lunar Resort in this case) and clicking *Content &rarr; Forms*. 

- Once you're in the Forms App, click the Add button
(![Add](../../../images/icon-add.png)). You're taken to a form that you'll
fill out to create a form. If you're thinking, "Wait, did they just tell me to fill
out a form to create a form?" Yes, we did. 

- The first step is to give the form a name. For this example, enter *Guest Survey*. 

- For Help Text enter *Tell us how your stay was!*

- We need two simple fields, so click the (large) button at the bottom aptly
  labeled *Add Field*.

    ![Figure 1: You can choose from six field types when creating forms.](../../../images/forms-field-types.png)

- Choose a *Select* field.

    ![Figure 2: Fill out a select field with as many options as you need.](../../../images/forms-select-field.png)

- Here's how to configure your select field:
    - **Label:** *Rate your visit to The Lunar Resort.*
    - **Help Text:** Leave this blank for our purposes. If you want a subheading
      for your field to provide additional guidance, this would be useful.
    - Make sure you turn on the *Required Field* selector. You want to make sure
      that, at a minimum, you capture whether the guests had a favorable stay or
        not.
    - Leave the manual option checked for creating the list of selections. If
      you're interested in populating the field from a data provider, read the
        next article in this section.
    - Add these options: *It was out of this world!*, *I had a good time.*, *I'd
      rather go to the beach*, and *I'll never come back.* You'll notice that
        typing in one of the fields automatically adds another blank selection line.
        Just leave the last one blank when you're done.

- When you're finished configuring the field's basic options, click *Save*.
If you want to see additional options, click *Show More Options*. 

- Now you need a text field, so add one using the same procedure you used for
  the select field.

- Configure the field as follows:
    - **Label:** *Want to tell us more?*
    - **Help Text:** Leave this blank again so the form has a consistent look.
    - **My text field has:** Choose *Multiple Lines*. Let guests prattle on
      about their stay if they want to.
    - **Required Field:** Leave this unselected. Only require guests to fill out
      the select field and leave this one as optional.

    ![Figure 3: Add a text field for guests to provide a longer comment.](../../../images/forms-text-field.png)

- Click *Save* when you're finished configuring the text field.

- Back on the form builder page, you can see the way the fields will be laid
  out. This view shows the first (and only in this case) page of the form.

    ![Figure 4: The form builder page lets you preview your form layout, add a page to the form, or add some more fields.](../../../images/forms-form-builder.png)

- This form is finished, so click *Save*.

Now your form can be added to a page, and Lunar Resort guests can be emailed and
provided with a link to the page where the form is displayed.

## Displaying Forms [](id=displaying-forms)

The form is developed. Now you just need to display it in The Lunar Resort site. 

- Create a new page in The Lunar Resort site called *Guest Survey Page*. Hide it
  from the navigation, and give it a *1 Column* layout.

    ![Figure 5: Add a page for guests to view and fill out your new form.](../../../images/forms-guest-survey-page.png)

- Navigate to the Guest Survey Page and add a Form application to the page.

- Click *Select Form*. When the configuration window opens, click your *Guest
  Survey* form and then *Save*.

    ![Figure 6: Select the form you want to display.](../../../images/forms-select-form.png)

- Your form is now added to the page.

    ![Figure 7: Lunar Resort Guests can access a simple form to record their feelings about the resort.](../../../images/forms-guest-survey.png)

Next learn how to view the form entries. Since there aren't any yet, fill out and
submit the form a few times. 

## Viewing Form Entries [](id=viewing-form-entries)

When users fill out your forms, they're generating data. If you're collecting data, you're
going to want to see it at some point. 

- From the Menu, navigate back to the *Content* &rarr; *Forms* section of The
  Lunar Resort site.

- Click the *Actions* (![Actions](../../../images/icon-actions.png)) button and
  select *View Entries*.

    ![Figure 8: You can view the entries right in the Forms application. ](../../../images/forms-view-entries.png)

- What if you  have a lot of form fields and you can't see all of the data for
  each entry in the search container? Just click the *Actions*
(![Actions](../../../images/icon-actions.png)) button for the entry and select
*View*. You'll be able to see all the specifics for that form entry.

    ![Figure 9: You can view a single entry right in the Forms application.](../../../images/forms-view-entry.png)

Viewing entries is great, but this is serious data we're talking about. You
might need to get all the entries into a spreadsheet so you can work with them,
for example.

## Exporting Form Entries [](id=exporting-form-entries)

So, you need to put your form entries in a spreadsheet to do spreadhseet things
with them? No problem.

- Navigate to the Forms application in The Lunar Resort site's Content section
  again.

- Click the *Actions* (![Actions](../../../images/icon-actions.png)) button and
  select *Export*. 

- Choose a File Extension. You can get entries in CSV or XML. For this example, pick CSV.

- Click *Okay*, and open the file or save it locally. Open it with your favorite
  spreadsheet program and confirm that your form entries are entered into a
spreadsheet for you.

![Figure 10: You can export entries as CSV or XML files.](../../../images/forms-export-csv.png)

+$$$

**Note:** The Forms application itself has an *Import/Export* window that you can
access from the application's Configuration menu
(![Configuration](../../../images/icon-options.png)). This is how you import and
export the application configuration and its data (forms and form entries). The
file format for this type of import and export is a LAR file. For more
information, see the article on
[importing and exporting application content.](/discover/portal/-/knowledge_base/7-0/importing-exporting-pages-and-content).

![Figure 11: As with other Liferay Apps, you can import or export forms and their entries with a LAR file.](../../../images/forms-import-export-lar.png)

$$$

Once you export a batch of form entries, it can make sense to delete them from
the database.

## Deleting Form Entries [](id=deleting-form-entries)

What if you export a form's entries and now you want them to be removed from the
Liferay database? It's easy to delete all of a form's entries at once.

Navigate back to the Forms application In The Lunar Resort Content section.
Click the *Actions* (![Actions](../../../images/icon-actions.png)) button next
to the Guest Survey form and select *View Entries* again. Select all of the
entries by checking the box next to *All*. A trash can icon
(![Trash](../../../images/icon-trash.png)) appears in the top right corner of
the Form Entries screen. Click it.

![Figure 12: Delete all form entries in one fell swoop.](../../../images/forms-delete-entries.png)

If you just wanted to delete a single entry, you'd just select that entry by checking
its box, then click the trash can.

If you're worried about deleting everything irrecoverably by accident, don't
worry. You'll have to confirm the deletion in a dialog box that pops up after
clicking the trash can.

Now you know the basics of creating and managing forms in Liferay. But this
presentation didn't do the Forms application justice. It's much more powerful
than hinted at here. Read the next article to see what the Forms app can do for
your more advanced form buiding needs.
