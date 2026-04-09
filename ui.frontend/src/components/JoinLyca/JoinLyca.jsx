import React, { useState } from "react";
import "./JoinLyca.css";

const JoinLyca = (props) => {
const [mobile, setMobile] = useState("");
const [code, setCode] = useState("+91");
const { ctas: ctaButtons = [] } = props;
const { ctas = [] } = props;
const [activeIndex, setActiveIndex] = useState(0); 

  if (!ctaButtons || ctaButtons.length === 0) {
    return <div>No CTA Buttons configured</div>;
  }
  return (
    <div className="cta-card">

      {/* LEFT */}
      <div className="left">
        <h1 className="title">{props.title}</h1>
         <div dangerouslySetInnerHTML={{ __html: props.description }}></div>

        <div className="icons">
          {props.ctaItems?.map((item, i) => (
            <a key={i} href={item.link}>
              <img src={item.icon} alt={item.label} />
              <span>{item.label}</span>
            </a>
          ))}
        </div>
      </div>
<div className="right-lyca">
  <h2>{props.heading}</h2>

<div className="btn-group">
      {ctas.map((item, index) => (
        <a
          key={`${item.text}-${index}`}
          href={item.link || "#"}
          className={`primary-btn ${activeIndex === index ? "active" : ""}`}
          onClick={(e) => {
            e.preventDefault(); // prevent navigation (optional)
            setActiveIndex(index);
          }}
        >
          {item.text}
        </a>
      ))}
    </div>
  <div className="input-wrapper">
  <div className="input-group">
    
    <select
      className="code-dropdown"
      value={code}
      onChange={(e) => setCode(e.target.value)}
    >
      <option value="+1">+1</option>
      <option value="+91">+91</option>
      <option value="+44">+44</option>
      <option value="+90">+90</option>
    </select>

 <div className="divider"></div>

  <input
  className="mobile-input"
  value={mobile}
  onChange={(e) => setMobile(e.target.value)}

   placeholder={
  window.innerWidth <= 400
    ? props.placeholder
    : `${props.placeholder} (e.g. 1234567890)`
   }
  />
  </div>
</div>
  
   <a href={props.submitLink} className="proceed-btn">
    {props.submitText}
</a>
  <p
  className="join-Lyca-footer"
  dangerouslySetInnerHTML={{
    __html: props.promotionText
      ? props.promotionText.replace(/href="(\/content[^".]*)"/g, 'href="$1.html"')
      : ""
  }}
  />  
  </div>
  </div>
  );
};
export default JoinLyca;


