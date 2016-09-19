package com.faforever.gw.mapping;

import com.faforever.gw.model.Character;
import com.faforever.gw.repository.CharacterRepository;
import com.faforever.gw.tables.records.CharacterRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CharacterMapper implements RecordMapper<CharacterRecord, Character> {
    @Resource
    DSLContext dslContext;

    @Resource
    CharacterRepository characterRepository;

    @Override
    public Character map(CharacterRecord r) {
        Character killer = null;
        if (r.getKilledBy() != null)
            killer = characterRepository.findOne(r.getKilledBy(), null);

        return new Character(null, null, r.getFaction(), r.getId(), null, killer, r.getName(), null, null);
    }
}
