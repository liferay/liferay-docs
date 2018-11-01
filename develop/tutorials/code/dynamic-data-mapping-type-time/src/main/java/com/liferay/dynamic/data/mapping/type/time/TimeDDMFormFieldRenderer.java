package com.liferay.dynamic.data.mapping.type.time;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldRenderer;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Renato Rego
 */
@Component(
	immediate = true,
	property = "ddm.form.field.type.name=time",
	service = DDMFormFieldRenderer.class
)
public class TimeDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {

	@Override
	public String getTemplateLanguage() {
		return TemplateConstants.LANG_TYPE_SOY;
	}

	@Override
	public String getTemplateNamespace() {
		return "DDMTime.render";
	}

	@Override
	public TemplateResource getTemplateResource() {
		return _templateResource;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_templateResource = getTemplateResource(
			"/META-INF/resources/time.soy");
	}

    @Override
	protected void populateOptionalContext(
		Template template, DDMFormField ddmFormField,
		DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

		Map<String, Object> parameters =
			timeDDMFormFieldTemplateContextContributor.getParameters(
				ddmFormField, ddmFormFieldRenderingContext);

		template.putAll(parameters);
	}

	@Reference
	protected TimeDDMFormFieldTemplateContextContributor
		timeDDMFormFieldTemplateContextContributor;

	private TemplateResource _templateResource;

}