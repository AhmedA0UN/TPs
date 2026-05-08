import { useState } from "react"
import { useNavigate } from "react-router-dom"
import "./hi.css"

function Register() {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("") 
  const [email, setEmail] = useState("")  
  const [id, setId] = useState(1)
  const [users, setUsers] = useState([])

  const navigate = useNavigate()

  const annul = () => {
    navigate("/login")
  }

  const add_user = (e) => {
    e.preventDefault() // empêche le rechargement
    setUsers([...users, { id, username, password, email }])
    setId(id + 1)

    setTimeout(() => {
      navigate("/login")
    }, 1000)
  }

  return (
    <div className="Register Auth">
      <h2>Inscription</h2>
      <form onSubmit={add_user}>
        <input 
          type="text" 
          placeholder="full username" 
          required 
          pattern="[A-Za-z]+"
          onChange={(e) => setUsername(e.target.value)} 
        />
        <input 
          type="password" 
          placeholder="password" 
          required 
          minLength={8}
          onChange={(e) => setPassword(e.target.value)} 
        />
        <input 
          type="email" 
          placeholder="adresse Email" 
          required 
          onChange={(e) => setEmail(e.target.value)} 
        />

        <button className="btn" type="submit">S'inscrire</button>
        <button className="btn annuler" type="button" onClick={annul}>Annuler</button>
      </form>
    </div>
  )
}

export default Register
