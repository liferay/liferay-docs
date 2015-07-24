# Creating Social Media Links for Your Theme

Follow the steps below to add the social media links to the Footer.

1. Open `portal_normal.ftl`.

2. Add the following code inside of the `<footer>` tag, above 
   `<p class="powered-by">`:
  
        <div class="social-media-wrapper">
        <h2 class="nav-heading">
            Follow Us
        </h2>
        <ul>
            <li>
                <div style="background-color:#3C5998;"><a class="icon-facebook" 
                style="color:#ffffff;" 
                href="http://www.facebook.com/pages/Liferay/45119213107" 
                target="_blank"><span class="hide">Facebook</span></a></div>
            </li>
            <li>
                <div style="background-color:#34A9E3;"><a class="icon-twitter" 
                style="color:#ffffff;" href="http://www.twitter.com/liferay" 
                target="_blank"><span class="hide">Twitter</span></a></div>
            </li>
            <li>
                <div style="background-color:#0C2C5B;"><a class="icon-linkedin" 
                style="color:#ffffff;" 
                href="http://www.linkedin.com/company/83609" 
                target="_blank"><span class="hide">LinkedIn</span></a></div>
            </li>
            <li>
                <div style="background-color:##CF3327;"><a class="icon-youtube" 
                style="color:#ffffff;" 
                href="http://www.youtube.com/user/liferayinc" 
                target="_blank"><span class="hide">YouTube</span></a></div>
            </li>
            <li>
                <div style="background-color:#DA4835;"><a 
                class="icon-google-plus" style="color:#ffffff;" 
                href="https://plus.google.com/+liferay/posts" 
                target="_blank"><span class="hide">Bloogs</span></a></div>
            </li>
        </ul>
        </div>

3. Save the file.  

Each social media link uses a Font Awesome icon. Font Awesome offers two choices 
for each brand icon: 

1. One set of icons where the brand logo is in a fixed position, made up of 
   empty pixels, with a border. This lets you move the logo and border around as 
   a whole, but does not give you control over the position or styling of the 
   logo itself.

    ![Figure 1: Font Awesome brand icons can have the logo made up of empty pixels.](../../images/font-awesome-icon-01.png)
   
2. One set of icons where the brand logo does not have a border. This allows you 
   to position the and style the logo, and create the border yourself.

    ![Figure 2: Font Awesome brand icons also offer logos without borders.](../../images/font-awesome-icon-02.png)

For the purposes of the social media icons, you need control over the background 
color and the logo's position. If you take a look at the [icon-facebook-sign](http://fortawesome.github.io/Font-Awesome/3.2.1/icon/facebook-sign/) 
icon's trademark *f* logo you can see that it is made up of empty pixels. 
Because the icon's logo is made up of empty pixels, the Footers's background 
color shows through the logo, which in this case red, doesn't fit the design 
mockup.  

Using the second option, the borderless brand icon, the brand logos are given 
their own styling in the `<a>` tags. They're then placed within `<div>` tags, 
which are given styling to color the background. This allows you to position the 
logo, with its own color, while also letting you control the size and color of 
the background and border.

Now that the social media links are in place, you can add the CSS for them.

1. Open `custom.css` and place the following code below the `.powered-by` 
   styling in the `footer`:

        .social-media-wrapper {
            h2{
            color:white;
            text-align:center;
            }
            li {
            display: inline-block;
            font-size: 31px;
            border-radius: 6px;
            padding: 3px;
            margin-top: 11px;
                a{
                border-radius:6px;
                padding: 7px 8px;
                    &:hover{
                    text-decoration:none;
                    }
                }
            }

            ul {
            text-align: center;
                li div{
                width:44px;
                height:39px;
                border: 1px solid #CD4023;
                border-radius:6px;
                padding-top:7px;
                }
            }
        }
	    
2. Save the file and wait for the theme to republish.

3. Load the *Welcome* page of the theme and you'll see your new social media 
icons have been placed in the Footer just below the *Follow Us* heading.

Congrats! the Footer is finished.

In the next portion of the learning path you'll learn how to make configurable
theme settings for the Lunar Resort.
