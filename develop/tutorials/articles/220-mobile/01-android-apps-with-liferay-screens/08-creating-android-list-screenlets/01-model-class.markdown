# Creating the Model Class [](id=creating-the-model-class)

Entities come back from Liferay in JSON. To work with these results efficiently 
in your app, you must convert them to model objects that represent the entity in 
Liferay. Although Screens's 
[`BaseListInteractor`](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) 
transforms the JSON entities into `Map` objects for you, you still must 
convert these into proper entity objects for use in your app. You'll do this via 
a model class. 

For example, Bookmark List Screenlet's model class (`Bookmark`) creates 
`Bookmark` objects that contain a bookmark's URL and other data. To ensure quick 
access to the URL, the constructor that takes a `Map<String, Object>` extracts 
it from the `Map` and sets it to the `url` variable. To allow access to any 
other data, the same constructor sets the entire `Map` to the `values` variable. 
Besides the getters and setter, the rest of this class implements 
[Android's `Parcelable` interface](https://developer.android.com/reference/android/os/Parcelable.html): 

    import android.os.Parcel;
    import android.os.Parcelable;

    import java.util.Map;

    public class Bookmark implements Parcelable {

        private String url;
        private Map values;

        public static final Creator<Bookmark> CREATOR = new Creator<Bookmark>() {
            @Override
            public Bookmark createFromParcel(Parcel in) {
                return new Bookmark(in);
            }

            @Override
            public Bookmark[] newArray(int size) {
                return new Bookmark[size];
            }
        };

        public Bookmark() {
            super();
        }

        protected Bookmark(Parcel in) {
            url = in.readString();
        }

        public Bookmark(Map<String, Object> stringObjectMap) {
            url = (String) stringObjectMap.get("url");
            values = stringObjectMap;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public String getUrl() {
            return url;
        }

        public Map getValues() {
            return values;
        }

        public void setValues(Map values) {
            this.values = values;
        }

    }

Now that you have your model class, you can create your Screenlet's View. 

## Related Topics [](id=related-topics)

[Creating the View](/develop/tutorials/-/knowledge_base/7-1/creating-the-view)

[Creating the Interactor](/develop/tutorials/-/knowledge_base/7-1/creating-the-interactor-0)

[Creating the Screenlet Class](/develop/tutorials/-/knowledge_base/7-1/creating-the-screenlet-class-0)
