# Editing Images [](id=editing-images)

Editing and re-uploading images when you only need to apply simple edits is 
tedious. Docs & Media contains a simple built-in image editor for exactly this 
reason. To access the image editor, locate the image you want to edit. Click
the Actions icon (![Actions](../../../../images/icon-actions.png)) and select
*Edit With Image Editor*. 

You can also access the image editor when selecting an image to insert in 
content (i.e., via an item selector). Anywhere an image is, you can edit it. For 
example, you can access the image editor via item selector preview windows in 
blog entries and web content articles. To do this, click the pencil icon 
(![Pencil](../../../../images/icon-edit-pencil.png)) in the bottom-right corner 
of the preview window. 

![Figure 1: You can access the image editor through the Documents and Media repository.](../../../../images/image-editor-docs-and-media.png)

![Figure 2: You can also access the image editor through the item selector preview window.](../../../../images/image-editor-preview-window.png)

If you edit and save the image via the Documents and Media repository, the file 
version is incremented a minor version (e.g., from version 1.0 to version 1.1). 
You can view the image's version history (and previous versions) by clicking the 
image, clicking its *Info* button 
(![**i**](../../../../images/icon-information.png)), and then selecting 
*Versions* from the menu currently set to *Details*. In contrast, if you edit 
and save an image via an item selector, a copy of the image is created and saved 
to the document library. 

Liferay designed the image editor with quick editing in mind. It offers a 
minimal, user-friendly UI. The main toolbar consists of three buttons, each of 
which contains a subset of options: 

![Figure 3: The image editor's UI is clear and to the point, offering only what you need.](../../../../images/image-editor-tools.png)

**Transform** (![Transform](../../../../images/icon-transform.png))

-   **Rotate**: Rotate the image to the left or right, in 90 degree increments.
-   **Resize**: Resize the image. If the lock is closed, the aspect ratio is 
    locked and changing width or height automatically adjusts the other 
    dimension to maintain the aspect ratio. When the lock is opened, the width 
    and height can be changed individually, letting the aspect ratio change 
    (this isn't recommended because the image can become distorted). 
-   **Crop**: Crop the image. 

**Adjustment** (![Adjustment](../../../../images/icon-adjustment.png))

-   **Saturation**: Adjust the color saturation. The default value is 50. Values 
    range from 0 (completely desaturated) to 100 (completely saturated). 
-   **Contrast**: Adjust the contrast. The default value is 50. Values range 
    from 0 (no contrast) to 100 (full contrast). 
-   **Brightness**: Adjust the brightness. The default value is 50. Values range 
    from 0 (completely black) to 100 (completely white). 

**Filter** (![Wand](../../../../images/icon-wand.png)): Apply a filter to the 
image.

![Figure 4: Select from a set of preset image filters.](../../../../images/image-editor-filters.png)

Upon editing the image in the editor, you can click the *Cancel* button to 
cancel the changes, or the *Apply* button to apply them. Upon applying the 
changes, the history bar appears. It lets you undo, redo, or reset the changes. 
Use the Reset button with caution; it resets the image to its original state, 
reverting all changes made in the editor. 

![Figure 5: The history bar lets you undo, redo, and reset changes.](../../../../images/image-editor-history-bar.png)
