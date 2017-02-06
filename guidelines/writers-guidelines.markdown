# Liferay Documentation Writer's Guidelines

Now that the documentation is on Github, Liferay and the community can
collaborate on documentation. To make this collaboration successful, it is
important that all documentation be written and formatted consistently.
Otherwise, time is wasted reformatting and wordsmithing the docs for
publication--time that could be used to create new or improve existing
documentation. 

For these reasons, we ask that those contributing to the documentation adhere to
the following editorial conventions. 

## Hierarchical Design 

The official documentation has a hierarchical design. This means that the text
is divided up into small sections that have headings above them. This breaks up
the text and allows the reader to see more easily the overall organization of
the documentation. It has the added benefit of allowing the reader to skip to
the section he or she wants to read most (because we--even those of us who write
it--all know that reading documentation is not necessarily everyone's favorite
task). 

Sections should be kept as short as possible, by breaking them up further into
subsections. Markdown gives you six levels of headings (the same as HTML), which
should be sufficient for all your needs. 

## Tutorials 

Tutorials should be in the neighborhood of 1000-2000 words. If you have more
material than that, you should consider breaking it up. 

### Tutorial Intro 

A tutorial should begin with an unlabeled section that serves as your
introduction. You can use an anecdote, a story, or industry information to
provide your introduction. This introduction, however, needs to lead up to a
series of bullet points that summarize what the tutorial is about. This enables
the reader to quickly glance over the topics to see if the tutorial covers the 
information the reader is looking for. 

Don't end your intro with the bullet points themselves; always provide some
summary text after the bullet points. 

### Tutorial Body 

The body of the tutorial contains a set of multiple, hierarchical sections. See
below for information on sections. 

### Tutorial Summary 

The end of each chapter should contain a summary section that briefly recounts
what was covered in the tutorial. 

## Sections 

If you're a programmer, think of sections like linked lists. While each section
needs to cover its own material, at the end of a section you should point to
what's coming next. This is called a *segue*, and it generally sounds like: 

You can see from this how easy it is to do. Next, we'll look at this other
thing. 

Segues are discussed in more detail below. 

Never end a section with an image. 

Never end a section with code.

### Section Headings and Subheadings 

Headings and Subheadings should follow an outline format. This means that if
they're in a hierarchy, there should always be at least two children: otherwise,
you don't have a true hierarchy. 

Because of the way Markdown is often parsed, header lines should never break. If
you have set your text editor to automatically break lines, and you have a long
header, make sure your header does not break. 

### Segues

Use segues to introduce new headings and to sum up completed ones. For example: 

*...that's how you configure Liferay. Next, we'll look at what it takes to make
it sing a song. *

*Singing a Song*

You'd think that making Liferay sing would be hard, but it is in fact easy. All
you have to do....

## Text Conventions 

There are several text conventions that will help to ensure a consistent
documentation style. Please follow these conventions when entering
documentation. 

### Code in Text 

Programmers love to put stuff in quotes when they're doing documentation,
probably because it's similar to code. Code looks like this: 

	String myString = "Hello there!";

Consequently, many programmers therefore write documentation that looks like
this: 

    # Set this property to false for easier debugging for development. You can
    # also disable fast loading by setting the URL parameter "css_fast_load" to
    # "0".   

*Please do not do that for documentation.* Use a code style instead. In
Markdown, this is done by placing your code inside a left accent character,
which on US keyboards is to the left of the number 1 key, like this: 

	`my code is here`

We are not writing code; we are writing instructions for human beings to be able
to write code or use our software. Obviously, if you are documenting something
in a text file in the product itself, you can't do this, and the example above
was taken from Liferay's `portal.properties` file. 

This text was reformatted in the documentation so that it looks like this: 

Set this property to `false` for easier debugging for development. You can also
disable fast loading by setting the URL parameter `css_fast_load` to `0`. 

