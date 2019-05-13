# Auto Tagging Images [](id=auto-tagging-images)

[Tags](/discover/portal/-/knowledge_base/7-2/tagging-content) 
let you find and organize files, including images. With 
[asset auto-tagging enabled](/discover/portal/-/knowledge_base/7-2/auto-tagging-assets), 
@product@ by default automatically 
[tags](/discover/portal/-/knowledge_base/7-2/tagging-content) 
images uploaded to the Documents and Media Library. This lets you use tags 
without requiring anyone to manually apply them. 

You can, however, change this default behavior so that image auto-tagging must 
be manually enabled for each instance you want to use it in. You can also change 
how @product@ automatically tags images. This article shows you how to do these 
things. 

+$$$

**Note:** By default, image auto-tagging in @product@ is based on 
[TensorFlow's `LabelImage` sample for Java](https://github.com/tensorflow/tensorflow/blob/master/tensorflow/java/src/main/java/org/tensorflow/examples/LabelImage.java), 
and uses the Inception5h model. You can, however, use Google Cloud Vision or 
Microsoft Cognitive Services for auto-tagging instead. See 
[Additional Auto-tagging Providers](#additional-auto-tagging-providers) 
for more information. 

$$$

+$$$

**Note:** Tags applied automatically are currently English only. 

$$$

![Figure 1: The tags *freight car* and *electric locomotive* were automatically applied to this image.](../../../images/auto-tagging-images.png)

## Configuring Image Auto-tagging [](id=configuring-image-auto-tagging)

By default, image auto-tagging is enabled in any instance that has asset 
auto-tagging enabled. For instructions on enabling asset auto-tagging, see 
[the article on auto-tagging assets](/discover/portal/-/knowledge_base/7-2/auto-tagging-assets). 
You can, however, change this default behavior. You can also configure how 
@product@ automatically applies the tags. To do so, follow these steps: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*, then 
    select *Documents and Media*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select *TensorFlow Image Auto Tagging*. The 
    following settings are available: 

    -   **Enabled:** Whether image auto-tagging is enabled by default in any 
        instance that has asset auto-tagging enabled. Note that you can override 
        this value for specific instances, as the next section shows. 

    -   **Confidence Threshold:** TensorFlow assigns a confidence level between 
        0 and 1 for each tag, where 1 is the highest confidence and 0 is the 
        lowest. This field sets the minimum confidence level that TensorFlow 
        needs to apply a tag. Higher values yield fewer tags because TensorFlow 
        needs more confidence before it applies a tag. Likewise, lower values 
        yield more tags. You can override this value for specific instances, as 
        the next section shows. 

3.  Click *Save* to save your changes. 

![Figure 2: Configure image auto-tagging for the instances in your portal.](../../../images/auto-tagging-image-settings.png)

### Overriding Image Auto-tagging Settings for Specific Instances [](id=overriding-image-auto-tagging-settings-for-specific-instances)

You can also override the above image auto-tagging settings for specific 
instances. Follow these steps to do so: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings*, 
    then select the *Asset Auto Tagging* tab. 

2.  Expand the section *TensorFlow Image Auto Tagging* and configure the 
    settings as desired. 

3.  Click *Save* to save your changes. 

![Figure 3: Configure image auto-tagging for a specific instance.](../../../images/auto-tagging-image-instance.png)

## Additional Auto-tagging Providers [](id=additional-auto-tagging-providers)

TensorFlow is the default image auto-tagging provider. @product@, however, 
contains two additional such providers. You can configure them in the same 
places that you configure the TensorFlow provider, as the previous sections 
describe: 

-   **Google Cloud Vision:** This provider uses the 
    [Google Cloud Vision API](https://cloud.google.com/vision/) 
    to automatically tag images. You must provide a valid API key. For more 
    information, see 
    [Google's documentation on API keys](https://cloud.google.com/docs/authentication/api-keys). 

    ![Figure 4: The Google Cloud Vision provider requires an API key.](../../../images/auto-tagging-image-google.png)

-   **Microsoft Cognitive Services:** This provider uses 
    [Microsoft Cognitive Services](https://azure.microsoft.com/en-us/services/cognitive-services/) 
    to automatically tag images. Note that you must provide a valid 
    [API key](https://azure.microsoft.com/en-us/try/cognitive-services/my-apis/?apiSlug=computer-services) 
    and an endpoint. For more information, see the 
    [Microsoft Cognitive Services documentation](https://docs.microsoft.com/en-us/azure/cognitive-services/). 

    ![Figure 5: The Microsoft Cognitive Services provider requires an API key and an endpoint.](../../../images/auto-tagging-image-microsoft.png)
