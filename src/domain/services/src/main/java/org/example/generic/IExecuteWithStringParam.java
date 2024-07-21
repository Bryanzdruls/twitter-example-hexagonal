package org.example.generic;

public interface IExecuteWithStringParam<T> {
    T execute(String name);

    T execute(T generic, String value, String value2);
}
