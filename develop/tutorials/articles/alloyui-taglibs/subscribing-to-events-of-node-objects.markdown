# Subscribing to Events of Node Objects

Now that you have referenced your element nodes you can subscribe to events for 
them. Subscribing to events of your element nodes is a quick and easy process. 
Using the `on()` method of the node object, you can subscribe to events and 
trigger a function when the event occurs. A few of the available events you can
subscribe to are `blur`(when a node loses focus), `click`, 
`focus`(when a node gets focus), `mouseout`, and `mouseover`.

+$$$

**Note:** You can read more about events in the [AlloyUI Rosetta Stone](http://alloyui.com/rosetta-stone/#common).

$$$

The example below subscribes to the `click` event of the silly phrase button 
node, which is set to the variable `btnSubmit`.

1.  Add the following function just below your variables:

        btnSubmit.on('click', function(event){

        });    

Next, you need to declare variables for the value of your input fields. 

