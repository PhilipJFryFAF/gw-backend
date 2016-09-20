package com.faforever.gw;

import com.faforever.gw.model.Character;
import org.jooq.DSLContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/gw")
public class GalacticWarController {

    @Resource
    private DSLContext dslContext;




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
