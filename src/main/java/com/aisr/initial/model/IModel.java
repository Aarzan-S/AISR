package com.aisr.initial.model;

import java.io.IOException;
import java.util.List;

public interface IModel<T> {
    int connect();
    int disconnect();
    T get(String name);
    List<T> getAll();;
    int add(T subject);
    int update(T subject, int index);
    List<T> loadData();
    int register() throws IOException;
}
