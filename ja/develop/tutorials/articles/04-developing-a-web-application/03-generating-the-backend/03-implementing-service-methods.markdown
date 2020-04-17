---
header-id: implementing-service-methods
---

# サービスメソッドの実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">バックエンドの生成</p><p>手順3/3</p>
</div>

Service Builderを使用する場合は、サービスモジュールを利用してサービスを実装します。 アプリケーションのプロジェクトは[コンポーネント](/docs/7-1/tutorials/-/knowledge_base/t/fundamentals#components)であるため、Webモジュールからサービスレイヤーを参照できます。

`guestbook-service`モジュールの`GuestbookLocalServiceImpl`および `EntryLocalServiceImpl`で、それぞれゲストブックおよびエントリのサービスを実装します。

`GuestbookLocalServiceImpl`でゲストブックのサービスを実装するには、次の手順に従います。

1.  `com.liferay.docs.guestbook.service.impl`パッケージで、`GuestbookLocalServiceImpl`を開きます。 次に、この`addGuestbook`メソッドを追加します。
   
        public Guestbook addGuestbook(
            long userId, String name, ServiceContext serviceContext)
            throws PortalException {
       
            long groupId = serviceContext.getScopeGroupId();
       
            User user = userLocalService.getUserById(userId);
       
            Date now = new Date();
       
            validate(name);
       
            long guestbookId = counterLocalService.increment();
       
            Guestbook guestbook = guestbookPersistence.create(guestbookId);
       
            guestbook.setUuid(serviceContext.getUuid());
            guestbook.setUserId(userId);
            guestbook.setGroupId(groupId);
            guestbook.setCompanyId(user.getCompanyId());
            guestbook.setUserName(user.getFullName());
            guestbook.setCreateDate(serviceContext.getCreateDate(now));
            guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
            guestbook.setName(name);
            guestbook.setExpandoBridgeAttributes(serviceContext);
       
            guestbookPersistence.update(guestbook);
       
            return guestbook;
       
        }

    このメソッドは、データベースにゲストブックを追加します。 ユーザーから渡されたデータとともに、環境からメタデータ（現在のユーザーID、グループIDなど）を取得します。 このデータを検証し、それを使用して`Guestbook`オブジェクトを構築します。 このメソッドはこのオブジェクトをデータベースに保持し、オブジェクトを返します。 Service Builderはすでにモデルを生成しており、そのモデルをデータベースにマップするすべてのコードが生成されているため、ここではビジネスロジックのみを実装します。

2.  `Guestbook`オブジェクトを取得するためのメソッドを追加します。
   
        public List<Guestbook> getGuestbooks(long groupId) {
       
            return guestbookPersistence.findByGroupId(groupId);
        }
       
        public List<Guestbook> getGuestbooks(long groupId, int start, int end, 
            OrderByComparator<Guestbook> obc) {
       
            return guestbookPersistence.findByGroupId(groupId, start, end, obc);
        }
       
        public List<Guestbook> getGuestbooks(long groupId, int start, int end) {
       
            return guestbookPersistence.findByGroupId(groupId, start, end);
        }
       
        public int getGuestbooksCount(long groupId) {
       
            return guestbookPersistence.countByGroupId(groupId);
        }

    これらは、Service Builderで生成したFinderを呼び出します。 最初のメソッドは `groupId`で指定されたサイトから、ゲストブックのリストを取得します。 次の2つのメソッドは、オプションで特定の順序でページ番号を付けられたリストを取得します。 最後の方法では、特定のサイトのゲストブックの総数を取得できます。

3.  最後に、ゲストブックの認証メソッドを追加します。 
   
        protected void validate(String name) throws PortalException {
            if (Validator.isNull(name)) {
                throw new GuestbookNameException();
            }
        }

    このメソッドは@product@の`Validator`を使用して、ユーザーがゲストブック名のテキストを入力したことを確認します。

4.  \[CTRL\]+\[SHIFT\]+O を押してインポートを整理し、プロンプトが表示されたら次のクラスを選択します。

      - `java.util.Date`
      - `com.liferay.portal.kernel.service.ServiceContext`
      - `com.liferay.docs.guestbook.model.Entry`
      - `com.liferay.portal.kernel.util.Validator`

`EntryLocalServiceImpl`エントリにサービスを実装する準備ができました。 次の手順を実行してください。

1.  `com.liferay.docs.guestbook.service.impl`パッケージで、 `EntryLocalServiceImpl`を開きます。 この`addEntry`メソッドを追加します。
   
        public Entry addEntry(
            long userId, long guestbookId, String name, String email,
            String message, ServiceContext serviceContext)
            throws PortalException {
       
            long groupId = serviceContext.getScopeGroupId();
       
            User user = userLocalService.getUserById(userId);
       
            Date now = new Date();
       
            validate(name, email, message);
       
            long entryId = counterLocalService.increment();
       
            Entry entry = entryPersistence.create(entryId);
       
            entry.setUuid(serviceContext.getUuid());
            entry.setUserId(userId);
            entry.setGroupId(groupId);
            entry.setCompanyId(user.getCompanyId());
            entry.setUserName(user.getFullName());
            entry.setCreateDate(serviceContext.getCreateDate(now));
            entry.setModifiedDate(serviceContext.getModifiedDate(now));
            entry.setExpandoBridgeAttributes(serviceContext);
            entry.setGuestbookId(guestbookId);
            entry.setName(name);
            entry.setEmail(email);
            entry.setMessage(message);
       
            entryPersistence.update(entry);
       
            return entry;
        }

    `addGuestbook`メソッドと同様に、`addEntry`は、ユーザーが入力したデータとともに現在のコンテキストからデータを取得し、検証して、モデルオブジェクトを作成します。 そのオブジェクトはデータベースに保持され、返されます。

2.  この`updateEntry`メソッドを追加します。
   
        public Entry updateEntry (
            long userId, long guestbookId, long entryId, String name, String email,
            String message, ServiceContext serviceContext)
            throws PortalException, SystemException {
       
            Date now = new Date();
       
            validate(name, email, message);
       
            Entry entry = getEntry(entryId);
       
            User user = userLocalService.getUserById(userId);
       
            entry.setUserId(userId);
            entry.setUserName(user.getFullName());
            entry.setModifiedDate(serviceContext.getModifiedDate(now));
            entry.setName(name);
            entry.setEmail(email);
            entry.setMessage(message);
            entry.setExpandoBridgeAttributes(serviceContext);
       
            entryPersistence.update(entry);
       
            return entry;
        }

    このメソッドは、最初にエントリを取得しそのデータをアップデートします。変更された日付も含めて、ユーザーが送信した内容を反映します。

3.  この`deleteEntry`メソッドを追加します。
   
        public Entry deleteEntry (long entryId, ServiceContext serviceContext)
            throws PortalException {
       
            Entry entry = getEntry(entryId);
       
            entry = deleteEntry(entryId);
       
            return entry;
        }

    このメソッドは、`entryId`で定義された`entry`オブジェクトを取得し、データベースから削除してから、削除されたオブジェクトを返します。

4.  `Entry`オブジェクトを取得するためのメソッドを追加します。
   
        public List<Entry> getEntries(long groupId, long guestbookId) {
            return entryPersistence.findByG_G(groupId, guestbookId);
        }
       
        public List<Entry> getEntries(long groupId, long guestbookId, int start, int end)
            throws SystemException {
       
            return entryPersistence.findByG_G(groupId, guestbookId, start, end);
        }
       
        public List<Entry> getEntries(
            long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {
       
            return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
        }
       
        public int getEntriesCount(long groupId, long guestbookId) {
            return entryPersistence.countByG_G(groupId, guestbookId);
        }

    `GuestbookLocalServiceImpl`のgetterのようなこれらのメソッドは、Service Builderで生成したFinderを呼び出します。 ただし、これらの`getEntries *`メソッドは、指定されたゲストブックとサイトからエントリを取得します。 最初のメソッドはエントリのリストを取得します。 次のメソッドはページ番号を付けられたリストを取得します。 3番目の方法では、ページ番号を付けられたリストがソートされ、最後のメソッドではエントリの総数が整数として取得されます。

5.  `validate`メソッドを追加します。
   
        protected void validate(String name, String email, String entry)
            throws PortalException {
       
            if (Validator.isNull(name)) {
                throw new EntryNameException();
            }
       
            if (!Validator.isEmailAddress(email)) {
                throw new EntryEmailException();
            }
       
            if (Validator.isNull(entry)) {
                throw new EntryMessageException();
            }
        }

    このメソッドは、エントリの作成時にユーザーが関連するデータを入力したことを確認します。

6.  \[CTRL\]+\[SHIFT\]+O を押してインポートを整理し、プロンプトが表示されたら次のクラスを選択します。

      - `java.util.Date`
      - `com.liferay.portal.kernel.service.ServiceContext`
      - `com.liferay.docs.guestbook.model.Entry`
      - `com.liferay.portal.kernel.util.Validator`

これでローカルサービスメソッドは、ポートレットクラスで参照されるサービスを実装します。

## 生成されたクラスのアップデート

サービスメソッドを実装したので、アプリケーションの残りの部分で利用できるようにする必要があります。 これを行うには、`buildService`を再度実行します。

1.  *Gradle Tasks* → *guestbook-service* → *build*と進んだら`buildService`で右クリックし、 *Run Gradle Tasks*を選択します。 ユーティリティクラスでは、Service Builderによって、新しく作成されたサービスメソッドへの呼び出しが設定されます。

2.  Project Explorerで、`guestbook-service`モジュールを右クリックし、*Refresh*を選択します。 `guestbook-api`モジュールに対してこの手順を繰り返します。 これにより、Service Builderによる変更がLiferay @ide@に表示されます。

3.  Project Explorerで、`guestbook-service`モジュールを右クリックして、*Gradle* → *Refresh Gradle Project*を選択します。 `guestbook-api`モジュールに対してこの手順を繰り返します。 これにより、モジュールのGradle依存関係が最新のものになります。

| **ヒント: ** Service Builderでの作業中に問題が発生した場合は、Service Builderを再度実行してこれらの手順 | を繰り返し、APIとサービスモジュールを更新します。

これで、新しいバックエンドが生成されました。 それでは、プロトタイプをリファクタリングして使用します。
