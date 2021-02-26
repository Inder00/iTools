package pl.inder00.api.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;

import pl.inder00.tools.Tools;

public class URLContent {
	
	private String url;
	
	public URLContent(String url) {
		this.setURL(url);
	}
	public String connect(){
		if(url == null) return null;
		InputStreamReader data;
		URL url;
		String output = null;
		try {
			url = new URL(this.getURL());
			URLConnection conn = url.openConnection();
			conn.connect();
			data = new InputStreamReader(conn.getInputStream());
			BufferedReader buffer = new BufferedReader(data);
			output = buffer.readLine();
		} catch (Exception e) {
			return null;
		}
		return output;
	}
    @SuppressWarnings("unused")
	public void downloadFileFromURL(File destination) {    
        try{
        	URL url = new URL(this.url);

            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();


            File outputFile =destination;           
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            long total = 0;
            while ((len1 = is.read(buffer)) != -1) {
                total += len1;
                fos.write(buffer, 0, len1);
            }
            fos.flush();
            fos.close();
            is.close();
            
            File f = new File(Tools.getInstance().getDataFolder(), "update");
            f.createNewFile();
            
            Bukkit.reload();
       } 
       catch(Exception e){
       }
    }

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}


}
