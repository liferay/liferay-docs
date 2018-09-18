# Sort Order Changed with a Different Database [](id=sort-order-changed-with-a-different-database)

If you've been using @product@, but are switching it to use a different database
type, consult your database vendor documentation to understand your old and new
database's default query result order. The default order is either
case-sensitive or case-insensitive. This affects entity sort order in @product@.

Here are some examples of ascending alphabetical sort order. 

Case-sensitive:

    111
    222
    AAA
    BBB
    aaa
    bbb

Case-insensitive:

    111
    222
    AAA
    aaa
    BBB
    bbb

Your new database's default query result order might differ from your current
database's order. 

Consult your vendor's documentation to configure the order the way you want. 
