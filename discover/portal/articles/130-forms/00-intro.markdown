# Collecting Information from Users [](id=collecting-information-from-users)

Collecting information from your site's Users is important, whether you're
asking them to submit a brief survey or apply for a mortgage. In either case,
you need to design a form. Liferay gives you almost limitless form building
capability with the Forms application (also referred to as Liferay Forms). For a
complete list of form fields available in @product@, visit the [form fields
reference article](/discover/reference/-/knowledge_base/7-0/form-field-types).

Because the complexity of use cases for the Forms application varies from
needing only one basic input field, to needing many pages of fields with many
different configurations, it makes sense to show you how to quickly build simple
forms that can be ready for use very quickly, and show you a case where a form
is long, complex, and needs to be well planned in order to capture the needed
information. Here's a sampling of the what the Forms application can do right
now (yeah, there's even more planned for future releases):

- Populate a Select or Radio field with a REST Data Provider
- Make a field appear based on the value entered into another field
- Add extra pages to the form
- Enable CAPTCHA for a form
- Store results in JSON
- Enable workflow for the form
- Redirect to a different URL after a successful form submission
- Send an email notification to administrators whenever a form is submitted
- Provide a default value (entered if left alone by the user) or a placeholder
  value (not entered if left alone by user) for each field
- Validate fields using a number of different criteria

If you only need a very basic form, don't worry. Despite this long list of more
complex options, developing a simple, elegant form to suit basic needs takes
little effort. The next article will cover basic form building, and will be
followed by a more complete, complex tutorial on the Forms application.

# Forms and Lists [](id=forms-and-lists)

When you need a form, what you're really looking for is data. @product@ has two
applications for building forms to collect precisely the data you need:

1.  [Liferay
    Forms](/discover/portal/-/knowledge_base/7-0/collecting-information-from-users):
    @product@'s primary form building application is for the simplest one or two
    question survey to the most complex, multi-page, homeowners insurance
    application containing rules and lists populated by a REST data
    provider.

2.  [Dynamic Data
    Lists (DDL)](/discover/portal/-/knowledge_base/7-0/creating-simple-applications):
    Provides a user interface tool for building reusable form- and list-based
    applications intended for display on pages, using 
    [templates](/discover/portal/-/knowledge_base/7-0/using-templates-to-display-forms-and-lists).

+$$$

**Kaleo Forms:** If you're a Liferay Digital Enterprise customer, there's a
third form building tool called 
[Kaleo Forms](https://customer.liferay.com/documentation/7.0/admin/-/official_documentation/portal/workflow-forms). 
It integrates form building with workflow to create form-based business
processes, like a Conference Room Checkout Form, or a Support Ticket Process so
support tickets go through the proper channels on their way to resolution. Read
more about Kaleo Forms in the 
[workflow section](https://customer.liferay.com/documentation/7.0/admin/-/official_documentation/portal/workflow-forms).

$$$

## Which Form Builder Should I Use? [](id=which-form-builder-should-i-use)

Liferay Forms (also referred to as Forms) is a relatively new application, first
appearing in @product@ version 7.0. It's the default form builder in @product@.
If you can use Liferay Forms for your use case, you should.

So the question "Which form builder should I use?" can be restated to "When
should I use Dynamic Data Lists?"

- Use Dynamic Data Lists (DDL) if you need a way for users to enter data, *and*
    you need to display the data in the user interface.

- Use DDL if you need to style your lists and forms with templates.

- Use DDL if there's a field type you need that's not included (yet) in Liferay
    Forms. These are the field types included in DDL that *are not* in Liferay
    Forms at the time of this writing:
        - Geolocation
        - Web Content
        - Documents and Media
        - Link to Page

It's important to note that these (and more!) form field types will be
included in future versions of the Liferay Forms application.

Liferay Forms gives you a lot of form design flexibility, whether you need
a [simple form](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms)
or a [complex form](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms) 
with advanced features. 
[Form pages](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms#creating-form-pages)
and 
[form field layout](/discover/portal/-/knowledge_base/7-0/creating-advanced-forms#form-layouts)
tools make form layouts flexible. While these elements are enough for most
forms, more features are planned for Liferay Forms, and the key features of DDL
will be merged into Liferay Forms as well. Once that happens, you'll have
a single form building application, Liferay Forms. If you're a heavy DDL user,
don't worry. Liferay is committed to preserving the core DDL features by
integrating them into Liferay Forms. 

When all form building features are fully merged into Liferay Forms, the best
features of DDL, all the new features of Liferay Forms, and all future
improvements will be in one application. Now is the time to familiarize yourself
with Liferay Forms and begin using it for all your form building needs, except
for the narrow use cases described above.
