# Creating a Layout Template [](id=creating-a-layout-template)

With the Plugins SDK you can deploy layout templates as plugins, and creating
layout templates with Liferay Developer Studio is easier than ever. Let's call
our layout template called *Columns 1 4 1*. 

***Using Developer Studio:*** 

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Enter *columns-1-4-1* for the Project name and *Columns 1 4 1* for the
    Display name.
    
3.  Choose whichever build type you prefer (Ant or Maven) and select the
    appropriate *Plugins SDK* and *Liferay runtime*. 

4.  Select *Layout Template* as your plugin type. 

5.  Click *Finish*. 

![Figure 6.1: Creating a custom layout template project in Studio](../../images/layout-template-new-project.png)

***Using the terminal:*** Navigate to your Plugins SDK's `layouttpl` folder,
and execute the *create* script in your terminal. Here's the generic version of
the *create* script, followed by operating system-specific commands:

    ./create.[sh|bat] <project-name> "<layout template title>"

1.  Example in Linux and Mac OS X:

        ./create.sh columns-1-4-1 "Columns 1 4 1"

2.  Example in Windows:

        create.bat columns-1-4-1 "Columns 1 4 1"

Developer Studio's *New Project* wizard and the *create* scripts generate layout
template projects in your Plugin SDK's `layouttpl` folder. Layout template
project names must end with *-layouttpl* so when you enter *columns-1-4-1* for
the project name, *-layouttpl* is automatically appended to the project name. 
