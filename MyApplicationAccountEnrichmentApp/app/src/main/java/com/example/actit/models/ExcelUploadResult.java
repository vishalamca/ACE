package com.example.actit.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExcelUploadResult
{
    public ExcelUploadResult()
    {
        ContactDetails = new ArrayList<Contact>();
    }
    @JsonProperty("status")
    public Status Status;
    @JsonProperty("errorMessage")
    public String ErrorMessage;
    @JsonProperty("contactDetails")
    public ArrayList<Contact> ContactDetails;
}