Always set off code so that it is clear it's code and not text. This makes it so
that people can easily copy/paste from the documentation whatever code you are
providing. Of course, make sure your code works. 

### File Names 

When you need to refer to a file name, put the file name in a code font as well. 

**Example:** You will want to use the Software Catalog portlet if you will
have multiple users submitting portlets into the repository, and if you don't
want to worry about creating the `liferay-plugin-repository.xml` file yourself. 

### Quotation Marks 

It's much better to set off properties, classes, or code blocks with a code
style instead of quotation marks. This has two benefits: the text is marked so
that people pay more attention to it, and there is no ambiguity as to whether or
not to include the quotation marks when copying and pasting these values. 

Programmers are used to writing code, not prose. Because of this, programmers
like to put periods, commas, and other punctuation outside the quotation marks,
which is incorrect punctuation--but good programming syntax. If you do need to
use quotes for something, please remember the punctuation goes *inside* the
quotes. 

### Italics 

Below are some guidelines for when to use italics. 

#### Introducing Concepts 

If you're introducing a concept, italicize the concept the first time you use
it. Thereafter, you don't need the italics. 

**Example:** One of the primary ways of extending the functionality of Liferay
Portal is by the use of *plugins*. Plugins are an umbrella term for installable
*portlet*, *theme*, *layout template*, and *web module* Java EE .war files.
Though Liferay comes bundled with a number of functional portlets, themes,
layout templates, and web modules, plugins provide a means of extending Liferay
to be able to do almost anything. 


Notice the italics on *plugins*, *portlet*, *theme*, *layout template*, and *web
module* the first time the terms are used, but none when the terms are used
after they are introduced.

#### UI Elements 

When you're telling the user to click on something in the UI, italicize it. 

**Example:** Click the *Save* button to continue. 

If you're referring to a UI element, but there's no direction for the user,
capitalize it, but *don't* italicize it. 

**Example:** After clicking the button, the Configuration page appears. 

To provide as much clarity on UI elements as possible, let's combine the above
two rules: 

**Example:** On the Configuration page, click the *Add* button. 

As you can see, the UI element that contains direction for the user is
italicized, but the UI element that has no direction is not. 

### Bold 

Bold is used sparingly in the documentation. Why is this? Because too much bold
is distracting. The reader's eye is drawn to bold text more than to anything
else. All the headings are bold anyway, so we don't want to use it very much in
the body text itself. There is one place where we want to use bold: field Lists. 

When explaining a form that users can fill out, use bold for the field names. 

**Example:**

**Name:** Enter the user's name. 

**Address:** Enter the user's address. 

Never end a section with a list of form elements, like I was about to do before
I typed this sentence. 

### Bullet Points 

Bullet points are strange animals. They can be lists of things. They can be
lists of arguments. Here's a good rule for bullet points: if it's a sentence,
use a period. If it's not a sentence, don't. 

**Examples:** Don't use a period for bullets that are not sentences, like: 

*  Shoes
*  Pants
*  Shirt

Use a period for bullets that are sentences, like: 

*  Roll your mouse over the Dock and click *Sign In*. 
*  Enter your email address and password.

Never end a section with bullet points, like I was about to do before I typed
this sentence.

### Trailing Spaces 

The Markdown source files for the documentation should be clean. Every sentence,
regardless of whether it ends a paragraph or not, should end with one--and only
one--trailing space. This is so that paragraphs can be combined more easily
during the editing process, when this becomes necessary. 

There should never be more than one trailing space after a sentence, paragraph,
or bullet point. 

### Spaces vs. Tabs 

Unlike the Liferay source code, Liferay documentation should have no tabs in it.
This is so that you can more easily differentiate various constructs within
Markdown. For example, preformatted text is denoted in Markdown by either one
tab or four spaces. List levels are denoted by indenting by spaces. If you use
spaces instead of tabs, you have three list levels to work with before your
syntax-highlighting text editor mistakes your outline levels for preformatted
text. 

## Images and Captions 

