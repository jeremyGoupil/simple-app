package fr.arolla.core;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;


public class ParserXMLHandler extends DefaultHandler {

    // nom des tags XML
    private final String ITEM = "item";
    private final String TITLE = "title";
    private final String LINK = "link";
    private final String PUBDATE = "pubDate";
    private final String CREATOR = "creator";
    private final String DESCRIPTION = "description";

    // Array list de feeds
    private ArrayList<Feed> feeds;

    // Boolean permettant de savoir si nous sommes ‡ l'intÈrieur d'un item
    private boolean inItem;

    // Feed courant
    private Feed currentFeed;

    // Buffer permettant de contenir les donnÈes d'un tag XML
    private StringBuffer buffer;

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        super.processingInstruction(target, data);
    }

    public ParserXMLHandler() {
        super();
    }


    // * Cette mÈthode est appelÈe par le parser une et une seule
    // * fois au dÈmarrage de l'analyse de votre flux xml.
    // * Elle est appelÈe avant toutes les autres mÈthodes de l'interface,
    // * ‡ l'exception unique, Èvidemment, de la mÈthode setDocumentLocator.
    // * Cet ÈvÈnement devrait vous permettre d'initialiser tout ce qui doit
    // * l'Ítre avant ledÈbut du parcours du document.

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        feeds = new ArrayList<Feed>();

    }

    /*
      * Fonction Ètant dÈclenchÈe lorsque le parser trouve un tag XML
      * C'est cette mÈthode que nous allons utiliser pour instancier un nouveau feed
      */
    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        // Nous rÈinitialisons le buffer a chaque fois qu'il rencontre un item
        buffer = new StringBuffer();

        // Ci dessous, localName contient le nom du tag rencontrÈ

        // Nous avons rencontrÈ un tag ITEM, il faut donc instancier un nouveau feed
        if (localName.equalsIgnoreCase(ITEM)) {
            this.currentFeed = new Feed();
            inItem = true;
        }

        // Vous pouvez dÈfinir des actions ‡ effectuer pour chaque item rencontrÈ
        if (localName.equalsIgnoreCase(TITLE)) {
            // Nothing to do
        }
        if (localName.equalsIgnoreCase(LINK)) {
            // Nothing to do
        }
        if (localName.equalsIgnoreCase(PUBDATE)) {
            // Nothing to do
        }
        if (localName.equalsIgnoreCase(CREATOR)) {
            // Nothing to do
        }
        if (localName.equalsIgnoreCase(DESCRIPTION)) {
            // Nothing to do
        }
    }


    // * Fonction Ètant dÈclenchÈe lorsque le parser ‡ parsÈ
    // * l'intÈrieur de la balise XML La mÈthode characters
    // * a donc fait son ouvrage et tous les caractËre inclus
    // * dans la balise en cours sont copiÈs dans le buffer
    // * On peut donc tranquillement les rÈcupÈrer pour complÈter
    // * notre objet currentFeed

    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {

        if (localName.equalsIgnoreCase(TITLE)) {
            if (inItem) {
                // Les caractËres sont dans l'objet buffer
                this.currentFeed.setTitle(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(LINK)) {
            if (inItem) {
                this.currentFeed.setLink(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(PUBDATE)) {
            if (inItem) {
                this.currentFeed.setPubDate(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(CREATOR)) {
            if (inItem) {
                this.currentFeed.setCreator(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(DESCRIPTION)) {
            if (inItem) {
                this.currentFeed.setDescription(buffer.toString());
                buffer = null;
            }
        }
        if (localName.equalsIgnoreCase(ITEM)) {
            feeds.add(currentFeed);
            inItem = false;
        }
    }

    // * Tout ce qui est dans l'arborescence mais n'est pas partie
    // * intÈgrante d'un tag, dÈclenche la levÈe de cet ÈvÈnement.
    // * En gÈnÈral, cet ÈvÈnement est donc levÈ tout simplement
    // * par la prÈsence de texte entre la balise d'ouverture et
    // * la balise de fermeture

    public void characters(char[] ch, int start, int length) throws SAXException {
        String lecture = new String(ch, start, length);
        if (buffer != null) buffer.append(lecture);
    }


    // cette mÈthode nous permettra de rÈcupÈrer les donnÈes
    public ArrayList<Feed> getData() {
        return feeds;
    }
}
