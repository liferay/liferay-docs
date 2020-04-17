---
header-id: compiling-and-building-themes-with-ant-gradle-and-maven
---

# Ant、Gradle、およびMavenを使用したテーマのコンパイルと構築

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

LiferayのTheme Builderは、 [LiferayのTheme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes) （GradleやMavenなど）を使用していない開発者に、テーマWARファイルをコンパイルおよびビルドする方法を提供します。 テーマビルダーを使用するには、プロジェクトに適用する必要があります。 @製品の構造のテーマにしている場合は、あなたがわからないどのように@を参照してください [テーマの概要を](/docs/7-1/tutorials/-/knowledge_base/t/introduction-to-themes) チュートリアル。

以下の手順に従って、テーマビルダープラグインを適用し、テーマWARをビルドします。

## ステップ1：テーマビルダープラグインをテーマプロジェクトに適用する

Liferayは、ビルドツールに応じて2つのテーマビルダープラグインを提供します。

  - [com.liferay.portal.tools.theme.builder](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.tools.theme.builder/) （Ant、Mavenなど）
  - [com.liferay.gradle.plugins.theme.builder](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.gradle.plugins.theme.builder/) （Gradle）

テーマビルダープラグインをAntプロジェクトに適用する場合は、以下の例として `build.xml` ファイルを調べます。

    <?xml version="1.0"?>
    <!DOCTYPE project>
    
    <project>
        <path id="theme.builder.classpath">
            <fileset dir="[PATH_TO_THEME_BUILDER_JAR]" includes="com.liferay.portal.tools.theme.builder-*.jar" />
        </path>
    
        <taskdef classpathref="theme.builder.classpath" resource="com/liferay/portal/tools/theme/builder/ant/taskdefs.properties" />
    
        <target name="build-theme">
            <build-theme
                diffsDir="diffs"
                outputDir="../dist"
                parentDir="[PATH_TO_STYLED_THEME]/classes/META-INF/resources/_styled"
                parentName="_styled"
                unstyledDir="[PATH_TO_UNSTYLED_THEME]/classes/META-INF/resources/_unstyled"
            />
        </target>
    </project>

最初に、テーマビルダーJARへのパスを指定する必要があります。 上記のコードは、ローカルマシン上のJARへのリテラルパスを設定します。 別の方法として、 [Ivy](http://ant.apache.org/ivy/) を設定して、舞台裏でこれを行うことができます。 次に、テーマをビルドするために必要なパラメーターを構成するAntターゲット（ `build-theme`）を作成します。

GradleのやMavenプロジェクトのテーマBuilderプラグインを適用する支援については、 [テーマビルダーのGradleプラグイン](/docs/7-1/reference/-/knowledge_base/r/theme-builder-gradle-plugin) または [のMavenプロジェクトの建築テーマ](/docs/7-1/tutorials/-/knowledge_base/t/building-themes-in-a-maven-project) それぞれの記事を。

## ステップ2：テーマを作成する

ビルドツールに基づいて適切なコマンドを実行します。

  - *Ant：* `ant build-theme`
  - *Gradle：* `gradlew buildTheme`
  - *Maven：* `mvn verify`

WARは、使用したビルドツールに応じて、次のフォルダーに生成されます。

  - *Ant：* `/ dist`
  - *Gradle：* `/ビルド`
  - *Maven：* `/ターゲット`

それでおしまい\！ プロジェクトでテーマビルダーを正常に構成し、活用しました。 テーマビルダーを使用して、プラグインSDKテーマをLiferayワークスペースに移行することもできます。 詳細については、「プラグインSDKからテーマをワークスペース</a> 移行する

チュートリアル」を参照してください。</p> 



## 関連トピック

[テーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[テーマテンプレート](/docs/7-0/reference/-/knowledge_base/r/theme-template)
