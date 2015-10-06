package com.liferay.docs.atomclient;

import com.liferay.portal.kernel.util.Base64;
import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.parser.stax.util.FOMHelper;
import org.apache.abdera.protocol.Response.ResponseType;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.abdera.protocol.client.ClientResponse;
import org.apache.abdera.protocol.client.RequestOptions;
import org.apache.abdera.protocol.client.util.BaseRequestEntity;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class AtomClient {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            showHelp();
            
            return;
        }
        
        String blogsUrl = "http://localhost:8080/api/atom/blogs";
        String filesUrl = "http://localhost:8080/api/atom/files";
        
        switch (args[0]) {
            case "BLOGS_POST": {
                Abdera abdera = new Abdera();
                Factory factory = abdera.getFactory();
                Entry entry = factory.newEntry();
                entry.setId(FOMHelper.generateUuid());
                entry.setUpdated(new Date());
                entry.addAuthor("Joe Bloggs");
                entry.setTitle("Title");
                entry.setSummary("Summary");
                entry.setContentAsHtml(
                    "<p>This is post was <b>created</b> by the Atom client!</p>");

                AbderaClient client = new AbderaClient(abdera);
                RequestOptions options = createRequestOptions(client);
                BaseRequestEntity requestEntity =
                    new BaseRequestEntity(entry, false);

                ClientResponse response =
                    client.post(blogsUrl + "?groupId=20146", requestEntity, options);

                if (response.getType() == ResponseType.SUCCESS) {
                    System.out.println("HTTP POST succeeded!");
                }
                else {
                    System.out.println("HTTP POST failed!!");
                }

                break;
            }
            case "BLOGS_PUT": {
                Abdera abdera = new Abdera();
                Entry entry = abdera.newEntry();
                entry.setId("tag:liferay.com:blogs:entry:20760");
                entry.setUpdated(new Date());
                entry.addAuthor("Joseph Blogger");
                entry.setTitle("Updated title");
                entry.setSummary("Updated summary");
                entry.setContentAsHtml(
                    "<p>This post was <b>updated</b> by the Atom client!</p>");

                AbderaClient client = new AbderaClient(abdera);
                RequestOptions options = createRequestOptions(client);
                BaseRequestEntity requestEntity =
                    new BaseRequestEntity(entry, false);

                ClientResponse response = client.put(
                    blogsUrl + "/20760?companyId=20118", requestEntity, options);

                if (response.getType() == ResponseType.SUCCESS) {
                    System.out.println("HTTP PUT succeeded!");
                }
                else {
                    System.out.println("HTTP PUT failed!");
                }
                
                break;
            }
            case "BLOGS_DELETE": {
                Abdera abdera = new Abdera();
                AbderaClient client = new AbderaClient(abdera);
                RequestOptions options = createRequestOptions(client);

                ClientResponse response = client.delete(
                    blogsUrl + "/20760?companyId=20118", options);

                if (response.getType() == ResponseType.SUCCESS) {
                    System.out.println("HTTP DELETE succeeded!");
                }
                else {
                    System.out.println("HTTP DELETE failed!");
                }
                
                break;
            }
            case "FILES_POST": {
                Abdera abdera = new Abdera();
                Factory factory = abdera.getFactory();
                Entry entry = factory.newEntry();
                entry.setId(FOMHelper.generateUuid());
                entry.setUpdated(new Date());
                entry.setTitle("cat.jpg");
                entry.setSummary("This is a cat!");

                File f = new File("/local/path/cat.jpg");
                FileInputStream fis = new FileInputStream(f);
                entry.setContent(fis, "image/jpeg");

                AbderaClient client = new AbderaClient(abdera);
                RequestOptions options = createRequestOptions(client);
                options.setSlug(f.getName());
                options.setContentType("application/atom+xml");
                options.setHeader("Media-Content-Type", "image/jpeg");

                // ClientResponse response = client.post(filesUrl + "?folderId=21503", entry, options);
                ClientResponse response = client.post(filesUrl + "?repositoryId=20146", entry, options);
                
                if (response.getType() == ResponseType.SUCCESS) {
                    System.out.println("HTTP POST succeeded!");
                }
                else {
                    System.out.println("HTTP POST failed!");
                }
                
                break;
            }
            case "FILES_POST_DIRECT": {
                Abdera abdera = new Abdera();

                File f = new File("/local/path/cat.jpg");
                FileInputStream fis = new FileInputStream(f);

                AbderaClient client = new AbderaClient(abdera);
                RequestOptions options = createRequestOptions(client);
                options.setSlug(f.getName());
                options.setContentType("image/jpeg");
                options.setHeader("Title", "cat");
                options.setHeader("Summary", "This is another cat!");

                InputStreamRequestEntity streamRequest = new InputStreamRequestEntity(fis, "image/jpeg");

                // ClientResponse response = client.post(filesUrl + "?folderId=21503", streamRequest, options);
                ClientResponse response = client.post(filesUrl + "?repositoryId=20146", streamRequest, options);
                
                if (response.getType() == ResponseType.SUCCESS) {
                    System.out.println("HTTP POST succeeded!");
                }
                else {
                    System.out.println("HTTP POST failed!");
                }
                
                break;
            }
            case "FILES_PUT": {
                Abdera abdera = new Abdera();
                
                Factory factory = abdera.getFactory();
                Entry entry = factory.newEntry();
                entry.setId("tag:liferay.com:dl:entry:20776");
                entry.setUpdated(new Date());
                entry.setTitle("updated-title.jpg");
                entry.setSummary("Updated summary");

                File f = new File("/local/path/cat.jpg");
                FileInputStream fis = new FileInputStream(f);
                entry.setContent(fis, "image/jpeg");

                AbderaClient client = new AbderaClient(abdera);
                RequestOptions options = createRequestOptions(client);
                options.setContentType("application/atom+xml");
                options.setHeader("Media-Content-Type", "image/jpeg");
                options.setSlug(f.getName());

                ClientResponse response = client.put(filesUrl + "/20776", entry, options);

                if (response.getType() == ResponseType.SUCCESS) {
                    System.out.println("HTTP PUT succeeded!");
                }
                else {
                    System.out.println("HTTP PUT failed!");
                }
                
                break;
            }
            case "FILES_PUT_DIRECT": {
                Abdera abdera = new Abdera();

                File f = new File("/local/path/cat.jpg");
                FileInputStream fis = new FileInputStream(f);

                AbderaClient client = new AbderaClient(abdera);
                RequestOptions options = createRequestOptions(client);
                options.setSlug(f.getName());
                options.setContentType("image/jpeg");
                options.setHeader("Title", "updated-directly.jpg");
                options.setHeader("Summary", "Summary updated directly!");

                InputStreamRequestEntity streamRequest = new InputStreamRequestEntity(fis, "image/jpeg");
                ClientResponse response = client.put(filesUrl + "/20789:media", streamRequest, options);

                if (response.getType() == ResponseType.SUCCESS) {
                    System.out.println("HTTP PUT succeeded!");
                }
                else {
                    System.out.println("HTTP PUT failed!");
                }
                
                break;
            }
            case "FILES_DELETE": {
                Abdera abdera = new Abdera();
                AbderaClient client = new AbderaClient(abdera);
                RequestOptions options = createRequestOptions(client);

                ClientResponse response = client.delete(
                    filesUrl + "/20776", options);
                
                if (response.getType() == ResponseType.SUCCESS) {
                    System.out.println("HTTP DELETE succeeded!");
                }
                else {
                    System.out.println("HTTP DELETE failed!");
                }
                
                break;
            }
            default: {
                showHelp();
            }
        }
    }

    private static RequestOptions createRequestOptions(AbderaClient client) {
        String encodedCredential =
            Base64.encode("test@liferay.com:test".getBytes());
        RequestOptions options = client.getDefaultRequestOptions();
        options.setHeader("Authorization", "Basic " + encodedCredential);

        return options;
    }
    
    private static void showHelp() {
        System.out.println("When invoking the Atom client, please specify a "
            + "single command line argument: 'BLOGS_POST', 'BLOGS_PUT', " +
            "'BLOGS_DELETE', 'FILES_POST', 'FILES_POST_DIRECT', 'FILES_PUT', "
            + "'FILES_PUT_DIRECT', or 'FILES_DELETE'.");
    }

}
