# Forms and Lists [](id=forms-and-lists)

When you need a form in @product@, what you really need is data. @product@ has
two applications for building forms to collect precisely the data you need:

1.  [Liferay Forms](/discover/portal/-/knowledge_base/7-0/collecting-information-from-users): @product@'s primary form building application lets you
    build forms ranging from the simplest one or two question survey to the most
    complex, multi-page, homeowners insurance application form featuring form
    rules and lists populated by a REST data provider.

2.  [Dynamic Data
    Lists(DDL)](/discover/portal/-/knowledge_base/7-0/creating-simple-applications):
    Provides a user interface tool for building reusable form- and list-based
    applications intended for display on pages in @product@. It's the ability to
    mold the display using [templates](/discover/portal/-/knowledge_base/7-0/using-templates-to-display-forms-and-lists) that sets DDL apart.

+$$$

**Kaleo Forms:** If you're a Liferay Digital Enterprise customer, there's a
third form building tool in @product@ called [Kaleo
Forms](https://customer.liferay.com/documentation/7.0/admin/-/official_documentation/portal/workflow-forms). It integrates form
building with workflow to create form based business processes, like a
Conference Room Checkout Form, or a Support Ticket Process so support tickets go
through the proper channels on their way to resolution. Read more about Kaleo
Forms in the [workflow
section](https://customer.liferay.com/documentation/7.0/admin/-/official_documentation/portal/workflow-forms).

$$$

<!-- Please leave the absolute links to the customer portal in the above
sidebar. This is to ensure that folks reading on dev.liferay.com are directed to
the article on customer.liferay.com, instead of getting a Resource Not Found error.
-->

## Which Form Builder Should I Use? [](id=which-form-builder-should-i-use)

Liferay Forms is a relatively new application, first appearing in @product@
version 7.0. It's intended to be the default form builder in @product@. If you
can use Liferay Forms for your use case, you should.

So the question "Which form builder should I use?" can be restated to "When
should I use Dynamic Data Lists?"

- Use Dynamic Data Lists (DDL) if you need a way for users to enter data, *and*
    you need to display the data in the user interface.

- Use DDL if you need to style your lists and forms with templates.

- Use DDL if there's a field type you need that's not included (yet) in Liferay
    Forms. The field types included in DDL that *are not* in Liferay Forms at
    the time of this writing include:
        - Geolocation
        - Web Content
        - Documents and Media
        - Link to Page

        It's important to note that these (and more!) form field types will be
        included in future versions of the Liferay Forms application.

Liferay Forms gives you a lot of control over how your form is displayed,
allowing you to make [form
pages](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms#creating-form-pages),
and adjust the [form
layout](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms#form-layouts)
of the fields. These styling elements are enough for most forms. Over time, more
features will be added to Liferay Forms, and DDL might someday become obsolete,
in favor of a single form building application. If you're a heavy user of DDL,
don't worry, Liferay is committed to preserving and enhancing the features
provided by DDL; they just might be moved to Liferay Forms. Now is the time to
familiarize yourself with Liferay Forms and begin using it for all your form
building needs, except for the narrow use cases described above.
