# Rating Assets

Liferay's asset framework supports a rating system that lets your users rate 
content in plugins. You've probably seen this in many of Liferay's built-in 
plugins. A great example is the Blogs portlet. When users click on a blog, a row 
of stars appears at the bottom that lets them rate the blog from one to five 
stars. Letting users rate content is a fantastic way not just to figure out what 
content is popular, but also to foster a sense of community and collaboration 
among content producers and consumers. Furthermore, implementing ratings in your 
plugins is a snap. Read on to find out how!

## Creating a JSP for Rating Entities

The first step to enabling ratings in your plugins is to create a separate JSP 
that your users access to rate content. You might be wondering why you need a 
separate JSP for this. The truth is, you don't. You could just put the ratings 
in the edit JSP. However, separating collaborative activities like ratings from 
editing or creating content makes sense--it cuts down on confusion between the 
two activities. You also probably don't want users to be able to edit content 
that belongs to someone else.
