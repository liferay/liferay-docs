# Advanced Templates [](id=advanced-templates)

<div class="learn-path-step">
    <p>Creating Content<br>Step 3D of 3D</p>
</div>

The template you created for lunar guides used CSS, HTML, and FreeMarker to 
style and format the corresponding structure's fields. Templates in @product@ 
can also use JavaScript. This lets you create complex, interactive content. To 
illustrate this, you'll create a new structure, and then create its template 
using JavaScript. 

## Creating the Structure [](id=creating-the-structure)

The structure for the booking form is relatively simple--it only needs a Text 
field and an Image field. Follow these steps to create it:

1.  Open the *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)) and select *The Lunar 
    Resort* &rarr; *Content* &rarr; *Web Content*. 

2.  At the top-right of the screen, select *Options* 
    (![Options](../../../../images/icon-options.png)) &rarr; *Structures*.

3.  Click the *Add* icon 
    (![Add](../../../../images/icon-add.png)) at the bottom-right of the page. 

4.  Name the structure *Booking Form*. 

5.  Add a *Text* field to the canvas. Below that field, add an *Image* field. 

6.  Set the Text field's *Field Label* to *Button Text*. Set the field's *Name* 
    to `buttontext`.

7.  Set the Image field's *Name* to `bgimage`. Leave the field's Field Label 
    alone. 

    ![Figure x: The Booking Form structure contains a Text field above an Image field.](../../../../images/001-booking-form.png)

8.  Click *Save*. 

Now that the structure is complete, you can move on the template.

## Creating the Template [](id=creating-the-template)

The booking form's template styles the text and image, and also contains a 
JavaScript function that submits the booking. Follow these steps to create this 
template:

1.  Open the the *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)) and select *The Lunar 
    Resort* &rarr; *Content* &rarr; *Web Content*. 

2.  At the top-right of the screen, select *Options* 
    (![Options](../../../../images/icon-options.png)) &rarr; *Templates*. 

3.  Click the *Add* icon 
    (![Add](../../../../images/icon-add.png)) at the bottom-right of the page. 

4.  Name the template *Booking Form*. 

5.  Open the *Details* section and select *Booking Form* for the *Structure*. 
    In the *Language* field, leave *FreeMarker* selected. 

6.  Now you're ready to create the template's script. First, you'll add
    a JavaScript function that triggers a pop-up when called. In the *Script*
    section, delete any existing code and add this code: 

        <script>
        function bookNow() {
          var popup = document.getElementById("myPopup");
          popup.classList.toggle("show");
        }
        </script>
 
7.  Now you'll add a `div` named `popup` containing a `span` named 
    `popuptext` that operates on the `bookNow` function 
    when clicked. This `span` retrieves the `buttontext` from the structure 
    and sets the `popuptext` to display in the function. Everything is 
    namespaced so that you can create the styles accordingly. Add this code now,
     **above** the `bookNow` function: 

        <div class="popup" onclick="bookNow()">
          <button>${buttontext.getData()}</button>
          <span class="popuptext" id="myPopup">You have successfully booked your trip!    <br />  See you on the moon!</span>
        </div>

8.  Finally, you'll add the styles. The following code first defines the styles 
    for the main `div` and the button. Next, it defines the style for the area 
    that appears when the `bookNow` function is called (note that its initial 
    visibility is `hidden`). Finally, when the function runs, the `.show` class 
    becomes active and the visibility is set to `visible`. Add this code 
    **above** the existing code in the editor:

        <style>
        .popup {
            text-align: center;
            width: 100%;
            margin: auto;
            display: inline-block;
            cursor: pointer;
            height: 350px;
            background-image: url("${bgimage.getData()}");
        }

        button {
            position: relative;
            top: 110px;
            padding: 20px 20px;
            text-align: center;
            text-decoration: none;
            border: none;
            font-size: 40px;
            border-radius: 6px;
            background-color: #65b6f0;
            color: white;
        }

        .popup .popuptext {
            visibility: hidden;
            width: 450px;
            background-color: #555;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 8px 0;
            position: absolute;
            z-index: 1;
            bottom: 100%;
            left: 50%;
            margin-left: -220px;
            font-size: 150%;
        }

        .popup .show {
            visibility: visible;
        }
        </style>

    When you're done, click *Save.*

Awesome! Now you have a template for the booking form. You're ready to use the 
structure and template to create some content. 

## Creating the Content [](id=creating-the-content)

Like the structure, content for this structure and template is simple. It can 
only contain an image and button text. Follow these steps to create such content 
now:

1.  Open the the *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)) and select *The Lunar 
    Resort* &rarr; *Content* &rarr; *Web Content*. 

2.  Click the *Add* icon 
    (![Add](../../../../images/icon-add.png)) at the bottom-right of the page 
    and select *Booking Form*. 

3.  Enter *Booking Form* for the title. 

4.  Enter *Book Now!* for the button text. 

5.  For the image, click *Select* and then upload and select the 
    `booking-image.png`. 

6.  Click *Publish*.

Now, all you need to do is add it to the page.

## Displaying the Content [](id=displaying-the-content)

1.  Navigate to the Lunar Resort's *Book a Trip* page. 

2.  Click the *Add* icon 
    (![Add](../../../../images/icon-add-app.png)) at the top-right of the page 
    and expand *Content*. 

3.  Drag the *Booking Form* content onto the page. 

4.  Open the *Look and Feel Configuration* of the Web Content Display app that 
    contains your content. 

5.  On the *General* tab, set *Application Decorators* to *Barebone* and click 
    *Save*. 

6.  Refresh the page, and then test out your button.

![Figure x: Template before click.](../../../../images/001-adv-template-final1.png)

![Figure x: Template after click.](../../../../images/001-adv-template-final2.png)

Great! Now you know how to use structures and templates to create and style web 
content. Next, you'll learn about some other ways to publish content in 
@product@. 
