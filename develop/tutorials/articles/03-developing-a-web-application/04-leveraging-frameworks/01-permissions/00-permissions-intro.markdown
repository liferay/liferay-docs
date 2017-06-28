# Permissions

So far you've created a robust application which uses the database for data 
storage, and then expanded that application to include multiple components for
managing and accessing that data. What comes next? You need to protect that data
and ensure that no one can create or access it without the correct credentials.

You'll implement a permissions scheme that only allows signed in users to create
guestbook entries, and enables only administrators to create new guestbooks.

# Configuring Your Permissions Scheme

First you need to define your permissions scheme. This is done via XML in the
`default.xml` file. Objects in your application are defined as resources and
resource actions are defined to manage how users can interact with those
resources.

IMAGE?
