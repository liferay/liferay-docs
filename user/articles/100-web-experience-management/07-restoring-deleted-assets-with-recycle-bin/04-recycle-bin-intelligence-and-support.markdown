---
header-id: recycle-bin-intelligence-and-support
---

# Recycle Bin Intelligence and Support

[TOC levels=1-4]

Have you ever wondered what happens to file shortcuts if their linked assets 
are recycled? What if you restore a file that has the same name as another file 
currently stored in your site/instance? The Recycle Bin already knows how to 
handle these types of issues.

When documents with shortcuts are moved to the Recycle Bin, the shortcuts are
removed. This ensures that all your links and shortcuts work and cuts down on 
maintenance time and backtracking.

Another important trait how recycled content is managed with the
[Staging](/docs/7-2/user/-/knowledge_base/u/staging) framework.

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

| **Note:** The Staging Recycle Bin saves its contents until the staged material
| has been published to the live site. This means that you can turn the staging
| mode on and off without losing your recycled material.

The Recycle Bin saves you time by letting you restore content that's been
recycled. Instead of recreating or re-uploading content, you'll be tailoring
your Liferay instance to fully leverage its capabilities.
