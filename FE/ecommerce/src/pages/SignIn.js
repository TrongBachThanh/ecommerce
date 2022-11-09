import React, { useState } from 'react';
import { Container, Row, Col, Button, Form, Spinner, InputGroup } from 'react-bootstrap';
import { useThemeHook } from '../GlobalComponents/ThemeProvider';
import { Link, useNavigate } from "@reach/router";
import axios from 'axios'
import Cookies from 'universal-cookie'

//icons
import { AiOutlineUser } from 'react-icons/ai';
import { VscKey } from 'react-icons/vsc';

const SignIn = () => {
    const [loading, setLoading] = useState(false);
    const [theme] = useThemeHook();
    const navigate = useNavigate();
    const cookies = new Cookies();


    const handleSubmit = async (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        const username = form.username.value;
        const password = form.password.value;
        if (username && password) {
            setLoading(true);
            await axios.post("http://localhost:8080/api/v1/login", { username, password })
                .then((res) => {
                    cookies.set('username', res.data.username, { path: '/' })
                    cookies.set('fullName', res.data.fullName, { path: '/' })
                    cookies.set('role', res.data.role, { path: '/' })
                    cookies.set('token', res.data.token, { path: '/' })
                })
                .catch((error) => {
                    alert(error)
                })
                .finally(() => {
                    setLoading(false);
                    navigate('/')
                    window.location.reload()
                    alert('Login successfully');
                })


            // setLoading(true);
            // fetch('https://fakestoreapi.com/auth/login',{
            //     method: 'POST',
            //     headers: {
            //         'Content-Type': 'application/json'
            //     },
            //     body:JSON.stringify({
            //         username: username,
            //         password: password
            //     })
            // }).then(res=>res.json())
            // .then(json=>sessionStorage.setItem("token", json.token))
            // .catch(error=> console.error(error))
            // .finally(()=>{
            //     setLoading(false);
            //     navigate('/', {replace: true})
            //     alert('Login successfully');
            // })
        }
    }
    return (
        <Container className="py-5 mt-5">
            <Row className="justify-content-center mt-5">
                <Col xs={11} sm={10} md={8} lg={4} className={`p-4 rounded ${theme ? 'text-light bg-dark' : 'text-black bg-light'}`}>
                    <h1 className={`text-center border-bottom pb-3 ${theme ? 'text-dark-primary' : 'text-light-primary'}`}>
                        Sign in
                    </h1>
                    <Form onSubmit={handleSubmit}>
                        <InputGroup className="mb-4 mt-5">
                            <InputGroup.Text>
                                <AiOutlineUser size="1.8rem" />
                            </InputGroup.Text>
                            <Form.Control name="username" type="text" placeholder="Username" minLength={3} required />
                        </InputGroup>
                        <InputGroup className="mb-4">
                            <InputGroup.Text>
                                <VscKey size="1.8rem" />
                            </InputGroup.Text>
                            <Form.Control name="password" type="password" placeholder="Password" minLength={3} required />
                        </InputGroup>
                        <Button
                            type="submit"
                            className={`${theme ? 'bg-dark-primary text-black' : 'bg-light-primary'} m-auto d-block`}
                            disabled={loading}
                            style={{ border: 0 }}
                        >
                            {loading ?
                                <>
                                    <Spinner
                                        as="span"
                                        animation="grow"
                                        size="sm"
                                        role="status"
                                        aria-hidden="true"
                                    />
                                    &nbsp;Loading...
                                </> : 'Sign in'
                            }
                        </Button>
                        <Form.Group className="mt-3 text-center">
                            <Form.Text className="text-muted fw-bold">
                                New to E-cart?
                            </Form.Text>
                            <Row className="py-2 border-bottom mb-3" />
                            <Link to='/register' className="btn btn-info rounded-0">
                                Create your E-cart account
                            </Link>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default SignIn;