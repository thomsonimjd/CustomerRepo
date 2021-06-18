package com.syniti;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class CustomerDataValidatorTest {
    private static final String CUSTOMER_ID_1 = "93825";
    private static final String CUSTOMER_ID_2 = "93825";
    private static final String CUSTOMER_ID_3 = "d94cd";
    private static final String CUSTOMER_ID_4 = "test_id";
    private CustomerDataValidator customerData;

    @Before
    public void setUp() {
        customerData = new CustomerDataValidator();
    }

    @Test
    public void readCustomerDataJSONSuccess() {
        List<Customer> customers = customerData.readCustomerDataJSON();
        Assert.assertFalse(customers.isEmpty());
    }

    @Test
    public void validateCustomerDataSuccess() {
        Set<String> invalidCustomerIds = customerData.validateCustomerData();
        Assert.assertFalse(invalidCustomerIds.isEmpty());
        Assert.assertTrue(invalidCustomerIds.contains(CUSTOMER_ID_1));
        Assert.assertTrue(invalidCustomerIds.contains(CUSTOMER_ID_2));
        Assert.assertTrue(invalidCustomerIds.contains(CUSTOMER_ID_3));
    }

    @Test
    public void validateCustomerDataFail() {
        Set<String> invalidCustomerIds = customerData.validateCustomerData();
        Assert.assertFalse(invalidCustomerIds.contains(CUSTOMER_ID_4));
    }
}
