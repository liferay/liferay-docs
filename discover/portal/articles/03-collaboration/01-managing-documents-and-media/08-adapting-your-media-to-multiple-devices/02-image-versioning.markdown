# Adaptive Image Versioning [](id=adaptive-image-versioning)

Users can modify existing images in Documents and Media by uploading a new 
image. They can also edit existing images using the Image Editor. In both cases, 
a version history with all the changes is preserved in Documents and Media.

There are two different kinds of URLs that are supported for adapted images, 
depending on whether the URL points to a specific version of the image or 
whether it points to the latest version.

An example pattern of each type of URL is shown below:

Latest version:

    /o/adaptive-media/image/[file-entry-id]/[image-resolution-id]/[image title]
    
This relative URL points to the latest version of the adapted image generated 
with the file entry ID `34798` and image resolution ID `thumbnail`:

    /o/adaptive-media/image/34798/thumbnail/beach.jpeg


Specific version:

    /o/adaptive-media/image/[file-entry-id]/[file-version-id]/[image-resolution-id]/[image title]

This URL points to the adapted image generated with the file entry ID `34798`, 
file version ID `34803`, and image resolution ID `thumbnail`:

    /o/adaptive-media/image/34798/34803/thumbnail/beach.jpeg
    
<!-- Note Nick that this is probably more relevant for Adaptive Media APIs so 
you can retrieve the latest version of an image or a specific version of the 
image. May want to leave this section out until the APIs tutorial is published. -->
