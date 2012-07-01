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

    // Boolean permettant de savoir si nous sommes à l'intèrieur d'un item
    private boolean inItem;

    // Feed courant
    private Feed currentFeed;

    // Buffer permettant de contenir les données d'un tag XML
    private StringBuffer buffer;

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        super.processingInstruction(target, data);
    }

    public ParserXMLHandler() {
        super();
    }


    // * Cette méthode est appelée par le parser une et une seule
    // * fois au démarrage de l'analyse de votre flux xml.
    // * Elle est appelée avant toutes les autres méthodes de l'interface,
    // * à l'exception unique, évidemment, de la méthode setDocumentLocator.
    // * Cet évènement devrait vous permettre d'initialiser tout ce qui doit
    // * l'être avant le début du parcours du document.

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        feeds = new ArrayList<Feed>();

    }

    /*
      * Fonction étant déclenchée lorsque le parser trouve un tag XML
      * C'est cette méthode que nous allons utiliser pour instancier un nouveau feed
      */
    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        // Nous réinitialisons le buffer a chaque fois qu'il rencontre un item
        buffer = new StringBuffer();

        // Ci dessous, localName contient le nom du tag rencontré

        // Nous avons rencontré un tag ITEM, il faut donc instancier un nouveau feed
        if (localName.equalsIgnoreCase(ITEM)) {
            this.currentFeed = new Feed();
            inItem = true;
        }

        // Vous pouvez dèfinir des actions à effectuer pour chaque item rencontré
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


    // * Fonction étant déclenchée lorsque le parser à parsé
    // * l'intèrieur de la balise XML La méthode characters
    // * a donc fait son ouvrage et tous les caractères inclus
    // * dans la balise en cours sont copiés dans le buffer
    // * On peut donc tranquillement les récupèrer pour compléter
    // * notre objet currentFeed

    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {

        if (localName.equalsIgnoreCase(TITLE)) {
            if (inItem) {
                // Les caractères sont dans l'objet buffer
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
    // * intègrante d'un tag, déclenche la levée de cet évènement.
    // * En général, cet évènement est donc levé tout simplement
    // * par la présence de texte entre la balise d'ouverture et
    // * la balise de fermeture

    public void characters(char[] ch, int start, int length) throws SAXException {
        String lecture = new String(ch, start, length);
        if (buffer != null) buffer.append(lecture);
    }


    // cette méthode nous permettra de récupérer les données
    public ArrayList<Feed> getData() {
        return feeds;
    }
}
