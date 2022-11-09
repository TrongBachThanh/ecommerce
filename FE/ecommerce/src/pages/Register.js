import React, { useState } from 'react';
import { Container, Row, Col, Button, Form, Spinner, InputGroup } from 'react-bootstrap';
import { useThemeHook } from '../GlobalComponents/ThemeProvider';
import PhoneInput from 'react-phone-input-2';
import 'react-phone-input-2/lib/high-res.css';
import axios from 'axios'
import { useNavigate } from 'react-router-dom';


const Register = () => {
    const [loading, setLoading] = useState(false);
    const [phone, setPhone] = useState(null);
    const [theme] = useThemeHook();
    const navigate = useNavigate();


    const handleSubmit = async (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        const username = form.username.value;
        const password = form.password.value;
        const confirmPassword = form.confirmPassword.value;

        const firstName = form.firstName.value;
        const lastName = form.lastName.value;
        const email = form.email.value;

        if (password !== confirmPassword) {
            alert('Confirm Password is not match!');
        }

        if (username && password && firstName && lastName && email && phone) {
            setLoading(true);
            await axios.post("http://localhost:8080/api/v1/register", { username, password, firstName, lastName, email, phone, confirmPassword })
                .then((res) => {
                    alert('Login successfully');
                    navigate('/sign-in');
                    window.location.reload()
                })
                .catch((error) => {
                    alert(error)
                })
                .finally(() => {
                    setLoading(false);
                })
        }
    }
    return (
        <Container className="py-5 mt-5">
            <Row className="justify-content-center mt-5">
                <Col xs={11} sm={10} md={8} lg={6} className={`p-4 rounded ${theme ? 'text-light bg-dark' : 'text-black bg-light'}`}>
                    <h1 className={`text-center border-bottom pb-3 ${theme ? 'text-dark-primary' : 'text-light-primary'}`}>
                        Create Account
                    </h1>
                    <Form onSubmit={handleSubmit}>
                        <Row>
                            <Form.Group className="mb-3 col-lg-6">
                                <Form.Label>First name</Form.Label>
                                <Form.Control name="firstName" type="text" placeholder="First name" required />
                            </Form.Group>
                            <Form.Group className="mb-3 col-lg-6">
                                <Form.Label>Last name</Form.Label>
                                <Form.Control name="lastName" type="text" placeholder="Last name" required />
                            </Form.Group>
                        </Row>
                        <Form.Group className="mb-3">
                            <Form.Label>Email</Form.Label>
                            <Form.Control name="email" type="email" placeholder="Email" required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Username</Form.Label>
                            <Form.Control name="username" type="text" placeholder="Username" minLength={3} required />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Mobile number</Form.Label>
                            <PhoneInput
                                country={'vn'}
                                value={phone}
                                onChange={phone => setPhone(phone)}
                                className="text-dark"
                            />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Password</Form.Label>
                            <Form.Control name="password" type="password" placeholder="Password" minLength={6} required />
                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label>Password</Form.Label>
                            <Form.Control name="confirmPassword" type="password" placeholder="Confirm Password" minLength={6} required />
                        </Form.Group>
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
                                </> : 'Continue'
                            }
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default Register;