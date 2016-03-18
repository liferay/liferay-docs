# Getting Started with Get Guestbooks Screenlet [](id=getting-started-with-get-guestbooks-screenlet)

Before creating a Screenlet, you need to know where you'll use it. If you only 
plan to use it in one app, then you can create it in that app's project. If you 
need to use it in several apps, however, then it's best to create it in a 
separate project for redistribution. For information on creating Screenlets for 
redistribution, see the tutorial 
[Packaging Your Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/packaging-your-android-screenlets).
Since you'll use the Get Guestbooks Screenlet only in this app, you can create 
the Screenlet in a new package inside the app's project. 

Right click the *java* folder in Android Studio's project view and select 
*New* &rarr; *Package*. Select *.../app/src/main/java* as the destination 
directory, and click *OK*. Then enter `com.liferay.docs.getguestbooksscreenlet` 
as the package's name and click *OK*. Android Studio now lists the new package 
alongside the package containing the app's activity and fragment 
(`liferayguestbook`). If it doesn't appear at first, you may need to collapse 
and reopen the *java* folder in project view. 

![Figure 1: The new package for the Get Guestbooks Screenlet is highlighted.](../../images/android-guestbooks-screenlet-package.png)

Next, you'll create the model class the Screenlet needs. 

## Creating the Model Class for Guestbooks [](id=creating-the-model-class-for-guestbooks)

The Guestbook Mobile SDK returns guestbooks from the portlet in a `JSONArray` 
that contains each guestbook in a `JSONObject`. To work efficiently with these 
guestbooks, you need a way of transforming them into proper guestbook objects.
You'll do this via a model class that creates `GuestbookModel` objects. 

You'll create this model class in a separate package outside of the 
`getguestbooksscreenlet` package. In this case, it makes sense to organize your 
code this way because additional Screenlets you create may also use the model 
class. For example, if you created a Screenlet for editing a guestbook, it would 
also need to use `GuestbookModel` objects. Putting the model class in a separate 
package makes it clear that this class doesn't belong exclusively to a single 
Screenlet. 

First, create a new package called `model` inside the `com.liferay.docs` 
package. Inside this new `model` package, create a new class called 
`GuestbookModel`. Replace the `GuestbookModel` class's contents with the 
following code: 

    package com.liferay.docs.model;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.Serializable;
    import java.util.Date;

    public class GuestbookModel implements Serializable {

        private long _guestbookId;
        private long _groupId;
        private long _companyId;
        private long _userId;
        private String _userName;
        private long _createDate;
        private long _modifiedDate;
        private String _name;

        public GuestbookModel(JSONObject json) throws JSONException {
            _guestbookId = json.getLong("guestbookId");
            _groupId = json.getLong("groupId");
            _companyId = json.getLong("companyId");
            _userId = json.getLong("userId");
            _userName = json.getString("userName");
            _createDate = json.getLong("createDate");
            _modifiedDate = json.getLong("modifiedDate");
            _name = json.getString("name");
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof GuestbookModel)) {
                return false;
            }

            GuestbookModel guestbook = (GuestbookModel) obj;

            return (_guestbookId == guestbook.getGuestbookId());
        }

        @Override
        public String toString() {
            return _name;
        }

        public long getGuestbookId() {
            return _guestbookId;
        }

        public long getGroupId() {
            return _groupId;
        }

        public long getCompanyId() {
            return _companyId;
        }

        public long getUserId() {
            return _userId;
        }

        public String getUserName() {
            return _userName;
        }

        public Date getCreateDate() {
            Date createDate = new Date(_createDate);
            return createDate;
        }

        public Date getModifiedDate() {
            Date modifiedDate = new Date(_modifiedDate);
            return modifiedDate;
        }

        public String getName() {
            return _name;
        }
    }

This class creates `GuestbookModel` objects that effectively represent 
`Guestbook` objects in the portlet. It does so by retrieving `Guestbook` 
parameters from the `JSONObject` returned by the Mobile SDK's remote service 
calls. The constructor does this by using the `getLong` and `getString` methods. 
To see how the `Guestbook` parameters are defined in the portlet, see the 
[Liferay MVC Learning Path article on Service Builder](/develop/learning-paths/mvc/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr). 
For now, the only parameters you really need in this class are `guestbookId` and 
`name`. Because you might need the rest later, however, it's best to add 
support for all of them now. 

You should also note the `toString` method in this class. It's very simple; it
only returns a guestbook's name. It's very important, though. Android calls
`toString` to render objects in the Screenlet. If `toString` isn't defined for 
an object, the Screenlet shows strings with the object's full package path and 
internal ID. In other words, the Screenlet displays illegible text if you don't 
define `toString` here. By defining `toString` to return each `GuestbookModel`'s 
name, you're telling the Screenlet to show each guestbook's name. 

Next, you need to create the Screenlet's UI. 
