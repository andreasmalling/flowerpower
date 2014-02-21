import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jdom2.JDOMException;


@ManagedBean(name="ShopBean")
@ApplicationScoped

public class ShopBean {
	private String message;
	private ArrayList<Item> items = null;
	private Item item = null;

	public ShopBean(){
		updateItems();
		// item = new Item(520, "a" ,1 ,"b", "c" ,1);
	}
	
	public String modifyAction() {
		System.out.println("Modify");
		if (item != null)
			System.out.println(item.getItemID());
		else
			System.out.println("null");
		return "MODIFY";
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public ArrayList<Item> updateItems(){
		try {
			 items = new Communication().getItems();
		} catch(Exception e) {
			message = e.getMessage();
			
		}
		return items;
	}
	
	public String modify(){
		try {
			new Communication().modifyItem(item);
			updateItems();
			System.out.println("OK MODIFY");
		} catch (Exception e) {
			message = e.getMessage();
			System.out.println("WRONG MODIFY");
			return "WRONG";
		}
		return "OK";
	}
	
	public Item getItem(){
		return item;
	}
	public void setItem(Item i){
		System.out.println("Setting item to " + i.getItemID());
		item = i;
	}
	public String getMessage(){
		return message;
	}
}
