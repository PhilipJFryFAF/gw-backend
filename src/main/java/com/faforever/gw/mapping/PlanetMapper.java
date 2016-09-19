package com.faforever.gw.mapping;

import com.faforever.gw.model.Planet;
import com.faforever.gw.tables.records.PlanetRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.faforever.gw.Tables.BATTLE;

@Component
public class PlanetMapper implements RecordMapper<PlanetRecord, Planet> {
    @Resource public DSLContext create;

    @Override
    public Planet map(PlanetRecord r) {
        String currentOwner = create.select(BATTLE.WINNING_FACTION).from(BATTLE).where(BATTLE.FK_PLANET.eq(r.getId())).orderBy(BATTLE.ENDED_AT.desc()).limit(1).fetchOneInto(String.class);
        return new Planet(currentOwner, r.getFkMap(), r.getFkSunSystem(), r.getGround(), r.getHabitable(), r.getId(), r.getOrbitLevel(), r.getSize());
    }
}
