---
header-id: defining-global-service-information
---

# グローバルサービス情報の定義

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

サービスのグローバル情報はすべてのエンティティに適用されるため、開始するのに適した場所です。 Liferay @ide@で、 `service.xml` ファイルの概要モードの左上隅にある *Service Builder* ノードを選択します。 ビューのメインセクションに、サービスのグローバル情報を入力するService Builderフォームが表示されます。 フィールドにはサービスの

  - [パッケージパス](#package-path)
  - [名前空間オプション](#namespace-options)
  - [マルチバージョン同時実行制御](#multiversion-concurrency-control-mvcc)
  - [著者](#author)

![図1：これは、ブックマークアプリケーションの <code>service.xml</code>からのService Builderフォームです。](../../../../images/service-builder-main-form.png)

## パッケージパス

パッケージパスは、サービスクラスと永続クラスが生成されるパッケージを指定します。 ブックマークのパッケージパスにより、 `* -api` モジュールのサービスクラスが `com.liferay.bookmarks` パッケージで生成されます。 永続クラスは、 `* -service` モジュールの同じ名前のパッケージで生成されます。 たとえば、Bookmarksアプリケーションの [bookmarks-api](https://github.com/liferay/liferay-portal/tree/master/modules/apps/bookmarks/bookmarks-api) および [bookmarks-service](https://github.com/liferay/liferay-portal/tree/master/modules/apps/bookmarks/bookmarks-service) モジュールを調べて、これらが自動的に生成される方法を確認します。 後のチュートリアル [では、パッケージの内容](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)について説明します。

## 名前空間オプション

Service Builderは、サービス用に生成するデータベーステーブルに名前を付ける際にサービス名前空間を使用します。 たとえば、 *ブックマーク* は、ブックマークアプリケーションサービスのネームスペースとして機能します。

``` xml
<namespace>Bookmarks</namespace>
```

Service Builderは、 `src / main / resources / sql` フォルダーで生成する次のSQLスクリプトで名前空間を使用します。

  - `indexes.sql`
  - `sequences.sql`
  - `tables.sql`

| **注：** 生成されたSQLスクリプトフォルダーの場所は構成可能です。 のために|たとえば、Gradleを使用している場合、|で `sqlDir` 設定を定義できます。プロジェクトのGradle `build.gradle` ファイルまたはMaven `pom.xml` ファイル、同じ方法|以下の例では、 `databaseNameMaxLength` 設定が適用されます。

Service Builderは、SQLスクリプトを使用して、 `service.xml` 定義するすべてのエンティティのデータベーステーブルを作成します。 データベーステーブル名には、作成時に名前空間が付加されます。 名前空間の例の値は `Bookmarks`であるため、エンティティ用に作成されたデータベーステーブル名は、プレフィックスとして `Bookmarks_` 始まります。 各Service Builderプロジェクトの名前空間は一意である必要があります。 個別のプラグインは個別のネームスペースを使用し、Liferayエンティティ（ `ユーザー` または `グループ`）で既に使用されているネームスペースを使用しないでください。 Liferayのデータベースのテーブル名を確認して、すでに使用されているネームスペースを確認してください。

**警告：** 名前空間の値を割り当てるときは注意してください。 一部のデータベースでは、データベースのテーブル名と列名の長さに強い制限があります。 Service Builder [Gradle](/docs/7-1/reference/-/knowledge_base/r/service-builder-gradle-plugin#task-properties) および [Maven](/docs/7-1/reference/-/knowledge_base/r/service-builder-with-maven#available-parameters) プラグインパラメーター `databaseNameMaxLength` は、テーブル名と列名に使用できる最大長を設定します。 ビルドファイルに `databaseNameMaxLength` を設定する言い換え例を次に示します。

**Gradle `build.gradle`**

``` groovy
buildService {
    ...
    databaseNameMaxLength = 64
    ...
}
```

**Maven `pom.xml`**

``` xml
<configuration>
    ...
    <databaseNameMaxLength>64</databaseNameMaxLength>
    ...
</configuration>
```

## マルチバージョン同時実行制御（MVCC）

`service-builder` 要素の `mvcc-enabled` 属性は、デフォルトでは `false` です。 `mvcc-enabled = "true"` （ヒント： *ソース* ビューで `service.xml` を編集）を設定すると、サービスのすべてのエンティティに対して [マルチバージョン同時実行制御](https://en.wikipedia.org/wiki/Multiversion_concurrency_control) （MVCC）が有効になります。 システムでは、同時更新が一般的です。 MVCCがないと、知らないうちに無効な状態のデータを読み取ったり上書きしたりする可能性があります。 MVCCでは、各変更は指定された基本バージョン番号に基づいて行われます。 Hibernateは更新を受け取ると、 `where` 節を使用して現在のデータバージョンが期待するバージョンであることを確認する `update` SQLステートメントを生成します。

現在のデータバージョン

  - **は予想されるバージョン**と一致します。データ操作は最新のデータに基づいており、受け入れられます。

  - **は期待されるバージョン**と一致しません。操作しているデータは古くなっています。 @product@はデータ操作を拒否し、例外をスローします。例外をキャッチすると、ユーザーが例外を処理するのに役立ちます（操作の再試行を提案するなど）。

**重要：** `<service-builder/>` 要素に `mvcc-enabled = "true"` を設定して、すべてのサービスのMVCCを有効にします。 サービスエンティティの更新（例： `fooService.update（object）`）を呼び出すときは、トランザクションで必ず実行してください。 ユーザーが処理できるように、拒否されたトランザクションをUIに伝達します。

## 著者

グローバル情報の最後の部分として、 `service.xml` ファイルにサービスの *者* として名前を入力します。 Service Builderは、生成するすべてのJavaクラスとインターフェースに、指定された名前の@author</code> 注釈を `個追加します。 <code>service.xml` ファイルを保存します。 次に、サービスのエンティティを追加します。
