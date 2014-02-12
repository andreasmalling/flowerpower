# Project Handin 3 #
## Administering Products ##
In this handin you will write a simple server-based web application and communicate more with the cloud. Two tasks must be completed:

- Write a JSF application to list and edit products in the web shop.
- Generate XML and send XML to the cloud in order to store and read products.

### Assignment ###

You web shop needs an administrative interface so that you can update products on the cloud using a web interface. In this handin you will write a server-based web application and you must use JSF for the task as presented at the lecture.

To a large degree you can decide how the interface looks but the administrative interface of the web shop must support the following functionality:

- It must allow owners to create new items.
- It must present a list of items, including item descriptions rendered as (X)HTML.
- It must allow owners to update items with new pictures, descriptions and prices.
- It must allow owners to adjust the inventory of an item.
- It must support login so that only certain users can update the products. For this you will need to create customers on the cloud server and hard-code the list of usernames for administrators of your web shop.

Use JDOM to transform the Cloud XML itemDescription to an (X)HTML version that can be rendered in the browser and create a custom JSF tag to insert the value in a template. Base the communication with the cloud server on the code you wrote for handin 2. You will able to reuse some of the JDOM code for the fourth handin. Therefore, make sure to keep it in a separate class, so you can easily plug it into the next handin as well. Write JDOM code for creating customers, you will need to create the user interface for this as part of the next handin. 

Use your completed schema to ensure validity of the data sent to and from the cloud.

Compile your application and load it into your Tomcat server (from handin 1) so that others can access it. Create and hand in a zip-file containing all the source code for your application plus a (short) README explaining your TA, including;
1. How to make the application run
2. URL where the running application can be accessed.

A (minimal) example solution can be seen at http://services2.brics.dk/java/admin.

### Grading of the handin ###
The handin will be graded based on the following criteria:

- Whether the JSF application works.
- How well the application is documented.
- Whether the communication with the cloud works.
- Whether the generated HTML is valid.
- Whether the JSF application is safe against cross-site scripting.
- Whether the application runs somewhere on a university machine.

Up to 30 points are awarded for this handin