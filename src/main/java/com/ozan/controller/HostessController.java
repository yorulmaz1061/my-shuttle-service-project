package com.ozan.controller;

import com.ozan.dto.HostessDTO;
import com.ozan.dto.ResponseWrapper;
import com.ozan.service.HostessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hostess")
public class HostessController {
    private final HostessService hostessService;

    public HostessController(HostessService hostessService) {
        this.hostessService = hostessService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper>createHostess(@RequestBody HostessDTO hostess){
        HostessDTO hostessDTO = hostessService.save(hostess);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Hostess is created."
                , 201, hostessDTO);
        return ResponseEntity.status(201).body(responseWrapper);
    }
    @GetMapping
    public ResponseEntity<ResponseWrapper>getAllHostess(){
        List<HostessDTO> hostessDTOList = hostessService.listAllHostesses();
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Hostesses are listed."
                , 200, hostessDTOList);
        return ResponseEntity.status(200).body(responseWrapper);
    }
    @GetMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>getHostessByTcId(@PathVariable("userTcId") String userTcId){
        HostessDTO hostessDTO = hostessService.findByUserTcId(userTcId);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Hostess is listed."
                , 200, hostessDTO);
        return ResponseEntity.status(200).body(responseWrapper);
    }
    @PutMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>updateHostess(@PathVariable("userTcId") String userTcId, @RequestBody HostessDTO hostessDTO){
        HostessDTO updatedHostessDTO = hostessService.updateHostess(hostessDTO);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Hostess is updated."
                , 200, updatedHostessDTO);
        return ResponseEntity.status(200).body(responseWrapper);
    }
    @DeleteMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper> deleteHostess(@PathVariable("userTcId") String userTcId){
        hostessService.delete(userTcId);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Hostess is deleted."
                , 200, null);
        return ResponseEntity.status(200).body(responseWrapper);
    }


}
