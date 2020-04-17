---
header-id: creating-language-keys
---

# 言語キーの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">メッセージとエラーの表示</p><p>手順1/3</p>
</div>

最新のアプリケーションでは、メッセージを作成し、複製して複数の言語に翻訳できる言語キーファイルにフィールドラベルを配置する必要があります。 ここで、あなたはどのように提供するかを学びます *デフォルト* アプリケーションのための英語キーのセットを。 より多くの言語キーの情報と自動的に翻訳言語キーを提供するために、 [、このチュートリアルを参照](/docs/7-1/tutorials/-/knowledge_base/t/automatically-generating-language-files)。

言語キーは、 `guestbook-web` モジュールに含まれる `Language.properties` ファイルに保存されます。 `Language.properties` がデフォルトですが、ファイル名にISO-639言語コードを追加することで多数の翻訳を作成できます（たとえば、スペイン語の場合は `Language_es.properties` 、ドイツ語の場合は `Language_de.properties`）。 今のところ、デフォルトの言語キーに固執します。

次の手順に従って、言語キーを作成します。

1.  `guestbook-web` モジュールで `/src/main/resources/content/Language.properties` を開きます。 このファイルのデフォルトキーを削除します。

2.  次のキーを貼り付けます。
   
        entry-added=Entry added successfully.
        entry-deleted=Entry deleted successfully.
        guestbook-added=Guestbook added successfully.
        guestbook-updated=Guestbook updated successfully.
        guestbook-deleted=Guestbook deleted successfully.

3.  ファイルを保存します。

これでメッセージが配置され、アプリケーションで使用できるようになりました。 次に、アクションメソッドに追加します。
