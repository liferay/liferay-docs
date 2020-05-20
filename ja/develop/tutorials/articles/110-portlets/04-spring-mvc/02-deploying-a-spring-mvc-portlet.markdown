---
header-id: deploying-a-spring-mvc-portlet
---

# Spring MVCポートレットの展開

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product@のポートレットを作成する開発者は、通常、ポートレットをJava EEスタイルのWebアプリケーションARchive（WAR）アーティファクトまたはJava ARchive（JAR）OSGiバンドルアーティファクトとしてデプロイできます。 Spring MVCポートレット開発者にはそのような柔軟性はありません。 Spring MVCフレームワークはJava EE用に設計されているため、Spring MVCポートレットはWARアーティファクトとしてパッケージ化する必要があります。 したがって、WARレイアウトを想定しており、 `WEB-INF / web.xml` 記述子などのJava EEリソースが必要です。

LiferayはOSGi WAB（Web Application Bundler）標準の展開をサポートしているため、WARを展開でき、OSGiランタイムで期待どおりに実行されます。 これがLiferayで機能する理由のハイポイントです。

  - Liferay自動デプロイプロセスが実行され、 `PortletServlet` および `PlugincontextListener` 構成が `WEB-INF / web.xml` ファイルに追加されます。

  - [Liferay WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) は、OSGi対応 `META-INF / MANIFEST.MF` ファイルを自動的に作成します。 マニフェストファイルのコンテンツに影響を与える場合は、bndディレクティブとOSGiヘッダーをWABの `WEB-INF / liferay-plugin-package.properties` ファイルに直接配置できます。

[クラスパッケージ](/docs/7-1/tutorials/-/knowledge_base/t/importing-packages) インポートし、 `liferay-plugin-package.properties` ファイルの `Import-Package` ヘッダーにパッケージを追加して、ポートレットの記述子ファイルが参照します。

次に、 `Import-Package` ヘッダーの例を示し` 。</p>

<pre><code>Import-Package:\
    org.springframework.beans.factory.xml,\
    org.springframework.context.config,\
    org.springframework.security.config,\
    org.springframework.web.servlet.config
`</pre>

自動展開プロセスとLiferayのWABジェネレーターは、プロジェクトをLiferay対応のWABに変換します。 WABジェネレーターは、クラスの `import` ステートメントを検出し、すべての外部パッケージをWABの `Import-Package` ヘッダーに追加します。 ジェネレーターは、プラグインの `liferay-plugin-package.properties` のパッケージもヘッダーにマージします。

`java。*` パッケージ以外のJavaの `rt.jar` のパッケージに依存している場合、 [ポータルプロパティ `org.osgi.framework.bootdelegation`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework) をオーバーライドし、プロパティのリストに追加します。 行く [ここで](/docs/7-1/tutorials/-/knowledge_base/t/resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles#case-4-the-missing-class-belongs-to-a-java-runtime-package) 詳細については。

| **注**：組み込みJARにプロパティファイルが含まれるSpring MVCポートレット| （例えば、 `spring.handlers`、 `spring.schemas`、 `spring.tooling`）影響を受ける可能性があります|問題ごと| [LPS-75212](https://issues.liferay.com/browse/LPS-75212)。 |プロパティファイルを持つ最後のJARは、そのプロパティが唯一のJARです。結果のWABのクラスパスに追加されます。 他のJARのプロパティは追加されません。 | |たとえば、ポートレットにこれらを含む複数のJARがあるとします。プロパティファイル：| | `WEB-INF / src / META-INF / spring.handlers` | `WEB-INF / src / META-INF / spring.schemas` | `WEB-INF / src / META-INF / spring.tooling` | |最後に処理されたJARのプロパティのみが|に追加されます。クラスパス。 モジュールを使用するには、プロパティファイルがクラスパス上になければなりません。それらを使用します。 | |すべてのプロパティファイルをクラスパスに追加するには、それらを1つに結合できます。各タイプ（たとえば、1つの `spring.handlers`つの `spring.schemas`、および1つの `spring.tooling`）を `WEB-INF / src`追加します。 | |これらのファイルを結合するシェルスクリプトを次に示します。 | cat / dev / null \> docroot / WEB-INF / src / META-INF / spring.handlers | cat / dev / null \> docroot / WEB-INF / src / META-INF / spring.schemas | cat / dev / null \> docroot / WEB-INF / src / META-INF / spring.tooling | jar for $（find docroot / WEB-INF / lib / -name '\ *。jar'）;する| $（unzip -l $jar | grep -F META-INF / springのファイルの場合。 | awk '| { print $4 } | '）;する| if \ ["META-INF / spring.tld" \！= "$file" \];その後| unzip -p $jar $file \>\> docroot / WEB-INF / src /$file | echo \>\> docroot / WEB-INF / src /$file | fi |完了|完了| |シェルスクリプトを変更および使用して、JARのプロパティファイルを追加できます。クラスパス。

| **注**：異なるSpring Frameworkバージョンを使用する場合| Liferayが提供するバージョンでは、Spring Framework JARに名前を付ける必要があります|ものとは異なる| [ポータルプロパティ `module.framework.web.generator.excluded.paths`](https://docs.liferay.com/ce/portal/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework) |除外します。 Spring Framework JARの名前を変更しない場合、WABジェネレーター| LiferayのSpring Framework JARを使用していることを前提とし、次のものから除外します。生成されたWAB。 | [除外されたJARを理解する](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#understanding-excluded-jars) | @product@のSpring Frameworkバージョンを検出する方法を説明します。

Spring MVCポートレットをWARとしてパッケージ化したら、それを `[LIFERAY_HOME]/ deploy`コピーしてデプロイできます。

Spring MVCポートレットのデプロイおめでとうございます！
