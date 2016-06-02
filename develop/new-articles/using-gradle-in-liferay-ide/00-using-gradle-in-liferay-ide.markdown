# Using Gradle in Liferay IDE

[Gradle](http://gradle.org/) is an open source build automation system.
[Buildship](https://projects.eclipse.org/releases/neon) is a collection of
Eclipse plugin-ins that provide support for building software using Gradle and
it is going to be a part of Neon. In the meantime, we’ve bundled it in Liferay
IDE 3.0.0 and users can use gradle easily in any version of eclipse with Liferay
IDE 3.0.0 intsalled.

![buildship: Eclipse plug-ins for Gradle](img/1Buildship-in-liferayide.png)

## Creating Gradle Project Wizard in Liferay IDE

- Go to File > New > Project… > Select Gradle Project > Next.

- Click Next > Enter a valid project name > Next.

     In this page, user can specify their gradle distribution and other advanced
     options.

- Click Next  > Finish.

     User can review the configuration before clicking Finish button. 

![specify optional options in new gradle project wizard](img/2New-Gradle-Project.png)

![review configuration in new gradle project wizard](img/3New-Gradle-Project.png)

## Import Gradle Project in Liferay IDE

- Go to File > Import… > Gradle Project > Next > Next.

- Click “Browse..” button to choose a gradle project.

- Click Next.

      User can specify optional import options in this page.

- Click Next > Finish.
  
    User can review the import configuration before finishing import the gradle
    project.

![Import Gradle Project](img/4New-Gradle-Project.png)

## Gradle Tasks and Gradle Executions Views

### Open views

- Open Gradle Tasks and Gradle Executions views manually.
    
    1. Go to Window > Show view > Other…

    2. Open Gradle Tasks and Gradle Executions views separately

- Gradle Tasks and Gradle Executions views will be opened automatically once
user create or import a gradle project.

### Dealing with Gradle Tasks

Gradle tasks are listed under every project in Gradle Tasks view. User can
execute every task by double clicking on it.

![Gradle Tasks](img/5Gradle-tasks.png)

### Dealing with Gradle Executions

User can see the outputs immediately in Gradle Executions view once execute a
gradle task.

![Gradle Executions](img/6Gradle-Executions.png)

## Refresh Gradle Project

If there is any change in build scripts inside gradle projects, such as
build.gradle and settings.gradle files, user need to refresh the project using
the following step:

- Right click on the project > Gradle > Refresh Gradle Project

![Refresh Gradle Project](img/7Update-Gradle-Project.png)
