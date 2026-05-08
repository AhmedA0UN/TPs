import { useState } from 'react';
import './notes.css';

function Notes() {
  const [notes, setNotes] = useState([]);
  const inputRef = useState(null);

  const [text, setText] = useState("");
  const Lmax = 1000;
  const msg = Lmax - text.length;

  const addNote = () => {
    if (inputRef.current.value.trim() !== "") {
        const text = inputRef.current.value.trim();
        const newNote = { complete: false, text };
        setNotes([...notes, newNote]);
        setText("");
        inputRef.current.value = "";
    }
    else {
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
  }

  const modifier = (index) => {
    const newNotes = [...notes];
    const newText = prompt("Modifier la note:", newNotes[index].text);
    if (newText !== null) {
      newNotes[index].text = newText;
      setNotes(newNotes);
    }
  };



  return (
    <div className="Notestyle">
      <h2>Voici vos notes</h2>
      <div className="note-container">
        <ul>
            {notes.map(({text, complete}, index) => (
                <div className='item'>
                    <li key={index} className={complete ? "complete" : ""} onClick={() => done(index)}> {text} </li>
                    <span onClick={() => supprimer(index)} className="x">❌</span>
                    <span onClick={() => modifier(index)} className='x'>💱</span>
                </div>
            ))}
        </ul>
        <input type="text" placeholder="Ajouter un Titre"/>

        <div className="note-container">
          <textarea id="additional-info" maxLength={Lmax} required value={text} ref={inputRef}
            placeholder="Ajouter une note... ({Lmax} caractères max)"
            onChange={(e) => setText(e.target.value)}
          />
          <div className="char-count" style={{ color: msg < 50 ? "#e74c3c" : msg < 100 ? "#f39c12" : "#666", }} >
            <span>{msg}</span> <span className='nrml'>caractères restants</span>
          </div>
        </div>

        <div className='prior'>
            <label for="slect">Priorité: </label>
            <select>
              <option value="important">Important</option>
              <option value="urgent">Urgent</option>
              <option value="normal" selected>Normal</option>
            </select>
        </div>       
        <button className='ajt' onClick={addNote}>Ajouter</button>
      </div>
    </div>
  );
}

export default Notes;
