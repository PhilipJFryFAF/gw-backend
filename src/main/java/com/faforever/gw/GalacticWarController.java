package com.faforever.gw;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.json.Battle;
import com.faforever.gw.json.Character;
import com.faforever.gw.mapping.BattleMapper;
import com.faforever.gw.tables.pojos.MapPool;
import org.jooq.DSLContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import static com.faforever.gw.Tables.BATTLES;

@Controller
@RequestMapping("/gw")
public class GalacticWarController {

    @Resource
    private DSLContext dslContext;

    @RequestMapping(value = "battles/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Battle getBattleById(@PathVariable("id") int battleID) {
        List<Battle> battles = dslContext.selectFrom(BATTLES).where(BATTLES.ID.equal(battleID)).fetch().map(new BattleMapper(dslContext));
        return battles.get(0);
    }

    @RequestMapping(value = "characters/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Character> getCharacterById(@PathVariable("id") int characterID) {
        try {
            return new ResponseEntity(Character.selectById(dslContext, characterID),HttpStatus.OK);
        }
        catch(EntityNotFoundException e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "mappool", method = RequestMethod.GET)
    public
    @ResponseBody
    List<MapPool> getMapPool() {
        return dslContext.selectFrom(Tables.MAP_POOL).fetchInto(MapPool.class);
    }
}
