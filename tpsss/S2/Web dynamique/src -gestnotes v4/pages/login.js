import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import "../styles/hi.css";

function Login() {
  const [username, setusername] = useState("");
  const [password, setpassword] = useState("");
  const [users, setusers] = useState([]);
  const navigate = useNavigate();

  const add = (e) => {
    e.preventDefault(); 

    if (username.trim() !== "" && password.trim() !== "") {   
      setusers([...users, { username, password }]);
      console.log(users);

      navigate("/welcome");
      /*setTimeout(() => {
        navigate("/register");
      }, 1000); */

    }
    else {
      alert("Veuillez remplir tous les champs.");
    }
  };

  return (
    <div className="Auth">
      <form onSubmit={add} method="POST">
        <h2>Login</h2>
        <input
          type="text"
          pattern="[A-Za-z]+"
          placeholder="username"
          onChange={(e) => setusername(e.target.value)}
        />
        <input
          type="password"
          minLength={8}
          placeholder="password"
          onChange={(e) => setpassword(e.target.value)}
        />
        <button className="btn" type="submit">Connexion</button>
        <button className="btn annuler" type="reset">Annuler</button>
        <br />
        <span>
          Vous n'avez pas de compte ? <Link className="lnk" to="/register">Inscrivez‑vous</Link>
        </span>
      </form>
    </div>
  );
}

export default Login;
