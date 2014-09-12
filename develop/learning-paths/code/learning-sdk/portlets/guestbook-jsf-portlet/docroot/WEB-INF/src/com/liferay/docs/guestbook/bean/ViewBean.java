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

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.persistence.GuestbookUtil;
import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * @author  Vernon Singleton
 */
@ManagedBean(name = "viewBean")
@ViewScoped
public class ViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Logger
	protected static final Logger logger = LoggerFactory.getLogger(ViewBean.class);
	
	public static final String DEFAULT_GUESTBOOK_NAME = "Main";

	private boolean editingGuestbook;
	private boolean editingEntry;

	public void entry() {
		editingEntry = true;
		editingGuestbook = false;
	}

	public void guestbook() {
		editingEntry = false;
		editingGuestbook = true;
	}

	public void master() {
		editingEntry = false;
		editingGuestbook = false;
	}

	@PostConstruct
	public void postConstruct() {
		
		try {

			LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
			long scopeGroupId = liferayFacesContext.getScopeGroupId();

			Guestbook defaultGuestbook = GuestbookLocalServiceUtil.getFirstGuestbookByName(scopeGroupId,
					DEFAULT_GUESTBOOK_NAME);

			// Create the default guestbook if it does not exist in the database
			if (defaultGuestbook == null) {
				logger.info("postConstruct: creating a default guestbook named " + DEFAULT_GUESTBOOK_NAME + " ...");

				Guestbook guestbook = GuestbookUtil.create(0L);
				guestbook.setName(DEFAULT_GUESTBOOK_NAME);
				guestbook.setGroupId(scopeGroupId);
				guestbook.setCompanyId(liferayFacesContext.getCompanyId());
				guestbook.setUserId(liferayFacesContext.getUserId());
				GuestbookLocalServiceUtil.addGuestbook(guestbook);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEditingEntry(boolean editingEntry) {
		this.editingEntry = editingEntry;
	}

	public void setEditingGuestbook(boolean editingGuestbook) {
		this.editingGuestbook = editingGuestbook;
	}

	public boolean isEditingGuestbook() {
		return editingGuestbook;
	}

	public boolean isEditingEntry() {
		return editingEntry;
	}

}
