package com.cryptp.cryptoinvestment.api;

import com.cryptp.cryptoinvestment.application.CryptoService;
import com.cryptp.cryptoinvestment.domain.model.CryptoEnum;
import com.cryptp.cryptoinvestment.domain.model.CryptoExtreme;
import com.cryptp.cryptoinvestment.domain.model.CryptoNormalizedRange;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.TreeSet;

@RequiredArgsConstructor
@RestController
public class CryptoController {

    private final CryptoService cryptoService;

    @Operation(summary = "Endpoint that will return a descending sorted list of all the cryptos," +
            "comparing the normalized range (i.e. (max-min)/min)")
    @ApiResponse(responseCode = "200", description = "Set of cryptos sorted",
            content = { @Content(mediaType = "application/json")})
    @GetMapping("/getDescendingNormRangeList")
    public ResponseEntity<TreeSet<CryptoNormalizedRange>> getDescendingNormRangeList(){
        return new ResponseEntity<>(cryptoService.parseAll(), HttpStatus.OK);
    }

    @Operation(summary ="Endpoint that will return the oldest/newest/min/max values for a requested crypto")
    @ApiResponse(responseCode = "200", description = "Oldest/newest/min/max values for specific coin",
            content = { @Content(mediaType = "application/json")})
    @GetMapping("/getExtreme/{crypto}")
    public ResponseEntity<CryptoExtreme> getExtreme(@PathVariable("crypto") CryptoEnum cryptoEnum){
        return new ResponseEntity<>(cryptoService.parse(cryptoEnum).getExtreme(), HttpStatus.OK);
    }

    @Operation(summary ="Endpoint that will return the crypto with the highest normalized range for a specific day")
    @ApiResponse(responseCode = "200", description = "Coin with highest normalized range in a specific day",
            content = { @Content(mediaType = "application/json")})
    @GetMapping("/getHighNormRange/{day}")
    public ResponseEntity<CryptoNormalizedRange> getHighNormRange(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd") Date day){
        return new ResponseEntity<>(cryptoService.parseAll(day).first(), HttpStatus.OK);
    }
}
