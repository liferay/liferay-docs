---
header-id: auto-tagging-images
---

# Auto Tagging Images

[TOC levels=1-4]

[Tags](/docs/7-2/user/-/knowledge_base/u/tagging-content) 
help you find and organize files, including images. With 
[asset auto tagging enabled](/docs/7-2/user/-/knowledge_base/u/configuring-asset-auto-tagging), 
you can also enable image auto tagging. Image auto tagging automatically tags 
images uploaded to the Documents and Media Library. This lets you use tags 
without requiring anyone to apply them manually. 

| **Note:** Currently, tags applied automatically are English only. 

Image auto tagging is disabled by default. To use it, you must do two things: 

1.  Ensure that 
    [asset auto tagging](/docs/7-2/user/-/knowledge_base/u/configuring-asset-auto-tagging) 
    is enabled. Although it's enabled by default, administrators can disable it. 

2.  Ensure that an image auto tagging provider is enabled. These providers are
    disabled by default. Here, you'll learn how to enable/disable them. 

| **Note:** Prior to Liferay DXP Fix Pack 1 and Liferay Portal CE GA2, you must 
| configure the providers shown here in *Documents and Media* instead of 
| *Assets* (in System/Instance Settings). 

Three such providers are available: 

[**TensorFlow:**](#configuring-tensorflow-image-auto-tagging) 
An open-source library that provides machine learning capabilities. TensorFlow 
image auto-tagging in @product@ is based on 
[TensorFlow's `LabelImage` sample for Java](https://github.com/tensorflow/tensorflow/blob/master/tensorflow/java/src/main/java/org/tensorflow/examples/LabelImage.java), 
and uses the Inception5h model. Use this with caution, since its accuracy is 
limited. 

[**Google Cloud Vision:**](#configuring-google-cloud-vision) 
Uses the 
[Google Cloud Vision API](https://cloud.google.com/vision/) 
to automatically tag images. 

[**Microsoft Cognitive Services:**](#configuring-microsoft-cognitive-services) 
Uses 
[Microsoft Cognitive Services](https://azure.microsoft.com/en-us/services/cognitive-services/) 
to automatically tag images. 

![Figure 1: The tags *freight car* and *electric locomotive* were automatically applied to this image.](../../../images/auto-tagging-images.png)

## Configuring TensorFlow Image Auto Tagging

Follow these steps to configure TensorFlow Image Auto Tagging: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
    *Assets*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select *TensorFlow Image Auto Tagging*. The 
    following settings are available: 

    **Enable TensorFlow Image Auto Tagging:** Check this box to select whether
    image auto-tagging is enabled by default in any instance that has asset auto
    tagging enabled. Note that you can override this value for specific
    instances, as the next section shows. 

    **Confidence Threshold:** TensorFlow assigns a confidence level between 0 
    and 1 for each tag, where 1 is the highest confidence and 0 is the lowest. 
    This field sets the minimum confidence level that TensorFlow needs to apply 
    a tag. Higher values yield fewer tags because TensorFlow needs more 
    confidence before it applies a tag. Likewise, lower values yield more tags. 

3.  Click *Save* to save your changes. 

You can override these settings for each instance from *Control Panel* &rarr;
*Configuration* &rarr; *Instance Settings* &rarr; *Assets*. 

![Figure 2: Configure TensorFlow image auto-tagging for your portal instances.](../../../images/auto-tagging-tensorflow.png)

To optimize performance, you can also control the process that runs TensorFlow 
image auto tagging: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
    *Assets*. 

2.  Under *SYSTEM SCOPE*, select *TensorFlow Image Auto Tagging Process*. The 
    following settings are available: 

    **Maximum Number of Relaunches:** The maximum number of times the process is 
    allowed to crash before it is permanently disabled. 

    **Maximum Number of Relaunches Time Interval:** The time in seconds after 
    which the counter is reset. 

3.  Click *Save* to save your changes. 

![Figure 3: You can fine tune the process that runs the TensorFlow image auto tagging in the portal.](../../../images/auto-tagging-tensorflow-process.png)

## Configuring Google Cloud Vision

Follow these steps to configure Google Cloud Vision image auto tagging: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
    *Assets*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select 
    *Google Cloud Vision Image Auto Tagging*. The following settings are 
    available: 

    **API Key:** The API key to use for the Google Cloud Vision API. For more 
    information, see 
    [Google's documentation on API keys](https://cloud.google.com/docs/authentication/api-keys). 

    **Enabled:** Whether Google Cloud Vision image auto tagging is enabled. 

3.  Click *Save* to save your changes. 

You can override these settings for each instance from *Control Panel* &rarr; 
*Configuration* &rarr; *Instance Settings* &rarr; *Assets*. 

![Figure 4: The Google Cloud Vision provider requires an API key.](../../../images/auto-tagging-image-google.png)

## Configuring Microsoft Cognitive Services

Follow these steps to configure Microsoft Cognitive Services image auto tagging: 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
    *Assets*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select 
    *Microsoft Cognitive Services Image Auto Tagging*. The following settings 
    are available: 

    **API Key:** Your 
    [API key](https://azure.microsoft.com/en-us/try/cognitive-services/my-apis/?apiSlug=computer-services) 
    for the Computer Vision API V2. 

    **API Endpoint:** The endpoint for the Computer Vision API V2 (e.g., 
    `https://westcentralus.api.cognitive.microsoft.com/vision/v2.0`). 

    **Enabled:** Whether Microsoft Cognitive Services image auto tagging is 
    enabled. 

    For more information, see the 
    [Microsoft Cognitive Services documentation](https://docs.microsoft.com/en-us/azure/cognitive-services/).

3.  Click *Save* to save your changes. 

You can override these settings for each instance from *Control Panel* &rarr; 
*Configuration* &rarr; *Instance Settings* &rarr; *Assets*. 

![Figure 5: The Microsoft Cognitive Services provider requires an API key and an endpoint.](../../../images/auto-tagging-image-microsoft.png)
