import React from "react";
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useThemeHook } from './GlobalComponents/ThemeProvider';
import Header from './components/Header';
import { Router } from "@reach/router";

//Pages
import Home from './Pages/Home';
import Cart from './Pages/Cart';
import ProductDetails from "./Pages/ProductDetails";
import SignIn from "./Pages/SignIn";
import Register from "./Pages/Register";
import MyAccount from "./Pages/MyAccount";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  const [theme] = useThemeHook();
  return (
    <BrowserRouter>

      <main className={theme ? 'bg-black' : 'bg-light-2'} style={{ height: '100vh', overflowY: 'auto' }}>
        <Header />
        <Routes>
          <Route path='/' element={<Home />} exact />
          <Route path='/my-account' element={<MyAccount />} exact />
          <Route path='/sign-in' element={<SignIn />} exact />
          <Route path='/register' element={<Register />} exact />
          <Route path='product-details/:productId' element={<ProductDetails />} exact />
          <Route path='/cart' element={<Cart />} exact />
        </Routes>
      </main>
    </BrowserRouter>

  );
}

export default App;
