import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home, Contact, Login, Register, ProductDetail } from "./pages";
import { Header, Footer } from "./components";


function App() {
  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path='/products/:id' element={<ProductDetail />} />

        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  );
}

export default App;
