# LIFERAY-DOCS

 ![Liferay Docs Icon](guidelines/images/liferayDeveloperNetworkSmallerEdit7.jpg)

Welcome to Liferay's official documentation repository, home to
[Liferay Developer Network](https://dev.liferay.com) articles. You can
contribute new articles and submit fixes for existing articles in this
repository. The articles are written in
[Markdown](http://daringfireball.net/projects/markdown/syntax) text for
uploading to the Liferay Developer Network. To render article drafts as HTML
locally, you can execute a [`convert`](bin/) script. You have style guidelines
for [writing](guidelines/writers-guidelines.markdown) and following
[Markdown conventions](guidelines/writers-guidelines.markdown), to
produce documentation that is comprehensive and consistent. 

## Table of Contents

- [Getting Started](#getting-started)
- [Structure](#structure)
- [FAQ](#faq)

## Getting Started

If you’re new to the `liferay-docs` repository, you’ll need to clone the project
locally to begin adding/editing material. For more information on how to do
this, visit the [How do I use Git and GitHub?](#how-do-i-use-git-and-github)
FAQ.

To add a new article you’ve written and its encompassing images to this
repository, you’ll need to place it in the appropriate `new-articles` folder.
The repository is laid out similarly to how it’s displayed in [Liferay’s
Developer Network (LDN)](https://dev.liferay.com/). For example, if your article
should reside in the Develop section of LDN, then paste your article into the
`new-articles` folder residing in the `liferay-docs/develop` directory. For more
information on where to place new articles and the guidelines on writing them,
visit the following [How do I write and submit my own article?](#how-do-i-write-and-submit-my-own-article)
FAQ.

Your new article should be written in Markdown, which is a text format designed
so it can be converted to HTML. Sometimes converting the Markdown to HTML is the
easiest way to ensure you’ve correctly formatted your text. You can convert your
Markdown to HTML by using *convert* scripts that this repository provides. Visit
the [How do I convert my Markdown using the provided convert scripts?](#how-do-i-convert-my-markdown-using-the-provided-convert-scripts)
FAQ for details on how to use them.

If you’d like to edit an existing article, you can open it in its current
location and make the necessary updates. There is no need to reproduce the
article by placing it in the new-articles folder.

After you’ve made your changes, you’ll need to commit them and submit a Github
pull request. To learn more about committing your changes and submitting pull
requests, visit the [How do I use Git and GitHub?](#how-do-i-use-git-and-github)
FAQ. You should submit all pull requests to the default `liferay` user.

Once your pull request is submitted, Liferay’s documentation team will review
your additions/changes, merge them into the `liferay-docs` repo, and publish
them on LDN.

## Structure

All of the [Liferay Developer Network](https://dev.liferay.com) articles map to
logical folders in the liferay-docs repository. Each folders under discover,
develop, and distribute represents a section of articles. For example, the
discover/portal folder contains article Markdown files and images that are the
source for the
[Discover&rarr;Portal](https://dev.liferay.com/discover/portal) section of
Liferay Developer Network. 

To contribute new articles, and images, to the 
[Discover](https://dev.liferay.com/discover) or
[Develop](https://dev.liferay.com/develop) sections of Liferay Developer
Network, commit them to the respective `new-articles` folder. 

**Liferay Developer Network article source folders:**

 ![Discover](guidelines/images/discover.png)                                             | ![Develop](guidelines/images/develop.png)                                              | ![Distribute](guidelines/images/discover.png) 
 :-------------------------------------------------------------------------------------- | :------------------------------------------------------------------------------------- | :--------------------------------------------
  &nbsp;[**discover/**](https://dev.liferay.com/discover)                                | &nbsp;[**develop/**](https://dev.liferay.com/develop)                                  | [**distribute/**](https://dev.liferay.com/distribute) 
  &nbsp;&#8226;&nbsp;[`deployment/`](https://dev.liferay.com/discover/deployment)        | &nbsp;&#8226;&nbsp;[`learning-paths/`](https://dev.liferay.com/develop/learning-paths) |
  &nbsp;&#8226;&nbsp;`new-articles/`                                                     | &nbsp;&#8226;&nbsp;`new-articles/`                                                     |
  &nbsp;&#8226;&nbsp;[`portal/`](https://dev.liferay.com/discover/portal)                | &nbsp;&#8226;&nbsp;[`reference/`](https://dev.liferay.com/develop/reference)           |
  &nbsp;&#8226;&nbsp;[`reference/`](https://dev.liferay.com/discover/reference)          | &nbsp;&#8226;&nbsp;[`tutorials/`](https://dev.liferay.com/develop/tutorials)           |
  &nbsp;&#8226;&nbsp;[`social-office/`](https://dev.liferay.com/discover/social-office)  |                                                                                        |
  
You've learned how to get started with contributing and you know the article
folder structure. Got questions? We have answers for you in the FAQ section,
next. 

## FAQ

**Table of Contents**

* [How do I submit a change to an article?](#how-do-i-submit-a-change-to-an-article)
* [How do I request a new article or a fix to an existing article?](#how-do-i-request-a-new-article-or-a-fix-to-an-existing-article)
* [How do I use Git and GitHub?](#how-do-i-use-git-and-github)
* [How do I write and submit my own article?](#how-do-i-write-and-submit-my-own-article)
* [How do I convert my Markdown using the provided convert scripts?](#how-do-i-convert-my-markdown-using-the-provided-convert-scripts)

### How do I submit a change to an article?

Submitting changes to an article on LDN is very straightforward. At the top 
right of each article on LDN is a green *Edit on Github* button. Click it:
    
![Figure x: The *Edit on GitHub button on LDN articles.](guidelines/images/readme-edit-on-github.png)
    
You're then taken to that article on GitHub. To edit it, click the pencil icon 
at the top right of the article. This forks the project and opens up an editor 
in your browser so you can make changes to the file. Make your changes in the 
editor and then put in a title and a description of the changes in the 
*Propose file change* section at the bottom of the screen. When you’re finished, 
click the *Propose file change* button. This takes you to a screen where you can 
submit a pull request with your changes.

### How do I request a new article or a fix to an existing article?

To request new articles or fixes, you need to create a JIRA ticket in the public 
[Liferay Documentation project](https://issues.liferay.com/browse/LRDOCS/?selectedTab=com.atlassian.jira.jira-projects-plugin:summary-panel). 
To create a ticket, click the blue *Create* button at the top of the JIRA page:
    
![Figure x: Click the *Create* button to create a new ticket in the Liferay Documentation Project.](guidelines/images/readme-new-ticket.png)
    
This launches the *Create Issue* wizard, which walks you through the steps of 
creating a new ticket. First, make sure that *PUBLIC - Liferay Documentation* is 
selected in the *Project* menu. Then select the issue type from the *Issue Type* 
menu. Select *Bug* if you're reporting incorrect information in an existing 
article. For a new article or addition to an existing article, select 
*Documentation*. Click *Next* when you're done.
    
![Figure x: The second step of the *Create Issue* wizard.](guidelines/images/readme-new-ticket-info.png)
    
In the second step of the *Create Issue* wizard, enter a brief summary of the 
change or addition. Then select the component type from the *Component/s* menu. 
The component indicates the type of article your request is for. For example, if 
you're requesting a change or addition to a LDN tutorial, select *Tutorial*. 
Similarly, select *Learning Path* if you're requesting a change to a LDN 
Learning Path article. Next, if you're submitting a bug, select the version that 
your request applies to from the *Affects Version/s* menu. Last but certainly 
not least, enter a more detailed description of your request in the 
*Description* field. Click *Create* when you're finished.
    
### How do I use Git and GitHub?

To use Git and GitHub, you first need to create an account on [github.com](https://github.com/). 
Then just go through the following tutorials on GitHub:
    
* [Set up Git](https://help.github.com/articles/set-up-git/)
* [Fork a Repo](https://help.github.com/articles/fork-a-repo/)
* [Committing Your Changes](http://git-scm.com/book/en/v2/Git-Basics-Recording-Changes-to-the-Repository#Committing-Your-Changes)
* [Using Pull Requests](https://help.github.com/articles/using-pull-requests/)
* [Be Social](https://help.github.com/articles/be-social/)

### How do I write and submit my own article?
   
First, you should read the [Liferay Documentation Tools](guidelines/liferay-documentation-tools.markdown) 
and [Liferay Documentation Writer's Guidelines](guidelines/writers-guidelines.markdown) 
articles. These articles describe the tools and writing style that you need to 
use. The structure of your article also depends on what kind of article it is. 
If you're writing a LDN developer tutorial, you should structure it according to 
the [tutorial template](develop/tutorial-template.markdown). If you're writing a 
LDN user article you should structure it similarly to the 
[articles in the Discover section of LDN](discover/portal/articles/01-what-is-liferay/01-building-site-with-liferay-web-content.markdown). 
To submit an article, you first need to fork the liferay-docs repository on 
GitHub. Then create a new branch for your article on your forked repository. 
If your article is a tutorial, place it in the [develop/new-articles](develop/new-articles) 
folder. If your article is a user article, place it in the 
[discover/new-articles](discover/new-articles) folder. You should also place any 
images your article uses in those folders along with it. Once you commit and 
push your changes to your forked repository, submit a pull request to 
liferay-docs. Alternatively, you can write and submit your article using the web 
interface on GitHub. Just navigate to the appropriate new-articles folder for 
your article type and then click the plus icon in the file path: 
    
![Figure x: Click the *plus* icon to add a new file in the file path.](guidelines/images/readme-new-file-github.png)
    
This forks the repository and brings up the web editor where you can name the 
new file and compose your article. When you're done, enter a description in the 
*Propose new file* section at the bottom of the screen and then click the 
*Propose new file* button.
    
### How do I convert my Markdown using the provided convert scripts?

There is a Batch and Shell convert script provided so you can use this tool on 
any operating system. These files are located in the `liferay-docs/bin` 
directory. To run the convert script, use the following command:
    
    convert.bat|sh [Markdown file to convert][HTML file to be written]
    
The first parameter is the path to the convert script you choose to use. The 
second parameter should be the file path to the Markdown file you’d like to 
convert to HTML. The third parameter is optional, which describes the file path 
where you'd like to create the converted HTML file. As a working example, the 
following command would convert an article placed in the discover directory's 
`new-articles` folder:
    
    FILE_PATH/convert.bat REPO_PATH/liferay-docs/discover/new-articles/intro.markdown
        
By running this command, your HTML conversion file is placed in the 
`new-articles` folder. When submitting new articles, make sure you've deleted 
the HTML file so it's not included in your pull request to `liferay-docs`. It's 
also important to note that you can run the script from any directory, as long 
as you provide the script's file path.