Every image should have a caption, and the caption should be at least one
descriptive, complete sentence. In other words, captions that are simple labels
aren't enough. DON'T do something like this for a caption: 

*The Liferay setup wizard.*

Instead, do this: 

*The Liferay setup wizard makes connecting to your database and configuring a
default administrator easy.*

Picture yourself taking the reader by the hand and leading him or her through
the concept you're describing. All of these tips are designed to help you do
that.

Images should be in a web-friendly format (.png or .jpg). If you've created a
drawing in a vector format (.svg or other), save it in the `drawings` folder for
the guide you're working on and save a converted .png or .jpg in the `images`
folder. 

Image file names should all be lower case, with no spaces in the file names. Use
dashes (-) to replace spaces in multi-word file names. 

## Capitalization 

Platform: Always platform

Configuration: Always Configuration

Site: Always Site

Site Administration: Always Site Administration

Personal Site: Always Personal Site

Role: Always Role

User: Always User

User Group: Always User Group

Organization: Always Organization

Control Menu: Always Control Menu

Navigation: Always Navigation

Account Settings: Always Account Settings

## Common Documentation and Phraseology Issues 

Explain every option, no matter how redundant and stupid it seems. Yes, most
forms in the product ask for a name and a description, but you need to explain
them anyway. 

Never, ever, ever say something is "self-explanatory." It isn't. All you're
doing is insulting the reader who doesn't understand the same things you
understand.

Remove the future tense from your documenting vocabulary. When you click on
something, something else *appears*. You don't want to say it will appear, and
you especially don't want to say you *are going to* do something else after it
appears. 

## Know your audience! 

You have at least two types of people to whom you are writing: the person who is
reading the book cover to cover (rare), and the person who is flipping or
clicking through trying to find a specific section that tells him or her
everything necessary (more common). 

It's better to write for this second type of reader. Many features in the
product overlap (message board threads can be tagged; wiki articles can be
tagged; blogs can be tagged, etc.), but we can't assume readers have read
everything. For this reason, don't say things like "Tagging wiki articles works
the same way it does for web content," and leave it there. If you say that, use
it only by way of introduction to how you'll tell readers exactly how to tag
wiki articles. You can then point readers to the specific place where tagging in
general is explained. 

With that in mind, another thing you want to avoid is *mind reading*. It's easy
to fall into making statements like: "You probably aren't interested in blah
blah now, but you will be once you finish this section." As the writer, you
actually have no idea what your readers might be thinking, so avoid mind reading
at all costs. 

The Golden Rule says "Do unto others as you would have them do unto you." That's
a great way to approach documentation. Write something you'd want to read.
Include all the details you would want if you were newly approaching the topic. 

## Be Creative! 

Use jokes. Add anecdotes. Write stories. Do whatever you can to keep the text
from being regular, old, dry, boring documentation. One of our authors (Stephen
Kostas) came up with the Nosester Social Network. That's awesome. I (Rich Sezov)
wrote a story as the intro and summary of the Audit section. Do this sort of
thing. It makes the text more interesting to read, and that causes people to
actually read the documentation. Let's make sure Liferay's docs don't sound like
everybody else's. Let's be recognized as much for our cool documentation as for
our cool product.

## Common Grammatical Mistakes 

