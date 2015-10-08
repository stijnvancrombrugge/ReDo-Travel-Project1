package com.realdolmen.project1.XML;

import com.realdolmen.project1.XML.TripElement;
import com.realdolmen.project1.XML.TripsElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JVDAX31 on 8/10/2015.
 */
public class TripXMLParser implements Serializable{

    /*
        @ManyToOne
    private Location From;

    @ManyToOne
    private Location Destination;

    private Date departureDate;
    private Date returnDate;
    private String Description;
    private Double pricePerDay;
    private String picturename;
    private int totalPlaces;
    private int availablePlaces;
     */

    public List<TripElement> parseXML(String filename){

        try {
            //web-module\src\main\trips.xml
            File file = new File(filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(TripsElement.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            TripsElement que= (TripsElement) jaxbUnmarshaller.unmarshal(file);

            return que.getTrip();

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }



   // public static void main(String[] args) {

        /*
        boolean bFrom = false;
        boolean bTo = false;
        boolean bDepartureDate = false;
        boolean bReturnDate = false;
        boolean bDescription =false;
        boolean bPricePerDay = false;
        boolean
        boolean bFreeplaces = false;
        boolean bFlights = false;
        boolean bFlight = false;
        boolean bFlightID = false;
        String from;
        String to;
        String freeplaces;
        String flightID;
        List<String> allFlights = new ArrayList<>();

        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath);
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(
                            new FileReader("web-module\\src\\main\\trips.xml"));

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("trip")) {
                            System.out.println("Start Element : student");
                         } else if (qName.equalsIgnoreCase("from")) {
                            bFrom = true;
                        } else if (qName.equalsIgnoreCase("to")) {
                            bTo = true;
                        } else if (qName.equalsIgnoreCase("freeplaces")) {
                            bFreeplaces = true;
                        } else if (qName.equalsIgnoreCase("flights")) {
                            bFlights = true;
                        } else if (qName.equalsIgnoreCase("flight")) {
                            bFlight = true;
                        } else if (qName.equalsIgnoreCase("flightID")) {
                            bFlightID = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bFrom){
                            System.out.println("From: "
                                    + characters.getData());
                            bFrom = false;
                        }
                        if(bTo){
                            System.out.println("To: "
                                    + characters.getData());
                            bTo = false;
                        }
                        if(bFreeplaces){
                            System.out.println("Freeplaces: "
                                    + characters.getData());
                            bFreeplaces = false;
                        }
                        if(bFlightID){
                            flightID = characters.getData();
                            System.out.println("FlightID: "
                                    + flightID);
                            allFlights.add(flightID);
                            bFlightID = false;

                        }

                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equalsIgnoreCase("trip")){
                            System.out.println("End Element : trip");
                            System.out.println("nbr of flights: " + allFlights.size());
                            System.out.println();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        */


    /*

        try {

            File file = new File("web-module\\src\\main\\trips.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(TripsElement.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            TripsElement que= (TripsElement) jaxbUnmarshaller.unmarshal(file);
            System.out.println(que.getTrip());

            List<TripElement> list=que.getTrip();
            for(TripElement ans:list) {
                System.out.println(ans.getFrom() + " " + ans.getTo() + "  " + ans.getFreeplaces()+ "  " + ans.getDepartureDate() + "  " + ans.getPricePerDay()
                        + "  " + ans.getDescription()  + "  " + ans.getPicturename()  + "  " + ans.getAvailablePlaces()  + "  " + ans.getReturnDate()
                        + "  " + ans.getTotalPlaces());
                List<FlightElement> l = ans.getFlight();
                for(FlightElement f:l)
                    System.out.println(f.getFlightID());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    */
}

