package com.liferay.docs.guestbook.search;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

@Component(
		immediate = true,
		property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
		service = ModelSummaryContributor.class
)
public class GuestbookModelSummaryContributor
	implements ModelSummaryContributor {

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		System.out.println("GuestbookModelSummaryContributor.getSummarySummary()");

		Summary summary = createSummary(document);

		summary.setMaxContentLength(128);

		return summary;
	}

	private Summary createSummary(Document document) {
		
		System.out.println("GuestbookModelSummaryContributor.createSummary()");

		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String title = document.get(prefix + Field.TITLE, Field.TITLE);

		return new Summary(title, StringPool.BLANK);
	}

}