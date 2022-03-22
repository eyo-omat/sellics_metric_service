package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sellics_metrics")
public class SellicsMetricController {

    @GetMapping("/individual-ranks/{keyword}")
    public ResponseEntity<String> fetchIndividualRanksForKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok("Individual Ranks for keyword: " + keyword);
    }

    @GetMapping("/aggregated-ranks/{keyword}")
    public ResponseEntity<String> fetchAggregatedRanksForKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok("Aggregated Ranks for keyword: " + keyword);
    }

    @GetMapping("/ranks/{asin}")
    public ResponseEntity<String> fetchRanksForKeyword(@PathVariable String asin) {
        return ResponseEntity.ok("Aggregated Ranks for ASIN: " + asin);
    }
}
