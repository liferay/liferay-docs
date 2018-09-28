# Embedding Content in the AlloyEditor [](id=embedding-content-in-the-alloy-editor)

Whether it's a video from a popular streaming service, or an entertaining 
podcast, embedded content is commonplace on the web. Sharing content from a 
third party is sometimes required to properly cover a topic. The `EmbedProvider` 
mechanism lets you embed third party content in the AlloyEditor, while writing 
blog posts, web content articles, etc. By default, the `EmbedProvider` mechanism 
is only configured for embedding video content 
(Facebook, Twitch, Vimeo, and YouTube) into the AlloyEditor. This tutorial shows 
how to include additional video providers, and even add support for additional 
content types. 

An `EmbedProvider` requires four pieces of information:

- An ID: The content's ID
- A Template: The required embed code for the provider
- A URL Schemes: URL patterns that are supported for the provider template
- A Type (optional): The provider category

When you add a supported URL to the editor, the `EmbedProvider` transforms the 
URL into the embed code. 

Follow these steps to create an `*EmbedProvider`:

1.  [Create a module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module) 
    for the Embed Provider.

2.  Add the following dependencies to the `build.gradle` file:

        compileOnly group: "com.liferay", name:
        "com.liferay.frontend.editor.api", version: "1.0.1"

        compileOnly group: "com.liferay", name: "com.liferay.petra.string",
        version: "2.0.0"

3.  Create a component class that implements the `EditorEmbedProvider` service:

        @Component(
          immediate = true,
          service = EditorEmbedProvider.class
        )

4.  Optionally set the `type` property to the content's type. If creating a 
    provider for a content type other than video, you can create a new type 
    constant and 
    [add a new button](/develop/tutorials/-/knowledge_base/7-1/creating-new-buttons-for-alloyeditor) 
    for the content type. If you do create your own button, we recommend that 
    you use the existing 
    [embed video button's JSX files](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-editor/frontend-editor-alloyeditor-web/src/main/resources/META-INF/resources/js/extras/buttons/embed) 
    as an example to write your own files. By default, the provider is 
    categorized as `UNKNOWN`. The example configuration below specifies the 
    `VIDEO` type, using a constant provided by the 
    [`EditorEmbedProviderTypeConstants` class](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-editor/frontend-editor-api/src/main/java/com/liferay/frontend/editor/api/embed/EditorEmbedProviderTypeConstants.java):

        @Component(
          immediate = true,
          property = "type=" + EditorEmbedProviderTypeConstants.VIDEO,
          service = EditorEmbedProvider.class
        )

5.  Implement the 
    [`EditorEmbedProvider` interface](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-editor/frontend-editor-api/src/main/java/com/liferay/frontend/editor/api/embed/EditorEmbedProvider.java). 
    An example configuration is shown below:

        public class MyEditorEmbedProvider implements EditorEmbedProvider {

        }

6.  Add the required imports:

        import com.liferay.frontend.editor.api.embed.EditorEmbedProvider;
        import com.liferay.frontend.editor.api.embed.EditorEmbedProviderTypeConstants;
        import com.liferay.petra.string.StringBundler;

    Note the `*TypeConstants` import is only needed if you're adding a Video 
    type provider. 

7.  Override the `*EmbedProvider`'s `getId()` method to return the ID for the 
    provider. An example configuration is shown below:

        @Override
        public String getId() {
        	return "providerName";
        }

8.  Override the `*EmbedProvider`'s `getTpl()` method to provide the embed 
    template code (usually an iframe for the provider). The example below 
    defines the template for a streaming video service. Note that `{embedId}` is 
    a placeholder for the unique identifier for the embedded content:

        @Override
        public String getTpl() {
        	return StringBundler.concat(
        		"<iframe allow=\"autoplay; encrypted-media\" allowfullscreen ",
        		"height=\"315\" frameborder=\"0\" ",
        		"src=\"https://www.liferaylunarresortstreaming.com/embed/{embedId}?rel=0\" ",
        		"width=\"560\"></iframe>");
        }

9.  Override the `*EmbedProvider`'s `getURLSchemes()` method to return an array 
    of supported URL schemes that have an embedded representation for the 
    provider. URL schemes are defined using a JavaScript regular expression that 
    indicates whether a URL matches the provider. Every URL scheme should 
    contain a single matching group. Matches replace the `{embedId}` placeholder 
    defined in the previous step:

        @Override
        public String[] getURLSchemes() {
        	return new String[] {
        		"https?:\\/\\/(?:www\\.)?liferaylunarresortstreaming.com\\/watch\\?v=(\\S*)$"
        	};
        }

10.  Deploy your module and open an app that uses the AlloyEditor, such as 
     Blogs, and create a new entry. Click the *add button* and select the video 
     button---or your new content type button---and paste the content's URL. 
     Click the *checkmark* to confirm that the URL scheme is supported. The 
     content is embedded into the editor. 

Now you know how to embed content in the AlloyEditor. Create a new content 
entry, such as a blog post, and click the embed video button---or the one you 
created---and paste the content's URL. 

## Related Topics [](id=related-topics)

[Adding Buttons to AlloyEditor's Toolbars](/develop/tutorials/-/knowledge_base/7-1/adding-buttons-to-alloyeditor-toolbars)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)
