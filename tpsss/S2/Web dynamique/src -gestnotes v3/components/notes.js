import { useState } from 'react';
import './notes.css';

function Notes() {
  const [notes, setNotes] = useState([]);
  const [text, setText] = useState("");
  const [tit, setTit] = useState("");
  const [priority, setPriority] = useState("normal");
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
      setPriority("normal");
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
    const newText = prompt("Modifier la note:", newNotes[index].text);
    if (newText !== null) {
      newNotes[index].text = newText;
      setNotes(newNotes);
    }
  };

  const priColeur = (priority) => {
    switch (priority) {
      case "haute": return "🟥";
      case "moyenne": return "🟨";
      case "basse": return "🟩";
      /*default: return "⬜";*/
    }
  };

  return (
    <div className="Notestyle">
      <h2 className='tt'>Voici vos notes</h2>
      <div className="note-container">
        <ul>
          {notes.map(({ text, complete, priority, t, title }, index) => (
            <div key={index} className='item'>
              <li className={complete ? "complete" : ""} onClick={() => done(index)}>
                <ul className='affnote'>
                  <li className='afftitre'>
                    <span className='actions'>
                      <span onClick={() => supprimer(index)} className="x">❌</span>
                      <span onClick={() => modifier(index)} className='x'>💱</span>
                    </span>
                    <span id='aff'>Titre : </span> <strong>{title}</strong>
                  </li>
                  <li className='affcontent'> <span id='aff'>Contenu : </span> {text}</li>
                  <li className='suplimentaires'>
                    <span id='afs'>Info : </span> {priColeur(priority)} 
                    <span className='tim'>écrite le {t.toLocaleDateString()} à {t.toLocaleTimeString()}</span>
                  </li>
                </ul>
              </li>
            </div>
          ))}
        </ul>

        <input 
          id='additional-info-container' 
          value={tit} 
          onChange={(e) => setTit(e.target.value)} 
          type="text" 
          placeholder="Ajouter un Titre" 
        />

        <div className="note-container">
          <textarea required  id="additional-info" maxLength={Lmax} value={text}
            placeholder={`Ajouter une note... (${Lmax} caractères max)`} onChange={(e) => setText(e.target.value)} />
          <div className="char-count">
            <span style={{ color: msg < 150 ? "#e74c3c" : msg < 500 ? "#f39c12" : "#666" }}>
              {msg}
            </span> 
            <span className='nrml'>caractères restants</span>
          </div>
        </div>

        <div className='prior'>
          <label htmlFor="select">Priorité: </label>
          <select required id='select' name='select' value={priority} onChange={(e) => setPriority(e.target.value)} >
            <option id='r' value="haute">Haute</option>
            <option id='or' value="moyenne">Moyenne</option>
            <option selected id='v' value="basse">Basse</option>
          </select>
        </div>

        <button className='ajt' onClick={addNote}>Ajouter</button>
      </div>
    </div>
  );
}

export default Notes;
