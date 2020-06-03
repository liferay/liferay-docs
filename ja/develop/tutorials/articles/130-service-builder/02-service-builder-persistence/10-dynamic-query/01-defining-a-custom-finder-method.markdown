---
header-id: defining-a-custom-finder-method
---

# カスタムFinderメソッドの定義

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

動的クエリは、finderメソッドに属します。 それらを実装してから、インターフェースを介して使用可能にします。 このチュートリアルでは、実装クラスでfinderメソッドを定義し、そのインターフェイスを生成して、実装をインターフェイスに結び付けます。

この例は、2つのエンティティ（ゲストブックとエントリ）を持つゲストブックアプリケーションです。 各エントリはゲストブックに属しているため、エントリエンティティには外部キーとして `guestbookId` フィールドがあります。 エントリ名とゲストブック名でゲストブックエントリを検索するためにファインダが必要な場合、 `GuestbookFinderImpl` ファインダメソッドを追加し、 `findByEntryNameGuestbookName`という名前を付けます。 完全なメソッドシグネチャは `findByEntryNameGuestbookName（String entryName、String guestbookName）`ます。 手順は次のとおりです。

1.  サービスモジュールの `src / main / java` フォルダーの `[パッケージパス] .service.persistence.impl` パッケージに `[Entity]FinderImpl` クラスを作成します。 `service.xml` ファイルで `[パッケージパス]` を指定したことを思い出してください。 以下に例を示します。
   
        <service-builder package-path="com.liferay.docs.guestbook">
            ...
        </service-builder>

2.  作成したクラスで `findBy *` finderメソッドを定義します。 必要な引数を必ずファインダーメソッドシグネチャに追加してください。

3.  Service Builderを実行して、APIおよびサービスモジュールの `service` フォルダーの `[package path] .service.persistence` パッケージに適切なインターフェイスを生成します。

    たとえば、 `findByEntryNameGuestbookName（String entryName、String guestbookName）` を `GuestbookFinderImpl` に追加し、Service Builderを実行すると、インターフェイス `com.liferay.docs.guestbook.service.persistence.GuestbookFinder` が生成されます。

4.  ファインダークラスがインターフェイスを実装していることを確認してください。 たとえば、クラス宣言は次のようになります。
   
        public class GuestbookFinderImpl extends BasePersistenceImpl<Guestbook> implements GuestbookFinder

次のステップは、Dynamic Query APIを使用して、finderメソッドにクエリを実装することです。
