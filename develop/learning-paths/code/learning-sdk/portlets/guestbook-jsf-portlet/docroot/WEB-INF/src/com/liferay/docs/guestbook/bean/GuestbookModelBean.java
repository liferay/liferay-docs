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

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 * @author  Vernon Singleton
 */
@ManagedBean
@ViewScoped
public class GuestbookModelBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(GuestbookModelBean.class);

	// Private Data Members
	private Guestbook selectedGuestbook;
	private List<Guestbook> guestbooks;

	private Entry selectedEntry;
	private List<Entry> entries;

	public void forceEntriesReload() {
		setEntries(null);
	}

	public void forceGuestbooksReload() {
		setGuestbooks(null);
	}

	public List<Entry> getEntries() {

		if (entries == null) {
			long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();
			Guestbook selectedGuestbook = getSelectedGuestbook();

			try {
				entries = EntryLocalServiceUtil.getEntries(scopeGroupId, selectedGuestbook.getGuestbookId());
			}
			catch (SystemException e) {
				logger.error(e);
			}
		}

		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public List<Guestbook> getGuestbooks() {

		if (guestbooks == null) {
			long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();

			try {
				guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);
			}
			catch (SystemException e) {
				logger.error(e);
			}
		}

		return guestbooks;
	}

	public void setGuestbooks(List<Guestbook> guestbooks) {
		this.guestbooks = guestbooks;
	}

	public Entry getSelectedEntry() {
		return selectedEntry;
	}

	public void setSelectedEntry(Entry selectedEntry) {
		this.selectedEntry = selectedEntry;
	}

	public Guestbook getSelectedGuestbook() {

		if (selectedGuestbook == null) {
			long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();

			try {
				selectedGuestbook = GuestbookLocalServiceUtil.getFirstGuestbookByName(scopeGroupId,
						ViewBean.DEFAULT_GUESTBOOK_NAME);
			}
			catch (SystemException e) {
				logger.error(e);
			}
		}

		return selectedGuestbook;
	}

	public void setSelectedGuestbook(Guestbook selectedGuestbook) {
		this.selectedGuestbook = selectedGuestbook;
	}

}
