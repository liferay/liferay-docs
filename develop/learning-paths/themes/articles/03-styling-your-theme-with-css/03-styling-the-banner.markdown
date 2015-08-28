# Styling the Banner

The Banner holds all the elements in the theme's header. This section is very
important because it is the first thing the user sees when they visit your site.

1. Add the following code to `custom.css` below the *Banner* placeholder:

        #banner {
            background-color: $bkColor;

            #heading {
                padding: 0 4%;

                .site-title {
                    .logo {
                        padding-bottom: 16px;
                    }
                }

                .page-title {
                    color: $themeMainColor;
                    font-style: oblique;
                    text-transform: uppercase;
                }
            }

            /* ---------- Main Navigation ---------- */

            #navigation {
                background-color: $themeMainColor;
                display: block;
                position: relative;
                z-index: 1;

                .active{
                    background-color: #00C4FF;
                        > a {
                            color: #FFF;
                        }
                }        
                
                .dropdown-menu li {
                    background: #00ACFF;
                    border-width: 0;
                    margin: 0;
                    position: relative;

                    &:after, &:before {
                        border-width: 0;
                    }

                    a {
                        color: #FFF;

                        &:hover {
                            background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
                        }
                    }
                }

                li {
                    margin: 0 4px;
                    padding: 15px 10px;
                    position: absolute;

                    &:hover {
                        background-color: #00C4FF;

                        .child-menu {
                            display: block;
                            margin-top: 0;
                        }

                        a {
                            color: #FFF;
                            text-decoration: none;
                        }
                    }
                }

                ul {
                    li {
                        &:hover {
                        background-color:#00C4FF;
                            .child-menu {
                                display: block;
                                padding: 0;
                                li {
                                    background-color: $themeMainColor;
                                    &:hover {
                                        background-color: #00C4FB;
                                    }
                                }
                                a {
                                    color: #FFF;
                                    margin: -10px;
                                    text-decoration: none;
                                }
                            }
                        }
                    }
                }

                > ul > li {
                    display: inline-block;
                    font-size: 1.2em;
                    position: relative;

                    a {
                        color: #FFF;
                        padding: 15px 10px;
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