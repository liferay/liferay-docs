---
header-id: building-themes-in-a-maven-project
---

# Mavenプロジェクトでテーマを作成する

[TOC levels=1-4]

LiferayのTheme Builderは、プロジェクトで@product@テーマファイルを作成するために使用するツールです。Theme BuilderをMavenプロジェクトに組み込んで、@product@にデプロイ可能なWARスタイルのテーマを作成することができます。@product@のテーマの詳細については、[Themes and Layout Templates](/docs/7-1/tutorials/-/knowledge_base/t/themes-and-layout-templates)のチュートリアルセクションを参照してください。

MavenでLiferayテーマを作成するにあたって、最も簡単な方法は、Liferayが提供する[テーマアーキタイプ](/docs/7-1/reference/-/knowledge_base/r/theme-template)を使用して、新しいMavenプロジェクトを作成することです。Mavenテーマプロジェクトを作成する方法は、[Generating New Projects Using Archetypes](/docs/7-1/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes)で説明しています。ただし、場合によっては、この方法は適していません。たとえば、レガシーテーマプロジェクトがあり、最初からやり直したくない場合は、新しいプロジェクトを作成するのは理想的ではありません。

このような場合は、テーマをビルドするためにMavenプロジェクトを手動で設定する必要があります。ここでは、その方法について説明します。

