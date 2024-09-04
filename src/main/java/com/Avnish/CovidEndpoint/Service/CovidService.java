package com.Avnish.CovidEndpoint.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.Avnish.CovidEndpoint.Congigure.REstTemp;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CovidService {
    /*
     * OkHttpClient client = new OkHttpClient();

Request request = new Request.Builder()
	.url("https://covid-19-data.p.rapidapi.com/country/code?format=json&code=")
	.get()
	.addHeader("x-rapidapi-key", "b8929f0e79msh2828157ffd7a342p1be05cjsnde3a26d5c362")
	.addHeader("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
	.build();

Response response = client.newCall(request).execute();
     */
    private static final String url="https://covid-19-data.p.rapidapi.com/country/code?format=json&code=in";
    private static final String x_rapid_key="b8929f0e79msh2828157ffd7a342p1be05cjsnde3a26d5c362";
    private static final String host="covid-19-data.p.rapidapi.com";
    @Autowired
    private RestTemplate restTemplate;
    public Object getallCoviData(){
        
  try{
    HttpHeaders headers= new HttpHeaders();
    headers.set("x-rapidapi-key", x_rapid_key);
    headers.set("x-rapidapi-host", host);
    ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,new HttpEntity<>(headers),String.class);
     log.info("output from rapid api",response.getBody());
     return response.getBody();

  }catch(Exception e){
    log.error("something went wrong");
    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Exception while calling endpoint ",e);
    }

}
}
