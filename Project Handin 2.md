# Project Handin 2 #
## Working with XML ##
The second handin will help you become familiar with XML and XML manipulation in Java as well as with basic network programming. This handin has three tasks:

- To write XML based on a schema description.
- To change a JDOM XML representation.
- To make a small program that can send data to the cloud.

### Assignment ###
All the information of your web shop is stored on the dWebTek web shop cloud. Your task for this handin is to construct data and a small command line program so that you can send data to this cloud.

First, create 10 products that follow the schema description of web shop items (called item in the schema file). Since you do not have any cloud server item IDs for these items, simply fill the itemId element with your favorite number. Then fill in the missing schema description of the modifyItem and itemDescription elements that used are by request messages for changing items on the cloud server.

After creating the XML product descriptions, write a small program using JDOM. The program must be able to accept an XML file on the command line. The XML file must contain a single product (root element: *item*). Storing this data on the cloud server requires two requests (see the guide page for some hints in HTTP requests in Java):

1. First, add this product to your list of products on the cloud using the createItem URL.
2. Afterwards, call the modifyItem to fill in the rest of the data from your XML file. For this you need the *itemID* from the response of the first request.

Make sure to read the *itemID* of the response from the cloud and print it back to the user of your program so that he can see whether the operation went well.

Once your program works, add all 10 products to the cloud server.

Hand in the JDOM command line program.

*Note: Remember to construct your XML using JDOM. Treating XML as strings and generating XML data for the cloud using string concatenation is not an acceptable solution to this handin.*

### Grading of the handin ###
The handin will be graded based on the following criteria:

- Whether the schema description has been completed according to the handin description.
- Whether the hand-generated XML is well-formed and valid according the schema description.
- Whether the JDOM program can correctly generate XML, that is whether the output is valid.
- Whether the JDOM program can communicate correctly with the cloud server.
- Whether at least 10 products with the same theme has been added to the cloud (Hint: Use listItems to verify this).

Up to 20 points are awarded for this handin.