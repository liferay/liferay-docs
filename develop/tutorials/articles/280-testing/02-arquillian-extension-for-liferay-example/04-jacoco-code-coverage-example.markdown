# JaCoCo Code Coverage Example [](id=jacoco-code-coverage-example)

[JaCoCo](http://eclemma.org/jacoco/) measures Java code coverage. The
Arquillian Blade Example uses JaCoCo to report parts and percentages of the product code the tests execute. 

![Figure 1: JaCoCo reports lines of code tests execute in methods and classes.](../../../images/arquillian-example-jacoco-results.png)

## Enabling JaCoCo [](id=enabling-jacoco)

JaCoCo requires attaching an agent to the JVM. To attach the JaCoCo agent,
append the following JaCoCo options to  the Tomcat environment script's
`CATALINA_OPTS` variable.

### `setenv.sh` JaCoCo Settings [](id=setenv-sh-jacoco-settings)

    JACOCO_OPTS="-javaagent:PATH_TO_JACOCO_AGENT_JAR/jacocoagent.jar=destfile=JACOCO_EXEC_FILE,output=file,append=true,jmx=true"
    
    CATALINA_OPTS="${CATALINA_OPTS} ${JACOCO_OPTS}"

### `setenv.bat` JaCoCo Settings [](id=setenv-bat-jacoco-settings)

    set "JACOCO_OPTS=-javaagent:PATH_TO_JACOCO_AGENT_JAR/jacocoagent.jar=destfile=JACOCO_EXEC_FILE,output=file,append=true,jmx=true"
    
    set "CATALINA_OPTS=%CATALINA_OPTS% %JACOCO_OPTS%"

Replace `PATH_TO_JACOCO_AGENT_JAR` with the path to the `jacocoagent.jar` file
and `JACOCO_EXEC_FILE` with the path to the JaCoCo result dump file. 

## JaCoCo Build Instructions [](id=jacoco-build-instructions)

The Gradle build file `build.gradle` specifies several JaCoCo-related
instructions:

1.  Apply the plugin to the build:

        apply plugin: 'jacoco'

        jacoco {
            toolVersion = '0.7.9'
        }

2.  Copy the JaCoCo agent into the project build. 

        task copyJacocoAgent(type: Copy) {
        println configurations.jacocoAgent

            configurations.jacocoAgent.asFileTree.each {
                from(zipTree(it))
            }

            into "${rootDir}/build/jacoco"
        }

3.  Dump the code coverage data:

        task dumpJacoco {
            doLast {
                def serverUrl = 'service:jmx:rmi:///jndi/rmi://localhost:8099/jmxrmi'
                String beanName = "org.jacoco:type=Runtime"
                def server = JmxFactory.connect(new JmxUrl(serverUrl)).MBeanServerConnection
                def gmxb = new GroovyMBean(server, beanName)

                println "Connected to:\n$gmxb\n"
                println "Executing dump()"
                gmxb.dump(true)
            }
        }

4.  Generate JaCoCo reports:

        jacocoTestReport {
            dependsOn dumpJacoco
            group = "Reporting"
            reports {
                xml.enabled true
                csv.enabled false
                html.destination "${buildDir}/reports/coverage"
            }
            executionData = files("${rootDir}/build/jacoco/testIntegration.exec")
        }

JaCoCo code coverage reporting runs as part of the project's `testIntegration`
Gradle task. You'll run the tests and JaCoCo next. 
