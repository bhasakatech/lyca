import React, { useState, useEffect, useRef } from "react";
import "./BestSimPlans.css";

const BestSimPlans = ({
  heading,
  fragmentParentPath,
  hideText,
  ctaLabel,
  ctaLink,
}) => {
  const [hideCommon, setHideCommon] = useState(false);
  const [viewMode, setViewMode] = useState("table");
  const [activeProvider, setActiveProvider] = useState("LYCA");
  const [canScrollLeft, setCanScrollLeft] = useState(false);
  const [canScrollRight, setCanScrollRight] = useState(false);
  const tableWrapperRef = useRef(null);

  // State for pixel-perfect sliding highlighter
  const [sliderStyle, setSliderStyle] = useState({ left: 0, width: 0 });
  const activeColRef = useRef(null);

  const displayHeading = heading || "SEARCHING FOR THE BEST SIM ONLY DEAL?";
  const displayCtaLabel = ctaLabel || "Add More SIM";

  const providers = [
    { id: 'LYCA', name: 'LYCA', price: '$5', data: '5GB', minutes: '1000', texts: '1000', contract: 'Not Contract', priceRise: 'NO', roaming: 'Yes' },
    { id: 'EE', name: 'EE', price: '$27', data: '5GB', minutes: 'Unlimited', texts: 'Unlimited', contract: 'Not Contract', priceRise: 'NO', roaming: 'NO' },
    { id: 'O2', name: 'O2', price: '$22', data: '3GB', minutes: 'Unlimited', texts: 'Unlimited', contract: '1 Month', priceRise: 'Yes', roaming: 'NO' },
    { id: 'Three', name: 'Three', price: '$22', data: '12GB', minutes: 'Unlimited', texts: 'Unlimited', contract: '1 Month', priceRise: 'Yes', roaming: 'NO' },
    { id: 'Giffgaff', name: 'Giffgaff', price: '$6', data: '2GB', minutes: 'Unlimited', texts: 'Unlimited', contract: 'No Contract', priceRise: 'Yes', roaming: 'Yes' },
    { id: 'O3', name: 'O2', price: '$22', data: '3GB', minutes: 'Unlimited', texts: 'Unlimited', contract: '1 Month', priceRise: 'Yes', roaming: 'NO' },
    { id: 'Threee', name: 'Three', price: '$22', data: '12GB', minutes: 'Unlimited', texts: 'Unlimited', contract: '1 Month', priceRise: 'Yes', roaming: 'NO' },
    { id: 'Giffgafff', name: 'Giffgaff', price: '$6', data: '2GB', minutes: 'Unlimited', texts: 'Unlimited', contract: 'No Contract', priceRise: 'Yes', roaming: 'Yes' },
    { id: 'O4', name: 'O2', price: '$22', data: '3GB', minutes: 'Unlimited', texts: 'Unlimited', contract: '1 Month', priceRise: 'Yes', roaming: 'NO' },
    { id: 'Threeee', name: 'Three', price: '$22', data: '12GB', minutes: 'Unlimited', texts: 'Unlimited', contract: '1 Month', priceRise: 'Yes', roaming: 'NO' },
    { id: 'Giffgaffff', name: 'Giffgaff', price: '$6', data: '2GB', minutes: 'Unlimited', texts: 'Unlimited', contract: 'No Contract', priceRise: 'Yes', roaming: 'Yes' }
  ];

  const features = [
    { key: 'price', label: '£ Monthly Cost' },
    { key: 'data', label: 'Data', icon: '📊' },
    { key: 'minutes', label: 'Minutes', icon: '📞', isCommonCheck: 'Unlimited' },
    { key: 'texts', label: 'Texts', icon: '💬', isCommonCheck: 'Unlimited' },
    { key: 'contract', label: 'Contract', icon: '📄' },
    { key: 'priceRise', label: 'Annual Price Rise', icon: '⬆' },
    { key: 'roaming', label: 'Free EU Roaming', icon: '🌍' }
  ];

  const visibleFeatures = features.filter(f => !(hideCommon && f.isCommonCheck));

  useEffect(() => {
    const updateSlider = () => {
      if (activeColRef.current && viewMode === "table") {
        setSliderStyle({
          left: activeColRef.current.offsetLeft,
          width: activeColRef.current.offsetWidth
        });
      }
    };

    setTimeout(updateSlider, 10);
    window.addEventListener("resize", updateSlider);

    return () => window.removeEventListener("resize", updateSlider);
  }, [activeProvider, viewMode, hideCommon]);

  useEffect(() => {
    const checkScrollState = () => {
      if (tableWrapperRef.current && viewMode === "table") {
        const { scrollLeft, scrollWidth, clientWidth } = tableWrapperRef.current;
        setCanScrollLeft(scrollLeft > 5);
        setCanScrollRight(scrollLeft + clientWidth < scrollWidth - 5);
      }
    };
    checkScrollState();
    const timer = setTimeout(checkScrollState, 100);
    window.addEventListener("resize", checkScrollState);
    return () => {
      clearTimeout(timer);
      window.removeEventListener("resize", checkScrollState);
    };
  }, [viewMode, hideCommon, activeProvider]);

  const handleTableScroll = () => {
    if (tableWrapperRef.current) {
      const { scrollLeft, scrollWidth, clientWidth } = tableWrapperRef.current;
      setCanScrollLeft(scrollLeft > 5);
      setCanScrollRight(scrollLeft + clientWidth < scrollWidth - 5);
    }
  };

  const scrollTable = (direction) => {
    if (tableWrapperRef.current) {
      const scrollAmount = direction === "right" ? 160 : -160;
      tableWrapperRef.current.scrollBy({ left: scrollAmount, behavior: 'smooth' });
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

                <div
                  className="column-highlighter"
                  style={{
                    left: `${sliderStyle.left}px`,
                    width: `${sliderStyle.width}px`
                  }}
                />

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
                      {providers.map(p => {
                        const isActive = activeProvider === p.id;
                        return (
                          <th
                            key={p.id}
                            ref={isActive ? activeColRef : null}
                            className={`provider-col ${isActive ? 'is-active' : ''}`}
                            onClick={() => setActiveProvider(p.id)}
                          >
                            {p.name}
                          </th>
                        )
                      })}
                    </tr>
                  </thead>
                  <tbody>
                    {visibleFeatures.map((feat) => (
                      <tr key={feat.key}>
                        <td className="feature-col">
                          {feat.icon && <span className="feature-col-icon" role="img" aria-hidden="true">{feat.icon}</span>}
                          {feat.label}
                        </td>
                        {providers.map(p => {
                          const isActive = activeProvider === p.id;
                          let cellClass = `provider-col cell-interactive ${isActive ? 'is-active' : ''}`;

                          if (feat.key === 'price') {
                            cellClass += isActive ? ' val-highlight-blue' : ' val-bold-blue';
                          }
                          if (feat.key === 'data') {
                            cellClass += isActive ? ' val-large-data' : '';
                          }

                          return (
                            <td
                              key={`${p.id}-${feat.key}`}
                              className={cellClass}
                              onClick={() => setActiveProvider(p.id)}
                            >
                              {p[feat.key]}
                            </td>
                          );
                        })}
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
              {providers.map(p => {
                const isActive = activeProvider === p.id;
                return (
                  <div
                    key={p.id}
                    className={`provider-card ${isActive ? 'is-active' : ''}`}
                    onClick={() => setActiveProvider(p.id)}
                  >
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
                          <span className={`provider-card__row-value ${feat.key === 'price' ? 'large-price' : ''} ${feat.key === 'data' ? 'bold-data' : ''}`}>
                            {p[feat.key]}
                          </span>
                        </div>
                      ))}
                    </div>
                    {isActive && <div className="provider-card__active-badge"><span role="img" aria-label="check">✅</span> Selected</div>}
                  </div>
                );
              })}
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

      {fragmentParentPath && (
        <div className="best-sim-plans__meta">
          <small>Fragment Path: {fragmentParentPath}</small>
        </div>
      )}
    </section>
  );
};

export default BestSimPlans;