---
header-id: jsp-overrides-using-custom-jsp-bag
---

# カスタムJSPバッグを使用したJSPのオーバーライド

[TOC levels=1-4]

LiferayのJSPをオーバーライドするAPIベースのアプローチ（つまり、[動的インクルード](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps-with-dynamic-includes)と[ポートレットフィルター](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-portlet-filters)）は、アプリおよびコアのJSPをオーバーライドする最良の方法です。カスタムJSPバッグを使用して、コアJSPをオーバーライドすることもできます。しかし、このアプローチはAPIベースのアプローチほど安定していません。カスタムJSPバッグのJSPにバグがある場合（コードやLiferayの変更のせいで）、ほとんどの場合は実行時に、機能が壊れたり、厄介なログエラーが表示されたりすることで気付くはずです。カスタムJSPバッグを使用してJSPをオーバーライドすることはバッドプラクティスであり、Extプラグインを使用して@product@をカスタマイズするのと同じです。ただし、既存のカスタムJSPバッグを維持している場合は、このチュートリアルでその動作について説明します。

**重要**：Liferayは、カスタムJSPバッグを使用してオーバーライドされたJSPのアップグレードを保証できません。

カスタムJSPバッグモジュールは、次の基準を満たす必要があります。

- 拡張するJSPのカスタムJSPを提供および指定している。

- カスタムJSPを提供するための[`CustomJspBag`](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/deploy/hot/CustomJspBag.html)実装が含まれている。

モジュールは、このコードをLiferayのOSGiランタイムに転送します。
[新しいモジュールを作成](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)したら、カスタムJSPを指定します。

## カスタムJSPの指定

@product@のコアJSPをオーバーライドするJSPを作成します。Mavenの[標準ディレクトリレイアウト](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)を使用している場合は、`src/main/resources/META-INF/jsps`の下にJSPを配置します。以下をオーバーライドする場合：

    portal-web/docroot/html/common/themes/bottom-ext.jsp

カスタムJSPを次のように配置します。

    [your module]/src/main/resources/META-INF/jsps/html/common/themes/bottom-ext.jsp

**注**：モジュール内`src/main/resources/META-INF/jsps`以外の場所にカスタムJSPを配置する場合は、その場所をモジュールの`bnd.bnd`ファイル内の`-includeresource: META-INF/jsps=`ディレクティブに割り当てます。
たとえばモジュール内のフォルダ`src/META-INF/custom_jsps`にカスタムJSPを配置する場合は、`bnd.bnd`内で次のように指定します。

 -includeresource: META-INF/jsps=src/META-INF/custom_jsps

## カスタムJSPバッグを実装する

@product@（特に[`CustomJspBagRegistryUtil`クラス](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/deploy/hot/CustomJspBagRegistryUtil.html)）は、[`CustomJspBag`](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/deploy/hot/CustomJspBag.html)サービスからJSPを読み込みます。次の手順では、カスタムJSPバッグを実装します。

1. モジュールで、[`CustomJspBag`](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/deploy/hot/CustomJspBag.html)を実装するクラスを作成します。

2. 次のように、`@Component`アノテーションを追加して、クラスをOSGiサービスとして登録します。

       @Component(
       immediate = true,
       property = {
       "context.id=BladeCustomJspBag",
       "context.name=Test Custom JSP Bag",
       "service.ranking:Integer=100"
       }
       )
   
   - **`immediate = true`**：モジュールのアクティベーションでサービスを利用できるようにします。
   - **`context.id`**：カスタムJSPバッグのクラス名。`BladeCustomJspBag`をクラス名に置き換えます。
   - **`context.name`**：人間が読み取れるサービス名。独自の名前に置き換えます。
   - **`service.ranking:Integer`**：実装の優先順位。コンテナは、優先度が最も高い実装を選択します。

3. `getCustomJspDir`メソッドを実装して、JSPが存在するモジュールのJAR内のフォルダパスを返します（例：`META-INF/jsps`）。

       @Override
       public String getCustomJspDir() {
       return "META-INF/jsps/";
       }
   
4. `activate`メソッドと次のフィールドを作成します。このメソッドは、モジュールがアクティブ化されると、すべてのカスタムJSPのURLパスをリストに追加します。

       @Activate
       protected void activate(BundleContext bundleContext) {
       _bundle = bundleContext.getBundle();
       
       _customJsps = new ArrayList<>();
       
       Enumeration<URL> entries = _bundle.findEntries(
       getCustomJspDir(), "*.jsp", true);
       
       while (entries.hasMoreElements()) {
       URL url = entries.nextElement();
       
       _customJsps.add(url.getPath());
       }
       }
       
       private Bundle _bundle;
       private List<String> _customJsps;
   
