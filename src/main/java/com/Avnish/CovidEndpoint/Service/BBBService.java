package com.Avnish.CovidEndpoint.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.Avnish.CovidEndpoint.Utils.BBBUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
    import java.security.NoSuchAlgorithmException;
    
    @Service
    public class BBBService {
    
    
        
    

        private String apiUrl="https://manager.bigbluemeeting.com/bigbluebutton/";
    
        
        private String secret="YgQrXPst1edSmD3C04BfPdB77Z7AxsoGRou6y0T7z9";


        @Autowired
        private RestTemplate restTemplate;
    
     

      
   public String generateJoinUrl(String meetingId, String fullName, String password) {
        try {
            String apiMethod="join";
            String queryString = "fullName=" + URLEncoder.encode(fullName, "UTF-8") +
                                 "&meetingID=" + URLEncoder.encode(meetingId, "UTF-8") +
                                 "&password=" + URLEncoder.encode(password, "UTF-8");

            String checksum = BBBUtils.generateChecksum("join", queryString, secret);

          UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + "/join")
                                                               .queryParam("fullName", fullName)
                                                               .queryParam("meetingID", meetingId)
                                                               .queryParam("password", password)
                                                               .queryParam("checksum", checksum);

           return builder.toUriString();
       /*    String requestUrl = apiUrl + apiMethod + "?" + queryString + "&checksum=" + checksum;

           return restTemplate.getForObject(requestUrl, String.class);
         */
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding URL parameters", e);
        }
    }

    
      
    
           public String createMeeting(String meetingID, String meetingName, String moderatorPW, String attendeePW) {
        String apiMethod = "create";
        String queryString = "name=" + URLEncoder.encode(meetingName, StandardCharsets.UTF_8) +
                             "&meetingID=" + URLEncoder.encode(meetingID, StandardCharsets.UTF_8) +
                             "&moderatorPW=" + URLEncoder.encode(moderatorPW, StandardCharsets.UTF_8) +
                             "&attendeePW=" + URLEncoder.encode(attendeePW, StandardCharsets.UTF_8)+
                             "&record=true";

        String checksum = BBBUtils.generateChecksum(apiMethod, queryString, secret);
        String requestUrl = apiUrl + apiMethod + "?" + queryString +"&checksum=" + checksum;

        return restTemplate.getForObject(requestUrl, String.class);
    }

    public String joinMeeting(String meetingID, String fullName, String password) {
        String apiMethod = "join";
        String queryString = "fullName=" + URLEncoder.encode(fullName, StandardCharsets.UTF_8) +
                             "&meetingID=" + URLEncoder.encode(meetingID, StandardCharsets.UTF_8) +
                             "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

        String checksum = BBBUtils.generateChecksum(apiMethod, queryString, secret);
        String requestUrl = apiUrl + apiMethod + "?" + queryString + "&checksum=" + checksum;

        return restTemplate.getForObject(requestUrl, String.class);
    }

    public String endMeeting(String meetingID, String password) {
        String apiMethod = "end";
        String queryString = "meetingID=" + URLEncoder.encode(meetingID, StandardCharsets.UTF_8) +
                             "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

        String checksum = BBBUtils.generateChecksum(apiMethod, queryString, secret);
        String requestUrl = apiUrl + apiMethod + "?" + queryString + "&checksum=" + checksum;

        return restTemplate.getForObject(requestUrl, String.class);
    }
    public String getMeetingInfo(String meetingID,String password)
    {
        String apiMethod="getMeetingInfo";
     
    String queryString = "meetingID=" + meetingID + "&password=" + password;
    String checksum = BBBUtils.generateChecksum("getMeetingInfo", queryString, secret);
    String url = apiUrl + apiMethod+"?"+queryString + "&checksum=" + checksum;
    
    // Perform HTTP GET request to the above URL and return response
    return restTemplate.getForObject(url, String.class);
}
public String getMeeting()
{
    String apiMethod="getMeetings";
    String queryString="";

    String checksum=BBBUtils.generateChecksum(apiMethod, queryString, secret);
    String url =apiUrl +apiMethod+"?"+ queryString + "&checksum=" + checksum;
    
    // Perform HTTP GET request to the above URL and return response
    return restTemplate.getForObject(url, String.class);
}
    public String isMeetingRunning(String  meetingID)
    {

        String queryString="meetingID="+meetingID;
        String apiMethod="isMeetingRunning";
        String checksum=BBBUtils.generateChecksum("isMeetingRunning",queryString, secret);
        String url=apiUrl+apiMethod+"?"+queryString+"&checksum="+checksum;
        return restTemplate.getForObject(url, String.class);
    }
    public String getRecordings(String meetingId)
    {
        String queryString="meetingID="+meetingId;
        String apiMethod="getRecordings";
        String checksum=BBBUtils.generateChecksum(apiMethod, queryString, secret);
        String url=apiUrl+apiMethod+"?"+queryString+"&checksum="+checksum;
        return restTemplate.getForObject(url,String.class);
   
    }
    public String publishRecordings(String meetingId)
    {
        String queryString="meetingID="+meetingId;
        String apiMethod="publishRecordings";
        String checksum=BBBUtils.generateChecksum(apiMethod, queryString, secret);
        String url=apiUrl+apiMethod+"?"+queryString+"&checksum="+checksum;
        return restTemplate.getForObject(url, String.class);
    }
    }
    
    

