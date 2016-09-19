package com.faforever.gw.mapping;

import com.faforever.gw.model.Battle;
import com.faforever.gw.model.Planet;
import com.faforever.gw.tables.records.BattleRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.jooq.exception.MappingException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class BattleMapper implements RecordMapper<BattleRecord, Battle> {
    @Resource
    public DSLContext create;

    @Override
    public Battle map(BattleRecord r) {
        Optional<Planet> planetOptional = Planet.selectById(create, r.getFkPlanet());

        if(planetOptional.isPresent())
            return new Battle(r.getAttackingFaction(), r.getDefendingFaction(), r.getEndedAt(), r.getId(), r.getInitiatedAt(), null, r.getStartedAt(), r.getStatus(), r.getWinningFaction());
        else
            throw new MappingException("Planet not found ID="+r.getFkPlanet());
    }

}
