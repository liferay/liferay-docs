# Setting Up custom.css 

Before you add the CSS for your theme, you'll need to complete some preliminary
steps.

1. Open `custom.css` in your `_diffs/css/` directory.

    You'll add the CSS piece by piece, just as you saw in the HTML picture
    in the last section of the Learning Path. You can refer to this diagram 
    again for reference:
    
    ![Figure 1: The HTML is broken up into sections.](../../images/html-diagram.png)

    It's a good practice to add a roadmap to your CSS file before you start, to
    keep things organized as you go along.
    
2. Replace the contents of `custom.css` with the following code:

        /* This file allows you to override default styles in one central location for easier upgrade and maintenance. */
        @import "compass";
        @import "mixins";

        html {
            height: 100%;

            body {
                @include background-image(linear-gradient(to bottom, 
                rgba(0,196,255,1), rgba(0, 196, 255, 0.3), 
                rgba(238, 231, 0, 0)));

                background-repeat: no-repeat;
                height: 100%;
                
                /*-------------------Admin Menus------------------------*/
                .nav-account-controls{
                
                        .user-avatar .dropdown-menu, .admin-links .dropdown-menu, 
                        .dockbar-user-notifications .dropdown-menu, 
                        .my-sites .dropdown-menu, .sign-in a{
                            background-color: $themeMainColor;  
                        }
                        
                        .user-avatar .dropdown-toggle, .admin-links 
                        .dropdown-toggle, .dockbar-user-notifications 
                        .dropdown-toggle, .my-sites .dropdown-toggle, 
                        .sign-in a{
                            background-color: $themeMainColor;
                            &:hover{
                                background-color: #00C4FB;
                            }
                        }
                }
                
                /*---------Add, Edit, and Preview Controls---------*/
                .nav-add-controls{
                        #_145_addPanel, #_145_previewPanel, 
                        #_145_editLayoutPanel {
                                background-color: $themeMainColor;
                                &:hover {
                                        background-color: #00C4FB;
                                }
                        }
                        
                        .page-edit-controls, .site-add-controls, 
                        .page-preview-controls, .toggle-controls{
                                border-radius:6px;
                        }
                }

                /*--- Return Body styling for popup menus back to normal --- */

                &.portal-popup {
                    background-image:none;
                }
            }

        /* ---------- Wrapper ---------- */

        /* ---------- Banner ---------- */

        /* ---------- Content ---------- */

        /* ---------- Footer ---------- */
        }
   
    Liferay supports SASS(Syntactically Awesome Style Sheets). SASS allows 
    you to nest similar styles together, keeping your code concise and well 
    organized. It also allows you to use variable declarations in your CSS, 
    making it easier to maintain. 
    
    You can read more about SASS [here](http://sass-lang.com/guide).
    
    In addition to the map, some initial styling has been added for the 
    background of the site, edit controls, dockbar, as well as some imports. 
    Along with the *compass* and *mixins* imports, `_aui_variables.scss` has 
    been imported so that you can use the variables in `custom.css` later on.
    
    The styling that has been added to the body of the site adds a gradient to 
    the background and preserves the default styling for pop-up windows.
    
    With the ground work layed, you can start with the Wrapper's styling next.