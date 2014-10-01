# Traversing the DOM with AlloyUI [](id=traversing-the-dom-with-alloyui)

In the last portion of the learning path, you learned how AlloyUI can be applied
to your application. In this section, you'll learn how you can use AlloyUI to
manipulate HTML elements in the DOM (Document Object Model). You'll explore this
subject through a fun addition to your Guestbook application: you'll
create a Silly Phrase Generator using the `<aui:form>` and `<aui:button>` tags 
that were covered in the last section. For now, the silly phrase is generated on 
the fly; you'll persist the phrase to the database in a later section.

First you'll walk through familiar territory: creating the form.

## Creating the form

1.  Open the `edit_entry.jsp` and drag the snippet labeled *form*, underneath the
    *Liferay AUI Taglib* category, to the bottom. Configure the `name` attribute
    to match the value below:
    
        name="<portlet:namespace />silly"

    Next, you need to create the <aui:fieldset> tags for the form. 
    
2.  Drag the *fieldset* snippet from underneath the Liferay AUI Taglib and add
    it inside of the `<aui:form>` tag.
    
    
    Next, you need to add the input fields to the form. If you've seen the 
    children's game *Madlibs*, then this will be familiar to you. You'll
    generate a silly phrase that will be made up of different parts of speech. 
    As you did with the other input fields, you'll add `aui:validator` tags to 
    the fields.  Since you'll be grabbing these input elements later, each one 
    needs to be given a unique id as well.

2.  Drag *input* snippets from the Liferay AUI Taglib category, add them to 
    the form, and configure the values to match the values below:

        <aui:input name="adjective" id="adj" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="verb" id="verb" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="adverb" id="adv" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="animal" id="ani" type="text">
            <aui:validator name="required"/>
        </aui:input>
        <aui:input name="location" id="loc" type="text">
            <aui:validator name="required"/>
        </aui:input>
            
    You added an additional type attribute of `text` for each input field to 
    make them visible. The entry bean that is used in the guestbook form defines 
    the bean for the `<aui:input>` fields automatically. Since you are not 
    adding silly phrase form fields to the bean, you have to force the `type` to 
    text so that they appear.

3.  Add an AUI *button* snippet to the form, just below the closing 
    `</aui:fieldset>` tag, to generate the silly phrase, and configure the `type`, 
    `class`, `id`, and `value` attributes to refelect the values below:

        <aui:button type="submit" class="btn btn-primary" id="submit" value="Generate"/>

    Giving the button a `type` value of `submit` will guarantee that our fields 
    will be validated. If the form is submitted with any of the fields left 
    blank, the phrase will not generate and the validation messages will alert 
    the user. Now that the form is done, you need to add the `<div>` element for 
    the silly phrase to display in.

4. Add the `<div>` element just above the `<aui:form>` opening tag you made 
   earlier for the silly phrase form:

        <div id="container"></div>

For now an empty `<div>` has been created with a unique id. You'll append a `<p>` 
element that will display your silly phrase later on. Now that you have the 
foundation layer, you can add the logic to make it work.

## Adding Logic to the Silly Phrase Generator

The first thing you need to do is grab the `<aui:button>` element so that you 
can attach a click event to it:

1.  Drag a  *script* snippet just below the `<aui-char-counter>` script you 
    created in the first part of the learning path and configure it to match the 
    values below:

        <aui:script use="event, node">
          var btnSubmit = A.one("#submit");
        </aui:script>

    You give the `use` attribute of the `<aui:script>` tag the value of the 
    `event` and `node` packages so that you have access to them for your click 
    event and input nodes later on. You then use the `A.one` selector method to 
    grab the button node by passing its `id` as the argument. Now that you have 
    a variable for the button element and the aui packages selected, you can 
    declare variables.