1. Liferayの[Theme Builder](/docs/7-1/reference/-/knowledge_base/r/theme-builder-plugin)プラグインをプロジェクトの`pom.xml`ファイルで設定します。

       <build>
       <plugins>
       <plugin>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.portal.tools.theme.builder</artifactId>
       <version>1.1.4</version>
       <executions>
       <execution>
       <phase>generate-resources</phase>
       <goals>
       <goal>build</goal>
       </goals>
       <configuration>
       <diffsDir>${maven.war.src}</diffsDir>
       <name>${project.artifactId}</name>
       <outputDir>${project.build.directory}/${project.build.finalName}</outputDir>
       <parentDir>${project.build.directory}/deps/com.liferay.frontend.theme.styled.jar</parentDir>
       <parentName>_styled</parentName>
       <templateExtension>ftl</templateExtension>
       <unstyledDir>${project.build.directory}/deps/com.liferay.frontend.theme.unstyled.jar</unstyledDir>
       </configuration>
       </execution>
       </executions>
       </plugin>
       </plugins>
       </build>
   
   上記の設定は、`groupId`、`artifactId`、および`version`を指定することで、Theme Builderを適用します。次に、Theme Builderの実行と設定を定義します。

   - [executions](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag)タグは、Theme BuilderがMavenプロジェクトのビルドライフサイクルの`generate-resources`フェーズ中に実行するように設定します。 `build`
      [goal](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)は、ライフサイクルフェーズ用に定義されています。
   - [configuration](https://maven.apache.org/pom.html#Plugins)タグは、以下に紹介する重要なプロパティを定義します。
      - `diffsDir`: 親テーマにコピーするファイルを保持するフォルダ。
      - `name`: 新しいテーマの名前。
      - `outputDir`: テーマを作成するフォルダ。
      - `parentDir`: 親テーマのフォルダ。
      - `parentName`: 親テーマの名前。
      - `templateExtension`: テンプレートファイルの拡張子（例: `ftl`または`vm`）。
      - `unstyledDir`: スタイルが設定されていないテーマのフォルダ。

2. Theme Builderを使用するために必要な、CSS Builderプラグインを適用します。

       <plugin>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.css.builder</artifactId>
       <version>2.1.0</version>
       <executions>
       <execution>
       <id>default-build</id>
       <phase>compile</phase>
       <goals>
       <goal>build</goal>
       </goals>
       </execution>
       </executions>
       <configuration>
       <docrootDirName>target/${project.build.finalName}</docrootDirName>
       <outputDirName>/</outputDirName>
       <portalCommonPath>target/deps/com.liferay.frontend.css.common.jar</portalCommonPath>
       </configuration>
       </plugin>
   
   CSS BuilderのMaven設定の詳細については、[Compiling Sass Files in a Maven Project](/docs/7-1/tutorials/-/knowledge_base/t/compiling-sass-files-in-a-maven-project)のチュートリアルを参照してください。

3. Sassファイルをテーマにパッケージ化しないように、プロジェクトを設定することができます。これはオプションですが、不要なソースコードをWARから除外するのに便利です。Theme BuilderはWARスタイルのテーマを作成するため、[maven-war-plugin](https://maven.apache.org/plugins/maven-war-plugin/)を適用して、WARファイルのパッケージ化プロセスにSassファイルを除外するよう指示する必要があります。

       <plugin>
       <artifactId>maven-war-plugin</artifactId>
       <version>3.0.0</version>
       <configuration>
       <packagingExcludes>**/*.scss</packagingExcludes>
       </configuration>
       </plugin>
   
4. プロジェクトがWARファイルとして正しくパッケージ化されるように、プロジェクトのPOMに`<packaging>`タグを挿入します。このタグは、プロジェクトの`groupId`、`artifactId`および`version`の指定とともに、以下のように配置できます。

       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.project.templates.theme</artifactId>
       <version>1.0.0</version>
       <packaging>war</packaging>
   
5. テーマのビルドには、特定の依存関係が必要です。プロジェクトの`pom.xml`にある[これらの依存関係](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)をディレクトリまたはJARファイルとして設定できます。JARを使用する場合は、以下のように[maven-dependency-plugin](http://maven.apache.org/plugins/maven-dependency-plugin/)を適用し、Maven CentralからプロジェクトにJAR依存関係をコピーする必要があります。

       <plugin>
       <artifactId>maven-dependency-plugin</artifactId>
       <executions>
       <execution>
       <phase>generate-sources</phase>
       <goals>
       <goal>copy</goal>
       </goals>
       <configuration>
       <artifactItems>
       <artifactItem>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.frontend.css.common</artifactId>
       <version>${com.liferay.frontend.css.common.version}</version>
       </artifactItem>
       <artifactItem>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.frontend.theme.styled</artifactId>
       <version>${com.liferay.frontend.theme.styled.version}</version>
       </artifactItem>
       <artifactItem>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.frontend.theme.unstyled</artifactId>
       <version>${com.liferay.frontend.theme.unstyled.version}</version>
       </artifactItem>
       </artifactItems>
       <outputDirectory>${project.build.directory}/deps</outputDirectory>
       <stripVersion>true</stripVersion>
       </configuration>
       </execution>
       </executions>
       </plugin>
   
   この設定は、`com.liferay.frontend.css.common`、`com.liferay.frontend.theme.styled`、および`com.liferay.frontend.theme.unstyled`の依存関係をMavenプロジェクトにコピーします。
`stripVersion`タグを`true`に設定し、各`artifactItem`タグ内のアーティファクトバージョンを設定していることに注意してください。次に、これらのバージョンとMavenプロジェクトのその他のプロパティの一部を設定します。

6. `pom.xml`ファイルで、以下のようにしてプロジェクトのプロパティを設定します。

       <properties>
       <com.liferay.css.builder.version>2.0.1</com.liferay.css.builder.version>
       <com.liferay.frontend.css.common.version>2.0.4</com.liferay.frontend.css.common.version>
       <com.liferay.frontend.theme.styled.version>2.0.28</com.liferay.frontend.theme.styled.version>
       <com.liferay.frontend.theme.unstyled.version>2.2.5</com.liferay.frontend.theme.unstyled.version>
       <com.liferay.portal.tools.theme.builder.version>1.1.4</com.liferay.portal.tools.theme.builder.version>
       </properties>
   
   上記のプロパティは、CSSおよびTheme Builderプラグインのバージョンとそれらの依存関係を設定します。

これで、LiferayテーマをビルドするためにMavenプロジェクトが正常に設定できました。MavenプロジェクトでTheme Builderを実行する方法については、[Theme Builder](/docs/7-1/tutorials/-/knowledge_base/t/compiling-and-building-themes-with-ant-gradle-and-maven)のチュートリアルを参照してください。
