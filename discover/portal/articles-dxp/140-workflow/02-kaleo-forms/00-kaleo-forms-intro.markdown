# Workflow Forms [](id=workflow-forms)

Business processes are often form-based and workflow-driven. They start with
some data that needs to be entered and progress by sending the data to other
people or groups. The initial data is processed in some way (for example,
further data is entered or approval is granted), and the process moves on until
completion, when each interested party has seen and manipulated the data. To
write an app for each of these fluid processes is laborious and not cost
effective. Instead, a tool for quickly defining a process to suit each use case
is needed. The process architect must define the data that gets collected and
the process the data moves through to reach its final state. To accomplish this,
@product@ already includes the 
[Dynamic Data Lists application](/discover/portal/-/knowledge_base/7-1/creating-data-definitions)
for defining forms, and the 
[Kaleo Designer application](/discover/portal/-/knowledge_base/7-1/kaleo-designer) 
for designing workflows. The Kaleo Forms solution combines the features of these
applications, letting you design an integrated process for sending forms through
a workflow in one intuitive UI.

The entire process includes:

1. Creating a Kaleo Process

2. Creating the process's field set (or selecting an existing 
   [Data Definition](/discover/portal/-/knowledge_base/7-1/creating-data-definitions))

3. Creating the process's workflow definition (or selecting one that's already
   created)

4. Creating and assigning forms for each workflow task

+$$$

**Example Use Case:** 

The Lunar Resort spa is a popular place. With all the adventure-driven
activities (lunar hiking, rover races, etc.) available to guests, it's important
to offer some relaxing and rejuvenating experiences. As the spa expands and new
Spa Technicians are hired, the Spa Manager decides to implement a new process to
handle orders. It involves several steps and several people:

- The customer service representative for the spa interacts with a customer and
  fills out an order form.

- The customer service representative gives the order details to the spa
  technicians so they can divide up the requests, with a single technician
  assigned to each order. 

- The spa technician sends the order to the spa manager for final approval and
  comment. 

- The spa manager sends the final order back to the customer service
  representative, who contacts the customer with the final details of his spa
  service order.

A process like this is a nightmare to manage manually, but perfectly suited for
Kaleo Forms.

$$$

Since Kaleo Forms is accessed through a site's administration menu (under
Content &rarr; Kaleo Forms Admin), make sure the intended users of your Kaleo
Processes have access to the application. See the article on [roles and permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions) if you're unsure how that works.
