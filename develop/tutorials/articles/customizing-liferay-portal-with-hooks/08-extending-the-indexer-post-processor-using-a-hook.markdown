# Extending the Indexer Post Processor Using a Hook [](id=extending-the-indexer-post-processor-using-a-hook)

Would you like to modify the search summaries, indexes, and queries available in
your portal instance? Developing an Indexer Post Processor hook lets you do just
that. The indexer hook implements a post processing system on top of the
existing indexer, letting you modify your portal's search, index, and query 
capabilities.

In this tutorial, you'll run through a simple example to learn what you can 
accomplish with an indexer hook. For this example, you'll add *Job Title* into 
the User Indexer, so you can search for users by job title.

## Implementing the Indexer Post Processor Hook [](id=implementing-the-indexer-post-processor-hook)

1. If you don't yet have a hook project, create one following the steps in the 
   [Creating a Hook Project in the Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/creating-a-hook-project-in-the-plugins-sdk)
   tutorial.

2. In your hook project, open `docroot/WEB-INF/liferay-hook.xml` and insert the
   following lines before the closing `</hook>` tag:

        <indexer-post-processor>
            <indexer-class-name>com.liferay.portal.model.User</indexer-class-name>
            <indexer-post-processor-impl>com.liferay.hook.indexer.SampleIndexerPostProcessor</indexer-post-processor-impl>
        </indexer-post-processor>

    You specify the indexer's model entity in the `indexer-class-name` element
    and specify your custom indexer post processor class in the
    `indexer-post-processor-impl` element. The DTD for `liferay-hook.xml` is
    [here](https://docs.liferay.com/portal/6.2/definitions/liferay-hook_6_2_0.dtd.html). 

3. In your hook project's `docroot/WEB-INF/src` folder, create your custom
   indexer post processor class and its package. Here's code for the
   `SampleIndexerPostProcessor` custom indexer post processor class:

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

        public class SampleIndexerPostProcessor extends BaseIndexerPostProcessor {

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
	[`BaseIndexerPostProcessor`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/search/BaseIndexerPostProcessor.html)
	base implementation. In your custom class' `postProcessDocument` method, you
	can add fields to index. The `SampleIndexerPostProcessor` class'
	`postProcessDocument` method adds the *Job Title* field, enabling users to
	search by *Job Title*. You've just extended an indexer!
	
4. [Deploy](/develop/tutorials/-/knowledge_base/6-2/deploying-plugins-to-a-local-portal-instance)
   the hook plugin. 

5. You now need to reindex the portal. Go to the *Control Panel* and click 
   *Server Administration*. Click on the *Execute* button for the Action
   *Reindex all search indexes*.
   
    ![Figure 1: Click *Execute* to reindex the search indexes.](../../images/reindex-search-indexes.png)
   
6. Navigate to *Control Panel* &rarr; *Users and Organizations* &rarr; *All
   Users* and select a user. Assign the user a job title (e.g., *Blogger*) and
   click *Save*.

7. Test the indexer hook by searching for the user by the job title you
   assigned. 

![Figure 2: By extending the indexer post processor for a model entity, you can search for instances of that model based on the fields you add to the indexer post processor. In this example, you extended the User entity's search index to include *Job Title* so you could search for users by job title (e.g., *Blogger*).](../../images/indexer-hook-search.png)

Great! Now you know the basics of extending the Indexer Post Processor using a 
hook.

## Related Topics [](id=related-topics)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

[Customizing Liferay Portal with Hooks](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Developging Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with Maven](/develop/tutorials/-/knowledge_base/6-2/maven)
