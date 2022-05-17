package Rest;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ServiceConfiguration {
    public static final String WS_URI = "http://localhost:8081/epos";

    /* *************************************
     * 			 UTIL METHODS
     ***************************************/
    // Check the status of RESTful WebService
    public static boolean checkWS() {
        Boolean stateOfWS = false;
        try {
            URL siteURL = new URL(getBaseUri().toString()+"/actuator/health");
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if(code == 200) stateOfWS = true;
        } catch(Exception e) {
            // do nothing
        }
        return stateOfWS;
    }
    public static URI getBaseUri() {
        return UriBuilder.fromUri(WS_URI).build();
    }
}
