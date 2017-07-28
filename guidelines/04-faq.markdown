# FAQ

**Table of Contents**

* [How do I submit a change to an article?](#how-do-i-submit-a-change-to-an-article)
* [How do I request a new article or a fix to an existing article?](#how-do-i-request-a-new-article-or-a-fix-to-an-existing-article)
* [How do I use Git and GitHub?](#how-do-i-use-git-and-github)
* [How do I write and submit my own article?](#how-do-i-write-and-submit-my-own-article)
* [How do I convert my local Markdown to HTML using the provided convert scripts?](#how-do-i-convert-my-local-markdown-to-html-using-the-provided-convert-scripts)

## How do I submit a change to an article?

Submitting changes to an article on LDN is straightforward. At the top 
right of each article on LDN is a green *Edit on GitHub* button. Click it:

![Figure 1: Each LDN article has an *Edit on GitHub* button.](images/readme-edit-on-github.png)
 
You're then taken to that article on GitHub. To edit it, click the pencil icon 
at the top right of the article. This forks the project and opens up an editor 
in your browser so you can make changes to the file. Make your changes in the 
editor and then put in a title and a description of the changes in the 
*Propose file change* section at the bottom of the screen. When you’re finished, 
click the *Propose file change* button. This takes you to a screen where you can 
submit a pull request with your changes.

## How do I request a new article or a fix to an existing article?

To request new articles or fixes, create a ticket in the JIRA project 
[Liferay Documentation](https://issues.liferay.com/browse/LRDOCS/?selectedTab=com.atlassian.jira.jira-projects-plugin:summary-panel). 
To create a ticket, click the blue *Create* button at the top of the JIRA page:
 
![Figure 2: Click the *Create* button to create a new ticket in the Liferay Documentation Project.](images/readme-new-ticket.png)
 
This launches the *Create Issue* wizard, which walks you through the steps of 
creating a new ticket. First, make sure that *PUBLIC - Liferay Documentation* is 
selected in the *Project* menu. Then select the issue type from the *Issue Type* 
menu. Select *Bug* if you're reporting incorrect information in an existing 
article. For a new article or addition to an existing article, select 
*Documentation*. Then click *Next*.
 
![Figure 3: The *Create Issue* wizard guides you in logging your request.](images/readme-new-ticket-info.png)

In the second step of the *Create Issue* wizard, enter a brief summary of the 
change or addition. Then select the component type from the *Component/s* menu. 
The component indicates the type of article your request is for. For example, if 
you're requesting a change or addition to an LDN tutorial, select *Tutorial*. 
Similarly, select *Learning Path* if you're requesting a change to an LDN 
Learning Path article. Next, if you're submitting a bug, select the version that 
your request applies to from the *Affects Version/s* menu. Last but certainly 
not least, enter a more detailed description of your request in the 
*Description* field. Click *Create* when you're finished.
 
## How do I use Git and GitHub?

To use Git and GitHub, you first need to create an account on
[github.com](https://github.com/). Then read from the following GitHub
tutorials: 
 
* [Set up Git](https://help.github.com/articles/set-up-git/)
* [Fork a Repo](https://help.github.com/articles/fork-a-repo/)
* [Committing Your Changes](http://git-scm.com/book/en/v2/Git-Basics-Recording-Changes-to-the-Repository#Committing-Your-Changes)
* [Using Pull Requests](https://help.github.com/articles/using-pull-requests/)
* [Be Social](https://help.github.com/articles/be-social/)

## How do I write and submit my own article?
 
First, you should read 
[Creating Docs for Liferay](01-creating-docs-for-liferay.markdown), 
[Standards and Customizations](02-standards-and-customizations.markdown), and
[Writer's Guidelines](03-writers-guidelines.markdown). These articles describe
the tools and writing style that you should use. The structure of your article
also depends on what kind of article it is.  If you're writing an LDN developer
tutorial, you should structure it according to the [tutorial
template](../develop/tutorial-template.markdown). If you're writing an LDN user
article, you should structure it similarly to the 
[articles in the Discover section of LDN](../discover/portal/articles/01-what-is-liferay/01-building-site-with-liferay-web-content.markdown). 
To submit an article, you must first fork the liferay-docs repository on 
GitHub. Then create a new branch for your article on your forked repository. 
If your article is a tutorial, place it in the
[develop/new-articles](../develop/new-articles) folder. If your article is a user
article, place it in the
[discover/new-articles](../discover/new-articles) folder. You should also place any 
images your article uses in those folders along with it. Once you commit and 
push your changes to your forked liferay-docs repository, submit a pull
request to the default user `liferay`. Alternatively, you can write and submit
your article using the web interface on GitHub. Just navigate to the appropriate
`new-articles` folder for your article type and then click the plus icon in the
file path: 
 
![Figure 4: On GitHub, you can click the *plus* icon to add a new file in the file path.](images/readme-new-file-github.png)
 
This forks the repository and brings up the web editor where you can name the 
new file and compose your article. When you're done, enter a description in the 
*Propose new file* section at the bottom of the screen and then click the 
*Propose new file* button.
 
## How do I convert my local Markdown to HTML using the provided convert scripts?

There's a Batch and Shell convert script provided so you can use this tool on 
any operating system. These files are located in the [`liferay-docs/bin`](../bin/)
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

