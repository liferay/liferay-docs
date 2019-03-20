# Tracking Custom Assets [](id=tracking-custom-assets)

[Liferay Analytics Cloud](https://help.liferay.com/hc/en-us/categories/360000608871-Liferay-Analytics-Cloud-Admin-Guide) 
can detect and analyze built-in Liferay DXP assets like Forms, Blogs, Documents 
and Media, and Web Content. To analyze assets in your custom app, however, you 
must tag your app's HTML so the Analytics Cloud JavaScript plugin can detect and 
track user interaction with your assets. Here, you'll learn how. 

## Asset Events [](id=asset-events)

The Analytics Cloud JavaScript plugin contains the following events that you can 
track: 

**AssetClicked:** User clicks the asset area. Also carries information about the 
tag clicked. 

**AssetDepthReached:** Scroll event in the asset area. Also carries information 
about the content depth the user reached (e.g., how far down a blog post the 
user scrolled). 

**AssetViewed:** User views the asset. 

**AssetDownloaded:** User clicks a link that downloads the asset. 

**AssetSubmitted:** Form submission in the asset area. This requires an input 
type of `submit` to be placed under an HTML `form` element. 

## Required Metadata [](id=required-metadata)

You must have the following information to enable tracking for a custom entity. 
You must specify this information in HTML via the attributes listed: 

**Asset Type (String):** The asset type to track. The HTML attribute for this is 
`data-analytics-asset-type`. Note that the value for this attribute **is not** 
your entity's exact type. For custom entities, this value is always `custom`. 

**Asset ID (String):** The asset's unique identifier. The HTML attribute for 
this is `data-analytics-asset-id`. 

**Asset Category (String, Optional):** The category of the custom app that 
contains the asset. The HTML attribute for this is 
`data-analytics-asset-category`. You can use this to identify the custom app by 
name (e.g., "polls"). Note that within a category, all asset IDs must be unique. 

**Asset Title (String, Optional):** The asset's title. The HTML attribute for 
this is `data-analytics-asset-title`. 

## Tracking Asset Events [](id=tracking-asset-events)

For example, imagine that you want to track a poll in a custom Polls app. Here's 
the example HTML for such a poll: 

    <div>
    <h1> What's your favorite food? </h1>
    <form action="/submit.php">
    <div>
      <input type="radio" id="sushi" name="food" value="Sushi"
         checked>
      <label for="sushi">Sushi</label>
    </div>
    <div>
      <input type="radio" id="pizza" name="food" value="Pizza">
      <label for="pizza">Pizza</label>
    </div>
    <input type="submit" value="Submit" />
    </form>
    </div>

To track this poll's events, add the above attributes to the `div`: 

    <div data-analytics-asset-type="custom"
      data-analytics-asset-id="favorite-food-poll"
      data-analytics-asset-category="polls"
      data-analytics-asset-title="What is your favorite food Poll">

    <h1> What's your favorite food? </h1>
    ...
    </div>

Note that you must add these attributes to each individual asset that you want 
Analytics Cloud to track. However, you can populate the attributes' values via a 
script, therefore automating this process for each asset. 

### Tracking Downloads [](id=tracking-downloads)

To track downloads, you must tag the element that triggers the action with this 
attribute:

    data-analytics-asset-action="download"

For example, here's the above poll with a download link for a PDF file that 
contains the poll's instructions: 

    <div data-analytics-asset-type="custom"
      data-analytics-asset-id="favorite-food-poll"
      data-analytics-asset-category="polls"
      data-analytics-asset-title="What is your favorite food Poll">

    <a href="/poll-instructions.pdf" data-analytics-asset-action="download">Download the Poll Instructions </a>

    <h1> What's your favorite food? </h1>
    ...
    </div>

