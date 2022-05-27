package com.devdowns.nicaraguantaxcalculator.controller;

import com.devdowns.nicaraguantaxcalculator.SalaryInformation;
import com.devdowns.nicaraguantaxcalculator.SalaryDetail;
import com.devdowns.nicaraguantaxcalculator.service.SalaryDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaxCalculatorController {

    private final SalaryDetailService salaryDetailService;

    @GetMapping("/salary")
    public ResponseEntity<SalaryDetail> getSalaryDetails(@RequestBody SalaryInformation salary){
        log.info("hit controller");
        var details = salaryDetailService.getSalaryDetails(salary);
        return ResponseEntity.ok().body(details);

    }
}
