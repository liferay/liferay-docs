---
header-id: generating-a-jsf-project-using-ide
---

# Dev Studioを使用したJSFプロジェクトの生成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Dev Studioを使用して、独自のフォルダー構造と記述子ファイルを手動で作成することなく、Liferay Facesアプリケーションを生成できます。 あなたは手動で、またはあなたが基本的なJSFアプリケーションの構造を調べたい場合は、JSFアプリケーションの構造を作成するに興味があれば、訪問 [JSFプロジェクトの手動での作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-jsf-project-manually) チュートリアルを。

このチュートリアルでは、Liferay Dev Studioを使用してサンプルのJSFプロジェクトを生成します。 Dev Studioインスタンスを開いて開始します。

1.  *ファイル* → *新規* → *プロジェクト...*ます。 これにより、新しいプロジェクトウィザードが開きます。

2.  *Liferay* プロジェクトを選択し、リストされたサブプロジェクトから *Liferay JSF Project* を選択します。 次に、 *Next*クリックします。

    ![図1：* Liferay JSF Project *オプションを選択して、Dev StudioでJSFプロジェクトの作成を開始します。](../../../images/jsf-project-ide.png)

3.  JSFプロジェクトに名前、ビルドフレームワーク（GradleまたはMaven）、およびコンポーネントスイートを割り当てます。 次から選択できる5つのコンポーネントスイートがあります。

      - ICEFaces
      - JSF標準
      - Liferay Faces Alloy
      - プライムフェイス
      - リッチフェイス

    ![図2：JSFプロジェクトに適したオプションを選択します。](../../../images/new-jsf-project-ide.png)

4.  Liferay JSFプロジェクトを生成するには、 *完了* をクリックします。

Dev Studio \を使用してLiferay JSFプロジェクトを生成しました！ 生成したプロジェクトには、カスタマイズ可能なシンプルなポートレットが含まれています。

![図3：生成されたJSFポートレットプロジェクトには、基本的なビルド情報が表示されます。](../../../images/jsf-ide-generated-project.png)

| **注：** Dev Studioの *ファイル* → *新規* メニューに別のオプションがあります| *Liferay JSFポートレット*。 これは、既存のJSFにポートレットを追加することを目的としています。プロジェクト。 現在、これはLiferay Portal 6.2 JSFを作成するためにのみ構成されています。ポートレット。 @product-ver@向けに開発している場合は、このオプションを使用しないでください。

新しいJSFプロジェクトを@product@インスタンス</a>に

デプロイするには、Liferayサーバーにドラッグアンドドロップします。</p> 

ファンタスティック\！ Liferay Dev Studio \を使用して、Liferay JSFプロジェクトを迅速に生成できるようになりました！
