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
stylistically consistent, the liferay-docs project provides
[writing guidelines](guidelines/writers-guidelines.markdown),
[Markdown guidelines](guidelines/liferay-documentation-tools.markdown), and a
[tutorial template](develop/tutorial-template.markdown).

You'll learn how to quickly add a new article and its images, next. 

## Quick Steps to Add a New Article

From [dev.liferay.com](https://dev.liferay.com) you can access articles in the
context of the liferay-docs GitHub project, for adding peer articles. 

1.  On [dev.liferay.com](https://dev.liferay.com), navigate to an article in the
    section ([Discover](https://dev.liferay.com/discover),
    [Develop](https://dev.liferay.com/develop), or
    [Distribute](https://dev.liferay.com/distribute)) in which you want to add a
    new article. 

    If you don't already have a GitHub account,
    [signup](https://github.com/join) for GitHub. 

2.  Click the
    ![Edit on GitHub](guidelines/images/edit-on-github.png) button, to see the
    article in the context of the liferay-docs project.

3.  In GitHub, navigate to the `new-articles` folder
    of the current article's section and click the **plus** sign:
    ![new articles folder](guidelines/images/new-articles-folder.png)

4.  Write your article and click the
    ![Propose new file](guidelines/images/propose-new-file.png) button, to
    prepare a pull request for the new article. 

5.  Send your
    [pull request](https://help.github.com/articles/using-pull-requests/) to the
    default user `liferay`. 

6.  Drag your article's images into the pull request's comments, so the
    reviewers can access them. 

You've new article is submitted! Liferay's Knowledge Management team will review
your contribution. Approved changes are merged into the liferay-docs repo and
published to the [Liferay Developer Network](https://dev.liferay.com). 

## Working with the Project Locally

If you’re new to the liferay-docs repository and you want to add/edit material
locally, you first must clone the project. For information on how to work with
Git projects, see
[*How do I use Git and GitHub?*](guidelines/faq.markdown#how-do-i-use-git-and-github)

All [Liferay Developer Network](https://dev.liferay.com) articles reside in
liferay-docs repository folders. The folders are laid out in a similar manner to
LDN's pages and articles. Each repository folder under [`discover`](discover),
[`develop`](develop), and [`distribute`](distribute) represents a section of
LDN's articles. For example, the [`discover/portal`](discover/portal) folder
contains Markdown files and images that are the source for LDN's
[Discover&nbsp;&rarr;&nbsp;Portal](https://dev.liferay.com/discover/portal) articles. 

To contribute new articles and images or modifications to LDN's 
[Discover](https://dev.liferay.com/discover),
[Develop](https://dev.liferay.com/develop), or
[Distribute](https://dev.liferay.com/distribute) sections, commit them to the
respective `new-articles` folder. 

**Article Folders and Mappings to LDN Pages:**

 ![Discover](guidelines/images/discover.png)                                             | ![Develop](guidelines/images/develop.png)                                              | ![Distribute](guidelines/images/distribute.png) 
 :-------------------------------------------------------------------------------------- | :------------------------------------------------------------------------------------- | :--------------------------------------------
  &nbsp;[**discover/**](https://dev.liferay.com/discover)                                | &nbsp;[**develop/**](https://dev.liferay.com/develop)                                  | [**distribute/**](https://dev.liferay.com/distribute) 
  &nbsp;&#8226;&nbsp;[`deployment/`](https://dev.liferay.com/discover/deployment)        | &nbsp;&#8226;&nbsp;[`learning-paths/`](https://dev.liferay.com/develop/learning-paths) | &nbsp;&#8226;&nbsp;[`distribute/new-articles/`](new-articles)
  &nbsp;&#8226;&nbsp;[`new-articles/`](discover/new-articles)                            | &nbsp;&#8226;&nbsp;[`new-articles/`](develop/new-articles)                             |
  &nbsp;&#8226;&nbsp;[`portal/`](https://dev.liferay.com/discover/portal)                | &nbsp;&#8226;&nbsp;[`reference/`](https://dev.liferay.com/develop/reference)           |
  &nbsp;&#8226;&nbsp;[`reference/`](https://dev.liferay.com/discover/reference)          | &nbsp;&#8226;&nbsp;[`tutorials/`](https://dev.liferay.com/develop/tutorials)           |
  &nbsp;&#8226;&nbsp;[`social-office/`](https://dev.liferay.com/discover/social-office)  |                                                                                        |

To add an article to LDN's
[Develop](https://dev.liferay.com/develop) section, for example, create your
article and its images in the `liferay-docs/develop/new-articles` folder. For
more information on where to place new articles and for guidelines on writing
them, see
[*How do I write and submit my own article?*](guidelines/faq.markdown#how-do-i-write-and-submit-my-own-article)

Converting your article to HTML locally helps you ensure you’ve correctly
formatted your Markdown text. You can convert your Markdown to HTML by using one
of the `convert.[bat|sh]` scripts found in the [`bin`](bin/) folder. See
[*How do I convert my local Markdown to HTML using the provided convert scripts?*](guidelines/faq.markdown#how-do-i-convert-my-local-markdown-to-html-using-the-provided-convert-scripts)
for details on using the script.

If you’d like to modify an existing article, you can edit it in its current
repository location. After you’ve made your changes, commit them and submit a
GitHub pull request to the default user `liferay`. To learn how to commit
changes and submit pull requests, see
[*How do I use Git and GitHub?*](guidelines/faq.markdown#how-do-i-use-git-and-github) 

You've learned how to contirbute and you know the article folder structure. Got
questions? Check out the liferay-docs [FAQ](guidelines/faq.markdown) for
answers. 

We look forward to your contributions to Liferay's official docs, here in the
liferay-docs project! 

