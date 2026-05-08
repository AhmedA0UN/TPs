import { useNavigate } from "react-router-dom"

function Logout() {
  const navigate = useNavigate()
  const logout = () => {
    localStorage.removeItem("token")
    navigate("/login")
  }

  return (
    <div className="Logout">
      <button onClick={logout}>Se déconnecter</button>
    </div>
  )
}

export default Logout
