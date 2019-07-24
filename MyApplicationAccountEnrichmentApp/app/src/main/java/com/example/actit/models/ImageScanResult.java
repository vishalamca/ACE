package com.example.actit.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageScanResult extends ExcelUploadResult {
    public ImageScanResult() {
        super();
    }

    public String BusinessCardUrl;
}
