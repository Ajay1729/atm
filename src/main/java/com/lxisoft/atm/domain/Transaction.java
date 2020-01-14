package com.lxisoft.atm.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "amount")
    private Double amount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public Transaction transactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Transaction customerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", transactionId=" + getTransactionId() +
            ", customerId=" + getCustomerId() +
            ", amount=" + getAmount() +
            "}";
    }
}
