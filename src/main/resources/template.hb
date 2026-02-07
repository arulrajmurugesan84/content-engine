{
  "createRecord": {
    "product": {
      "name": "{{productName}}",
      "price": "{{productPrice}}",
      "discounted": "{{discountedPrice}}"
    },
    "supplier": {
      "name": "{{supplierName}}"
    },
    "address": {
      "line1": "{{addressLine1}}"
    },
    "stores": [
      {{#each stores}}
      {
        "city": "{{this.city}}",
        "zip": "{{default this.zip '00000'}}"
      }{{#unless @last}},{{/unless}}
      {{/each}}
    ]
  }
}
