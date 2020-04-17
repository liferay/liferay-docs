---
header-id: making-configurable-theme-settings
---

# 構成可能なテーマ設定を行う

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマを変更するたびに、変更を行ってからサーバーにデプロイする必要があります。 サイト管理者にとって、このプロセスは、特にバナーの色の変更など、テーマに対するマイナーな変更である場合、面倒です。 元に戻す必要がある場合はどうなりますか？ 再度展開する必要があります。 大きなテーマの変更の場合、このプロセスは避けられませんが、小さな変更の場合は、より良い方法があります。構成可能なテーマ設定です。 テーマ開発者は、テーマ要素の可視性と価値を制御し、サイト管理者がコントロールパネルで構成および選択するテーマ要素のバリエーションを提供できます。 このチュートリアルでは、テーマの構成可能なテーマ設定を作成する方法について説明します。

次の手順を実行します：

1.  テーマの `WEB-INF / liferay-look-and-feel.xml` ファイルを開くか、存在しない場合は作成し、以下のパターンに従って、親 `<settings>` 要素内に `<setting/>` 要素をネストしますテーマに必要な各設定：
   
        <look-and-feel>
<compatibility> <version>7.1.0+</version> </compatibility> <theme id="your-theme-name" name="Your Theme Name"> <template-extension>ftl</template-extension> <settings> <setting configurable="true" key="theme-setting-key" options="true,false" type="select" value="true" /> <setting configurable="true" key="theme-setting-key" type="text" value="My placeholder text" /> </settings> <portlet-decorator> ポートレットデコレータ... </portlet-decorator> </theme> </look-and-feel>

    以下の設定例は、カスタムテキストのテキスト入力設定を追加します。
   
        <settings>
          <setting configurable="true" key="my-custom-text"
          type="text" value="Liferay rocks!" />
        </settings>

    利用可能なテーマ `<setting>` 属性を以下に示します。

    `構成可能`：設定が構成可能か静的か

    `キー`：テーマ設定を識別する言語キー

    `オプション`： `タイプ` が `選択`場合、選択メニューのオプションを設定します

    `type`：テーマ設定を制御するためにレンダリングするUIのタイプ。 可能な値は `チェックボックス`、 `を選択`、 `、テキスト`、又は `TEXTAREA`。

    `値`：テーマ設定のデフォルト値を設定します

    `liferay-look-and-feel.xml` 属性および他のすべての構成の設定に関する詳細については、その [DTDドキュメント](@platform-ref@/7.1-latest/definitions/liferay-look-and-feel_7_1_0.dtd.html#settings)。

2.  テーマテンプレートに `init_custom.ftl` がまだ存在しない場合は追加し、以下のパターンに従ってテーマ設定変数を定義します。

    ブール値（オプション `true` および `false` 選択ボックス、または値 `yes` および `no`チェックボックス）：
   
        <#assign my_variable_name =
        getterUtil.getBoolean(themeDisplay.getThemeSetting("theme-setting-key"))/>

    文字列（テキスト入力またはテキスト領域入力）：
   
        <#assign my_variable_name =
        getterUtil.getString(themeDisplay.getThemeSetting("theme-setting-key"))/>

    以下の設定例は、カスタムテキスト設定を追加します。
   
        <#assign my_custom_text =
        getterUtil.getString(themeDisplay.getThemeSetting("my-custom-text"))/>

3.  テーマ設定をテーマテンプレートに適用します。 以下の構成例では、 `<p>` 要素の `my_custom_text` 変数の値を出力します。

    `portal_normal.ftl`： <p>${my_custom_text}</p>

4.  テーマをデプロイしてインストールし、 *コントロールメニュー* → *サイト管理* → *ビルド* → *ページ*を開きます。 この時点から、2つのスコープのテーマ設定を構成できます。

      - **パブリックまたはプライベートページセット：** *設定するページセットの横にある歯車アイコン* クリックします。 次に、 *Look＆Feel* タブで設定を選択し、 *Save* をクリックしてページセットに変更を適用します。

      - **個々のページ：** ページの横にある[アクション]メニューを開き、[ *構成]*を選択します。 その後、クリック *ルック・アンド・フィール* ]タブを選択し、 *、このページ固有のルックアンドフィールを定義* オプションを選択します。 設定を選択し、 *保存* をクリックして、ページに変更を適用します。

![図1：以下は、サイト管理者の構成可能な設定の例です。](../../../../images/theme-dev-configurable-theme-settings.png)

テーマの構成可能なテーマ設定を行う方法がわかりました！

## 関連トピック

[テーマ用に再利用可能なコードを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)

[テーマの拡張機能リスト](/docs/7-1/tutorials/-/knowledge_base/t/listing-your-themes-extensions)

[テーマを使用したリソースのインポート](/docs/7-1/tutorials/-/knowledge_base/t/importing-resources-with-a-theme)
