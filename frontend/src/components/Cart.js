import React, { Component } from 'react';
import CartItem from './CartItem';

class Cart extends Component {
    state = {
        isLoading: true,
        orderProducts: []
    };

    // call getAllOrder api
    async componentDidMount() {
        const response = await fetch('/cart/orders');
        const body = await response.json();        
        this.setState({ orderProducts: body, isLoading: false });
    }

    emptyCart() {
        fetch('/cart/orders/deleteAll', {
          credentials: 'same-origin',
          method: 'DELETE'
        });
        //window.location.reload();
    }

    render() {
        const temp = this.state;
        // var itemsMapped = this.props.items.map(function (item, index) {
        //     return <CartItem item={item} key={index} />
        // });
        var totalPrice = 0.0;
        this.state.orderProducts.map(function(items, index) {
            if(items.orderProducts[0].totalPrice){
                totalPrice = totalPrice + items.orderProducts[0].totalPrice;
            }
                
        });
        
        var itemsMapped = this.state.orderProducts.map(function (item, index) {
            return <CartItem item={item} key={index} />
        });

        var empty = <div className="alert alert-info">Cart is empty</div>;
        
        return(
            
            <div className="shopping-cart">
                {/* <!-- Title --> */}
                <div className="title">
                   <h1>Shopping Bag</h1> <br />
                </div>
                <table className="table">
                    <thead>
                        <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Price (per unit)</th>
                        <th scope="col">Quantity</th>
                        <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>

                        {itemsMapped.length > 0 ? itemsMapped : ''}

                        <tr>
                            <td>
                            <h4 className="text-right">Total <strong>${totalPrice}</strong></h4>
                            </td>
                            <td></td>
                            <td>
                            <button type="button" className="btn btn-info btn-sm btn-block" onClick={this.emptyCart.bind(this)} disabled={itemsMapped.length == 0}>
                                Empty cart
                            </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}

export default Cart;