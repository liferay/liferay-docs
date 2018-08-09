# Form Field Types [](id=form-field-types)

A form without fields is no form at all. To meet your form-building needs,
Liferay Forms provides useful and highly configurable field types. To see
examples of the form fields described here, visit the 
[Forms](/discover/portal/-/knowledge_base/7-1/forms)
section of the User Guide.

![Figure 1: Out-of-the-box form field types.](../images/forms-field-types.png)

**Paragraph:** This is static text on the form. Users do not enter data into 
form text fields. The form creator enters text that form users see displayed on 
the form. This is useful for longer instructions. 

![Figure 2: Use Paragraph fields to enter longer instructions on Form Pages.](../images/forms-paragraph.png)

**Text Field:** Users enter text into these fields. For example, a Full Name 
field is a text field. By default, a text field keeps all input on a single line 
of text. To accommodate longer responses, choose the multi-line setting when 
configuring the text field 
[as in this example](/discover/portal/-/knowledge_base/7-1/creating-and-managing-forms#building-a-form). 
You can put limits on the text users can enter (e.g., numbers from 1-10, email 
addresses, etc.) by using the text field's 
[validation options](/discover/portal/-/knowledge_base/7-1/validating-text-and-numeric-fields). 

![Figure 3: Text fields can be single line or multi-line.](../images/forms-multiline.png)

**Select from List:** Users select one option (or more, if configured to allow 
it) from a list of choices. Choices are entered manually, or automatically 
populated by a data provider. For example, a Country of Residence field can be 
selected from list field populated by a Countries of the World data provider. 

![Figure 4: Use a select from list field to let Users choose predefined options.](../images/forms-select-list.png)

**Single Selection:** Using a radio button, users select one option from a list 
of options displayed on the form. 

![Figure 5: Single selection fields allow only one selection.](../images/forms-single-selection.png)

**Date:** Users select a date using a date picker. For example, a Birth Date 
field uses the Date field type.

![Figure 6: Date fields show a date picker so Users enter a valid date.](../images/forms-date.png)

**Multiple Selection:** Users select one or more options from check boxes (or 
toggles, if configured).

![Figure 7: A multiple selection field using a toggle.](../images/forms-switcher.png)

**Grid:** Using radio buttons, users select from options laid out in rows and 
columns. One selection can be made per row. This is useful when the same 
response metric is needed for multiple questions. For example, a product survey 
form might ask users to rate a list of their product's characteristics as 
*Wonderful*, *Pretty Good*, *Not So Good*, or *Awful*. 

![Figure 8: Grid fields use the same options (columns) for multiple categories (rows).](../images/forms-grid.png)

**Numeric:** Users enter numeric data (integers or decimals) into numeric 
fields. Non-number input is not accepted. For example, configure a numeric field 
that accepts integers to ask users how many pets they have. 

![Figure 9: Numeric fields accept only numeric input.](../images/forms-numeric.png)

**Upload:** Users can select a file from the Documents and Media library or 
upload a file from their local filesystem. 

![Figure 10: An upload field.](../images/forms-upload.png)
