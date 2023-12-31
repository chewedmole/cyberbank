package com.codemika.cyberbank.card.api;

import com.codemika.cyberbank.card.annotation.CheckRole;
import com.codemika.cyberbank.card.dto.RsCardOutput;
import com.codemika.cyberbank.card.service.CardService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/card/output")
@Data
public class CardOutputController {
    private final CardService cardService;
    private final RestTemplate restTemplate = new RestTemplate();
    @CheckRole(isUser = true)
    @GetMapping("get-user-credit-cards")
    public String getCreditCards(@RequestParam String token){
        List<RsCardOutput> cards = cardService.getAllCreditCards(token);
        String output = "";
        for (RsCardOutput temp: cards) {
            output += "Название: " + temp.getTitle() + "\n"
                    + "Номер: " + "************" + temp.getAccountNumber().substring(temp.getAccountNumber().length() - 4) + "\n"
                    + "Баланс: " + temp.getBalance() + "\n"
                    + "Срок кредита: " + temp.getCreditTerm() + " месяцев" + "\n"
                    + "---------------------------------------------------\n";
        }
        return output;
    }
}
