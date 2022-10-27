import styles from "./auth.module.scss";
import registerImg from "../../assets/register.png";
import Card from "../../components/card/Card";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react"
import Loader from "../../components/loader/Loader";


const Register = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [cPassword, setCPassword] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    const navigate = useNavigate();

    const loginUser = (e) => {
        e.preventDefault();

        if (password !== cPassword) {
            alert("Password does not match!")
        }
        setIsLoading(true);
        setTimeout(() => {
            // Handle login
            setIsLoading(false);
            navigate("/login");
        }, 2000)

    }

    return (
        <>
            {isLoading && <Loader />}
            <section className={`container ${styles.auth}`}>
                <Card>
                    <div className={styles.form}>
                        <h2>Register</h2>

                        <form onSubmit={loginUser}>
                            <input type="text" placeholder="Username" required value={username}
                                onChange={(e) => setUsername(e.target.value)} />

                            <input type="password" placeholder="Password" required value={password}
                                onChange={(e) => setPassword(e.target.value)} />

                            <input type="password" placeholder="Confirm Password" required value={cPassword}
                                onChange={(e) => setCPassword(e.target.value)} />

                            <button type="submit" className="--btn --btn-primary --btn-block">
                                Register
                            </button>
                        </form>

                        <span className={styles.register}>
                            <p>Already an account?</p>
                            <Link to="/login"> Login</Link>
                        </span>
                    </div>
                </Card>
                <div className={styles.img}>
                    <img src={registerImg} alt="Register" width="400" />
                </div>
            </section>
        </>
    );
};

export default Register;