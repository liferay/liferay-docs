# Developer Documentation for Developer Site

This folder holds article source files for publishing to the Developer Site (dev
site). The articles are divided into the following sets (or documents):

- *Learning Paths:* Articles that guide you through the fundamentals of
developing applications to meet your user's needs.
- *Tutorials:* Articles that comprehensively teach you how to use Liferay's
APIs and frameworks.

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
    - `build-site-common.xml` (primary buildfile for dev site documentation)
    - `release-site.properties` (properties for creating document Zip files for
    a release) 
    - `develop/` (maps to the site's *Develop* tab)
        - `build.xml`
        - `tutorial-template.markdown` (template for tutorial articles)
        - `learning-paths/` (*Learning Paths* document folder. Has the same
        directory structure as the `tutorials/` document folder below.)  
        - `tutorials/` (*Tutorials* document folder)  
            - `build.xml`
            - `articles/` 
                - `01-some-category/` (category folder named after the title of
                its dev site navigation) 
                    - `01-some-article.markdown` (named after the article's `<h1>` heading)
                    - `02-some-article.markdown`
                    - ...
                - `02-some-category/`
                - ...
            - *build/* (generated when building articles locally)
            - `code/` (code examples mapped to the document's articles) 
                - `01-some-category/`
                    - `begin/`
                        - `some-portlet` (the plugin on starting the tutorial)
                    - `end/`
                        - `some-portlet` (the plugin on finishing the tutorial)
                - ...
            - *dist/* (generated when building the Zip file of all of the
            document's articles and images, for importing to the dev site) 
            - `drawings/` (internal source files, such as .odg files, for drawings)
            - `images/` (image files used in the document's articles)
            - `build.xml`
    - `guidelines/` (instructions on writing articles, using tools, following
    Markdown conventions, etc.) 
    - `lib/` (internal JARs used by the custom Ant tasks)

Now that you know the "lay of the land", let's learn how to add new articles.

## Adding Articles

To add a new article to the dev site, follow these simple steps:

1.  Determine the *document* for your article: the *Learning Paths* document or
the *Tutorials* document. If your article demonstrates using a particular API or
framework, it's a Tutorials article. If in doubt, add your article as a
Tutorials document article. We can always move your article to where it needs to
be after you submit it. 

2.  Determine the document's *category* for the article. Check the dev site's
navigation tree nodes for a category that best fits your article's topic. The
article categories folders under `learning-paths/articles/` and
`tutorials/articles/` map to these dev site categories. 

3.  Save your article to the category folder prefixing the file with the next
available article number prefix. 

    For example, if you want to add a new article to the
    `tutorials/articles/01-users-and-organizations/` category folder and the
    folder has existing article files prefixed from `01-` to 
    `03-`, prefix your new article file with `04-`:

    ```
    01-users-and-organizations/
        01-adding-users.markdown
        02-updating-users.markdown
        03-adding-organizations.markdown
        04-[your-new-article].markdown
    ```
4.  Save each image file that your article references into the appropriate
`images/` folder (e.g., `learning-paths/images/` or `tutorials/images/`). 

As you write your article, make sure to follow the
[Liferay Documentation Writer's Guidelines](https://github.com/liferay/liferay-docs/blob/master/guidelines/writers-guidelines.markdown)
and our
[Markdown
Tips](https://github.com/liferay/liferay-docs/blob/master/guidelines/liferay-documentation-tools.markdown#markdown-tips).
You can edit your Markdown files using your favorite editor. Many editors, like
jEdit, have Markdown plugins for highlighting its syntax. For tips on
configuring jEdit, see [Editing Markdown
Files](https://github.com/liferay/liferay-docs/blob/master/guidelines/liferay-documentation-tools.markdown#editing-markdown-files). 

From time to time as you write your article, you'll probably want to see how it
renders in HTML. Next we'll show you how to convert it from Markdown to HTML. 

## Rendering Your Markdown Article Files as HTML Locally 

To see what your article looks like converted to HTML, you can use our
[Ant](http://ant.apache.org/bindownload.cgi) build targets and the
[Pandoc](https://github.com/liferay/liferay-docs/blob/master/guidelines/liferay-documentation-tools.markdown#installing-pandoc)
Markdown parsing application. 

Here's how to build an HTML version of a *Tutorials* article from the terminal:

1. `cd liferay-docs/develop/tutorials`

2.  Execute the `article-to-html` target passing the article's relative path as the
`article` property value. Below is an example of executing this command on the
`01-using-liferay-ui-success-and-error-messages.markdown` file that resides in
the `21-liferayui-taglibs` category folder. 

    On Linux, execute:

    ```
    ant article-to-html -Darticle=articles/21-liferayui-taglibs/01-using-liferay-ui-success-and-error-messages.markdown
    ```
    
    On Windows, execute the target that has the `-win` suffix:

    ```
    ant article-to-html-win -Darticle=articles/21-liferayui-taglibs/01-using-liferay-ui-success-and-error-messages.markdown
    ```

    *Hint:* To leverage your terminal's auto-complete feature in typing out the
    article path, leave a space between the `=` character and the article path.
    You can always remove the space before running the command. 

3.  The HTML file is produced to the corresponding folder under `build/` (e.g.,
`build/articles/21-liferayui-taglibs/01-using-liferay-ui-success-and-error-messages.markdown.html`).

It's just that easy!

## Contributing Documentation

To contribute new articles or bug fixes, please send pull requests to user
`liferay` on the appropriate branch.  

Here are the branches for the respective product releases:

- Liferay Portal 6.2 CE/EE - `master`
- Liferay Portal 6.1 CE/EE - `6.1.x`

We look forward to your contributions!

