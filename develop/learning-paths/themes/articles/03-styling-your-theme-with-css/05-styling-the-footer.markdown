# Styling the Footer

As you create the theme you want to keep the whole picture in view. The Footer 
can be seen as the bow that nicely wraps the finished package. With that in 
mind, it's good to find a way to unify the theme with the Footer's design; in
this case matching its look to the navigation bar.

1. Add the following code to `custom.css` below the *Footer* placeholder, 
   and save the file:

        footer{
            background-color: $themeMainColor;
            padding: 35px;
                .powered-by {
                    color: #FFF;
                    float: right;
                    margin-top: 0;
                }
        }
        
The Footer is now styled to match the navigation bar and padded to give its 
content more room. The 'powered-by' link has also been given a text color to 
match the navigation links. The Footer's content is sparse at the moment, but 
this will change later in the Learning Path.

Up to this point you're styling has been focused on the macro picture, dealing 
with the larger elements of the page. Next you'll explore the micro picture a
bit, and focus your styling on an individual portlet.