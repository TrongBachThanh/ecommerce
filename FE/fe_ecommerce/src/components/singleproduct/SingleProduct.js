import { React, useEffect } from 'react'
import { Card, Button } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Cookies from 'universal-cookie';
import axios from 'axios';


export default function SigleProduct({ prod }) {
    const cookies = new Cookies()

    useEffect(() => {
        postCart();
    }, [])

    const postCart = async (e) => {
        e.preventDefault()
        axios.post(`http://localhost:8080/products/${prod.id}`, {
            'id': { 'customerId': cookies.get('customerId'), 'productId': prod.id }, 'quantity': 1
        }
            , {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + cookies.get('token'),
                    'customerId': cookies.get('customerId')
                }
            })
    }
    return (
        <div className='products'>
            <Card>
                <Card.Img variant='top' src={prod.thumnail} alt={prod.name} />
                <Card.Body>
                    <Card.Title>{prod.name}</Card.Title>
                    <div style={{ paddingBottom: 10 }}>
                        <span>{prod.price} đ</span>
                    </div>
                    <Button onClick={(e) => postCart(e)}>Add to cart</Button>
                    <Link to={'/products/' + prod.id}>
                        <Button variant='success' style={{ marginLeft: 5 }}>View product</Button>
                    </Link>

                </Card.Body>
            </Card>
        </div>
    )
}

