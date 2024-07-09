package formagio.santander.controller.dto;

import java.math.BigDecimal;

import formagio.santander.model.Account;

public record AccountDTO(Long id, String number, String agency, BigDecimal balance, BigDecimal limit) {

    public AccountDTO(Account model) {
        this(model.getId(), model.getNumber(), model.getAgency(), model.getBalance(), model.getLimit());
    }

    public Account toModel() {
        Account model = new Account();
        model.setId(this.id);
        model.setNumber(this.number);
        model.setAgency(this.agency);
        model.setBalance(this.balance);
        model.setLimit(this.limit);
        return model;
    }
}