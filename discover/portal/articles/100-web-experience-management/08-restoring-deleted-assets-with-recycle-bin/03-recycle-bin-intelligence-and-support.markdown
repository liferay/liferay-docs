# Recycle Bin Intelligence and Support [](id=recycle-bin-intelligence-and-support)

Have you ever wondered what happens to file shortcuts if their linked assets 
are recycled? What if you restore a file that has the same name as another file 
currently stored in your site/instance? The Recycle Bin already knows how to 
handle these types of issues.

When documents with shortcuts are moved to the Recycle Bin, the shortcuts are
removed. This ensures that all your links and shortcuts work and cuts down on 
maintenance time and backtracking.

Another important trait is the duplicate name recognition feature. When a file
is restored, the Recycle Bin scans the corresponding asset type files currently
in the site/instance to check for duplicate file names. If a duplicate file name
is found, the Recycle Bin prompts you to overwrite the existing file or rename
the file name you're trying to restore. 

For example, suppose you have the document `file1` stored in the Recycle Bin and
you have a separate document you created later with the same name in the
document library. If you try to restore the `file1` document, the Recycle Bin
recognizes duplicate names and prompts you to overwrite the existing document
in the document library or rename the document you're trying to restore.

![Figure 1: The Recycle Bin always scans your site/instance for duplicate file names during the restoration process.](../../../images/recycle-bin-duplicate-name.png)

Although the Recycle Bin prohibits the restoration of files that match
pre-existing file names in your site/instance, it stores files with matching
names.

## The Staging Recycle Bin [](id=the-staging-recycle-bin)

Although you there is only one master Recycle Bin for all asset types, when 
staging is enabled a *Staging* Recycle Bin is created. The original Recycle 
Bin, or *Live* Recycle Bin, is still viewable while in staging; however, it is 
never used.

During staging, everything you recycle is sent to the Staging Recycle Bin. This
prevents staged and unstaged recycled content from mixing. For example, if you 
have an unstaged document currently on your live site you can enable staging 
and delete that document. If you were to turn staging off and return to the 
live site, without separate Recycle Bins, the live document would be both on
your site and in the Recycle Bin! Because of this, the separate Staging Recycle
Bin is necessary and only used during the staging process. When you publish your
staged material, the Staging Recycle Bin content is transferred to the Live
Recycle Bin.

+$$$

**Note:** The Staging Recycle Bin saves its contents until the staged material
has been published to the live site. This means that you can turn the staging
mode on and off without losing your recycled material.

$$$

The Recycle Bin saves you time by letting you restore content that's been
recycled. Instead of recreating or re-uploading content, you'll be tailoring
your Liferay instance to fully leverage its capabilities.
