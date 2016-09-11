package com.faforever.gw.mapping;

import com.faforever.gw.json.Battle;
import com.faforever.gw.json.BattleParticipant;
import com.faforever.gw.tables.records.BattleParticipantsRecord;
import com.faforever.gw.tables.records.BattlesRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.faforever.gw.Tables.BATTLE_PARTICIPANTS;

public class BattleMapper implements RecordMapper<BattlesRecord, Battle> {
    DSLContext dslContext;

    private BattleMapper(){}

    public BattleMapper(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    @Override
    public Battle map(BattlesRecord r) {
        return new Battle(r.getAttackingFaction(), r.getDefendingFaction(), r.getEndedAt(), r.getId(), r.getInitiatedAt(), null, r.getStartedAt(), r.getStatus(), r.getWinningFaction());
    }

}