We all make grammatical errors sometimes. I'm sure that even J.R.R. Tolkien had
to correct some grammatical errors before publishing *Lord of the Rings*. What
separates the men from the boys (or women from the girls) here, though, is
whether or not you are willing to look at your text a second, third, or fourth
time and find those grammatical errors to which you are prone (See? I didn't end
that sentence with a preposition--even though that's not always wrong). You can
then try to be more aware of those errors as you write and thereby avoid them.
It also helps to read your text after you have written it. This sounds like
common sense, but you'd be surprised at how many people just write something and
send it off like it's email. To get good prose, you have to go over it several
times. 

What follows is a list of common grammatical mistakes.

### It's and Its 

This one is easier than it looks, even though most people give up and always use
*it's*. Why they choose the one with the apostrophe is beyond me, since you have
to type an extra character. 

**It's:** This is short for "it is." So when you're about to write an *its* or
an *it's*, ask yourself: do I mean "it is" here? If so, use the single quote. If
not, see below. 

**Its**: This is always possessive. *The cat blinked its eyes.* 

### Subject Agreement 

Your subjects' references must always agree in a sentence. You can't have one be
singular and another be plural. For example, you never say, "If you configure
this permission for the user, they cannot access the resource." You have to say,
"If you configure this permission for the user, he or she cannot access the
resource." Since you're talking about a single user, it's either *he* or *she*
(or both), but not *they*. Some people, in order to try to be fair to the
genders, alternate genders as they go ("If you configure this permission for the
user, she cannot access the resource.").  That's also correct. Others try to
make the sentence plural ("If you configure this permission for users, they
cannot access the resource."). Again, this is correct. 

### Notes on Commas 

Commas have several elements which can be confusing. Here are some tips to help
you be successful with commas. 

#### Conjunctions vs. Compound Verbs 

You only use a comma after a conjunction (i.e., "and," "or," "but," etc.) if it
is separating two independent clauses. If you have a compound verb, you don't
need a comma. So you'd use a comma with "Click on the button, and the portal
will refresh." But you wouldn't use a comma with "Select option three and click
save." In the first instance, you have two independent clauses; in the second,
you have a compound verb ("select and click"). One easy way to tell which one
you're dealing with is to find the subject(s). If you have two different
subjects, you have two independent clauses. If you have one subject sharing two
verbs, you have a compound verb. 


So in our first example, our two subjects were *you* and *portal* (the you is
understood as (You) click on the button...). So we need a comma there, because
we're separating two independent clauses. 

In our second example, we had one subject: *you*. The subject of the sentence is
doing two things: selecting and clicking. We therefore have a compound verb and
don't need a comma. 

#### Comma Splices, Semicolons, and Em Dashes 

Most people I've talked to have no idea what to do with a semicolon (;). Java
programmers, of course, know what to do with it: you end a statement with it!
(That's a poor attempt at humor.) Seriously, though, many people wind up using a
comma in a place where you should use a semicolon, and that is when you're
separating two independent clauses (i.e., sentences). Consider the following
example: 

*Don't try doing it all yourself, it's better to work as a team.* 

That's a comma splice. You're splicing together two complete sentences with a
comma: 

*Don't try doing it all yourself. It's better to work as a team.* 

While a comma splice is incorrect punctuation, using a semicolon in the same
place is a *correct* drop-in replacement for a comma splice, as a semicolon's
purpose in life is to separate two independent clauses: 

*Don't try doing it all yourself; it's better to work as a team.*

If you write it this way, your punctuation is correct, and you have just become
one of those few who know how to use a semicolon! 

It's also important to note that the em-dash (--) can also be used in this way: 

*Don't try doing it all yourself--it's better to work as a team.*

Em-dashes have no spaces around them and always consist of *two* dash
characters. Our Markdown parser will convert that into a proper em-dash
character, but it won't convert any of the other variants. 

## Terminology

Below is terminology and how we use it. 

**Folder:** Always use folder. Never use directory, directory structure, etc. 

**Command line:** Never use Terminal or Command Prompt. We're trying to stay
OS-agnostic here, people. 

**Script:** Always use script; never batch file, batch, etc. And always default
to the Unix/Mac/Linux way of doing things (`./catalina.sh`, not `catalina` or
`catalina.bat`) because those environments are used more often by Liferay
developers and administrators. 

## Summary

These guidelines help us to ensure consistency and clarity within the
documentation. All contributors should be familiar with them and do their best
to adhere to them before sending a pull request to have their text included in
our documentation. They are evolving over time, so you may want to review them
for changes if you haven't looked at them in a while. 

