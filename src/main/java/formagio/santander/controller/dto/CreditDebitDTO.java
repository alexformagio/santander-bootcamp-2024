package formagio.santander.controller.dto;

import java.math.BigDecimal;

public record CreditDebitDTO(String account,String description, BigDecimal amount) {

}
