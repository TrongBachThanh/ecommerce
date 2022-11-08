import axios from 'axios'
import React, { useEffect, useState } from 'react'
import Product from '../components/Product';

import "./Home.css"
export default function Home() {
    const [newProducts, setNewProducts] = useState([]);
    const [featuredProducts, setFeaturedProducts] = useState([]);


    useEffect(() => {
        loadNewProducts();
    }, [])

    useEffect(() => {
        loadFeaturedProducts();
    }, [])

    const loadNewProducts = async () => {
        await axios.get("http://localhost:8080/api/v1/products/new-top-5")
            .then((res) => {
                setNewProducts(res.data);
                console.log(res)
            })
    }

    const loadFeaturedProducts = async () => {
        await axios.get("http://localhost:8080/api/v1/products/featured-top-5")
            .then((res) => {
                setFeaturedProducts(res.data);
            })
    }

    return (
        <div className='products'>
            {
                newProducts.map(product => {
                    return <Product key={product.id} product={product} />
                })


            }
        </div>
    )
}
