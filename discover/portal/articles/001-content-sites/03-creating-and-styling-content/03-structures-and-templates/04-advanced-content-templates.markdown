# Advanced Content Templates

Our previous template used CSS, HTML, and Freemarker to create styled content.
Next, you'll use Javascript to create a more interactive element.

## Creating the new Structure

The structure will actually be simpler this time. It will contain just an image 
and text. Create it now.

1. Go to the Structure page - *The Lunar Resort* &rarr; *Web Content* &rarr; *Structures* (from the top menu).

2. Click the `+` icon to create a new structure.

3. For the *Name* enter "Booking Form"

4. In the *Fields* section add a *Text* and an *Image*.

5. Click on the Text field to open its settings.

6. Set the *Field Label* to "Button Text" and the *Name* to "buttontext".

7. For the Image, leave the field label as *Image* and set the *Name* as
    "bgimage".

![Figure x: The finished structure](../../../../images/001-booking-form.png)

Now that the structure is complete, you can move on the Template.

## Creating the Template

For the template, you will need to style the text and image and also add a 
Javascript function for submitting the booking.

1. Go to the Template page - *The Lunar Resort* &rarr; *Web Content* &rarr; 
    *Templates*.

2. Click the `+` icon to create a new template.

3. For the *Name* enter "Booking Form".

4. Open *Details* and select *Booking Form* for the *Structure*.

5. In the code field, we'll start with the bottom this time. First enter the
    script in the bottom:

        <script>
        function bookNow() {
          var popup = document.getElementById("myPopup");
          popup.classList.toggle("show");
        }
        </script>

    This creates a function named "bookNow" that triggers a popup when it is 
    called. Next, we'll the main body of the template.
    
6. Paste in the main section next:

        <div class="main" onclick="bookNow()">
          <button>${buttontext.getData()}</button>
          <span class="popuptext" id="myPopup">You have successfully booked your trip!    <br />  See you on the moon!</span>
        </div>
    
    This creates a div named "popup" which fires on the "bookNow" function when
    clicked. It retrieves the `buttontext` from the structure, and also sets
    the "popuptext" which will display in the function. Everything is namespaced
    so that you can create the styles accordingly.

7. Finally let's add the styles class by class.

    First the styles for the main div:

        <style>
         .main {
            text-align: center;
            width: 100%;
            margin: auto;
            display: inline-block;
            cursor: pointer;
            height: 350px;
            background-image: url("${bgimage.getData()}");
        }

    Next the button styles:
    
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
    
    After that, the style for the area that will appear when the function is
    called. Notice that its initial visibility is set to "hidden".
    
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

    Finally, when the function runs, the `.show` class will become active and
    this style will set the visibility to `vibisible`
    
        .popup .show {
            visibility: visible;
        }
        </style>

That covers the template creation, but we also need to create the content to
fill it out.

## Creating the Content

The content for this template is pretty simple - a background image and your
button text.

1. Go back to the main *Web Content Page*.

2. Click the `+` button and select *Booking Form* which will appear in the list 
    now.

3. For the title enter "Booking Form"

4. For *Button Text* enter "Book Now!"

5. For the image, click *Select* and go the the *Documents and Media* tab.

6. Select the `booking-image.png` that you uploaded earlier.

7. Click *Publish*.

Now, all you need to do is add it to the page.

## Demonstrate the Content

1. Navigate to the Lunar Resort site and the *Book a Trip* page.

2. Open the add menu.

3. Drag the *Booking Form* content onto the page.

4. Open the *Look and Feel Configuration*.

5. Set the border style to *Barebones* and click *Save.

6. Refresh the page.

7. Test out your button!


