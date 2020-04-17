---
header-id: using-front-end-code-assist-features-in-ide
---

# Dev Studioでのフロントエンドコードアシスト機能の使用

[TOC levels=1-4]

Liferay Dev Studioは、Liferay開発を支援する拡張性の高いフロントエンド開発ツールを提供しています。これまでに、AlloyUI、JavaScript、CSS、およびjQueryのコード推論およびコード補完機能にアクセスできるようになりました。

このチュートリアルでは、Dev Studioのコードアシスト機能の使用方法について説明します。

- [AlloyUI](#alloyui-code-assist-features)
- [JavaScript](#javascript-code-assist-features)
- [CSS](#css-code-assist-features)
- [jQuery](#jquery-code-assist-features)

各言語はそれぞれのセクションで説明されているため、関心のある言語のセクションを参照してください。DevStudioがコードアシストを提供する言語は、上記の4つの言語を含め、多数あります。これは、Dev Studioの[Tern](http://ternjs.net/)統合によって提供されます。これらの機能にアクセスするには、使用している言語が想定されているファイル（例: JavaScript、JSP、HTML、CSSなど）で作業する必要があります。

Tern機能を使用するには、プロジェクトで有効にする必要があります。また、使用している言語に基づいて、適切なTernモジュールを有効にする必要があります。たとえば、jQueryファイルを記述している場合は、Ternの*jQuery*モジュールを適用してコードアシストを使用する必要があります。プロジェクトのTern機能を有効にする方法については、[Enabling Code Assist Features in your Project](/docs/7-1/tutorials/-/knowledge_base/t/enabling-code-assist-features-in-your-project)を参照してください。

最初に、AlloyUIコードアシスト機能のテストを開始します。

## AlloyUIのコードアシスト機能

AlloyUIのコードを記述する際の生産性の向上に役立つコードアシスト機能がいくつかあります。開始する前に、AlloyUI機能を使用するために必要な以下のTernモジュールを有効にします。*AlloyUI*、*Browser*、*JSCS*、 *Liferay*、および*YUI Library*です。以下の例は、プロジェクトの`main.js`でAlloyUIコードアシスト機能にアクセスする方法を示しています。

1. プロジェクトの`main.js`ファイルを開き、以下のコードを入力します。

       AUI().
   
2. カーソルを`AUI().`の右側に置いて、*[Ctrl]+[Space]*を押します。これにより、`AUI()`のグローバルオブジェクトのコード推論が表示されます。AlloyUIフレームワークの独自のAPIドキュメントも表示されることに注意してください。*[Enter]*キーを押して、コード補完を使用します。

![図1：この図は、JSファイルでのコード推論を表示しています。](../../../images/code-inference.png)

| **注：**コードアシストは、オブジェクトのメソッドに対して機能するだけではなく、| オブジェクトのAUI固有のTern補完に対しても機能します。たとえば、`AU`と入力して、| *[Ctrl]+[Space]*を押して、選択するオブジェクトのリストを表示させることができます。

デフォルトでは、コード推論はキーストロークの組み合わせによってトリガーされます。ただし、Dev Studioの[設定]メニューで自動アクティベーションを有効にすることができます。自動アクティベーションは、以下の手順に従って有効にできます。

1. *[ウィンドウ]* → *[設定]* → *[JavaScript]* → *[編集]* →*[コンテンツアシスト]*に移動します。

2. *[自動アクティベーションを有効にする]*というチェックボックスをオンにして、*[適用して閉じる]*をクリックします。

   以下の図は、自動アクティベーションを有効にする方法を示しています。

   ![図2：*[自動アクティベーションを有効にする]*のチェックボックスは、*[自動アクティベーション]*の見出しの下に表示されています。](../../../images/enable-auto-activation.png)

前の例に従ってトリガーキーを押すと、すぐにコード推論がアクティブになります。この場合は、*.*（ピリオド）キーがトリガーキーです。

AlloyUIの一般的なコード推論に加えて、コードテンプレートにもアクセスできます。AUI JavaScriptテンプレートは、`<script>`および`<aui-script>`タグを使用する場合、EclipseのJavaScriptエディタとHTML/JSPエディタで使用できます。以下の手順に従って、AUIコードテンプレートを使用します。

1. `main.js`に以下のコードを入力します。

       AUI
   
2. *[Ctrl]+[Space]*を押して`AUI`のコード推論を表示すると、利用可能なすべてのAlloyUIコードテンプレートのリストとドキュメントが表示されます。

   ![図3：Dev Studioを使って、JSおよびJSPエディタにあるAUIコードテンプレートにアクセスできます。](../../../images/code-template.png)

3. テンプレートを選択し、*[Enter]*を押してその内容を`main.js`に貼り付けます。

   | **注：**Dev Studioの[設定]メニューに移動し、*[JavaScript]*| → *[エディタ]* → *[テンプレート]*を選択すると、| インストールしたAlloyUIコードテンプレートをすべて表示できます。

JSファイルのコード推論に加えて、`<aui:script>`タグを使用してJSP/HTMLファイルのコード推論を使用することもできます。

プロジェクトのJSPのいずれかを開き、まだASP taglibディレクティブがJSPにない場合は以下のようにして追加します。

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

また、*[Taglib imports]* → *[Liferay AUI Taglib Import v6.0]*の下にあるスニペットメニュー（*[ウィンドウ]* → *[ビュー表示]* → *[スニペット]*）からインポートを追加することもできます。

1. JSP内に`<aui:script>`タグを追加し、以下のコードのように設定します。

       <aui:script>
       aui
       </aui:script>
   
2. `aui`の右側にカーソルを置いて*[Ctrl]+[Space]*を押すと、コード推論が表示されます。

 これで、JavaScriptファイル内でもJSP内でも、ワークフローを改善するコードアシスト機能にアクセスできるようになりました。

次は、Dev StudioのJavaScriptコードアシスト機能について説明します。

## JavaScriptのコードアシスト機能

AlloyUIのコードアシスト機能に加えて、生のJavaScriptを使用してコードの推論と補完にもアクセスすることができます。Ternモジュールの`Liferay`が有効になっているため、プロジェクトでこのコードアシスト機能を使用することができるのです。このプラグインは、Liferay Portalでの実行時にポートレットで使用可能な静的JavaScriptオブジェクトであるAPIのコード補完を提供します。EclipseでのTernモジュールの有効化の詳細については、[Enabling Code Assist Features in Your Project](/docs/7-1/tutorials/-/knowledge_base/t/enabling-code-assist-features-in-your-project)を参照してください。

以下の例は、コードアシスト機能を使用して、ポートレットプロジェクトの機能に簡単にアクセスする方法です。

1. ポートレットの`main.js`を開き、以下の関数を追加します。

       function say(text){
       alert(text);
       }
   
2. 次に、以下のボタンをポートレットの`view.jsp`に追加します。

       <aui:button onClick=""/>
   
3. そして、`onClick`属性の引用符内にカーソルを置き、*[Ctrl]+[Space]*を押します。コード推論ダイアログがポップアップし、使用可能なJavaScript関数のリストが表示されます。

4. *「say」*と入力すると、リストが新しい`say(text)`関数に絞り込まれていることがわかります。

   ![図4：JavaScriptのコードアシスト機能により、関数に簡単にアクセスできます。](../../../images/js-code-inference.png)

5. `say(text)`関数を選択すると、関数のパラメータと関数が配置されているファイルパスを提供するドキュメントが添付されていることがわかります。

6. *[Enter]*キーを押してコード補完を使用し、ボタンに関数を追加します。

このように、JavaScript開発はDev Studioのコードアシスト機能を使用して簡単に行うことができます。これまでに、AlloyUIおよびJavaScriptのコードアシスト機能の使用方法を紹介しましたが、次はCSSのコードアシスト機能の使用方法を説明します。

## CSSのコードアシスト機能

Dev Studioは、CSSのコード推論および補完ツールを提供しています。これらのツールを使用するには、追加のプラグインをインストールする必要があります。

| **注：** 以下で説明するプラグインは、近い将来、Liferay| Dev Studioにバンドルされる予定です。プラグインの初期テストで、場合によってはパフォーマンス| に問題が発生することが明らかになったため、まだLiferay Dev Studioにバンドルされていません。
| 問題は一貫していないため、プラグインのインストールで問題が発生することはないかもしれません。しかし、注意点として記載しています。

以下の手順に従って、Dev Studioにプラグインをインストールします。

1. *[ヘルプ]* → *[新しいソフトウェアのインストール...]*に移動します。

2. 以下のリンクを`[作業:]`入力フィールドに貼り付けます。

       http://oss.opensagres.fr/eclipse-wtp-webresources/1.1.0/
   
3. *[追加...]*をクリックし、*[WTP HTML-Webリソース]*の横にあるチェックボックスをオンにします。

4. *[次へ]*をクリックして、インストールの手順に従います。

これでプラグインがインストールされたので、次はプロジェクトでのCSS機能を有効にする必要があります。プロジェクトを右クリックして、*[プロパティ]* → *[Webリソース]* → *[CSS]*に移動します。両方のチェックボックスをオンにして、プロジェクトのCSS機能を有効にします。

これで、プロジェクトに新しいCSS機能を正常にインストールして有効にできました。

CSS機能が有効になったので、次はその使用方法を確認します。以下の手順に従って、CSSのコードアシスト機能を使用してCSSクラスを見つけます。また、以下のプロセスを使用してIDを見つけることもできます。

1. `main.css`ファイルを開き、以下のクラスを追加します。

       .sample-class {
       background-color:green;
       }
   
2. `view.jsp`内で`<aui:button/>`タグを追加し、以下のコードと一致するように設定します。

       <aui:button name="test" value="test" cssClass=""/>
   
3. `cssClass`属性の引用符内で*[Ctrl]+[Space]*を押して、CSSのコード推論を表示します。*sample-class*と入力して、目的のクラスに絞り込みます。

   ![図5：CSSのコード推論は、CSSで開発する際のワークフローを改善します。](../../../images/css-code-inference.png)

   コード推論とともに、クラスのスタイリングとクラスが配置されているファイルを表示することもできます。

4. *[Enter]*キーを押してコード補完を使用し、CSSクラスをJSPに追加します。

CSSクラスのコード推論ダイアログを見ると、独自のCSSクラスに加えて、Liferay PortalにあるBootstrap CSSクラスにもアクセスできることがわかります。

| **注：**JSPの上にカーソルを合わせ、`[Ctrl]`（Windows）または| `[command]`（Mac）キーを押したままにし、表示されるハイパーリンクをクリックすると、| クラス、ID、または関数が置かれているファイルに移動することができます。

最後に、jQueryのコードアシスト機能について説明します。

## jQueryのコードアシスト機能

jQueryでコードアシストを使用することもできます。これを行うには、`jQuery`のTernモジュールを有効にする必要があるので、ここでは、[Enabling Code Assist Features in Your Project](/docs/7-1/tutorials/-/knowledge_base/t/enabling-code-assist-features-in-your-project)チュートリアルの手順に従って、プロジェクトでTernモジュールを有効にする方法を説明します。

jQueryのTernプラグインは、jQueryフレームワークの型情報を提供します。以下の例では、jQueryのコードアシスト機能をテストします。

1. プロジェクトの`jquery.js`ファイルを開きます。

2. ファイルに、以下のサンプル変数を入力します。

       var form =
   
3. *[Ctrl]+[Space]*を押して、宣言している変数のコード推論を表示します。すると、利用可能なすべてのリストが表示されます。また、各メソッドに対してjQueryのドキュメンテーションが利用可能であることにも注意してください。jQueryでコードアシストを使用する例については、以下の図を確認してください。

   ![図6：jQueryのコードアシスト機能を使用すると、利用可能なものと各オプションの背景にあるドキュメンテーションを表示する手間が省けます。](../../../images/jquery-js-1.png)

   さらに、jQueryのコールバックハンドラーでは、パラメータの型情報も利用できます。

   ![図7：jQueryコードアシストは、パラメータの型情報も表示します。](../../../images/jquery-js-2.png)

 Dev Studioのフロントエンド開発のためのコードアシスト機能を使用したワークフローの改善方法についての説明は、以上です。

## 関連トピック

[Enabling Code Assist Features in your Project](/docs/7-1/tutorials/-/knowledge_base/t/enabling-code-assist-features-in-your-project)

[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)

[From Liferay 6 to @product-ver@](/docs/7-1/tutorials/-/knowledge_base/t/from-liferay-6-to-liferay-7)
