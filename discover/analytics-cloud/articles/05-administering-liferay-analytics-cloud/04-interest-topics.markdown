---
header-id: managing-interest-topics
---

# Managing Interest Topics

Analytics Cloud's 
[recommendation API](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/04-analyzing-touchpoints/09-content-recommendation-api.markdown) 
suggests topics of interest based on user interactions with content in Liferay 
DXP. Analytics Cloud administrators can exclude certain keywords from the 
recommendation API, therefore preventing Analytics Cloud from suggesting content 
based on those keywords. This is useful if the administrator considers a given 
topic too broad, too narrow, offensive, or otherwise not useful for suggesting 
content. 

Follow these steps to add a keyword to the block list: 

1.  In Analytics Cloud, select *Settings* &rarr; *Interest Topics*. A table 
    lists any existing keywords in the block list. 

2.  Click *Add Keyword* and enter one or more keywords in a comma-delimited 
    list. 

3.  Click *Send* when you're finished. Your keywords then appear in the table. 

To delete a keyword from the block list, click that keyword's trash icon 
(![Trash](../../images/icon-trash.png)). 
To delete multiple keywords at once, select the checkbox for each and click the 
trash icon that appears at the top of the table. You can select all keywords via 
the checkbox at the top of the table. 

![Figure 1: Cool beans aren't allowed.](../../images/interest-topics.png)

