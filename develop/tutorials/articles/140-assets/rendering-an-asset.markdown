# Rendering an Asset

There are several options you have for rendering an asset in @product@. Before
setting up the rendering process for your asset, make sure it's properly added
to the asset framework by following the
[Adding, Updating, and Deleting Assets](/develop/tutorials/-/knowledge_base/7-0/adding-updating-and-deleting-assets-for-custom-entities)
tutorial. Once you have your asset properly added to the framework, Liferay can
render the asset by default. The default rendering process @product@ provides,
however, only displays the asset's title and description text, which can be
published by the Asset Publisher application. Any further rendering of your
asset requires additional set up. For instance, consider the list below of
popular cases when you'd want to provide additional rendering capabilities:

- Providing an edit feature to let users modify an asset.
- Allowing users to view an asset in its original context (e.g., viewing a blog
  in the Blogs application rather than restricting its viewing capabilities to
  the Asset Publisher).
- Including interactive files like images, videos, and audio.
- Restricting access to users who do not have permissions to interact with the
  asset.
- Allowing users to comment on the asset by providing a comments section.

Now that we've enticed you with a slew of rendering possibilities, you may be
curious as to how this is all possible.

Liferay lets you dictate your asset's rendering capabilities by providing the
*Asset Renderer* framework. Implementing an asset renderer for an existing asset
is easy because Liferay offers applicable interfaces and factories to help get
your asset renderer created fast.


