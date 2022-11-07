package me.siyum.schola.dao;

import me.siyum.schola.entity.SuperEntity;

public interface CrudDAO<T extends SuperEntity,ID> {
    public boolean save(T t);
}
