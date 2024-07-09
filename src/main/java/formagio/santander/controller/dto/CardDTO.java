package formagio.santander.controller.dto;

import java.math.BigDecimal;

import formagio.santander.model.Card;

public record CardDTO(Long id, String number, BigDecimal limit) {

    public CardDTO(Card model) {
        this(model.getId(), model.getNumber(), model.getLimit());
    }

    public Card toModel() {
        Card model = new Card();
        model.setId(this.id);
        model.setNumber(this.number);
        model.setLimit(this.limit);
        return model;
    }
}