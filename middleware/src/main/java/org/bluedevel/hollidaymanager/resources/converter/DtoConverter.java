package org.bluedevel.hollidaymanager.resources.converter;

/**
 * @author Robin Engel
 */
public interface DtoConverter<D, M> {
    D toDto(M model);
    M toModel(D dto);
}
