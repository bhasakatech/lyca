import React, { useState }  from 'react';
import './Navigation.css';
import { Link } from 'react-router-dom';

const Navigation = (props) => {
    const [menuOpen, setMenuOpen] = useState(false);
    const navItems = props.items || [];
    return (
        <nav className="spa-nav">
            <div className="nav-left">
                {props.navLogo && (
                    <img className="logo" src={props.navLogo} alt="logo" />
                )}
                <div className="hamburger" onClick={() => setMenuOpen(!menuOpen)}>
                    ☰
                </div>
                <ul className={`nav-list ${menuOpen ? 'active' : ''}`}>
                    {navItems.map((item, index) => (
                        <li key={index}>
                            <Link to={item.link.url}  className={item.active ? 'active-link' : ''}>
                                {item.title}
                            </Link>
                        </li>
                    ))}
                </ul>
            </div>
            {props.buttonLabel && (
                <button className="quick-btn">
                   <Link to={props.buttonLink} className="nav-btn">
                     {props.buttonLabel}
                   </Link>
               </button>
            )}
        </nav>
    );
}
export default Navigation;