import { useNavigate } from "react-router-dom"
import { useEffect } from "react"
import "../styles/welcome.css"

function Welcome() {
  const navigate = useNavigate()

  useEffect(() => {
    const timer = setTimeout(() => {
      navigate("/notes")
    }, 3000)

    // Nettoyage du timer si le composant est démonté
    return () => clearTimeout(timer)
  }, [navigate])

  return (
    <div className="welcome">
      <h2>Bienvenue dans votre espace personnel de gestion de notes</h2>
      <h1>😊😊</h1>
    </div>
  )
}

export default Welcome
