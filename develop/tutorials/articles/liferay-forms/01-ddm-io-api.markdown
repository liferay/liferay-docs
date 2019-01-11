# Form Serialization with the DDM IO API

When a form creator saves a form, the form itself can be stored in any format.

The default form storage format is JSON. A simple form, _My Form_, with one text
field, _Full Name_, is first created as a `DDMForm` Java object, then
_serialized_ into JSON for storage in the @product@ database when saved.

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

From it's Java object initial state as `DDMForm`, the form is _serialized_ into
JSON format, and upon retrieval from the database, it's _deserialized_, meaning
the backing JSON object representing the form is translated back into a
`DDMForm` Java object, with all its requisite fields. For example, the JSON for
the above example holds each form field in the `fields` attribute. To translate
this back into the necessary `DDMForm` object (deserialization), first parse the
data contained in the JSON object into an actual form field using your
deserialization logic. Here's the logic from `DDMFormJsonDeserializer` that
parses the JSON `"fields"` element into a list of `DDMFormFields`:

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
completes the translation process, from the JSON array back to a `DDMFormField`
object that the `DDMForm` needs.

If you'd like to store forms in a different format, provide custom
_serialization_ and _deserialization_ functionality.

The DDM IO API serializes and deserializes forms using  a request/response
structure. The example here creates a serializer for saving form data in [YAML](https://yaml.org/)
format. The same principles shown here apply to writing a deserializer. 

## Creating the Serializer

The steps used to create this example serializer apply equally to
deserializers.

To serialize form data into YAML:

1. Create a class that implements `DDMFormSerializer`:

        @Component(immediate = true, property = "ddm.form.serializer.type=yaml") public
        class DDMFormYamlSerializer implements DDMFormSerializer { .....  }

The property `ddm.form.serializer.type=yaml` marks the Component so that
`DDMFormSerializerTracker` can find the YAML serializer.

2.  Add the serializing logic to the overridden serialize method. It takes a
    `DDMFormSerializerSerializeRequest` and returns a
    `DDMFormSerializerSerializeResponse`  with the serialized string in it.

        @Override public DDMFormSerializerSerializeResponse serialize(
        DDMFormSerializerSerializeRequest ddmFormSerializerSerializeRequest) {

                DDMForm ddmForm = ddmFormSerializerSerializeRequest.getDDMForm(); 

                ...YOUR CODE FOR BUILDING A YAML OBJECT GOES HERE ...  

                DDMFormSerializerSerializeResponse.Builder builder = 
                    DDMFormSerializerSerializeResponse.Builder.newBuilder(yamlObject.toString());

                return builder.build(); }

This is what you need to create your serializer. Of course, _YOUR CODE FOR
BUILDING A YAML OBJECT GOES HERE_ requires some explanation. While you can
do whatever you want here, there are several things you really ought to
do:

**Add the available Language IDs:** Since you have the `DDMForm` object from the
    request, call `ddmForm.getAvailableLocales()`.

**Add the default Language ID:** Get this from the `DDMForm` object by calling
    `ddmForm.getDefaultLocale()`.

**Add the Form Fields:** Get these from the `DDMForm` object by calling
    `ddmForm.getDDMFormFields()`.

**Add any Form Rules:** Get them form the `DDMForm` object with
    `ddmForm.getDDMFormRules()`.

**Add Success Page Settings:** Get these from the `DDMForm` with
    `ddmForm.getDDMFormSuccessPageSettings()`.

All these are done in the default form serializer, `DDMFormJSONSerializer`.

If you have the @product@ source code, you can find the default serializer in

    modules/apps/dynamic-data-mapping/dynamic-data-mapping-io/src/main/java/com/liferay/dynamic/data/mapping/io/internal/DDMFormJSONSerializer.java

You didn't create serialization code for no reason. You'll want to call it from
somewhere.

## Calling the Serializer 

To get properly serialized form content, follow these steps:

1.  Get the serializer from the `DDMFormSerializerTracker`, passing in the value
    of the `ddm.form.serializer.type` property.

2.  Construct a `DDMFormSerializerSerializeRequest` object using its nested
    static `Builder` class.

3.  Call the `serialize` method you wrote in the last section to create the
    `DDMFormSerializerSerializeResponse`, passing the
    `DDMFormSerializerSerializeRequest`
    object, via a call to the `Builder`'s `build` method.

4.  Get the serialized form content from the
    `DDMFormSerializerSerializeResponse` by calling its `getContent` method.

Here's a code example:

    DDMFormSerializer ddmFormSerializer =
    ddmFormSerializerTracker.getDDMFormSerializer("yaml");

    DDMFormSerializerSerializeRequest.Builder builder =
    DDMFormSerializerSerializeRequest.Builder.newBuilder(ddmForm);

    DDMFormSerializerSerializeResponse ddmFormSerializerSerializeResponse =
    ddmFormSerializer.serialize(builder.build());

    ddmFormSerializerSerializeResponse.getContent();

You can create a serializer for any format that can be saved in the database as
a String. Once you create the serializer, make it the default by changing the
storage format in the Form's Settings menu.

