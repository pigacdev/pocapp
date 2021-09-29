package com.pidev.poc.utils;

import com.pidev.poc.jaxb.DataDetailsType;
import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

@Component
public class JaxbUtils {

    public static <T> T unmarshal(final Class<T> pClazz, final InputStream pInputStream) {
        try {
            JAXBContext tJAXBContext = JAXBContext.newInstance(pClazz);
            Unmarshaller tUnmarshaller = tJAXBContext.createUnmarshaller();

            JAXBElement<T> root = tUnmarshaller.unmarshal(new StreamSource(
                    pInputStream), pClazz);
            T element = root.getValue();
            return element;
        } catch (JAXBException e) {
            throw new RuntimeException("Unmarshalling error: ", e);
        }
    }

    public static void jaxbObjectToXML(DataDetailsType pDataDetailsType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DataDetailsType.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // To format XML
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //If we DO NOT have JAXB annotated class
            JAXBElement<DataDetailsType> jaxbElement =
                    new JAXBElement<DataDetailsType>( new QName("http://aaa.hr/test/v0/DataDetails", "DataDetails"),
                            DataDetailsType.class,
                            pDataDetailsType);

            jaxbMarshaller.marshal(jaxbElement, System.out);

            //If we have JAXB annotated class
            //jaxbMarshaller.marshal(employeeObj, System.out);

        } catch (JAXBException e) {
            throw new RuntimeException("Marshalling error: ", e);
        }
    }
}
