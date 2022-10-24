import React from 'react'
import styles from "./auth.module.scss"
import loginImg from "../../assets/login.png"
import { Link } from 'react-router-dom'
import Card from '../../components/card/Card'

const Login = () => {
    return <section className={`container ${styles.auth}`}>
        <div className={styles.img}>
            <img src={loginImg} alt='' width="400">
            </img>
        </div>

        <Card>
            <div className={styles.form}>
                <h2 className='--color-orange'>Login</h2>
                <form>
                    <input type="text" placeholder='Username' required />
                    <input type="password" placeholder='Password' required />
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
}

export default Login