package com.liferay.docs.formnavportlet;

import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

import com.liferay.portal.kernel.language.LanguageUtil;
import java.util.Locale;

public abstract class PersonBaseFormNavigatorEntry 
		extends BaseJSPFormNavigatorEntry<Object>
		implements FormNavigatorEntry<Object> {


	@Override
	public String getFormNavigatorId() {
		return FormNavConstants.FORM_NAVIGATOR_ID_PERSON_SETTINGS;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}
}
