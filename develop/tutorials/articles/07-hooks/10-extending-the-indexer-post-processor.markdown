# Extending the Indexer Post Processor [](id=extending-the-indexer-post-processor)

Would you like to modify the search summaries, indexes, and queries available in
your portal instance? Developing an Indexer Post Processor hook lets you do just
that. The indexer hook implements a post processing system on top of the
existing indexer to allow plugin hook developers to modify their search, index,
and query capabilities. Let's run through a simple example to preview what you
can accomplish with an indexer hook. For our example, we're going to add *Job
Title* into the User Indexer so we can search for users by their Job Title.

1. In your existing example-hook project, open the `liferay-hook.xml` file and
insert the following lines before the closing `</hook>` tag:

		<indexer-post-processor>
        	<indexer-class-name>com.liferay.portal.model.User</indexer-class-name>
        	<indexer-post-processor-impl>com.liferay.hook.indexer.SampleIndexerPostProcessor</indexer-post-processor-impl>
        </indexer-post-processor>

    The `<indexer-class-name/>` tag clarifies the model entity for the indexer.
    Furthermore, the `<indexer-post-processor-impl/>` tag clarifies the
    implementation of the interface.

2. Create a new class in the `docroot/WEB-INF/src/com/liferay/hook/indexer`
directory of your example-hook named *SampleIndexerPostProcessor*. Then replace
the Java source file's contents with the following lines:

		package com.liferay.hook.indexer;

		import java.util.Locale;
		import javax.portlet.PortletURL;
		import com.liferay.portal.kernel.log.Log;
		import com.liferay.portal.kernel.log.LogFactoryUtil;
		import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
		import com.liferay.portal.kernel.search.BooleanQuery;
		import com.liferay.portal.kernel.search.Document;
		import com.liferay.portal.kernel.search.Field;
		import com.liferay.portal.kernel.search.SearchContext;
		import com.liferay.portal.kernel.search.Summary;
		import com.liferay.portal.model.User;


			public class SampleIndexerPostProcessor extends BaseIndexerPostProcessor
			{
				public void postProcessContextQuery(BooleanQuery booleanQuery, SearchContext searchcontext)
						throws Exception {
					if(_log.isDebugEnabled())
						_log.debug(" postProcessContextQuery()");
					}

				public void postProcessDocument(Document document, Object object)
						throws Exception {
					User userEntity = (User) object;
					String indexerUserTitle = "";
					try {
						indexerUserTitle = userEntity.getJobTitle();
					} catch (Exception e) {}
					if(indexerUserTitle.length() > 0)
						document.addText(Field.TITLE, indexerUserTitle);
				}

				public void postProcessFullQuery(BooleanQuery fullQuery, SearchContext searchcontext)
						throws Exception {
					if(_log.isDebugEnabled())
						_log.debug(" postProcessFullQuery()");
				}

				public void postProcessSearchQuery(BooleanQuery searchquery, SearchContext searchcontext)
						throws Exception {
					if(_log.isDebugEnabled())
						_log.debug(" postProcessSearchQuery()");
				}

				public void postProcessSummary(Summary summary, Document document, Locale locale,
						String snippet, PortletURL portletURL) {
					if(_log.isDebugEnabled())
						_log.debug("postProcessSummary()");
				}
				private static Log _log = LogFactoryUtil.getLog(SampleIndexerPostProcessor.class);
			}

	Notice the `SampleIndexerPostProcessor` class extends Liferay's
	`BaseIndexerPostProcessor` base implementation. Then we add our own logic to
	enable users to search for *Job Title* amongst all the portal's users. Thus,
	we've added a new feature for the User Indexer. Let's give it a try!

Navigate to the *Control Panel* &rarr; *Users and Organizations* and make sure a
user has a job title, which can be added in any user's *My Account* interface.
Then test out the indexer hook by searching for that job title.

![Figure 10.5: In this example, searching for *Nose Model* returns one user with the matching job title.](../../images/indexer-hook-search.png)

In the next section, we'll explore more hooks that allow for customizing
Liferay's core features.
