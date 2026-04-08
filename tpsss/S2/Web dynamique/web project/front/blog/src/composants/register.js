import { useState } from "react"

function Register () {



    const [username, setusername] = useState("")
    const [password, setpassword] = useState("") 
    const [email, setemail] = useState("")  
    const [id, setid] = useState(1)
    const [users,setusers]=useState([])

    const add_user=()=>{                         
        setusers([...users,{id,username,password,email}])
        setid(id+1) 
    }

    return (
        <div className="Register">
            <h2>Inscription</h2>
            <form>
                
                <input type="text" placeholder="username" required   onChange={(e)=>setusername(e.target.value)} />
                <input type="password" placeholder="password" required  onChange={(e)=>setpassword(e.target.value)} />
                <input type="email" placeholder="adresse Email" required   onChange={(e)=>setemail(e.target.value)} />
                <button onClick={add_user}>s'inscrire</button>
                <button>Annuler</button>
            </form>
        </div>
    )
}

export default Register