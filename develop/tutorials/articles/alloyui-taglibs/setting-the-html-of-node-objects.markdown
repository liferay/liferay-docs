# Setting the HTML of Node Objects

AlloyUI provides the `html()` method to set and get the HTML of a node object.
To get the HTML of a node object simply pass no arguments in the method. To set
the HTML of a node object, pass in the HTML you wish to set for the node object
as the argument for the `html()` method.

The example below sets the HTML for the phrase node.

You'll want to make sure that none of the HTML is rendered for the phrase if any 
fields are left blank; to do this, you'll wrap the silly phrase code in a 
conditional statement.

1.  Add this code below the phrase variable:

        if (ver != '' && adje != '' && adve != '' && mama != '' && loca != '') {
			phrase.html('Your silly phrase of the day is:<br>' + '"' + ver + 
			' your ' + adje + ' ' + mama + ' ' + adve + ' in the ' + loca + '."');	
		}

The conditional statement uses the !=(not equal) operator to check that each
field is not blank. The &&(and) operator is used between each condition, meaning 
that all of the conditions must be true before the statement is true. Once the 
statement is true, you use the `html()` method to set the innerHTML of the 
message element.

There you have it! Redeploy your application and test your new Silly Phrase 
Generator. With the form filled out like the one below, your silly phrase should 
read: "Walk your left-footed platypus gingerly in the warehouse."

![Figure 1: You can generate some silly phrases with the silly phrase generator.](../../images/silly-phrase.png)

Now that your silly phrase generator is working properly, there are a couple 
more things you can add to it.

