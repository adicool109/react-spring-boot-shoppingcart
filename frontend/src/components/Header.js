import React from 'react';
//import {connect} from 'react-redux';
import {NavLink} from 'react-router-dom';
import {Link} from 'react-router-dom';
import { useState , useEffect} from 'react';
import { useCallback } from 'react';

const Header = (props) => {
        
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div className="container">
                <NavLink className="navbar-brand" to="/">Ecommerce</NavLink>
                <div>
                    <ul className="navbar-nav ml-auto">
                        <nav>
                            <NavLink className="nav-link" to={"/cart"}>Cart </NavLink>
                        </nav>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

// const mapStateToProps = (state) => {
//   return {
//       cartLength: state.shop.cart.length
//   }
// };

// export default connect(mapStateToProps, null)(Header);

export default Header;