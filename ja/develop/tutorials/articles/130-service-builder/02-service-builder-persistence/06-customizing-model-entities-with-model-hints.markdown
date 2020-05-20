---
header-id: customizing-model-entities-with-model-hints
---

# モデルヒントを使用したモデルエンティティのカスタマイズ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Service Builderを使用してモデルエンティティを定義したら、ユーザーがそのデータを入力する方法をさらに改良することができます。 たとえば、モデルヒントは、将来の日付のみを選択できるカレンダーフィールドを定義できます。 モデルヒントは、エンティティデータの制限やその他のフォーマットを指定するための単一の場所を提供します。

`portlet-model-hints.xml`というファイルでモデルヒントを定義します。 プロジェクトにAPIモジュールと [Service Builderプロジェクトテンプレート](/docs/7-1/reference/-/knowledge_base/r/using-the-service-builder-template)ようなサービスモジュールがある場合、 `portlet-model-hints.xml` はサービスモジュールの `src / main / resources / META-INF` フォルダーに入ります。 たとえば、Liferayのブックマークアプリケーションでは、 `portlet-model-hints.xml` ファイルは `bookmarks-service / src / main / resources / META-INF /` フォルダーにあります。

モデルヒントは2つのことを定義します。

1.  エンティティがユーザーに提示される方法

2.  データベース列のサイズ

Liferayがフォームフィールドをレンダリングするとき、設定に基づいてフォームの入力フィールドをカスタマイズします。

| **注：** Service Builderは、いくつかのXML構成ファイルを生成します|サービスモジュールの `src / main / resources / META-INF` フォルダー。 Service Builderの使用|これらのファイルのほとんどは、SpringおよびHibernate構成を管理します。 変更しないでください| SpringまたはHibernate構成ファイル。それらへの変更は上書きされます| Service Builderの実行時。 ただし、|を安全に編集できます。 `portlet-model-hints.xml` ファイル。

例として、 [ブックマークアプリサービスモジュールの](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.bookmarks.service/) モデルヒントファイルを考えます。

    <?xml version="1.0"?>
    
    <model-hints>
        <model name="com.liferay.bookmarks.model.BookmarksEntry">
            <field name="uuid" type="String" />
            <field name="entryId" type="long" />
            <field name="groupId" type="long" />
            <field name="companyId" type="long" />
            <field name="userId" type="long" />
            <field name="userName" type="String" />
            <field name="createDate" type="Date" />
            <field name="modifiedDate" type="Date" />
            <field name="folderId" type="long" />
            <field name="treePath" type="String">
                <hint name="max-length">4000</hint>
            </field>
            <field name="name" type="String">
                <hint name="max-length">255</hint>
            </field>
            <field name="url" type="String">
                <hint-collection name="URL" />
                <validator name="required" />
                <validator name="url" />
            </field>
            <field name="description" type="String">
                <hint-collection name="TEXTAREA" />
            </field>
            <field name="visits" type="int" />
            <field name="priority" type="int">
                <hint name="display-width">20</hint>
            </field>
            <field name="lastPublishDate" type="Date" />
            <field name="status" type="int" />
            <field name="statusByUserId" type="long" />
            <field name="statusByUserName" type="String" />
            <field name="statusDate" type="Date" />
        </model>
        <model name="com.liferay.bookmarks.model.BookmarksFolder">
            ...
        </model>
    </model-hints>

ルートレベル要素は `model-hints`です。 モデルエンティティは、 `モデルヒント` 要素の `モデル` サブ要素で表されます。 `モデル` 各要素には、完全修飾クラス名を指定する `name` 属性が必要です。 モデルには、エンティティの列を表す `フィールド` 要素があります。 最後に、 `フィールド` 要素には名前とタイプが必要です。 各 `フィールド` 要素の名前とタイプは、サービスモジュールの `service.xml` ファイルのエンティティの列に指定された名前とタイプにマップされます。 Service Builderは、 `service.xml`基づいてこれらすべての要素を生成します。

フィールドにヒントを追加するには、 `ヒント` 子要素を追加します。 たとえば、 `表示幅ヒント` を追加して、フィールドの表示に使用するピクセル幅を指定できます。 デフォルトのピクセル幅は `350`です。 50ピクセルの `文字列` フィールドを表示するには、 `display-width` という名前の `ヒント` 要素をネストし、値に `50`ます。

