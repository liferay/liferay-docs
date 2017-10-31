# Adaptive Image Versioning [](id=adaptive-image-versioning)

Users can modify images in Documents and Media by editing them with the Image 
Editor, or uploading edited versions of those images. In either case, Documents 
and Media preserves a version history with all the changes. As with all files in 
Documents and Media, each adapted image has a URL for direct access. The exact 
URL pattern depends on whether it points to an earlier version of an image, or 
its latest version. 

Here's the URL pattern for an adapted image's latest version:

    /o/adaptive-media/image/[file-entry-id]/[image-resolution-id]/[image title]
    
For example, the following relative URL points to the latest version of the 
adapted image generated with the file entry ID `34798` and image resolution ID 
`thumbnail`: 

    /o/adaptive-media/image/34798/thumbnail/beach.jpeg

Here's the URL pattern for a specific, earlier version of an adapted image: 

    /o/adaptive-media/image/[file-entry-id]/[file-version-id]/[image-resolution-id]/[image title]

For example, the following URL points to the adapted image generated with the 
file entry ID `34798`, file version ID `34803`, and image resolution ID 
`thumbnail`: 

    /o/adaptive-media/image/34798/34803/thumbnail/beach.jpeg

Great! Now you know how Adaptive Media constructs URLs for adapted images. Next, 
you'll learn how to manage image resolutions. 
