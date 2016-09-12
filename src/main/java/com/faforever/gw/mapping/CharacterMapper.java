package com.faforever.gw.mapping;

import com.faforever.gw.model.Character;
import com.faforever.gw.repository.CharacterRepository;
import com.faforever.gw.tables.records.CharactersRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CharacterMapper implements RecordMapper<CharactersRecord, Character> {
    @Resource
    DSLContext dslContext;

    @Resource
    CharacterRepository characterRepository;

    @Override
    public Character map(CharactersRecord r) {

        Character killer = null;
        if(r.getKilledBy() != null)
            killer = characterRepository.findOne(r.getKilledBy(), null);

        return new Character(null, null, r.getFaction(), r.getId(), null, killer, r.getName(), null, null);
    }
}
