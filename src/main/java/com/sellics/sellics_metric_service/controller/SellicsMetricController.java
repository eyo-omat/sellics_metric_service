package com.sellics.sellics_metric_service.controller;

import com.sellics.sellics_metric_service.model.AsinData;
import com.sellics.sellics_metric_service.service.SellicService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/sellics_metrics")
@RequiredArgsConstructor
public class SellicsMetricController {

    @NonNull
    private SellicService service;

    @GetMapping("/individual-ranks/{keyword}/{asin}")
    public ResponseEntity<List<AsinData>> fetchIndividualRanksForAsinForKeyword(@PathVariable String keyword, @PathVariable String asin) throws IOException {
        List<AsinData> asinDataList = service.fetchIndividualRanksForAsinForKeyword(keyword, asin);
        return ResponseEntity.ok(asinDataList);
    }

    @GetMapping("/aggregated-ranks/{keyword}")
    public ResponseEntity<Map<Integer, List<AsinData>>> fetchAggregatedRanksForKeyword(@PathVariable String keyword) throws IOException {
        Map<Integer, List<AsinData>> map = service.fetchAggregatedRanksForKeyword(keyword);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/ranks/{asin}")
    public ResponseEntity<String> fetchRanksForKeyword(@PathVariable String asin) {
        return ResponseEntity.ok("Aggregated Ranks for ASIN: " + asin);
    }
}
