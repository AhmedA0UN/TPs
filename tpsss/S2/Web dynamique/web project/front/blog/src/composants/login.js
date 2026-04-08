import { Link } from "react-router-dom"
import { useState } from "react"

function Login () {
    const [username, setusername] = useState("")
    const [password, setpassword] = useState("")   
    const [users,setusers]=useState([])

    const add=()=>{                         
        setusers([...users,{username,password}])
        console.log(users) 
    }

    return(
        <div className="Auth">
            <form onSubmit={add}>
                <h2>Login</h2>
                <input type="text" placeholder="username" onChange={(e)=>setusername(e.target.value)} />
                <input type="password" placeholder="password" onChange={(e)=>setpassword(e.target.value)}  />
                <button type="submit">connexion</button>
                <button type="reset">Annuler</button>
                <span>
                    vous n'avez pas du copmte ? <Link to ="/register"> inscrivez vous </Link> 
                </span>
            </form>
        </div>
    )
}

export default Login


