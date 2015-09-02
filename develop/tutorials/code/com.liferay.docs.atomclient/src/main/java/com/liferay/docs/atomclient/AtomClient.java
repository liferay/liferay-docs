package com.liferay.docs.atomclient;

import java.util.Date;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.parser.stax.util.FOMHelper;
import org.apache.abdera.protocol.Response.ResponseType;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.abdera.protocol.client.ClientResponse;
import org.apache.abdera.protocol.client.RequestOptions;
import org.apache.abdera.protocol.client.util.BaseRequestEntity;

import com.liferay.portal.kernel.util.Base64;

public class AtomClient {

	public static void main(String[] args) {
		if (args.length == 0) {
			showHelp();
			
			return;
		}
		
		String url = "http://localhost:8080/api/atom/blogs";
		
		if (args[0].equals("POST")) {
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
                client.post(url + "?groupId=20146", requestEntity, options);

            if (response.getType() == ResponseType.SUCCESS) {
                System.out.println("HTTP POST succeeded!");
            }
            else {
                System.out.println("HTTP POST failed!!");
            }
		}
		else if (args[0].equals("PUT")) {
            Abdera abdera = new Abdera();
            Entry entry = abdera.newEntry();
            entry.setId("tag:liferay.com:blogs:entry:21308");
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
                url + "/21308?companyId=20118", requestEntity, options);

            if (response.getType() == ResponseType.SUCCESS) {
                System.out.println("HTTP PUT succeeded!");
            }
            else {
                System.out.println("HTTP PUT failed!");
            }
        }
		else if (args[0].equals("DELETE")) {
            Abdera abdera = new Abdera();
            AbderaClient client = new AbderaClient(abdera);
            RequestOptions options = createRequestOptions(client);

            ClientResponse response = client.delete(
                url + "/21315?companyId=20118", options);

            if (response.getType() == ResponseType.SUCCESS) {
                System.out.println("HTTP DELETE succeeded!");
            }
            else {
                System.out.println("HTTP DELETE failed!");
            }
		}
		else {
			showHelp();
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
            + "single command line argument: 'POST', 'PUT', or 'DELETE'.");
	}

}
