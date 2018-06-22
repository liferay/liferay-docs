# Importing Knowledge Base Articles [](id=importing-knowledge-base-articles)

As mentioned earlier, the Knowledge Base app can import articles in bulk. This 
lets you have an offline process where articles are prepared ahead of time 
before they're published. Articles are imported into the Knowledge Base as 
[Markdown](http://commonmark.org) 
files. Markdown is a text-only file format that is easy to read, yet supports 
all the things you'd need to do to format your articles. 

+$$$

**Note:** To import articles, your Role must be granted the *Knowledge Base* 
&rarr; *Resource Permissions: Import Articles* permission.

$$$

The Knowledge Base supports a Markdown dialect known as 
[Multi-Markdown](http://fletcher.github.io/MultiMarkdown-4/). 
This dialect extends the original Markdown with features like table formatting, 
image captions, and footnotes. 

For the Knowledge Base to import your Markdown articles, they must adhere to
these requirements: 

-   All source files must use the `.markdown` or `.md` extensions.
-   Articles must start with a top-level header (e.g., `# Some Heading ...`). 
-   Each header must have an associated, unique ID for the article's friendly 
    URL title and for anchor tags in the article's sub headers. Here's an 
    example of a top-level header that correctly specifies an ID: 

  `# Some Heading  [](id=some-heading)`

Here's Markdown source text for a simple example article: 

    # The Moons of Mars [](id=the-moons-of-mars)

    As you look up from your chaise lounge, you're sure to see our neighboring
    planet Mars. Did you know that Mars has two moons? You might have to break 
    out a pair of binoculars to see them.

    Its two moons are aptly named after the two sons of mythical Roman god Mars.
    Their names are Phobos and Deimos. 

In the first line above, notice the header's ID assignment
`id=the-moons-of-mars`. On import, the ID value becomes the Knowledge Base
article's URL title. 

Markdown is something of a standard: there's 
[Github Flavored Markdown](https://help.github.com/articles/github-flavored-markdown), 
a proposed 
[common Markdown syntax](http://www.commonmark.org), 
forums that support Markdown (reddit, StackExchange, and others), Markdown
editors, and an 
[IETF draft](https://tools.ietf.org/html/rfc7763) 
for making it an official Internet media type (text/markdown). Why is there so 
much interest in Markdown? 

1.  It's readable. Even if you don't know Markdown, you can read it without
    having to filter out the syntax. 

2.  It gets out of a writer's way. You don't have to worry about mousing to
    various icons to change text into a heading or create bulleted lists. Just 
    start typing. The syntax is very intuitive. 

3.  There are tools to convert it to many other formats, though it was designed 
    to convert to HTML. If your articles are in Markdown, it's straightforward 
    to publish them to the web, mobile formats (Kindle, ePub), and print. 

4.  Since it's only text, you can use existing tools to collaborate on that
    text. Using services like GitHub, people can contribute to your articles, 
    and you can see all the changes that have been made to them. 
