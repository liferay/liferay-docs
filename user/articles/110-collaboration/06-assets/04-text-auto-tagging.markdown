---
header-id: auto-tagging-text
---

# Auto Tagging Text

[TOC levels=1-4]

With 
[asset auto tagging enabled](/docs/7-2/user/-/knowledge_base/u/configuring-asset-auto-tagging), 
you can also configure text auto tagging. Text auto tagging automatically 
[tags](/docs/7-2/user/-/knowledge_base/u/tagging-content) 
text-based assets. This lets you use tags without requiring anyone to manually
apply them. 

| **Note:** Currently, text auto tagging is only available for text-based 
| documents and web content. Tags applied automatically are English only. 

Text auto tagging is disabled by default. To use it, you must enable it: 

1.  Ensure that 
    [asset auto tagging](/docs/7-2/user/-/knowledge_base/u/configuring-asset-auto-tagging) 
    is enabled. Although it's enabled by default, administrators can disable it. 

2.  Ensure that a text auto tagging provider is configured. These providers
    aren't configured by default. Here, you'll learn how to configure them. 

3.  For the asset type you want to tag automatically (e.g., documents, web 
    content), enable the text auto-tagging provider that you configured in step 
    two. This step is covered in separate articles on text auto tagging for 
    specific asset types: 

    -   [Auto Tagging Documents](/docs/7-2/user/-/knowledge_base/u/auto-tagging-documents)
    -   [Auto Tagging Web Content](/docs/7-2/user/-/knowledge_base/u/auto-tagging-web-content)

There are two text auto-tagging providers in the portal: 

**Google Cloud Natural Language Text Auto Tagging:** Uses the 
[Google Cloud Natural Language API](https://cloud.google.com/natural-language/) 
to analyze and automatically tag portal content. 

**OpenNLP Text Auto Tagging:** Uses the open source 
[Apache OpenNLP](https://opennlp.apache.org/) 
library to analyze and automatically tag portal content. Three models are used: 
location name finder, organization finder, and person name finder. Use this 
provider with caution, as its accuracy may be limited. 

## Configuring Google Cloud Natural Language Text Auto Tagging

Follow these steps to configure the auto-tagging provider for the Google Cloud 
Natural Language API: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
    *Assets*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select 
    *Google Cloud Natural Language Text Auto Tagging*. The following settings 
    are available: 

    **API Key:** The API key to use for the Google Cloud Natural Language API. 
    For more information, see 
    [Google's documentation on API keys](https://cloud.google.com/docs/authentication/api-keys). 

    **Classification Endpoint Enabled:** Whether to enable auto tagging of text 
    using the Google Cloud Natural Language API Classification endpoint. 

    **Confidence:** Set the classifier's confidence of the category. This number 
    represents how certain the classifier is that this category represents the 
    given text. 

    **Entity Endpoint Enabled:** Whether to enable auto tagging of text using 
    the Google Cloud Natural Language API Entity endpoint. 

    **Salience:** The salience score for an entity provides information about 
    the importance or centrality of that entity to the entire text. 

3.  Click *Save* to save your changes. 

You can override these settings for each instance from *Control Panel* &rarr; 
*Configuration* &rarr; *Instance Settings* &rarr; *Assets*. 

![Figure 1: Configure Google Cloud Natural Language text auto tagging for your portal instances.](../../../images/auto-tagging-text-google.png)

## Configuring OpenNLP Text Auto Tagging

Follow these steps to configure the OpenNLP Text Auto Tagging provider: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
    *Assets*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select *OpenNLP Text Auto Tagging*. The 
    following settings are available: 

    **Confidence Threshold:** Set the minimum confidence threshold (from 0 to 1, 
    where 1 is the highest confidence) above which tags will be applied. Higher 
    values yield fewer tags because the provider needs more confidence before it 
    applies a tag. Likewise, lower values yield more tags. 

    **Enable OpenNLP Text Auto Tagging:** Whether to enable OpenNLP text auto 
    tagging for every portal instance. 

3.  Click *Save* to save your changes. 

You can override these settings for each instance from *Control Panel* &rarr; 
*Configuration* &rarr; *Instance Settings* &rarr; *Assets*. 

![Figure 2: Configure OpenNLP text auto tagging for your portal instances.](../../../images/auto-tagging-text-open-nlp.png)
