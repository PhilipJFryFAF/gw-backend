package com.faforever.gw.mapping;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.model.BattleParticipant;
import com.faforever.gw.model.Character;
import com.faforever.gw.repository.CharacterRepository;
import com.faforever.gw.tables.records.BattleParticipantsRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class BattleParticipantMapper implements RecordMapper<BattleParticipantsRecord, BattleParticipant> {
    @Resource
    DSLContext dslContext;

    @Resource
    CharacterRepository characterRepository;


    @Override
    public BattleParticipant map(BattleParticipantsRecord r) {
        return null;//new BattleParticipant(getCharacter(r.getFkCharacter()), r.getResult(), r.getRole());
    }

    private Character getCharacter(Long characterID) {
        return characterRepository.findOne(characterID, null);
    }
}
