# Building Themes in a Maven Project

Liferay's Theme Builder is a tool used to build Liferay theme files in your
project.



























        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
            <modelVersion>4.0.0</modelVersion>
            <artifactId>sample-maven-theme</artifactId>
            <version>1.0.0</version>
            <packaging>war</packaging>
            <parent>
                <groupId>com.liferay.maven.workspace</groupId>
                <artifactId>liferay-maven-workspace-themes</artifactId>
                <version>1.0.0</version>
            </parent>

            <properties>
                <com.liferay.css.builder.version>1.0.20</com.liferay.css.builder.version>
                <com.liferay.frontend.css.common.version>2.0.1</com.liferay.frontend.css.common.version>
                <com.liferay.frontend.theme.styled.version>2.0.13</com.liferay.frontend.theme.styled.version>
                <com.liferay.frontend.theme.unstyled.version>2.0.13</com.liferay.frontend.theme.unstyled.version>
                <com.liferay.portal.tools.theme.builder.version>1.0.0</com.liferay.portal.tools.theme.builder.version>
                <com.liferay.portal.tools.theme.builder.outputDir>target/build-theme</com.liferay.portal.tools.theme.builder.outputDir>
            </properties>

            <build>
                <plugins>
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
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.theme.builder</artifactId>
                <version>${com.liferay.portal.tools.theme.builder.version}</version>
                <executions>
                    <execution>
                        <phase>generate-resources</phase>
                        <goals>
                            <goal>build-theme</goal>
                        </goals>
                        <configuration>
                            <diffsDir>src/main/webapp/</diffsDir>
                            <name>${project.artifactId}</name>
                            <outputDir>${com.liferay.portal.tools.theme.builder.outputDir}</outputDir>
                            <parentDir>${project.build.directory}/deps/com.liferay.frontend.theme.styled.jar</parentDir>
                            <parentName>_styled</parentName>
                            <templateExtension>ftl</templateExtension>
                            <unstyledDir>${project.build.directory}/deps/com.liferay.frontend.theme.unstyled.jar</unstyledDir>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.css.builder</artifactId>
                <version>${com.liferay.css.builder.version}</version>
                <executions>
                    <execution>
                        <id>default-build-css</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>build-css</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <docrootDirName>${com.liferay.portal.tools.theme.builder.outputDir}</docrootDirName>
                    <outputDirName>/</outputDirName>
                    <portalCommonPath>target/deps/com.liferay.frontend.css.common.jar</portalCommonPath>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.0.0</version>
                <configuration>
                    <packagingExcludes>**/*.scss</packagingExcludes>
                    <webResources>
                        <resource>
                            <directory>${com.liferay.portal.tools.theme.builder.outputDir}</directory>
                            <excludes>
                                <exclude>**/*.scss</exclude>
                            </excludes>
                        </resource>
                    </webResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
