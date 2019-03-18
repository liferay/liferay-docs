# Creating a Contributed Fragment Collection

To create a Contributed Fragment Collection, a developer must,

1.  Create a module which will contain the necessary logic and the fragments.

2.  Extend the class `BaseFragmentCollectionContributor` with all the logic for
    reading the contributed fragments.

3.  Add fragments as resources in the module.

Once you deploy the module, any fragments contained in it will be available for use. 



To better understand Contributed Fragment Collections, create one called `DemoFragmentCollectionContributor`.

## Create a Module

First you must create the module in your development environment. Follow the instructions in [Creating a Module](link).

## Create the Java class

Next, you need to create the Java package and class to handle the logic for the 
contributed collection:

1.  Create a package in your module named `com.liferay.fragment.collection.contributor.demo`

2.  Inside of that package, create a Java class named `DemoFragmentCollectionContributor` that extends `BaseFragmentCollectionContributor`.

3.  Above the class declaration, add the `@Component` annotation to set
    the service class:
    
        @Component(service = FragmentCollectionContributor.class)

4.  Create the variable for the servlet context:

    	private ServletContext _servletContext;

5.  Next, define the `getFragmentCollectionKey()` and `getServletContext()` 
    methods:
    
    	@Override
    	public String getFragmentCollectionKey() {
    		return "DEMO";
    	}

    	@Override
    	public ServletContext getServletContext() {
    		return _servletContext;
    	}
        
5.  Below that use the `@Reference` annotation to define your modules symbolic
    name:
    
    	@Reference(
    		target = "(osgi.web.symbolicname=com.liferay.fragment.collection.contributor.demo)"
    	)
        
6.  Organize your imports and save.

## Create the Resources

Next you need to include the fragments that you want to contribute in your 
module:

1.  In your module's `resources/` folder, create the folder structure `/com/liferay/fragments/collection/contributor/demo`.

2.  Copy the Fragments you want to distribute into the folder. You can learn 
    how to create a Fragments in the [Creating Fragments section](link).

3.  Create a file named `collections.json` in the same folder with this format:

        {
        	"fragments": [
        		"[fragment-1]",
        		"[fragment-2]",
        		"[fragment-3]",
                ...
        	],
        	"name": "[collection-name]"
        }
    
    If a fragment is not listed in `collections.json` it will not be available
    in the Contributed Collection, even if the files are included in the module.
    
You can also provide thumbnail images for reference for your fragments:

1.  Under `resources/META-INF/resources` create a folder named `thumbnails`.

2.  Copy thumbnail images into the folder with the format \[fragment-name\].png 
    for each fragment.

| **Note:** All fragments added through a Contributed Fragment Collection
| will be available globally to all Sites.

## Deploy the Contributed Fragment Collection

Now that you have created the necessary pieces of the module, you can build 
it and deploy it to Liferay. After it is deployed, the Fragments will be 
available for use. Contributed Fragments cannot be edited with Liferay, and can 
only be updated by editing the fragments in your module and the building and 
redeploying them.
