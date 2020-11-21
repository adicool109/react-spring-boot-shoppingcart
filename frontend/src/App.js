import React, {Component} from 'react';
import { useState } from 'react';
import {useEffect} from 'react';
import {BrowserRouter, Switch, Route, BrowserRouter as Router, Redirect, useHistory} from 'react-router-dom';
import './App.css';
import Header from './components/Header';
import ProductList from './components/ProductList';
import Cart from './components/Cart'
import Login from './components/Login';
//import Navbar from './components/Navbar';

//class App extends Component {
const App = () => {
  const [user, setUser] = useState('');
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [loginError, setLoginError] = useState('');
  var isNav = true;
  const history = useHistory();

  const handleLogin = () => {
    console.log("inside handleLogin");
    // call rest api
    fetch('/login', {
      credentials: 'same-origin',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: userName,
        password: password
     })
    }).then((response) => {
      // response.json();
      if(response.statusText == "Unauthorized") {
        setLoginError(response.statusText);
      } else {
        
        console.log("userName = ", userName);
        setUser(userName);
        isNav = response.ok;
        //history.push("/cart");
        //navigator("/cart");
       
        console.log("user data from svc = ", user);
        console.log("isNav = " , isNav);
      }
    })
    .then((json) => {
      // console.log("inside after add to cart odata call", json);
    })
    .catch((error) => {
      console.error(error);
      setLoginError(error);
    });

    // if(isNav) {
    //   return <Redirect to="/cart" />
    // }
  }

  // useEffect(() => {
  //   handleLogin();
  // }, []);
  
  return (
    <Router>
      <div className="App">
        {isNav ? (
          <div>
            
            <Header cartLength={0}/>
            <br />
            
            <Switch>
              
              <Route path="/" exact component={ProductList} />
              <Route path="/login" component={Login} />
              <Route path="/cart" component={Cart} />
            </Switch>
          </div>
        ) : (
          <Login 
            userName={userName}  
            setUserName={setUserName}
            password={password}
            setPassword={setPassword}
            loginError={loginError}
            setLoginError={setLoginError}
            handleLogin={handleLogin}
          />
        )}
        
      </div>
    </Router>
  );

}

export default App;