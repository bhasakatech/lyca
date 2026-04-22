import React from 'react';
import './LycaFAQComponent.css';
import  { useState } from "react";

const LycaFAQComponent=(props)=>{
    const [activeIndex, setActiveIndex] = useState(0); // first open by default
  const toggleFaq = (index) => {
    setActiveIndex(index === activeIndex ? -1 : index);
  };
      const cleanHtml = (html) => {
    if (!html) return '';

    return html
      .replace(/<p>(&nbsp;|\s|<br\s*\/?>)*<\/p>/g, '')
      .replace(/>\s+</g, '><')
      .trim();
  };
    return(
        <div className="lyca-faq-container">
            <h2>{props.faqMainHeading}</h2>
            <div className="faq-list">
        {(props.items || []).map((item, index) => (
          <div className="faq-item" key={index}>
            <div className="faq-content">
            <div
              className="faq-question"
              onClick={() => toggleFaq(index)}
            >
              <span className="faq-question-text">{item.question}</span>
              <span className="faq-icon">
                {activeIndex === index ? "−" : "+"}
              </span>
            </div>

            {activeIndex === index && (
              <div
                className="faq-answer"
                dangerouslySetInnerHTML={{ __html: cleanHtml(item.ans) }}
              />
            )}

          </div>
          </div>
        ))}
      </div>
            </div>    

    );
}
export default LycaFAQComponent;