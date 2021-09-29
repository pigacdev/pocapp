package com.pidev.poc.dto;

import com.pidev.poc.jaxb.OfficeType;
import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DataDetailsDto {

    // Operation
    private String id;
    private XMLGregorianCalendar arrivalDateTime;
    private String storingFlag;
    private String type;
    private String additionalType;
    private XMLGregorianCalendar acceptanceDate;

    // OfficeType
    private String originReferenceNumber;
    private String destinationReferenceNumber;

    private XMLGregorianCalendar dateOfStart;
    private BigDecimal accountValue;
    private String exporterReferenceNumber;
    private List<OfficeType> visitingLocations;
    
    
//    private Operation operation;
//    private OfficeType origin;
//    private OfficeType destination;
//    private XMLGregorianCalendar dateOfStart;
//    private BigDecimal accountValue;
//    private OfficeType exporter;
//    private List<OfficeType> visitingLocations;
}
