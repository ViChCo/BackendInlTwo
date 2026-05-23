package se.yrgo.services.calls;
import se.yrgo.domain.Action;
import se.yrgo.domain.Call;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;
import se.yrgo.services.diary.DiaryManagementService;


import java.util.Collection;

//2-1.

public class CallHandlingServiceImpl implements  CallHandlingService {

    private CustomerManagementService customerService;
    private DiaryManagementService diaryService;

    public void setCustomerService(CustomerManagementService customerService) {
        this.customerService = customerService;
    }

    public void setDiaryService(DiaryManagementService diaryService) {
        this.diaryService = diaryService;
    }

    /**
     * steg 1: Anropa recordCall metoden i CustomerManagementService.
     * steg 2: Anropa recordAction metoden i DiaryManagementService
     *
     * CustomerManagementService och DairyManagemtService är båda beroenden till denna klassen.
     * de är inte kopplad till varandra
     *
     * @param customerId
     * @param newCall
     * @param actions
     * @throws CustomerNotFoundException
     */
    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions)
            throws CustomerNotFoundException {
        customerService.recordCall(customerId, newCall);

        for ( Action action : actions) {
            diaryService.recordAction(action);
        }

        }
    }

