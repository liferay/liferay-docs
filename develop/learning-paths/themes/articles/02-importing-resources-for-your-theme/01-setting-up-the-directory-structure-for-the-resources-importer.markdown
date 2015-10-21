# Setting up the Directory Structure for the Resources Importer [](id=setting-up-the-directory-structure-for-the-resources-importer)

A blank `resources-importer` directory was created with your theme. Rather than
add each file individually and create all the remaining folders, go ahead and
delete the existing `resources-importer` directory.

1. Delete the existing `resources-importer` folder in the `docroot/WEB-INF/src` 
   directory of the Lunar Resort theme and replace it with the 
   `resources-importer` folder in the `themes/code/` directory of the Learning 
   Path.
 
The updated Resources Importer directory uses the following structure to 
organize the files for the Lunar Resort:

- `lunar-resort-theme/`
    - `docroot/WEB-INF/src/`
        - `resources-importer/`
            - `document_library/`
                - `documents/`
                    - (documents and media files)
            - `journal/`
                - `articles/`
                    - (web content articles)
                - `structures/`
                - `templates/`
 
The theme's resources are organized into documents and media files and web
content articles. Structures and templates are given their own folders inside 
the `journal` directory.
 
Web content articles can be written in either HTML or XML and placed in the 
`journal/articles/` folder. Images included in the web content are referenced 
using the name of the file in the `document_library/documents` folder. The web 
content references the image using the following code:
   
    src= "[$FILE=filename.filetype$]"
 
That's all the prep work that needs to be completed for most web content. For
more advanced web content that requires structures and templates, there are a 
few additional steps you'll need to take.

For the carousel, the web content is added to its own folder, `carousel` inside 
the `journal/articles/` folder. The structure is then added to the 
`/structures` folder. Finally, the matching template is added to the 
`/templates` folder and placed in its own `carousel` folder. The finished 
directory for the carousel matches the following structure:

- `journal/`
    - `articles/`
        - `carousel/`
            - `carousel.xml`
        - (more web content articles)
    - `structures/`
        - `carousel.xml`
    - `templates/`
        - `carousel/`
            - `carousel.vm`

It's important that you create the file structure this way so the web content 
is recognized.

Now  you can create the `sitemap.json` file.
