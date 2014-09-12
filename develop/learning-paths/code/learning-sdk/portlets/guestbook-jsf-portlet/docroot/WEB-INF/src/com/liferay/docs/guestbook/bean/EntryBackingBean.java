/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.liferay.docs.guestbook.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.persistence.EntryUtil;
import com.liferay.faces.portal.context.LiferayFacesContext;

import java.io.IOException;


/**
 * @author  Vernon Singleton
 */
@ManagedBean
@RequestScoped
public class EntryBackingBean extends AbstractBackingBean {
	
	private Boolean hasAddPermission;

	public void add() {
		Entry entry = EntryUtil.create(0L);
		LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
		entry.setGroupId(liferayFacesContext.getScopeGroupId());
		entry.setGuestbookId(guestbookModelBean.getSelectedGuestbook().getGuestbookId());
		guestbookModelBean.setSelectedEntry(entry);
		viewBean.entry();
	}

	public void delete(Entry entry) {

		try {
			EntryLocalServiceUtil.deleteEntry(entry.getEntryId());
			addGlobalSuccessInfoMessage();
		}
		catch (Exception e) {
			addGlobalUnexpectedErrorMessage();
			logger.error(e);
		}

		guestbookModelBean.forceEntriesReload();
		viewBean.master();
	}

	public void edit(Entry entry) {
		guestbookModelBean.setSelectedEntry(entry);
		viewBean.entry();
	}
	
	public Boolean getHasAddPermission() {

		if (hasAddPermission == null) {
			LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();
			hasAddPermission = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(
					scopeGroupId, "com.liferay.docs.guestbook.model", scopeGroupId, "ADD_ENTRY"
					);
		}

		return hasAddPermission;
	}
	
	public void setHasAddPermission(Boolean hasAddPermission) {
		this.hasAddPermission = hasAddPermission;
	}

	public void save() {

		Entry entry = guestbookModelBean.getSelectedEntry();

		try {

			if (entry.getEntryId() == 0) {
				entry = EntryLocalServiceUtil.addEntry(entry);
			}
			else {
				entry = EntryLocalServiceUtil.updateEntry(entry);
			}

			addGlobalSuccessInfoMessage();
		}
		catch (Exception e) {
			addGlobalUnexpectedErrorMessage();
			logger.error(e);
		}

		guestbookModelBean.forceEntriesReload();
		viewBean.master();
	}

}
