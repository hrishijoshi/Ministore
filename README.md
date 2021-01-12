# Ministore Project

## Technical Specification
* Java : Minimum 8
* Framework : Spring Boot 2.4
* Build tool : Gradle
* Dependency management : Gradle (Central maven repo)

## Features
* Product Management
	* Create Product: Use to register/create a product in database `POST /ministore/products`
	
		```json
		Request
		{
			"requestId": "String",
			"price": "double",
			"description": "String"
		}
		
		```
		```json
		Response
		{
			"requestId" : "String",
			"product" : {
				"id" : "String",
				"price" : "double",
				"description" : "String"
			}
		}
		```
	* Update Product: Use to update the product properties `PUT /ministore/product`
		* Update product description: If the description is left null, it is not updated
		* Update product price: If the price is left null or absent, it is not updated.
		```json
		Request
		{
			"requestId" : "String",
			"product" : {
				"id" : "String",
				"price" : "double",
				"description" : "String"
			}
		}
		```
	
		```json
		Response
		{
			"requestId" : "String",
			"product" : {
				"id" : "String",
				"price" : "double",
				"description" : "String"
			}
		}
	
		```
	* Delete Product: Use to update the product properties `DELETE /ministore/product`
		* productId is one that is returned in create product response
		* This operation Idempotent. If the product does not exist, it will still return success on deletion
		```json
		Request
		{
			"requestId" : "String",
			"productId" : "String"
		}
		Response is plain HTTP 200OK
		```
* Deal Management
	* Using this feature, one can create different deals on pre-registered/created products
	* There are two types of deals possible
		* Discount deal : This deal is created on single product. For a minimum number of items of the product purchased, the last item gets configured percentage in discount.
		```json
		Request
		{
			"@type" : "discount",
			"baseProductId" : "String",
			"requestId" : "String",
			"discountPercentage" : "integer",
			"discountSetCount": "integer"
		}
		```
		```json
		Response
		{
            "requestId": "String",
            "deal" : {
                "dealId": "String",
                "baseProductId" : "String",
                "discountPercentage": "integer",
                "discountSetCount": "integer"
            }
		}
		```
		* Bundle deal : In this, one can combine more than one products and configure the discount percentage on the second product item.
		```json
		Request
		{
			"@type" : "bundle",
			"baseProductId" : "String",
			"requestId" : "String",
			"discountPercentage" : "integer",
			"secondaryProductId": "String"
		}
		```
		```json
		Response
		{
            "requestId": "String",
            "deal" : {
                "dealId": "String",
                "baseProductId" : "String",
                "discountPercentage": "integer",
                "secondaryProductId": "integer"
            }
		}
		```
* Shopping cart management
    * Create cart `PORT /ministore/cart`
        * Shopping session starts with this API first.
        * cart.id returned in the response will be used for futher operations on the cart
    ``` json
    Request
    {
        "requestId": "String",
        "itemsCount": "integer",
        "productId": "String"
    }
    ```
    ``` json
    Response
    {
        "requestId": "String",
        "cartId": "String",
        "cart": {
            "id": "String",
            "items": {
                "productId" : [integer]
            }
        }
    }
    ```
    * Update product items count in the cart `PORT /ministore/cart`
        * The cartId is the cart.id returned in the Create Cart request
	    * itemsCount is the count of items for the product set in the cart.
	    * If itemsCount is set to 0, it will remove the product from the cart
	    * itemsCount must be a +ve integer
    ``` json
    Request
    {
        "requestId": "String",
        "cartId": "String",
        "itemsCount": "integer",
        "productId": "String"
    }
    ```
    ``` json
    Response
    {
        "requestId": "String",
        "cartId": "String",
        "cart": {
            "id": "String",
            "items": {
                "productId" : [integer]
            }
        }
    }
    ```
* Checkout `POST /ministore/checkout`
    * Used to calculate the aoverall amount of items in the shopping cart after applying all deals
    ```json
    Request
    {
        "requestId" : "String",
        "cartId" : "String"
    }
    ```
    ```json
    Response
    {
        "requestId" : "String",
        "items": [
            {
                "productId": "String",
                "price": double,
                "dealId": "String"
            }
        ],
        "amount" : double
    }
    ```