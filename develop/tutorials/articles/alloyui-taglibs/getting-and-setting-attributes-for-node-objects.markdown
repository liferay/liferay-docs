# Getting and Setting Attributes for Node Objects

There are a couple methods that you can use to get and set attributes for your
node objects: the `val()` method; and the `attr()` method; both of these methods 
are overloaded, meaning that they can be used for both getting and setting 
values. If the `val()` method is passed an argument, it sets the value attribute, 
otherwise it gets the value attribute. The `attr()` method takes two arguments: 
the first argument defines the attribute; the second argument defines the value 
of the attribute. If a second argument is not given, the `attr()` method gets 
the attribute, otherwise it sets the attribute. 

The example below gets the value of each of the input fields of the silly phrase 
generator and assigns them to unique variables.

1.  Add these variables just inside the function you just created:

        ver = verb.val();
        adje = adj.val();
        adve = adv.val();
        mama = mam.val();
        loca = loc.val();

Next, you'll add an element to display your silly phrase. To do this, you'll 
append a `<p>` element to the container `<div>` and give it a unique id to grab 
in the steps to follow.

