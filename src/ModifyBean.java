
import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.jdom2.JDOMException;


@ManagedBean(name="ModifyBean")
@ViewScoped
public class ModifyBean {
	private String itemID;
	private Item item;
	private String message;
	
	@ManagedProperty("#{ShopBean}")
	transient private ShopBean shopBean;
	
	public ShopBean getShopBean(){
		return shopBean;
	}
	
	public void setShopBean(ShopBean s){
		shopBean = s;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String s){
		message = s;
	}
	
	public String modify(){
		try {
			new Communication().modifyItem(item);
			shopBean.updateItems();
			System.out.println("OK MODIFY");
		} catch (Exception e) {
			message = e.getMessage();
			System.out.println("WRONG MODIFY");
			return "WRONG";
		}
		return "OK";
	}
	
	public String create(){
		try {
			new Communication().createItem(item);
			shopBean.updateItems();
			System.out.println("OK CREATE");
		} catch (Exception e) {
			message = e.getMessage();
			System.out.println("WRONG CREATE");
			return "WRONG";
		}
		return "OK";
	}
	
	public String getItemID(){
		return itemID;
	}
	
	public void setItemID (String s){
		itemID = s;
		for(Item i : shopBean.getItems()) {
			System.out.println("Found " + i.getItemID());
			if(Integer.toString(i.getItemID()).equals(itemID))
				item = i;
		}
	}
	
	public Item getItem(){
		return item;
	}
	
	public void setItem (Item s){
		item = s;
		System.out.println("Set Item");
	}
}
