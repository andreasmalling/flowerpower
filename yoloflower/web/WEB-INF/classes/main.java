
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class main {
private static ArrayList itemObjectList = new ArrayList();
private static Item item;
/**
     * @param args the command line arguments
     */
    public static ArrayList main(String[] args) throws JDOMException, IOException {
        SAXBuilder saxybitch = new SAXBuilder();
        // Hvor awesome er det lige, at man kan give en SAXBuilder en url som argument?
        Document d = saxybitch.build("http://services.brics.dk/java4/cloud/listItems?shopID=237");
       // giver en liste med alle Item-børn til Items-forældren        
        List<Element> items = d.getRootElement().getChildren();
        // for every Item child of Items, an Item-object is created with the parameters associated with a given item. 
        
        for (int i = 0; i < items.size(); i++) {
            Namespace w = Namespace.getNamespace("http://www.cs.au.dk/dWebTek/2014");
            int itemID = Integer.parseInt(items.get(i).getChildText("itemID", w));
            String itemName = items.get(i).getChildText("itemName", w);
            int itemPrice = Integer.parseInt(items.get(i).getChildText("itemPrice", w));
            String itemURL = items.get(i).getChildText("itemURL", w);
            String itemDescription = items.get(i).getChildText("itemDescription", w);
            int itemStock = Integer.parseInt(items.get(i).getChildText("itemStock", w));

            item = new Item(itemID, itemName, itemPrice, itemURL, itemDescription, itemStock);
            itemObjectList.add(item);  
        }
        return itemObjectList;
          
    }
    
}
