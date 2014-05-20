package com.liferay.docs.guestbook.portlet;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.guestbook.model.Entry;

/**
 * Portlet implementation class GuestbookPortlet
 */
public class GuestbookPortlet extends MVCPortlet {
	
	public void addEntry (ActionRequest request, ActionResponse response) {
		
		PortletPreferences prefs = request.getPreferences();

		ArrayList<String> entries = new ArrayList<String>
		   (Arrays.asList(prefs.getValues("guestbook-entries", new String[1])));

		String userName = ParamUtil.getString(request, "name");
		String message = ParamUtil.getString(request, "message");
		String entry = userName + "|" + message;

		entries.add(entry);

		String[] array = entries.toArray(new String[entries.size()]);

		try {
			prefs.setValues("guestbook-entries", array);
		} catch (ReadOnlyException e) {
			System.out.println("Couldn't add entries.");
		}
		
	}
	
	public void render (RenderRequest renderRequest, RenderResponse renderResponse) {
		
		PortletPreferences prefs = renderRequest.getPreferences();
		String[] guestbookEntries = prefs.getValues("guestbook-entries",
				new String[1]);

		if (guestbookEntries != null) {

			List<Entry> entries = parseEntries(guestbookEntries);

			renderRequest.setAttribute("guestbook-entries", entries);
		}
		
		
	}
	
	private List<Entry> parseEntries (String[] guestbookEntries) {
		
		List<Entry> entries = Collections.EMPTY_LIST;

		for (String entry : guestbookEntries) {
			String[] parts = entry.split("|");
			Entry gbEntry = new Entry(parts[0], parts[1]);
			entries.add(gbEntry);
		}

		return entries;
	}
 

}
