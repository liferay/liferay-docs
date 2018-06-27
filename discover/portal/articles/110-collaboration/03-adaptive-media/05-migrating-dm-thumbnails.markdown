# Migrating Documents and Media Thumbnails to Adaptive Media [](id=migrating-documents-and-media-thumbnails-to-adaptive-media)

@product@ automatically generates thumbnails for images in Documents and Media.
Once you deploy the Adaptive Media app, however, @product@ doesn't display 
thumbnails until you migrate them to Adaptive Media. This article walks you
through this migration process. 

+$$$

**Note:** You must be a Portal Administrator to perform the actions described
here. 

$$$

You'll get started by creating image resolutions for the thumbnails in Adaptive 
Media. 

## Adding the Replacement Image Resolutions [](id=adding-the-replacement-image-resolutions)

To migrate the existing Documents and Media thumbnails, you must add new image 
resolutions in Adaptive Media that have maximum height and maximum width values 
that match the values specified in the following portal properties: 

    dl.file.entry.thumbnail.max.height

    dl.file.entry.thumbnail.max.width

    dl.file.entry.thumbnail.custom1.max.height

    dl.file.entry.thumbnail.custom1.max.width

    dl.file.entry.thumbnail.custom2.max.height

    dl.file.entry.thumbnail.custom2.max.width

+$$$

**Note:** Some of these properties may not be enabled. You need only create
image resolutions in Adaptive Media for the enabled properties. 

$$$

To create the new Image Resolutions, follow the instructions found in the 
[Adding Image Resolutions](/discover/portal/-/knowledge_base/7-1/adding-image-resolutions) 
section of the Adaptive Media user guide. 

Now you're ready to to create the Adaptive Media images. 

## Creating the Adaptive Media Images [](id=creating-the-adaptive-media-images)

Once the required image resolutions exist, you can convert the Documents and 
Media thumbnails to Adaptive Media images. As mentioned in 
[the Adaptive Media installation guide](/discover/portal/-/knowledge_base/7-1/installing-adaptive-media), 
the module *Liferay Adaptive Media Document Library Thumbnails* (which is 
included in the Adaptive Media app) enables this functionality. 

There are two different ways to migrate the Documents and Media thumbnails to 
Adaptive Media: 

-   **Adapt the images for the thumbnail image resolution:** This scales the 
    existing thumbnails to the values in the Adaptive Media image resolutions, 
    which can take time depending on the number of images. We only recommend 
    this approach when there isn't a large number of thumbnails to process, or 
    if you prefer to generate your images from scratch. This approach is covered 
    in more detail in the 
    [Generating Missing Adapted Images](/discover/portal/-/knowledge_base/7-1/managing-image-resolutions#generating-missing-image-resolutions) 
    section of the Adaptive Media user guide. 

-   **Execute a migrate process that reuses the existing thumbnails:** This 
    copies the existing thumbnails to Adaptive Media, which performs better
    because it avoids the computationally expensive scaling operation.
    The next section describes the steps to run this process. 

### Running the Migration Process [](id=running-the-migration-process)

The migration process is a set of Gogo console commands. You can learn more
about using the Gogo console in 
[the Felix Gogo Shell tutorial](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell). 

Follow these steps to migrate your thumbnails from the Gogo console:

1.  Run the `thumbnails:check` command. For each instance, this lists how many 
    thumbnails are pending migration. 

2.  Run the `thumbnails:migrate` command. This executes the migration process, 
    which may take a while to finish depending on the number of images. 

3.  Run the `thumbnails:cleanUp` command. This deletes all the original 
    Documents and Media thumbnails and updates the count returned by 
    `thumbnails:check`. Therefore, you should **only** run `thumbnails:cleanUp`
    after running the migrate command and ensuring that the migration ran 
    successfully and no images are pending migration. 

+$$$

**Note:** If you undeploy Adaptive Media at some point after running the 
migration process, you must regenerate the Documents and Media thumbnails. 
To do this, navigate to *Control Panel* &rarr; *Configuration* &rarr; *Server 
Administration* and click *Execute* next to *Reset preview and thumbnail files 
for Documents and Media*. 

$$$

Great! Now you know how to migrate your Documents and Media thumbnails to 
adapted images. 
