package com.liferay.docs.formnavportlet;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorCategory;

@Component(
	immediate = true, property = {"service.ranking:Integer=10"},
	service = FormNavigatorCategory.class
)
public class PersonMiscFormNavigatorCategory implements FormNavigatorCategory {

	@Override
	public String getFormNavigatorId() {
		return FormNavConstants.FORM_NAVIGATOR_ID_PERSON_SETTINGS;
	}

	@Override
	public String getKey() {
		return FormNavConstants.CATEGORY_KEY_PERSON_MISC;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "miscellaneous");
	}
}
