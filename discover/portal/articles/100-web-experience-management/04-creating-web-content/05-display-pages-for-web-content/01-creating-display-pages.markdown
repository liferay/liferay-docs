## Creating Display Pages

Display Pages are created initially in much the same way as Content Pages. You 
select any number of page fragments and add them to the page to create your 
page. Where Display pages are different is that after you add the fragments, 
you can then map editable fields in those fragments to the fields of an asset. 
You can learn more about creating Page Fragments in the [Creating Page Fragments tutorial](/discover/portal/-/knowledge_base/7-1/creating-page-fragments).

Looking at our previous example---a template for a long form article---we can 
see how Display Pages utilize Page Fragments. The article above has an image, a 
title (simple style text), a main body (rich text), another image, a 
highlighted quote (simple styled text), and then a standard footer. So your 
first step in creating the Display Page would be to create a Page Fragment which
has all of those fields formatted the way you want them. Your fragment could 
have these fields:

-  Editable header

-  Editable Image 1

-  Editable rich text

    -  Editable image 2 (styled inside of the main text area)
    
    -  Editable plain text (with block-quote styling)
    
-  Non-editable footer

There could be additional fields in the fragment, like a site-wide header which 
would be static content that appeared for every article, or an embedded widget 
which displayed content, but these are the basics that we're most concerned 
about.

To go along with this fragment, you could have a Web Content Structure with 
these fields:

-  Title (Text box)

-  Image 1 (Documents and Media image)

-  Content (Web Content)

-  Image 2 (Documents and Media image)

-  Quote (Text area)

![Figure X: Connecting structure fields to fragment data.](../../../../images/structure-to-fragment.png)

Now you can use a Display Page to map the fields from the Web Content Structure 
to match the fragment.


