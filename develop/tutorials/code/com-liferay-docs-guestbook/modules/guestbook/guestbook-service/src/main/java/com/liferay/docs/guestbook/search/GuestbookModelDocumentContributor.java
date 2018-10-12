package com.liferay.docs.guestbook.search;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

@Component(
		immediate = true,
		property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
		service = ModelDocumentContributor.class
)
public class GuestbookModelDocumentContributor
	implements ModelDocumentContributor<Guestbook> {

	@Override
	public void contribute(Document document, Guestbook guestbook) {
		System.out.println("contribute method of GuestbookModelDocumentContributor was called");
		try {
			document.addDate(Field.MODIFIED_DATE, guestbook.getModifiedDate());

			Locale defaultLocale = PortalUtil.getSiteDefaultLocale(
	guestbook.getGroupId());

			String localizedTitle = LocalizationUtil.getLocalizedName(
	Field.TITLE, defaultLocale.toString());

			document.addText(localizedTitle, guestbook.getName());
		} catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
	"Unable to index guestbook " + guestbook.getGuestbookId(), pe);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
	GuestbookModelDocumentContributor.class);

}