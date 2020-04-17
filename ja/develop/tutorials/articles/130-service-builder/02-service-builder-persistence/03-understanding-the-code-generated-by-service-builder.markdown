---
header-id: understanding-the-code-generated-by-service-builder
---

# Service Builderによって生成されたコードを理解する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[Service Builderは、エンティティをサポートするためにコード](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder) を生成します。 以下の[ローカルサービス]および[リモートサービス]にリストされているファイルは、 `ローカルサービス` および `リモートサービス` 属性が `true`設定されているエンティティに対してのみ生成されます。 Service Builderは、 `service.xml`指定したパッケージ内のアプリケーションの `* -api` および `* -service` モジュールでこれらのエンティティのサービスを生成します。 たとえば、Liferayのブックマークアプリケーションのパッケージパスは次のとおりです。

  - `/bookmarks-api/src/main/java/com/liferay/bookmarks`
  - `/bookmarks-service/src/main/java/com/liferay/bookmarks`

`bookmarks-api` モジュールのインターフェイスは、ブックマークアプリケーションAPIを定義します。 `* -api` モジュールインターフェイスは、アプリケーションの永続層、サービス層、およびモデル層を定義します。 `* -api` モジュールをコンパイルしてデプロイすると、そのすべてのクラスとインターフェースが、モジュールの `build / libs` フォルダー内の `PROJECT_NAME-api.jar` という `.jar` ファイルにパッケージ化されます。 このJARをLiferay *デプロイすると、* APIがOSGiサービスとして定義されます。

`bookmarks-service` モジュールクラスは、 `bookmarks-api` モジュールインターフェイスを実装します。 `* -service` モジュールは、LiferayのOSGiフレームワークにデプロイするOSGiサービスの実装を提供します。

次に、指定したエンティティに対して生成されたクラスとインターフェイスを調べます。 `service.xml`で各エンティティがどのように指定されているかに応じて、同様のクラスが各エンティティに対して生成されます。 3種類のカスタマイズ可能なクラスを次に示します。

  - `*LocalServiceImpl`
  - `*ServiceImpl`
  - `*Impl`

`*` は、上記のクラスのエンティティ名を表します。

