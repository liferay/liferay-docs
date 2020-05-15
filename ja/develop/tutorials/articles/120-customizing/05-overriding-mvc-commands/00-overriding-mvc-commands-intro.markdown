---
header-id: overriding-mvc-commands
---

# MVCコマンドのオーバーライド

[TOC levels=1-4]

MVCコマンドは、[Liferay MVCアプリケーション](/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet)のコントローラーレイヤーをより小さく、より消化しやすいコードチャンクに分割するために使用されます。

Liferayアプリケーションであろうと、ソースコードを所有していない別のLiferay MVCアプリケーションであろうと、MVCコマンドをオーバーライドしたい場合があります。
MVCコマンドはOSGiランタイムに登録されたコンポーネントであるため、簡単にコンポーネントの独自のカスタマイズを公開し、より高いサービスのランキングを与えてデプロイすることができます。

（greedy参照ポリシーを使用して）元のMVCコマンドサービスコンポーネントを参照する既存のすべてのコンポーネントは、新しいコンポーネントを参照するように切り替わります。
元のコマンドへの既存の[消極的な参照は、新しいコマンドを参照するように設定されます](/docs/7-1/tutorials/-/knowledge_base/t/overriding-service-references)。
新しいサービスコンポーネントを使用して構成すると、JSPのコマンドURLが新しいカスタムMVCコマンドを呼び出します。

各Liferay MVCコマンドタイプで使用可能なカスタマイズオプションは次のとおりです。

- MVCActionCommand：[ロジックの追加](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcactioncommand)
- MVCRenderCommand：
   - [ロジックの追加](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcrendercommand#adding-logic-to-an-existing-mvc-render-command)
   - [異なるJSPへのリダイレクト](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcrendercommand#redirecting-to-a-new-jsp)
- MVCResourceCommand：[ロジックの追加](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcresourcecommand)

これらのチュートリアルは、各MVCコマンドのカスタマイズオプションを示しています。ロジックを追加する手順は一般的にMVCコマンドタイプ全体で同じなので、[ロジックの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands)から始めます。
