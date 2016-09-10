package com.faforever.gw.json;

import java.time.LocalDateTime;

public class CreditJournalEntry {
    private Long amount = null;
    private Battle battle = null;
    private Long id = null;
    private String reason = null;
    private LocalDateTime transactionDate = null;
    private UnitTransaction unitTransaction = null;

    public CreditJournalEntry(Long amount, Battle battle, Long id, String reason, LocalDateTime transactionDate, UnitTransaction unitTransaction) {
        this.amount = amount;
        this.battle = battle;
        this.id = id;
        this.reason = reason;
        this.transactionDate = transactionDate;
        this.unitTransaction = unitTransaction;
    }

    public Long getAmount() {
        return amount;
    }

    public Battle getBattle() {
        return battle;
    }

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public UnitTransaction getUnitTransaction() {
        return unitTransaction;
    }
}
