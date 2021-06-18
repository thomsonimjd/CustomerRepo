package com.syniti;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerDataValidator {

    /*
     * fetches the customer data and filters invalid data alone
     * */
    public Set<String> validateCustomerData() {
        List<Customer> customers = readCustomerDataJSON();
        return filterInvalidCustomerIds(customers);
    }

    /*
     * filters customer ids of invalid zipcode, name and address
     * zip code is validated against regex of US zipcode format
     * */
    public Set<String> filterInvalidCustomerIds(List<Customer> customers) {

        /**
         * Regular Expression to match the US Zip-Code
         */
        String regex = "^[0-9]{5}(?:-[0-9]{4})?$";

        /*
         * has tables to store address, zip and name of customer to validate if duplicate
         * */
        Set<String> addressTable = new HashSet<>();
        Set<String> zipTable = new HashSet<>();
        Set<String> nameTable = new HashSet<>();

        Set<String> invalidCustomerIds = new HashSet<>();
        for (Customer cs : customers) {
            if (isEmpty(cs.getAddress()) || addressTable.contains(cs.getAddress()))
                invalidCustomerIds.add(cs.getId());
            else
                addressTable.add(cs.getAddress());

            if (isEmpty(cs.getZip()) || zipTable.contains(cs.getZip()) || !cs.getZip().matches(regex))
                invalidCustomerIds.add(cs.getId());
            else
                zipTable.add(cs.getZip());

            if (isEmpty(cs.getName()) || nameTable.contains(cs.getName()))
                invalidCustomerIds.add(cs.getId());
            else
                nameTable.add(cs.getName());
        }
        return invalidCustomerIds;
    }

    /*
     *  read the json from from resources and converts that to List of Customer object
     * */
    public List<Customer> readCustomerDataJSON() {
        ObjectMapper mapper = new ObjectMapper();
        List<Customer> customers = null;
        try {
            customers = mapper.readValue(ClassLoader.getSystemResource("data.json"), new TypeReference<List<Customer>>() {
            });
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /*
     * Check whether the given String is empty
     * */
    private boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

}
