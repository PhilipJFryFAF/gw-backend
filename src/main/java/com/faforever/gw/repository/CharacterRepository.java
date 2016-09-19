package com.faforever.gw.repository;

import com.faforever.gw.Tables;
import com.faforever.gw.exceptions.SemanticsException;
import com.faforever.gw.mapping.CharacterMapper;
import com.faforever.gw.model.Character;
import com.faforever.gw.tables.records.RankRecord;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiFindAllWithIds;
import io.katharsis.repository.annotations.JsonApiFindOne;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.resource.exception.ResourceNotFoundException;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

import static com.faforever.gw.Tables.*;
import static org.jooq.impl.DSL.sum;


@JsonApiResourceRepository(Character.class)
@Component
public class CharacterRepository {
    @Resource
    DSLContext dslContext;

    @Resource
    CharacterMapper characterMapper;


    @Lazy
    public CharacterRepository() {
    }


    @JsonApiFindOne
    public Character findOne(Long characterId, QueryParams requestParams) {
        Optional<Character> characterOptional = dslContext.selectFrom(CHARACTER)
                .where(CHARACTER.ID.equal(characterId))
                .fetchOptional(characterMapper);

        if (characterOptional.isPresent()) {
            Character character = characterOptional.get();

            Long currentCredits = dslContext.select(sum(CREDIT_JOURNAL.AMOUNT)).from(CREDIT_JOURNAL).where(CREDIT_JOURNAL.FK_CHARACTER.equal(characterId)).fetchOneInto(Long.class);
            character.setCurrentCredits(currentCredits);

            Long currentXp = dslContext.select(sum(XP_JOURNAL.AMOUNT)).from(XP_JOURNAL).where(XP_JOURNAL.FK_CHARACTER.equal(characterId)).fetchOneInto(Long.class);
            character.setCurrentXp(currentXp);

            Long rankId = dslContext.select(PROMOTION.NEW_RANK).from(PROMOTION).where(PROMOTION.FK_CHARACTER.equal(characterId)).orderBy(PROMOTION.CREATED_AT.desc()).limit(1).fetchOneInto(Long.class);
            character.setRankId(rankId);

            RankRecord ranksRecord = dslContext.selectFrom(RANK).where(RANK.LEVEL.eq(character.getRankId())).fetchOptionalInto(RankRecord.class)
                    .orElseThrow(() -> new SemanticsException("There must be a rank for each character rank level."));

            switch (character.getFaction()) {
                case "A":
                    character.setRankTitle(ranksRecord.getAeonTitle());
                    break;
                case "C":
                    character.setRankTitle(ranksRecord.getCybranTitle());
                    break;
                case "S":
                    character.setRankTitle(ranksRecord.getSeraphTitle());
                    break;
                case "U":
                    character.setRankTitle(ranksRecord.getUefTitle());
                    break;
            }

            return character;
        }

        throw new ResourceNotFoundException("Character not found!");
    }

    @JsonApiFindAll
    public Iterable<Character> findAll(QueryParams requestParams) {
        return dslContext.selectFrom(Tables.CHARACTER).fetch(characterMapper);
    }

    @JsonApiFindAllWithIds
    public Iterable<Character> findAll(Iterable<Long> characterIds, QueryParams requestParams) {
        return dslContext.selectFrom(Tables.CHARACTER).fetch(characterMapper);
    }

}
