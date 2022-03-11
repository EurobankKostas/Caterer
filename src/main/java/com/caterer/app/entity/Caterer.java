package com.caterer.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Caterer {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String itemId;

    @Field
    private String location;

    @Field
    private String cityName;

    @Field
    private String streetName;

    @Field
    private Integer streetNumber;

    @Field
    private Integer minGuests;

    @Field
    private Integer maxGuests;

    @Field
    private ContactInfo contactInfo;


}
