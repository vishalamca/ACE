package com.example.actit.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContactNumber
{
    @JsonProperty("type")
    public ContactNumberType Type ;
    @JsonProperty("number")
    public String Number;
}
