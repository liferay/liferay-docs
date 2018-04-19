 # Options

**An option** is a catalog feature that allows buyers to choose between variants
of a product. To create an option, you must first create an option template and
then--where applicable--a set of option values. 

**An Option Template** describes the kind of variation that the option defines.
If the buyer is to be prompted to choose between product variants of different
sizes, then the template would be defined as *Size*.

**An Option Value** refers to a specific selection a buyer might choose,
wherever the option involves preset rather than open-ended choices. For example,
an option template called *Size* might be assigned the option values *Small*,
*Medium*, and *Large*. In other cases, an option may not require any values at
all.

**Scope:** When an option template is created, it is scoped to the catalog. This
means that the template's configuration is applied to every product that deploys
it. However, when you deploy a template to an individual product, you can make
changes to the template that are scoped only to that product--other products in
the catalog will not be affected.

Option values can be scoped either to an option template or to a product. Values
that are scoped to a template apply to all products that deploy that template.
Values scoped to a product apply only to that product, but can override any
template-scoped values.
