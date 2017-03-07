# LIFERAY-DOCS

[![Join the chat at https://gitter.im/liferay/liferay-docs](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/liferay/liferay-docs?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

<a href="https://dev.liferay.com" >
<img src="guidelines/images/liferayDeveloperNetworkSmallerEdit7.jpg" alt="Liferay Docs Icon">
</a>

[![Join the chat at https://gitter.im/rotty3000/blade](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/liferay/liferay-docs?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Welcome to Liferay's official documentation project, the home of
[Liferay Developer Network](https://dev.liferay.com) articles. All articles are
written in
<a href="http://fletcher.github.com/peg-multimarkdown/mmd-manual.pdf" target="_blank">
Markdown</a>, making them easy to write and easy to read. Approved articles are
uploaded to the Liferay Developer Network (LDN) and converted automatically to
HTML. In this project, you can contribute new articles, improve existing
articles, or fix documentation bugs. To produce documentation that is
comprehensive and stylistically consistent, the liferay-docs project provides
<a href="guidelines/writers-guidelines.markdown" target="_blank">writing guidelines</a>,
<a href="guidelines/liferay-documentation-tools.markdown" target="_blank">Markdown & tools guide</a>,
and a
<a href="develop/tutorial-template.markdown" target="_blank">tutorial template</a>.

You'll learn how quickly to add a new article and its images next. 

## Quick Steps to Add a New Article

You can follow these steps to create a new article and contribute it from 
GitHub. 

1.  Sign in to GitHub. If you don't already have a GitHub account, you must
    [join](https://github.com/join) GitHub in order to contribute to
    liferay-docs. 

2.  Click on a `new-articles` link folder below. These match the LDN sections
    where you can add a articles:

    - <a href="develop/new-articles" target="_blank">`develop/new-articles`</a>

    - <a href="discover/new-articles" target="_blank">`discover/new-articles`</a>

    - <a href="distribute/new-articles" target="_blank">`distribute/new-articles`</a>

3.  Select the liferay-docs branch that matches the Liferay Portal version
    you're writing about: 

    <table border="1">
    <tr>
    <td><b>&nbsp;Branch&nbsp;</b></td>
    <td><b>&nbsp;Portal Version&nbsp;</b></td>
    </tr>
    <tr>
    <td>&nbsp;master&nbsp;</td>
    <td>&nbsp;7.0</td>
    </tr>
    <tr>
    <td>&nbsp;6.2.x</td>
    <td>&nbsp;6.2</td>
    </tr>
    </table>

4.  Click the **plus** sign after **new-articles/:**
    ![new articles folder](guidelines/images/new-articles-folder.png)

5.  Write your article and click the
    ![Propose new file](guidelines/images/propose-new-file.png) button, to
    prepare a [pull request](https://help.github.com/articles/using-pull-requests/). 

6.  Send the pull request to the default user `liferay`. 

7.  Drag your article's images into the pull request's comments to associate
    the images with your article. 

Your new article is submitted! Liferay's Knowledge Management team will review
your contribution. Approved changes are merged into the liferay-docs repo and
published to the [Liferay Developer Network](https://dev.liferay.com). 

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

 ![User & Admin](guidelines/images/discover.png)                                                                                           |
 :------------------------------------------------------------------------------------------------------------------------------------ |
  &nbsp;[**liferay-docs/discover/**](discover)                                                                                         |
  &nbsp;&#8226;&nbsp;[`portal/`](discover/portal) &rarr; [Portal](https://dev.liferay.com/discover/portal)                             |
  &nbsp;&#8226;&nbsp;[`social-office/`](https://github.com/liferay/liferay-docs/tree/6.2.x/discover/social-office) &rarr; [Social Office](https://dev.liferay.com/discover/social-office) |
  &nbsp;&#8226;&nbsp;[`deployment/`](discover/deployment) &rarr; [Deployment](https://dev.liferay.com/discover/deployment)             |
  &nbsp;&#8226;&nbsp;[`reference/`](discover/reference) &rarr; [Reference](https://dev.liferay.com/discover/reference)                 |
  &nbsp;&#8226;&nbsp;[`new-articles/`](discover/new-articles)  (You can submit new articles here for LDN's *User & Admin* section)         |

  ![Developer](guidelines/images/develop.png) |
 :-------------------------------------------------------------------------------------------------------------------------------------- |
  &nbsp;[**liferay-docs/develop/**](develop)                                                                                             |
  &nbsp;&#8226;&nbsp;[`learning-paths/`](develop/learning-paths) &rarr; [Learning Paths](https://dev.liferay.com/develop/learning-paths) |
  &nbsp;&#8226;&nbsp;[`tutorials/`](develop/tutorials) &rarr; [Tutorials](https://dev.liferay.com/develop/tutorials)                     |
  &nbsp;&#8226;&nbsp;[`reference/`](develop/reference) &rarr; [Reference](https://dev.liferay.com/develop/reference)                     |
  &nbsp;&#8226;&nbsp;[`new-articles/`](develop/new-articles)  (You can submit new articles here for LDN's *Developer* section)             |

  ![Marketplace](guidelines/images/distribute.png)                                                                                             |
 :------------------------------------------------------------------------------------------------------------------------------------------- |
  &nbsp;[**liferay-docs/distribute/**](distribute)                                                                                            |
  &nbsp;&#8226;&nbsp;[`distribute/new-articles/`](distribute/new-articles)  (You can submit new articles here for LDN's *Marketplace* section) |

Now that you're familiar with the layout of the liferay-docs repository, you
may be interested in adding and editing LDN articles in a local copy of the
liferay-docs repository.

## Working Locally

If you're new to the liferay-docs repository and you want to add/edit material
locally, you first must [clone](https://help.github.com/articles/fork-a-repo/)
the project. For information on how to work with Git projects, see
[*How do I use Git and GitHub?*](guidelines/faq.markdown#how-do-i-use-git-and-github)

**To add an article** to LDN's [Develop](https://dev.liferay.com/develop)
section (as an example), create your article and its images in the
`liferay-docs/develop/new-articles` folder. Commit your added files and send a
[pull request](https://help.github.com/articles/using-pull-requests/) to default
user `liferay`. For more information on where to place new articles and for
guidelines on writing them, see
[*How do I write and submit my own article?*](guidelines/faq.markdown#how-do-i-write-and-submit-my-own-article)

**To modify an article**, you can edit it locally in its repository location, in
a branch created for that purpose. After you've made your changes, commit them
and submit a [pull request](https://help.github.com/articles/using-pull-requests/) to the
default user `liferay`. 

**To preview an article**, invoke one of the `convert.[bat|sh]` scripts
on your Markdown file. The scripts are in the [`bin`](bin/) folder. See
[*How do I convert my local Markdown to HTML using the provided convert scripts?*](guidelines/faq.markdown#how-do-i-convert-my-local-markdown-to-html-using-the-provided-convert-scripts)
for details on using the script.

You've learned how to contribute and you know the article folder structure. Got
questions? Check out the liferay-docs [FAQ](guidelines/faq.markdown) for
answers. 

Thanks for helping us out with Liferay documentation! 

