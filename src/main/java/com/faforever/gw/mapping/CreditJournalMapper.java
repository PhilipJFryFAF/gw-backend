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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.faforever.gw.Tables.BATTLE_PARTICIPANTS;

@Component
public class CreditJournalMapper implements RecordMapper<CreditJournalRecord, CreditJournalEntry> {
    @Resource
    public DSLContext dslContext;

    private CreditJournalMapper(){}

    public CreditJournalMapper(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    @Override
    public CreditJournalEntry map(CreditJournalRecord r) {
        Optional<Battle> battleOptional = Battle.selectById(dslContext, r.getFkBattle());
        return new CreditJournalEntry(r.getAmount(), battleOptional.get(), r.getId(), r.getReason(), r.getTransactionDate(), null);
    }

}
