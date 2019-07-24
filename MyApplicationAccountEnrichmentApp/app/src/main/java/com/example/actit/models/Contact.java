package com.example.actit.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact
{
    public Contact()
    {
        ContactNumbers = new ArrayList<ContactNumber>();
        AddressLines = new ArrayList<String>();
        EmailAddresses = new ArrayList<String>();
        Websites = new ArrayList<String>();
        UnidentifiedLines = new ArrayList<String>();
    }
        @JsonProperty("name")
    public String Name ;
    @JsonProperty("jobTitle")
    public String JobTitle;
    @JsonProperty("company")
    public String Company;
    @JsonProperty("contactNumbers")
    public ArrayList<ContactNumber> ContactNumbers;
    @JsonProperty("emailAddresses")
    public ArrayList<String> EmailAddresses;
    @JsonProperty("addressLines")
    public ArrayList<String> AddressLines;
    @JsonProperty("websites")
    public ArrayList<String> Websites;
    @JsonProperty("unidentifiedLines")
    public ArrayList<String> UnidentifiedLines;
}

