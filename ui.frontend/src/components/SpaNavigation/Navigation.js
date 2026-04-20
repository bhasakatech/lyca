import React, { useState, useEffect, useRef } from 'react';
import './Navigation.css';
import { Link } from 'react-router-dom';

const Navigation = (props) => {
   
    const [menuOpen, setMenuOpen] = useState(false);
    const [isScrolled, setIsScrolled] = useState(false);
    const [navHeight, setNavHeight] = useState(0);
    const navRef = useRef(null);
    const navItems = props.items || [];

    const basket = JSON.parse(localStorage.getItem('basket')) || [];
    const [cartItemsCount, setCartItemsCount] = useState(basket.length);
    
    const placeholderRef = useRef(null);

    const locale = props.locale ?? [];

    const [selectedLocale, setSelectedLocale] = useState(locale[0] || null);
    const [open, setOpen] = useState(false);

    useEffect(() => {
        if (locale.length > 0) {
            setSelectedLocale(locale[0]);
        }
    }, [locale]);

    const remainingLocales = locale.filter(
        (item) => item !== selectedLocale
    );

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
                                <Link to={item.link.url} className={item.active ? 'nav-active-link' : ''}>
                                    {item.title}
                                </Link>
                            </li>
                        ))}
                    </ul>
                </div>
                <div className="nav-right">
                    <button className="quick-btn">
                        <Link to={props.buttonLink} className="nav-btn">
                            {props.buttonLabel}
                        </Link>
                    </button>

                    <Link to={props.cartPageLocation}>
                        <button id="nav-cart">
                            <img src={props.cart} alt="Cart" />
                            <div className="nav-cart-empty">{cartItemsCount}</div>
                        </button>
                    </Link>

                    <div className="nav-locale-wrapper">

                        {selectedLocale && (
                            <>
                                {/* Selected Locale */}
                                <button id="nav-localecountry" onClick={() => setOpen(!open)} >
                                    <span>{selectedLocale.language}</span>
                                    <img src={selectedLocale.region} alt={`${selectedLocale.language} flag`}/>
                                </button>

                                {/* Dropdown */}
                                {open && remainingLocales.length > 0 && (
                                    <ul className="locale-dropdown">
                                        {remainingLocales.map((item, index) => (
                                            <li key={index}>
                                                <button className="locale-item"
                                                    onClick={() => {
                                                        setSelectedLocale(item);
                                                        setOpen(false);
                                                    }}
                                                >
                                                    <span>{item.language}</span>
                                                    <img src={item.region} alt={`${item.language} flag`} />
                                                </button>
                                            </li>
                                        ))}
                                    </ul>
                                )}
                            </>
                        )}
                    </div>
                </div>
            </nav>
        </>
    );
}
export default Navigation;