package com.Avnish.CovidEndpoint.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.Avnish.CovidEndpoint.Service.BBBService;
import com.Avnish.CovidEndpoint.Utils.BBBUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/bbb")
public class BBBController {
    @Autowired
    private BBBService bbbService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/createMeeting")
    public String createMeeting(@RequestParam String meetingID,
                                @RequestParam String meetingName,
                                @RequestParam String moderatorPW,
                                @RequestParam String attendeePW) {
        return bbbService.createMeeting(meetingID, meetingName, moderatorPW, attendeePW);
    }

   /*  @GetMapping("/joinMeeting")
    public String joinMeeting(@RequestParam String meetingID,
                              @RequestParam String fullName,
                              @RequestParam String password) {
        return bbbService.joinMeeting(meetingID, fullName, password);
    }*/

    @GetMapping("/endMeeting")
    public String endMeeting(@RequestParam String meetingID,
                             @RequestParam String password) {
        return bbbService.endMeeting(meetingID, password);
    }


    @GetMapping("/join")
    public String joinMeeting(@RequestParam String meetingId,
                              @RequestParam String fullName,
                              @RequestParam String password) {
        String joinUrl = bbbService.generateJoinUrl(meetingId, fullName, password);
        return "redirect:" + joinUrl;  // Redirects the user to the BBB join URL
    }

    @GetMapping("/getMeetingInfo")
public String getMeetingInfo(@RequestParam String meetingId, @RequestParam String password) {
    return bbbService.getMeetingInfo(meetingId,password);
}
@GetMapping("/getMeeting")
public String getMeeting()
{
    return bbbService.getMeeting();
}
@GetMapping("/getMeetingRunning")
public String isMeetingRunning(@RequestParam String meetingID)
{
    return bbbService.isMeetingRunning(meetingID);
}
@GetMapping("/getRecordings")
public String getRecordings(@RequestParam String meetingId) {
    return bbbService.getRecordings(meetingId);
}
@GetMapping("/publishRecordings")
public String publishRecordings(@RequestParam String meetingId)
{
    return bbbService.publishRecordings(meetingId);

}

}           
