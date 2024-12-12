package com.fatma.customer.customer;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document    //it is a mongo db database
public class Customer {


    @Id
    private String id;
    private String firstname;
    private String lastname;
    private Address adress;
    private  String email;



}
