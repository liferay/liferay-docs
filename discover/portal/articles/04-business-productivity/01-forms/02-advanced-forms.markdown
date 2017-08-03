# Creating Advanced Forms [](id=creating-advanced-forms)

Liferay's built-in Forms application supports your most basic form building
needs (something like a one question survey), and your most advanced (like a
mortgage application, or conference sign-up form). In [a separate article](/discover/portal/-/knowledge_base/7-0/basic-forms), learn
about the basics of form creation:

-  Navigating to the Forms application
-  Adding form fields, and supported field types
-  Configuring form fields
-  Viewing and Exporting form entries
-  Deleting form entries

This article covers more advanced use cases for forms, and a basic knowledge of
the Forms application is assumed. Here's a current list of the features you can
use in your forms that are covered in this article:

- Populating a Select field with a REST Data Provider
- Making a field appear based on the value entered into another field
- Add extra pages to the form
- Enabling workflow for the form
- Providing a predefined value or a placeholder value
- Validating fields using a number of different criteria
- Configuring form permissions
- Laying out the form

Aside from those features, which enhance the building of your forms, there are
some settings and processing features to explore:

- Enabling CAPTCHA for a form
- Redirecting to a different URL after a successful form submission
- Sending an email notification whenever a form entry is submitted

+$$$

**Note:** By default, form entries are stored in Liferay's database using JSON
format. If you navigate to Form Settings (click the *Options*
(![Options](../../../images/icon-options.png)) button &rarr; *Settings*), you'll
see that the only storage option is JSON. Keep in mind that developers can
implement their own storage types if JSON is not desirable. If they do, the
option will appear here in Form Settings. See the tutorial on custom storage
types (not yet written) for more information.

$$$

More features are being planned, so don't despair if you need a feature that's
not yet present in the Forms application.

## The Lunar Resort Employment Application Form [](id=the-lunar-resort-employment-application-form)

At The Lunar Resort, management is VERY particular in its hiring practices. Lots
of people think they want to work on the moon full time, but it takes a special
kind of person to really make it work. There's so much that can go wrong:

-  Astrophobia: If a newly hired employee discovers they have an unshakable fear
   of space, it's quite costly to send them back home.
-  Claustrophobia: The tiny sleeping capsules where employees rest is not
   compatible with a fear of small spaces.
-  Alienophobia: Some of The Lunar Resort guests are not from planet Earth. It's
   important for employees to be comfortable and friendly with the more
