import { useState } from 'react';
import Navbar from '../pages/navbar.js';
import Footer from '../pages/footer';
import './notes.css';

function Notes() {
  const [notes, setNotes] = useState([]);
  const [text, setText] = useState("");
  const [tit, setTit] = useState("");
  const [priority, setPriority] = useState("basse");
  const titmax = 100;
  const Lmax = 1000;
  const msg = Lmax - text.length;

  const addNote = () => {
    if (text.trim() !== "") {
      const newNote = {
        complete: false,
        text: text.trim(),
        title: tit,
        priority,
        t: new Date()
      };
      setNotes([...notes, newNote]);
      setText("");
      setTit("");
      setPriority("basse");
    } else {
      alert("Veuillez entrer une note avant d'ajouter.");
    }
  };

  const done = (index) => {
    const newNotes = [...notes];
    newNotes[index].complete = !newNotes[index].complete;
    setNotes(newNotes);
  };

  const supprimer = (index) => {
    const newNotes = [...notes];
    newNotes.splice(index, 1);
    setNotes(newNotes);
  };

  const modifier = (index) => {
    const newNotes = [...notes];
    const newTit = prompt("Modifier le titre:", newNotes[index].title);
    const newText = prompt("Modifier la note:", newNotes[index].text);
      if (newTit !== null && newText !== null) {
        newNotes[index].title = newTit;
        newNotes[index].text = newText;
      setNotes(newNotes);
    }
  };

  const priColeur = (priority) => {
    switch (priority) {
      case "haute": return "🟥";
      case "moyenne": return "🟨";
      case "basse": return "🟩";
      default: return "⬜";
    }
  };

const formatDate = (date) => {
  const optionsDate = { day: "2-digit", month: "2-digit", year: "numeric" };
  const optionsTime = { hour: "2-digit", minute: "2-digit", second: "2-digit" };

  const datePart = date.toLocaleDateString("fr-FR", optionsDate);
  const timePart = date.toLocaleTimeString("fr-FR", optionsTime);

  return `${datePart} à ${timePart}`;
};



  return (
    <div>
      <Navbar />

      <div className="Notestyle">
        <h2 className='tt'>Voici vos notes</h2>
        <div className="note-container">
          <ul>
            {notes.slice().sort((a, b) => b.t - a.t).map(({ text, complete, priority, t, title }, index) => (
              <div className='item'>
                
                <li className={complete ? "complete" : ""} >
                  <div className='affnote'>
                    <div className='afftitre'>
                      <span className='actions'>
                        <span onClick={() => supprimer(index)} className="x">❌</span>
                        <span onClick={() => modifier(index)} className='x'>💱</span>
                      </span>
                      <span className={complete ? "complete" : ""} onClick={() => done(index)} id='aff'>Titre : </span> <strong>{title}</strong>
                    </div>
                    <div className={complete ? "complete" : ""} onClick={() => done(index)} id='affcontent'> <span id='aff'>Contenu : </span> {text}</div>
                    <div  className='suplimentaires'>
                      <span id='afs'>Info : </span> <span className='priorits'>priorité : </span> {priColeur(priority)}
                      <span className='tim'> <span className='w'>🕐</span> écrite le {formatDate(t)}</span>
                    </div>
                  </div>
                </li>
              </div>
            ))}
          </ul>

          <input method="POST" maxLength={titmax} id='intit' value={tit} onChange={(e) => setTit(e.target.value)} type="text" placeholder="Ajouter un Titre" />
          <div method="POST" className="note-container">
            <textarea required id="additional-info" maxLength={Lmax} value={text}
              placeholder={`Ajouter une note... (${Lmax} caractères max)`} onChange={(e) => setText(e.target.value)} />
            <div className="char-count">
              <span style={{ color: msg < 150 ? "#e74c3c" : msg < 500 ? "#f39c12" : "#666" }}>
                {msg} </span> <span className='nrml'>caractères restants</span>
            </div>
          </div>

          <div className='prior'>
            <label htmlFor="select">Priorité: </label>
            <select method="POST" required id='select' name='select' value={priority} onChange={(e) => setPriority(e.target.value)} >
              <option id='r' value="haute">Haute</option>
              <option id='or' value="moyenne">Moyenne</option>
              <option selected id='v' value="basse">Basse</option>
            </select>
          </div>

          <button className='ajt' onClick={addNote}>Ajouter</button>
        </div>
      </div>

      <Footer />
    </div>
  );
}

export default Notes;
