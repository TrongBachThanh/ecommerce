import React, { useContext, useState, useEffect } from 'react';
import { Container, Navbar, Nav, Button } from 'react-bootstrap';
import { ThemeContext } from '../GlobalComponents/ThemeProvider';
import { BiSun, BiMoon, BiCart } from 'react-icons/bi';
import { VscAccount } from 'react-icons/vsc';
import { useCart } from "react-use-cart";
import Cookies from 'universal-cookie'
import { Link, useNavigate } from 'react-router-dom';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';

const Header = () => {
  const { theme, setThemeMode } = useContext(ThemeContext);
  const [darkMode, setDarkMode] = useState(theme);
  let navigate = useNavigate()

  const cookies = new Cookies();

  useEffect(() => {
    setThemeMode(darkMode);
  }, [darkMode]);

  const {
    isEmpty,
    totalItems,
  } = useCart();

  const handleSignOut = () => {
    cookies.remove('username', { path: '/' })
    cookies.remove('fullName', { path: '/' })
    cookies.remove('role', { path: '/' })
    cookies.remove('token', { path: '/' })
    navigate("/")
    window.location.reload()
  }

  return (
    <Navbar collapseOnSelect expand="md"
      variant={darkMode ? 'dark' : 'light'}
      className={darkMode ? 'bg-light-black border-bottom' : 'bg-light border-bottom'}
      style={{ width: '100%', position: 'fixed', zIndex: 100 }}
    >
      {
        cookies.get('role') !== "ROLE_ADMIN" ? (
          <Container>
            <Link to="/">
              <Navbar.Brand className={darkMode ? 'text-dark-primary' : 'text-light-primary'} >
                <b>Simple-ecart</b>
              </Navbar.Brand>
            </Link>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <DropdownButton id="dropdown-basic-button" title="Categories">
                <Dropdown.Item>
                  <Link to="/categories/phone">
                    Phone
                  </Link>
                </Dropdown.Item>

                <Dropdown.Item to="">
                  <Link to="/categories/tablet">
                    Ipad
                  </Link>
                </Dropdown.Item>

                <Dropdown.Item>
                  <Link to="/categories/laptop">
                    Laptop
                  </Link>

                </Dropdown.Item>
              </DropdownButton>
              <Nav className="ms-auto">
                {
                  cookies.get('role') === "ROLE_USER" ? (
                    <Button className={`text-white nav-link ${darkMode ? 'text-dark-primary' : 'text-light-primary'}`}
                      onClick={alert('Hello sign out')}>
                      Sign out
                    </Button>

                  ) :
                    (
                      <Link to="sign-in" className={`nav-link ${darkMode ? 'text-dark-primary' : 'text-light-primary'}`}>
                        Sign in
                      </Link>
                    )
                }
                <Nav.Link
                  className={darkMode ? 'text-dark-primary' : 'text-light-primary'}
                  onClick={() => setDarkMode(!darkMode)}
                >
                  {darkMode ? <BiSun size="1.7rem" /> : <BiMoon size="1.7rem" />}
                </Nav.Link>
                <Link
                  to="/cart"
                  className={`${darkMode ? 'text-dark-primary' : 'text-light-primary'} d-flex align-items-center`}
                >
                  <BiCart size="2rem" />
                  {!isEmpty && <span style={{ position: 'relative', left: '-21px', top: '-18px' }}>{totalItems}</span>}
                  <span style={{ marginLeft: !isEmpty ? '-13px' : 0 }}>&nbsp;Cart</span>
                </Link>
                {
                  cookies.get('role') === "ROLE_USER" ?
                    (<Link to="my-account" className={`nav-link ${darkMode ? 'text-dark-primary' : 'text-light-primary'}`}>
                      <VscAccount size="1.8rem" />
                      &nbsp;My Account
                    </Link>) :
                    (
                      <>
                      </>
                    )
                }
              </Nav>
            </Navbar.Collapse>
          </Container>
        ) :
          (
            <Button className={`text-white nav-link ${darkMode ? 'text-dark-primary' : 'text-light-primary'}`}
              onClick={handleSignOut}>
              Sign out
            </Button>
          )
      }

    </Navbar>
  );
};

export default Header;