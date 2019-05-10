---
header-id: fundamentals
---

# Fundamentals

[TOC levels=1-4]

The fundamentals of developing on @product@ and customizing it are perhaps best
learned in the context of projects. It's in projects that you configure access
to @product@'s API, extend and override @product@ features, and package your
software for deployment. Projects are developed as WARs or OSGi JARs, but are
all installed to Liferay's OSGi framework as OSGi bundles. These bundles can
depend on external Java packages, share Java packages, and be manipulated at run
time via Apache Gogo Shell. The fundamentals are explained in the context of
projects so that you understand them in a practical sense and can apply them
right away. Here are the fundamental topics:

-   **WARs Versus OSGi JAR** explains fundamental differences between the WAR 
    and OSGi JAR structures and how they're deployed in @product@. 

-   **Configuring Dependencies** demonstrates how to identify and configure 
    Liferay artifacts and third-party artifacts to use their Java packages in
    your projects. 

-   **Importing and Exporting Packages** shows how to import the packages your 
    projects need and export packages your projects provide. Liferay's tooling
    detects package use and specifies package imports automatically. 

-   **Semantic Versioning** shows how @product@ uses a standard for ascribing 
    meaning to major, minor, and micro versions of modules and Java packages. 

-   **Deploying WARs (WAB Generator)** explains how Liferay's WAB Generator 
    deploys WAR applications as OSGi Web Application Bundles (WABs). 

-   **Gogo Shell** enables you to examine components, debug issues, and manage 
    deployments. 

| **Note:** The
| [Architecture section](/docs/7-2/customization/-/knowledge_base/c/architecture)
| provides conceptual diagrams and explains @product@ services and components, 
| and applications, and the @product@ core. 

Start with understanding how WAR and OSGi JAR project structures are used in
development. 
