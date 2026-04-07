import React from "react";
// import { MapTo } from "@adobe/aem-react-editable-components";
import "./Footer.css";

const Footer = (props) => {
  console.log("Footer Props:", props);

  const {
    lycaLogo,
    lycaDescription,
    footerLinks = [],
    copyright
  } = props;

  return (
    <footer className="footer">
      <div className="footer-inner">
    
      <div className="footer-container">

        {/* Logo + Description */}
        <div className="footer-column">
          {lycaLogo && (
            <img src={lycaLogo} alt="Logo" className="footer-logo" />
          )}

          <div
            dangerouslySetInnerHTML={{ __html: lycaDescription }}
          />
        </div>

        {/* Dynamic Columns */}
        {footerLinks.map((column, index) => (
          <div className="footer-column" key={index}>
            <h4>{column.title}</h4>

            <ul>
              {column.links?.map((link, i) => (
                <li key={i}>
                  <a href={link.navigationURL}>
                    {link.navigationLabel}
                  </a>
                </li>
              ))}
            </ul>
          </div>
        ))}

      </div>

      {/* Bottom */}
      <div
        className="footer-bottom"
        dangerouslySetInnerHTML={{ __html: copyright }}
      />

    </div>

    </footer>
  );
};

export default Footer;
