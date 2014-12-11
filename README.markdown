# LIFERAY-DOCS

<a href="https://dev.liferay.com" >
<img src="guidelines/images/liferayDeveloperNetworkSmallerEdit7.jpg" alt="Liferay Docs Icon">
</a>

Welcome to Liferay's official documentation project, the home of
[Liferay Developer Network](https://dev.liferay.com) articles. All articles are
written in
[Markdown](http://fletcher.github.com/peg-multimarkdown/mmd-manual.pdf), making
them easy to write and easy to read. Approved articles are uploaded to the
Liferay Developer Network (LDN) and converted automatically to HTML. In this
project, you can contribute new articles, improve existing articles, or fix
documentation bugs. To produce documentation that is comprehensive and
stylistically consistent, the `liferay-docs` project provides
[writing guidelines](guidelines/writers-guidelines.markdown),
[Markdown guidelines](guidelines/writers-guidelines.markdown), and a
[tutorial template](develop/tutorial-template.markdown).

You can take a quick look at the *Table of Contents* below and then learn how to
contribute, by following the [Getting Started](#getting-started) section. 

## Table of Contents

- [Getting Started](#getting-started)
- [Structure](#structure)
- [FAQ](#faq)

## Getting Started

If you’re new to the `liferay-docs` repository and you want to add/edit material
locally, you first must clone the project. For information on how to work with
Git projects, see
[How do I use Git and GitHub?](#how-do-i-use-git-and-github).

To contribute a new article, add it in an appropriate `new-articles` folder
found in either the `discover`, `develop`, or `distribute` folder. The
repository folders follow a [structure](#structure) similar to the Liferay
Developer Network's layout of pages and articles. To add an article to LDN's
[Develop](https://dev.liferay.com/develop) section, for example, create your
article and its images in the `liferay-docs/develop/new-articles` folder. For
more information on where to place new articles and for guidelines on writing
them, see
[How do I write and submit my own article?](#how-do-i-write-and-submit-my-own-article).

All articles should be written in Markdown. Converting your article to HTML
locally helps you ensure you’ve correctly formatted your Markdown text. You can
convert your Markdown to HTML by using one of the `convert.[bat|sh]` scripts
found in the [`bin`](bin/) folder. See
[How do I convert my local Markdown to HTML using the provided convert scripts?](#how-do-i-convert-my-local-markdown-to-html-using-the-provided-convert-scripts)
for details on using the script.

If you’d like to modify an existing article, you can edit it in its current
repository location. After you’ve made your changes, commit them and submit a
GitHub pull request to the default user `liferay`. To learn how to commit
changes and submit pull requests, see
[How do I use Git and GitHub?](#how-do-i-use-git-and-github). 

After you've submitted a pull request, Liferay’s documentation team reviews
your contribution. Approved changes are merged into the `liferay-docs` repo and
published to the [Liferay Developer Network](https://dev.liferay.com).

## Structure

All [Liferay Developer Network](https://dev.liferay.com) articles reside in
`liferay-docs` repository folders. The folders are laid out in a similar manner
to LDN's pages and articles. Each repository folder under `discover`, `develop`,
and `distribute` represents a section of LDN's articles. For example, the
`discover/portal` folder contains Markdown files and images that are the source
for LDN's
[Discover&rarr;Portal](https://dev.liferay.com/discover/portal) articles. 

To contribute new articles and images or modifications to LDN's 
[Discover](https://dev.liferay.com/discover),
[Develop](https://dev.liferay.com/develop), or
[Distribute](https://dev.liferay.com/distribute) sections, commit them to the
respective `new-articles` folder. 

**Article Folders and Mappings to LDN Pages:**

 ![Discover](guidelines/images/discover.png)                                             | ![Develop](guidelines/images/develop.png)                                              | ![Distribute](guidelines/images/distribute.png) 
 :-------------------------------------------------------------------------------------- | :------------------------------------------------------------------------------------- | :--------------------------------------------
  &nbsp;[**discover/**](https://dev.liferay.com/discover)                                | &nbsp;[**develop/**](https://dev.liferay.com/develop)                                  | [**distribute/**](https://dev.liferay.com/distribute) 
  &nbsp;&#8226;&nbsp;[`deployment/`](https://dev.liferay.com/discover/deployment)        | &nbsp;&#8226;&nbsp;[`learning-paths/`](https://dev.liferay.com/develop/learning-paths) | &nbsp;&#8226;&nbsp;`new-articles/` 
  &nbsp;&#8226;&nbsp;`new-articles/`                                                     | &nbsp;&#8226;&nbsp;`new-articles/`                                                     |
  &nbsp;&#8226;&nbsp;[`portal/`](https://dev.liferay.com/discover/portal)                | &nbsp;&#8226;&nbsp;[`reference/`](https://dev.liferay.com/develop/reference)           |
  &nbsp;&#8226;&nbsp;[`reference/`](https://dev.liferay.com/discover/reference)          | &nbsp;&#8226;&nbsp;[`tutorials/`](https://dev.liferay.com/develop/tutorials)           |
  &nbsp;&#8226;&nbsp;[`social-office/`](https://dev.liferay.com/discover/social-office)  |                                                                                        |
  
You've learned how to contirbute and you know the article folder structure. Got
questions? We have answers for you in the FAQ section, next. 

## FAQ

**Table of Contents**

* [How do I submit a change to an article?](#how-do-i-submit-a-change-to-an-article)
* [How do I request a new article or a fix to an existing article?](#how-do-i-request-a-new-article-or-a-fix-to-an-existing-article)
* [How do I use Git and GitHub?](#how-do-i-use-git-and-github)
* [How do I write and submit my own article?](#how-do-i-write-and-submit-my-own-article)
* [How do I convert my local Markdown to HTML using the provided convert scripts?](#how-do-i-convert-my-local-markdown-to-html-using-the-provided-convert-scripts)

### How do I submit a change to an article?

Submitting changes to an article on LDN is straightforward. At the top 
right of each article on LDN is a green *Edit on GitHub* button. Click it:

![Figure 1: Each LDN article has an *Edit on GitHub* button.](guidelines/images/readme-edit-on-github.png)
    
You're then taken to that article on GitHub. To edit it, click the pencil icon 
at the top right of the article. This forks the project and opens up an editor 
in your browser so you can make changes to the file. Make your changes in the 
editor and then put in a title and a description of the changes in the 
*Propose file change* section at the bottom of the screen. When you’re finished, 
click the *Propose file change* button. This takes you to a screen where you can 
submit a pull request with your changes.

### How do I request a new article or a fix to an existing article?

To request new articles or fixes, create a ticket in the JIRA project 
[Liferay Documentation](https://issues.liferay.com/browse/LRDOCS/?selectedTab=com.atlassian.jira.jira-projects-plugin:summary-panel). 
To create a ticket, click the blue *Create* button at the top of the JIRA page:
    
![Figure 2: Click the *Create* button to create a new ticket in the Liferay Documentation Project.](guidelines/images/readme-new-ticket.png)
    
This launches the *Create Issue* wizard, which walks you through the steps of 
creating a new ticket. First, make sure that *PUBLIC - Liferay Documentation* is 
selected in the *Project* menu. Then select the issue type from the *Issue Type* 
menu. Select *Bug* if you're reporting incorrect information in an existing 
article. For a new article or addition to an existing article, select 
*Documentation*. Then click *Next*.
    
![Figure 3: The *Create Issue* wizard guides you in logging your request.](guidelines/images/readme-new-ticket-info.png)

In the second step of the *Create Issue* wizard, enter a brief summary of the 
change or addition. Then select the component type from the *Component/s* menu. 
The component indicates the type of article your request is for. For example, if 
you're requesting a change or addition to an LDN tutorial, select *Tutorial*. 
Similarly, select *Learning Path* if you're requesting a change to an LDN 
Learning Path article. Next, if you're submitting a bug, select the version that 
your request applies to from the *Affects Version/s* menu. Last but certainly 
not least, enter a more detailed description of your request in the 
*Description* field. Click *Create* when you're finished.
    
### How do I use Git and GitHub?

To use Git and GitHub, you first need to create an account on
[github.com](https://github.com/). Then read from the following GitHub
tutorials: 
    
* [Set up Git](https://help.github.com/articles/set-up-git/)
* [Fork a Repo](https://help.github.com/articles/fork-a-repo/)
* [Committing Your Changes](http://git-scm.com/book/en/v2/Git-Basics-Recording-Changes-to-the-Repository#Committing-Your-Changes)
* [Using Pull Requests](https://help.github.com/articles/using-pull-requests/)
* [Be Social](https://help.github.com/articles/be-social/)

### How do I write and submit my own article?
   
First, you should read the
[Liferay Documentation Tools](guidelines/liferay-documentation-tools.markdown) 
and
[Liferay Documentation Writer's Guidelines](guidelines/writers-guidelines.markdown) 
articles. These articles describe the tools and writing style that you should 
use. The structure of your article also depends on what kind of article it is. 
If you're writing an LDN developer tutorial, you should structure it according to 
the [tutorial template](develop/tutorial-template.markdown). If you're writing
an LDN user article, you should structure it similarly to the 
[articles in the Discover section of LDN](discover/portal/articles/01-what-is-liferay/01-building-site-with-liferay-web-content.markdown). 
To submit an article, you must first fork the `liferay-docs` repository on 
GitHub. Then create a new branch for your article on your forked repository. 
If your article is a tutorial, place it in the
[develop/new-articles](develop/new-articles) folder. If your article is a user
article, place it in the
[discover/new-articles](discover/new-articles) folder. You should also place any 
images your article uses in those folders along with it. Once you commit and 
push your changes to your forked `liferay-docs` repository, submit a pull
request to the default user `liferay`. Alternatively, you can write and submit
your article using the web interface on GitHub. Just navigate to the appropriate
`new-articles` folder for your article type and then click the plus icon in the
file path:  
    
![Figure 4: On GitHub, you can click the *plus* icon to add a new file in the file path.](guidelines/images/readme-new-file-github.png)
    
This forks the repository and brings up the web editor where you can name the 
new file and compose your article. When you're done, enter a description in the 
*Propose new file* section at the bottom of the screen and then click the 
*Propose new file* button.
    
### How do I convert my local Markdown to HTML using the provided convert scripts?

There's a Batch and Shell convert script provided so you can use this tool on 
any operating system. These files are located in the [`liferay-docs/bin`](bin/)
folder. To run the convert script, use the following command:
    
    convert.bat|sh [Markdown file to convert] [HTML file to be written]
    
First specify the path for either the `convert.bat` or `convert.sh` script. The
script's first parameter must be the Markdown file path. The second
parameter (optional) lets you specify the path of an HTML file to generate.
As a working example, the following command would convert an article found in
the `discover/new-articles` folder:

    FILE_PATH/convert.bat REPO_PATH/liferay-docs/discover/new-articles/intro.markdown

By running this command without specifying an HTML file path, an HTML file named
after the Markdown file (but having a `.html` suffix) is generated in the
Markdown file's folder. When committing new/modified articles, make sure not to
commit generated HTML files. It's also important to note that you can run the
script from any directory, as long as you provide the script's file path. 

