import React, { useState } from 'react'
import styles from "./auth.module.scss"
import loginImg from "../../assets/login.png"
import { Link, useNavigate } from 'react-router-dom'
import Card from '../../components/card/Card'
import Loader from '../../components/loader/Loader'




const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    const navigate = useNavigate();

    const registerUser = (e) => {
        e.preventDefault();
        setIsLoading(true);
        setTimeout(() => {
            // Handle login
            setIsLoading(false);
            navigate("/");
        }, 2000)

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
                        <form onSubmit={registerUser}>
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