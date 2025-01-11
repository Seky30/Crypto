package ProjectCrypto.Controller;

import ProjectCrypto.Model.Crypto;
import ProjectCrypto.Service.CryptoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cryptos")

public class CryptoController {
    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @PostMapping
    public ResponseEntity<Crypto> addCrypto(@RequestBody Crypto crypto) {
        return new ResponseEntity<>(cryptoService.addCrypto(crypto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Crypto>> getCryptoList(@RequestParam(required = false) String sort) {
        return ResponseEntity.ok(cryptoService.getCryptoList(sort));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Crypto> getCryptoById(@PathVariable Integer id) {
        return cryptoService.getCryptoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
