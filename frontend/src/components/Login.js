import React from 'react';
import './Login.css';

const Login = (props) => {

    const {userName,setUserName,password,setPassword,loginError,setLoginError,handleLogin} = props;

    return(
        <div className="login-page">
            <div className="form">
                <form className="register-form">
                    <input type="text" placeholder="name"/>
                    <input type="password" placeholder="password"/>
                    <input type="text" placeholder="email address"/>
                    <button>create</button>
                    <p className="message">Already registered? <a href="#">Sign In</a></p>
                </form>
                <form className="login-form">
                    <input type="text" placeholder="username" value={userName} onChange={(e)=>setUserName(e.target.value)} />
                    <input type="password" placeholder="password" value={password} onChange={(e)=>setPassword(e.target.value)} />
                    <p className="errorMsg">{loginError}</p>
                    <button onClick={handleLogin}>login</button>
                    
                    {/* <p className="message">Not registered? <a href="#">Create an account</a></p> */}
                </form>
            </div>
        </div>
    );
}

export default Login;