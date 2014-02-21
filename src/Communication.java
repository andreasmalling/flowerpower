
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Communication {

    static final String KEY = "CB91051165CC463A0975655C";
    //static final File SCHEMA = new File("cloud.xsd");
    static final Namespace w = Namespace.getNamespace("http://www.cs.au.dk/dWebTek/2014");

    public String modifyItem(Item i) throws JDOMException, IOException {
        Element modifyItem = new Element("modifyItem", w);
        Document modifyItemDoc = new Document(modifyItem);
        modifyItem.addContent(new Element("shopKey", w).setText(KEY));
        modifyItem.addContent(new Element("itemID", w).setText(Integer.toString(i.getItemID())));
        modifyItem.addContent(new Element("itemName", w).setText(i.getItemName()));
        modifyItem.addContent(new Element("itemPrice", w).setText(Integer.toString(i.getItemPrice())));
        modifyItem.addContent(new Element("itemURL", w).setText(i.getItemURL()));
        
        Element itemD = new SAXBuilder().build(new ByteArrayInputStream(i.getItemDescription().getBytes())).getRootElement(); //Detach?
        modifyItem.addContent(new Element("itemDescription", w).addContent(itemD) );//.setText(i.getItemDescription()));      
        new XMLOutputter().output(modifyItemDoc,System.out);	//FIXME
        
        if (validate(modifyItemDoc)) {
        	postHttpRequest("http://services.brics.dk/java4/cloud/modifyItem", modifyItemDoc);
        }
        return "OK";
    }

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

    public ArrayList<Item> getItems() throws JDOMException, IOException {
    	ArrayList<Item> itemObjectList = new ArrayList<Item>();
    	Item item;
    	SAXBuilder saxybitch = new SAXBuilder();
        // Hvor awesome er det lige, at man kan give en SAXBuilder en url som argument?
        Document d = saxybitch.build("http://services.brics.dk/java4/cloud/listItems?shopID=237");
       // giver en liste med alle Item-børn til Items-forældren        
        List<Element> items = d.getRootElement().getChildren();
        // for every Item child of Items, an Item-object is created with the parameters associated with a given item. 
        
        for (int i = 0; i < items.size(); i++) {
            int itemID = Integer.parseInt(items.get(i).getChildText("itemID", w));
            String itemName = items.get(i).getChildText("itemName", w);
            int itemPrice = Integer.parseInt(items.get(i).getChildText("itemPrice", w));
            String itemURL = items.get(i).getChildText("itemURL", w);
            String itemDescription = new XMLOutputter().outputElementContentString(items.get(i).getChild("itemDescription", w));
            int itemStock = Integer.parseInt(items.get(i).getChildText("itemStock", w));

            item = new Item(itemID, itemName, itemPrice, itemURL, itemDescription, itemStock);
            itemObjectList.add(item);  
        }
        return itemObjectList;
    }

    public String adjustItem(Item i) throws JDOMException, IOException {
    	Element adjustItemStock = new Element("adjustItemStock", w);
        Document adjustItem = new Document(adjustItemStock);
        adjustItem.addContent(new Element("shopKey", w).setText(KEY));
        adjustItem.addContent(new Element("adjustment", w).setText(Integer.toString(i.getItemStock())));
        
        if (validate(adjustItem)) {
        	return postHttpRequest("http://services.brics.dk/java4/cloud/modifyItem", adjustItem);
        }
        return "OK";
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
                "http://users-cs.au.dk/amao/test/cloud.xsd");
         final ByteArrayOutputStream out = new ByteArrayOutputStream();
        new XMLOutputter().output(d, out);
 
        builder.build(new ByteArrayInputStream(out.toByteArray()));
        return true;
    }

    private Document XML2Document(Item i) {
    	return null;	// FIXME: Dummy-return
    }
    
    private String Document2XML(Document doc) {
    	return new XMLOutputter().outputString(doc);
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
        
        if (connection.getResponseCode() != 200)
        {
        	 return "Server error! Message: " + connection.getResponseMessage();
        }
        
        return "OK";	// FIXME: Dummy-return
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