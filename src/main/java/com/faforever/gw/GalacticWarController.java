package com.faforever.gw;

import com.faforever.gw.exceptions.EntityNotFoundException;
import com.faforever.gw.json.Battle;
import com.faforever.gw.json.Character;
import com.faforever.gw.json.CreditJournalEntry;
import com.faforever.gw.mapping.CreditJournalMapper;
import com.faforever.gw.tables.pojos.CreditJournal;
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

import static com.faforever.gw.Tables.CHARACTERS;
import static com.faforever.gw.Tables.CREDIT_JOURNAL;

@Controller
@RequestMapping("/gw")
public class GalacticWarController {

    @Resource
    private DSLContext dslContext;

    @RequestMapping(value = "battles/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Battle> getBattleById(@PathVariable("id") int battleID) {
        try {
            return new ResponseEntity(Battle.selectById(dslContext, battleID), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "characters/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Character> getCharacterById(@PathVariable("id") int characterID) {
        try {
            return new ResponseEntity(Character.selectById(dslContext, characterID), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "characters/{id}/credit_journal", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<CreditJournalEntry>> getCreditJournal(@PathVariable("id") int characterID) {
        int count = dslContext.select(CHARACTERS.ID.count()).from(CHARACTERS).where(CHARACTERS.ID.equal(characterID)).fetchOne(0, int.class);

        if (count == 0)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        List<CreditJournalEntry> creditJournals = dslContext.selectFrom(CREDIT_JOURNAL).where(CHARACTERS.ID.equal(characterID)).fetch().map(new CreditJournalMapper(dslContext));
        return new ResponseEntity(null, HttpStatus.OK);
    }


    @RequestMapping(value = "mappool", method = RequestMethod.GET)
    public
    @ResponseBody
    List<MapPool> getMapPool() {
        return dslContext.selectFrom(Tables.MAP_POOL).fetchInto(MapPool.class);
    }
}
