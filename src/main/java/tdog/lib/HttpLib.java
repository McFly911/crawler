package tdog.lib;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public final class HttpLib {
	public static void main(String[] args) throws Exception {
		post("ss");
	}
	
	// HTTP POST request
		public static String post(String urlz) throws Exception {

			String url = "https://news.zing.vn";

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);


			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
			urlParameters.add(new BasicNameValuePair("cn", ""));
			urlParameters.add(new BasicNameValuePair("locale", ""));
			urlParameters.add(new BasicNameValuePair("caller", ""));
			urlParameters.add(new BasicNameValuePair("num", "12345"));
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(post);
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + post.getEntity());
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

			
			String myString = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
System.out.println(myString);
return myString;
		}
}
