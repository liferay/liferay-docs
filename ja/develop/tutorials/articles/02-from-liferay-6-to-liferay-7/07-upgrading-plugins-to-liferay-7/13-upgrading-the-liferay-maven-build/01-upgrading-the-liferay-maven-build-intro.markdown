---
header-id: upgrading-the-liferay-maven-build
---

# Liferay Mavenビルドのアップグレード

[TOC levels=1-4]

もしあなたが熱心なMavenユーザーであり、Liferay Portal 6.2プロジェクトの開発にそれを使用している場合は、@product-ver@開発と互換性を持たせるようMavenビルドをアップグレードする必要があります。 対処すべきMaven環境のアップグレードプロセスには、2つの主要な要素があります。

  - [新しい@product-ver@ Mavenプラグインへのアップグレード](#upgrading-to-new-product-ver-maven-plugins)
  - [Liferay Mavenアーティファクトの依存関係のアップデート](#updating-liferay-maven-artifact-dependencies)

@product-ver@でMavenを使用する方法の詳細については、[Maven tutorial section](/docs/7-1/tutorials/-/knowledge_base/t/maven)を参照してください。 Mavenビルドのガイド付き優先アップグレードプロセスについては、[Liferay Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)をお試しください。

Liferayは、@product-ver@開発専用に調整されたMaven開発環境も提供しています。 詳細については、チュートリアル[Maven Workspace](/docs/7-1/tutorials/-/knowledge_base/t/maven-workspace)をご覧ください。

まずは、Maven環境のLiferay Mavenプラグインをアップグレードすることから始めます。

## 新しい@product-ver@ Mavenプラグインへのアップグレード

プロジェクトのビルドプラグインの最大の変更点は、`liferay-maven-plugin`の削除です。 Liferayは、特定のタスクを実行する複数の個別のMavenプラグインを提供するようになりました。 たとえば、LiferayのCSS Builder、Service Builder、Theme BuilderなどのためのMavenプラグインを設定できます。 プロジェクト内の特定のタスクを実行するために使用可能な小さなプラグインを使用すると、不要な多くの機能を提供する1つの大きなプラグインに依存する必要がなくなります。

たとえば、Liferay Portal 6.2プロジェクトが、Liferay CSS Builderのみに適した`lifelife-maven-plugin`を使用していたとします。 まずはプロジェクトの`pom.xml`ファイルから、従来の`liferay-maven-plugin`プラグインの依存関係を削除する必要があります。

    <plugin>
        <groupId>com.liferay.maven.plugins</groupId>
        <artifactId>liferay-maven-plugin</artifactId>
        <version>${liferay.version}</version>
        <configuration>
            <autoDeployDir>${liferay.auto.deploy.dir}</autoDeployDir>
            <liferayVersion>${liferay.version}</liferayVersion>
            <pluginType>portlet</pluginType>
        </configuration>
    </plugin>

次に、プロジェクトの`pom.xml`ファイルにCSS Builderプラグインの依存関係を追加します。

    <plugin>
        <groupId>com.liferay</groupId>
        <artifactId>com.liferay.css.builder</artifactId>
        <version>1.0.21</version>
        <executions>
            <execution>
                <id>default-build</id>
                <phase>generate-sources</phase>
                <goals>
                    <goal>build</goal>
                </goals>
            </execution>
        </executions>
            <configuration>
                <docrootDirName>src/main/webapp</docrootDirName>
            </configuration>
    </plugin>

いくつかの一般的なLiferay Mavenプラグインを以下にリストし、対応するアーティファクトIDとそれらの設定方法を説明するチュートリアルを示します。

**一般的なLiferay Mavenプラグイン**

| 名前                | アーティファクトID                                                                                                                       | チュートリアル                                                                                                       |
|:----------------- |:-------------------------------------------------------------------------------------------------------------------------------- |:------------------------------------------------------------------------------------------------------------- |
| Bundle Support    | [com.liferay.portal.tools.bundle.support](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.bundle.support)   | 近日公開                                                                                                          |
| CSS Builder       | [com.liferay.css.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.css.builder)                                   | [MavenプロジェクトでのSASSファイルのコンパイル](/docs/7-1/tutorials/-/knowledge_base/t/compiling-sass-files-in-a-maven-project) |
| DB Support        | [com.liferay.portal.tools.db.support](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.db.support)           | [DBサポートプラグイン](/docs/7-1/reference/-/knowledge_base/r/db-support-plugin)                                       |
| Deployment Helper | [com.liferay.deployment.helper](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.deployment.helper)                       | 近日公開                                                                                                          |
| Javadoc Formatter | [com.liferay.javadoc.formatter](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.javadoc.formatter)                       | 近日公開                                                                                                          |
| Lang Builder      | [com.liferay.lang.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.lang.builder)                                 | 近日公開                                                                                                          |
| Service Builder   | [com.liferay.portal.tools.service.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.service.builder) | [Service Builderプラグイン](/docs/7-1/reference/-/knowledge_base/r/service-builder-with-maven)                     |
| Source Formatter  | [com.liferay.source.formatter](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.source.formatter)                         | [Source Formatterプラグイン](/docs/7-0/reference/-/knowledge_base/r/source-formatter-plugin)                       |
| Theme Builder     | [com.liferay.portal.tools.theme.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.theme.builder)     | [Theme Builderプラグイン](/docs/7-1/reference/-/knowledge_base/r/theme-builder-plugin)                             |
| TLD Formatter     | [com.liferay.tld.formatter](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.tld.formatter)                               | 近日公開                                                                                                          |
| WSDD Builder      | [com.liferay.portal.tools.wsdd.builder](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.portal.tools.wsdd.builder)       | 近日公開                                                                                                          |
| XML Formatter     | [com.liferay.xml.formatter](https://search.maven.org/#search%7Cga%7C1%7Ccom.liferay.xml.formatter)                               | 近日公開                                                                                                          |

Liferay Portal 6.2では、すべてのアプリケーションサーバーの構造設定も指定する必要がありました。 たとえば、親POMには次のような設定が含まれていた可能性があります。

    <properties>
        <liferay.app.server.deploy.dir>
            E:\liferay-portal-6.2.0-ce-ga1\tomcat-7.0.42\webapps
        </liferay.app.server.deploy.dir>
    
        <liferay.app.server.lib.global.dir>
            E:\liferay-portal-6.2.0-ce-ga1\tomcat-7.0.42\lib\ext
        </liferay.app.server.lib.global.dir>
    
        <liferay.app.server.portal.dir>
            E:\liferay-portal-6.2.0-ce-ga1\tomcat-7.0.42\webapps\root
        </liferay.app.server.portal.dir> 
    
        <liferay.auto.deploy.dir>
            E:\liferay-portal-6.2.0-ce-ga1\deploy
        </liferay.auto.deploy.dir>
    
        <liferay.version>
            6.2.0
        </liferay.version>
    
        <liferay.maven.plugin.version>
            6.2.0
        </liferay.maven.plugin.version
    
    </properties>

LiferayのMavenツールは@product@インストールの特定のバージョンに依存しないため、@product-ver@では不要になりました。 POMファイルからこれらを削除してください。

@product-ver@開発で使用できる新しいMavenプラグインについて学習しました。 次に、Liferay Mavenアーティファクトのアップデートについて学習します。

## Liferay Mavenアーティファクトの依存関係のアップデート

使用していたLiferay Portal 6.2アーティファクトの多くの依存関係が、@product-ver@で変更されました。 変更された、よく使用されているLiferay Mavenアーティファクトについては、以下の表を参照してください。

| Liferay Portal 6.2アーティファクトID | @product-ver@アーティファクトID     |
|:---------------------------- |:--------------------------- |
| `portal-service`             | `com.liferay.portal.kernel` |
| `util-bridges`               | `com.liferay.util.bridges`  |
| `util-java`                  | `com.liferay.util.java`     |
| `util-slf4j`                 | `com.liferay.util.slf4j`    |
| `util-taglib`                | `com.liferay.util.taglib`   |

@product-ver@で依存関係を解決する方法については、チュートリアル[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)で詳細を参照してください。

もちろん、プロジェクトで参照しているアーティファクトもアップデートする必要があります。 セントラルリポジトリを使用してLiferay Mavenアーティファクトをインストールする場合は、POMのアーティファクトをアップデートするだけで構いません。ただし、プロキシで作業している場合やインターネットにアクセスできない場合は、会社の共有リポジトリまたはローカルリポジトリを、最新の@product-ver@ Mavenアーティファクトでアップデートする必要があります。 説明については、チュートリアル[Installing Liferay Maven Artifacts](/docs/7-1/tutorials/-/knowledge_base/t/installing-liferay-maven-artifacts)を参照してください。

これらのアップデートでLiferay Mavenビルドを簡単にアップグレードできるため、@product-ver@用の従来のプラグインプロジェクトの開発を開始できます。
