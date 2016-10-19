# Creating a Module JAR Using Maven

If you have an existing Liferay Maven module project that you created from
scratch, or you're upgrading your project from a previous version of
Liferay, your Maven project may not be configured to generate an executable OSGi
JAR. Don't fret! You can configure your project to generate a deployable Liferay
OSGi JAR by making a few minor configurations in your module's POMs.

+$$$

**Note:** If you use Liferay's Maven archetypes to generate your module project,
the project is already configured with the Maven plugins required to generate an
OSGi JAR.

$$$

Continue on to see how this is done.

1.  In your parent project's `pom.xml` file, add the following plugin
    declaration:

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <configuration>
                <useDefaultManifestFile>true</useDefaultManifestFile>
            </configuration>
        </plugin>

    This dependency configures the
    [Maven JAR Plugin](http://maven.apache.org/plugins/maven-jar-plugin/) for
    all Maven module projects residing in the parent folder. The above logic
    also sets the default project `MANIFEST.MF` file path for all projects
    residing in this parent folder, which is essential when packaging OSGi
    modules.

2.  In your project's `pom.xml` file, add the following plugin declaration:

        <plugin>
            <groupId>biz.aQute.bnd</groupId>
            <artifactId>bnd-maven-plugin</artifactId>
            <version>3.2.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>bnd-process</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>

    This dependency configures the
    [BND Maven Plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html)
    for your Maven module project. This plugin is required to generate a Liferay
    OSGi module.

    The `bnd-maven-plugin` generates the JAR manifest
    










3.  Make sure you've added a `bnd.bnd` file to your Lifray Maven project,
    residing in the same folder as your project's `pom.xml` file.















