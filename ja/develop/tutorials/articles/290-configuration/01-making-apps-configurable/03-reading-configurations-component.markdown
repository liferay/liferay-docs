---
header-id: reading-configuration-values-from-a-component
---

# コンポーネントからの構成値の読み取り

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

管理者がシステム設定で構成できるように [アプリケーションを](/docs/7-1/tutorials/-/knowledge_base/t/making-applications-configurable) 構成したら、アプリケーションのJavaコードから構成を読み取る方法を疑問に思うかもしれません。

答えは、いくつかの方法があるということです。 どちらを使用するかは、構成を読み取る必要のあるコンテキストによって異なります。

1.  任意のコンポーネントクラスから

2.  MVCポートレットのJSPから

3.  MVCポートレットのポートレットクラスから

4.  非コンポーネントクラスから

このチュートリアルでは、コンポーネントクラスから設定を読み取る最初の使用方法を示します。

## コンポーネントクラスからの設定の読み取り

1.  最初に、 `configurationPid` Componentプロパティを構成クラスの完全修飾クラス名として設定します。
   
        @Component(configurationPid = "com.liferay.dynamic.data.mapping.form.web.configuration.DDMFormWebConfiguration")

2.  次に、コンポーネントが開始されるとすぐにメソッドが呼び出されるように `@Activate` 、および構成が変更されるたびに呼び出されるように `@Modified` 注釈が付いた `activate` メソッドを提供します。
   
        @Activate
        @Modified
        protected void activate(Map<String, Object> properties) {
            _formWebConfiguration = ConfigurableUtil.createConfigurable(
                DDMFormWebConfiguration.class, properties);
        }
       
        private volatile DDMFormWebConfiguration _formWebConfiguration;

    `activate（）` メソッドは、メソッド `ConfigurableUtil.createConfigurable（）` を呼び出して、構成のプロパティのマップを、扱いやすい型付きクラスに変換します。 構成は、 `volatile` フィールドに格納されます。 スレッドセーフの問題を防ぐために、 `volatile` にすることを忘れないでください。

3.  activateメソッドを設定したら、必要な場所から特定のプロパティを構成から取得します。
   
        public void orderCar(String model) {
            order("car", model, _configuration.favoriteColor);
        }

    これはダミーコードです。Liferayのソースコードで見つけようとしないでください。 Stringの設定値 `favoriteColor` は、 `order` メソッド呼び出しに渡されます。これにより、注文されたモデル車が、設定されたお気に入りの色で注文されるようになります。

| **注：** bndライブラリーは、|というクラスも提供します。 `aQute.bnd.annotation.metatype.Configurable` with `createConfigurable（）` |方法。 Liferayの代わりに使用できます| `com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil` なし|すべての問題。 Liferayの開発者は、 `ConfigurableUtil` クラスを作成して| bndの実装のパフォーマンスを改善し、内部コードで使用されます。 |好みの方法を自由に使用してください。

ほんの数行のコードで、構成を動的に変更し、自動生成されたUIを持ち、簡単なAPIを使用して構成にアクセスする構成可能なアプリケーションがあります。
