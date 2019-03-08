# Form Serialization with the DDM IO API

When a form creator saves a form in the Liferay Forms application, the Form
object is _serialized_ (converted) into JSON for storage in the @product@
database. That's the default behavior; if you need the form in a different
format just write your own serialization and deserialization code. Why would you
want to do that? Maybe you think JSON storage is not secure, or you have another
tool that can consume the form if it's in YAML. Whatever your reasons, the form
can be stored in any format as long as you write a `DDMFormSerializer`, and its
corresponding `DDMFormDeserializer` with the proper logic.

First consider what form data looks like by default, in JSON. A simple form, _My
Form_, with one text field, _Full Name_, is first created as a `DDMForm` Java
object, then _serialized_ into JSON for storage in the @product@ database when
saved.

    {
        "availableLanguageIds":["en_US"],
        "successPage":{"body":{},
        "title":{},
        "enabled":false},
        "defaultLanguageId":"en_US",
        "fields":[{
            "autocomplete":false,
            "ddmDataProviderInstanceId":"[]",
            "dataType":"string",
            "predefinedValue":{"en_US":""},
            "tooltip":{"en_US":""},
            "readOnly":false,
            "label":{"en_US":"Full Name"},
            "type":"text",
            "required":false,
            "showLabel":true,
            "displayStyle":"singleline",
            "fieldNamespace":"",
            "indexType":"keyword",
            "visibilityExpression":"",
            "ddmDataProviderInstanceOutput":"[]",
            "repeatable":false,
            "name":"FullName",
            "options":[{"label":{"en_US":"Option"},"value":"Option"}],
            "localizable":true,
            "tip":{"en_US":""},
            "placeholder":{"en_US":""},
            "dataSourceType":"",
            "validation":{"expression":"","errorMessage":""}
        }]
    }

From its initial state as a `DDMForm` Java object, the form is _serialized_ into
JSON format, and upon retrieval from the database, it's _deserialized_: 
the JSON object representing the form is translated back into a `DDMForm` Java
object, with all its requisite fields. For example, the JSON for the above
example holds each form field in the `fields` attribute. To translate
this back into the necessary `DDMForm` object, first parse the data contained in
the JSON object into an actual form field using your deserialization logic.
Here's the logic from `DDMFormJsonDeserializer` that parses the JSON `"fields"`
element into a list of `DDMFormFields`:

	protected List<DDMFormField> getDDMFormFields(JSONArray jsonArray)
		throws PortalException {

		List<DDMFormField> ddmFormFields = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDMFormField ddmFormField = getDDMFormField(
				jsonArray.getJSONObject(i));

			ddmFormFields.add(ddmFormField);
		}

		return ddmFormFields;
	}

Now calling `DDMForm.setDDMFormFields(ddmFormFields)` in the deserializer
completes the translation process from the JSON array back to a `DDMFormField`
object that the `DDMForm` needs.

If you'd like to store forms in a different format, provide custom
_serialization_ and _deserialization_ functionality.

