package me.siyum.schola.dao;

public interface CrudDAO<T,ID> {
    public boolean save(T t);
}
