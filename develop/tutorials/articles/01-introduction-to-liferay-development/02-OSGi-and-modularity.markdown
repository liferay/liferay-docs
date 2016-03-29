# OSGi and Modularity

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

## Benefits of OSGi for Liferay

At Liferay, many teams work on the product at the same time. Before modularity,
teams would step on each other: a bug fix in one place might introduce a bug in
another place due to an implementation change or a break in an API contract. To
support its vision of extensibility, Liferay also contained a lot of code that
enabled developers to modify its core in a way that avoided patching the source
itself and was easy to use. While this worked well, points of customization had
to be defined ahead of time, and identifying what users would want to customize
was hit and miss. 

Modularity, and OSGi specifically, solves all that. OSGi is the most mature
modular platform in existence, and offers fantastic technologies for building
modular applications. Because it is so mature, it has defined patterns of its
own, such as the whiteboard and extender patterns, that Liferay uses to build
its extensible framework. Additionally, OSGi lives in the open source world: it
has several implementations, which means Liferay didn't have to build its
framework from scratch. Instead, Liferay was able to *eliminate* a lot of code
for extending itself, because now the platform by design supports that natively. 

Finally, one of the most important benefits of OSGi for Liferay is *semantic
versioning*. This is a way of defining exactly which version of which service is
supported by which other service. This is an extraordinary help to developers
and system administrators alike, because it avoids what they call "dependency
hell." It allows the programmatic detection of version compatibility at runtime,
resulting in far fewer runtime errors and more long term stability. 

OSGi was an excellent choice for Liferay, and Liferay is excited to bring our
new technology to you. 

## Semantic Versioning 

In any complex system there are a ton of supporting libraries. Developers find a
library that performs a specific function which they can call to provide
supporting infrastructure for what they are developing. Of course, since this is
a third-party piece of software, its author upgrades and enhances it according
to his or her timeline. If you use version 2.2.1 of this library and its author
a month later releases 2.3.0 and changes a call you made, you cannot upgrade to
the new version unless you first make a change to your software. This is called
"version lock." Similarly, if your software requires version 2.3.0 and another
supporting library requires 2.2.1, you may use one version in one classloader
and another version in a different classloader. This is called "version
promiscuity." 

Either of these situations makes your software more complex. You have to manage
upgrades manually and make sure all necessary versions are accounted for. This
prevents you from safely and easily evolving your project to the next level. 

[Semantic Versioning](http://semver.org) changes that with a system for managing
library versions using a MAJOR.MINOR.PATCH numbering system. This is used in
OSGi to define dependencies for modules. When packages are imported are
exported, the required version is also specified, and this allows the system to
provide the proper versions to the proper packages, if those versions are
installed. 

In this way, dependencies are managed in a much more straightforward way by the
system, freeing you to move forward with your project much more easily. 

### ProviderType vs. ConsumerType

In a modular, semantically versioned world, then, code is broken up into modules
that have various inter-dependencies. These are managed by the container at
runtime. For the container to be able to manage these dependencies
appropriately, modules that implement an API need to be marked as such. This
allows the container to use semantic versioning to import the proper version of
the package. 

Required versions or version ranges are declared in a module's `bnd.bnd` file,
and this is what the container uses to create the proper configuration. To mark
an implementation as a provider, all a developer needs to do is add the
`@ProviderType` annotation to the interface. Once this is done, the container
can match APIs and implementers by their versions. 

If you don't provide this annotation, the module you're writing is assumed to be
a `@ConsumerType`. This is a module that *consumes* or uses the service. This is
a complex concept that is better explained when you get to the code, so for now
just keep it in the back of your mind, as you'll encounter it later. 

## Extensibility 

As you might imagine, the system described above contains all the tools
necessary to make a well designed system that allows developers not only to
create applications based on modules, but also to extend the existing
functionality of the system. Liferay can benefit from this now because the
platform on which it rests is designed for both application development and
customization. 

Components in the OSGi runtime have what's called a *service ranking*. This can
be used by developers to extend or override modules that are already running in
the system. Extending or overriding existing functionality then becomes as easy
as providing your own implementation and then deploying your module with a
ranking higher than the default ranking. This is a simple and natural way to
extend the system, and it's incorporated into the design of the platform from
the ground up. 

