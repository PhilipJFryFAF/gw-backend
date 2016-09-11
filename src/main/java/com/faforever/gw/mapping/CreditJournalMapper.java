package com.faforever.gw.mapping;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.json.Battle;
import com.faforever.gw.json.BattleParticipant;
import com.faforever.gw.json.Character;
import com.faforever.gw.json.CreditJournalEntry;
import com.faforever.gw.tables.CreditJournal;
import com.faforever.gw.tables.records.CreditJournalRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.exception.MappingException;

import java.util.List;

import static com.faforever.gw.Tables.BATTLE_PARTICIPANTS;

public class CreditJournalMapper implements RecordMapper<CreditJournalRecord, CreditJournalEntry> {
    private DSLContext dslContext;

    private CreditJournalMapper(){}

    public CreditJournalMapper(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    @Override
    public CreditJournalEntry map(CreditJournalRecord r) {
        try {
        return new CreditJournalEntry(r.getAmount(), Battle.selectById(dslContext, r.getFkBattle()), r.getId(), r.getReason(), r.getTransactionDate(), null);
        }
        catch( EntityNotFoundException e ){
            throw new MappingException("Battle not found. id="+r.getFkBattle());
        }
    }

}
