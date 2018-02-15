package com.dimas.ymlparser.service;

import com.dimas.ymlparser.model.Model;
import com.dimas.ymlparser.model.Offer;
import com.dimas.ymlparser.model.Variation;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    public static List<Model> getModels(List<Offer> offers) {
        Map<String, Model> map = new HashMap<>();
        for (Offer offer : offers) {
            String key = offer.getGroupId();
            if (key == null) {
                key = offer.getId();
            }
            if (map.containsKey(key)) {
                Model model = map.get(key);
                Variation variation = new Variation();
                variation.setVariationId(offer.getId());
                variation.setParams(offer.getParams());
                variation.setModel(model);
                model.getVariations().add(variation);
            } else {
                Model model = new Model(key, offer.getPicture(), offer.getDescription());
                map.put(key, model);
                Variation variation = new Variation();
                variation.setVariationId(offer.getId());
                variation.setParams(offer.getParams());
                variation.setModel(model);
                if (model.getVariations() == null) {
                    model.setVariations(new HashSet<>());
                }
                model.getVariations().add(variation);
            }
        }
        return new ArrayList<>(map.values());
    }


    public static List<Offer> parseXML(String fileName) {
        List<Offer> empList = new ArrayList<>();
        Offer emp = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equalsIgnoreCase("Offer")) {
                        emp = new Offer();
                        //Get the 'id' attribute from Offer element
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            emp.setId(idAttr.getValue());
                        }
                        Attribute grooupIdAttr = startElement.getAttributeByName(new QName("group_id"));
                        if (grooupIdAttr != null) {
                            emp.setGroupId(grooupIdAttr.getValue());
                        }
                    }
                    //set the other varibles from xml elements
                    else if (startElement.getName().getLocalPart().equalsIgnoreCase("price")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setPrice(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("name")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("picture")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setPicture(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("description")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        if (xmlEvent.isEndElement()) {
                            emp.setDescription("");
                        } else {
                            emp.setDescription(xmlEvent.asCharacters().getData());
                        }

                    } else if (startElement.getName().getLocalPart().equals("param")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        Map<String, String> map = emp.getParams();
                        Attribute nameAttr = startElement.getAttributeByName(new QName("name"));
                        if (nameAttr != null) {
                            if (xmlEvent.isEndElement()) {
                                map.put(nameAttr.getValue(), "");
                            } else {
                                map.put(nameAttr.getValue(), xmlEvent.asCharacters().getData());
                            }
                        }
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("Offer")) {
                        empList.add(emp);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return empList;
    }

}
