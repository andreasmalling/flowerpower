
import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.jdom2.JDOMException;


@ManagedBean(name="CreateBean")
@ViewScoped
public class CreateBean {
	private String itemID;
	private String itemName;
	
	@ManagedProperty("#{ShopBean}")
	transient private ShopBean shopBean;
	
	public ShopBean getShopBean(){
		return shopBean;
	}
	
	public void setShopBean(ShopBean s){
		shopBean = s;
	}
	
	public String getItemName(){
		return itemName;
	}
	
	public void setItemName(String s){
		itemName = s;
	}

	public String create(){
		try {
			new Communication().createItem(new Item(new Integer(itemID), itemName, 0, null,null,0));
			shopBean.updateItems();
			System.out.println("OK CREATE");
		} catch (Exception e) {
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
	}
}
