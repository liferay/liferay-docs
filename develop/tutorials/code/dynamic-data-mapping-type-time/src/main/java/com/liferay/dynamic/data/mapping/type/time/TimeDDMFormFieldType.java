package com.liferay.dynamic.data.mapping.type.time;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeSettings;

import org.osgi.service.component.annotations.Component;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true,
	property = {
		"ddm.form.field.type.description=time-field-type-description",
		"ddm.form.field.type.display.order:Integer=10",
		"ddm.form.field.type.icon=time",
		"ddm.form.field.type.js.class.name=Liferay.DDM.Field.Time",
		"ddm.form.field.type.js.module=liferay-ddm-form-field-time",
		"ddm.form.field.type.label=time-field-type-label",
		"ddm.form.field.type.name=time"
	},
	service = DDMFormFieldType.class
)
public class TimeDDMFormFieldType extends BaseDDMFormFieldType {

	@Override
	public Class<? extends DDMFormFieldTypeSettings>
		getDDMFormFieldTypeSettings() {

		return TimeDDMFormFieldTypeSettings.class;
	}

	@Override
	public String getName() {
		return "time";
	}

}