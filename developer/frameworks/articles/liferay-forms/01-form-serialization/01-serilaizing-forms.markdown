---
header-id: serializing-forms
---

# Serializing Forms

[TOC levels=1-4]

The DDM IO API serializes and deserializes forms using a request/response
structure. The example here creates a serializer for saving form data in
[YAML](https://yaml.org) format. The same principles shown here apply to
writing a deserializer. 

To serialize form data into YAML:

1. Create a class that implements `DDMFormSerializer`:

        @Component(immediate = true, property = "ddm.form.serializer.type=yaml") public
        class DDMFormYamlSerializer implements DDMFormSerializer { .....  }

The property `ddm.form.serializer.type=yaml` marks the Component so that
`DDMFormSerializerTracker` can find the YAML serializer.

2.  Add the serializing logic to the overridden `serialize` method. It takes a
    `DDMFormSerializerSerializeRequest` and returns a
    `DDMFormSerializerSerializeResponse` with the serialized string in it.

        @Override public DDMFormSerializerSerializeResponse serialize(
        DDMFormSerializerSerializeRequest ddmFormSerializerSerializeRequest) {

                DDMForm ddmForm = ddmFormSerializerSerializeRequest.getDDMForm(); 

                ...YOUR CODE FOR BUILDING A YAML OBJECT GOES HERE ...  

                DDMFormSerializerSerializeResponse.Builder builder = 
                    DDMFormSerializerSerializeResponse.Builder.newBuilder(yamlObject.toString());

                return builder.build(); }

This is what you need to create your serializer. Of course, `YOUR CODE FOR
BUILDING A YAML OBJECT GOES HERE` requires some explanation. While you can
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

