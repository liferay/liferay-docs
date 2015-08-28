# Setting up the Directory Structure for the Resources Importer

A blank `resources-importer` directory was added to your theme when it was first 
created. Rather than add each file individually and create all the remaining 
folders, go ahead and delete the existing `resources-importer` directory.

1. Delete the existing `resources-importer` folder in the `docroot/WEB-INF/src` 
   directory of the Lunar Resort theme and replace it with the 
   `resources-importer` folder in the `themes/code/` directory of the learning 
   path.
   
The updated Resources Importer directory uses the following structure to 
organize the files for the Lunar Resort:

- `lunar-resort-theme/`
    - `docroot/WEB-INF/src/`
        - `resources-importer/`
            - `document_library/`
                -`documents/`
                        - (documents and media files)
            - `journal/`
                -`articles`
                        - (web content articles)
                -`structures/`
                -`templates/`
   
The resources for the theme are organized into documents and media files and
web content articles. Structures and templates are given their own folders
inside of the `journal` directory.
                
web content articles can be written in either HTML or XML and placed in the 
`journal/articles/` folder. Images included in the web content are referenced 
using the name of the file in the `document_library/documents` folder. The Web 
Content references the image using the following code:
   
    src= "[$FILE=filename.filetype$]"
        
That's all the prep work that needs to be completed for most web content. For
more advanced web content that requires structures and templates, there are a 
few additional steps you'll need to take.

For the carousel, the web content is added to its own folder, `carousel`, inside 
of the `journal/articles/` directory. The structure is then added to the 
`/structures` folder. Finally, the matching template is added to the 
`/templates` folder and placed in its own `carousel` folder. The finished 
directory for the carousel matches the following structure:

- `journal/`
    - `articles/`
        - `carousel/`
            - `carousel.xml`
            - (more web content articles)
    - `structures/`
        -`carousel.xml`
    - `templates/`
        -`carousel/`
            -`carousel.vm`

It's important that you setup the file structure this way so the web content 
will be recognized.

Now that you have the resources-importer file structure layed out, you can
create the `sitemap.json` next.