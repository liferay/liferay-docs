---
header-id: updating-your-service-layer
---

# サービスレイヤーの更新

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Guestbook Adminアプリの作成</p><p>手順3/5</p>
</div>

前のセクションでは、 `GuestbookLocalServiceImpl`で `addGuestbook` サービスメソッドを記述しましたが、使用しませんでした。 ゲストブックに対して完全な機能を使用するには、ゲストブックを更新および削除するメソッド、およびサイト内のゲストブックの数を返すメソッドも追加する必要があります。

## ゲストブックサービスメソッドの追加

Service Builderを使用する場合、 `* Impl` クラスでサービスを定義することに注意してください。 `* Impl` クラスのメソッドを追加、削除、またはメソッドのシグネチャを変更した後、Service Builderを実行する必要があります。 Service Builderは、影響を受けるインターフェイスおよびその他の生成されたコードを更新します。

次の手順に従って、必要なゲストブックサービスメソッドを追加します。

1.  `guestbook-service` プロジェクトに移動し、 `com.liferay.docs.guestbook.service.impl` パッケージの `GuestbookLocalServiceImpl.java` を開きます。 ゲストブックを更新するために次のメソッドを追加します。
   
        public Guestbook updateGuestbook(long userId, long guestbookId,
            String name, ServiceContext serviceContext) throws PortalException,
                        SystemException {
       
                Date now = new Date();
       
                validate(name);
       
                Guestbook guestbook = getGuestbook(guestbookId);
       
                User user = userLocalService.getUser(userId);
       
                guestbook.setUserId(userId);
                guestbook.setUserName(user.getFullName());
                guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
                guestbook.setName(name);
                guestbook.setExpandoBridgeAttributes(serviceContext);
       
                guestbookPersistence.update(guestbook);
       
                return guestbook;
        }

    `updateGuestbook` メソッドは、 `Guestbook` をそのIDで取得し、そのデータをユーザーが入力したものに置き換えてから、永続層を呼び出してデータベースに保存します。

2.  次に、ゲストブックを削除する次のメソッドを追加します。
   
        public Guestbook deleteGuestbook(long guestbookId,
                        ServiceContext serviceContext) throws PortalException,
                        SystemException {
       
                Guestbook guestbook = getGuestbook(guestbookId);
       
                List<Entry> entries = entryLocalService.getEntries(
                                serviceContext.getScopeGroupId(), guestbookId);
       
                for (Entry entry : entries) {
                        entryLocalService.deleteEntry(entry.getEntryId());
                }
       
                guestbook = deleteGuestbook(guestbook);
       
                return guestbook;
        }

    既存のエントリがあるゲストブックを削除した場合にどうなるかを考慮することが重要です。 ゲストブックを削除したばかりの場合、ゲストブックのエントリはデータベースに残っていますが、孤立しています。 `deleteGuestbook` サービスメソッドは、ゲストブックを削除する前にゲストブックのエントリを削除するサービス呼び出しを行います。 このようにして、ゲストブックのエントリが孤立することはありません。

3.  \ [CTRL \] + \ [SHIFT \] + Oを使用してインポートを更新し、 `GuestbookLocalServiceImpl.java`保存します。

4.  Liferay @ide@の右側のGradleタスクペインで、 `guestbook-service` モジュールを開き、 `buildService`をダブルクリックしてService Builderを実行します。

サービスレイヤーの更新が完了したので、Guestbook Adminポートレット自体を操作します。
