# content-engine

Merged JSON:
{
  "product" : {
    "id" : "product_1",
    "name" : "Eye Shadow",
    "description" : "A beautiful eye shadow palette.",
    "price" : 29.99
  },
  "supplier" : {
    "name" : "Beauty Supplier Co.",
    "product_id" : "product_1",
    "price" : 24.99,
    "product_name" : "Eye Shadow"
  },
  "store" : {
    "id" : "store_1",
    "mailingAddress" : {
      "line1" : "123 Main St",
      "line2" : "Suite 500"
    },
    "physicalAddress" : {
      "line1" : "789 Market St",
      "line2" : "Floor 2"
    },
    "locations" : [ {
      "city" : "New York",
      "zip" : "10001"
    }, {
      "city" : "Chicago"
    } ]
  }
}

Rendered Output:
{
  "createRecord": {
    "product": {
      "name": "Eye Shadow",
      "price": "$29.99",
      "discounted": "0"
    },
    "supplier": {
      "name": "Beauty Supplier Co."
    },
    "address": {
      "line1": "123 Main St"
    },
    "stores": [
      
      {
        "city": "New York",
        "zip": "10001"
      },
      
      {
        "city": "Chicago",
        "zip": "00000"
      }
      
    ]
  }
}
