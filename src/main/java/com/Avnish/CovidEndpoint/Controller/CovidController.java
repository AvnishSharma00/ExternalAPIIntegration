package com.Avnish.CovidEndpoint.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Avnish.CovidEndpoint.Service.CovidService;

@RestController
@RequestMapping("/Covid")
public class CovidController {
    @Autowired
    private CovidService covidService;
    @GetMapping("/get")
    public ResponseEntity<?> callRapidEndpoint()
    {
        return ResponseEntity.ok(covidService.getallCoviData());

    }

}
