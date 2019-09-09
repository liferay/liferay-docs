package com.liferay.dynamic.data.mapping.type.time;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Renato Rego
 */
@Component(
    immediate = true, property = "ddm.form.field.type.name=time",
    service = {
        DDMFormFieldTemplateContextContributor.class,
        TimeDDMFormFieldTemplateContextContributor.class
    }
)
public class TimeDDMFormFieldTemplateContextContributor
    implements DDMFormFieldTemplateContextContributor {

    @Override
    public Map<String, Object> getParameters(
        DDMFormField ddmFormField,
        DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put(
            "placeholder", (String)ddmFormField.getProperty("placeholder"));
        parameters.put("mask", (String)ddmFormField.getProperty("mask"));

        return parameters;
    }

}