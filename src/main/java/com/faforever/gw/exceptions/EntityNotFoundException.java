package com.faforever.gw.exceptions;

public class EntityNotFoundException extends Exception {
    private Class entityClass;
    private String searchCriteria;

    public EntityNotFoundException(Class entityClass, String searchCriteria) {
        super(String.format("Entity of type %s could not be found. Search criteria was: %s", entityClass.toString(), searchCriteria));
        this.entityClass = entityClass;
        this.searchCriteria = searchCriteria;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }
}
