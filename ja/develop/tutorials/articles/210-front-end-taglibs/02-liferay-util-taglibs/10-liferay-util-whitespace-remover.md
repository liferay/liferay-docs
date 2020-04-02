---
header-id: using-liferay-util-whitespace-remover
---

# Liferay Util Whitespace Removerの使用

[TOC levels=1-4]

whitespace removerタグは、タグの開始と終了の間に含まれているコード ブロックから改行とタブを削除します。以下は、`<liferay-util:whitespace-remover>`タグの構成例です。

removerを使用：

    <liferay-util:whitespace-remover>
    <p>Here is some text with        tabs.</p>
    </liferay-util:whitespace-remover>

結果：

    Here is some text withtabs.

これで、`<liferay-util:whitespace-remover>`タグを使用してコードのフォーマットが一貫していることを確認する方法がわかりました。

## 関連トピック

[Using the Liferay Util Param Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-param)

[Using the Liferay Util Buffer Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-buffer)

[Using the AUI Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-aui-taglibs-in-your-portlets)
