# Styling the Content Div

Like the Wrapper, the Content `<div>` is used to position and set the dimensions 
for the theme's content. Currently the Content `<div>` is pressed right up 
agianst the edge of the Wrapper, giving the theme a claustrophobic feel. It's 
time to give the content a little breathing room.

1. Add the following code to `custom.css` below the *Content* placeholder:

        #content{
        background-color:$bkColor;
        padding: 0px 20px;
        
        /* ---------- Carousel ---------- */
        .carousel {
        margin-top:-10px;
        z-index:0;
        }
        
        /*------Activities------*/
            #imgWrapper{
            min-width:100px; 
            max-width:800px; 
            margin-left:auto; 
            margin-right:auto; 
            padding-top:15px;
        
                .img{
                max-height: 30%;
                max-width: 30%;
                @include border-radius(90px);
                    &:hover{
                    opacity:0.5;
                    }
                }
            }

        /*------Out of This World------*/
        #columnWrapper{
        overflow: visible; 
        max-width:812px; 
        height:200px;
        margin-left: auto;
        margin-right: auto;
        padding-top: 25px;
        padding-bottom: 25px;
        
            #columnLeft{
            float: left; 
            font-size: 36px; 
            border-right: 1px solid black; 
            width: 30%; 
            max-height: 700px; 
            min-width:100px; 
            line-height: 60px; 
            text-align: center
            }
                
            #columnRight{
            float: right; 
            font-size:16px; 
            width:67%;
            max-height: 700px; 
            line-height:30px
            }
        }
        
        #reserveBtnWrapper{
        margin-left: auto;
        margin-right: auto;
        width: 300px;
        padding-bottom: 25px;
        }

        #reserveBtn{
        background-color: #00C4FB;
        color: white;
        margin-left: 32px;
        font-size: 1.5em;
        height: 62px;
        border-radius: 10px;
        border: none;
        padding:10px 0px;
    
        &:hover{
        background-color: #df5946;
        }

        a{
        text-decoration:none;
        color:white;
        padding: 9px 10px;
        border-radius:10px;
        }
        }

        /*--Portlet styles---*/
        .portlet{
        
           .portlet-title{
        
               img{
               display:none;
               }
           }
        
        }

        }
   
    This adds some padding between the edge of the Wrapper and the Content 
    `<div>`. It also takes care of the styles for a couple web content articles 
    on the *Welcome* page and removes the portlet icons from the bordered 
    portlets.
    
    The carousel has been given some styling as well. Although, at the moment it 
    does not reflect this. You will have to turn off the portlet borders for the 
    carousel in order for the styles to take effect.
   
2. Save the file and navigate to the *Welcome* page.

3. Click on the gear *Options* menu for the carousel's Web Content Display and 
   select *look and Feel*.
   
4. Set the *Show Borders* option to *No* and Save.

5. Close the dialog window and refresh the page.

    The carousel has been centered within the Wrapper and pushed up towards the 
    *Breadcrumbs* to bring it into focus; this ties the content to the header.
    Later in the learning path, you'll add a theme setting to remove the
    breadcrumbs from the *Welcome* page, which will place the carousel right
    against the navigation bar. 
   
6. Save the file and refresh the browser.

Your theme should look similar to the figure below:
    
![Figure 1: The content is clearer to read now that the there is some padding.](../../images/css-content.png)
    
Three down and one to go! You can move on to the Footer's styling next.