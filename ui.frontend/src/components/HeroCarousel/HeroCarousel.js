import React, { useState, useEffect } from "react";
import { MapTo } from "@adobe/aem-react-editable-components";
import "./HeroCarousel.css";

const HeroCarousel = ({ slides = [] }) => {
  const [index, setIndex] = useState(0);

  // Auto-play timer for sliding every 3 seconds
  useEffect(() => {
    if (slides.length <= 1) return;
    const interval = setInterval(() => {
      setIndex((prev) => (prev + 1) % slides.length);
    }, 10000);
    return () => clearInterval(interval);
  }, [slides.length]);

  if (!slides.length) {
    return <div>No Slides Available</div>;
  }

  const nextSlide = () => {
    setIndex((prev) => (prev + 1) % slides.length);
  };

  const prevSlide = () => {
    setIndex((prev) => (prev - 1 + slides.length) % slides.length);
  };

  const slide = slides[index];
  console.log("Rendering slide:", slide);

  return (
    <div
      className="hero-carousel"
      style={{ backgroundColor: slide.bgColor }}
    >
      {/* Preload all backgrounds silently to eliminate render flash */}
      <div style={{ display: 'none' }}>
        {slides.map((s, i) => s.bgImage ? <img key={i} src={s.bgImage} alt="preload" /> : null)}
      </div>

      {/* Wrapper keyed by index to trigger mount animation every slide change */}
      <div key={index} className="hero-slide" style={{ width: '100%', height: '100%' }}>
        {/* Vovel-shaped background image container mapped to bgImage */}
        {slide.bgImage && (
        <div 
          className="hero-bg-image-vovel" 
          style={{ backgroundImage: `url(${slide.bgImage})` }} 
        />
      )}
      <div className="hero-container">

        {/* LEFT CONTENT */}
        <div className="hero-content">
          <p className="pretitle">{slide.preTitle}</p>

          <h1 className="title">{slide.title}</h1>

          <div className="tagline">
            {slide.subtitle}
          </div>


          <div className="price">
            {slide.price}<span>/{slide.duration}</span>
          </div>

          <a href={slide.ctaLink} className="cta-btn">
            {slide.ctaText}
          </a>
        </div>

        {/* RIGHT IMAGE - Optional decorative image */}
        {slide.decorativeImage && (
          <div className="hero-image">
            <img src={slide.decorativeImage} alt="decorative" />
          </div>
        )}
      </div>
      </div>

      {/* NAVIGATION WITH PAGINATION IN CENTER */}
      <div className="navigation-container">
        <button className="nav prev" onClick={prevSlide}>←</button>
        <div className="pagination">
          {index + 1} / {slides.length}
        </div>
        <button className="nav next" onClick={nextSlide}>→</button>
      </div>
    </div>
  );
};

export default HeroCarousel;