---
header-id: creating-a-request-context-contributor
---

# Creating a Request Context Contributor

[TOC levels=1-4]

To better understand the Request Context Contributor, you'll explore how to
create one. First, you'll create the `SampleRequestContentContributor` class
file, which contains the `contribute` method that contributes a new field to
the context with a custom attribute. You can view the
[full project on Github](https://github.com/liferay/liferay-portal/tree/master/modules/apps/segments/segments-context-extension-sample).

1.  [Create a new module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  Inside the module, create a package named 
    `com.liferay.segments.context.extension.sample.internal.context.contributor`

3.  Create a Java class named `SampleRequestContentContributor`.

4.  Inside the file, insert the `@Component` declaration:

    ```java
    @Component(
        immediate = true,
        property = {
            "request.context.contributor.key=" + SampleRequestContextContributor.KEY,
            "request.context.contributor.type=boolean"
        },
        service = RequestContextContributor.class
    )
    ```

4.  Add the class declaration:

    ```java
    public class SampleRequestContextContributor
        implements RequestContextContributor {

    }
    ```

5.  Create the attribute that you're adding. In this case, it's just a static
    string.

    ```java
    public static final String KEY = "sample";
    ```

6.  Create the `contribute` method:

    ```java
    @Override
    public void contribute(
        Context context, HttpServletRequest httpServletRequest) {

    		context.put(KEY,
            GetterUtil.getBoolean(httpServletRequest.getAttribute("sample.attribute")));
    	}
    ```

To customize your field label or add a set of selectable options, you can add 
an optional `SegmentsFieldCustomizer` service associated to your contributed 
field by its key. Create one now.

1.  Inside the module, create a package named 
    `com.liferay.context.extension.sample.internal.field.customizer`

2.  Create a Java class named `SampleSegmentsFieldCustomizer`.

3.  Inside the file, insert the `@Component` declaration:

    ```java
    @Component(
        immediate = true,
        property = {
            "segments.field.customizer.entity.name=Context",
            "segments.field.customizer.key=" + SampleSegmentsFieldCustomizer.KEY,
            "segments.field.customizer.priority:Integer=50"
        },
        service = SegmentsFieldCustomizer.class
    )
    ```

4.  Create the class declaration:

    ```java
    public class SampleSegmentsFieldCustomizer implements SegmentsFieldCustomizer {

    }
    ```

5.  Create the `KEY` value:

    ```java
    public static final String KEY = "sample";
    ```

6.  Create the methods to provide a list of fields to be displayed when
    configuring the criteria.

    ```java
    @Override
    public List<String> getFieldNames() {
        return _fieldNames;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getLabel(String fieldName, Locale locale) {
    		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());

    		return LanguageUtil.get(resourceBundle, "sample-field-label");
    }

    private static final List<String> _fieldNames = ListUtil.fromArray(
    		new String[] {"sample"});
    ```

Once you deploy your extensions, the session section of the segment criteria
editor includes your new context-based field.

![Figure 1: The sample field appears.](../../images/context-based-field.png)

Great! You've created a Request Context Contributor!
