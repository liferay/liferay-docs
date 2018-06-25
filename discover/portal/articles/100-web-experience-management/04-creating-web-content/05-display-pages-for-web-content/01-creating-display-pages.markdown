# Creating Display Pages [](id=creating-display-pages)

Display Pages are created initially in much the same way as Content Pages. You 
select any number of page fragments and add them to the page to create your 
page. Where Display pages are different is that after you add the fragments, 
you can then map editable fields in those fragments to the fields of an web 
content article. You can learn more about creating Page Fragments in the [Creating Page Fragments tutorial](/discover/portal/-/knowledge_base/7-1/creating-page-fragments).

Looking at the example of a template for a long form article, we can 
see how Display Pages utilize Page Fragments. The article can have an image, a 
title (simple style text), a main body (rich text), a  highlighted quote 
(simple styled text), and then a standard footer. So your first step in 
creating the Display Page would be to create a Page Fragment which
has all of those fields formatted the way you want them. Your fragment could 
have these fields:

-  Editable header

-  Editable Image

-  Editable rich text
    
-  Editable plain text (with block-quote styling)
    
-  Non-editable footer

There could be additional fields in the fragment, like a site-wide header which 
would be static content that appeared for every article, or an embedded widget 
which displayed content, but these are the basics that we're most concerned 
about.

To go along with this fragment, you could have a Web Content Structure with 
these fields:

-  Title (Text box)

-  Image (Documents and Media image)

-  Content (Web Content)

-  Quote (Text area)

![Figure 1: Connecting structure fields to fragment data.](../../../../images/structure-to-fragment.png)

The Display Page maps the fields from the Web Content Structure 
to the fragment. So when the Display Page is assigned for an article with that
Structure, it is displayed on a display page with the formatting from the 
fragment. Next you'll see an example of this in action.


