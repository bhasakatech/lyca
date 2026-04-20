import React from 'react';
import './Loader.css';

const Loader = ({ fullScreen = false, message = "" }) => {
  return (
    <div className={`lyca-loader-wrapper ${fullScreen ? 'fullscreen' : ''}`}>
      <div className="lyca-loader-container">
        {/* SVG ViewBox natively scales exactly as defined flexibly in CSS */}
        <svg viewBox="0 0 100 100" className="lyca-heart-svg">
          {/* Subtle background track */}
          <path 
            className="lyca-flow-track"
            d="M 50 30 C 45 10, 10 10, 10 35 C 10 60, 50 85, 50 85 C 50 85, 90 60, 90 35 C 90 10, 55 10, 50 30 Z" 
          />
          
          {/* Lyca Blue sweeping path */}
          <path 
            className="lyca-flow-blue"
            d="M 50 30 C 45 10, 10 10, 10 35 C 10 60, 50 85, 50 85 C 50 85, 90 60, 90 35 C 90 10, 55 10, 50 30 Z" 
            pathLength="100"
          />
          
          {/* Lyca Green sweeping path */}
          <path 
            className="lyca-flow-green"
            d="M 50 30 C 45 10, 10 10, 10 35 C 10 60, 50 85, 50 85 C 50 85, 90 60, 90 35 C 90 10, 55 10, 50 30 Z" 
            pathLength="100"
          />
        </svg>
      </div>
      
      {/* Optional loading messaging */}
      {message && <div className="lyca-loader-text">{message}</div>}
    </div>
  );
};

export default Loader;
