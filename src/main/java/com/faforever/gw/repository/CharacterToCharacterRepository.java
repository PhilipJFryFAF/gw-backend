package com.faforever.gw.repository;

import com.faforever.gw.model.Character;
import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindOneTarget;
import io.katharsis.repository.annotations.JsonApiRelationshipRepository;
import io.katharsis.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@JsonApiRelationshipRepository(source = Character.class, target = Character.class)
@Repository
public class CharacterToCharacterRepository {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterToCharacterRepository(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @JsonApiFindOneTarget
    public Character findOneTarget(Long id, String fieldName, QueryParams requestParams) {
        Character character = characterRepository.findOne(id, requestParams);
        try {
            return (Character) PropertyUtils.getProperty(character, fieldName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
