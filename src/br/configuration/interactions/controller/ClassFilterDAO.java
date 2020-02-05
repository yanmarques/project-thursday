package br.configuration.interactions.controller;

import br.common.database.TableMapping;
import br.common.models.classes.Class;

import java.util.ArrayList;

public interface ClassFilterDAO<T extends TableMapping> {
    ArrayList<T> allByClass(Class classModel);
}