"exotic" guests, such as [the Daleks](https://en.wikipedia.org/wiki/Dalek).

There are more hiring pitfalls than the phobias listed above, but it's time to
start building the application form, starting with the creation of its pages.

## Creating Form Pages [](id=creating-form-pages)

Liferay's Forms application supports multi-page forms. To add one, click the
Actions (![Action](../../../images/icon-actions.png)) button at the top right hand
corner of the form, and then click *Add New Page*.

![Figure 1: You can add new pages or reset the current page from the Page Actions menu.](../../../images/forms-page-actions.png)

You also have the option to *Reset Page*. If you click this, the title,
description, and any fields you added to the page will be deleted. It gives you
a blank slate.

The Lunar Resort employment form needs three pages with clear page
descriptions:

-  Personal Details: This page requires you to give us some pretty personal information.
-  Very Personal Details: This page requires you to give us some very personal information.
-  Extremely Personal Details: This page requires you to give us some extremely personal information.

Once you create the form's pages, you'll see how navigating the pages is easy
with the form page navigation wizard.

![Figure 2: Multi-page forms have a default *Wizard Mode* that makes navigating the form easy for users.](../../../images/forms-pagination-modes.png)

Once you have the form pages created, navigate back to the first page, Personal
Details.

## Adding Placeholder Text and Predefined Values [](id=adding-placeholder-text-and-predefined-values)

To populate the employment application form for The Lunar Resort, you should
start by adding some of the usual fields, like a Full Name field:

-  **Full Name:** Add a required text field titled *Full Name*, with a description that reads
   *Enter your full name, no matter how long.* Under Show More Options, enter
Placeholder Text: *e.g., Maximillian Aurelius Piroux the 11th*.

    ![Figure 3: The Full Name field in the employment form. It has a placeholder value to help users understand what you are looking for.](../../../images/forms-employment-full-name.png)

+$$$

**Note:** There's an important difference between *Predefined Values* and
*Placeholder Text*. If a field is left untouched by the user, the predefined
value will be submitted, while placeholder text is not submitted (it's as if the
user left the field blank).

$$$

Remember that placeholder values will not be submitted if the field is left
blank, so you don't have to worry about getting a bunch of applications from
*Maximillian Aurelius Piroux the 11th*.

## Validating Text Fields [](id=validating-text-fields)

The HR department needs to weed prospective employees out if they're not mature
enough. If the user is under a certain age, make sure to ask them if they've
ever been away for an extended period of time. This can be done using a text
field that accepts only numbers (age), and then a field to probe
whether the applicant can handle being away from home for a long period of time.
In that field, you can use a Field Visibility Expression so it only appears if the
user enters a number less than *30*.

-  **Age:** Add a required text field called *Age*, with a description that says, *Enter
   your age in years*, and the placeholder text *Numbers Only*.

    Enable Validation and select *Number*, *is greater than*, and enter *0*. You
can enter an error message *You must enter your age in years, and it must be
greater than 0*.

    ![Figure 4: The Age field in the employment form. It uses validation to ensure that a number greater than zero is entered.](../../../images/forms-employment-age.png)

+$$$

**Note:** Field validation lets you decide if you're going to reject certain
types of information from being submitted. If a form field's data doesn't pass
the validation rules you specify, the entire form entry won't be submitted until
the user enters valid data. What validation rules are built in for your
convenience?

-  Text: If you begin by selecting *Text*, only text submissions are allowed for
   your form field. 
    -  Contains: Selecting *Contains* means you're going to specify a character
       or word that *must* be entered.
    -  Does not contain: If you use this rule, you're going to specify a
       character or word that *must not* be submitted.
    -  URL: Submissions must be URLs with the proper syntax.
    -  Email: Submissions must be email addresses with the proper syntax.

    ![Figure 5: You can validate text submissions for text fields.](../../../images/forms-text-validation.png)
-  Number: If you select *Number*, only number submissions are allowed for your
   form field.
    -  Is greater than or equal to: Specify the smallest number that can be
       entered.
    -  Is greater than: Entries must be greater than the specified number.
    -  Is equal to: Allow only entries equal to the number you specify.
    -  Is less than or equal to: Specify the largest number that can be
       submitted.
    -  Is less than: Entries must be less than the specified number.

    ![Figure 6: You can validate number submissions for text fields.](../../../images/forms-number-validation.png)

It can be helpful to alert users to your validation rules in help text or
placeholder text (for example, *You must enter a number greater than 0*).


If you mark a field as *Required*, that's another form of validation. Liferay is
going to make sure that the field is not empty. If it is, an error message will
appear, directing the user to enter something into the required field.

$$$

Make one more required field on the first page:

-  **Email Address:** A required text field that only accepts email address. Use
   the validation rule for *Text* &rarr; *Email* to make sure that a valid email
address is entered.

Next create a field that will be displayed only if the user enters any number
less than *30* in the Age field.

## Using Field Visibility Expressions [](id=using-field-visibility-expressions)

Sometimes you only want a form field to appear if a certain value is entered in
another field. In the Lunar Resort  application for employment, you want to ask
an additional questions to users who are less than 30 years old, to make sure
they're comfortable with being away from home for a long period of time.

-  **Maximum Time Away From Home:** This is a required select field with the description
   *What's the longest time, in consecutive months, that you've spent away from
your permanent residence?* Give it the following options: *<1*, *1-3*, *4-12*,
*>12*.

    Enter a Predefined Value of *<1*.

    Now comes the interesting part. Under Field Visibility Expression, enter
*between(Age,0,29)*. This expression will cause the field to appear *only* if
the Age field has a value between 0 and 29 entered. Another expression that will
produce the same result is *(Age>=0)&&(Age=<29)*. In this case, the expression
is checking to make sure that the Age value is greater than or equal to zero and
it's checking to make sure that the entered Age value is less than or equal to
29. 

![Figure 7: You can make a field appear based on the value of another field in your form.](../../../images/forms-vis-expr-fields.png)

+$$$

**Note:** There are several functions and operators you can use to control
field visibility based on the value entered in another field. An expression can
be as simple as containing a single operator:

    Age<29

Alternatively, operators and functions can be combined to make a more complex
expression:

    sum(Housing,CarPayment)>MonthlySalary

In this expression, the field would only be displayed if the sum of the
*Housing* field and the *Car Payment* field is greater than the *Monthly Salary*
field. 

As demonstrated above, you can use a form field value (whether text or numeric)
in place of a hard coded value, by entering the Field Name instead of a specific
value. 

![Figure 8: A form field's Field Name is automatically generated from the Label you give the field.](../../../images/forms-field-name.png)

There are functions and operators for your use in your field visibility
expressions. First consider the functions: 

-  **between(FieldName,lower bound value, upper bound value)**: Check whether
   the entered value of the specified numeric field is between a lower and upper
bound. If it is in the specified range, the field will appear on the form. For
example:

        between(Age,0,29)

-  **equals(FieldName, value)**: Display your field only if the value entered
   into the specified field equals a specific numeric value (or if it equals the
value of another field). For example:

        equals(Salary,TotalExpenses)

-  **sum(FieldName1, FieldName2, ...)**: Add numeric fields together, usually for
   comparing to a given value using one of the operators covered below. For
example:

        sum(Expense1,Expense2,Expense3)>Salary

-  **min(value, FieldName1, FieldName2,...)**: Get the lowest numeric value in the list of
   parameters (you can use Field Names and/or values), usually for comparing to
a given value using one of the operators below. For example:

        Salary>min(TotalExpenses,TotalDebt)

-  **max(Field Name, value)**: Get the largest numeric value in the list of
   parameters, usually for comparing to a given value using one of the operators
covered below. For example:

        Salary<max(TotalExpenses,TotalDebt)

-  **contains(Field Name, value)**: Used to check a field for a specific string
   of text or the text entered in another field. The text value can be in the
beginning, middle, or end of the field being checked. For example:

        contains(FullName,LastName)

-  **concat(Field Name, value)**: Concatenate a string of text with a given
   text value, usually used in constructing a larger expression. For example:

        contains(FullName,concat(FirstName, " Smith"))

In addition to the functions described above, there are a bunch of operators you
can use in building your Field Visibility Expressions:

-  **+**: Addition operator
-  **-**: Subtraction operator
-  **\***: Multiplication operator
-  **/**: Division operator
-  **%**: Division remainder operator
-  **^**: Power operator
-  **&&**: *And* condition operator
-  **||**: *Or* condition operator
-  **>**: *Greater than* relational operator
-  **>=**: *Greater than or equal to* relational operator
-  **<**: *Less than* relational operator
-  **<=**: *Less than or equal to* relational operator
-  **==**: *Equals* relational operator
-  **!=**: Relational operator to make sure the arguments are *not* equal
-  **NOT**: Negates a boolean expression. Boolean (true or false) expressions
   check for a *true* condition by default. You can use *NOT* to check the opposite:

        NOT(contains(FullName, FirstName))

    The above expression checks whether the *FullName* field contains the value
entered in the *FirstName* field. If it does *not* then your field will appear.

To check for a value in a select field, you need to use the Field Name for
the field, and as the value, use the Field Name for the select option you
entered.

![Figure 9: A select field's options will be given Field Names. These are used as the value in your Field Visibility Expression.](../../../images/forms-select-option-name.png)

For example, if you want to display a field only if the *Maximum Time Away From
Home* field's *>12* option is selected, you could write this Field Visibility
Expression:

    contains(MaximumTimeAwayFromHome,12)

$$$

Field Visibility Expressions are great for dynamically displaying a field based
on another field's entered data. Next, find out how to dynamically populate a
select list from a Data Provider.

## Populating Select Fields with REST Data Providers [](id=populating-select-fields-with-rest-data-providers)

On its final page (*Extremely Personal Details*) The Lunar Resort application
form needs five select fields, all populated with the complete list
of the countries of earth. Read the sidebar note below to find out why this
field is needed (it's just a fictional story, so skip if you don't have time for
some light entertainment).

+$$$

**Note:** Interstellar citizenship rules are strange, and since the moon
declared independence from earth (Lunar Independence Day is a big deal at The
Lunar Resort), it has its own set of citizenship rules. Among other things, the
rules require employees of The Lunar Resort to be citizens of the moon.

Moon citizenship is cool, especially if you're a dual citizen of your native
earth country and the moon! However, during the Space Wars (in which the moon
gained its independence), many countries on earth made strict laws dictating
that dual citizenship would never be granted if the applicant's second
citizenship allegiance was to the moon. That can lead to interesting situations
when an employee leaves The Lunar Resort, or is terminated. The Lunar Resort
management needs to know a leaving employee's top five destinations on earth. If
none of the countries the employee lists are willing to accept them, there's a
secret colony of earth's expatriates on the dark side of the moon that usually
has vacancies.

That's why the application form's last page needs to have five select fields
with all the countries of earth.

$$$

Unless you have an intern, you don't want to manually enter all the countries of
earth into five different select fields. Instead, you want to populate the fields
using a Data Provider.

### Adding and Configuring a Data Provider [](id=adding-and-configuring-a-data-provider)

Before using a Data Provider in your select fields, you need to configure one
that you can use in your forms. Navigate to the Forms application in *Site
Administration* &rarr; *Content*, and click the Options
(![Options](../../../images/icon-options.png)) button.

![Figure 10: Set up a data provider that can be reused in all your forms.](../../../images/forms-options-menu.png)

If you click on *Data Providers*, the data providers window opens, and you can
see any existing data providers or add a new one. Click the *Add* button
(![Add](../../../images/icon-add.png)).

Fill out the Data Providers form, passing in the following values:

-  Name: *Countries List*
-  Description: *A complete list of earth's countries*
-  URL: http://localhost:8080/api/jsonws/country/get-countries
-  Displayed JSON Attribute: *nameCurrentValue*
-  Stored JSON Attribute: *name*
-  User Name: test@liferay.com (or whatever your admin user name is)
-  Password: *test* (or whatever your admin password is)

You're probably wondering what the *URL*, *Displayed JSON Attribute*, and
*Stored JSON Attribute* fields are all about. First, it's good to understand
that what you're doing when setting up a data provider is accessing a [REST web service](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=5&cad=rja&uact=8&ved=0ahUKEwj5xqa_nOLMAhWDYpoKHXCSAl8QFgg-MAQ&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FRepresentational_state_transfer&usg=AFQjCNEbs7wls7WPYkz8WvaaxEkpyhJdeQ&sig2=_HSCpCVSb_gzRMpEwKm3mw). You can use the [JSON web services registered in Liferay](/develop/tutorials/-/knowledge_base/7-0/registering-json-web-services),
or any other REST web service you have access to. To find a list of the
ready-to-use registered JSON web services in Liferay, navigate to
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws) (assuming you're running Liferay locally). If
you do that, you'll be able to browse the available Liferay services that you can use to
populate your list. The services useful to you in the Forms application will get
a list of something.  The service you are calling in the data provider you set
up above gets a list of countries. Find the `get-countries` JSON web service and
click on it, then click *Invoke*. The *Result* tab will have a list of countries
using JSON syntax, like this:

    [
      {
        "a2": "AF",
        "a3": "AFG",
        "countryId": "20",
        "idd": "093",
        "mvccVersion": "0",
        "name": "afghanistan",
        "nameCurrentValue": "Afghanistan",
        "number": "4"
      },
        ...

That's the record for the country Afghanistan. As you can see in the *URL* tab,
the URL you entered into the data provider form is the same as the one generated
for accessing the `get-countries` JSON web service. You can find the URL for any
registered JSON web service using this same procedure. 

The *Displayed JSON Attribute* field is where you specify which field you want
to use to populate your select list. In this case, `nameCurrentValue` provides
the full, properly capitalized country name, and that's what you want users of
the form to see.

The *Stored JSON Attribute* field is where you set which field will be stored in
the database for the form entry. The `name` field was selected in this example.

Once you set up the data provider, click *Save*. Now you can set up five select
fields that list all the countries of earth in no time.

### Using a Data Provider in a Select Field [](id=using-a-data-provider-in-a-select-field)

Back in the application form, go to the third page, Extremely Personal
Details. Add a select field.

-  **Relocation Country: First Choice**: Make the select field required, and
   enter *If relocation becomes necessary, to which country on earth would you
most like to go?* as Help Text. Select the radio button to *Create List From
Data Provider*, and choose *Countries List*. Click *Save*.

If you're building the example application form, do the same thing four more
times, using field labels to reflect the choice number. For example, you'd call
the second select field *Relocation Country: Second Choice*, and so on until you
have five of them. You can decide what help text to provide, if any.

![Figure 11: You can configure a data provider and use it to populate a select field.](../../../images/forms-data-provider-list.png)

Your Data Provider is now being used to populate your select fields. However,
this form is going to be submitted by Guest users, who don't currently have
permission to see the list of results from the data provider.

### Granting Data Provider Permissions [](id=granting-data-provider-permissions)

To configure the data provider's permissions, navigate to the Forms application (*Site Administration* &rarr; *Content* &rarr;
*Forms*). Open the Options menu (![Options](../../../images/icon-options.png))
and select *Data Providers*. For the data provider you want to configure, click
the Actions button (![Actions](../../../images/icon-actions.png)), then
*Permissions*. 

For the employment application form in The Lunar Resort, Guest users need the
*View* permission, or else they won't be able to see the options provided by the
data provider in the select fields. Once you grant the permission, click *Save*.


+$$$

**Note:** If you're developing The Lunar Resort employment application form,
you'll notice we skipped over the second page, Very Personal Details. It will be
dealt with in the section on laying out your forms.

$$$

That concludes the actual development of the form (adding pages and form
fields). The rest of the article covers features of the Forms application that
you'll use during or after form entry submission.
<!--Picture of full form?-->
<!-- Should these Form Settings options be covered separately? -->

## Enabling CAPTCHA on Form Submissions [](id=enabling-captcha-on-form-submissions)

You're probably familiar with CAPTCHA, the program used to prevent a bot from
submitting forms. It's often used in [Login applications](/discover/deployment/-/knowledge_base/7-0/logging-in-to-liferay),
but you can use it in the Forms application, too. 

![Figure 12: You can enable CAPTCHA for your form in the Form Settings window.](../../../images/forms-settings-captcha.png)

To enable CAPTCHA, click the form's *Options*
(![Options](../../../images/icon-options.png)) button, then *Settings*.
Enable the *Require CAPTCHA* setting, click *Done*, save the form, and that's
all there is to it!

![Figure 13: Once you enable CAPTCHA, your form has protection against bot submissions.](../../../images/forms-captcha.png)

Now, you're much more likely to receive form submissions from humans!

You can move on to enabling workflow for your form.

## Enabling Workflow for Forms [](id=enabling-workflow-for-forms)
<!--For story purposes, we could have the workflow developed in the Designing
Workflow article (not yet written) be used in this article. An applicant submits
the form, then it goew to an HR rep, tehn to a lower management person, then an
upper management person, and sends emails to the applicant along the way.
Something like that.-->

To enable [workflow](/discover/portal/-/knowledge_base/7-0/using-workflow) for a form, open the Form Settings window (click the *Options*
(![Options](../../../images/icon-options.png)) button &rarr; *Settings*). In the
Select a Workflow field, select the workflow you want to use for the form, then
click *Done* and save the form.

![Figure 14: Enable workflow on a form from its Form Settings window.](../../../images/forms-workflow.png)

Because each form can be so different, it makes sense to configure workflow for
forms in each form's Form Settings section.

## Setting Up Form Notifications [](id=setting-up-form-notifications)

It's possible to have a notification email sent each time a form entry is
successfully submitted. Open the form's Form Settings section (click the
*Options* (![Options](../../../images/icon-options.png)) button &rarr;
*Settings*). Click the *Email Notifications* tab, enable the option to *Send an
email notification for each entry*, and fill out the fields:

-  From Name: The name of the sender. This could be the site name, the form,
   name, or anything else that will be informative to the recipient.
-  From Address: You can use something like *noreply@liferay-forms.com*, so that
   recipients of the notification don't try to reply.
-  To Address: This should be the email address of the notification recipient
   (for example, *test@liferay.com*). 
-  Subject: An informative subject line tells the recipient what happened.
   *An application for employment was submitted in The Lunar Resort* might be
appropriate for the form developed in this article.

If you enabled workflow for the form, and there's already a notification going
to the people who need to process the form entry, you might not need to generate
any notification from the Forms application itself.

## Redirecting Users after Form Submission [](id=redirecting-users-after-form-submission)

You can send users to any URL you want upon form submission. You might have a
particular *Thanks for your submission!* type page with a link to the home page,
or you might want to direct a user somewhere specific based on the form they just
submitted.

Whatever your use case is, you can set up a redirect URL by opening the form's
Form Settings section (click the *Options*
(![Options](../../../images/icon-options.png)) button &rarr; *Settings*). In the
*Redirect URL* just type the URL. That's it.

So, your form is all set up ready to be filled out by users. However, you
haven't configured the permissions yet, so the only user with permission to add
records is you, the owner! There's a good chance you'll want to customize the
form's permissions.

## Configuring Form Permissions [](id=configuring-form-permissions)
<!-- Is this better in the basic forms article? -->

By default, you can grant the following permissions for a form:

-  Delete: Permission to delete the form.
-  Permissions: This gives any role with the permission the ability to configure
   the form's permissions.
-  Add Record: Permission to submit form entries.
-  Update: Permission to update form entries.
-  View: All the other permissions you can configure for the form depend on this
   one. Think about it: if you can't see the form, how can you add a record?

![Figure 15: You can configure the permissions for a form.](../../../images/forms-form-permissions.png)

If you want to allow unauthenticated visitors to your site to view and fill out
forms, give the *Guest* role the *View* and *Add Record* permissions.

Navigate to the Forms application (*Site Administration* &rarr; *Content* &rarr;
*Forms*). Click the application employment form's  *Actions* button
(![Actions](../../../images/icon-actions.png)), and select *Permissions*.
Give the Guest role the View and Add permissions, then click *Save*.

+$$$

**Note:** By default, all users inherit the Guest role's permissions. The Guest
role represents unauthenticated visitors of your site, and it makes sense that
if you want to allow Guest users to submit forms, you're fine with your site
members and portal users submitting forms, as well. If you want to disable the
automatic inheritance of the Guest role permissions, there's a
[property](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Permissions) you can set in your `portal-ext.properties` file:

    permissions.check.guest.enabled=false

$$$

## Form Layouts [](id=form-layouts)

Sometimes, it doesn't make sense to have a single column, vertically oriented
form, which is what you get by default when you just keep clicking the large *Add
Field* button on the form as you build the form.

Instead, you might use more than one column in your form.

![Figure 16: An example of a multiple column form layout.](../../../images/forms-layout-multicolumn.png)

As you can see, the multi-column approach saves a lot of space in the pictured
form. You can also use a mixed approach, with each row broken into a different
number of columns.

![Figure 17: There are many ways to lay out your Liferay forms.](../../../images/forms-layout-mixed.png)

If you're going to lay out your forms, you'll be working with two entities in
the form builder interface: Rows and Field Slots. You've already added and
configured fields. All you're doing here is learning to resize them and move them
around. Rows are just that, the rows of the form.

If you've added a field to the form, you've also added a row. By default,
clicking the large *Add Field* button on the form will add a field that
occupies the entire row. 

![Figure 18: By default, fields in your form will occupy an entire row.](../../../images/forms-form-row.png)

If you'd like, you can design your forms before you add the fields. Just start
resizing the row using the form layout functionality (drag the chevrons to
resize the field slots, or columns, in a row). As you make a field slot smaller, the new
space is taken up by another field slot. You'll end by having divided the row
into two columns. Resizing again will make three columns. Once you have the row
situated how you'd like, start adding the form fields.

![Figure 19: It's easy to resize form rows and make new form slots.](../../../images/forms-row-resizing.gif)
<!-- Remake with better quality for publication-->

You can also cut entire rows from the form and paste them elsewhere. Any fields
you added and configured will be pasted with the field. Once you click the cut
icon (the scissors), you'll be alerted to the available positions where you can
paste the row and its fields.

![Figure 20: Cut and past an entire row, including its fields, in your form layout.](../../../images/forms-cut-row.png)

Did you notice the delete icon (![Trash](../../../images/icon-trash.png))?
Clicking it deletes the entire row, with all its fields. 

Just like you can cut and paste an entire row, you can move an individual field
to any unoccupied field slot, in any row. Just open the field's actions menu by
clicking the Actions button (![Actions](../../../images/icon-actions.png)),
which will appear when you hover your mouse over the field in the layout.

![Figure 21: Move an individual field in your form layout.](../../../images/forms-move-field.png)

In addition to moving the field, you can edit the field or delete it.

+$$$

**Note:** If you're following along with the employment application form, it's
time to build the second page of the form (Very Personal Details). Combining
your form building knowledge with your ability to customize the form layout,
build the form to match the screenshot below. It will use form text, date,
single selection, text, and single checkbox (multiple selection on Liferay
Portal CE) fields, laid out in two columns.

![Figure 22: The second page of The Lunar Resort employment application.](../../../images/forms-page2.png)

$$$

Now it's done. Your users, including Guest users, can submit applications to The
Lunar Resort. The form is multi-page, full of features, and there's even some
processing that's done on form submission. As you can see, there's a lot of
flexibility built in to Liferay's Forms application. 
