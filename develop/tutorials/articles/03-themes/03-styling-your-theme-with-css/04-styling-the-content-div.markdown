# Styling the Content Div [](id=styling-the-content-div)

The Content `<div>` is used to position and set the dimensions for the theme's 
content. Currently the Content `<div>` is pressed right up against the edge of 
the Wrapper, giving the theme a claustrophobic feel. It's time to give the 
content a little breathing room.

1. Add the following code to `custom.css` below the *Content* placeholder:

        #content {
            background-color: $bkColor;
            padding: 0px 20px;
        
            /* ---------- Carousel ---------- */
            .carousel {
                z-index: 0;
            }
        
            /*------Activities------*/
            .img:hover {
                opacity: 0.5;
            }

            /*------Out of This World------*/
            #columnLeft { 
                font-size: 36px; 
                line-height: 60px;   
                text-align: center;
                color: $themeMainColor;
            }
                
            #columnRight {
                font-size: 16px;
                line-height: 30px;            
                color: #696969;
            }

            /*------Reserve Button------*/
            #reserveBtnWrapper {
                margin-left: auto;
                margin-right: auto;
                padding-bottom: 25px;
                width: 300px;

                #reserveBtn {
                    background-color: #00C4FB;
                    border-radius: 10px;                    
                    color: #FFF;
                    font-size: 1.5em;
                    height: 62px;
                    margin: 30px;
                    padding: 10px 0;

                        &:hover {
                            background-color: $themeMainColor;
                        }

                        a {
                            border-radius: 10px;                        
                            color: #FFF;
                            padding: 9px 10px;
                            text-decoration: none;
                        }
                }
            }

            /*--Portlet styles---*/
            .portlet {
                .portlet-title {
                    img {
                        display: none;
                    }
                }
            }
        }

    This adds some padding between the edge of the Wrapper and the Content 
    `<div>`s. It also creates styles for some web content articles on the
    *Welcome* page and removes the portlet icons from the bordered portlets.

    The carousel has been given some styling as well. It's been centered and
    some spacing has been added between the breadcrumbs and the carousel. The 
    carousel has also been given a `z-index` of `0`, so the navigation shows 
    above it. Save the file and navigate to the *Welcome* page.

6.  Save the file and refresh the browser. Sometimes, you may need to redeploy
    the theme. 

Your theme should look similar to the figure below:

![Figure 1: The content is clearer to read now that the there is some padding.](../../images/css-content.png)

Three down and one to go! You can move on to the Footer's styling next.
