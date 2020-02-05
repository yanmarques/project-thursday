package br.configuration.controllers;

public interface ActionsInterface<T extends Enum> {
    T getAction();

    String toString();

    String getDescription();
}
