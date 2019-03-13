# Understanding Staged Models [](id=understanding-staged-models)

To track an entity of an application with the Staging framework, you must
implement the
[StagedModel](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface in the app's model classes. It provides the behavior contract for 
entities during the Staging process. For example, the Bookmarks application
manages
[BookmarksEntry](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntry.html)s
and
[BookmarksFolder](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksFolder.html)s,
and both implement the `StagedModel` interface. Once you've configured your
staged models, you can create staged model data handlers, which supply
information about a staged model (entity) and its referenced content to the
Export/Import and Staging frameworks. See the
[Understanding Data Handlers](/developer/reference/-/knowledge_base/7-2/understanding-data-handlers)
tutorial for more information.

There are two ways to create staged models for your application's entities:

- Using Service Builder to generate the required Staging implementations
  ([tutorial](/developer/frameworks/-/knowledge_base/7-2/generating-staged-models-using-service-builder)).
- Implementing the required Staging interfaces manually
  ([tutorial](/developer/frameworks/-/knowledge_base/7-2/creating-staged-models-manually)).

You can follow step-by-step procedures for creating staged models for your
entities by visiting their respective articles.

Continue on to learn more about Staged Models!
