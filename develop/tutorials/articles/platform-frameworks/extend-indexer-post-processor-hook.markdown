# Extending the Indexer Post Processor Using a Hook

Would you like to modify the search summaries, indexes, and queries available in
your portal instance? Developing an Indexer Post Processor hook lets you do just
that. The indexer hook implements a post processing system on top of the
existing indexer that lets you modify your portal's search, index, and query 
capabilities.

In this tutorial you'll run through a simple example to show what you can 
accomplish with an indexer hook. For this example, you'll add *Job Title* into 
the User Indexer so you can search for users by their Job Title.

## Implementing the Indexer Post Processor Hook

1. If you don't yet have a hook project, create one following the steps in the 
   [Creating a Hook](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/creating-a-hook-lp-6-2-develop-tutorial) tutorial.

2. In your hook project, open `liferay-hook.xml` and insert the following lines 
   before the closing `</hook>` tag:

		<indexer-post-processor>
        	<indexer-class-name>com.liferay.portal.model.User</indexer-class-name>
        	<indexer-post-processor-impl>com.liferay.hook.indexer.SampleIndexerPostProcessor</indexer-post-processor-impl>
        </indexer-post-processor>

    The `<indexer-class-name/>` tag clarifies the model entity for the indexer.
    Furthermore, the `<indexer-post-processor-impl/>` tag clarifies the
    implementation of the interface.

3. In your hook project, create the directory 
   `docroot/WEB-INF/src/com/liferay/hook/indexer`. Inside that directory, create 
   a file called `SampleIndexerPostProcessor.java` with the following content:

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
	`BaseIndexerPostProcessor` base implementation. Then logic is added to
	enable users to search for *Job Title* amongst all the portal's users. Thus,
	you just added a new feature for the User Indexer!
	
4. You now need to reindex the portal. Go to the *Control Panel* and click 
   *Server Administration*. Now click the *Execute* button next to 
   *Reindex all search indexes*.
   
   ![Click *Execute* to reindex the search indexes.](../../images/reindex-search-indexes.png)
   
5. Navigate to *Control Panel* &rarr; *Users and Organizations* and make sure a
   user has a job title, which can be added in any user's *My Account* 
   interface. Then test out the indexer hook by searching for that job title.

![In this example, searching for *Blogger* returns two users with that job title.](../../images/indexer-hook-search.png)

Great! Now you know the basics of extending the Indexer Post Proccessor using a 
hook.
