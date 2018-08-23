# What's New with Liferay Forms [](id=whats-new-with-liferay-forms)

Liferay Forms is evolving. This article compiles the prominent changes and
additions to Forms. More details on these features are found in the other
articles of this section.

**Form Rules**
: Set dynamic form behavior by specifying conditions (*if this happens in field
X*) and actions (*then do this: X*). Available actions include Show, Enable,
Require, Autofill, Calculate, and Jump to Page.

![Figure 1: Form Rules trigger an action in response to some condition.](../../images/forms-rule-development.png)

**Form Translation**
: Provide translations of the same form into the platform's supported languages.
Form Users select the language they'd like to see.

![Figure 2: Translate a Form into a supported language.](../../images/forms-translation.png)

**New Fields and Properties**
: File Upload fields let logged in Users upload documents to the Form. Numeric
fields validate that input is numeric. Grid fields let Users select from options
laid out in rows and columns. One selection can be made per row. 

![Figure 3: File Upload, Grid, and Numeric fields are new.](../../images/forms-sidebar.png)

**Element Sets**
: Create reusable Form fragments that can be used to quickly populate new Forms
with common fields and configurations.

![Figure 4: Reusable Element Sets are great for quickly populating new forms.](../../images/forms-element-set.png)

**Autosave** 
: By default, a Form is auto-saved every minute (without the Form builder
clicking *Save*). The duration is configurable in Control Panel &rarr;
Configuration &rarr; System Settings &rarr; Forms. Auto-saves do not propagate to
published Forms. 

![Figure 5: Autosaving Forms ensures your recent changes are preserved.](../../images/forms-autosave-interval.png)

**Autocomplete Text Fields**
: Using a Data Provider or manually typed options, configure a Text field to
offer Users autocompleted values to choose from as they type in the Text field.
Set up autocomplete in the Text field's Properties.

![Figure 6: A User beings typing in a Text field and is pleasantly surprised by a list of
autocomplete options.](../../images/forms-autocomplete.png)

**Form Success Page**
: Add a Form Page that displays after a successful Form submission.

![Figure 7: A Success Page provides feedback to the User that the form
Submission was successful.](../../images/forms-success-page.png)

**Duplicate Forms and Form Fields**
: Duplicate a Form to avoid building a similar one from scratch. Duplicate Form
Fields if you need multiple similarly configured fields.

![Figure 8: Duplicate a Form to get a head start on your next, similar Form.](../../images/forms-duplicate.png)

**Validate Text Field Entries**
: Add validation logic to a Text field and create custom messages for form
validation errors. Use regular expressions in your validation by choosing the
_Does not match_ value in the _If Input_ Select from List field.

![Figure 9: Validate Text fields to ensure Users enter the correct data.](../../images/forms-text-validation.png)

**Data Provider Enhancements**
: Data Providers are still used to populate Select from List fields. The Data
Provider setup form has been overhauled, and you can import and export Data
Provider definitions. Data Providers are also linked with the Autocomplete
feature and the Autofill rule.

![Figure 10: The Data Provider interface was redesigned and new options added.](../../images/forms-data-provider.png)

**XLS Form Entries Data**
: Export entries to XLS format.

![Figure 11: Export Form Entries to XLS. CSV and XML are also available.](../../images/forms-xls.png)

More enhancements are planned for the future. 