2.  Declare these variables just below the `btnSubmit` variable:

        var verb = A.one("#<portlet:namespace />verb");
        var adj = A.one("#<portlet:namespace />adj");
        var mam = A.one("#<portlet:namespace />ani"); 
        var adv = A.one("#<portlet:namespace />adv");
        var loc = A.one("#<portlet:namespace />loc");

    Each id for the input fields is preceeded by `<portlet:namespace/>` tag to 
    avoid namespacing issues between portlets. In addition to the input field 
    and button variables, you must declare a variable for an element to display 
    the silly phrase inside. You'll grab the `<div>` element you created earlier 
    for this:

        var container = A.one("#container");

    All variables have been declared, so now you can create the click 
    event.

3.  Add the function just below the container variable:

        btnSubmit.on('click', function(event){

        )};    

    Next, you need to declare variables for the value of your input fields.

4. Add these variables just inside the function you just created:

        ver = (verb.get('value'));
        adje = (adj.get('value'));
        adve = (adv.get('value'));
        mama = (mam.get('value'));
        loca = (loc.get('value'));

    Next, you'll add an element to display your silly phrase. To do this, 
    you'll append a `<p>` element to the container `<div>` and give it a 
    unique id to grab in the steps to follow.

5.  Append the element just below the value variables:

        container.append('<p id="phrase"></p>');

    Now that you have the HTML element created, you must assign it a variable 
    that you can call upon later.

6.  Add the variable below the appended element:

        phrase= A.one("#phrase");

    All right! The framework is set; now you need to write the code for the 
    phrase. To do this, you'll set the HTML of the `<p>` element which you just 
    assigned to the phrase variable. You'll also want to make sure that none of 
    the HTML is rendered for the phrase if any fields are left blank; to do this, 
    you'll wrap the silly phrase code in a conditional statement.

7.  Add this code below the phrase variable:

        if (ver != '' && adje != '' && adve != '' && mama != '' && loca != '') {
			phrase.html('Your silly phrase of the day is:<br>' + '"' + ver + 
			' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."');	
		}

The conditional statement uses the !=(not equal) operator to check that each
field is not blank. The &&(and) operator is used between each condition, meaning 
that all of the conditions must be true before the statement is true. Once the 
statement is true, you use the `html()` method to set the inner HTML of the 
message element.

There you have it! Redeploy your application and go to the Guestbook's Add Entry
page to see your new Silly Phrase Generator. With the form filled out like the 
one below, your silly phrase should read: "Walk your left-footed platypus
gingerly in the warehouse."

![Figure 1: You can generate some silly phrases with the Silly Phrase Generator.](../../images/guestbook-silly-phrase.png)

Everything is in working order, but there are a few things left that you can do 
to make the Silly Phrase Generator better.

## Finishing Touches

The Silly Phrase Generator works well. Congrats! There are a few finishing 
touches that will hit it out of the park, though. First of all, you can style 
the silly phrase so that it sticks out from the rest of the form. Second, you 
can add an option to hide the Silly Phrase Generator when it's not being used. 
lastly, you'll add a tool-tip using the `<liferay-ui:icon-help>` tag so that 
users know that they have the option to create a silly phrase.

### Styling the Silly Phrase

The first thing you can do is add a bit of style to the phrase, so that it
stands out from the form. Just like the previous learning path, You will add all 
of your styling to the `main.css` file.

1.  Add the style code to the top of the `main.css`:

        #phrase
        {
            font-size:200%;
            line-height:120%;
        }

    You style your silly phrase by referencing its `id`, which you set to 
    `phrase`. In this case, you set the font size to double the original size. 
    You've also set the `lineHeight` a little wider than normal so that the 
    words don't overlap when the page is resized to a smaller width. One final 
    thing you can do to give the silly phrase form a bit of separation from the 
    rest of the page is to add a title.

2.  Add the following `<p>` element just above the `<div>` tag with the id 
    `container`:

        <p id="phraseTitle">Silly Phrase Generator</p>

    The `<p>` element has been given the `id` `phraseTitle`. As you did before 
    with the styling for the phrase, you'll add styles to the title next.
    