フィールドに対するヒントの効果を確認するには、 [でService Builder](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder) 再度実行し、 [モジュールを再デプロイします](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)。 `表示幅` を変更しても、ユーザーが `名前` フィールドに入力できる文字数は制限されません。 AlloyUI入力フォームでフィールドの幅を制御するだけです。

モデルフィールドのデータベース列の最大サイズ（つまり、フィールドに保存できる最大文字数）を構成するには、 `max-length` ヒントを使用します。 デフォルトの `max-length` 値は `75` 文字です。 `name` フィールドを最大100文字まで保持する場合は、そのフィールドに `max-length` ヒントを追加します。

    <field name="name" type="String">
        <hint name="display-width">50</hint>
        <hint name="max-length">100</hint>
    </field>

`portlet-model-hints.xml` ファイルを更新した後、忘れずにService Builderを実行し、プロジェクトを再デプロイしてください。

## モデルのヒントタイプ

これまでのところ、いくつかの異なるヒントを見てきました。 次の表に、使用可能なポートレットモデルヒントを示します。

**モデルのヒント値と説明**



| 名前                  | 値のタイプ | 説明                                              | デフォルト                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| ------------------- |:----- |:----------------------------------------------- |:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `auto-escape`       | ブール値  | `HtmlUtil.escape`介してテキスト値をエスケープするかどうかを設定します     | 本当                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `autoSize`          | ブール値  | スクロール可能なテキスト領域にフィールドを表示します                      | 偽                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `day-nullable`      | ブール値  | 日付フィールドで日をnullにすることができます                        | 偽                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `default-value`     | ひも    | aui taglibを使用してレンダリングされたフォームフィールドのデフォルト値を設定します  | （空の文字列）                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| `display-height`    | 整数    | aui taglibを使用してレンダリングされたフォームフィールドの表示高さを設定します    | 15                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `display-width`     | 整数    | aui taglibを使用してレンダリングされたフォームフィールドの表示幅を設定します     | 350                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| `editor`            | ブール値  | 入力にエディターを提供するかどうかを設定します                         | 偽                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `max-length`        | 整数    | SQLファイル生成の最大列サイズを設定します                          | 75                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `month-nullable`    | ブール値  | 日付フィールドで月をヌルにすることができます                          | 偽                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `secret`            | ブール値  | ユーザーが入力した文字を非表示にするかどうかを設定します                    | 偽                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `show-time`         | ブール値  | 日付とともに時刻を表示するかどうかを設定します                         | 本当                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `upper-case`        | ブール値  | すべての文字を大文字に変換します                                | 偽                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `year-nullable`     | ブール値  | 日付フィールドの年をnullにすることができます                        | 偽                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `year-range-delta`  | 整数    | aui taglibでレンダリングされた日付フィールドに今日の日付から表示する年数を指定します | 5                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `year-range-future` | ブール値  | 将来の日付を含めるかどうかを設定します                             | 本当                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `year-range-past`   | ブール値  | 過去の日付を含めるかどうかを設定します                             | 真|<!--</td>
</tr>
<tr>
  <td>I wanted to get a complete list of model hints used in portal. I found the</td>
  <td align="left"></td>
  <td align="left"></td>
  <td align="left"></td>
</tr>
<tr>
  <td>ModelHints interface and then searched for where (*.java, *.js, *.jsp) it was</td>
  <td align="left"></td>
  <td align="left"></td>
  <td align="left"></td>
</tr>
<tr>
  <td>referenced. It led me to portal-web/docroot/html/taglib/ui/input_field/page.jsp.</td>
  <td align="left"></td>
  <td align="left"></td>
  <td align="left"></td>
</tr>
<tr>
  <td>I found some more hints and added them to the table above.</td>
  <td align="left"></td>
  <td align="left"></td>
  <td align="left"></td>
</tr>
<tr>
  <td></td>
  <td align="left"></td>
  <td align="left"></td>
  <td align="left"></td>
</tr>
<tr>
  <td>I'm not sure what the <code>check-tab</code> hint does. Would be worth asking the UI team.</td>
  <td align="left"></td>
  <td align="left"></td>
  <td align="left"></td>
</tr>
<tr>
  <td></td>
  <td align="left"></td>
  <td align="left"></td>
  <td align="left"></td>
