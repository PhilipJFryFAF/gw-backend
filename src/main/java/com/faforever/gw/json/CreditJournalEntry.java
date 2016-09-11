package com.faforever.gw.json;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CreditJournalEntry {
    private Integer amount = null;
    private Battle battle = null;
    private Integer id = null;
    private String reason = null;
    private Timestamp transactionDate = null;
    private UnitTransaction unitTransaction = null;

    public CreditJournalEntry(Integer amount, Battle battle, Integer id, String reason, Timestamp transactionDate, UnitTransaction unitTransaction) {
        this.amount = amount;
        this.battle = battle;
        this.id = id;
        this.reason = reason;
        this.transactionDate = transactionDate;
        this.unitTransaction = unitTransaction;
    }

    public Integer getAmount() {
        return amount;
    }

    public Battle getBattle() {
        return battle;
    }

    public Integer getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public UnitTransaction getUnitTransaction() {
        return unitTransaction;
    }
}
