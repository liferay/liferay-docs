# Blade CLI Project Template Commands

You can run the `blade create` command to
[create a Liferay project](/developer/reference/-/knowledge_base/7-2/creating-a-project#blade-cli)
based on a
[project template](/developer/reference/-/knowledge_base/7-2/project-templates).
Each project template come with its own unique options, allowing you to mold
your project just the way you want it. All of Blade CLI's `create` command
options are listed below:

<table class="table table-striped table-bordered">
<thead>
<tr>
<th>Option</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>`--base`</td>
<td>The base directory. The default base directory is the current directory.</td>
</tr>
<tr>
<td>`-b`, `--build`, `-P`, `--profile-name`</td>
<td>The build type of the project. Available options are `gradle` (default) and `maven`. Additional profiles can be developed locally.</td>
</tr>
<tr>
<td>`-c`, `--classname`</td>
<td>If a class is generated in the project, provide the name of the class to be generated. If not provided, the class name defaults to the project name.</td>
</tr>
<tr>
<td>`-C`, `--contributor-type`</td>
<td>Identifies your module as a theme contributor. This is also used to add the `Liferay-Theme-Contributor-Type` and `Web-ContextPath` bundle headers to the BND file.</td>
</tr>
<tr>
<td>`-d`, `--dir`</td>
<td>The directory to create the new project.</td>
</tr>
<tr>
<td>`-h`, `--host-bundle-bsn`</td>
<td>If creating a new JSP hook fragment, provide the name of the host bundle symbolic name. This is required when using the `fragment` project template.</td>
</tr>
<tr>
<td>`-H`, `--host-bundle-version`</td>
<td>If a new JSP hook fragment needs to be created, provide the name of the host bundle version. This is required when using the `fragment` project template.</td>
</tr>
<tr>
<td>`-v`, `--liferay-version`</td>
<td>The version to target when creating a project. Available options are `7.1` (default) and `7.0`.</td>
</tr>
<tr>
<td>`-l`, `--list-templates`</td>
<td>Prints a list of available project templates.</td>
</tr>
<tr>
<td>`-m`, `--original-module-name`</td>
<td>The name of the original module when creating a project with the `modules-ext` template.</td>
</tr>
<tr>
<td>`-M`, `--original-module-version`</td>
<td>The version of the original module when creating a project with the `modules-ext` template.</td>
</tr>
<tr>
<td>`-p`, `--package-name`</td>
<td>The Java package name to use when creating the project.</td>
</tr>
<tr>
<td>`-s`, `--service`</td>
<td>If a new Declarative Services (DS) component needs to be created, provide the name of the service to be implemented. Note that in this context, the term *service* refers to an OSGi service, not to a Liferay API.</td>
</tr>
<tr>
<td>`-t`, `--template`</td>
<td>The project template to use when creating the project. Run `blade create -l` for a listing of available Blade CLI templates.</td>
</tr>
<tr>
<td>`--trace`</td>
<td>Prints exception stack traces when they occur. This is `false` by default.</td>
</tr>
<tr>
<td></td>
<td></td>
</tr>
</tbody>
</table>