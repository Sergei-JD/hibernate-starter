package com.hibernate.dao;

import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class PaymentFilter {
    String firstName;
    String lastName;
}
