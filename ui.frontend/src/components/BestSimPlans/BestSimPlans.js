import React, { useState, useEffect, useRef } from "react";
import "./BestSimPlans.css";

const BestSimPlans = ({
  heading,
  fragmentParentPath,
  hideText,
  ctaLabel,
  ctaLink,
  plans
}) => {
  const [hideCommon, setHideCommon] = useState(false);
  const [viewMode, setViewMode] = useState("table");
  const [canScrollLeft, setCanScrollLeft] = useState(false);
  const [canScrollRight, setCanScrollRight] = useState(false);
  const tableWrapperRef = useRef(null);

  const displayHeading = heading || "SEARCHING FOR THE BEST SIM ONLY DEAL?";
  const displayCtaLabel = ctaLabel || "Add More SIM";


  const features = [
    { key: 'monthlyCost', label: '£ Monthly Cost' },
    { key: 'data', label: 'Data', icon: '📊' },
    { key: 'minutes', label: 'Minutes', icon: '📞', isCommonCheck: 'Unlimited' },
    { key: 'texts', label: 'Texts', icon: '💬', isCommonCheck: 'Unlimited' },
    { key: 'contract', label: 'Contract', icon: '📄' },
    { key: 'annualPriceRise', label: 'Annual Price Rise', icon: '⬆' },
    { key: 'freeEuRoaming', label: 'Free EU Roaming', icon: '🌍' }
  ];

  const visibleFeatures = features.filter(f => !(hideCommon && f.isCommonCheck));

  useEffect(() => {
    const checkScrollState = () => {
      if (tableWrapperRef.current && viewMode === "table") {
        const { scrollLeft, scrollWidth, clientWidth } = tableWrapperRef.current;
        setCanScrollLeft(Math.ceil(scrollLeft) > 5);
        setCanScrollRight(Math.ceil(scrollLeft) + clientWidth < scrollWidth - 5);
      }
    };
    checkScrollState();
    const timer = setTimeout(checkScrollState, 100);
    window.addEventListener("resize", checkScrollState);
    return () => {
      clearTimeout(timer);
      window.removeEventListener("resize", checkScrollState);
    };
  }, [viewMode, hideCommon]);

  const handleTableScroll = () => {
    if (tableWrapperRef.current) {
      const { scrollLeft, scrollWidth, clientWidth } = tableWrapperRef.current;
      setCanScrollLeft(Math.ceil(scrollLeft) > 5);
      setCanScrollRight(Math.ceil(scrollLeft) + clientWidth < scrollWidth - 5);
    }
  };

  const scrollTable = (direction) => {
    if (tableWrapperRef.current) {
      const providerCol = tableWrapperRef.current.querySelector('.provider-col');
      const scrollAmount = providerCol ? providerCol.offsetWidth : 160;
      tableWrapperRef.current.scrollBy({ left: direction === "right" ? scrollAmount : -scrollAmount, behavior: 'smooth' });
    }
  };

  return (
    <section className="best-sim-plans">
      {displayHeading && <h2 className="best-sim-plans__heading">{displayHeading}</h2>}

      {!hideText && (
        <p className="best-sim-plans__description" style={{ display: "none" }}>
          Search and compare the best SIM only plans.
        </p>
      )}

      <div className="best-sim-plans__interactive-container">
        {viewMode === "table" ? (
          <div className="best-sim-plans__table-container-relative" style={{ position: "relative" }}>
            {canScrollLeft && (
              <div className="scroll-hint-bubble arrow-left" onClick={() => scrollTable('left')}>
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                  <line x1="19" y1="12" x2="5" y2="12"></line>
                  <polyline points="12 19 5 12 12 5"></polyline>
                </svg>
              </div>
            )}
            {canScrollRight && (
              <div className="scroll-hint-bubble arrow-right" onClick={() => scrollTable('right')}>
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                  <line x1="5" y1="12" x2="19" y2="12"></line>
                  <polyline points="12 5 19 12 12 19"></polyline>
                </svg>
              </div>
            )}
            <div className="best-sim-plans__table-wrapper" onScroll={handleTableScroll} ref={tableWrapperRef}>
              <div className="table-scroll-container">



                <table className="best-sim-plans__table">
                  <thead>
                    <tr>
                      <th className="feature-col">
                        <div className="feature-header-wrapper">
                          Features
                          <button
                            className="view-toggle-btn-icon small-icon"
                            onClick={() => setViewMode('card')}
                            title="Switch to Card View"
                          >
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                              <rect x="3" y="3" width="7" height="7"></rect>
                              <rect x="14" y="3" width="7" height="7"></rect>
                              <rect x="14" y="14" width="7" height="7"></rect>
                              <rect x="3" y="14" width="7" height="7"></rect>
                            </svg>
                          </button>
                        </div>
                      </th>
                      {plans.map(p => (
                        <th key={p.id} className="provider-col">
                          {p.name}
                        </th>
                      ))}
                    </tr>
                  </thead>
                  <tbody>
                    {visibleFeatures.map((feat) => (
                      <tr key={feat.key}>
                        <td className="feature-col">
                          {feat.icon && <span className="feature-col-icon" role="img" aria-hidden="true">{feat.icon}</span>}
                          {feat.label}
                        </td>
                        {plans.map(p => (
                          <td
                            key={`${p.id}-${feat.key}`}
                            className={`provider-col ${feat.key === 'monthlyCost' ? 'val-bold-blue' : ''}`}
                          >
                            {p[feat.key]}
                          </td>
                        ))}
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        ) : (
          <div className="best-sim-plans__cards-wrapper">
            <div className="cards-header-controls">
              <button
                className="view-toggle-btn-icon small-icon"
                onClick={() => setViewMode('table')}
                title="Switch to Table View"
              >
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round">
                  <line x1="8" y1="6" x2="21" y2="6"></line>
                  <line x1="8" y1="12" x2="21" y2="12"></line>
                  <line x1="8" y1="18" x2="21" y2="18"></line>
                  <line x1="3" y1="6" x2="3.01" y2="6"></line>
                  <line x1="3" y1="12" x2="3.01" y2="12"></line>
                  <line x1="3" y1="18" x2="3.01" y2="18"></line>
                </svg>
              </button>
            </div>
            <div className="best-sim-plans__cards-grid">
              {plans.map(p => (
                <div key={p.id} className="provider-card">
                  <div className="provider-card__header">
                    <h3>{p.name}</h3>
                  </div>
                  <div className="provider-card__body">
                    {visibleFeatures.map(feat => (
                      <div className="provider-card__row" key={feat.key}>
                        <span className="provider-card__row-label">
                          {feat.icon && <span className="card-icon">{feat.icon}</span>}
                          {feat.label}
                        </span>
                        <span className={`provider-card__row-value ${feat.key === 'monthlyCost' ? 'large-price' : ''} ${feat.key === 'data' ? 'bold-data' : ''}`}>
                          {p[feat.key]}
                        </span>
                      </div>
                    ))}
                  </div>
                </div>
              ))}
            </div>
          </div>
        )}
      </div>

      <div className="best-sim-plans__bottom-controls">
        <label className="best-sim-plans__hide-common">
          <input
            type="checkbox"
            checked={hideCommon}
            onChange={(e) => setHideCommon(e.target.checked)}
          />
          Hide Common Features
        </label>

        <div className="best-sim-plans__cta">
          <a className="best-sim-plans__button" href={ctaLink || "#"}>
            {displayCtaLabel}
          </a>
        </div>
      </div>

      {/* {fragmentParentPath && (
        <div className="best-sim-plans__meta">
          <small>Fragment Path: {fragmentParentPath}</small>
        </div>
      )} */}
    </section>
  );
};

export default BestSimPlans;