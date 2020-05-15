---
header-id: troubleshooting-faq
---

# トラブルシューティングに関するFAQ

[TOC levels=1-4]

プラットフォームでコーディングする場合、明確な解決策がない問題に遭遇することがあります。これは特にもどかしいことです。アプリやモジュールの構築、デプロイ、実行に問題がある場合は、それらを迅速に解決する必要があります。ここに示すよくある質問と回答は、問題のトラブルシューティングと修正に役立ちます。

トラブルシューティングのセクションは次のとおりです。

- [モジュール](#modules)
- [サービスとコンポーネント](#services-and-components)
- [フロントエンド](/docs/7-1/tutorials/-/knowledge_base/t/troubleshooting-front-end-development-issues)

質問をクリックすると回答が表示されます。

## モジュール

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Liferayアーティファクトに依存関係を構成するにはどうすればよいですか？ <span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies">Configuring Dependencies</a>を参照してください。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">オプションのパッケージインポートとは何ですか。どのように指定できますか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>モジュールを開発する際、<em>オプションの</em>パッケージインポートを宣言できます。オプションのパッケージインポートは、使用可能な場合にモジュールが使用できるものですが、それを使用しなくても機能します。<a href="/docs/7-1/tutorials/-/knowledge_base/t/declaring-optional-import-package-requirements">オプションのパッケージインポートの指定</a>は簡単です。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">モジュールからJNDIデータソースに接続するにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>LiferayのOSGi環境からアプリケーションサーバーのJNDIデータソースへ接続するのは、Java EE環境から接続するのとほぼ同じです。唯一の違いは、OSGi環境では、<a href="/docs/7-1/tutorials/-/knowledge_base/t/connecting-to-data-sources-using-jndi">アプリケーションサーバーのJNDIクラスをロードするために@product@のクラスローダーを使用する</a>必要があることです。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">モジュールに未解決の要件があります。どうすればよいですか？ <span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>バンドルの1つが、Liferay OSGiランタイムで他のバンドルがエクスポートしないパッケージをインポートした場合、@product@は未解決の要件を報告します。</p>
    <pre><code>!could not resolve the bundles: ...
    Unresolved requirement: Import-Package: ...
    ...
    Unresolved requirement: Require-Capability ...
    </code></pre>
    <p>要件を満たすには、<a href="/docs/7-1/tutorials/-/knowledge_base/t/resolving-bundle-requirements">機能を提供するモジュールを見つけ、それをビルドファイルの依存関係に追加して、デプロイします</a>。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">IllegalContextNameExceptionによって、バンドルのコンテキスト名がBundle-SymbolicName構文に従っていないことが報告されました。コンテキスト名を修正するにはどうすればよいですか？ <span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/resolving-bundle-symbolicname-syntax-issues">構文に従うように<code>Bundle-SymbolicName</code>を調整してください。</a> </p>
  </div>
</div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">モジュールのJavaScriptとCSSの変更が表示されないのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/why-arent-my-modules-javascript-and-css-changes-showing">コンポーネントプロパティが誤っていたりブラウザキャッシュが古いと、JavaScriptおよびCSSの変更が表示されない場合があります</a>。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">フラグメントのJSPのオーバーライドが表示されないのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/why-arent-jsp-overrides-i-made-using-fragments-showing"><code>Fragment-Host</code>のバンドルバージョンがホストのバンドルバージョンと互換性があることを確認してください</a>。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">フラグメントホストから使用しているパッケージが解決しないのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/why-is-a-package-i-use-from-the-fragment-host-unresolved">ホストがエクスポートしていないホストパッケージはインポートしないでください（<code>Import-Package: ...</code>）</a>。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">アプリケーションサーバーとデータベースは起動しましたが、@product@がデータベースへの接続に失敗しました。何が起こったのですか。修正するにはどうしたらよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>準備ができていないデータベースサーバーに接続しようとすると、@product@の初期化が失敗する場合があります。<a href="/docs/7-1/tutorials/-/knowledge_base/t/portal-failed-to-initialize-because-the-database-wasnt-ready">JDBC接続を再試行するように起動を構成する</a>と、@product@をデータベースに接続しやすくなります。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">モジュールのロギングを調整するにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/adjusting-module-logging">Adjusting Module Logging</a>を参照してください。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">モジュールまたはプラグインにロギングを実装するにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/implementing-logging">メッセージをログに記録するには、Simple Logging Facade for Java（SLF4J）を使用します</a>。</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">新しいデータベースタイプに移行したときに、エンティティの並べ替え順序が変更されたのはなぜですか？ <span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/sort-order-changed-with-a-different-database">新しいデータベースでは、異なるデフォルトのクエリ結果の順序が使用されています。異なる順序を設定できます</a>。</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Service Builderエンティティ間のリレーショナルマッピングを作成した後、ポートレットが大量のメモリを使用しています。どうすればよいですか？ <span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/disabling-cache-for-table-mapper-tables">エンティティマッピングに関連するキャッシュを無効にすると、メモリ使用量が低下します</a>。</p>
</div>
</div>

## サービスとコンポーネント

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">OSGiコンテナで何が起こっているかを確認するにはどうすればよいですか？ <span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/system-check">システムチェックを実行します</a>。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">未解決のOSGiコンポーネントを検出するにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span> 
  <div class="hide">  
    <p>Service Builderを使用するモジュールコンポーネントはDependency Manager（DM）を使用し、他のほとんどのモジュールコンポーネントはDeclarative Services（DS）を使用します。<a href="/docs/7-1/tutorials/-/knowledge_base/t/detecting-unresolved-osgi-components">Gogoのシェルコマンドとツールを使用すると、DMおよびDSコンポーネントの不満足なコンポーネント参照を見つけて検査できます</a>。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">OSGi以外のコードからOSGiサービスを呼び出す最も安全な方法は何ですか？ <span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/service-trackers">Calling Non-OSGi Code that Uses OSGi Services</a>を参照してください。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">ファイルを使用してコンポーネントを構成するにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/using-files-to-configure-product-modules">Using Files to Configure Module Components</a>を参照してください。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">ExtプラグインからOSGiサービスにアクセスするにはどうすればよいですか？ <span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-1/tutorials/-/knowledge_base/t/using-osgi-services-from-ext-plugins">`ServiceTracker`を使用します</a>。 </p>
  </div>
</div>
