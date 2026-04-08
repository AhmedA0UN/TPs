import { Link } from "react-router-dom";

function Footer() {
  return (
    <footer style={{ padding: "1rem", backgroundColor: "#f4f4f4", textAlign: "center" }}>
      <nav>
        <Link to="/" style={{ margin: "0 1rem" }}>Accueil</Link>
        <Link to="/about" style={{ margin: "0 1rem" }}>À propos</Link>
        <Link to="/contact" style={{ margin: "0 1rem" }}>Contact</Link>
      </nav>
      <p style={{ marginTop: "1rem", fontSize: "0.9rem" }}>
        © {new Date().getFullYear()} MonSite. Tous droits réservés.
      </p>
    </footer>
  );
}

export default Footer;