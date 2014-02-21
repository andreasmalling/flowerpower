import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.*;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

@FacesComponent("YoloComponent")
public class YoloComponent extends UIComponentBase {

	
	@Override
	public String getFamily() { return "YoloComponent";}
	
	public YoloComponent(){
		
	}
	
	@Override
	public void encodeBegin(FacesContext arg) throws IOException{
		super.encodeBegin(arg);
		
		}
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException{

		String value = (String) getAttributes().get("value");
		System.out.print(value);
		
		try {
			Document old = new SAXBuilder().build(new ByteArrayInputStream(value.getBytes()));
			Element newRoot = (Element)htmlify(old.getRootElement());
			
			List<Content> detached = new ArrayList<Content>();
			for (Content c : newRoot.getChildren()) {
				c.detach();
				detached.add(c);
			}
			
			
			Document newDoc = new Document(detached); // possibly detach
			XMLOutputter out = new XMLOutputter();
			out.output(newDoc, context.getResponseWriter());
		} catch (JDOMException e) {
			
		}
		
	}

	private Content htmlify(Content old) {
		
		
		
		Element test = new Element("b");
		test.setText("Bold text");
		
		Element doc = new Element("document");
		doc.addContent(test);
		
		return doc;
	}
}


//Element document = doc.root();





//metode: (HTML)Element toHTML((Tr�ls)Element e) {
	
//	Content htmlElement;
//if (content er element) {}
//		if (e.name == "list") // list, item, bold, italics
//		htmlElement = new Element("ul");
//	else if (e.name == "item")
//		htmlElement = new Element("li");
//		...
//	else if (content er tekst)
//		return e;


//	List<Element> children = e.getChildren();


//	for (Element child : children) {
//		Element translatedChild = toHTML(child);
//		htmlElement.addContent(translatedChild);
//	}


//	return htmlElement;


