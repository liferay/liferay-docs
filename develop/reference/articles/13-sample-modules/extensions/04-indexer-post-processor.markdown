# Indexer Post Processor [](id=indexer-post-processor)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Indexer Post Processor sample demonstrates using the `IndexerPostProcessor`
interface, which is provided to customize search queries and documents before
they're sent to the search engine, and/or customize result summaries when
they're returned to end users. This basic demonstration prints a message in the
log when one of the `*IndexerPostProcessor` methods is called. 

To see this sample's messages in @product@'s log, you must add a logging
category to the portal. Navigate to *Control Panel* &rarr; *Configuration*
&rarr; *Server Administration* and click on *Log Levels* &rarr; *Add Category*.
Then fill out the form:

- *Logger Name*: `com.liferay.blade.samples.indexerpostprocessor`
- *Log Level*: `INFO`

Once you save the new logging category, you can witness the sample indexer post
processor in action. For example, you can test the sample's
`BlogsIndexerPostProcessor` implementation by creating a blog entry. When you
publish the blog, the following message is logged in the console:

    18:27:30,737 INFO  [http-nio-8080-exec-8][BlogsIndexerPostProcessor:76] postProcessDocument

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[IndexerPostProcessor](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/search/IndexerPostProcessor.html)
API.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample contains four implementations of the `IndexerPostProcessor`
interface:

- `BlogsIndexerPostProcessor`
- `MultipleEntityIndexerPostProcessor`
- `MultipleIndexerPostProcessor`
- `UserEntityIndexerPostProcessor`

All these classes leverage the interface as an OSGi service via the `@Component`
annotation. For example, the `@Component` annotation of the
`UserEntityIndexerPostProcessor` looks like this:

    @Component(
        immediate = true,
        property = {
            "indexer.class.name=com.liferay.portal.kernel.model.User",
            "indexer.class.name=com.liferay.portal.kernel.model.UserGroup"
        },
        service = IndexerPostProcessor.class
    )

There's one property type provided via the `@Component` annotation:

- `indexer.class.name`: the fully qualified class name of the indexed entity or
an `Indexer` class itself.

This sample's implementations of the `IndexerPostProcessor` interface override
the following methods:

- `postProcessContextBooleanFilter`
- `postProcessContextQuery`
- `postProcessDocument`
- `postProcessFullQuery`
- `postProcessSearchQuery(BooleanQuery, BooleanFilter)`
- `postProcessSearchQuery(BooleanQuery, SearchContext)`
- `postProcessSummary`

For more information on Liferay's Search API, refer to the
[Introduction to Liferay Search](/developer/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search)
tutorial.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/indexer-post-processor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/extensions/indexer-post-processor)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/indexer-post-processor)
