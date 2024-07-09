package formagio.santander.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import formagio.santander.model.enums.StatementType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AccountStatement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(targetEntity = Account.class )
	//@JoinColumn(referencedColumnName = "number")
	private Account account;
	private LocalDate data;
	@Column(precision = 14, scale = 2)
	private BigDecimal previousBalance;
	@Column(name = "statement_value",precision = 14, scale = 2)
	private BigDecimal value;
	@Column(precision = 14, scale = 2)
	private BigDecimal currentBalance;
	@Column(length = 255)
	private String description;
	@Enumerated(EnumType.STRING)
	private StatementType statementType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}
	public void setPreviousBalance(BigDecimal previousBalance) {
		this.previousBalance = previousBalance;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatementType getStatementType() {
		return statementType;
	}
	public void setStatementType(StatementType type) {
		this.statementType = type;
	}
	
	

}
