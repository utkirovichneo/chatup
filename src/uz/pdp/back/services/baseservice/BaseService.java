package uz.pdp.back.services.baseservice;

import java.util.List;

public interface BaseService<T> {
    void create(T t);
    T edit(String id, String newValue);
    void delete(String id);
    T get(String id);
    List<T> getList();
}
