---
header-id: using-the-list-screenlet
---

# リストスクリーンレットの使用

[TOC levels=1-4]

これで、新しいリストScreenlet [他のScreenlet](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)と同じ方法で使用できます。

1.  スクリーンレットを使用するアクティビティまたはフラグメントのレイアウトにスクリーンレットのXMLを挿入します。 たとえば、ブックマークリストスクリーンレットのXMLは次のとおりです。

    ``` 
     <com.liferay.mobile.screens.listbookmark.BookmarkListScreenlet
         android:id="@+id/bookmarklist_screenlet"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         app:comparator="FULLY_QUALIFIED_COMPARATOR_CLASS"
         app:folderId="YOUR_FOLDER_ID"
         app:groupId="YOUR_GROUP_ID"
         app:layoutId="@layout/list_bookmarks"/>
    ```

    コンパレータを設定するには、完全修飾クラス名を使用する必要があることに注意してください。 たとえば、ブックマークポートレットの `EntryURLComparator`</a>

に使用するには、次のようにScreenlet XMLで `app：comparator` 設定します。 



    ``` 
     app:comparator="com.liferay.bookmarks.util.comparator.EntryURLComparator"
    ```
</p></li> 

2  アクティビティクラスまたはフラグメントクラスにScreenletのリスナーを実装します。 リストScreenletにカスタムリスナーがない場合は、モデルクラスでパラメーター化された `BaseListListener` 実装することでこれを行うことができます。 たとえば、 
  
  

    ``` 
     public class YourListActivity extends AppCompatActivity 
         implements BaseListListener<YourModelClass> {...
    ```


ただし、リストScreenletのカスタムリスナーを作成した場合は、代わりにアクティビティまたはフラグメントでそれを実装する必要があります。 たとえば、ブックマークリストスクリーンレットのリスナーの例は `BookmarkListListener`であることを思い出してください。 したがって、このスクリーンレットを使用するには、スクリーンレットを使用するアクティビティまたはフラグメントのクラスにこのリスナーを実装する必要があります。 たとえば、 



    ``` 
     public class ListBookmarksActivity extends AppCompatActivity 
         implements BookmarkListListener {...
    ```


GitHub</a>この 完全な例を参照してください。</p></li> </ol> 

よくやった\！ これで、リストスクリーンレットの作成方法がわかりました。



## 関連トピック

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)
