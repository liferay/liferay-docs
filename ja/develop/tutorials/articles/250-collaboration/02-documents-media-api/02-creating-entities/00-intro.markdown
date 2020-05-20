---
header-id: creating-files-folders-and-shortcuts
---

# ファイル、フォルダー、およびショートカットの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

APIの主な使用例は、ドキュメントとメディアライブラリにファイル、フォルダー、およびファイルショートカットを作成することです。

他のLiferay APIを使用したことがある場合、Docs & Media APIも同じ規則に従います。 一般に、同様のことを行うメソッドは、同様の名前を持つ傾向があります。 エンティティを作成する必要がある場合（それが何であれ）、パターン `add[ModelName]`に従うメソッドを探します。ここで、 `[ModelName]` はエンティティのデータモデルオブジェクトの名前です。 [入門チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) 説明しているように、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) を使用してAPIにアクセスします。 このサービスオブジェクトには、エンティティを追加するための次のメソッドが含まれています。

  - `addFileEntry`：ファイルを追加します。
  - `addFolder`：フォルダーを追加します。
  - `addFileShortcut`：ファイルにショートカットを追加します。

以下のチュートリアルでは、これらの方法の使用方法を示します。
