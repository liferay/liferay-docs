---
header-id: accessing-your-custom-finder-method-from-the-service-layer
---

# サービスレイヤーからカスタムFinderメソッドにアクセスする

[TOC levels=1-4]

これまでに、 `* FinderImpl` クラスを作成し、そのクラスで `findBy *` finderメソッドを定義し、動的クエリを使用してfinderメソッドを実装しました。 さて、サービスレイヤーからファインダーメソッドをどのように呼び出しますか？

Service Builderを実行すると、 `* Finder` インターフェイスが生成されました（たとえば、 `GuestbookFinder`）。 懸念事項を適切に分離するには、サービスモジュールのローカルまたはリモートサービス実装（つまり、 `* LocalServiceImpl` または `* ServiceImpl`）のみが `* Finder` クラスを呼び出す必要があります。 アプリケーションのWebモジュールのポートレットクラスは、アプリケーションのサービスモジュールから公開されたサービスのビジネスロジックを呼び出します。 サービスは、永続層のファインダークラスを使用してデータモデルにアクセスします。

| **注：** Liferayポータルの以前のバージョンでは、ファインダーメソッドは次のとおりでした。 `* FinderUtil` ユーティリティクラスからアクセス可能。 Finderメソッドが注入されるようになりました|アプリのローカルサービスに追加し、ファインダーユーティリティを呼び出す必要をなくします。

`* LocalServiceImpl` クラスにメソッドを追加し、 `* Finder` クラスを介してファインダーメソッドの実装を呼び出します。 次に、Webモジュール内のポートレットクラスとJSPがサービスにアクセスできるように、アプリケーションのサービスレイヤーを再構築します。

たとえば、Guestbookアプリケーションの場合、次のメソッドを `EntryLocalServiceImpl` クラスに追加します。

    public List<Entry> findByEntryNameGuestbookName(String entryName,
        String guestbookName) throws SystemException {
    
        return entryFinder.findByEntryNameGuestbookName(String entryName,
            String guestbookName);
    }

`findBy *` メソッドを `* LocalServiceImpl` クラスに追加したら、Service Builderを実行してインターフェイスを生成し、 `EntryLocalService` クラスでファインダーメソッドを使用できるようにします。

これで、 `_entryLocalService.findByEntryNameGuestbookName（...）`\！を呼び出すことで、ポートレットクラスまたはJSPからファインダーメソッドを間接的に呼び出すことができます。

カスタムファインダで動的クエリを開発し、それをポートレットのサービスとして公開するという3つのステップのプロセスに従っておめでとうございます！

## 関連トピック

[Service Builder Webサービス](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-web-services)

[ローカルサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-local-services)

[ローカルサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services)
