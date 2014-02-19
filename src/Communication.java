
import java.io.IOException;
import java.nio.file.Paths;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Communication {

    static final String key = "CB91051165CC463A0975655C";

    public ArrayList<Item> getItems() {

    }

    /**
     *
     * returns "OK" or "WRONG"
     */
    public String modifyItem(Item i) throws JDOMException, IOException{
Element modifyItem = new Element("modifyItem", w);
            Document modifyItemDoc = new Document(modifyItem);
            modifyItem.addContent(new Element("shopKey",w).setText(KEY));
           modifyItem.addContent(new Element("itemID", w).setText(i.getItemID));
           modifyItem.addContent(new Element("itemName", w).setText(i.getItemName));
           modifyItem.addContent(new Element("itemPrice", w).setText(i.getItemPrice));
           modifyItem.addContent(new Element("itemURL", w).setText(i.getItemURL));
           modifyItem.addContent(new Element("itemDescription", w).setText(i.getItemDescription));

    }

    /**
     *
     * returns "OK" or "WRONG"
     */
    public String createItem(Item i) throws JDOMException, IOException{
        Element createItem = new Element("createItem", w);
        Document createItemDoc = new Document(createItem);
        createItem.addContent(new Element("shopKey", w).setText(KEY));
        createItem.addContent(new Element("itemName", w).setText(i.getItemName());
        validator(createItemDoc);
        //send status til ?
        if (validator(createItemDoc)) {
            serverConnector(createItemDoc, createItem)
    
        }
        /**
         *
         * returns "OK" or "WRONG"
         */
    public String adjustItem(Item i) throws JDOMException, IOException{

    }

    @SuppressWarnings("deprecation")
    private bool validate(Document d) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(true);
        builder.setProperty(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        builder.setProperty(
                "http://java.sun.com/xml/jaxp/properties/schemaSource",
                SCHEMA.toFile());

        builder.build(Document d);                
   return true;
    }

    private Document XML2Document(Item i) {

    }
}
