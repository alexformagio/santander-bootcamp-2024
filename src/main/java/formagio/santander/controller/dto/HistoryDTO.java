package formagio.santander.controller.dto;

import java.util.List;

public record HistoryDTO(AccountDTO account, List<AccountStatementDTO> history) {

}
