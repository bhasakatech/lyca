import React, { useState, useEffect } from "react";
import { MapTo } from "@adobe/aem-react-editable-components";
import "./HeroCarousel.css";

const HeroCarousel = ({ slides = [], slideTime = 5000 }) => {
  const [index, setIndex] = useState(0);


  useEffect(() => {
    if (slides.length <= 1) return;

    let delay = parseInt(slideTime, 10);
    if (isNaN(delay) || delay <= 0) {
      delay = 5000; // default 5 seconds
    } else if (delay < 100) {
      delay = delay * 1000; // convert seconds to milliseconds if authored as seconds
    }

    const interval = setInterval(() => {
      setIndex((prev) => (prev + 1) % slides.length);
    }, delay);
    
    return () => clearInterval(interval);
  }, [slides.length, slideTime]);
  console.log("HeroCarousel received slides:", slides);

if (!slides.length) {
  return (
    <div
      className="cq-placeholder hero-carousel hero-carousel__placeholder"
      data-emptytext="Hero Carousel - Please add slides"
    >
      <div className="hero-carousel__placeholder-box">
        <h3>Hero Carousel</h3>
        <p>No slides added yet.</p>
        <p>Please open the dialog and add slide details.</p>
      </div>
    </div>
  );
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
    
      <div style={{ display: 'none' }}>
        {slides.map((s, i) => s.bgImage ? <img key={i} src={s.bgImage} alt="preload" /> : null)}
      </div>

      
      <div key={index} className="hero-slide" style={{ width: '100%', height: '100%' }}>
      
        {slide.bgImage && (
        <div 
          className="hero-bg-image-vovel" 
          style={{ 
            backgroundImage: `url(${slide.bgImage})`,
            backgroundColor: slide.bgImageColor || 'transparent'
          }} 
        />
      )}
      <div className="hero-container">

      
        <div className="hero-content" style={{ 
          '--title-len': slide.title ? slide.title.length : 1,
          '--tagline-len': slide.subtitle ? slide.subtitle.length : 1
        }}>
          <p className="hero-banner_pretitle">{slide.preTitle}</p>

          <h1 className="hero-banner_title">{slide.title}</h1>

          <div className="hero-banner_tagline">
            {slide.subtitle}
          </div>


          <div className="hero-banner_price">
            {slide.price}<span>/{slide.duration}</span>
          </div>

          <a href={slide.ctaLink} className="hero-banner_cta-btn">
            {slide.ctaText}
          </a>
        </div>

      
        {slide.decorativeImage && (
          <div className="hero-image">
            <img src={slide.decorativeImage} alt="decorative" />
          </div>
        )}
      </div>
      </div>

     
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