3.  Add the following styles below the `#phrase` styling in the `main.css`:

        #phraseTitle
        {
            font-size:35px;
            line-height:120%;
        }

    You've given the phrase title a `font-size` to make it stand out, and as 
    with the silly phrase, you've given it a larger than normal line height to 
    account for word wrapping. Now you can assign it a variable so that you can 
    access it later.
 
4.  Inside the `edit_entry.jsp` declare the title variable just below the 
    container variable:

        var title = A.one("#phraseTitle");

The silly phrase now stands out from the form. Next, you'll set up the logic to
hide the silly phrase.

### Hiding the Silly Phrase Generator

The first thing you need to do is create a node that can have an event attached 
to it to hide and show the Silly Phrase Generator. A check box works well for 
this, because of its two possible states: checked and unchecked. This option 
should be visible on the Guestbook form, so you'll add it there.

1.  Inside the `edit_entry.jsp`, drag the snippet labeled *input*, underneath 
    the *Liferay AUI Taglib* category, to the form, just before the closing 
    `</aui:fieldset>` tag, and configure the attributes to match the following 
    values: 

        <aui:input 
            id="show" 
            type="checkbox" 
            name="include" 
            label="Show Silly Phrase Generator" 
            checked="false"
        />

    Now that the check box is there, you can assign its node a variable.
 
2.  Declare this variable just below the title variable:

        var checkbox = A.one("#<portlet:namespace/>showCheckbox");
    
    Just as before, you use the node's `id` to reference it. One thing that you 
    probably notice is the added 'Checkbox' appended to the end of the show `id`. 
    It's important to note that when an `<aui:input>` is created of type 
    `checkbox`, 'Checkbox' is automatically appended to the end of the `id`. Now 
    that you have the `checkbox` variable attached to the node, you can create 
    the function that hides the Silly Phrase Generator.
 
3.  Add this function just below the `buttonSubmit.on('click'...` function:

        checkbox.on('change', function(event){

        });

    This is a function that listens for an 'onChange' event on the
    `checkbox` node. If there is any change in its state, this function is
    triggered. Now you can define the conditions for the `checkbox`. Since there 
    are two states to a `checkbox` element (checked and unchecked), you'll  
    naturally have two conditions. First you need a way to check the state of 
    the `checkbox`. In order to do that you'll use the `attr()` method to check 
    the value of the checkbox's `checked` attribute. You'll declare a private 
    variable to determine if the `checkbox` is checked.
 
4.  Declare the `checked` variable at the top of the `checkbox` function:

        var checked = checkbox.attr('checked');
    
    The checked variable is now equal to the `checked` attribute of the
    `checkbox` node. The `checked` attribute holds a boolean value depending on
    the state of the checkbox: true for `checked` and false for `unchecked`. Now
    you can write conditional statements to test for these two states.
 
5. Add the following conditional statements to the `checkbox` function:

        if(checked){
    
        }
        else if(!checked){
    
        }
    
    The two conditionals are now written: one that checks if the checked
    attribute is true, and one that checks if it is false. The `!`(not) operator
    placed before the checked condition in the `elseif` statement tests whether 
    the checked attribute is not true, (false). Now you can add the functionality.
    You'll use the `show()` and `hide()` methods to show and hide the nodes, 
    depending on the state of the `checkbox`. First you need to determine the 
    nodes to show and hide. The main element to be hidden is the silly phrase 
    generator form. As of right now there is no variable created for it, so you 
    must create one. If you were to try to hide the form element itself, the 
    input fields would still appear. Instead, you can wrap the form and the rest 
    of the Silly Phrase Generator elements in `<div>` tags and hide the `<div>` 
    element that holds all the content for the generator.

6.  Create a `<div>` tag with the id below, and place it just above the
    paragraph element with the id `phraseTitle`:

        <div id="silly">

    Be sure to add the closing `</div>` tag just below the closing `</aui:form>`
    tag for the Silly Phrase form. With the Silly Phrase Generator wrapped, you 
    will also need to add styles for it in the `main.css`

