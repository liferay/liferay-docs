# Styling the Banner

The Banner holds all the elements in the theme's header. This section is very 
important because it is the first thing the user sees when they visit your site.

1. Add the following code to `custom.css` below the *Banner* placeholder:
   
        #banner{
        background-color:$bkColor;

            #heading{
            padding: 0 4%;
	 
                .site-title{ 
                    .logo{
                    padding-bottom: 16px;
                    }
                }
                .page-title{
                color:$themeMainColor;
                font-style:oblique;
                text-transform: uppercase;
                }
	
            }

        /* ---------- Main Navigation ---------- */
            #navigation {
            display: block;
            position: relative;
            z-index: 1;
            background-color:$themeMainColor;
	
                .dropdown-menu li{
                background: #00ACFF;
                border-width: 0;
                margin: 0;
                position:relative;

                    &:after, &:before {
                    border-width: 0;
                    }

                    a {
                    color: #FFF;

                        &:hover {
                        background: none;
                        text-decoration: underline;
                        }
                    }
                }
	
	
            li{
            padding: 15px 10px;
            position: absolute;
            margin: 0px 4px;

                &:hover{
                background-color:#00c4ff;
                    .child-menu{
                    display:block;
                    margin-top:0;
                    }
                    a{
                    color:white;
                    text-decoration:none;
                    }
                }
            }
	
            ul{
                li{
                    &:hover{
                        .child-menu{
                        display:block;
                            a{
                            color:white;
                            margin:-10px;
                            text-decoration:none;
                            }
                        }
                    }
                }
            }
	
            > ul > li{
            display:inline-block;
            position:relative;
            font-size:1.2em;
                a{
                color:white;
                padding:15px 10px;
                    .child-menu{
                    }
                }
            }
        }
        }

The navigation has been formatted in a list and spaced further down from the 
logo. It has also been placed in a red bar to match the color of the logo. To 
further relate the navigation's color to the rest of the theme, the navigation 
items have been given a blue background when active and on hover. Your theme 
should now look like the following figure:
    
![Figure 1: The updated navigation bar brings the banner together and completes the header.](../../images/css-banner.png)

The Banner is now styled! You can move on to the Content `<div>` styling next.