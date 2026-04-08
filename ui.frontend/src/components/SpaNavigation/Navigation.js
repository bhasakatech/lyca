import React, { useState, useEffect, useRef }  from 'react';
import './Navigation.css';
import { Link } from 'react-router-dom';

const Navigation = (props) => {
    const [menuOpen, setMenuOpen] = useState(false);
    const [isScrolled, setIsScrolled] = useState(false);
    const [navHeight, setNavHeight] = useState(0);
    const navRef = useRef(null);
    const navItems = props.items || [];

    const placeholderRef = useRef(null);

    useEffect(() => {
        if (navRef.current) {
            setNavHeight(navRef.current.offsetHeight);
        }
        
        const handleScroll = () => {
            if (placeholderRef.current) {
                const rect = placeholderRef.current.getBoundingClientRect();
                if (rect.top <= 0) {
                    setIsScrolled(true);
                } else {
                    setIsScrolled(false);
                }
            }
        };

        window.addEventListener('scroll', handleScroll);
        
        const handleResize = () => {
            if (navRef.current) setNavHeight(navRef.current.offsetHeight);
        };
        window.addEventListener('resize', handleResize);
        
        return () => {
            window.removeEventListener('scroll', handleScroll);
            window.removeEventListener('resize', handleResize);
        }
    }, []);

    // Placeholder dynamically takes up the space only when the nav becomes fixed
    const placeholderActualHeight = isScrolled ? (navHeight || 76) : 0;

    return (
        <>
            <div ref={placeholderRef} style={{ height: `${placeholderActualHeight}px` }}></div>
            <nav ref={navRef} className={`spa-nav ${isScrolled ? 'scrolled' : ''}`}>
            <div className="nav-left">
                {props.navLogo && (
                    <img className="nav-logo" src={props.navLogo} alt="logo" />
                )}
                <div className="hamburger" onClick={() => setMenuOpen(!menuOpen)}>
                    ☰
                </div>
                <ul className={`nav-list ${menuOpen ? 'active' : ''}`}>
                    {navItems.map((item, index) => (
                        <li key={index}>
                            <Link to={item.link.url}  className={item.active ? 'nav-active-link' : ''}>
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
        </>
    );
}
export default Navigation;