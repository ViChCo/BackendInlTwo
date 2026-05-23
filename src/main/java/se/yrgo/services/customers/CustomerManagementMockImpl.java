package se.yrgo.services.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;

public class CustomerManagementMockImpl implements CustomerManagementService {
    private HashMap<String, Customer> customerMap;

    public CustomerManagementMockImpl() {
        customerMap = new HashMap<String, Customer>();
        customerMap.put("OB74", new Customer("OB74", "Fargo Ltd", "some notes"));
        customerMap.put("NV10", new Customer("NV10", "North Ltd", "some other notes"));
        customerMap.put("RM210", new Customer("RM210", "River Ltd", "some more notes"));
    }


    @Override
    public void newCustomer(Customer newCustomer) {
        customerMap.put(newCustomer.getCustomerId(), newCustomer);
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        customerMap.put(changedCustomer.getCustomerId(), changedCustomer);

    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        // TODO Auto-generated method stub
        customerMap.remove(oldCustomer.getCustomerId());

    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        // TODO Auto-generated method stub
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("No customer found with id: " + customerId);
        }
        return customer;
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        // TODO Auto-generated method stub
        List<Customer> result = new ArrayList<Customer>();
        for (Customer c : customerMap.values()) {
            if (c.getCompanyName().toLowerCase().contains(name.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomers() {
        // TODO Auto-generated method stub
        return new ArrayList<Customer>(customerMap.values());
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        // TODO Auto-generated method stub
        return findCustomerById(customerId);
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
        //First find the customer
        Customer customer = findCustomerById(customerId);

        //Call the addCall on the customer
        customer.addCall(callDetails);

    }
}
