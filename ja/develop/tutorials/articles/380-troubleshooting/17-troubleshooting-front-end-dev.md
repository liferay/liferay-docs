---
header-id: troubleshooting-front-end-development-issues
---

# フロントエンド開発の問題のトラブルシューティング

[TOC levels=1-4]

フロントエンド開発には、多くの可動部分が含まれます。途中で遭遇する問題の原因を特定するのが難しい場合があります。これは特にもどかしいことです。ここに示すよくある質問と回答は、フロントエンド開発中に発生する問題のトラブルシューティングと修正に役立ちます。

トラブルシューティングのセクションは次のとおりです。

- [CSS](#css)
- [モジュール](#modules)
- [ポートレット](#portlets)
- [テンプレート](#templates)
- [テーマ](#themes)

質問をクリックすると回答が表示されます。

## CSS

- [CSSテンプレートがAngularアプリに適用されないのはなぜですか？](#broken-css-angular-app)
- [Liferay PortalのCSSがInternet Explorerで壊れているのはなぜですか？](#portal-css-broken-ie)

<div class="ldn-faq-question" id="broken-css-angular-app">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">CSSテンプレートがAngularアプリに適用されないのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>Angularに既知の<a href="https://github.com/angular/angular/issues/4974">バグ</a>があると、CSSファイルの絶対URLが認識されなくなります。</p>
    <p>ポータルの性質上、アプリは任意のページに配置できるため、相対URLもオプションではありません。</p>
    <p>これを修正するには、CSSにテーマまたはテーマレットを提供するか、またはAngularコードを含むポートレットの<code>com.liferay.portlet.header-portlet-css</code>プロパティを使用してCSSファイルへのパスを指定します。</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="portal-css-broken-ie">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Liferay PortalのCSSがInternet Explorerで壊れているのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>デフォルトでは、CSSファイルはブラウザで縮小されます。 これにより、Internet Explorerで問題が発生する可能性があります。<code>portal-ext.properties</code>ファイルに<code>theme.css.fast.load=false</code>および<code>minifier.enabled=false</code>を含めることにより、この動作を無効にできます。 </p>
  </div>
</div>

## モジュール

- [ロードしようとすると、JQueryモジュールが匿名モジュールエラーをスローするのはなぜですか？](#jquery-anonymous-module-error)
- [AngularまたはTypescriptモジュールにソースマップが表示されないのはなぜですか？](#source-maps-not-showing)
- [複数のプロジェクトにliferay-npm-bundlerを使用しています。すべてのプロジェクトでツール全体の分析追跡を無効にするにはどうすればよいですか？](#disable-bundler-analytics)

<div class="ldn-faq-question" id="jquery-anonymous-module-error">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">ロードしようとすると、JQueryモジュールが匿名モジュールエラーをスローするのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>ホストする外部ライブラリを使用している場合は、<a href="https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/using-external-javascript-libraries#using-libraries-that-you-host">Using External JavaScript Libraries</a>のチュートリアルで説明されているように、<i>Expose Global</i>オプションを無効にする必要があります。</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="source-maps-not-showing">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">AngularまたはTypescriptモジュールにソースマップが表示されないのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>これは<a href="https://issues.liferay.com/browse/LPS-83052">LPS-83052</a>によるものです。</p>
    <p>これを解決するには、引数または<code>tsconfig.json</code>ファイルを使用して、<a href="https://www.typescriptlang.org/docs/handbook/compiler-options.html"><code>inlineSources</code>コンパイラオプション</a>をアクティブにします。</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="disable-bundler-analytics">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">複数のプロジェクトにliferay-npm-bundlerを使用しています。プロジェクトでliferay-npm-bundlerの分析追跡を無効にするにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>レポートを無効にするために使用できるオプションがいくつかあります。</p>
    <ul>
      <li><p><code>package.json</code>のビルドスクリプトで<code>--no-tracking</code>フラグを使用して、レポートを無効にします。</p>
      <p><pre><code>liferay-npm-bundler --no-tracking</code></pre></p>
      </li>
      <li>
      <p>プロジェクトのルートフォルダまたはその祖先のいずれかに<code>.liferay-npm-bundler-no-tracking</code>ファイルを作成して、レポートを無効にします。</p>
      <p>つまり、質問「<code>liferay-npm-bundlerは、時間の経過とともにツールを改善するための使用統計を匿名で報告できますか？</code>」に対する回答は<code>いいえ</code>です。</p>
      </li>
    </ul>
  </div>
</div>

## ポートレット

- [Angular/React/Vueポートレットでカスタムルーターを使用したいです。ポートレットでデフォルトのSenna JS SPAエンジンを無効にするにはどうすればよいですか？](#angular-react-vue-portlet-disable-spa)

<div class="ldn-faq-question" id="angular-react-vue-portlet-disable-spa">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Angular/React/Vueポートレットでカスタムルーターを使用したいです。ポートレットでデフォルトのSenna JS SPAエンジンを無効にするにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>デフォルトでは、<a href="https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/automatic-single-page-applications#what-is-sennajs">Senna JS SPAエンジン</a>はポートレットとサイトで有効になっています。これにより、ポートレットナビゲーション中のページ全体のリロードが無効になります。</p>
    <p>代わりにポートレットでカスタムルーターを使用する場合は、<a href="https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/automatic-single-page-applications#disabling-spa">SPAのドキュメントの指示</a>に従って、SPAからポートレットをブラックリストに追加してください。</p>
  </div>
</div>

## テンプレート

- [ページを更新するとWebコンテンツが壊れるのはなぜですか？](#cacheable-web-content-taglibs)

<div class="ldn-faq-question" id="cacheable-web-content-taglibs">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">ページを更新するとWebコンテンツが壊れるのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p><code>liferay-map</code> taglibなどの一部のtaglibは、キャッシュ可能なテンプレート（FreeMarkerやVelocityなど）で使用する場合に制限があります。たとえば、<code>liferay-map</code> taglibがキャッシュ可能なテンプレートで使用されている場合にユーザーがページを更新すると、マップは表示されません。 </p>
    <p>考えられる回避策の1つは、テンプレートを編集し、キャッシュ可能なオプションをオフにすることで、テンプレートのキャッシュを無効にすることです。または、<code>[System Settings]</code>→<code>[Template Engines]</code>に移動し、<code>[Resource Modification Check]</code>を<code>0</code>に設定して、すべてのテンプレートのキャッシュを無効にすることができます。 </p>
    <p>ただし、ベストプラクティスとして、キャッシュ可能なWebコンテンツではtaglibを使用しないことをお勧めします。 </p>
  </div>
</div>

## テーマ

- [Classicテーマをベーステーマとして使用するにはどうすればよいですか？](#classic-base-theme)
- [テーマにOSGiヘッダーを含めるにはどうすればよいですか？](#osgi-headers-in-themes)
- [テーマを再デプロイした後、変更内容が表示されないのはなぜですか？](#developer-mode)
- [テーマが読み込まれないのはなぜですか？代わりにデフォルトのテーマが返されます。](#default-theme-returned)

<div class="ldn-faq-question" id="classic-base-theme">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Classicテーマをベーステーマとして使用するにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>Classicテーマはすでに既存のベーステーマの実装であるため、拡張しないでください。必要に応じて、 <a href="/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files">Gulpキックスタート</a>タスクを使用して、Classicテーマからご自身のテーマにファイルをコピーできます。基本的なスタイル設定から始めたい場合は、代わりにStyledテーマから始めてください。</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="osgi-headers-in-themes">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">テーマにOSGiヘッダーを含めるにはどうすればよいですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>使用するヘッダーをテーマの<code>liferay-plugin-package.properties</code>ファイルで指定します。このファイルに配置されたヘッダーは、マニフェストとOSGiバンドルに自動的に含まれます。</p>
    <p>たとえば、エクスポートされたパッケージを<code>Import-Package</code>ヘッダーでインポートすることにより、テーマにOSGiの依存関係を追加できます。</p>
    <pre><code>Import-Package:com.liferay.docs.portlet</code></pre>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="developer-mode">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">テーマを再デプロイした後、変更内容が表示されないのはなぜですか？<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>デフォルトでは、CSS、JS、およびテーマテンプレートファイルはブラウザにキャッシュされます。開発中に、<a href="/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes">Devloperモード</a>を有効にして、テーマのファイルがキャッシュされないようにすることができます。 </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="default-theme-returned">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">テーマが読み込まれないのはなぜですか？代わりにデフォルトのテーマが返されます。<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>「No theme found for specified theme id...」という警告が表示される場合、サイトで古いテーマIDを参照している可能性があります。テーマの<code>liferay-look-and-feel.xml</code>内のテーマIDが、警告メッセージ「mytheme_WAR_mytheme」内のテーマIDと一致することを確認してください。テーマIDが一致している場合、サイトテーマの代わりに古いテーマを使用しているページがある可能性があります。これを確認するには、ページを手動で確認するか、データベースで<code>themeId -</code>の値を持つレイアウトを検索します。 </p>
  </div>
</div>
