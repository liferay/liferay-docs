---
header-id: sync-the-cache-with-the-server
---

# キャッシュをサーバーと同期する

[TOC levels=1-4]

オフラインモードをサポートする書き込みスクリーンレットを使用する場合、キャッシュに書き込まれる新しいデータもサーバーと同期する必要があります。 Liferay Screensに含まれる書き込みスクリーンレットがこれを行います。 ただし、カスタム書き込みスクリーンレットを使用する場合は、これを手動で行う必要があります。 これは、スクリーンレットを使用するアクティビティまたはフラグメントで行う必要があります。ただし、このアクティビティまたはフラグメントの正確な場所はユーザー次第です。

| **注：** 読み取りにオフラインモードのサポートを追加する場合、これを行う必要はありません。スクリーンレット。

書き込みScreenletのデータをサーバーと手動で同期するには、次の手順を実行します。

1.  サーバーと同期する必要があるイベントを取得します。 これを行うには、まずイベントに関連付けられたキャッシュキーを取得する必要があります。 次に、キーを `Cache.getObject` メソッドの引数として使用します。
2.  イベントでインタラクターを呼び出します。 これにより、データがサーバーと同期されます。

たとえば、次のコードは `Cache.findKeys` メソッドを使用して、キャッシュ内の `BookmarkEvent` キーをすべて取得します。 その後のループは、各キーに対応するイベントを取得し、Interactorを呼び出してサーバーに同期します。

    String[] keys = Cache.findKeys(BookmarkEvent.class, groupId, userId, locale, 0, 
        Integer.MAX_VALUE); 
    for (String key : keys) {
    
        BookmarkEvent event = Cache.getObject(BookmarkEvent.class, groupId, userId, key); 
        new AddBookmarkInteractor().execute(event); 
    }

Screenletクラスでキャッシュキーを設定しないことを選択した場合、キーの代わりに `null` を渡すことができます。

キャッシュキーを保存および取得する代替方法として、Androidの `SharedPreferences` APIを使用できることにも注意してください。 たとえば、次のコードは共有設定にキャッシュキーを保存します。

    SharedPreferences sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE); 
    HashSet<String> values = new HashSet<>();
    sharedPreferences.edit().putStringSet("keysToSync", values).apply();

その後、共有設定から他のキーと値のセットを取得するのと同じようにキーを取得できます。

    SharedPreferences sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE); 
    HashSet<String> keysToSync = sharedPreferences.getStringSet("keysToSync", new HashSet<>());

## 関連トピック

[イベントクラスを作成または更新する](/docs/7-1/tutorials/-/knowledge_base/t/create-or-update-the-event-class)

[リスナーを更新する](/docs/7-1/tutorials/-/knowledge_base/t/update-the-listener)

[インタラクタークラスを更新する](/docs/7-1/tutorials/-/knowledge_base/t/update-the-interactor-class)

[スクリーンレットクラスを更新する](/docs/7-1/tutorials/-/knowledge_base/t/update-the-screenlet-class)
