
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Communication {

    static final String KEY = "CB91051165CC463A0975655C";
    static final File SCHEMA = new File("cloud.xsd");
    static final Namespace w = Namespace.getNamespace("http://www.cs.au.dk/dWebTek/2014");

    /**
     *
     * returns "OK" or "WRONG"
     */
    public String modifyItem(Item i) throws JDOMException, IOException {
        Element modifyItem = new Element("modifyItem", w);
        Document modifyItemDoc = new Document(modifyItem);
        modifyItem.addContent(new Element("shopKey", w).setText(KEY));
        modifyItem.addContent(new Element("itemID", w).setText(Integer.toString(i.getItemID())));
        modifyItem.addContent(new Element("itemName", w).setText(i.getItemName()));
        modifyItem.addContent(new Element("itemPrice", w).setText(Integer.toString(i.getItemPrice())));
        modifyItem.addContent(new Element("itemURL", w).setText(i.getItemURL()));
        modifyItem.addContent(new Element("itemDescription", w).setText(i.getItemDescription()));
        
        if (validate(modifyItemDoc)) {
        	postHttpRequest("http://services.brics.dk/java4/cloud/modifyItem", modifyItemDoc);
        }
        return "OK";
    }

    /**
     *
     * returns "OK" or "WRONG"
     */
    public String createItem(Item i) throws JDOMException, IOException {
        Element createItem = new Element("createItem", w);
        Document d = new Document(createItem);
        createItem.addContent(new Element("shopKey", w).setText(KEY));
        createItem.addContent(new Element("itemName", w).setText(i.getItemName()));

        if (validate(d)) {
            postHttpRequest("http://services.brics.dk/java4/cloud/createItem", d);
        }
        return "OK";
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> shopItems = new ArrayList<Item>();

        return shopItems;
    }

    public String adjustItem(Item i) throws JDOMException, IOException {
		return null;	// FIXME: Dummy-return
    }

    @SuppressWarnings("deprecation")
    private boolean validate(Document d) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(true);
        builder.setProperty(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        builder.setProperty(
                "http://java.sun.com/xml/jaxp/properties/schemaSource",
                SCHEMA);

        builder.build(d);
        return true;
    }

    private Document XML2Document(Item i) {
    	return null;	// FIXME: Dummy-return
    }

    public static String postHttpRequest(String requestURL, Document doc) throws IOException {
        URL URL = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        outputter.output(doc, connection.getOutputStream());
        
        return null;	// FIXME: Dummy-return
    }

    public static Document getHttpRequest(String requestURL) throws IOException {
        URL URL = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        
        return null;	// FIXME: Dummy-return
    }
}