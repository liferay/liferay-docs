---
header-id: generating-a-jsf-application
---

# コマンドラインからJSFプロジェクトを生成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

独自のフォルダー構造、記述子ファイルなどを手動で作成することなく、Liferay Facesアプリケーションを生成できます。 あなたが本当に手動でそれを行うにしたい場合は、JSFアプリケーションの構造を調べ、中にゼロから1を作成することができます [JSFプロジェクトの手動での作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-jsf-project-manually) チュートリアルを。

<div class="video-thumbnail"></div>

\！P[ビデオサムネイル](https://portal.liferay.dev/documents/113763090/113919826/jsf-vid-thumbnail.png)

Before generating your JSF application, you should first visit [liferayfaces.org](http://liferayfaces.org/), a great reference spot for JSF application development targeted for @<product@>. このサイトでは、JSFアプリケーションのオプションを選択し、選択したオプションでアプリケーションを生成するために実行できるMavenアーキタイプコマンドを生成できます。 次のアーキタイプオプションを選択できます。

  - Liferayポータルバージョン
  - JSFバージョン
  - コンポーネントスイート

ビルドフレームワーク（GradleまたはMaven）を選択し、依存関係のリストを生成してページに表示することもできます。 依存関係は、選択したビルドタイプに応じて、 `pom.xml` または `build.gradle` ファイルのサイトページで提供されます。 これは、JSFアプリケーションを生成する前に、JSFアプリケーションで必要な依存関係を把握できるので便利です。

**注：** Gradle開発者は、 `archetype：generate` コマンドを使用することもできます。これは、 `build.gradle` と `pom.xml` 両方のファイルを生成するためです。

![図1：アーキタイプ生成コマンドにLiferay Portalバージョン、JSFバージョン、およびコンポーネントスイートを選択できます。](../../../images/jsf-app-generation.png)

次に、コマンドラインから [liferayfaces.org](http://liferayfaces.org/)を使用して、サンプルのJSFアプリケーション（Liferay Portal 7 + JSF 2.2 + JSF Standardなど）を生成します。

1.  [liferayfaces.org](http://liferayfaces.org/) に移動し、次のオプションを選択します。

      - **Liferayポータル：** 7
      - **JSF：** 2.2
      - **コンポーネントスイート：** JSF標準

2.  アーキタイプ生成コマンドをコピーして実行します。 プロジェクトを生成するフォルダーに移動したことを確認してください。

それでおしまい\！ JSFアプリケーションが現在のフォルダーに生成されます\！

MavenのインタラクティブなアーキタイプUIを使用して、Liferay JSFアプリケーションを生成することもできます。 これを行うには、 `mvn archetype：generate -Dfilter = liferay` を実行し、使用するJSFアーキタイプを選択します。 次に、各オプションを順に実行して、バージョン、グループID、アーティファクトIDなどを選択します。 この詳細については、参照 [原型利用した発電の新プロジェクトを](/docs/7-1/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes) チュートリアルを。

あなたのJSFアプリケーションが生成されたら、することができます [のLiferay IDE @ @へのインポート、それを](/docs/7-1/tutorials/-/knowledge_base/t/using-maven-in-liferay-ide#importing-maven-projects) し、さらにそれを開発。 [@product@インスタンス](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-liferay-ide)にデプロイするには、それを@product@サーバーにドラッグアンドドロップします。

プロジェクトをビルドして、コマンドラインから@product@にデプロイすることもできます！ Gradleを使用している場合は、次のコマンドを実行してJSFアプリケーションをビルドします。

    ../gradlew build

Mavenの場合、次のコマンドを実行します。

    mvn package

次に、生成されたWARを@product@の `deploy` フォルダーにコピーします。

    [cp|copy] ./com.mycompany.my.jsf.portlet.war LIFERAY_HOME/deploy

驚くばかり\！ JSFアプリケーションを生成し、コマンドラインを使用してデプロイしました。

<div class="video-wrapper" data-name="Developing a New JSF Portlet">
</div>

\！V[ビデオチュートリアル](https://portal.liferay.dev/documents/113763090/113919826/developing-a-new-jsf-portlet.mp4|https://portal.liferay.dev/documents/113763090/113919826/developing-a-new-jsf-portlet.mkv)
