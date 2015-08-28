# Responding to Tablet and Phone Views

With the theme elements identfied, you can now add the CSS to make them
responsive for phones and tablets.

1. Open the `custom.css` and add the following code to the bottom of the file:

        /*-------responsive-------*/
        @include respond-to(phone, tablet) {
            html #wrapper #banner #navigation {
                display:none;
                position:relative;
            }
            .aui {
                * {
                    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
                }

                body{
                    background-image:none;
                }

                .carousel{
                    display:none;
                }
                #wrapper {
                    margin: 0;
                    padding:0;
                    position:relative;

                    #banner {
                        position:relative;
                        padding-bottom:0;
                        padding-top:0;
                        #heading {
                            bottom: 100%;
                            left:-19px;
                            margin-top: 0;
                            position: absolute;
                            top:-70px;

                            .site-title {
                                font-size: 26px;

                                img {
                                    height:60px;
                                    width: auto;
                                }

                                .logo{
                                    height:35px;
                                    width:auto;
                                    top:0;
                                }
                            }
                            .page-title{
                                padding-top:20px;
                            }
                        }
                        #navigation {
                            display: none;
                            position:relative;
                            &.open {
                                display: block;
                                margin-top: 0px;

                                .navbar-inner {
                                    @include border-radius(3px);
                                    padding: 10px;
                                    .nav-collapse {
                                        height: auto;
                                        overflow: visible;
                                    }
                                }
                                li{
                                    width:100%;
                                    padding: 0px 0px;
                                    margin: 0px;
                                    text-align:center;

                                    .dropdown-menu{
                                        position:relative;
                                    }
                                }

                                ul{
                                    margin:0px;
                                    >li{
                                        display:inline-block;
                                    }
                                    li{
                                        a{
                                            display:block;
                                            padding: 15px 0px 5px 29px;
                                        }
                                        &:hover .child-menu a{
                                            margin:0;
                                        }
                                    }
                                }

                                .child-menu{
                                    width:100%;
                                    margin:-2px;
                                    background-color:$themeMainColor;
                                    li{
                                        display:inline;
                                    }
                                    a{
                                        padding: 5px 6px 0px 40px;
                                        color:#FFF;
                                        font-style:oblique;
                                        &:hover{
                                            background-color:#00C4FF;
                                        }
                                    }
                                }
                            }

                            .nav {
                                margin: 0;
                            }
                        }
                    }
                    #content{
                        padding-top:70px;
                    }

                    #footer{
                        max-width:979px;
                    }
                }

                .dockbar-split .dockbar, .dockbar {
                    float: right;
                    height: 56px;
                    position: relative;
                    z-index: 401;

                    .info-items {
                        height: auto;
                        position: static;
                        width: 300px;
                    }

                    .navbar-inner {
                        width: auto;
                        height:60px;
                        box-shadow:none;

                        img{
                            width:32px;
                        }

                        .btn-navbar {
                            background: transparent;
                            border: none;
                            float: left;
                            font-size: 24px;
                            padding: 12px 15px;

                            &:hover {
                                background-color: #5BBAE8;
                            }

                            &.open {
                                background-color: #F5AF45;
                            }
                        }

                        .nav-account-controls {
                            padding: 0 5px;

                            .user-avatar-image {
                                display: none;
                            }
                        }

                        .nav-add-controls {
                            background: transparent;
                            border-top-width: 0;
                            position: static;
                            z-index: 350;
                            padding: 0 5px;

                            > li {
                                float: none;
                                margin: 0;
                                position: relative;
                                transition:none;
                                left:0;
                                top: 0;

                                &:hover {
                                    left:0;
                                }

                                &.toggle-controls a {
                                    position: static;
                                    visibility: visible;
                                }

                                .page-preview-controls {
                                    display: none;
                                }

                                > a {
                                    background: transparent;
                                    text-align: left;
                                    white-space: nowrap;

                                    &:hover {
                                        background: #F3f3f3;
                                    }

                                    [class^="icon-"], [class^="icon-"], [class*=" icon-"] {
                                        font-size: 22px;
                                        margin-top: 0;
                                    }

                                    &.toggle-controls-link {
                                        background: transparent;

                                        [class^="icon-"], [class^="icon-"], [class*=" icon-"] {
                                            color: #FFF;
                                        }
                                    }

                                    .nav-item-label {
                                        background: transparent;
                                        display: inline;
                                        left: 0;
                                        margin-left: 0;
                                        margin-top: 0;
                                        padding: 0;
                                        position: relative;
                                    }

                                    &:hover .nav-item-label {
                                        display: inline;
                                    }

                                    .icon-caret-down {
                                        display: none;
                                    }
                                }
                            }
                        }

                        .nav-collapse {
                            z-index: 400;

                            &.open {
                                overflow: visible;
                            }

                            > .nav {
                                background: #009AE5;
                                border-width: 0;
                                left: auto;
                                margin: 0;
                                min-width: 160px;
                                position: absolute;
                                right: 0;
                                z-index: 400;

                                > li {
                                    &.dropdown {
                                        .dropdown-menu li a {
                                            font-size: 16px;
                                            line-height: 32px;
                                        }

                                        &.open {
                                            > .dropdown-toggle {
                                                color: #FFF;
                                            }
                                        }
                                    }

                                    > a {
                                        color: #FFF;
                                        font-size: 16px;
                                        margin: 5px 0;
                                        padding: 6px 15px;

                                        &:focus, &:hover {
                                            background-color: #5BBAE8;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                .portlet-dockbar {
                    display: inline-block;
                    width: 100%;
                }

                .signed-out #_145_navAddControlsNavbarBtn {
                    display: none;
                }
            }
        }
        /*-------------phone responsive--------------*/
        @include respond-to(phone) {
            .aui {

                #wrapper{
                    margin: 0 -20px;
                    #banner{
                        padding-top:70px;
                        #heading{
                            margin-top: -2em;
                            margin-bottom:0;
                            position: relative;
                            top: -30px;
                            left: 0;
                            .site-title{
                                img{
                                    width:auto;
                                    height:80px;
                                    margin-top:-13px;
                                }
                            }
                            .page-title{
                                padding-top:20px;
                            }
                        }

                        #navigation.open{
                            margin-top:-178px;
                        }
                    }

                    #content{
                        margin-top:-70px;
                    }
                }
                dockbar-split .dockbar, .dockbar {
                    float: none;
                }
            }
        }

Now if you preview your theme on the tablet and phone you'll see that all the
elements respond well. The logo has been scaled down and repositioned to better
fit the tablet and phone; the main navigation bar now hides with the following
code: `#navigation { display: none;...`. The pop-up navigation is handled with
`#navigation.open`, and can be accessed by selecting the hamburger button next
to the edit icon in the menu in the top right corner. Finally, the carousel has
been removed in both the tablet and phone views.

Sass mixins were used to tell the CSS to respond to the correct devices. For
instance, the `@include respond-to(phone, tablet)` mixin is used to wrap the
styles that are shared for both the phone and tablet devices. This is why it is
good to work organically for responsiveness. By starting off with the tablet
device, and moving down to the phone, you are able to figure out what elements
have shared render issues, and can then update them for both the phone and
tablet in one mixin.

The `@include respond-to(phone)` mixin is then used to wrap the remaining styles
that are unique to the phone only.

![Figure 1: With the updates to the CSS, the theme now responds well to the Smartphone.](../../images/phone-preview-04.png)

Congratulations! Your theme is now responsive.