# Getting Started with Guestbook List Screenlet

Before creating a Screenlet, you should know where you'll use it. If you only 
plan to use it in one app, then you can create it in that app's project. If you 
need to use it in several apps, however, then it's best to create it in a 
separate project for redistribution. For information on creating Screenlets for 
redistribution, see the tutorial 
[Packaging Your Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets). 
Since you'll use the Guestbook List Screenlet only in this app, you can create 
the Screenlet in a new package inside the app's project. 

Right click the *java* folder in Android Studio's project view and select 
*New* &rarr; *Package*. Select *.../app/src/main/java* as the destination 
directory, and click *OK*. Then enter `com.liferay.docs.guestbooklistscreenlet` 
as the package's name and click *OK*. Android Studio now lists the new package 
alongside the package containing the app's activity and fragment 
(`liferayguestbook`). If it doesn't appear at first, you may need to collapse 
and reopen the *java* folder in project view. 

![Figure 1: The new package for the Guestbook List Screenlet is highlighted.](../../../images/android-guestbooks-screenlet-package.png)
<!-- Replace screenshot -->

Next, you'll create the model class the Screenlet needs. 

## Creating the Model Class for Guestbooks

The Guestbook Mobile SDK returns guestbooks from the portlet in a `JSONArray` 
that contains each guestbook in a `JSONObject`. Although the list Screenlet 
framework's 
[`BaseListCallback` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListCallback.java) 
automatically transforms these JSON entities into `Map` objects for you, you 
must still create a model class that converts these `Map` objects into proper 
guestbook objects. This gives you an efficient way to work with guestbooks 
throughout the Screenlet and in any app that uses it. The model class you'll 
create--`GuestbookModel`--creates `GuestbookModel` objects that serve as these 
guestbook objects. 

You'll create this model class in a separate package outside of the 
`guestbooklistscreenlet` package. In this case, it makes sense to organize your 
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

    import android.os.Parcel;
    import android.os.Parcelable;

    import java.util.Date;
    import java.util.Map;

    public class GuestbookModel implements Parcelable {

        private Map _values;
        private long _guestbookId;
        private long _groupId;
        private long _companyId;
        private long _userId;
        private String _userName;
        private long _createDate;
        private long _modifiedDate;
        private String _name;

        public static final Creator<GuestbookModel> CREATOR = new Creator<GuestbookModel>() {
            @Override
            public GuestbookModel createFromParcel(Parcel in) {
                return new GuestbookModel(in);
            }

            @Override
            public GuestbookModel[] newArray(int size) {
                return new GuestbookModel[size];
            }
        };

        protected GuestbookModel(Parcel in) {
            _guestbookId = in.readLong();
            _groupId = in.readLong();
            _companyId = in.readLong();
            _userId = in.readLong();
            _userName = in.readString();
            _createDate = in.readLong();
            _modifiedDate = in.readLong();
            _name = in.readString();
        }

        public GuestbookModel(Map<String, Object> stringObjectMap) {
            _values = stringObjectMap;
            _guestbookId = Long.parseLong((String) stringObjectMap.get("guestbookId"));
            _groupId = Long.parseLong((String) stringObjectMap.get("groupId"));
            _companyId = Long.parseLong((String) stringObjectMap.get("companyId"));
            _userId = Long.parseLong((String) stringObjectMap.get("userId"));
            _userName = (String) stringObjectMap.get("userName");
            _createDate = (long) stringObjectMap.get("createDate");
            _modifiedDate = (long) stringObjectMap.get("modifiedDate");
            _name = (String) stringObjectMap.get("name");
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(_guestbookId);
            dest.writeLong(_groupId);
            dest.writeLong(_companyId);
            dest.writeLong(_userId);
            dest.writeString(_userName);
            dest.writeLong(_createDate);
            dest.writeLong(_modifiedDate);
            dest.writeString(_name);
        }

        @Override
        public int describeContents() {
            return 0;
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

        public Map getValues() {
            return _values;
        }

        public void setValues(Map values) {
            _values = values;
        }
    }

This class creates `GuestbookModel` objects that effectively represent the 
`Guestbook` objects from the Guestbook portlet. The constructor that takes the 
`Map<String, Object>` as an argument does the heavy lifting here. This `Map` is 
automatically returned by the list Screenlet framework's `BaseListCallback` 
class following the Mobile SDK call, and contains the data of a guestbook 
retrieved from the portlet. To retrieve the guestbook's data from the `Map`, the 
constructor uses the `get` method with each parameter of the portlet's 
`Guestbook` objects. To see how these `Guestbook` parameters are defined in the 
portlet, see the 
[Liferay MVC Learning Path article on Service Builder](/develop/tutorials/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr). 
For now, the only parameters you really need in `GuestbookModel` are 
`guestbookId` and `name`. Because you might need the rest later, however, it's 
best to add support for all of them now. 

Besides the getters and setter, the remaining code in `GuestbookModel` 
implements Android's `Parcelable` interface. For more information on this, see 
[Android's documentation on `Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html). 

Great! Now you have a model class capable of creating `GuestbookModel` objects. 
Now you're ready to create the Screenlet's UI. 