</tr>
<tr>
  <td>- Jim --> | **注**：aui taglibは完全にサポートされており、AlloyUI（ | JavaScriptライブラリ）は非推奨です。 |  |

| **注**：フォームでClayタグとauiタグを組み合わせて使用できます。 モデルのヒント、|ただし、auiタグのみに影響します。

Liferayはfile--独自のモデルのヒントを持っていることに注意してください`ポータルモデル-hints.xml`。 `portal-impl.jar`の `META-INF` フォルダーにあります。 このファイルには多くのヒントの例が含まれているため、 `portlet-model-hints.xml` ファイルを作成するときに参照できます。

## デフォルトのヒント

`default-hints` 要素を使用して、モデルのすべてのフィールドに適用するヒントのリストを定義できます。 たとえば、モデル要素内に次の要素を追加すると、300ピクセルの `表示幅` が各フィールドに適用されます。

    <default-hints>
        <hint name="display-width">300</hint>
    </default-hints>

## ヒント集

`model-hints` ルートレベル要素内に `ヒントコレクション` 要素を定義して、一緒に適用するヒントのリストを定義できます。 ヒントコレクションには名前が必要です。 たとえば、Liferayの `portal-model-hints.xml` は、次のヒントコレクションを定義します。

    <hint-collection name="CLOB">
        <hint name="max-length">2000000</hint>
    </hint-collection>
    <hint-collection name="EDITOR">
        <hint name="editor">true</hint>
        <hint name="max-length">2000000</hint>
    </hint-collection>
    <hint-collection name="EMAIL-ADDRESS">
        <hint name="max-length">254</hint>
    </hint-collection>
    <hint-collection name="HOSTNAME">
        <hint name="max-length">200</hint>
    </hint-collection>
    <hint-collection name="SEARCHABLE-DATE">
        <hint name="month-nullable">true</hint>
        <hint name="day-nullable">true</hint>
        <hint name="year-nullable">true</hint>
        <hint name="show-time">false</hint>
    </hint-collection>
    <hint-collection name="TEXTAREA">
        <hint name="display-height">105</hint>
        <hint name="display-width">500</hint>
        <hint name="max-length">4000</hint>
    </hint-collection>
    <hint-collection name="URL">
        <hint name="max-length">4000</hint>
    </hint-collection>

ヒントコレクションの名前を参照することにより、モデルフィールドにヒントコレクションを適用できます。 たとえば、 `model-hints` 要素で上記のような `SEARCHABLE-DATE` コレクションを定義する場合、コレクションを参照する `hint-collection` 要素を使用して、モデルの日付フィールドに適用できます。名：

    <field name="date" type="Date">
        <hint-collection name="SEARCHABLE-DATE" />
    </field>

プロジェクトでいくつかのモデルヒントを使用するとします。 コメントフィールドに入力するためのエディターをユーザーに提供することから始めます。 同じヒントを複数のエンティティに適用するには、ヒントコレクションとして定義します。 次に、各エンティティのヒントコレクションを参照します。

ヒントコレクションを定義するには、 `portlet-model-hints.xml` ファイルの `model-hints` ルート要素内に `hint-collection` 要素を追加します。 たとえば、

    <hint-collection name="COMMENT-TEXTAREA">
        <hint name="display-height">105</hint>
        <hint name="display-width">500</hint>
        <hint name="max-length">4000</hint>
    </hint-collection>

特定のフィールドのヒントコレクションを参照するには、フィールドの `フィールド` 要素内に `ヒントコレクション` 要素を追加します。

    <field name="comment" type="String">
        <hint-collection name="COMMENT-TEXTAREA" />
    </field>

ヒントコレクションを定義し、ヒントコレクション参照を追加したら、Service Builderを使用してサービスを再構築し、プロジェクトを再デプロイして、ヒントコレクションで定義されたヒントが有効になっていることを確認します。

よくやった\！ Liferayのモデルヒントを通じて説得の技術を学びました。 これで、モデルの入力フィールドの表示方法に影響を与えることができるだけでなく、データベーステーブルの列サイズを設定することもできます。 ヒントを整理したり、個々のヒントをフィールドに直接挿入したり、モデルのすべてのフィールドにデフォルトのヒントのセットを適用したり、これらのスコープのいずれかに適用するヒントのコレクションを定義したりできます。 Liferayモデルのヒントがアプリデータの表示方法を指定する方法に関する「ヒント」を取り上げました！
