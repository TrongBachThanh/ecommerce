import { React } from 'react'
import { FaShoppingCart } from 'react-icons/fa'
import {
    Container, FormControl, Navbar,
    Nav, Dropdown, Button,
} from 'react-bootstrap'
import { Link, useNavigate, useLocation } from 'react-router-dom'
import Cookies from 'universal-cookie'
import "./style.css"


export default function Header() {

    let navigate = useNavigate()
    const location = useLocation();
    const path = location.pathname
    const cookies = new Cookies();


    const logout = async (e) => {

        cookies.remove('username', { path: '/' })
        cookies.remove('fullName', { path: '/' })
        cookies.remove('role', { path: '/' })
        cookies.remove('token', { path: '/' })
        navigate("/")
        window.location.reload()
    }
    return (
        <Navbar bg="dark" variant="dark" style={{ height: 80 }}>


            {
                cookies.get('role') !== "ROLE_ADMIN" ? (
                    <Container>
                        <Navbar.Brand className='shopName'>
                            <Link to="/" className='logo'>Ecommerce</Link>
                        </Navbar.Brand>

                        <Navbar.Text className='search'>
                            <FormControl
                                style={{ width: 600 }}
                                placeholder='Search products'
                                className='m-auto' />
                        </Navbar.Text>

                        <Nav>
                            <Link to='/cart'>
                                <Button variant='success'>
                                    <FaShoppingCart color="white" fontSize="25px" /> Cart
                                </Button>
                            </Link>

                            {cookies.get('username') == null ? (
                                <Button style={{ marginLeft: 10 }} >
                                    <Link to="/login" className='login'>Login</Link>
                                </Button>
                            ) : (
                                <Dropdown style={{ marginLeft: 10 }}>
                                    <Dropdown.Toggle>
                                        {cookies.get('name')}
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <a className="dropdown-item" href='/customer'>Info</a>
                                        <a className="dropdown-item" href='/customer/order'>Orders</a>
                                        <Dropdown.Divider />
                                        <a className="dropdown-item" onClick={logout}>Log Out</a>
                                    </Dropdown.Menu>
                                </Dropdown>
                            )}

                        </Nav>

                    </Container>
                ) : (
                    <Container>
                        <Navbar.Brand className='shopName'>
                            <Link to="/">Ecommerce Admin</Link>
                        </Navbar.Brand>
                        <Nav>
                            <Link to="/manager">
                                <Button>Manager</Button>
                            </Link>
                            <Dropdown style={{ marginLeft: 10 }}>
                                <Dropdown.Toggle>
                                    {cookies.get('name')}
                                </Dropdown.Toggle>
                                <Dropdown.Menu>
                                    <a className="dropdown-item" onClick={logout}>Log Out</a>
                                </Dropdown.Menu>
                            </Dropdown>
                        </Nav>
                    </Container>
                )
            }
        </Navbar>

    )
};
