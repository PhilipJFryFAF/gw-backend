package com.faforever.gw.mapping;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.json.BattleParticipant;
import com.faforever.gw.json.Character;
import com.faforever.gw.tables.records.BattleParticipantsRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.exception.DataTypeException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class BattleParticipantMapper implements RecordMapper<BattleParticipantsRecord, BattleParticipant> {
    @Resource
    public DSLContext dslContext;


    @Override
    public BattleParticipant map(BattleParticipantsRecord r) {
        return null;//new BattleParticipant(getCharacter(r.getFkCharacter()), r.getResult(), r.getRole());
    }

    private Optional<Character> getCharacter(int characterID) throws EntityNotFoundException {
        return Character.selectById(dslContext, characterID);
    }
}
