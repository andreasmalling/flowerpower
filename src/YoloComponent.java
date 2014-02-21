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
import org.jdom2.output.Format;
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
		// System.out.print(value);
		
		try {
			XMLOutputter out = new XMLOutputter(Format.getRawFormat().setOmitDeclaration(true));
			Document old = new SAXBuilder().build(new ByteArrayInputStream(value.getBytes()));
			Element newRoot = (Element)htmlify(old.getRootElement());
			Document newDoc = new Document(newRoot); // possibly detach
			out.output(newDoc, context.getResponseWriter());
		} catch (JDOMException e) {
			
		}
		
	}

	private Content htmlify(Content old) {
		if( old instanceof Text){
			return new Text(((Text)old).getText());
		}
		
		Element work = (Element) old;
		// System.out.println(work.getName());
		Element htmlElement = null;
		
		if(work.getName().equals("italics") ){
			htmlElement = new Element("i");
			//htmlElement.setText("Sjov");
		} else if (work.getName().equals("bold") ){
			htmlElement = new Element("b");
			//htmlElement.setText(work.getText());
		} else if (work.getName().equals("list") ){
			htmlElement = new Element("ul");
			//htmlElement.setText(work.getText());
		} else if (work.getName().equals("item") ){
			htmlElement = new Element("li");
			//htmlElement.setText(work.getText());
		}else if (work.getName().equals("document") ){
			htmlElement = new Element("div");
		}
		
		for (Content e : work.getContent()){
			htmlElement.addContent(htmlify(e));
		}
		return htmlElement;
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


