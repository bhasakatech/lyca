import React, { useState } from "react";
import "./LycaWorldWide.css";

const LycaWorldWide = (props) => {
  const [search, setSearch] = useState("");
  const [showAll, setShowAll] = useState(false);
  const filteredCountries = props.countries?.filter((country) =>
    country.countryName?.toLowerCase().includes(search.toLowerCase())
  );
  console.log(filteredCountries);
  
  //SHOW ONLY 5 INITIALLY
  const visibleCountries = showAll
    ? filteredCountries
    : filteredCountries?.slice(0, 5);

  return (
    <>
      <div className="worldwide-container">
        <h1>{props.worldwideHeading}</h1>
        <p>{props.worldwideSubHeading}</p>
        <div className="search-box">
          <span className="icon">🔍</span>
          <input
            type="text"
            placeholder={props.searchPlaceholderText}
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>
        <div className="countries">
          {visibleCountries?.length > 0 ? (
            visibleCountries.map((country, index) => (
                <a
                    href={country?.logoLink + ".html"}
                    className="country-link"
                  >
              <div className="country-card" key={index}>
                <img
                  src={country?.countryLogo}
                  alt={country?.countryName}
                />
                <p>{country?.countryName}</p>
              </div>
              </a>
            ))
          ) : (
            <p className="no-data">No countries found</p>
          )}
        </div>
        {/* BUTTON LOGIC */}
        {filteredCountries?.length > 5 && (
          <button
            className="view-more"
            onClick={() => setShowAll(!showAll)}
          >
            {showAll ? "Show Less" : props.ctaLabel || "View More"}
          </button>
        )}
      </div>
    </>
  );
};

export default LycaWorldWide;