5. このモジュールのカスタムJSP URLパスのリストを返す`getCustomJsps`メソッドを実装します。

       @Override
       public List<String> getCustomJsps() {
       return _customJsps;
       }
   
6. 新しい`com.liferay.portal.kernel.url.URLContainer`を返す`getURLContainer`メソッドを実装します。URLコンテナをインスタンス化し、`getResources`および`getResource`メソッドをオーバーライドします。`getResources`メソッドは、指定されたパスで、コンテナ内のリソースへのすべてのパスを検索します。一致するカスタムJSPパスの`Strings`の`HashSet`が返されます。`getResource`メソッドは、その名前ごとに1つの特定のリソースを返します（パスを含む）。サンプルの`BladeCustomJspBag`クラスは、次のように`getURLContainer`を実装します。

       @Override
       public URLContainer getURLContainer() {
       return _urlContainer;
       }
       
       private final URLContainer _urlContainer = new URLContainer() {
       
       @Override
       public URL getResource(String name) {
       return _bundle.getEntry(name);
       }
       
       @Override
       public Set<String> getResources(String path) {
       Set<String> paths = new HashSet<>();
       
       for (String entry : _customJsps) {
       if (entry.startsWith(path)) {
       paths.add(entry);
       }
       }
       
       return paths;
       }
       
       };
   
7. `true`を返す`isCustomJspGlobal`メソッドを実装します。

       @Override
       public boolean isCustomJspGlobal() {
       return true;
       }
   
これで、モジュールはカスタムJSPとカスタムJSPバッグの実装を提供します。デプロイすると、@product@は、オーバーライドするコアJSPの代わりにカスタムJSPを使用します。

## JSPを拡張する

コアJSPに何かを追加する場合は、空の`-ext.jsp`があるかを確認し、JSP全体ではなくそれをオーバーライドします。JSP全体が大幅に変更されると、その過程でカスタマイズが崩れる可能性があるため、こうすることでよりシンプルかつ安定した状態を維持できます。`-ext.jsp`をオーバーライドすると、`-ext.jsp`を含む元のJSPのみに依存します。たとえば、`portal-web/docroot/html/common/themes/bottom.jsp`を開き、最後までスクロールしてください。
以下が表示されます。

    <liferay-util:include page="/html/common/themes/bottom-ext.jsp" />

`bottom.jsp`に何かを追加する必要がある場合は、`bottom-ext.jsp`をオーバーライドします。

@product@ 7.0以降、以前は`html/common/themes`に含まれていた以下のJSPファイルのコンテンツがインライン化され、パフォーマンスが向上しました。

- `body_bottom-ext.jsp`
- `body_top-ext.jsp`
- `bottom-ext.jsp`
- `bottom-test.jsp`

これらはコードベースの明示的なファイルではなくなりました。ただし、モジュールでそれらを作成して、機能とコンテンツを追加することは引き続き可能です。

このタイプのカスタマイズは最後の手段であることに留意してください。この実装の性質によりオーバーライドが壊れる可能性があり、Liferayのコア機能がそれとともに機能しなくなる可能性があります。オーバーライドするJSPが別のモジュールにある場合は、[JSPをオーバーライドするためのLiferay APIベースのアプローチ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps)に関するセクションを参照してください。

## サイトスコープのJSPカスタマイズ

Liferay Portal 6.2では、[アプリケーションアダプタ](/docs/6-2/tutorials/-/knowledge_base/t/customizing-sites-and-site-templates-with-application-adapters)を使用して、コアJSPのカスタマイズの対象を特定のサイトに絞り込むことができました。@product@ 7.0ではJSPの大部分がモジュールに移行されたため、このユースケースは大幅に縮小しました。コアJSPのカスタマイズの対象を特定のサイトに絞り込む必要がある場合は、[Liferay Portal 6.2の場合と同様に](/docs/6-2/tutorials/-/knowledge_base/t/customizing-sites-and-site-templates-with-application-adapters)アプリケーションアダプタを準備して、 @product-ver@にデプロイします。これで引き続き機能します。ただし、このアプローチは@product-ver@では非推奨であり、Liferay 8.0ではまったくサポートされていないことに注意してください。

<!-- Uncomment once we cover scoping to a site
If you're interested in scoping a module's JSP customization to a site, that's
another story. See the documentation on [using Dynamic Include](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps-with-dynamic-includes).
-->

## 関連トピック

[Upgrading Core JSP Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-jsp-hooks)
