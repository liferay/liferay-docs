---
header-id: customizing-jsps
---

# JSPのカスタマイズ

[TOC levels=1-4]

ポートレットおよびコア内のJSPをカスタマイズする方法はいくつかあります。
@product@のAPIでは、それらをカスタマイズする最も安全な方法を提供しています。他の方法でJSPをカスタマイズした場合、JSPの新しいバージョンではカスタマイズが無効になり、実行時エラーが発生する可能性があります。APIベースの方法のいずれかを使用することを強くお勧めします。

## LiferayのAPIを使用してJSPをオーバーライドする

@product@のJSPをオーバーライドするAPIベースのアプローチは次のとおりです。

| **アプローチ** | **説明** | **短所/制限** |
:----------- | :-------------- | :-------------- |
| [動的インクルード](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps-with-dynamic-includes) | 動的インクルードタグでコンテンツを追加します。 | `dynamic-include`タグ（またはクラスが`IncludeTag`から継承しているタグ）を持つJSPに限定されます。動的インクルードタグでJSPにのみコンテンツを挿入します。 |
| [ポートレットフィルター](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-portlet-filters) | ポートレットのリクエストやレスポンスを変更して、JSPカスタマイズをシミュレートします。 | このアプローチではJSPは直接カスタマイズされませんが、JSPカスタマイズの効果は得られます。 |

<!-- TODO - Include inlined content approach after getting more info. - Jim
Inlined content | Some @product@ JSPs include content from other JSPs that you can modify. This "inlines" the content from the other JSPs into specific places in the root JSP. | Limited to JSPs that inline other JSPs. |
-->

## LiferayのAPIを使用せずにJSPをオーバーライドする

前のセクションで説明したように、@product@のAPIを使用してJSPをカスタマイズすることを強くお勧めします。OSGiフラグメントまたはカスタムJSPバッグを使用したJSPのオーバーライドはAPIに基づいていないため、それらが正常に失敗する保証はありません。その代わり、カスタマイズにバグがある場合（コードやLiferayの変更のせいで）、ほとんどの場合は実行時に、機能が壊れたり、厄介なログエラーが表示されたりすることで気付くはずです。これらのアプローチは、最後の手段としてのみ使用してください。

これらのアプローチのいずれかを使用するJSPカスタマイズを維持している場合は、それらの動作を知っておく必要があります。このセクションでは、アプローチとそれらのチュートリアルへのリンクについて説明します。

@product@のAPIを使用せずにJSPをカスタマイズする方法は次のとおりです。

| **アプローチ** | **説明** | **短所/制限** |
:----------- | :-------------- | :-------------- |
| [OSGiフラグメント](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-osgi-fragments) | OSGiフラグメントを使用してモジュールのJSPを完全にオーバーライドします | 元のJSPまたはモジュールを変更すると、ランタイムエラーが発生する可能性があります。 |
| [カスタムJSPバッグ](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-custom-jsp-bag) | @product@のコアJSPまたは対応する`-ext.jsp`ファイルの1つを完全にオーバーライドします。 | @product@のコアJSPのみ。元のJSPまたはモジュールを変更すると、ランタイムエラーが発生する可能性があります。 |

すべてのJSPカスタマイズアプローチを使用できます。いくつかのJSPをカスタマイズしてみましょう。
