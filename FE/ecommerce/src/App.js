
import './App.css';
import { CookiesProvider } from "react-cookie";

import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import Cookies from 'universal-cookie';
import Header from './components/Header';
import Login from './pages/Login';
import Register from './pages/Register';
import Home from './pages/Home';

function App() {
  const cookies = new Cookies()

  return (
    <CookiesProvider>
      <BrowserRouter>
        <Header />
        {cookies.get('role') !== "ROLE_ADMIN" ? (
          <Routes>
            <Route path='/' element={<Home />} exact />
            <Route path='/login' element={<Login />} exact />
            <Route path='/register' element={<Register />} exact />
          </Routes>
        ) : (
          <Routes>

          </Routes>
        )}
      </BrowserRouter>
    </CookiesProvider>
  );
}

export default App;
