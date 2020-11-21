import React, {Component} from 'react';
import { useState } from 'react';

const Product = (props) => {
//class Product extends Component {
    //console.log(this.props);

    //addToCart(product) {
    //let textInput = React.createRef();

    const [cart, setCart] = useState([]);
    const [quantity, setQuantity] = useState([]);

    const addToCart = (selectedItem) => {
      let productOrder = {
        product: selectedItem,
        quantity : quantity
    };

    // const updateQuantity = () => {
    //   setQuantity = quantity;
    // };
      
      //const productOrders = {productOrder: productOrder};
      let productOrdersList = {productOrder};
      
      console.log(JSON.stringify(productOrdersList));
      var orderUnit = 0;
      
      // if (quantity == 0) {
      //   orderUnit = 1;
      // } 
      // else {
        orderUnit = quantity
      //}
      
      fetch('/cart/orders', {
        credentials: 'same-origin',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          productId: selectedItem.id,
          productName: selectedItem.name,
          price: selectedItem.price,
          pictureUrl: selectedItem.pictureUrl,
          quantity: orderUnit
       })
      }).then((response) => {
        // response.json();
        // setCart([...cart, selectedItem]);
      })
      .then((json) => {
        // console.log("inside after add to cart odata call"); 
        // console.log(json);
      })
      .catch((error) => {
        console.error(error);
      });
    }

    // updateQuantity() {

    // }

    //render() {
      //console.log(this.props);    http://placehold.it/200x150
      var name = props.product.name;
      var id = props.product.id;
      var price = props.product.price;
    
        return (
          <div className="column">
            <div className="col-item">
              <div className="photo">
                <img src={props.product.pictureUrl} className="img-responsive" alt="a" />
              </div>
              <div className="info">
                <div className="row">
                  <div className="price col-md-12">
                    <h5>{name}</h5>
                    <h5 className="price-text-color">${price}</h5>
                  </div>
                </div>
                <div className="separator clear-left">
                  <p className="section-qty">
                    <input className="form-control input-sm" type="number" min="1" value={quantity} onChange={(event) => setQuantity(event.target.value)} />
                    {/* <input className="form-control input-sm" type="text" /> */}
                  </p>
                  <p className="section-add">
                    <button type="button" className="btn btn-link btn-xs" onClick={()=>addToCart(props.product)}>
                    {/* <button type="button" className="btn btn-outline-success my-2 my-sm-0"> */}
                      <i className="fa fa-shopping-cart"></i><span className="hidden-sm">Add to cart</span>
                    </button>
                  </p>
                </div>
                <div className="clearfix"></div>
              </div>
            </div>
          </div>
        );
      //}
}

export default Product