package br.game.common;

import br.common.models.selectionRuleSubjects.SelectionRuleSubject;
import br.common.models.selectionRuleSubjects.SelectionRuleSubjectDAO;
import br.common.models.selectionRules.SelectionRule;
import br.common.models.subject.Subject;
import br.configuration.controllers.gameConfig.ruleActions.Actions;

import java.security.SecureRandom;
import java.util.*;

public class DetermineSubject {
    private Random random = new SecureRandom();
    private ArrayList<SelectionRuleSubject> subjects = new ArrayList<>();
    private ArrayList<SelectionRule> selectionRules;
    private Actions ruleAction;

    public DetermineSubject(ArrayList<SelectionRule> selectionRules, Actions ruleAction) {
        this.selectionRules = selectionRules;
        this.ruleAction = ruleAction;
        SelectionRuleSubjectDAO subjectDAO = new SelectionRuleSubjectDAO();
        selectionRules.forEach(selectionRule -> this.subjects.addAll(subjectDAO.allBySelectionRule(selectionRule)));
    }

    public Subject determine() {
        Map<Subject, Integer> subjectsGroupCount = new HashMap<>();

        this.subjects.forEach(selectionRuleSubject -> {
            int count = subjectsGroupCount.get(selectionRuleSubject.getSubject());
            subjectsGroupCount.put(selectionRuleSubject.getSubject(), count + 1);
        });

        List list = new LinkedList<>(subjectsGroupCount.entrySet());

        list.sort((count1, count2) -> ((Integer) count1).compareTo((Integer) count2));

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap<>();

        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry<Subject, Integer> entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }

        if (this.ruleAction == Actions.EMBRACING) {
            //
        }

        return null;
    }
}
