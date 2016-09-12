package com.faforever.gw;

import com.faforever.gw.model.Battle;
import com.faforever.gw.model.Character;
import com.faforever.gw.model.CreditJournalEntry;
import com.faforever.gw.mapping.CreditJournalMapper;
import org.jooq.DSLContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.faforever.gw.Tables.CHARACTERS;
import static com.faforever.gw.Tables.CREDIT_JOURNAL;

@RestController
@RequestMapping("/gw")
public class GalacticWarController {

    @Resource
    private DSLContext dslContext;


    @RequestMapping(value = "characters/{id}/credit_journal", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<CreditJournalEntry>> getCreditJournal(@PathVariable("id") Long characterID) {
        int count = dslContext.select(CHARACTERS.ID.count()).from(CHARACTERS).where(CHARACTERS.ID.equal(characterID)).fetchOne(0, int.class);

        if (count == 0)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        List<CreditJournalEntry> creditJournals = dslContext.selectFrom(CREDIT_JOURNAL).where(CHARACTERS.ID.equal(characterID)).fetch().map(new CreditJournalMapper(dslContext));
        return new ResponseEntity(null, HttpStatus.OK);
    }


    @RequestMapping(value = "characters/hall_of_fame/{faction}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<Character>> getHallOfFame(@PathVariable("faction") String faction) {
        return null;
//        List<Character> characterList;
//
//        Field<Integer> credits = create.select(sum(CREDIT_JOURNAL.AMOUNT)).from(CREDIT_JOURNAL).where(CREDIT_JOURNAL.FK_CHARACTER.equal(id)).asField("CREDITS");
//        Field<Integer> xp = create.select(sum(XP_JOURNAL.AMOUNT)).from(XP_JOURNAL).where(XP_JOURNAL.FK_CHARACTER.equal(id)).asField("XP");
//        Field<Integer> rankId = create.select(PROMOTIONS.NEW_RANK).from(PROMOTIONS).where(PROMOTIONS.FK_CHARACTER.equal(id)).orderBy(PROMOTIONS.CREATED_AT.desc()).limit(1).asField("RANK_ID");
//
//        Character c = create.select(CHARACTERS.ID, CHARACTERS.NAME, CHARACTERS.FACTION, CHARACTERS.KILLED_BY, credits, xp, rankId)
//                .from(CHARACTERS)
//                .where(CHARACTERS.ID.equal(id))
//                .fetchOne()
//                .into(Character.class);
//
//        if( faction == null || faction.isEmpty()){
//
//        }

    }
}
