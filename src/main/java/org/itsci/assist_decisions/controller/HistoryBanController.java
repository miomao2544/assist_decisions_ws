package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Choice;
import org.itsci.assist_decisions.model.History_Ban;
import org.itsci.assist_decisions.service.HistoryBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/historys")
public class HistoryBanController {
    @Autowired
    private HistoryBanService historyBanService;

    @PostMapping("/add")
    public ResponseEntity doBanStatus(@RequestBody Map<String,String> map){
        try{
            History_Ban historyBan = historyBanService.doBanStatus(map);
            return new ResponseEntity<>(historyBan, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/status/{username}/{status}")
    public ResponseEntity updateStatus(@PathVariable("username") String username,@PathVariable("status") String status){
        try{
             historyBanService.updateStatus(username,status);
            return new ResponseEntity<>("Update "+ status, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
