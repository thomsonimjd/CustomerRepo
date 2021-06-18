package com.syniti;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CustomerDataValidator validator = new CustomerDataValidator();
        Set<String> invalidCustomerIds = validator.validateCustomerData();
        System.out.println(invalidCustomerIds);
    }
}
