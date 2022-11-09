import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { useThemeHook } from '../GlobalComponents/ThemeProvider';
import Lightbox from 'react-lightbox-component';
import 'react-lightbox-component/build/css/index.css';
import './product-details.css';
import { useCart } from 'react-use-cart';
import { BsCartPlus } from 'react-icons/bs';
import { useParams } from 'react-router-dom';


const ProductDetails = () => {
    const [productData, setProductData] = useState([]);
    const [theme] = useThemeHook();
    const { addItem } = useCart();
    const params = useParams();

    useEffect(() => {
        getResponse();
    }, []);

    const getResponse = async () => {
        const res = await fetch(`http://localhost:8080/api/v1/products/${params.productId}`)
            .then(res => res.json());
        setProductData(await res);
    }
    return (
        <Container className="py-5">
            <Row className="justify-content-center mt-5">
                <Col xs={10} md={7} lg={5} className="p-0">
                    {
                        productData.images !== undefined ?
                            (
                                <Lightbox
                                    images={[
                                        {
                                            src: productData.images[0].file
                                        },
                                        {
                                            src: productData.images[1].file,

                                        }
                                    ]}
                                />
                            )
                            :
                            (
                                console.log('Hello Image')

                            )
                    }

                </Col>
                <Col xs={10} md={7} lg={7} className={`${theme ? 'text-light' : 'text-black'} product-details`}>
                    <h1>{productData.title}</h1>
                    <Button
                        onClick={() => addItem(productData)}
                        className={theme ? 'bg-dark-primary text-black' : 'bg-light-primary'}
                        style={{ borderRadius: '0', border: 0 }}
                    >
                        <BsCartPlus size="1.8rem" />
                        Add to cart
                    </Button>
                    <br />
                    <b className={`${theme ? 'text-dark-primary' : 'text-light-primary'} h4 mt-3 d-block`}>
                        Rs. {productData.price}
                    </b>
                    <br />
                    <b className="h5">4.1 ⭐</b>
                    <p className="mt-3 h5" style={{ opacity: '0.8', fontWeight: '400' }}>
                        {productData.description}
                    </p>
                </Col>
            </Row>
        </Container>
    );
};

export default ProductDetails;