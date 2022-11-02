import { React, useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from 'axios';
import "../../components/style.css";
export default function ProductDetail() {
    const { id } = useParams();
    const [product, setProduct] = useState([]);

    useEffect(() => {
        loadProduct();
    }, [])

    const loadProduct = async () => {
        const result = await axios.get(`http://localhost:8080/products/${id}`);
        setProduct(result.data);
    }

    return (
        <div className='productContainer'>
            <div className='d-flex justify-content-center col-md-6 offset-md-2 border rounded p-4 mt-2'>
                <div className='viewProductImage'>
                    <img src="/images/logo.png" width="100%" className="img-fluid" alt="Responsive" />
                </div>
                <div className=''>
                    <p> {product.name}</p>
                    <p> Price: {product.price}</p>
                    <p> {product.description}</p>
                </div>
            </div>
        </div>
    )
}
