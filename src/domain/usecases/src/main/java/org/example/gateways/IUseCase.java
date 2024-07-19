package org.example.gateways;

public interface IUseCase <T> {
    public abstract T execute(T value);
}
