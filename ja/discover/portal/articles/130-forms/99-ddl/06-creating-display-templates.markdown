---
header-id: creating-display-templates
---

# 表示テンプレートの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

すべてのデータ定義について、必要な数だけディスプレイを作成できます。 データリストのフォームビューに特定のデータ定義のすべてのフィールドを表示しないフォームテンプレートを作成した場合、それらのフィールドもリストビューに表示したくないでしょう。 表示テンプレートを使用してリストビューを変更します。

| **注：** 慣れている場合| [Webコンテンツテンプレート](/docs/7-1/user/-/knowledge_base/u/designing-web-content-with-templates)、|表示テンプレートは、リストの表示を同じ方法でカスタマイズします。 ディスプレイ|テンプレートはFreeMarkerまたはVelocityで記述され、データからデータを引き出します。 Webコンテンツテンプレートがそれらからデータをプルするのと同じ方法で定義します。構造。 Webコンテンツテンプレートと同様に、表示テンプレートは次のようになります。他の表示テンプレートに埋め込まれています。 これにより、再利用可能なコード、JavaScript |ライブラリのインポート、またはVelocityまたはFreeMarkerテンプレートによってインポートされたマクロ|システム。 表示テンプレートを埋め込むと、次の場合により効率的なプロセスが提供されます。同様のデータ定義が多数あります。 埋め込みディスプレイをインポートするだけ|テンプレートを使用して、新しい表示テンプレートを作成します。

[フォームテンプレート](/docs/7-1/user/-/knowledge_base/u/creating-form-templates)と同様に、サイト管理の動的データリストアプリまたは動的データリスト表示ウィジェットから表示テンプレートを作成できます。

次の手順に従って、サイト管理から表示テンプレートを作成します。

1.  メニュー（![Menu](../../../images/icon-menu.png)）を開き、サイトのメニュー（サイト管理メニュー）を展開します。 次に、 *Content* → *Dynamic Data Lists*選択します。 これにより、[動的データリスト]画面が開きます。 テーブルには、既存の動的データリストが一覧表示されます。

2.  右上の[ *オプション* ]ボタン（![Options](../../../images/icon-options.png)）をクリックし、[ *データ定義の管理*を選択します。 データ定義画面が表示されます。 表に既存のデータ定義を示します。

3.  定義の[ *アクション* ]ボタン（![Options](../../../images/icon-options.png)）をクリックし、[ *テンプレートの管理*]を選択します。 定義のテンプレートがリストされます。

4.  [ *追加* ]ボタン（![Add](../../../images/icon-add.png)）をクリックし、[ *表示テンプレートの追加*]を選択します。

5.  テンプレートに名前を付け、フォームの *Details* セクションを展開して、説明を入力します。 また、フォームの詳細セクションで、使用するテンプレート言語を *言語* セレクターから選択します。 [FreeMarker](https://freemarker.apache.org/index.html)または [Velocity](https://velocity.apache.org/)を選択できます。

6.  フォームの *Script* セクションで、前のステップで選択したテンプレート言語を使用して、エディターでテンプレートを作成します。 エディターの左側のパレットには、共通の変数が含まれています。 変数をクリックして、エディターに挿入します。 エディターもオートコンプリートします。 FreeMarkerテンプレートで、 `$ {`と入力します。これにより、共通変数のオートコンプリートリストが開きます。 変数を選択してエディターに挿入します。 または、エディターの下の[ *参照* ]ボタンを使用して、完全なスクリプトファイルをアップロードできます。

7.  テンプレートの作成が完了したら、[ *保存* クリックします。

![図1：エディターで表示テンプレートを作成します。](../../../images/ddl-template-editor.png)

または、動的データリスト表示ウィジェットを使用して、表示テンプレートを作成することもできます。

1.  サイトページでウィジェットを追加および構成するには、 [データリストの作成の記事](/docs/7-1/user/-/knowledge_base/u/creating-data-lists) の指示に従ってください。 テンプレートに作成するリストの定義を表示するようにウィジェットを設定してください。

2.  ウィジェットの *Add Display Template* ボタンをクリックします。 これにより、リストの定義の表示テンプレートを作成するための上記と同じフォームが開きます。

## 表示テンプレートエディター

ヘルパー変数は、テンプレートエディターで使用できます。 これらは、表示テンプレートの作成に使用するほとんどのデータへのアクセスを提供します。 [データリスト変数]という見出しの下の変数を使用すると、テンプレートが作成されるデータ定義に関する特定の情報を挿入できます。

**データ定義ID：** `reserved_ddm_structure_id`

**データリストの説明：** `reserved_record_set_description`

**データリストID：** `reserved_record_set_id`

**データリスト名：** `reserved_record_set_name`

**テンプレートID：** `reserved_ddm_template_id`

テンプレート内では、これらの変数はレコードセットのID、名前、説明、データ定義を提供します。

レコードを取得して、便利な `レコード` 変数に割り当てることにより、レコードのリストを表示します。 次の関数を含む `DDLDisplayTemplateHelper`からリストのレコードを取得します。

    getDocumentLibraryPreviewURL
    
    getHTMLContent
    
    getLayoutFriendlyURL
    
    getRecords
    
    renderRecordFieldValue

`DDLDisplayTemplateHelper` は一般的なタスクを実行します。 `getRecords` メソッドを使用して、データ定義のエントリにアクセスし、それらを `レコード` 変数に割り当てます。

    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>

この *は、関連付けられたデータリストのレコードを* フェッチします。 まだ何もしていないので、ディスプレイはまだ空です。 すべてのレコードを一覧表示するには、テンプレートエディターのサイドバーにある *Data List Records* ヘルパーを使用し* 。 テンプレートエディターウィンドウの適切な場所にカーソルを置いてから、[ *データリストレコード*]をクリックしてください。 このコードはカーソルに表示されます。</p> 

    <#if records?has_content>
        <#list records as cur_record>
            ${cur_record}
        </#list>
    </#if>

このデフォルトのコードスニペットは、指定されたデータ定義のデータベース内のすべてを吐き出します。

    {uuid=52c4ac1c-afe7-963c-49c6-5279b7030a99, recordId=35926, groupId=20126, 
    companyId=20099, userId=20139, userName=Test Test, versionUserId=20139, 
    versionUserName=Test Test, createDate=2018-07-16 14:31:51.056, 
    modifiedDate=2018-07-16 14:31:51.058, DDMStorageId=35927, recordSetId=35922, 
    recordSetVersion=1.0, version=1.0, displayIndex=0, lastPublishDate=null}

次に、埋め込まれた連絡先データ定義に基づくリストを使用し、会社名と電子メールのフィールドのみを箇条書きリストに表示する簡単なサンプルテンプレートを示します。

    <#assign records = ddlDisplayTemplateHelper.getRecords(reserved_record_set_id)>
    
        <h1>Here are contacts by company name and email address.</h1>
    
        <#if records?has_content>
            <#list records as cur_record>
                <ul>
                    <li>
                        <#-- The below gets the Company field and wraps it in an <em> tag -->
                        Company Name: <em>${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("company")?first, locale)}</em><br /> 
                        <#-- The below gets the Email field  and wraps it in an <em> tag --> 
                        Email: ${ddlDisplayTemplateHelper.renderRecordFieldValue(cur_record.getDDMFormFieldValues("email")?first, locale)} 
                    </li> 
                </ul> 
            </#list> 
        </#if>

これは次のようになります。

![図2：オブジェクト全体を吐き出すのではなく、適切な表示情報を抽出します。](../../../images/ddl-contacts-template.png)

これで、表示テンプレートを使用してデータリストを美しくする準備ができました。
