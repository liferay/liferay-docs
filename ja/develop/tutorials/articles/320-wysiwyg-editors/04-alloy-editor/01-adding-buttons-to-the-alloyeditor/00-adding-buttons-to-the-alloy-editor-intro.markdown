---
header-id: adding-buttons-to-alloyeditor-toolbars
---

# AlloyEditorのツールバーへのボタンの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

AlloyEditorのツールバーには、すぐに使える便利な機能がいくつか含まれています。 ただし、ツールバーに既存のボタンを追加する、または追加するには、あなたが作成したボタンが含まれるように、デフォルトの設定をカスタマイズすることもできます [@製品に同梱の既存のCKEditorバージョンボタンを@さんAlloyEditor](/docs/7-1/reference/-/knowledge_base/r/ckeditor-plugin-reference-guide)。 [`EditorConfigContributor` インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html)、ボタンをAlloyEditorのツールバーに追加するなど、エディターの構成を変更するために必要なすべてを提供します。 [エディターの動作を変更するCKEditor構成設定](https://docs.ckeditor.com/ckeditor4/latest/api/CKEDITOR_config.html) （UIの変更を除く）は、この構成オブジェクトを介して渡すこともできます。

`com.liferay.docs.my.button` モジュールは、これらのチュートリアル全体の例です。 独自の構成の開始点として使用したり、チュートリアルを実行したい場合は、 [Githubリポジトリ](https://github.com/liferay/liferay-docs/tree/7.1.x/develop/tutorials/code/osgi/modules/com.liferay.docs.my.button)からモジュールのzipファイルをダウンロードできます。
