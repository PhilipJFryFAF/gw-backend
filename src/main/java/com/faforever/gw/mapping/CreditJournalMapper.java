package com.faforever.gw.mapping;

import com.faforever.gw.model.Battle;
import com.faforever.gw.model.CreditJournalEntry;
import com.faforever.gw.tables.records.CreditJournalRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

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
