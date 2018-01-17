# Editing Images [](id=editing-images)

You've uploaded an image to the document library. It's almost perfect, but it's 
missing a little something. It could use a little more focus to really highlight 
the message you're trying to communicate. Just a little editing and the image 
would draw your readers in. You could edit the image in your favorite image 
editing program and re-upload it, but that's a bit tedious for the minor changes 
this image requires.

What else can you do? 

Lucky for you, @product@ now comes with its own built-in image editor. 

A crop here and there, a little bit more contrast, and some adjustments in 
saturation, and boom! The image is ready to go.   

Since Liferay Portal CE 7.0 GA2 and Liferay DXP 7.0, users can edit images 
within portal instances. To access the image editor, go to the Documents and 
Media repository and locate the image you want to edit. Click the Actions icon 
(![Actions](../../../../images/icon-actions.png)) and select *Edit With Image 
Editor*.

![Figure 1: You can access the image editor through the Documents and Media repository.](../../../../images/image-editor-docs-and-media.png)

The image editor can also be accessed through the item selector. Essentially, 
anywhere you have an image, you can edit images. For example, in blog entries, 
web content articles, and wikis, you can view a preview of the image.

Within the item selector preview window, you can access the image editor by 
clicking the pencil icon (![Pencil](../../../../images/icon-edit-pencil.png)) in 
the bottom right corner of the preview window.

![Figure 2: You can access the image editor through the item selector preview window.](../../../../images/image-editor-preview-window.png)

The image editor was designed with quick editing in mind, and offers a minimal, 
user-friendly UI:

![Figure 3: The image editor's UI is clear and to the point, offering only what you need.](../../../../images/image-editor-tools.png)

The main toolbar consists of three buttons, each of which contain a subset of
options. The menu options are described below:

**Transform Tools** (![Transform](../../../../images/icon-transform.png))

**Crop**: Lets you reframe the image, by removing the outer portions.

**Resize**: Lets you resize the image in pixels. If the lock is closed, the
aspect ratio remains locked, and changing either width or height will
automatically adjust the other dimension to maintain the aspect ratio. When the
lock is opened, the width and height can be changed individually, allowing the
aspect ratio to be altered. This is not recommended though, as the image can
become distorted.

**Rotate**: Lets you rotate the image to the left or right, in 90 degree
increments.

**Adjustment Tools** (![Adjustment](../../../../images/icon-adjustment.png))

**Saturation**: Lets you adjust the amount of color saturation in the image. The
default value of 50 is the current saturation. The slider values range
from 0(completely desaturated) to 100(completely saturated).

**Contrast**: Lets you adjust the difference between highlights and shadows. A
value of 50 is the current level of contrast. The slider values range from
0(no contrast) to 100(full contrast).

**Brightness**: Lets you adjust the amount of light in the image. A value of
50 is the current brightness. The slider ranges from 0(completely black) to
100(completely white).

The third and final toolset is a preset of filters, which you can access by 
clicking the wand icon (![Wand](../../../../images/icon-wand.png)):

![Figure 4: The image editor offers several filter presets for you to choose from.](../../../../images/image-editor-filters.png)

Once you have made changes to the image in the editor, you can click the 
*Cancel* button to cancel the changes, or the *Apply* button to apply the 
changes to the image. Once you have applied changes to your image, the history 
bar appears:

![Figure 5: The history bar allows you to undo, redo, and reset changes.](../../../../images/image-editor-history-bar.png)

The history bar allows you to undo, redo, and reset changes that have been made
to the image in the editor. Note that the reset button should be used with 
caution; once it has been pressed the image is reset to its original state, 
causing all changes made within the editor to be lost.

As mentioned earlier, you can access the image editor in the Documents and Media
repository, or through the item selector in blogs, web content articles, and
wikis. When the image is edited within the Documents and Media repository, and
saved, the version is incremented a minor version i.e. version 1.0 to version 
1.1. You can view the previous versions of the image through the 
*version history* table, under the information section for the image. When the 
image is edited within the item selector, and saved, a copy is created of the 
image and saved to the document library.

<!-- Add back in after developer tutorial has been written 

Note that these tools are modularized, and are each their own OSGi module. This 
modular design allows for developers to create their own custom tools and 
filters.

You can learn more about that in the [Expanding the image editor tutorial](URL)

-->

Next, you'll learn how to access Google Docs&trade; through the Document 
Library. 
