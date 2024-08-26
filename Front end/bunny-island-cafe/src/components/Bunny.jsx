import React from 'react';
import './Bunny.css';

function Bunny() {
  return (
    <div className='bunnyShowcase'>
      <nav className="navigation navbar navbar-expand-lg navbar-light">
        <img
          className="navbar-brand"
          src="src/assets/index/images/bunny.png"
          alt="Bunny icon by Dooder from Flaticon"
        />
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <a className="nav-link" href="index.html">
                Home
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="about_us.html">
                About us
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="menu.html">
                Menu
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link active" href="bunny.html">
                Bunny <span className="sr-only">(current)</span>
              </a>
            </li>
          </ul>
        </div>
      </nav>

      <main>
        <div id="main_content" className="row">
          <div id="filter_menu" className="col-4">
            <div className="checkbox_group">
              <h3>Popular Breed</h3>
              <div className="checkbox_items">
                <input name="Breed" value="Holland Lop" id="Holland Lop" type="checkbox" />
                <label htmlFor="Holland Lop">Holland Lop</label>
              </div>
              <div className="checkbox_items">
                <input name="Breed" value="Netherland Dwarf" id="Netherland Dwarf" type="checkbox" />
                <label htmlFor="Netherland Dwarf">Netherland Dwarf</label>
              </div>
              <div className="checkbox_items">
                <input name="Breed" value="Lionhead" id="Lionhead" type="checkbox" />
                <label htmlFor="Lionhead">Lionhead</label>
              </div>
              <div className="checkbox_items">
                <input name="Breed" value="Dutch" id="Dutch" type="checkbox" />
                <label htmlFor="Dutch">Dutch</label>
              </div>
              <div className="checkbox_items">
                <input name="Breed" value="Flemish Giant" id="Flemish Giant" type="checkbox" />
                <label htmlFor="Flemish Giant">Flemish Giant</label>
              </div>
              <div className="checkbox_items">
                <input name="Breed" value="Mini Lop" id="Mini Lop" type="checkbox" />
                <label htmlFor="Mini Lop">Mini Lop</label>
              </div>
            </div>
          </div>
          <div id="bunny_showcase" className="col-8">
            <div className="filter_bar">
              <div className="search">
                <input className="value" placeholder="Search" type="text" />
                <img className="icon" src="" alt="Search icon" />
              </div>
              <div className="tag_toggle_group"></div>
            </div>
            <div className="showcase_grid">
              <div className="showcase_card">
                <img src="" alt="Bunny" />
                <div className="card_body">
                  <p className="name">Bunny</p>
                  <p className="price">$0</p>
                </div>
              </div>
              <div className="showcase_card">
                <img src="" alt="Bunny" />
                <div className="card_body">
                  <p className="name">Bunny</p>
                  <p className="price">$0</p>
                </div>
              </div>
              <div className="showcase_card">
                <img src="" alt="Bunny" />
                <div className="card_body">
                  <p className="name">Bunny</p>
                  <p className="price">$0</p>
                </div>
              </div>
              <div className="showcase_card">
                <img src="" alt="Bunny" />
                <div className="card_body">
                  <p className="name">Bunny</p>
                  <p className="price">$0</p>
                </div>
              </div>
              <div className="showcase_card">
                <img src="" alt="Bunny" />
                <div className="card_body">
                  <p className="name">Bunny</p>
                  <p className="price">$0</p>
                </div>
              </div>
              <div className="showcase_card">
                <img src="" alt="Bunny" />
                <div className="card_body">
                  <p className="name">Bunny</p>
                  <p className="price">$0</p>
                </div>
              </div>
              <div className="showcase_card">
                <img src="" alt="Bunny" />
                <div className="card_body">
                  <p className="name">Bunny</p>
                  <p className="price">$0</p>
                </div>
              </div>
              <div className="showcase_card">
                <img src="" alt="Bunny" />
                <div className="card_body">
                  <p className="name">Bunny</p>
                  <p className="price">$0</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>

      <div className='footer'>
        <div className="FooterBox row">
          <div className="left col-6">
            <h2>Contact us</h2>
            <ul className="contact-list">
              <li className="item">Phone number: 093-827-232</li>
              <li className="item">
                Facebook: <a href="#">Facebook link</a>
              </li>
              <li className="item">
                Twitter: <a href="#">Twitter link</a>
              </li>
            </ul>
          </div>
          <div className="right col-6">
            <h2>Address</h2>
            <p>63726-1B, Apple Street, Delta District</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Bunny;
