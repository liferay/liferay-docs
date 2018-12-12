# Form Serialization with the DDM IO API

The data collected in forms created by Liferay Forms can be stored in many
formats. <!--Link to 7.2 Forms Storage Adapters, when available-->

In the [tutorial on Forms Storage Adapters](LINK WHEN AVAILABLE), the serializer
that ships with @product@, `DDMFormValuesJSONSerializer`, is used for
serializing saved form data into a `java.io.File` object. To deserialize when
form data must be retrieved, `DDMFormValuesJSONDeserializer` is leveraged. the
values when retrieved by @product@. If the serialization functionality provided
by the Liferay Forms API is sufficient, there's no need to continue. However, if
you need to write your own serialization and deserialization functionality for
saving and retrieving form data, you're in the right place.

The example demonstrated here shows you how to create a serializer for saving
form data in YAML format. The same principles apply to writing a deserializer. 

The IO API now allows you to serialize/deserialize your form using  a
request/response structure, giving you control over what you need to proper
perform this task. This way you can create personalized
serializers/deserializers to match the format you want.

## Steps

We will use a serializer for this example but it applies to deserializers too.

Let's create a Form serializer for Yaml format.

First we need to create a class that implements  DDMFormSerializer. Ex:

@Component(immediate = true, property = "ddm.form.serializer.type=yaml") public
class DDMFormYamlSerializer implements DDMFormSerializer { .....  }

It's important to notice the property ddm.form.serializer.type=yaml. This
property will be used by our DDMFormSerializerTracker to find our yaml
serializer.

Now we must add our serializing logic to the overridden serialize method.It
takes a DDMFormSerializerSerializeRequest and returns a
DDMFormSerializerSerializeResponse  with serialized string in it.










@Override public DDMFormSerializerSerializeResponse serialize(
DDMFormSerializerSerializeRequest ddmFormSerializerSerializeRequest) {

        DDMForm ddmForm = ddmFormSerializerSerializeRequest.getDDMForm(); ...
        YOUR CODE GOES HERE ...  DDMFormSerializerSerializeResponse.Builder
        builder = DDMFormSerializerSerializeResponse.Builder.newBuilder(
        yamlObject.toString());

        return builder.build(); }

This is what you need to create your serializer.

Code It's very simple to use it. You need to get  your serializer from the
DDMFormSerializerTracker, pass the DDMFormSerializerSerializeRequest and receive
the DDMFormSerializerSerializeResponse with the serialized value.

DDMFormSerializer ddmFormSerializer =
ddmFormSerializerTracker.getDDMFormSerializer("yaml");

        DDMFormSerializerSerializeRequest.Builder builder =
        DDMFormSerializerSerializeRequest.Builder.newBuilder(ddmForm);

        DDMFormSerializerSerializeResponse ddmFormSerializerSerializeResponse =
        ddmFormSerializer.serialize(builder.build());

        return ddmFormSerializerSerializeResponse.getContent();






Documentation for TS Provide the underlying technical details that TS would need
to support the product (for example, design concepts), if you have not already
done so in the sections above. 

