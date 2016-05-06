# Creating Advanced Forms

Liferay's built-in Forms application supports your most basic form building
needs (something like a one question survey), and your most advanced (like a
mortgage application, or conference sign-up form). In [a separate article](discover/portal/-/knowledge_base/7-0/basic-forms), learn
about the basics of form creation:

-  Navigating to the Forms application
-  Adding form fields, and supported field types
-  Configuring form fields
-  Exporting form entries
-  Deleting form entries

This article covers more advanced use cases for forms, and a basic knowledge of
the Forms application is assumed. Here's a current list of the features you can
use in your forms:

- Populate a Select or Radio field with a REST Data Provider
- Make a field appear based on the value entered into another field
- Add extra pages to the form
- Enable workflow for the form
- Provide a default value (entered if left alone by the user) or a placeholder
  value (not entered if left alone by user) for each field
- Validate fields using a number of different criteria

Aside from those features, which enhance the building of your forms, there are
some settings and processing features to look at:

- Store results in JSON
- Enable CAPTCHA for a form
- Redirect to a different URL after a successful form submission
- Send an email notification to administrators whenever a form is submitted

More features are being planned, so don't despair if you need a feature that's
not yet present in the Forms application.
<!--Add a list of planned features?-->

## Lunar Resort Application

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

## Creating Form Pages

Liferay's Forms application supports multi-page forms. To add one, click the
Actions (![Action](../../../images/icon-actions.png)) button at the top right hand
corner of the form, and then click *Add New Page*.

![Figure x: You can add new pages or reset the current page from the Page
Actions menu.](../../../images/forms-page-actions.png)

You also have the option to *Reset Page*. If you click this, the title,
description, and any fields you added to the page will be deleted. It gives you
a blank slate.

The Lunar Resort employment form should have three pages with clear page
descriptions:

-  Personal Details: This page requires you to give us some pretty personal information.
-  Very Personal Details: This page requires you to give us some very personal information.
-  Extremely Personal Details: This page requires you to give us some extremely personal information.

Once you create the form's pages, you'll see how navigating the pages is easy
with the form page navigation wizard.

![Figure x: Multi-page forms have a default *Wizard Mode* that makes navigating
the form easy for users.](../../../images/forms-pagination-modes.png)

Once you have the form pages created, navigate back to the first page, Personal
Details.

## Adding Placeholder Text and Predefined Values

To populate the employment application form for The Lunar Resort, you should
start by adding some of the usual fields:

-  **Full Name:** Add a required text field titled *Full Name*, with a description that reads
   *Enter your full name, no matter how long.* Under Show More Options, enter
Placeholder Text: *e.g., Maximillian Aurelius Piroux the 11th*.

    ![Figure x: The Full Name field in the employment form. It has a placeholder value to help users understand what you are looking for.](../../../images/forms-employment-full-name.png)

+$$$

**Note:** There's an important difference between *Predefined Values* and
*Placeholder Text*. If a field is left untouched by the user, the predefined
value will be submitted, while placeholder text is not submitted (it's as if the
user left the field blank).

$$$

Remember that placeholder values will not be submitted if the field is left
blank, so you don't have to worry about getting a bunch of applications from
*Maximillian Aurelius Piroux the 11th*.

## Validating Text Fields

The HR department needs to weed prospective employees out if they're not mature
enough. If the user is under a certain age, make sure to ask them if they've
ever been away for an extended period of time. This can be done using a text
field that accepts only numbers (age), and then a couple of fields to probe
whether the applicant can handle being away from home for a long period of time.
In those fields, you can use a Field Visibility Expression so they only appear if the
user enters a number less than *30*.

-  **Age:** Add a required text field called *Age*, with a description that says, *Enter
   your age in years*, and the placeholder text *Numbers Only*.

    Enable Validation and select *Number*, *is greater than*, and enter *0*. You
can enter an error message *You must enter your age in years, and it must be
greater than 0*.

    ![Figure x: The Age field in the employment form. It uses validation to
ensure that a number greater than zero is entered.It has a
placeholder value to help users understand what you are looking
for.](../../../images/forms-employment-age.png)

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

    ![Figure x: You can validate text submissions for text fields.](../../../images/forms-text-validation.png)
-  Number: If you select *Number*, only number submissions are allowed for your
   form field.
    -  Is greater than or equal to: Specify the smallest number that can be
       entered.
    -  Is greater than: Entries must be greater than the specified number.
    -  Is equal to: Allow only entries equal to the number you specify.
    -  Is less than or equal to: Specify the largest number that can be
       submitted.
    -  Is less than: Entries must be less than the specified number.

    ![Figure x: You can validate number submissions for text
fields.](../../../images/forms-number-validation.png)
It can be helpful to alert users to your validation rules in help text or
placeholder text (for example, *You must enter a number greater than 0*).

If you mark a field as *Required*, that's another form of validation. Liferay is
going to make sure that the field is not empty. If it is, an error message will
appear, directing the user to enter something into the required field.

$$$

Now you need to create some fields that will be displayed only if the user enters any
number less than *30*.

## Using Field Visibility Expressions

Sometimes you only want a form field to appear if a certain value is entered in
another field. In the Lunar Resort  application for employment, you want to ask
a few additional questions to users who are less than 30 years old, to make sure
they're comfortable with being away from home for a long period of time.

-  **Maximum Time Away From Home:** This is a required select field with the description
   *What's the longest time, in consecutive months, that you've spent away from
your permanent residence?* Give it the following options: *<1*, *1-3*, *6-12*,
*>12*.

    Enter a Predefined Value of *<1*.

    Now comes the interesting part. Under Field Visibility Expression, enter
*lessThan(Age,"30")*.
<!-- Find out the real syntax and update -->

