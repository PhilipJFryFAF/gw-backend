package com.faforever.gw.repository;

import com.faforever.gw.model.Character;
import com.google.common.collect.Sets;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiFindAllWithIds;
import io.katharsis.repository.annotations.JsonApiFindOne;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.resource.exception.ResourceNotFoundException;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

import static com.faforever.gw.Tables.*;
@JsonApiResourceRepository(Character.class)
@Component
public class CharacterRepository {
    /***
     * SQL SELECT:
     SELECT `character`.`id`, `character`.`name`, `character`.`faction`, `battle_participant`.`fk_battle` AS 'killed_at', `character`.`killed_by`, `promotion`.`new_rank` AS 'rank',
     (CASE `character`.`faction`
     WHEN 'A' THEN `rank`.`uef_title`
     WHEN 'C' THEN `rank`.`aeon_title`
     WHEN 'U' THEN `rank`.`uef_title`
     WHEN 'S' THEN `rank`.`cybran_title`
     END) AS "title"
     FROM `character`
     LEFT JOIN `battle_participant`
     ON `character`.`id` = `battle_participant`.`fk_character` AND `battle_participant`.`result` = 'D'
     JOIN (SELECT p1.`new_rank`, p1.`fk_character` FROM `promotion` AS p1
     LEFT JOIN `promotion` AS p2
     ON p1.`fk_character` = p2.`fk_character` AND p1.`created_at` < p2.`created_at`
     WHERE p2.`created_at` IS NULL
     ORDER BY `p1`.`fk_character` ASC) As `promotion`
     ON `character`.`id` = `promotion`.`fk_character`
     JOIN `rank`
     ON `rank`.`level` = `promotion`.`new_rank`
     *
     */

    @Resource
    DSLContext dslContext;

    @JsonApiFindOne
    public Character findOne(Long Id, QueryParams requestParams) {
        com.faforever.gw.tables.Promotion p1 = PROMOTION.as("p1");
        com.faforever.gw.tables.Promotion p2 = PROMOTION.as("p2");

        Character character = dslContext
                .select(CHARACTER.ID, CHARACTER.NAME, CHARACTER.FACTION, BATTLE_PARTICIPANT.FK_BATTLE, CHARACTER.KILLED_BY, PROMOTION.NEW_RANK,
                        DSL.choose(CHARACTER.FACTION)
                                .when("A", RANK.AEON_TITLE)
                                .when("C", RANK.CYBRAN_TITLE)
                                .when("U", RANK.UEF_TITLE)
                                .when("S", RANK.SERAPH_TITLE)
                ).from(CHARACTER)
                .leftJoin(BATTLE_PARTICIPANT)
                .on(CHARACTER.ID.eq(BATTLE_PARTICIPANT.FK_CHARACTER)).and(BATTLE_PARTICIPANT.RESULT.eq("D"))
                .join(
                        dslContext.select(p1.NEW_RANK, p1.FK_CHARACTER)
                                .from(p1)
                                .leftJoin(p2)
                                .on(p1.FK_CHARACTER.eq(p2.FK_CHARACTER))
                                .and(p1.CREATED_AT.lt(p2.CREATED_AT))
                                .where(p2.CREATED_AT.isNull())
                                .asTable("promotion")
                )
                .on(CHARACTER.ID.eq(PROMOTION.FK_CHARACTER))
                .join(RANK)
                .on(RANK.LEVEL.eq(PROMOTION.NEW_RANK))
                .where(CHARACTER.ID.eq(Id))
                .fetchOptionalInto(Character.class)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Character (id=%s) not found.", Id)));

        return character;
    }

    @JsonApiFindAll
    public Iterable<Character> findAll(QueryParams requestParams) {
        com.faforever.gw.tables.Promotion p1 = PROMOTION.as("p1");
        com.faforever.gw.tables.Promotion p2 = PROMOTION.as("p2");

        return dslContext
                .select(CHARACTER.ID, CHARACTER.NAME, CHARACTER.FACTION, BATTLE_PARTICIPANT.FK_BATTLE, CHARACTER.KILLED_BY, PROMOTION.NEW_RANK,
                        DSL.choose(CHARACTER.FACTION)
                                .when("A", RANK.AEON_TITLE)
                                .when("C", RANK.CYBRAN_TITLE)
                                .when("U", RANK.UEF_TITLE)
                                .when("S", RANK.SERAPH_TITLE)
                ).from(CHARACTER)
                .leftJoin(BATTLE_PARTICIPANT)
                .on(CHARACTER.ID.eq(BATTLE_PARTICIPANT.FK_CHARACTER)).and(BATTLE_PARTICIPANT.RESULT.eq("D"))
                .join(
                        dslContext.select(p1.NEW_RANK, p1.FK_CHARACTER)
                                .from(p1)
                                .leftJoin(p2)
                                .on(p1.FK_CHARACTER.eq(p2.FK_CHARACTER))
                                .and(p1.CREATED_AT.lt(p2.CREATED_AT))
                                .where(p2.CREATED_AT.isNull())
                                .asTable("promotion")
                )
                .on(CHARACTER.ID.eq(PROMOTION.FK_CHARACTER))
                .join(RANK)
                .on(RANK.LEVEL.eq(PROMOTION.NEW_RANK))
                .fetchInto(Character.class);
    }

    @JsonApiFindAllWithIds
    public Iterable<Character> findAll(Iterable<Long> Ids, QueryParams requestParams) {
        Collection<Long> idCollection = Sets.newHashSet(Ids);

        com.faforever.gw.tables.Promotion p1 = PROMOTION.as("p1");
        com.faforever.gw.tables.Promotion p2 = PROMOTION.as("p2");

        return dslContext
                .select(CHARACTER.ID, CHARACTER.NAME, CHARACTER.FACTION, BATTLE_PARTICIPANT.FK_BATTLE, CHARACTER.KILLED_BY, PROMOTION.NEW_RANK,
                        DSL.choose(CHARACTER.FACTION)
                                .when("A", RANK.AEON_TITLE)
                                .when("C", RANK.CYBRAN_TITLE)
                                .when("U", RANK.UEF_TITLE)
                                .when("S", RANK.SERAPH_TITLE)
                ).from(CHARACTER)
                .leftJoin(BATTLE_PARTICIPANT)
                .on(CHARACTER.ID.eq(BATTLE_PARTICIPANT.FK_CHARACTER)).and(BATTLE_PARTICIPANT.RESULT.eq("D"))
                .join(
                        dslContext.select(p1.NEW_RANK, p1.FK_CHARACTER)
                                .from(p1)
                                .leftJoin(p2)
                                .on(p1.FK_CHARACTER.eq(p2.FK_CHARACTER))
                                .and(p1.CREATED_AT.lt(p2.CREATED_AT))
                                .where(p2.CREATED_AT.isNull())
                                .asTable("promotion")
                )
                .on(CHARACTER.ID.eq(PROMOTION.FK_CHARACTER))
                .join(RANK)
                .on(RANK.LEVEL.eq(PROMOTION.NEW_RANK))
                .where(CHARACTER.ID.in(idCollection)).fetchInto(Character.class);
    }

}
