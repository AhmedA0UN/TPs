import { useNavigate } from "react-router-dom"
import { useState } from "react"
import "../styles/logout.css"

function Logout() {
  const navigate = useNavigate()
  const [message, setMessage] = useState("");
  const logout = () => {
    setMessage("Vous êtes déconnecté !");
    localStorage.removeItem("token");
    setTimeout(() => {
      navigate("/login");
    }, 1500);
  };

  return (
    <div className="Logout">
      <button onClick={logout}>Se déconnecter</button>
      {message && <p>{message}</p>}
    </div>
  )
}

export default Logout
