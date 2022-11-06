import axios from "axios";
import React, { useEffect, useState } from "react";
import Filter from "../../components/filter/Filter";
import "../../components/style.css";
import SingleProduct from "../../components/singleproduct/SingleProduct.js"


const Home = () => {

  // const [products, setProducts] = useState([]);

  // useEffect(() => {
  //   loadProducts();
  // }, [])

  // const loadProducts = async () => {
  //   const result = await axios.get("http://localhost:8080/products/");
  //   setProducts(result.data);
  //   console.log(result.data);
  // }


  return (
    <div className='home'>
      {/* <Filter />
      <div className='productContainer'>
        {products.map((prod) => {
          return <SingleProduct prod={prod} key={prod.id} />
        })}
      </div> */}
      Home

    </div>
  );
};

export default Home;
