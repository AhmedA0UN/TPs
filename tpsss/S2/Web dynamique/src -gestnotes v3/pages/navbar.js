import { Link , useNavigate } from "react-router-dom"
import { useState } from "react"
import "../styles/navbar.css"
import Logout from "./logout";


function Navbar() {
  const navigate = useNavigate();
  const [message, setMessage] = useState("");

  const handleLogout = () => {
    setMessage("Vous êtes déconnecté !");
    setTimeout(() => {
      navigate("/login");
    }, 1500);
  };

  return (
    <nav>
      <ul>
        <li><Link to="/welcome">Accueil</Link></li>
        <li><Link to="/rqs">Remarques</Link></li>
        <li><Logout /></li>
      </ul>
      {message && <p className="logout-message">{message}</p>}
    </nav>
  );
}

export default Navbar;
