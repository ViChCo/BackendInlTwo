package se.yrgo.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.domain.Action;

@Service("diaryService")
@Transactional
public class DiaryManagementServiceMockImpl implements DiaryManagementService {
    private Set<Action> allActions = new HashSet<Action>();

    // TODO Auto-generated method stub
    @Override
    public void recordAction(Action action) {
        allActions.add(action);
        System.out.println("DiaryService: Åtgärd registrerad – " + action.getDetails());
    }

    //Hint:
    //Create a list<Action>
    //In the for each loop going through the list use this condition: "if(action.getOwningUser().equals(requiredUser) && !action.isComplete())" to add a new action to the list.
    @Override
    public List<Action> getAllIncompleteActions(String requiredUser) {
        List<Action> result = new ArrayList<Action>();
        for (Action action : allActions) {
            if (action.getOwningUser().equals(requiredUser) && !action.isComplete()) {
                result.add(action);
            }
        }
        return result;
    }
}