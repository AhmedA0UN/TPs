import { useState } from 'react';
import './notes.css';

function Notes() {
  const [notes, setNotes] = useState([]);
  const inputRef = useState(null);

  const addNote = () => {
    if (inputRef.current.value.trim() !== "") {
        const text = inputRef.current.value.trim();
        const newNote = { complete: false, text };
        setNotes([...notes, newNote]);
        inputRef.current.value = "";
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

  

  return (
    <div className="Notestyle">
      <h2>Voici vos notes</h2>
      <div className="note-container">
        <ul>
            {notes.map(({text, complete}, index) => (
                <div className='item'>
                    <li key={index} className={complete ? "complete" : ""} onClick={() => done(index)}> {text} </li>
                    <span onClick={() => supprimer(index)} className='x'>❌</span>
                </div>
            ))}
        </ul>
        <input type="text" ref={inputRef} placeholder="Ajouter une note..."/>
        <button className='ajt' onClick={addNote}>Ajouter</button>
      </div>
    </div>
  );
}

export default Notes;
