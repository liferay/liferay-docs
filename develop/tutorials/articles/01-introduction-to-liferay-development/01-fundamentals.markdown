# Fundamentals 

Applications written for the Liferay platform version 7 are written in Java and
packaged as OSGi modules. Liferay is a Java EE application and has a default
configuration, but is designed to be extended by adding new applications,
customizing existing applications, modifying its behavior, or creating new
themes, through any programming language supported by the JVM. 

Web applications in Liferay are fragments of a web page known as *portlets*.
Liferay provides a convenient and easy-to-use framework called `MVCPortlet` to
make writing portlets easy, but developers are free to use any other framework,
such as Spring MVC, to create portlets. 

Liferay includes a utility called *Service Builder* that makes it easy to create
back-end database tables and an object-relational map in Java for accessing
them. It can also generate JSON or SOAP web services, giving developers a full
stack for storing and retrieving data using web or mobile clients.

Applications can be created very quickly, because Liferay already contains many
building blocks, called *assets*, that are commonly needed by today's
applications. For example, a commenting system allows developers to attach
comments to any asset that they define, whether they be assets they develop or
assets that ship with the system. Assets are shared by the system and are used
to represent many common elements, such as Users, Organizations, Sites, User
Groups, blog entries, and even Folders and Files. 

Liferay also includes many frameworks for operating on assets. A workflow system
makes it easy to create applications that require an approval process for users
to follow. The recycle bin stores deleted assets for a specified period of time,
making it easy for users to restore data without the intervention of an
administrator. A file storage API with multiple available back-ends makes it
easy to store and share files. Search is built into the system as well, and
developers can integrate it with their applications very easily. Almost any
framework you might think of using or developing is already there; you just need
to take advantage of it: a Social Networking API, user-generated forms with data
lists, a message bus, an audit system, and much more. 

The same is true for customization. Almost anything in Liferay can be
customized. Application interfaces can be redesigned, the whole user interface
can be themed, and menu items can be added or removed. 

Much of this is accomplished through modularity. 

## Modular Development

In the 1970s, personal computers were in their nascent stage of development, and
they were expensive. The core component of a personal computer, the
microprocessor, was perhaps the most complicated and expensive piece of hardware
in the machine, costing $300 or more by itself. An engineer named Chuck Peddle
wanted to change all that, and he did: inventing the 6502 processor in 1976 for
a mere $25. 

This changed everything. A minor explosion of development in personal computers
occurred, because now computer manufacturers could design their systems around
this one important--and now inexpensive--component, and their computers would be
affordable. You’ll recognize the products that followed: the Commodore PET,
VIC-20, and 64, the Apple II, the Atari 600 and 800 and VCS, the Nintendo
Entertainment System, and the BBC Micro computers. All of these products were
based on this one, revolutionary component. 

A few years later and in a similar fashion, IBM developed a PC to compete with
all of these products. In order to create it quickly, they engineered a system
based on off-the-shelf components: an Intel processor, an ISA bus, a standard
video interface. They developed one component of this system themselves: the
Basic Input Output System, or BIOS. Released in 1981, the original IBM PC was
one personal computer in a market crowded with competing products based on the
6502. 

Soon, some engineers realized what IBM had done and knew they could use the same
standard components IBM had used, if only they could create a compatible
implementation of the BIOS. This was done very quickly, and less than a year
later IBM-compatible PCs began to flood the market. All of these computers used
components that were available to everyone and interfaced in the same way. Where
one standard component, the 6502, had created a minor explosion in the nascent
computer industry, a whole system of components--a video interface, a disk
interface, and a bus interface for components to talk to each other--that
inter-operated in a standard way created a huge explosion, creating the computer
industry we have today. 

This is an example of modularity. You can plug any number of available devices
into a computer and they work because they are designed to integrate with the
system in a standard way. It doesn't matter who manufactured the computer,
because they are all made up of inter-operating modules that individually
implement specific functionality, but when put together comprise an entire
working system. 

Modularity is a powerful paradigm for designing any complex system like Liferay.
And now Liferay has made a bold move and re-engineered its product to take
advantage of modularity. 

### Modules and Components 

Liferay is customized or applications are built using OSGi modules, which are
also sometimes called bundles. Though this sounds like something new, it really
isn't: these are just .jar files, something with which Java developers are
extremely familiar. 

The core construct inside a module is a *component*. In simple terms, a
component is a Java class with the `@Component` annotation. In software
engineering terms, a component is the smallest building block of a larger
application, and that application is itself made up of many small components.
This makes it easier to develop an application because you only have to deal
with small, well-defined, bite-sized chunks of code at a time. 

Components are managed by a component container, which keeps track of which
components are installed and active. Many components offer services, and it's
the container's job to manage the available services by providing components the
services they need. It does this through a robust dependency management system
that's handled automatically at runtime. 

As you can see, many components running inside the system forms something of an
ecosystem of complimentary services. Liferay ships with many components that are
ready to use out of the box, and of course you can develop your own. You can
write components to provide new services or to override existing services with
your own implementation, and the container manages it all. 


