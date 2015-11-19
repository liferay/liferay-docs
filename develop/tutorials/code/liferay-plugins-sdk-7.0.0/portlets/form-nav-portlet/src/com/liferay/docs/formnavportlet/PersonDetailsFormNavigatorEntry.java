package com.liferay.docs.formnavportlet;


import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;

@Component(immediate = true, service = FormNavigatorEntry.class)
public class PersonDetailsFormNavigatorEntry 
		extends PersonBaseFormNavigatorEntry {

	@Override
	public String getCategoryKey() {
		return FormNavConstants.CATEGORY_KEY_PERSON_GENERAL;
	}

	@Override
	public String getKey() {
		return "details";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.docs.formnavportlet)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/details.jsp";
	}

}