永続性、サービス、およびモデルのクラスは次のとおりです。

  - 持続性

      - `[ENTITY_NAME]永続`：のようなエンティティのCRUDメソッド定義永続インタフェース `作成`、 `削除`、 `countAll`、 `検索`、 `のfindAll`等、
      - `[ENTITY_NAME]PersistenceImpl`： `[ENTITY_NAME]Persistence`を実装する永続化実装クラス。
      - `[ENTITY_NAME]Util`： `[ENTITY_NAME]PersistenceImpl` をラップし、CRUD操作のためにデータベースへの直接アクセスを提供する永続性ユーティリティクラス。 このユーティリティは、サービス層でのみ使用する必要があります。ポートレットクラスで、 [`@Reference` アノテーション](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)参照することにより、 `[ENTITY_NAME]` クラスを使用します。

    ![図1：Service Builderは、* Event *と呼ばれるエンティティの例に対して、これらの永続クラスとインターフェイスを生成します。 これらのクラスやインターフェースをカスタマイズするべきではありません（また、その必要はありません）。](../../../images/service-builder-persistence-diagram.png)

  - ローカルサービス（エンティティの `local-service` 属性が `service.xml``true` に設定されている場合にのみ、エンティティに対して生成されます）

      - `[ENTITY_NAME]LocalService`：ローカルサービスインターフェイス。
      - `[ENTITY_NAME]LocalServiceImpl` （**LOCAL SERVICE IMPLEMENTATION**）：ローカルサービスの実装。 これは、手動で変更する必要があるローカルサービスの唯一のクラスです。 ここでカスタムビジネスロジックを追加できます。 ここで追加されたメソッドの場合、Service Builderは、次に実行するときに、対応するメソッドを `[ENTITY_NAME]LocalService` インターフェイスに追加します。
      - `[ENTITY_NAME]LocalServiceBaseImpl`：ローカルサービスベースの実装。 これは抽象クラスです。 Service Builderは、さまざまなサービスおよび永続性クラスの多数のインスタンスをこのクラスに注入します。 `@抽象`
      - `[ENTITY_NAME]LocalServiceUtil`：ラップするローカルサービスユーティリティクラス `[ENTITY_NAME]LocalServiceImpl`。 このクラスは、後方互換性のためにのみ生成されます。 [`@Reference` アノテーション](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)参照することにより、 `* LocalService` クラスを使用し` 。</li>
<li><code>[ENTITY_NAME]LocalServiceWrapper`： `[ENTITY_NAME]LocalService`を実装するローカルサービスラッパー。 このクラスは、拡張できるように設計し、それはあなたのことができますされ [カスタマイズエンティティのローカルサービスを](/docs/7-1/tutorials/-/knowledge_base/t/customizing-liferay-services-service-wrappers)。

    ![図2：Service Builderはこれらのサービスクラスとインターフェイスを生成します。 \ [ENTITY \ _NAME \] LocalServiceImpl（たとえば、イベントエンティティのEventLocalServiceImpl）のみが、サービスレイヤーにカスタムメソッドを追加できます。](../../../images/service-builder-service-diagram.png)

  - リモートサービス（エンティティの `remote-service` 属性が *ではなく* が `設定されている場合にのみエンティティに対して生成されますfalse <code>in` service.xml</code>）

      - `[ENTITY_NAME]サービス`：リモートサービスインターフェイス。
      - `[ENTITY_NAME]ServiceImpl` （**REMOTE SERVICE IMPLEMENTATION**）：リモートサービスの実装。 これは、手動で変更する必要があるリモートサービス内の唯一のクラスです。 ここで、追加のセキュリティチェックを追加し、ローカルサービスを呼び出すコードを作成できます。 ここで追加されたカスタムメソッドの場合、Service Builderは、次に実行するときに、対応するメソッドを `[ENTITY_NAME]Service` インターフェイスに追加します。
      - `[ENTITY_NAME]ServiceBaseImpl`：リモートサービスベースの実装。 これは抽象クラスです。 `@抽象`
      - `[ENTITY_NAME]ServiceUtil`： `[ENTITY_NAME]ServiceImpl`をラップするリモートサービスユーティリティクラス。 このクラスは、後方互換性のためにのみ生成されます。 [`@Reference` 注釈](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)参照することにより、 `* Service` クラスを使用し` 。</li>
<li><code>[ENTITY_NAME]ServiceWrapper`： `[ENTITY_NAME]Service`を実装するリモートサービスラッパー。 このクラスは、拡張できるように設計し、それはあなたのことができますされ [カスタマイズエンティティのリモートサービスを](/docs/7-1/tutorials/-/knowledge_base/t/customizing-liferay-services-service-wrappers)。
      - `[ENTITY_NAME]ServiceSoap`：リモート `[ENTITY_NAME]ServiceUtil` リモートサービスユーティリティがアクセスできるSOAPユーティリティ。
      - `[ENTITY_NAME]石鹸`：SOAPモデルと同様 `[ENTITY_NAME]ModelImpl`。 `[ENTITY_NAME]SOAP` はシリアル化可能です。 `[ENTITY_NAME]`は実装していません。

  - モデル

      - `[ENTITY_NAME]モデル`：ベースモデルインターフェイス。 このインターフェイスとその `[ENTITY_NAME]ModelImpl` 実装は、Service Builderが生成するデフォルトプロパティアクセサーのコンテナーとしてのみ機能します。 ヘルパーメソッドとすべてのアプリケーションロジックを `[ENTITY_NAME]Impl`追加する必要があります。
      - `[ENTITY_NAME]ModelImpl`：ベースモデルの実装。
      - `[ENTITY_NAME]`： `[ENTITY_NAME]` `[ENTITY_NAME]モデル`を拡張するモデルインターフェイス。
      - `[ENTITY_NAME]Impl`：（**モデルの実装**）モデルの実装。 このクラスを使用して、ヘルパーメソッドとアプリケーションロジックをモデルに追加できます。 ヘルパーメソッドやアプリケーションロジックを追加しない場合は、自動生成されたフィールドゲッターとセッターのみが利用可能です。 このクラスにカスタムメソッドを追加するたびに、Service Builderは、次に実行するときに、対応するメソッドを `[ENTITY_NAME]` インターフェイスに追加します。
      - `[ENTITY_NAME]ラッパー`：ラッパー、ラップ `[ENTITY_NAME]`。 このクラスは、拡張できるように設計され、それはあなたができますされ [エンティティカスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-liferay-services-service-wrappers)。

    ![図3：Service Builderはこれらのモデルクラスとインターフェイスを生成します。 カスタムメソッドをサービスレイヤーに追加できるのは、 <code>[ENTITY_NAME]Impl</code> （たとえば、イベントエンティティのEventImpl）のみです。](../../../images/service-builder-model-diagram.png)

| **注：** `* Utility` クラスは、後方互換性のために生成されます|のみ。 モジュールアプリケーションは、utilクラスの呼び出しを避ける必要があります。 |を使用します代わりに非ユーティリティクラス-を使用してそれらを参照できます。 [`@Reference` アノテーション](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)。

Service Builderが生成する各ファイルは、関連するFreeMarkerテンプレートから組み立てられます。 FreeMarkerテンプレートは、 [`com.liferay.portal.tools.service.builder` モジュールの](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.tools.service.builder/) `/ com / liferay / portal / tools / service / builder / dependencies /` フォルダーにあります。 たとえば、Service Builderは `service_impl.ftl` テンプレートを使用して `* ServiceImpl.java` クラスを生成します。

Service Builderが生成する任意の `* Impl` クラスを変更でき` 。 最も一般的である <code>* LocalServiceImpl`、 `* ServiceImpl` 及び `* Implを`。 他のクラスを手動で変更した場合、Service Builderは次に実行したときに変更を上書きします。 `* LocalServiceImpl` クラス、 `* ServiceImpl` クラス、または `* Impl` クラスのメソッドを追加、削除、またはメソッドシグネチャを変更するたびに、Service Builderを再度実行して、影響を受けるインターフェイスとサービスを再生成する必要がありますJAR。

おめでとうございます！ アプリケーションの初期モデル、永続性、およびサービスレイヤーを生成し、生成されたコードを理解しました。

**関連トピック**

[Service Builderとは](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder)

[Service Builderの実行](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)

[サービスコンテキストについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)

[ローカルサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-local-services)
