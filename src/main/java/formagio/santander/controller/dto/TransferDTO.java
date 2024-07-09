package formagio.santander.controller.dto;

import java.math.BigDecimal;

public record TransferDTO(String OriginAccount, String DestinationAccount, BigDecimal amount) {

}
