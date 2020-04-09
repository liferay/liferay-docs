---
header-id: configuring-system-settings-and-instance-settings-for-your-js-portlet
---

# JavaScriptウィジェットのシステム設定とインスタンス設定の構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

JS Portlet Extender v1.1.0の時点で、ウィジェットの構成オプションを定義できます。 これらのオプションは、 `構成` パラメーターとしてウィジェットのJavaScriptエントリーポイントに渡されます。 エントリポイントの詳細については、 [メインエントリポイントのリファレンス](/docs/7-1/reference/-/knowledge_base/r/understanding-the-js-portlet-extender-configuration#main-entry-point) を参照してください。 以下の手順に従って、ウィジェットのシステムおよび/またはポートレットインスタンス設定を設定します。

1.  プロジェクトのルートフォルダーに `/ features` フォルダーが存在しない場合は、追加します。

    | **注：** この場所は|でオーバーライドできます。 `プロジェクトの <code>.npmbundlerrc` | create-jar.features.configuration</code> オプションファイル。 参照| [OSGiバンドル構成オプション](/docs/7-1/reference/-/knowledge_base/r/configuring-liferay-npm-bundler#osgi-bundle-creation-options) |バンドルで利用可能なすべてのオプション。

2.  `/ features` フォルダーに `configuration.json` ファイルを作成し、以下のパターンに従います。 利用可能な各オプションの説明については、 [構成JSON](/docs/7-1/reference/-/knowledge_base/r/configuring-system-settings-for-osgi-bundles-created-with-the-bundler) リファレンスを参照してください。

    {"system"：{"category"： "{category identifier}"、 "name"： "{name of configuration}"、 "fields"：{"{field id 1}"：{"type"： "{field type}"、 "name"： "{field name}"、 "description "："{field description}"、"デフォルト "："{default value}"、"オプション "：{"{option id 1}"："{option name 1}"、"{option id 2}"："{option name 2}"、

    ``` 
          "{option id n}": "{option name n}"
        }
      },
      "{field id 2}": {},

      "{field id n}": {}
    }
    ```

    }、 "portletInstance"：{"name"： "{name of configuration}"、 "fields"：{"{field id 1}"：{"type"： "{field type}"、 "name"： "{field name}"、 "description"： "{field description}"、 "デフォルト」：「{default value}」、「オプション」：{「{option id 1}」：「{option name 1}」、「{option id 2}」：「{option name 2}」、

    ``` 
          "{option id n}": "{option name n}"
        }
      },
      "{field id 2}": {},

      "{field id n}": {}
    }
    ```

    }}

3.  構文 `configuration.system` または `configuration.portletInstance` 使用して、システム設定の値またはポートレットインスタンス設定の値にそれぞれアクセスします。 たとえば、 `{field id 1}` システム設定の値を取得するには、 `configuration.systemを使用します。{field id 1}`。 記述子で宣言する型に関係なく、すべてのフィールドは文字列として渡されることに注意してください。

驚くばかり\！ これで、ウィジェットのシステム設定とポートレットインスタンス設定を構成する方法がわかりました。

## 関連トピック

  - [ウィジェットのローカライズ](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-portlet)
  - [JavaScriptウィジェットで翻訳機能を使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-translation-features-in-your-portlet)
  - [JavaScriptウィジェットのポートレットプロパティの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-portlet-properties-for-your-js-portlet)
