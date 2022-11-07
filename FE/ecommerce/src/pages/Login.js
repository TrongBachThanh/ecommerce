import { React, useState } from 'react'
import axios from 'axios'
import { useNavigate, Link } from 'react-router-dom'
import { Button } from 'react-bootstrap'
// import {useCookies} from 'react-cookie'
import Cookies from 'universal-cookie'

export default function Login() {

    let navigate = useNavigate()

    const cookies = new Cookies();


    const [login, setLogin] = useState({
        username: "",
        password: "",
    })
    const { username, password } = login

    const onInputChange = (e) => {
        setLogin({ ...login, [e.target.name]: e.target.value })
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post("http://localhost:8080/api/v1/login", login)
            .then((res) => {
                cookies.set('username', res.data.username, { path: '/' })
                cookies.set('fullName', res.data.fullName, { path: '/' })
                cookies.set('role', res.data.role, { path: '/' })
                cookies.set('token', res.data.token, { path: '/' })
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
                    <form onSubmit={(e) => onSubmit(e)}>
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
                        <div className='d-flex justify-content-center'>
                            <Button type="submit">Login</Button>
                            <Button variant="success" style={{ marginLeft: 10 }}>
                                <Link to='/register' className='text-decoration-none text-white'>Register New Accout</Link>
                            </Button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}
