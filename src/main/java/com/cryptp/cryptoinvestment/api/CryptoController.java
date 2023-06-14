package com.cryptp.cryptoinvestment.api;

import com.cryptp.cryptoinvestment.application.CryptoService;
import com.cryptp.cryptoinvestment.domain.model.CryptoEnum;
import com.cryptp.cryptoinvestment.domain.model.CryptoExtreme;
import com.cryptp.cryptoinvestment.domain.model.CryptoNormalizedRange;
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

    @GetMapping("/getDescendingList")
    public ResponseEntity<TreeSet<CryptoNormalizedRange>> getDescendingList(){
        return new ResponseEntity<>(cryptoService.parseAll(), HttpStatus.OK);
    }
    @GetMapping("/getExtreme/{crypto}")
    public ResponseEntity<CryptoExtreme> getExtreme(@PathVariable("crypto") CryptoEnum cryptoEnum){
        cryptoService.parse(cryptoEnum).getExtreme();
        return new ResponseEntity<>(cryptoService.parse(cryptoEnum).getExtreme(), HttpStatus.OK);
    }

    @GetMapping("/getByDay/{day}")
    public ResponseEntity<CryptoNormalizedRange> getExtreme(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd") Date day){
        return new ResponseEntity<>(cryptoService.parseAll(day).first(), HttpStatus.OK);
    }
}
