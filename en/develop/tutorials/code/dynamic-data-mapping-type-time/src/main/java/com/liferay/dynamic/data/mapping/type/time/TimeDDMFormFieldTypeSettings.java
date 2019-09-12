package com.liferay.dynamic.data.mapping.type.time;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;

/**
 * @author Renato Rego
 */
@DDMForm
@DDMFormLayout(
    paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
    value = {
        @DDMFormLayoutPage(
            title = "%basic",
            value = {
                @DDMFormLayoutRow(
                    {
                        @DDMFormLayoutColumn(
                            size = 12,
                            value = {
                                "label", "required", "tip", "mask",
                                "placeholder"
                            }
                        )
                    }
                )
            }
        ),
        @DDMFormLayoutPage(
            title = "%properties",
            value = {
                @DDMFormLayoutRow(
                    {
                        @DDMFormLayoutColumn(
                            size = 12,
                            value = {
                                "dataType", "name", "showLabel", "repeatable",
                                "type", "validation", "visibilityExpression"
                            }
                        )
                    }
                )
            }
        )
    }
)
public interface TimeDDMFormFieldTypeSettings
    extends DefaultDDMFormFieldTypeSettings {

    @DDMFormField(label = "%mask", predefinedValue="%I:%M %p")
    public String mask();

    @DDMFormField(label = "%placeholder-text")
    public String placeholder();

}