7.  Add the styling below for the `silly` id just below the `#title` styles:

        #silly
        {
	        display:none;
        }
    
    The `<div>` has been given a display style of `none` to hide the form when
    the portlet first loads.
    
8.  Declare the following variable below the `checkbox` variable:

        var silly = A.one("#silly");
    
    Now that the `<div>` element is assigned to a variable, you can use it
    to hide and show the Silly Phrase Generator. You added a display style of 
    `none` to the silly `<div>` because the generator must be hidden until the 
    check box is checked. If the check box is checked, you want to show the 
    generator, and if it's unchecked you want to hide the generator. With the 
    silly `<div>` assigned a variable and styled, you can now finish the 
    conditional statement for the 'onChange' event for the checkbox.

8.  Your conditional statement should look like the following code:

        checkbox.on('change', function(event){
        var checked = checkbox.attr('checked');
        if(checked){
            silly.attr('style', 'display:inline');
        }
        else if(!checked){
            silly.attr('style', 'display:none');
        }
	
        });

The `attr()` method has been used to show and hide the Silly Form Generator by 
setting the display style to `inline` and `none` respectively. The external 
`main.css` styles hide the generator by default, and then get overidden by the 
inline styles set by the `attr()` method. You can now hide and show the form! 
Next, you'll add an informational tooltip to explain what the silly phrase 
generator is.
 
### Configuring the liferay-ui:icon-help Tag

If you look at the Control Panel in Liferay Portal, you can see tooltips in 
action. Next to each link is a question mark icon that displays a tooltip on 
mouse over. The `<liferay-ui:icon-help>` tag is a quick method to create the
tooltip UI.

1.  Drag the snippet labeled *icon-help*, underneath the *Liferay UI Taglib* 
    category, and add it to the `edit_entry.jsp`, just below the 
    `<aui:input type="checkbox>`. 

2.  Fill out the value of the `message` attribute to match the following text:

    "Check the box to create a silly phrase or uncheck it to hide the form. Fill 
    out the fields below with the correct parts of speech to generate a unique 
    and silly phrase."
 
3.  Update your checkbox and tooltip to reflect the code below:

        <div>
            <div id="cbox">
                <aui:input 
                id="show" 
                type="checkbox" 
                name="include" 
                label="Show Silly Phrase Generator" 
                checked="false"
                />
            </div>
            <div id="ttip">
                <liferay-ui:icon-help 
                message="Check the box to create a silly phrase or uncheck it to 
                hide the form. Fill out the fields below with the correct parts 
                of speech to generate a unique and silly phrase."
                />
            </div>
        </div>

    What you've done is placed both the checkbox and tooltip inside their own
    `<div>`s and set them both inside a larger `<div>`; this has been done so 
    that they can be placed side by side. With the current styling, the tooltip 
    rest below the checkbox. Next, you'll add some styling to the `<div>`s 
    you've just created to fix this.

4.  Add the following styles to the `main.css`:

        #cbox
        {
            float:left;
        }
        #ttip
        {
            float:left;
            margin-left:10px;
        }

    The tooltip and checkbox have been set to float left inside the larger 
    `<div>`. The tooltip has been given an additional 10 pixel margin from the 
    left, so that its `<div>` is on the right of the check box label. With this 
    new organization and styles, the Silly Phrase Generator is now complete! 
    Give yourself a pat on the back. Your finished form should look like this:
 
![Figure 2: Here is what your finished form should look like.](../../images/guestbook-silly-phrase-finished.png)

You can view the full code for the `edit_entry.jsp` [here](https://github.com/liferay/liferay-docs/blob/master/develop/learning-paths/code/learning-sdk/portlets/guestbook-portlet/docroot/html/guestbook/edit_entry.jsp).

Congrats! Your Silly Phrase Generator is complete, and now you have a basic 
understanding of how to traverse the DOM using AlloyUI.

## Related Topics

<!--Add Link for JSF portlets here-->
