# Creating Docs for Liferay

It's a straightforward process to create Liferay documentation. The goal
is to create docs the developers want to read and to keep the style
consistent and clear regardless of author. For these reasons, we've
outlined the process and procedure below.

## Environment

Liferay Documentation is written in Markdown. The file extension should
be `.markdown`. Your text editor of choice can be used as long as you use
the following conventions:

-   All files are hard-wrapped at 80 columns
-   Use spaces instead of tabs because of the way Markdown processes
    indents
-   Every sentence, including those at the ends of paragraphs, should
    have a trailing space
-   Leave one blank space between headings and the paragraphs that
    follow
-   Links should start on their own line and not wrap

Markdown files are created in a folder structure that is parsed by our
Knowledge Base importer. The idea is to create a reading environment
like this:

![Our site has navigation on the left and articles on the right.](images/ldn-screenshot.png)

There are three areas to notice: the menu on the left, the article on the right,
and the list of articles at the bottom right. When a user clicks on a topic,
that topic opens. There could be one or several tutorials on that topic. The
first of these is the *intro* file. This is marked in the file system by
appending -intro to the file name (for example,
`service-builder-intro.markdown`).

Nesting is done by folders, and every folder must have an -intro file.
In the example above, therefore, you have this folder structure:

![The folder structure defines the outline.](images/folder-structure.png)

When a user, therefore, clicks on Tooling, LDN displays
00-intro.markdown on the right and expands the navigation on the left.
When a user clicks on Liferay IDE, LDN displays the 
`00-liferay-ide-intro.markdown` file and the navigation is further expanded.

Notice that in the file system, the folders are numbered. This is how
the topics are ordered on LDN. The topic titles, however, are taken from
the heading in the -intro file, so it's important to put the proper
headings in those files.

## Structure

All [Liferay Developer Network](https://dev.liferay.com) articles reside in
liferay-docs repository folders that are laid out in a similar manner to
LDN's pages and articles. Each repository folder under [`discover`](discover),
[`develop`](develop), and [`distribute`](distribute) represents a section of
LDN's articles. For example, the [`discover/portal`](discover/portal) folder
contains Markdown files and images that are the source for LDN's
[User & Admin&nbsp;&rarr;&nbsp;User Guide](https://dev.liferay.com/discover/portal)
articles. 

The following tables show the folder locations for articles published to LDN's
User & Admin, Developer, and Marketplace sections.

**Source Folders for Liferay Developer Network Articles:**

 ![User & Admin](images/discover.png)                                                                                           |
 :------------------------------------------------------------------------------------------------------------------------------------ |
  &nbsp;[**liferay-docs/discover/**](discover)                                                                                         |
  &nbsp;&#8226;&nbsp;[`portal/`](discover/portal) &rarr; [Portal](https://dev.liferay.com/discover/portal)                             |
  &nbsp;&#8226;&nbsp;[`social-office/`](https://github.com/liferay/liferay-docs/tree/6.2.x/discover/social-office) &rarr; [Social Office](https://dev.liferay.com/discover/social-office) |
  &nbsp;&#8226;&nbsp;[`deployment/`](discover/deployment) &rarr; [Deployment](https://dev.liferay.com/discover/deployment)             |
  &nbsp;&#8226;&nbsp;[`reference/`](discover/reference) &rarr; [Reference](https://dev.liferay.com/discover/reference)                 |
  &nbsp;&#8226;&nbsp;[`new-articles/`](discover/new-articles)  (You can submit new articles here for LDN's *User & Admin* section)         |

  ![Developer](images/develop.png) |
 :-------------------------------------------------------------------------------------------------------------------------------------- |
  &nbsp;[**liferay-docs/develop/**](develop)                                                                                             |
  &nbsp;&#8226;&nbsp;[`learning-paths/`](develop/learning-paths) &rarr; [Learning Paths](https://dev.liferay.com/develop/learning-paths) |
  &nbsp;&#8226;&nbsp;[`tutorials/`](develop/tutorials) &rarr; [Tutorials](https://dev.liferay.com/develop/tutorials)                     |
  &nbsp;&#8226;&nbsp;[`reference/`](develop/reference) &rarr; [Reference](https://dev.liferay.com/develop/reference)                     |
  &nbsp;&#8226;&nbsp;[`new-articles/`](develop/new-articles)  (You can submit new articles here for LDN's *Developer* section)             |

  ![Marketplace](images/distribute.png)                                                                                             |
 :------------------------------------------------------------------------------------------------------------------------------------------- |
  &nbsp;[**liferay-docs/distribute/**](distribute)                                                                                            |
  &nbsp;&#8226;&nbsp;[`distribute/new-articles/`](distribute/new-articles)  (You can submit new articles here for LDN's *Marketplace* section) |

## High Level Procedure 

First, you need a copy of the repository on your own machine, and to do that,
you must [clone](https://help.github.com/articles/fork-a-repo/)
the project. For information on how to work with Git projects, see
[*How do I use Git and GitHub?*](guidelines/faq.markdown#how-do-i-use-git-and-github). 

**To add an article** to LDN's [Develop](https://dev.liferay.com/develop)
section (as an example), create your article and its images in the
`liferay-docs/develop/new-articles` folder. Commit your added files and send a
[pull request](https://help.github.com/articles/using-pull-requests/) to default
user `liferay`. For more information on where to place new articles and for
guidelines on writing them, see
[*How do I write and submit my own article?*](guidelines/faq.markdown#how-do-i-write-and-submit-my-own-article). 

**To modify an article**, you can edit it locally in its repository location, in
a branch created for that purpose. After you've made your changes, commit them
and submit a [pull request](https://help.github.com/articles/using-pull-requests/) to the
default user `liferay`. 

**To preview an article**, invoke one of the `convert.[bat|sh]` scripts
on your Markdown file. The scripts are in the [`bin`](bin/) folder. See
[*How do I convert my local Markdown to HTML using the provided convert scripts?*](guidelines/faq.markdown#how-do-i-convert-my-local-markdown-to-html-using-the-provided-convert-scripts)
for details on using the script. 

## Liferay's Doc Standards and Customizations

As you write documentation, you'll want to be aware of our standards and
customizations. [Click here](02-standards-and-customizations.markdown)
