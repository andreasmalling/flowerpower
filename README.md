# AU: WebTek (Q3, 2014) #
## PROGRAMMING PROJECT ##
The project consists of weekly handins that each form a part of a system. A perfect solution will yield the group 120 points (of which 20 points are extra credits for advanced work). The individual multiple choice exam yields up to 100 points and the individual final score is determined as a weighed average of the two scores. You will need the same set of skills for the project and for the multiple choice exam so participating in making the project also prepares you for the exam.

You will develop an online shop that is part of a larger shopping network. Customers are registered once for the entire network and can then shop in each of the associated shops.

Each shop sells its own kind own products:

- books 
- DVDs 
- clothes
- cars
- hats
- pets
- so on...

Every group must program a shop (choosing its own line of products) and connect it to the shopping network.

Central to the network is the cloud, which is s web service that registers the customers and offers a database service where each shop may keep track of its product items, its inventory and its sales records. Communication with the cloud takes place by sending and receiving XML using HTTP *POST* and *GET* requests.

A shop is identified through a shop key that is given to each project group. The key is used to configure the shop and to identify the shop in all communications about the shop administration. Keep this key secret, or others may be able to make random changes in your store data. A shop also has a shop ID which is used to identify the shop when public data is requested.

The cloud is available at the URL http://services.brics.dk/java4/cloud and it supports the following requests:

---

| TYPE  | SYNTAX	            | BODY          | RESPONSE  |
|  ---  |       --------        |   --------:   |   ----:   |
|POST	| /login	            | login	        | loginResponse |
|POST	| /sellItems	        | sellItems	    | saleResponse |
|POST	| /createItem	        | createItem    | itemID |
|POST	| /createCustomer	    | createCustomer	| createCustomerResponse |
|POST	| /modifyItem           | modifyItem	    | none |
|POST	| /adjustItemStock	    | adjustItemStock	| none |
|GET	| /listShops	        | none              | shops |
|GET	| /listItems?shopID=ID  | none	            | items |
|GET	| /listCustomers	    | none	            | customers |
|GET	| /listCustomerSales?customerID=ID	| none	| sales |
|GET	| /listShopSales?shopKey=ID	        | none	| sales |

---
The types of bodies and responses of *POST* requests and the types of reponses of *GET* requests are defined in the XML Schema [cloud.xsd](https://github.com/andreasmalling/flowerpower/blob/master/WebContent/XML/cloud.xsd).

## Locating Webshop ##
The webshop is located at http://llama09.cs.au.dk:25556/flowerpower

**OBS!** You need to be connected to *AU VPN* to visit the site.