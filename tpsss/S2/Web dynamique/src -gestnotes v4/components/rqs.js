import React from "react";
import Footer from "../pages/footer";
import Navbar from "../pages/navbar";
import "../styles/rqs.css";

function Rqs() {
  return (
    <div className="rmq">

      <div>
        <Navbar />
      </div>
      
      <h2 className="tit">comment utiliser ce site</h2>
      <div>
        <ul className="list">
        <li><p>Ce site est une application de gestion de notes personnelles. Il vous permet de créer, organiser et gérer vos notes de manière efficace.</p></li>
        <li><p>Vous pouvez ajouter des titres, des descriptions et des catégories à vos notes pour les retrouver facilement.</p></li>
        <li><p>L'interface conviviale vous offre une expérience agréable pour garder vos idées et informations importantes à portée de main.</p></li>
        <li><p>Pour commencer, inscrivez-vous ou connectez-vous pour accéder à votre espace personnel où vous pourrez créer et gérer vos notes en toute simplicité.</p></li>
        <li><p>Profitez de cette application pour organiser vos pensées, vos tâches et vos projets de manière efficace !</p></li>
        <li><p>Le bouton "Ajouter" vous permet de créer une nouvelle note.</p></li>
        <li><p>Le bouton "💱" vous permet de modifier une note existante.</p></li>
        <li><p>Le bouton "❌" vous permet de supprimer une note existante.</p></li>
        <li><p>Le click sur une note vous permet de changer son état "terminée / en cours".</p></li>
        <li><p>Les notes sont triées par date de création.</p></li>
        <li><p>Les badges de couleurs "🟥🟩🟨" vous permettent de visualiser rapidement le niveau d'importance ou l'état d'une note.</p></li>
        <li><p>Merci d'utiliser notre application de gestion de notes personnelles !</p></li>
      </ul>
      <p id="fin">À bientôt sur notre application de gestion de notes personnelles !</p>
      
      </div>
      <div>
        <Footer />
      </div>
      <br />
      
    </div>
    
  );
}

export default Rqs;
