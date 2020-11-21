import React, { Component } from 'react';

class CartItem extends Component {
     deleteItem(id) {
      var cartId = id;
        fetch('/cart/orders/deleteRecord/'+cartId, {
          credentials: 'same-origin',
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
           // id: this.props.item.id
         })
        });
        //window.location.reload();
      }
    
      render() {
        var name = this.props.item.orderProducts[0].product.name;
        var id = this.props.item.id;
        var price = this.props.item.orderProducts[0].product.price;
        var quantity = this.props.item.orderProducts[0].quantity;
    
        return (
                    
            <tr>
              <td>{name}</td>
              <td>{price}</td>
              <td>{quantity}</td>
              <td>
              <button type="button" className="" onClick={this.deleteItem.bind(this, id)}>
                Remove
               </button>
              </td>
            </tr>
         
        );
      }
}

export default CartItem;