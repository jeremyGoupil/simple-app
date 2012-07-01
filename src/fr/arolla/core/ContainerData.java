package fr.arolla.core;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ContainerData {


    public ContainerData() {

    }


    public static ArrayList<Feed> getFeeds(final String urlString) {
        // On passe par une classe factory pour obtenir une instance de sax
        SAXParserFactory fabrique = SAXParserFactory.newInstance();
        SAXParser parseur = null;
        ArrayList<Feed> feeds = null;
        try {
            // On "fabrique" une instance de SAXParser
            parseur = fabrique.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        // On défini l'url du fichier XML
        URL url = null;
        try {

            url = new URL(urlString);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        /*
           * Le handler sera gestionnaire du fichier XML c'est à dire que c'est lui qui sera chargé
           * des opérations de parsing. On vera cette classe en détails ci après.
          */
        DefaultHandler handler = new ParserXMLHandler();
        try {
            // On parse le fichier XML
            parseur.parse(url.openConnection().getInputStream(), handler);

            // On récupère directement la liste des feeds
            feeds = ((ParserXMLHandler) handler).getData();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On la retourne l'array list
        return feeds;
    }

}