+$$$

**Note:** There are several expressions you can use to control field visibility.
<!-- Find this and doc it here. -->

$$$

## Populating Select Fields with REST Data Providers

The Lunar Resort application form needs no less than five select fields, all
populated with the complete list of the countries of earth. Read the sidebar
note below to find out why this field is needed (it's just a fictional story, so
skip if you don't have time for some light entertainment).

+$$$

**Note:** Interstellar citizenship rules are strange, and since the moon
declared independence from earth (Lunar Independence Day is a big deal at The
Lunar resort), it has its own set of citizenship rules. Among other things, the
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

Unless you have an intern, you don't want to enter all the countries of earth
into five different select fields. No, you want to populate the fields using a
Data Provider.

### Adding and Configuring a Data Provider

To set up a data provider that you can use in your forms, navigate to the Forms
application in *Site Administration* &rarr; *Content*, and click the Options
(![Options](../../../images/icon-options.png)) button.

![Figure x: Set up a data provider that can be reused in all your forms.](../../../images/forms-options-menu.png)

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
that what you're doing when setting up a data provider is accessing a [JSON web
service that has been registered in
Liferay](develop/tutorials/-/knowledge_base/7-0/registering-json-web-services).
Service registration is a prerequisite for using a service in the Forms
application to set up a data provider. To find a list of the ready-to-use
registered JSON web services, navigate to `http://localhost:8080/api/jsonws`
(assuming you're running Liferay locally). If you do that, you'll be able to
browse the available services that you can use to populate your list. The
services useful to you in the Forms application will get a list of something.
The service you are calling in the data provider you set up above gets a list of
countries. Find the `get-countries` JSON web service and click on it, then click
*Invoke*. The *Result* tab will have a list of countries using JSON syntax, like
this:

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

The *Displayed JSON Atrribute* field is where you specify which field you want
to use to populate your select list. In this case, `nameCurrentValue` provides
the full, properly capitalized country name, and that's what you want users of
the form to see.

The *Stored JSON Attribute* field is where you set which field will be stored in
the database for the form entry. The `name` field was selected in this example.

Once you set up the data provider, click *Save*. Now you can set up five select
fields that list all the countries of earth in no time.

### Using a Data Provider in a Select Field

Back in the application form, go to the third page, Extremely Personal
Details, and add a required Select field named *Relocation Country: First
Choice*. For Help Text, write *If relocation becomes necessary, to which country
on earth would you most like to go?*. Select the radio button to *Create List
From Data Provider*, and choose *Countries List*. Click *Save*.

If you're building the example application form, do the same thing four more
times, using field labels to reflect the choice number. For example, you'd call
the second select field *Relocation Country: Second Choice*, and so on until you
have five of them. You can decide what help text to provide, if any.

That concludes the actual development of the form. The rest of the article
covers features of the Forms application that you'll use during or after the
form entry is submitted.

<!--Picture of full form?-->
<!-- Should these Form Settings options be covered separately? -->

## Enabling CAPTCHA on Form Submissions

You're probably familiar with CAPTCHA, the program used to prevent a bot from
submitting forms. It's often used in [Login applications](discover/deployment/-/knowledge_base/7-0/logging-in-to-liferay),
but you can use it in the Forms application, too. 

![Figure x: You can enable CAPTCHA for your form in the Form Settings window.](../../../images/forms-settings-captcha.png)

To enable CAPTCHA, click the form's *Options*
(![Options](../../../images/icon-options.png)) button, then *Settings*.
Enable the *Require CAPTCHA* setting, click *Done*, save the form, and that's
all there is to it!

![Figure x: Once you enable CAPTCHA, your form has protection against bot submissions.](../../../images/forms-captcha.png)

You can move on to enabling workflow for your form.

## Enabling Workflow for Forms
<!--For story purposes, we could have the workflow developed in the Designing
Workflow article (not yet written) be used in this article. An applicant submits
the form, then it goew to an HR rep, tehn to a lower management person, then an
upper management person, and sends emails to the applicant along the way.
Something like that.-->

To enable [workflow](discover/portal/-/knowledge_base/7-0/using-workflow) for a form, open the Form Settings window (click the *Options*
(![Options](../../../images/icon-options.png)) button &rarr; *Settings*). In the
Select a Workflow field, select the workflow you want to use for the form, then
click *Done* and save the form.

![Figure x: Enable workflow on a form from its Form Settings window.](../../../images/forms-workflow.png)

Because each form can be so different, it makes sense to configure workflow for
forms in each form's Form Settings section.

## Setting Up Form Notifications

It's possible to have a notification email sent each time a form entry is
successfully submitted. Open the form's Form Settings section (click the
*Options* (![Options](../../../images/icon-options.png)) button &rarr;
*Settings*). Click the *Email Notificaitons* tab, enable the option to *Send an
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

## Redirecting Users after Form Submission

You can send users to any URL you want upon form submission. You might have a
particular *Thanks for your submission!* type page with a link to the home page,
or you might want to direct a user somewher specific based on the form they just
submitted.

Whatever your use case is, you can set up a redirect URL by opening the form's
Form Settings section (click the *Options*
(![Options](../../../images/icon-options.png)) button &rarr; *Settings*). In the
*Redirect URL* just type the URL, and that's it.





<!-- There's a bug that makes switching pagination modes not work. Revisit when
it's resolved.
Create those pages. Once you do, click the
Actions (![Action](../../../images/icon-actions.png)) button again, and you'll
see that there are new options:

-  **Add New Page:** This adds a new page, of course.
-  **Delete Current Page:** This deletes the current page.
-  **Switch Pagination Mode:** Two pagination modes are supported--
-->



