# Copying and Moving Entities [](id=copying-and-moving-entities)

Although the Documents and Media API lets you copy and move entities, these
operations have some important caveats and limitations. Keep these things in
mind when copying entities: 

-   There's no way to copy files---you can only copy folders. However, copying a 
    folder also copies its contents, which can include files. 
-   Folders can only be copied within their current repository. 

The move operation doesn't have these restrictions. It's possible to move files 
and folders between different repositories. In general, however, the move 
operation is a bit more complicated than the copy operation. For example, the 
API's behavior changes depending on whether you move entities to a different 
repository or within the same one. 

The tutorials in this section cover these differences, and more. 


