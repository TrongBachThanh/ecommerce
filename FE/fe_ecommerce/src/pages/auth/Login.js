import React, { useState } from 'react'
import styles from "./auth.module.scss"
import loginImg from "../../assets/login.png"
import { Link, useNavigate } from 'react-router-dom'
import Card from '../../components/card/Card'
import Loader from '../../components/loader/Loader'

import axios from 'axios'
import Cookies from 'universal-cookie'
import { useDispatch } from 'react-redux'
import { addUser } from '../../redux/slice/authSlice'


const Login = () => {
    const dispach = useDispatch();


    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    const navigate = useNavigate();
    const cookies = new Cookies();

    const loginUser = async (e) => {
        e.preventDefault();
        setIsLoading(true);

        let login = { username, password };

        await axios.post("http://localhost:8080/login", login)
            .then((res) => {
                cookies.set('token', res.data.token, { path: '/' })

                const action = addUser({
                    username: res.data.username,
                    fullName: res.data.fullName,
                    role: res.data.role
                });
                dispach(action);
                setIsLoading(false);
                alert("Login success!")
                navigate("/");
            })
            .catch((err) => {
                alert("Username or password is wrong")
                setIsLoading(false);
            })
    }

    return (
        <>
            {isLoading && <Loader />}
            <section className={`container ${styles.auth}`}>
                <div className={styles.img}>
                    <img src={loginImg} alt='' width="400">
                    </img>
                </div>

                <Card>
                    <div className={styles.form}>
                        <h2 className='--color-orange'>Login</h2>
                        <form onSubmit={loginUser}>
                            <input type="text" placeholder='Username' required value={username}
                                onChange={(e) => setUsername(e.target.value)} />
                            <input type="password" placeholder='Password' required value={password}
                                onChange={(e) => setPassword(e.target.value)} />
                            <button className='--btn --btn-primary --btn-block'>Login</button>
                        </form>
                        <span className={styles.register}>
                            <p>Don't have an account?
                                <Link to="/register"> Register </Link>
                            </p>
                        </span>
                    </div>
                </Card>
            </section>
        </>
    );
}

export default Login