import { React, useState } from 'react'
import axios from 'axios'
import { useNavigate, Link } from 'react-router-dom'
import { Button } from 'react-bootstrap'

export default function Register() {

    let navigate = useNavigate()
    const [user, setUser] = useState({
        username: "",
        fullname: "",
        phone: "",
        email: "",
        address: "",
        password: "",
        confirmPassword: ""
    })
    const { username, fullname, phone, email, address, password, confirmPassword } = user;

    const onInputChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value })
    }

    const handleRegister = async (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            alert("Confirm password is not match!");
        }
        await axios.post("http://localhost:8080/api/v1/register", user)
            .then((res) => {
                navigate('/')
                window.location.reload()
            })
            .catch((error) => {
                alert(error)
            })
    };

    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-2 border rounded p-4 mt-2'>
                    <h2 className='text-center m-4'>Login</h2>
                    <form onSubmit={(e) => handleRegister(e)}>
                        <div className='mb-3'>
                            <label htmlFor="username" className='form-label'>
                                Username
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                name="username"
                                value={username}
                                onChange={(e) => onInputChange(e)} />
                        </div>

                        <div className='mb-3'>
                            <label htmlFor="fullname" className='form-label'>
                                FullName
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                name="fullname"
                                value={fullname}
                                onChange={(e) => onInputChange(e)} />

                        </div>

                        <div className='mb-3'>
                            <label htmlFor="phone" className='form-label'>
                                Phone Number
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                name="phone"
                                value={phone}
                                onChange={(e) => onInputChange(e)} />
                        </div>

                        <div className='mb-3'>
                            <label htmlFor="address" className='form-label'>
                                Address
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                name="address"
                                value={address}
                                onChange={(e) => onInputChange(e)} />
                        </div>

                        <div className='mb-3'>
                            <label htmlFor="email" className='form-label'>
                                Email
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                name="email"
                                value={email}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>

                        <div className='mb-3'>
                            <label htmlFor="password" className='form-label'>
                                Password
                            </label>
                            <input
                                type={"password"}
                                className="form-control"
                                name="password"
                                value={password}
                                onChange={(e) => onInputChange(e)} />

                        </div>
                        <div className='mb-3'>
                            <label htmlFor="confirmPassword" className='form-label'>
                                Confirm Password
                            </label>
                            <input
                                type={"password"}
                                className="form-control"
                                name="confirmPassword"
                                value={confirmPassword}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>

                        <div className='d-flex justify-content-center'>
                            <Button type="submit">Register</Button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}
