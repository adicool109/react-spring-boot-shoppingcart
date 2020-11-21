import React, {Component} from 'react';
import Product from './Product';

//const ProductList = (props) => {
class ProductList extends Component {
    state = {
        isLoading: true,
        products: []
    };

    async componentDidMount() {
        const response = await fetch('/products');
        //const response = await fetch('http://localhost:8080/products');
        const body = await response.json();
        this.setState({ products: body, isLoading: false });
    }

    handleInput = (event) => {
        // this.setState({ query: event.target.value}); 
        const {products, isLoading} = this.state;
        // console.log(query);
    
        const query = event.target.value;
        const searchResult = products.filter(product => 
          product.name.toLowerCase().startsWith(query)
        );
        console.log(searchResult);
        if (query) {
          console.log("inside handleInput search");
          this.setState( {
            products: searchResult,
            query: query,
            loading: false
          } );
        //   this.setState( { query, loading: true, products: '' }, () => {
        //             this.fetchSearchResults( 1, searchResult , query);
        //         } );
        }
        
      }
    
      fetchSearchResults = ( updatedPageNo = '', result, queryParam ) => {
          console.log("inside fetchSearchResults");
          this.setState( {
            products: result,
            query: queryParam,
            loading: false
          } );
      }
    
    //   renderSearchResults = () => {
    //     const { products } = this.state;
    //     return(
    //     //   <Product products={products} />
    //         this.state.products.map(function (product, index) {
    //             return <Product product={product} key={index} />
    //         })
    //     );
    //   }

    render() {

        const {products, isLoading} = this.state;
        console.log("inside Produclist render...");
        //console.log(products);
        if (isLoading) {
          return <p>Loading...</p>;
        }

        var productsMapped = this.state.products.map(function (product, index) {
            return <Product product={product} key={index} />
          });

        return(
            <div>
                <form className="form-inline">
                    <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" 
                        // value={query}
                        onChange={this.handleInput} 
                    />
                    {/* <button class="btn btn-outline-success my-2 my-sm-0" type="submit" onClick={this.handleSearch}>Search</button> */}
                </form>
                <div className="row extra-bottom-margin"> {productsMapped} </div>
            </div>
        );
    }
}

export default ProductList