package org.itsci.assist_decisions.controller;

import org.itsci.assist_decisions.model.Ban_Type;
import org.itsci.assist_decisions.model.History_Ban;
import org.itsci.assist_decisions.model.Interest;
import org.itsci.assist_decisions.service.BanTypeService;
import org.itsci.assist_decisions.service.HistoryBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bantypes")
public class BanTypeController {

    @Autowired
    private BanTypeService banTypeService;

    @PostMapping("/add")
    public ResponseEntity doBanType(@RequestBody Map<String,String> map){
        try{
            Ban_Type banType = banTypeService.saveBanType(map);
            return new ResponseEntity<>(banType, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/list")
    public ResponseEntity listBanTypes(){
        try{
            List<Ban_Type> banTypes =  banTypeService.getAllBanTypes();
            return new ResponseEntity(banTypes, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
