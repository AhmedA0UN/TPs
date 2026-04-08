import React from "react";
import lac from "./imgs/lac.png";
import lion from "./imgs/lion.png";
import nature from "./imgs/nature.png";
import Navbar from "./navbar";
import Footer from "./footer";
import "../styles/content.css"   

function Content() {
  const posts = [
    { id: 1, title: "Publication 1", text: "Contenu de la publication 1", Image: lion },
    { id: 2, title: "Publication 2", text: "Contenu de la publication 2", Image: lac },
    { id: 3, title: "Publication 3", text: "Contenu de la publication 3", Image: nature }
  ];

  return (
    <div className="home">
      {/* Navbar */}
      <Navbar />   

      {/* Content */}
      <div className="content">
        

        {/* Posts */}
        <div className="posts">
          {posts.map(post => (
            <div key={post.id} className="post">
              <img src={post.Image} alt={post.title} />
              <h4>{post.title}</h4>
              <p>{post.text}</p>
            </div>
          ))}
        </div>
      </div>

      {/* Footer */}
      <Footer />
    </div>
  );
}

export default Content;


