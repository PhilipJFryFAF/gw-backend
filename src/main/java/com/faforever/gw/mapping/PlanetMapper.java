package com.faforever.gw.mapping;

import com.faforever.gw.Tables;
import com.faforever.gw.json.Planet;
import com.faforever.gw.tables.records.MapPoolRecord;
import com.faforever.gw.tables.records.PlanetsRecord;
import org.jooq.DSLContext;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.faforever.gw.Tables.BATTLES;

@Component
public class PlanetMapper implements RecordMapper<PlanetsRecord, Planet> {
    @Resource public DSLContext create;

    @Override
    public Planet map(PlanetsRecord r) {
        String currentOwner = create.select(BATTLES.WINNING_FACTION).from(BATTLES).where(BATTLES.FK_PLANET.eq(r.getId())).orderBy(BATTLES.ENDED_AT.desc()).limit(1).fetchOneInto(String.class);
        return new Planet(currentOwner, r.getFkMap(), r.getFkSunSystem(), r.getGround(), r.getHabitable()!=0, r.getId(), r.getOrbitLevel(), r.getSize());
    }
}
