import { Link } from "react-router-dom";
import persImg from "./imgs/pers.jpg"; 
import dglImg from "./imgs/dgl.png"
import "../styles/navbar.css"


function Navbar() {
  return (
    <nav>
      <ul>
        
          <img src={dglImg} alt="Profile" />
        
        <li>
          <h4>b7md_shop</h4>
        </li>
        <li>
          <Link to="/art">Art</Link>
        </li>
        <li>
          <Link to="/sceances">Sceances</Link>
        </li>
        <li>
          <Link to="/cinema">Cinema</Link>
        </li>
        <li>
          <Link to="/sport">Sport</Link>
        </li>
        <li>
          <img src={persImg} alt="jjh" />
        </li>
        <li>
          <Link to="/username">Username</Link>
        </li>
         <li>
          <Link to="/write">Write</Link>
        </li>
        <li>
          <Link to="/logout">Logout</Link>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;

