package ProjectCrypto.Service;

import ProjectCrypto.Model.Crypto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CryptoService {
    List<Crypto> cryptoList = new ArrayList<>();

    public Crypto addCrypto(Crypto newCryptoList) {
        cryptoList.add(newCryptoList);
        return newCryptoList;
    }

    public List<Crypto> getCryptoList(String sort) {
        return cryptoList.stream()
                .sorted((sort != null) ? switch (sort.toLowerCase()) {
                    case "name" -> Comparator.comparing(Crypto::getName);
                    case "price" -> Comparator.comparing(Crypto::getPrice);
                    case "quantity" -> Comparator.comparing(Crypto::getQuantity);
                    default -> (c1, c2) -> 0;
                } : (c1, c2) -> 0)
                .collect(Collectors.toList());
    }

    public Optional<Crypto> getCryptoId(Integer id) {
        return cryptoList.stream()
                .filter(crypto -> crypto.getId().equals(id))
                .findFirst();
    }
}
