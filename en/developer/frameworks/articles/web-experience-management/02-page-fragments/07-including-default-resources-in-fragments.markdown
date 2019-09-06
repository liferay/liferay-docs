---
header-id: including-default-resources-in-fragments
---

# Including Default Resources in Fragments

[TOC levels=1-4]

When creating Page Fragments, you can upload resources (e.g., images, documents,
etc.) to your Fragment Collection to make them always available from the
Collection, rather than relying on resources uploaded in other areas of your
Site (e.g., Documents and Media). For more information on how to include
resources in your Fragment Collection from the Page Fragments interface, see 
[Creating Page Fragments](/docs/7-2/user/-/knowledge_base/u/creating-page-fragments).

Once you've uploaded your resource to a Fragment Collection, you can specify it
in your Fragment:

1.  Navigate to the Fragment editing page by clicking your Fragment's *Actions*
    ![Actions](../../../images/icon-actions.png) &rarr; *Edit* button.

2.  Specify the image by using this syntax: `[resources:IMAGE_NAME]`. For
    example, you could include an image `building.png` within an HTML image tag
    like this:

    ```html
    <img src="[resources:building.png]">
    ```

    You can view a full example snippet below:

    ```html
    <div class="fragment_38314">
        <lfr-editable id="img" type="image">
            <img src="[resources:building.png]">
        </lfr-editable>
    </div>
    ```

3.  Add any additional HTML, CSS, or JavaScript to your Fragment and then click
    *Publish*.

    | **Note:** You can also reference your Fragment Collection's resources
    | in your CSS code too. It follows the same syntax as its HTML.

![Figure 1: Any Fragment from the Fragment Collection has access to the uploaded resources.](../../../images/fragment-resources.png)

Great! You've successfully referenced a default resource from your Fragment
Collection!
