---
header-id: returning-results
---

# Returning Results

[TOC levels=1-4]

When a model entity's indexed search document is obtained during a search
request, it's converted into a summary of the model entity. You can exert
control over your model entity's summary.

## Creating a Results Summary

`ModelSummaryContributor` classes get the `Summary` object created for each
search document, so you can manipulate it by adding specific fields or setting
the length of the displayed content.

**Extension Pointi (SPI):** [`com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/result/contributor/ModelSummaryContributor.java)

```java
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.foo.model.FooEntry",
	service = ModelSummaryContributor.class
)
public class FooEntryModelSummaryContributor
	implements ModelSummaryContributor {

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		String languageId = LocaleUtil.toLanguageId(locale);

		return _createSummary(
			document,
			LocalizationUtil.getLocalizedName(Field.CONTENT, languageId),
			LocalizationUtil.getLocalizedName(Field.TITLE, languageId));
	}

	private Summary _createSummary(
		Document document, String contentField, String titleField) {

		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		Summary summary = new Summary(
			document.get(prefix + titleField, titleField),
			document.get(prefix + contentField, contentField));

		summary.setMaxContentLength(200);

		return summary;
	}

}
```

## Controlling the Visibility of Model Entities

[`ModelVisibilityContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/result/contributor/ModelVisibilityContributor.java) 
classes control the visibility of model entities that can be attached to other
asset types (for example, File Entries can be attached to Wiki Pages), in the
search context. 

**Extension Point (SPI):** [`com.liferay.portal.search.spi.model.result.contributor.ModelVisibilityContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/result/contributor/ModelVisibilityContributor.java)

```java
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.foo.model.FooEntry",
	service = ModelVisibilityContributor.class
)
public class FooEntryModelVisibilityContributor
	implements ModelVisibilityContributor {

	@Override
	public boolean isVisible(long classPK, int status) {
		try {
			FooEntry entry = fooEntryLocalService.getEntry(classPK);

			return isVisible(entry.getStatus(), status);
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to check visibility for foo entry ", pe);
			}
		}

		return false;
	}

	protected boolean isVisible(int entryStatus, int queryStatus) {
		if (((queryStatus != WorkflowConstants.STATUS_ANY) &&
			 (entryStatus == queryStatus)) ||
			(entryStatus != WorkflowConstants.STATUS_IN_TRASH)) {

			return true;
		}

		return false;
	}

	@Reference
	protected FooEntryLocalService fooEntryLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		FooEntryModelVisibilityContributor.class);

}
```

Once you index model entities, add their terms to the @product@ search query,
and get the summary just right, your model entity is ready to be searched.
