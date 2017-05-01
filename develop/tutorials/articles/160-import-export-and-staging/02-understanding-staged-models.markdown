# Understanding Staged Models

To track an entity of an application with the Staging framework, you must
implement the
[StagedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface in the app's model classes. It provides the behavior contract for the
entities Staging used during the Staging process. For example, the Bookmarks
application manages
[BookmarksEntry](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntry.html)s
and
[BookmarksFolder](@app-ref/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksFolder.html)s,
and both implement the `StagedModel` interface. Once you've configured your
staged models, you can create staged model data handlers, which are used to
supply information about a staged model (entity) and its referenced content to
the Export/Import and Staging frameworks. See the
[Understanding Data Handlers](/develop/tutorials/-/knowledge_base/7-0/understanding-data-handlers)
tutorial for more information.

There are two ways to create staged models for your application's entities:

- Using Service Builder to generate the required Staging implementations
  ([tutorial]()).
- Implementing the required Staging interfaces manually
  ([tutorial]()).

You can follow step-by-step procedures for creating staged models for your
entities by visiting their respective tutorials.

Using Service Builder to generate your staged models is the easiest way to
create staged models for your app. You define the necessary columns in your
`service.xml` file and set the `uuid` attribute to `true`. Then you run
Service Builder, which generates the required code for your new staged models.

Implementing the necessary staged model logic manually should be done if you
**don't** want to extend your model with special attributes only required to
generate Staging logic (i.e., not needed by your business logic). In this case,
you should adapt your business logic to meet the Staging framework's needs.

To aid in your understanding of staged models, you'll explore the interfaces
involved.
