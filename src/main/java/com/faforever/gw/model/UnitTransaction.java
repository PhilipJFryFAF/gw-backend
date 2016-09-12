package com.faforever.gw.model;

import java.time.LocalDateTime;

public class UnitTransaction {
    private Battle battle = null;
    private LocalDateTime createdAt = null;
    private UnitTransactionDetails details = null;
    private Long id = null;
    private Long type = null;

    public UnitTransaction(Battle battle, LocalDateTime createdAt, UnitTransactionDetails details, Long id, Long type) {
        this.battle = battle;
        this.createdAt = createdAt;
        this.details = details;
        this.id = id;
        this.type = type;
    }

    public Battle getBattle() {
        return battle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UnitTransactionDetails getDetails() {
        return details;
    }

    public Long getId() {
        return id;
    }

    public Long getType() {
        return type;
    }
}
