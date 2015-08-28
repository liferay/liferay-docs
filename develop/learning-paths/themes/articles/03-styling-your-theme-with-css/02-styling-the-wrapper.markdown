## Styling the Wrapper

The Wrapper is the container that holds the theme's elements. It is used for
positioning and styling the theme as a whole. You'll use the Wrapper to set up
the dimensions and background for the theme.
    
1. Add the following code below the *Wrapper* placeholder:

        #wrapper {
            background-color: $bkColor;
            margin: 0 auto;
            max-width: 1000px;
            padding:0;
            position:relative;
    
2. Add the closing `}`(brace) for the `#wrapper` just above the closing `}` 
   for the `html` styling.
   
    The CSS above makes use of the SASS `$bkColor` variable to color the
    background of the wrapper, separating it from the background gradient. Most 
    important, it sets the width and margins for the theme.     
    
    As a design choice, the wrapper has been given a set width,
    `max-width: 1000px;` , and centered,`margin: 0 auto;`, relative to the 
    browser. This allows the gradient background to show when the browser is 
    wider than the `max-width`. If someone views the theme in a larger display 
    than the `max-width`, they won't be greeted with dead pixels on either side 
    of the main content. You always want to keep the user experience in mind 
    when designing the theme.
    
    Using the `max-width` property sets the width, while also allowing it to be 
    flexible. In a later section of the learning path, this will allow the theme 
    to scale down for smaller devices. Had the `width` property been used 
    instead, the width would be carved in stone.
   
3. Save the file and navigate to the *Welcome* page and refresh it to see the 
   changes.
    
    Your theme should look like the following figure:
    
    ![Figure 1: The theme is starting to take shape.](../../images/css-wrapper.png)
    
    Now that the Wrapper for the content is styled, you can move on to the
    Banner next.