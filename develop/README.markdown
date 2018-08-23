# Developer Documentation for Developer Site

This folder holds article source files for publishing to the Developer Site (dev
site). The articles are divided into the following sets (or documents):

- *Tutorials:* Articles that comprehensively teach you how to use Liferay's
APIs and frameworks. Learning Path sections are also included here, which guide
you through the fundamentals of developing applications to meet your user's
needs.
- *Reference Docs:* Articles that provide reference material that may supplement
the tutorials, or just provide a wealth of knowledge not fitting as a teachable
tutorial.

The documents are comprised of article source files in Markdown format,
that follow the
[MultiMarkdown](http://fletcher.github.com/peg-multimarkdown/mmd-manual.pdf)
syntax. Images referenced in the document articles are to be stored in each
document's `images/` folder. Document sources are stored in directories that map
to their developer site locations.

Here is a summary of the *liferay-docs* repository directory structure, as it
relates to dev site documentation:

- `liferay-docs/`
    - `build.properties`
    - `build.username.properties`
    - `build-common.xml` (primary buildfile for dev site documentation)
    - `release-site.properties` (properties for creating document Zip files for
    a release) 
    - `develop/` (maps to the site's *Develop* tab)
        - `build.xml`
        - `tutorial-template.markdown` (template for tutorial articles)
        - `new-articles/` (Submit new tutorial article Markdown files, images,
        and code here)
        - `tutorials/` (*Tutorials* document folder)  
            - `articles/` 
                - `some-category/` (category folder named after the title of
                its dev site navigation) 
                    - `some-article.markdown` (named after the article's `<h1>` heading)
                    - `another-article.markdown`
                    - ...
                - `another-category/`
                - ...
            - *build/* (generated when building articles locally)
            - `code/` (code examples mapped to the document's articles) 
                - `some-category/` (use abbreviated category name to keep code
                path short)
                    - `begin/` (optional. Can refer to the start/end of another
                    tutorial's code if it makes sense.)
                        - `some-portlet` (the plugin on starting the tutorial)
                    - `end/`
                        - `some-portlet` (the plugin on finishing the tutorial)
                - ...
            - *dist/* (generated when building the Zip file of all of the
            document's articles and images, for importing to the dev site) 
            - `drawings/` (internal source files, such as .odg files, for drawings)
            - `images/` (image files used in the document's articles)
            - `build.xml`
        - `reference/`  (maps to the site's *Reference* tab)
            - `build.xml`
            - `articles/` 
                - `some-category/` (category folder named after the title of
                its dev site navigation) 
                    - `some-article.markdown` (named after the article's `<h1>` heading)
                    - `another-article.markdown`
                    - ...
                - `another-category/`
                - ...
            - *build/* (generated when building articles locally)
            - *dist/* (generated when building the Zip file of all of the
            document's articles and images, for importing to the dev site) 
            - `images/` (image files used in the document's articles)
    - `guidelines/` (instructions on writing articles, using tools, following
    Markdown conventions, etc.) 
    - `lib/` (internal JARs used by the custom Ant tasks)

Now that you know the "lay of the land", let's learn how to add new articles.

## Contributing

You can save your new tutorial article Markdown text, images, and code to the
`develop/new-articles/` folder. We'll move your tutorial to a proper location
under `tutorials/`, review your tutorial, and publish it to the site. 

Then send a pull request to user `liferay` on the appropriate branch.  

Here are the branches for the respective product releases:

- Liferay Portal 7.1 CE/DE - `7.1.x`
- Liferay Portal 7.0 CE/DE - `7.0.x`
- Liferay Portal 6.2 CE/EE - `6.2.x`
- Liferay Portal 6.1 CE/EE - `6.1.x`

As you write your article, make sure to follow the
[Liferay Documentation Writer's Guidelines](https://github.com/liferay/liferay-docs/blob/master/guidelines/03-writers-guidelines.markdown)
and our
[Standards and Customizations](https://github.com/liferay/liferay-docs/blob/master/guidelines/02-standards-and-customizations.markdown).
You can edit your Markdown files using your favorite editor. Many editors, like
jEdit, have Markdown plugins for highlighting its syntax. For tips on
configuring jEdit, see
[Editing Markdown Files](https://github.com/liferay/liferay-docs/blob/master/guidelines/02-standards-and-customizations.markdown#editing-markdown-files). 

For frequently asked questions, see our
[FAQ](https://github.com/liferay/liferay-docs/blob/master/guidelines/04-faq.markdown)
article.

From time to time as you write your article, you'll probably want to see how it
renders in HTML. Next we'll show you how to convert it from Markdown to HTML. 

## Rendering an Existing Markdown Article File Locally as HTML 

To see what a local article looks like converted from Markdown to HTML, you can
use our [Ant](http://ant.apache.org/bindownload.cgi) build targets and the
[Pandoc](https://github.com/liferay/liferay-docs/blob/master/guidelines/02-standards-and-customizations.markdown#markdown-metadata)
Markdown parsing application. 

Here's how to build an HTML version of a *Tutorials* article from the terminal:

1. `cd liferay-docs/develop/tutorials`

2.  Execute the `article-to-html` target passing the article's relative path as the
`article` property value. Below is an example of executing this command on the
`01-using-liferay-ui-success-and-error-messages.markdown` file that resides in
the `21-liferayui-taglibs` category folder.

    ```
    ant article-to-html -Darticle=articles/liferayui-taglibs/using-liferay-ui-success-and-error-messages.markdown
    ```

    *Hint:* You can leverage your terminal's auto-complete feature when
    specifying the article path. 

3.  The HTML file is produced to the corresponding folder under `build/` (e.g.,
`build/articles/liferayui-taglibs/using-liferay-ui-success-and-error-messages.markdown.html`).

It's just that easy!

