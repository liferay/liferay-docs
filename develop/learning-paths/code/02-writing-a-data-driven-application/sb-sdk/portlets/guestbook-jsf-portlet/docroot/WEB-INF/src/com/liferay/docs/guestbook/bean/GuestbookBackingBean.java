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

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.persistence.GuestbookUtil;

import com.liferay.faces.portal.context.LiferayFacesContext;


/**
 * @author  Vernon Singleton
 */
@ManagedBean
@RequestScoped
public class GuestbookBackingBean extends AbstractBackingBean {

	public void add() {
		Guestbook guestbook = GuestbookUtil.create(0L);
		LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
		guestbook.setGroupId(liferayFacesContext.getScopeGroupId());
		guestbookModelBean.setSelectedGuestbook(guestbook);
		viewBean.guestbook();
	}

	@Override
	public void cancel() {

		// We do not want an un-validated guestbook to be the selectedGuestbook on the master view.
		guestbookModelBean.setSelectedGuestbook(null);
		super.cancel();
	}

	public void delete(Guestbook guestbook) {

		try {
			GuestbookLocalServiceUtil.deleteGuestbook(guestbook.getGuestbookId());
			addGlobalSuccessInfoMessage();
		}
		catch (Exception e) {
			addGlobalUnexpectedErrorMessage();
			logger.error(e);
		}

		guestbookModelBean.forceGuestbooksReload();
		guestbookModelBean.forceEntriesReload();
		viewBean.master();
	}

	public void edit(Guestbook guestbook) {
		guestbookModelBean.setSelectedGuestbook(guestbook);
		viewBean.guestbook();
	}

	public void save() {
		Guestbook guestbook = guestbookModelBean.getSelectedGuestbook();

		try {

			if (guestbook.getGuestbookId() == 0) {
				guestbook = GuestbookLocalServiceUtil.addGuestbook(guestbook);
			}
			else {
				guestbook = GuestbookLocalServiceUtil.updateGuestbook(guestbook);
			}

			addGlobalSuccessInfoMessage();
		}
		catch (Exception e) {
			addGlobalUnexpectedErrorMessage();
			logger.error(e);
		}

		guestbookModelBean.forceGuestbooksReload();
		guestbookModelBean.forceEntriesReload();
		viewBean.master();
	}

	public void select(Guestbook guestbook) {
		guestbookModelBean.setSelectedGuestbook(guestbook);
		guestbookModelBean.forceEntriesReload();
		viewBean.master();
	}
}
